/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import static Interface.PanneauNoeuds.recherchePremierPoint;
import static Interface.PanneauNoeuds.rechercheTriangle;
import fr.insa.brenckle.projets.AppuiDouble;
import fr.insa.brenckle.projets.AppuiSimple;
import fr.insa.brenckle.projets.Barre;
import fr.insa.brenckle.projets.Noeud;
import fr.insa.brenckle.projets.NoeudSimple;
import fr.insa.brenckle.projets.Objet;
import fr.insa.brenckle.projets.TerrainPoints;
import static fr.insa.brenckle.projets.TerrainPoints.CompletePoint;
import static fr.insa.brenckle.projets.TerrainPoints.TrianglePoint;
import static fr.insa.brenckle.projets.TerrainPoints.verifieForme;
import fr.insa.brenckle.projets.TerrainSegment;
import static fr.insa.brenckle.projets.TerrainSegment.Suppsegmendouble;
import static fr.insa.brenckle.projets.TerrainSegment.creationSegment;
import static fr.insa.brenckle.projets.TerrainSegment.creationSegmentTriangle;
import fr.insa.brenckle.projets.TerrainTriangle;
import static fr.insa.brenckle.projets.TerrainTriangle.Creationtriangle;
import fr.insa.brenckle.projets.Treillis;
import fr.insa.brenckle.projets.TypeBarre;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author Guillaume R
 */
public class Controleur {
    
    private int etat;
    private Interface vue;
    private ArrayList<Objet> selection;
    private ArrayList<TerrainSegment> segments;
    private Noeud noeud1;
    
    public Controleur(Interface vue){
        this.etat = 10;
        this.vue = vue;
        this.selection = new ArrayList<Objet>();
        segments = new ArrayList<TerrainSegment>();
        noeud1 = null;
    }
    
    public void changeEtat (int etat){
        this.etat = etat;
        if (this.etat == 10){
            this.getSelection().clear();
            this.vue.getGraph().redraw();
        }
        else if (this.etat == 19){
            this.vue.getMenuPrincipal().getMenuCreation().getCreePTerrain().setDisable(false);
        }
        else if (this.etat == 20){  //clique sur "nouveau" point terrain
            this.vue.getMenuPrincipal().getMenuCreation().getSaisiePointTerrain().show();
        }
        else if (this.etat == 21){
            ArrayList<TerrainPoints> listPT = new ArrayList<TerrainPoints>(this.vue.getMenuPrincipal().getMenuCreation().getSaisiePointTerrain().getP());
            for (TerrainPoints TP: listPT){
                GraphicsContext context = this.vue.getGraph().getGraphicsContext2D();
                context.clearRect(0, 0, this.vue.getGraph().getCanvas().getWidth(), this.vue.getGraph().getCanvas().getWidth());
                TP.draw(context);
            }
            this.vue.getMenuPrincipal().getMenuCreation().getSaisiePointTerrain().close();
            this.etat = 10;
        }
        else if (this.etat == 30){
            
            ArrayList<TerrainPoints> listPT = new ArrayList<TerrainPoints>(this.vue.getMenuPrincipal().getMenuCreation().getSaisiePointTerrain().getP()); //Points rentrés par l'utilisateur
            boolean b = verifieForme(listPT);
            listPT = CompletePoint(listPT, b);
            ArrayList<TerrainPoints> listPTr = new ArrayList<TerrainPoints>(TrianglePoint(listPT, b)); //Points complétant ceux de l'utilisateur 
           
            ArrayList<TerrainSegment> listST = new ArrayList<TerrainSegment>(creationSegment(listPT, listPTr, b)); //1ère liste de segments
            ArrayList<TerrainSegment> listSTr = new ArrayList<TerrainSegment>(creationSegmentTriangle(listPT, listPTr, b)); //2ème liste de segments pour trianguler
     segments.addAll(listST); segments.addAll(listSTr);
            listSTr = Suppsegmendouble(listST, listSTr); //Supprime les doublons de segments
            
            ArrayList<TerrainTriangle> listTT = new ArrayList<TerrainTriangle>(Creationtriangle(listST, listSTr, b)); //Génère les triangles
            
            this.vue.getTreillis().setTerrainTriangles(listTT);
            for (int i=0; i<this.vue.getTreillis().getNoeuds().size(); i++){
                if ((vue.getTreillis().getNoeuds().get(i) instanceof AppuiSimple) || (vue.getTreillis().getNoeuds().get(i) instanceof AppuiDouble)){
                    vue.getTreillis().getNoeuds().remove(i);
                }
            }
            this.vue.getGraph().redraw();
            this.etat = 10;
        } 
        else if (this.etat == 40){
            int etatPanneau = this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getEtat();
            System.out.println(etatPanneau);
            
            if (etatPanneau == 0){
                TextField absNoeud = this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getAbs();
                TextField ordNoeud = this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getOrd();
                if ((!absNoeud.getText().trim().isEmpty()) && (!ordNoeud.getText().trim().isEmpty())){
                    double x = Double.parseDouble(absNoeud.getText());
                    double y = Double.parseDouble(ordNoeud.getText());
                    NoeudSimple ns = new NoeudSimple (x,y);
                    this.vue.getMenuPrincipal().getI().getTreillis().ajouteN(ns);
                    this.vue.getMenuPrincipal().getI().getGraph().redraw();
                    absNoeud.clear();
                    ordNoeud.clear();
                }  
                System.out.println(this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getSegmentSelect().toString());
            }
            else if ((etatPanneau == 1)){
                TextField coeffLambda = this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getLambda();
                TerrainSegment TSselect = this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getSegmentSelect();
                
                if ((!coeffLambda.getText().trim().isEmpty()) && (TSselect != null) && (this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getChoixNoeud().getValue().toString() == "Appui simple")){
                    ArrayList<TerrainTriangle> listTT = this.vue.getMenuPrincipal().getI().getTreillis().getTerrainTriangles();
                    AppuiSimple ap = new AppuiSimple(1, rechercheTriangle (TSselect, listTT), recherchePremierPoint(TSselect, listTT), Double.parseDouble(coeffLambda.getText()));
                    this.vue.getMenuPrincipal().getI().getTreillis().ajouteN(ap);
                    this.vue.getMenuPrincipal().getI().getGraph().redraw();
                    coeffLambda.clear();
                    System.out.println("salut");
                } 
                else if ((!coeffLambda.getText().trim().isEmpty()) && (TSselect != null) && (this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getChoixNoeud().getValue().toString() == "Appui double")){
                    ArrayList<TerrainTriangle> listTT = this.vue.getMenuPrincipal().getI().getTreillis().getTerrainTriangles();
                    AppuiDouble ad = new AppuiDouble(1, rechercheTriangle (TSselect, listTT), recherchePremierPoint(TSselect, listTT), Double.parseDouble(coeffLambda.getText()));
                    this.vue.getMenuPrincipal().getI().getTreillis().ajouteN(ad); 
                    this.vue.getMenuPrincipal().getI().getGraph().redraw();
                    coeffLambda.clear();
                }  
                
                System.out.println(TSselect.toString());
            } 
            else if (this.etat==51){
                this.selection.clear();
                this.vue.getGraph().redraw();
            }
            this.etat = 10;
            
        }
        else if (this.etat == 71){
            for (int i=0; i<this.vue.getTreillis().getNoeuds().size(); i++){
                if ((vue.getTreillis().getNoeuds().get(i) instanceof NoeudSimple) && (!vue.getMenuPrincipal().getMenuGestion().getNoeudContenu().contains(vue.getTreillis().getNoeuds().get(i).toString()))){
                    vue.getMenuPrincipal().getMenuGestion().getListNoeud().getItems().add(vue.getTreillis().getNoeuds().get(i).toString());
                    vue.getMenuPrincipal().getMenuGestion().getNoeudContenu().add(vue.getTreillis().getNoeuds().get(i).toString());
                }
            }
            this.etat = 10;
        }
        else if (this.etat == 72){
            for (int i=0; i<this.vue.getTreillis().getNoeuds().size(); i++){
                if ((vue.getTreillis().getNoeuds().get(i) instanceof AppuiSimple) && (!vue.getMenuPrincipal().getMenuGestion().getNoeudContenu().contains(vue.getTreillis().getNoeuds().get(i).toString()))){
                    vue.getMenuPrincipal().getMenuGestion().getListAppuiSimple().getItems().add(vue.getTreillis().getNoeuds().get(i).toString());
                    vue.getMenuPrincipal().getMenuGestion().getNoeudContenu().add(vue.getTreillis().getNoeuds().get(i).toString());
                }
            }  
            this.etat = 10;
        }
        else if (this.etat == 73){
            for (int i=0; i<this.vue.getTreillis().getNoeuds().size(); i++){
                if ((vue.getTreillis().getNoeuds().get(i) instanceof AppuiDouble) && (!vue.getMenuPrincipal().getMenuGestion().getNoeudContenu().contains(vue.getTreillis().getNoeuds().get(i).toString()))){
                    vue.getMenuPrincipal().getMenuGestion().getListAppuiDouble().getItems().add(vue.getTreillis().getNoeuds().get(i).toString());
                    vue.getMenuPrincipal().getMenuGestion().getNoeudContenu().add(vue.getTreillis().getNoeuds().get(i).toString());
                }
            } 
            this.etat = 10;
        }
        else if (this.etat == 74){
            for (int i=0; i<this.vue.getTreillis().getBarres().size(); i++){
                if (!vue.getMenuPrincipal().getMenuGestion().getNoeudContenu().contains(vue.getTreillis().getBarres().get(i).toString())){
                    vue.getMenuPrincipal().getMenuGestion().getListBarre().getItems().add(vue.getTreillis().getBarres().get(i).toString());
                    vue.getMenuPrincipal().getMenuGestion().getNoeudContenu().add(vue.getTreillis().getBarres().get(i).toString());
                } 
            }
            this.etat = 10;
        }        
        else if (this.etat == 100){
            this.vue.getTreillis().getBarres().clear();
            this.vue.getTreillis().getCharge().clear();
            this.vue.getTreillis().getNoeuds().clear();
            this.vue.getTreillis().getTerrainTriangles().clear();
            this.vue.getTreillis().getTypeBarre().clear();
            this.vue.getMenuPrincipal().getMenuGestion().getListNoeud().getItems().removeAll();
            this.vue.getMenuPrincipal().getMenuGestion().getListBarre().getItems().removeAll();
            this.vue.getMenuPrincipal().getMenuGestion().getListAppuiSimple().getItems().removeAll();
            this.vue.getMenuPrincipal().getMenuGestion().getListAppuiDouble().getItems().removeAll();
            this.vue.getMenuPrincipal().getMenuGestion().getNoeudContenu().clear();
            this.vue.getMenuPrincipal().getMenuCreation().getSaisiePointTerrain().getP().clear();
            this.segments.clear();
            this.vue.getGraph().redraw();
            etat = 10;
        }
    }
     
    void clicDansInterface (MouseEvent t){
        if (this.etat == 10){    //sélection
            double posX = t.getX();
            double posY = t.getY();
            Objet objSelect = this.plusProche(posX, posY, 25);
            
            if (objSelect != null){
                if (t.isShiftDown() == true){
                    this.selection.add(objSelect);
                } else if (t.isControlDown()){
                    if (selection.contains(objSelect) == true){
                        selection.remove(objSelect);
                    } else {
                        selection.add(objSelect);
                    }
                } else {
                    selection.clear();
                    selection.add(objSelect);
                }
            } else {
                selection.clear();
            }
            //gère les états pour la création d'appuis (si un segment sélectionné ou pas)
            if ((selection.size() == 1) && (selection.get(0) instanceof TerrainSegment) && (this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getEtat() == 1)){
                this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getCreer().setDisable(false);
                this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().setSegmentSelect((TerrainSegment) selection.get(0));              
            }
            else if ((selection.size() != 1) && (this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getEtat() == 1)){
                this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getCreer().setDisable(true);
                this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().setSegmentSelect(null);
            }
            
            this.vue.getGraph().redraw();
        }
        else if (this.etat == 20){
            //A FAIRE saisie des points terrain
        }
        else if (this.etat == 30){
            //A FAIRE génération automatique des points terrains
            //quand cette étape est validée, autoriser l'ajout manuel de segments
            this.changeEtat(10);
        }
        else if (this.etat == 311){
            //A FAIRE ajout de segment manuellement --> clic sur 1er pt
            this.changeEtat(312);
        } 
        else if (this.etat == 312){
            //A FAIRE ajout segment manuel --> clic sur 2ème pt --> création du segment si absence d'erreur
            this.changeEtat(311);
        }
        else if (this.etat == 40){
            //A FAIRE saisie des noeuds
        }
        else if (this.etat == 51){
            double posX = t.getX();
            double posY = t.getY();
            Noeud noeudSelect = this.noeudPlusProche(posX, posY, 25);
            
            if (noeudSelect != null){
                this.noeud1 = noeudSelect;
                this.etat = 52;
            } else {
                this.noeud1 = null;
            }
        }
        else if (this.etat == 52){
            double posX = t.getX();
            double posY = t.getY();
            Noeud noeudSelect = this.noeudPlusProche(posX, posY, 25);
            
            if (noeudSelect != null){
                TypeBarre type = new TypeBarre (1, 25, Double.MAX_VALUE, 0, 100, 100);
                Barre neuBarre = new Barre(type, this.noeud1, noeudSelect);
                this.vue.getTreillis().ajoute(neuBarre);
                this.vue.getGraph().getGraphicsContext2D().clearRect(0, 0, vue.getGraph().getCanvas().getWidth(), vue.getGraph().getCanvas().getHeight());
                this.vue.getGraph().redraw();
                this.etat = 51;
            }            
        }
    }  
    
    public void mouvementSouris (MouseEvent t){
        if (this.etat == 52){
            double posX = t.getX();
            double posY = t.getY();
            TerrainPoints TP1 = new TerrainPoints (noeud1.getAbs(), noeud1.getOrd());
            TerrainPoints TP2 = new TerrainPoints (posX, posY);
            TerrainSegment ST = new TerrainSegment (TP1, TP2);
            this.vue.getGraph().getGraphicsContext2D().clearRect(0, 0, vue.getGraph().getCanvas().getWidth(), vue.getGraph().getCanvas().getHeight());
            this.vue.getGraph().redraw();
            ST.draw(this.vue.getGraph().getGraphicsContext2D());
        }
    }
    
    public Objet plusProche(double px, double py, double distanceMax){
        Treillis treillis = this.vue.getTreillis();
        ArrayList<TerrainPoints> TP = new ArrayList<TerrainPoints>(); TP.addAll(this.vue.getMenuPrincipal().getMenuCreation().getSaisiePointTerrain().getP());
        boolean b = verifieForme(TP);
        TP = CompletePoint(TP, b);
        
        if ((this.vue.getTreillis().getBarres().isEmpty()==true) && (this.vue.getTreillis().getNoeuds().isEmpty()==true) && (this.vue.getTreillis().getTerrainTriangles().isEmpty()==true) && (TP.isEmpty() == true)){
            return null;
        } else {
            Objet objProche = TP.get(0);
            double distMin = objProche.distancePoint(px, py);
            
            for (int i=0; i<TP.size(); i++){
                if (TP.get(i).distancePoint(px, py) < distMin){
                    objProche = TP.get(i);
                    distMin = objProche.distancePoint(px, py);
                }
            } for (int i=0; i<this.vue.getTreillis().getBarres().size(); i++){  //barres
                if (this.vue.getTreillis().getBarres().get(i).distancePoint(px, py) < distMin){
                    objProche = this.vue.getTreillis().getBarres().get(i);
                    distMin = objProche.distancePoint(px, py);
                }
            } for (int i=0; i<this.vue.getTreillis().getNoeuds().size(); i++){   //noeuds
                if (this.vue.getTreillis().getNoeuds().get(i).distancePoint(px, py) < distMin){
                    objProche = this.vue.getTreillis().getNoeuds().get(i);
                    distMin = objProche.distancePoint(px, py);
                }
            } for (int i=0; i<this.vue.getTreillis().getTerrainTriangles().size(); i++){
                if (this.vue.getTreillis().getTerrainTriangles().get(i).getC1().distancePoint(px, py) < distMin){  //segments
                    objProche = this.vue.getTreillis().getTerrainTriangles().get(i).getC1();
                    distMin = objProche.distancePoint(px, py);
                }
                if (this.vue.getTreillis().getTerrainTriangles().get(i).getC2().distancePoint(px, py) < distMin){
                    objProche = this.vue.getTreillis().getTerrainTriangles().get(i).getC2();
                    distMin = objProche.distancePoint(px, py);
                }
                if (this.vue.getTreillis().getTerrainTriangles().get(i).distancePoint(px, py) < distMin){
                    objProche = this.vue.getTreillis().getTerrainTriangles().get(i).getC3();
                    distMin = objProche.distancePoint(px, py);
                }
                if (this.vue.getTreillis().getTerrainTriangles().get(i).getC1().getA().distancePoint(px, py) < distMin){  //points terrain  C1 A
                    objProche = this.vue.getTreillis().getTerrainTriangles().get(i).getC1().getA();
                    distMin = objProche.distancePoint(px, py);
                } 
                if (this.vue.getTreillis().getTerrainTriangles().get(i).getC1().getB().distancePoint(px, py) < distMin){  //C1 B
                    objProche = this.vue.getTreillis().getTerrainTriangles().get(i).getC1().getB();
                    distMin = objProche.distancePoint(px, py);
                }
                if (this.vue.getTreillis().getTerrainTriangles().get(i).getC2().getA().distancePoint(px, py) < distMin){  //C2 A
                    objProche = this.vue.getTreillis().getTerrainTriangles().get(i).getC2().getA();
                    distMin = objProche.distancePoint(px, py);
                } 
                if (this.vue.getTreillis().getTerrainTriangles().get(i).getC2().getB().distancePoint(px, py) < distMin){  //C2 B
                    objProche = this.vue.getTreillis().getTerrainTriangles().get(i).getC2().getB();
                    distMin = objProche.distancePoint(px, py);
                }
                if (this.vue.getTreillis().getTerrainTriangles().get(i).getC3().getA().distancePoint(px, py) < distMin){  //C3 A
                    objProche = this.vue.getTreillis().getTerrainTriangles().get(i).getC3().getA();
                    distMin = objProche.distancePoint(px, py);
                } 
                if (this.vue.getTreillis().getTerrainTriangles().get(i).getC3().getB().distancePoint(px, py) < distMin){  //C3 B
                    objProche = this.vue.getTreillis().getTerrainTriangles().get(i).getC3().getB();
                    distMin = objProche.distancePoint(px, py);
                }                  
                
            }
            for (int i = 0; i<segments.size(); i++){
                if(segments.get(i).distancePoint(px, py)<distMin){
                    objProche = segments.get(i);
                    distMin = objProche.distancePoint(px, py);
                }
            }
            if (distMin <= distanceMax){
                return objProche;
            } else {
                return null;
            } 
        
        }
    }
    
    public Noeud noeudPlusProche(double px, double py, double distanceMax){
        Treillis treillis = this.vue.getTreillis();
        
        if (this.vue.getTreillis().getNoeuds().isEmpty()==true){
            return null;
        } else {
            Noeud noeudProche = this.vue.getTreillis().getNoeuds().get(0);
            double distMin = noeudProche.distancePoint(px, py);
            
         for (int i=0; i<this.vue.getTreillis().getNoeuds().size(); i++){   //noeuds
            if (this.vue.getTreillis().getNoeuds().get(i).distancePoint(px, py) < distMin){
                noeudProche = this.vue.getTreillis().getNoeuds().get(i);
                distMin = noeudProche.distancePoint(px, py);
            }
        } 
        if (distMin <= distanceMax){
            return noeudProche;
        } else {
            return null;
        } 
        }
    } 
    
    public void changeCouleur(Color couleur){
        if ((this.etat == 10) && (this.selection.size()>0)){
            for (int i=0; i<selection.size(); i++){
                selection.get(i).changeCouleur(couleur);
            }
        }
        this.vue.getGraph().redraw();
    }

    /**
     * @return the selection
     */
    public ArrayList<Objet> getSelection() {
        return selection;
    }
    
    
    
}
