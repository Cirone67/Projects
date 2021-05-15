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
import static fr.insa.brenckle.projets.Calcul.matriceCarree;
import static fr.insa.brenckle.projets.Barre.PrixTreillis;
import static fr.insa.brenckle.projets.Calcul.miseSousSystem;
import static fr.insa.brenckle.projets.Calcul.vecteurCharge;
import fr.insa.brenckle.projets.Charge;
import fr.insa.brenckle.projets.Matrice;
import fr.insa.brenckle.projets.Noeud;
import fr.insa.brenckle.projets.NoeudSimple;
import fr.insa.brenckle.projets.Objet;
import static fr.insa.brenckle.projets.Stockage.enregistrer;
import static fr.insa.brenckle.projets.Stockage.telechargement;
import fr.insa.brenckle.projets.Terrain;
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
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    
    public Controleur(Interface vue) {
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
            double[] coord = vue.getMenuPrincipal().getMenuCreation().getDelimite().getCoordonnees();
            this.vue.setTerrain(new Terrain (coord[0], coord[1], coord[2], coord[3]));
        }
        else if (this.etat == 20){  //clique sur "nouveau" point terrain
            this.vue.getMenuPrincipal().getMenuCreation().getSaisiePointTerrain().show();
            this.vue.getGraph().redraw();
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
                    for (Barre barre : vue.getTreillis().getBarres()) {
                        if((barre.getDebut()==vue.getTreillis().getNoeuds().get(i)) || (barre.getFin()==vue.getTreillis().getNoeuds().get(i))){
                            vue.getMenuPrincipal().getMenuGestion().supprimeList(barre);
                            vue.getTreillis().supprime(barre);
                        }
                    }
                    vue.getMenuPrincipal().getMenuGestion().supprimeList(vue.getTreillis().getNoeuds().get(i));
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
                    this.vue.getMenuPrincipal().getMenuGestion().getListNoeud().getItems().add(ns.toString());
                    this.vue.getMenuPrincipal().getI().getGraph().redraw();
                    absNoeud.clear();
                    ordNoeud.clear();
                } 
            }
            else if ((etatPanneau == 1)){
                TextField coeffLambda = this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getLambda();
                TerrainSegment TSselect = this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getSegmentSelect();
                
                if ((!coeffLambda.getText().trim().isBlank()) && (TSselect != null) && (this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getChoixNoeud().getValue().toString().equals("Appui simple"))){
                    ArrayList<TerrainTriangle> listTT = this.vue.getMenuPrincipal().getI().getTreillis().getTerrainTriangles();
                    AppuiSimple ap = new AppuiSimple(rechercheTriangle (TSselect, listTT), recherchePremierPoint(TSselect, listTT), Double.parseDouble(coeffLambda.getText()));
                    this.vue.getMenuPrincipal().getI().getTreillis().ajouteN(ap);
                    this.vue.getMenuPrincipal().getMenuGestion().getListAppuiSimple().getItems().add(ap.toString());
                    this.vue.getMenuPrincipal().getI().getGraph().redraw();
                    coeffLambda.clear();
                } 
                else if ((!coeffLambda.getText().trim().isBlank()) && (TSselect != null) && (this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getChoixNoeud().getValue().toString().equals("Appui double"))){
                    ArrayList<TerrainTriangle> listTT = this.vue.getMenuPrincipal().getI().getTreillis().getTerrainTriangles();
                    AppuiDouble ad = new AppuiDouble(rechercheTriangle (TSselect, listTT), recherchePremierPoint(TSselect, listTT), Double.parseDouble(coeffLambda.getText()));
                    this.vue.getMenuPrincipal().getI().getTreillis().ajouteN(ad); 
                    this.vue.getMenuPrincipal().getMenuGestion().getListAppuiDouble().getItems().add(ad.toString());
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
        else if (this.etat == 60){
            if ((!this.vue.getMenuPrincipal().getMenuGestion().getNorme().getText().trim().isBlank()) && (!this.vue.getMenuPrincipal().getMenuGestion().getAngle().getText().trim().isBlank())){
               Charge charge = new Charge (vue.getMenuPrincipal().getMenuGestion().getNoeudSelect(), Double.parseDouble(vue.getMenuPrincipal().getMenuGestion().getNorme().getText()), Double.parseDouble(vue.getMenuPrincipal().getMenuGestion().getAngle().getText()));
               vue.getMenuPrincipal().getMenuGestion().getAngle().clear(); vue.getMenuPrincipal().getMenuGestion().getNorme().clear();
               vue.getTreillis().getCharge().add(charge);
            }
            vue.getGraph().redraw();
            this.etat = 10;
        }
        else if (this.etat == 61){
            vue.getTreillis().getCharge().clear();
            for (Barre b: vue.getTreillis().getBarres()){
                    b.setCouleur(Color.BLACK);
            }
            vue.getGraph().redraw();
            this.etat = 10;
        }
        else if (this.etat == 71){   //selection avec le menu gestion (états 71 à 74)
            this.selection.clear();
            this.vue.getMenuPrincipal().getMenuGestion().getSaisieCharge().setDisable(true);
            this.vue.getMenuPrincipal().getMenuGestion().getListAppuiDouble().getSelectionModel().clearSelection();
            this.vue.getMenuPrincipal().getMenuGestion().getListAppuiSimple().getSelectionModel().clearSelection();
            this.vue.getMenuPrincipal().getMenuGestion().getListBarre().getSelectionModel().clearSelection();            
            int i =0; int k=0;
            String s = this.vue.getMenuPrincipal().getMenuGestion().getListNoeud().getSelectionModel().getSelectedItem();
            if(!vue.getTreillis().getNoeuds().isEmpty()){
                while (k==0){
                    if (i==vue.getTreillis().getNoeuds().size()){
                        k=1;
                    }
                    else if (vue.getTreillis().getNoeuds().get(i).toString().equals(s)){
                        k=1;
                        this.selection.add(vue.getTreillis().getNoeuds().get(i));
                        this.vue.getMenuPrincipal().getMenuGestion().setNoeudSelect(vue.getTreillis().getNoeuds().get(i));
                        this.vue.getMenuPrincipal().getMenuGestion().getSaisieCharge().setDisable(false);           
                    }                
                    i = i+1;
                }
            }
            this.vue.getGraph().redraw();
            this.vue.getMenuPrincipal().getMenuCreation().getSuppression().setDisable(false);
            this.etat = 10;
        }
        else if (this.etat == 72){
            this.selection.clear();
            this.vue.getMenuPrincipal().getMenuGestion().getSaisieCharge().setDisable(true);
            this.vue.getMenuPrincipal().getMenuGestion().getListNoeud().getSelectionModel().clearSelection();
            this.vue.getMenuPrincipal().getMenuGestion().getListAppuiDouble().getSelectionModel().clearSelection();
            this.vue.getMenuPrincipal().getMenuGestion().getListBarre().getSelectionModel().clearSelection();            
            int i =0; int k=0;
            String s = this.vue.getMenuPrincipal().getMenuGestion().getListAppuiSimple().getSelectionModel().getSelectedItem();
            if(!vue.getTreillis().getNoeuds().isEmpty()){
                while (k==0){
                    if (i==vue.getTreillis().getNoeuds().size()){
                        k=1;
                    }
                    else if (vue.getTreillis().getNoeuds().get(i).toString().equals(s)){
                        k=1;
                        this.selection.add(vue.getTreillis().getNoeuds().get(i));
                        this.vue.getMenuPrincipal().getMenuGestion().setNoeudSelect(vue.getTreillis().getNoeuds().get(i));
                        this.vue.getMenuPrincipal().getMenuGestion().getSaisieCharge().setDisable(false);
                    }                
                    i = i+1;
                }
            }
            this.vue.getGraph().redraw();
            this.vue.getMenuPrincipal().getMenuCreation().getSuppression().setDisable(false);
            this.etat = 10;
        }
        else if (this.etat == 73){
            this.selection.clear();
            this.vue.getMenuPrincipal().getMenuGestion().getSaisieCharge().setDisable(true);
            this.vue.getMenuPrincipal().getMenuGestion().getListNoeud().getSelectionModel().clearSelection();
            this.vue.getMenuPrincipal().getMenuGestion().getListAppuiSimple().getSelectionModel().clearSelection();
            this.vue.getMenuPrincipal().getMenuGestion().getListBarre().getSelectionModel().clearSelection();
            int i =0; int k=0;
            String s = this.vue.getMenuPrincipal().getMenuGestion().getListAppuiDouble().getSelectionModel().getSelectedItem();
            if(!vue.getTreillis().getNoeuds().isEmpty()){
                while (k==0){
                    if (i==vue.getTreillis().getNoeuds().size()){
                        k=1;
                    }
                    else if (vue.getTreillis().getNoeuds().get(i).toString().equals(s)){
                        k=1;
                        this.selection.add(vue.getTreillis().getNoeuds().get(i));
                        this.vue.getMenuPrincipal().getMenuGestion().setNoeudSelect(vue.getTreillis().getNoeuds().get(i));
                        this.vue.getMenuPrincipal().getMenuGestion().getSaisieCharge().setDisable(false);
                    }                
                    i = i+1;
                }
            }
            this.vue.getGraph().redraw();
            this.vue.getMenuPrincipal().getMenuCreation().getSuppression().setDisable(false);
            this.etat = 10;
        }
        else if (this.etat == 74){
            this.selection.clear();
            int i =0; int k=0;
            this.vue.getMenuPrincipal().getMenuGestion().getListNoeud().getSelectionModel().clearSelection();
            this.vue.getMenuPrincipal().getMenuGestion().getListAppuiSimple().getSelectionModel().clearSelection();
            this.vue.getMenuPrincipal().getMenuGestion().getListAppuiDouble().getSelectionModel().clearSelection();
            String s = this.vue.getMenuPrincipal().getMenuGestion().getListBarre().getSelectionModel().getSelectedItem();
            if(!vue.getTreillis().getBarres().isEmpty()){
                while (k==0){
                    if (i==vue.getTreillis().getBarres().size()){
                        k=1;
                    }
                    else if (vue.getTreillis().getBarres().get(i).toString().equals(s)){
                        k=1;
                        this.selection.add(vue.getTreillis().getBarres().get(i));
                    }                
                    i = i+1;
                }
            }
            this.vue.getGraph().redraw();
            this.vue.getMenuPrincipal().getMenuCreation().getSuppression().setDisable(false);
            this.etat = 10;
        }        
        else if (this.etat == 100){ //réinitialiser tout
            this.vue.getTreillis().getBarres().clear();
            this.vue.getTreillis().getCharge().clear();
            this.vue.getTreillis().getNoeuds().clear();
            this.vue.getTreillis().getTerrainTriangles().clear();
            this.vue.getMenuPrincipal().getMenuGestion().getListNoeud().getItems().clear();
            this.vue.getMenuPrincipal().getMenuGestion().getListBarre().getItems().clear();
            this.vue.getMenuPrincipal().getMenuGestion().getListAppuiSimple().getItems().clear();
            this.vue.getMenuPrincipal().getMenuGestion().getListAppuiDouble().getItems().clear();
            this.vue.getMenuPrincipal().getMenuCreation().getSaisiePointTerrain().getP().clear();
            this.segments.clear();
            this.vue.getGraph().redraw();
            this.etat = 10;
        }
        else if (this.etat == 101){ //sup barre ou noeud
            for (Objet O: selection){
                    if (O instanceof Barre){
                        vue.getTreillis().supprime((Barre) O);
                        vue.getMenuPrincipal().getMenuGestion().supprimeList(O);
                    }
                    if (O instanceof Noeud){
                        for (Barre b: vue.getTreillis().getBarres()){
                            if ((b.getDebut() == (Noeud) O) || (b.getFin() == (Noeud) O)){
                                vue.getTreillis().supprime(b);
                                vue.getMenuPrincipal().getMenuGestion().supprimeList(b);
                            } 
                        }
                    vue.getTreillis().supprimeN((Noeud) O);
                    vue.getMenuPrincipal().getMenuGestion().supprimeList(O);
                    }
            }
            selection.clear();
            vue.getGraph().redraw();
            this.vue.getMenuPrincipal().getMenuCreation().getSuppression().setDisable(true);
            this.etat = 10;
        }
        else if (this.etat == 102){  //réinitialisation du terrain
            vue.getTreillis().getTerrainTriangles().clear();
            vue.getMenuPrincipal().getMenuCreation().getSaisiePointTerrain().getP().clear();
            segments.clear();
            int i=0;
            for (Noeud n:this.vue.getTreillis().getNoeuds()){  //supprime appuis et barres reliées à ces appuis
                if ((n instanceof AppuiSimple) || (n instanceof AppuiDouble)){
                    for (Barre barre : vue.getTreillis().getBarres()) {
                        if((barre.getDebut()==n) || (barre.getFin()==n)){
                            vue.getMenuPrincipal().getMenuGestion().supprimeList(barre);
                            vue.getTreillis().supprime(barre);
                        }
                    }
                    vue.getMenuPrincipal().getMenuGestion().supprimeList(vue.getTreillis().getNoeuds().get(i));
                    vue.getTreillis().getNoeuds().remove(n);
                }
                i=i+1;
            }
            selection.clear();
            vue.getGraph().redraw();
            this.etat = 10;
        }
        else if (this.etat == 110){  //Enregistrer Sous
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName(this.vue.getMenuPrincipal().getMenuEdition().getFichier() + ".txt");
            File file =  fileChooser.showSaveDialog(this.vue.getFenetre());
            if (file != null){
            this.vue.getMenuPrincipal().getMenuEdition().setFichier(file);
            enregistrer(this.vue.getTreillis(), file.getName(), file); 
            this.vue.getFenetre().setTitle(this.vue.getMenuPrincipal().getMenuEdition().getFichier().getName());
            this.vue.getMenuPrincipal().getMenuEdition().setEtatSauvegarde(1);
            }
            this.etat = 10;
        }   
        else if (this.etat == 111){  //Enregistrer
            enregistrer(this.vue.getTreillis(), this.vue.getMenuPrincipal().getMenuEdition().getFichier().getName(), this.vue.getMenuPrincipal().getMenuEdition().getFichier());
            this.etat = 10;
        }
        else if (this.etat == 112){  //Télécharger
            enregistrer(this.vue.getTreillis(), this.vue.getMenuPrincipal().getMenuEdition().getFichier().getName(), this.vue.getMenuPrincipal().getMenuEdition().getFichier());
            FileChooser fileChooser = new FileChooser();
            File file =  fileChooser.showOpenDialog(this.vue.getFenetre());
            if (file != null){
                this.vue.setTreillis(telechargement(file, file.getName()));
                this.vue.getMenuPrincipal().getMenuGestion().viderListes();
                this.vue.getMenuPrincipal().getMenuGestion().remplirListes(telechargement(file, file.getName()));
            }
            this.vue.getGraph().redraw();
            this.etat = 10;  
        }
        else if (this.etat == 113){  //Nouveau
            Stage stage = new Stage();
            Interface I = new Interface (stage);
            Scene scene = new Scene (I, 1000, 500);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("Ressources/Apparence.css").toString());    
            stage.setTitle("Treillis Meister");
            InputStream inp = this.getClass().getResourceAsStream("Ressources/Bridge Logo2.png");
            Image img = new Image(inp);        
            stage.getIcons().add(img);
            stage.show();            
            this.etat = 10;
        }
        else if (this.etat == 150){
            Matrice system = miseSousSystem(vue.getTreillis());
            if(matriceCarree(system)){
            Matrice resultat = miseSousSystem(vue.getTreillis()).resoudreSyst(vecteurCharge(vue.getTreillis()));
            int i = 0;
            for (Barre b: vue.getTreillis().getBarres()){
                double res = resultat.getMij(i, 0);
                if (res<0){
                    b.setCouleur(Color.RED);
                } else{
                    b.setCouleur(Color.GREEN);
                }
                i=i+1;
            }
            this.vue.getGraph().redraw();
            TextArea text = new TextArea (); text.setText("Matrice résultat");
            text.setText(resultat.toString());
            Scene s = new Scene(text);
            Stage stage = new Stage();
            stage.setScene(s);
            stage.show();
            }else{
              Alert f = new Alert(Alert.AlertType.WARNING);
                    f.setHeaderText("Attention");
                    f.setContentText("Treillis hyperstatique. Veuillez le rendre isostatique");
                    f.showAndWait();  
                    this.vue.getGraph().redraw();  
            }
            this.etat = 10;
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
            this.verifieSelection();
            this.vue.getGraph().redraw();
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
            System.out.println(noeudSelect.toString());
            
            if (noeudSelect != null){
                int i =0; int k=0;
                while (k==0){
                    if (this.vue.getMenuPrincipal().getMenuCreation().getTypeBarre().getValue().equals(this.vue.getTreillis().getTypeBarre().get(i).getNom())){  //problème avec lisTypeBarre (outofbounds lenght 0)
                        k=1;
                    }
                    i=i+1;
                }
                TypeBarre type = this.vue.getTreillis().getTypeBarre().get(i-1);
                Barre neuBarre = new Barre(type, this.noeud1, noeudSelect);
                Boolean b = neuBarre.RestrictionL();
                if (b==false){
                    Alert f = new Alert(Alert.AlertType.WARNING);
                    f.setHeaderText("Attention");
                    f.setContentText("Longueur impossible pour ce type de barre");
                    f.showAndWait();  
                    this.vue.getGraph().redraw();
                }else{
                    this.vue.getTreillis().ajoute(neuBarre);
                    Double prix = PrixTreillis(vue.getTreillis().getBarres());
                    this.vue.getMenuPrincipal().getMenuGestion().getPrix().setText(Double.toString(prix) + " €");
                    this.vue.getMenuPrincipal().getMenuGestion().getListBarre().getItems().add(neuBarre.toString());
                    this.vue.getGraph().getGraphicsContext2D().clearRect(0, 0, vue.getGraph().getCanvas().getWidth(), vue.getGraph().getCanvas().getHeight());
                    this.vue.getGraph().redraw();
                }
                this.etat = 51;
            }            
        }
    } 
    
    public void verifieSelection(){
        //gère les états pour la création d'appuis (si un segment sélectionné ou pas)
        if ((selection.size() == 1) && (selection.get(0) instanceof TerrainSegment) && (this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getEtat() == 1)){
            this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getCreer().setDisable(false);
            this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().setSegmentSelect((TerrainSegment) selection.get(0));              
        }
        else if ((selection.size() != 1) && (this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getEtat() == 1)){
            this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().getCreer().setDisable(true);
            this.vue.getMenuPrincipal().getMenuCreation().getPanneauNoeuds().setSegmentSelect(null);
        }
        //gère les états pour la création de charges (un seul noeud doit être sélectionné)
        if ((selection.size() == 1) && (selection.get(0) instanceof Noeud)){
            this.vue.getMenuPrincipal().getMenuGestion().getSaisieCharge().setDisable(false);
            this.vue.getMenuPrincipal().getMenuGestion().setNoeudSelect((Noeud) selection.get(0));
        }
        else {
            this.vue.getMenuPrincipal().getMenuGestion().getSaisieCharge().setDisable(true);
            this.vue.getMenuPrincipal().getMenuGestion().setNoeudSelect(null); 
        }  
        //gère les états pour la suppression (on ne peut pas supprimer de segment terrain)
        int d =0;
        for (Objet O: this.selection){
            if ((O instanceof TerrainSegment) || (O instanceof TerrainPoints)){
                d=1;
            }
        }
        if (selection.size() == 0){ d=1; }
        if (d==0){
            this.vue.getMenuPrincipal().getMenuCreation().getSuppression().setDisable(false);     
        } else {this.vue.getMenuPrincipal().getMenuCreation().getSuppression().setDisable(true);}
        
        this.vue.getGraph().redraw();
                    
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
        Objet objProche = null;
        double distMin = Double.MAX_VALUE;
        
        if (!TP.isEmpty()){  //1ers points terrain
            objProche = TP.get(0);
            distMin = objProche.distancePoint(px, py); 
            TP = CompletePoint(TP, b);
            for (int i=0; i<TP.size(); i++){
                if (TP.get(i).distancePoint(px, py) < distMin){
                    objProche = TP.get(i);
                    distMin = objProche.distancePoint(px, py);
                }
            }
            
        }if (!this.vue.getTreillis().getTerrainTriangles().isEmpty()){ //segments et points terrain
            if (objProche == null){ 
                objProche = this.vue.getTreillis().getTerrainTriangles().get(0).getC1();
                distMin = objProche.distancePoint(px, py);
            }
            for (int i=0; i<this.vue.getTreillis().getTerrainTriangles().size(); i++){
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
//                System.out.println(objProche.toString());
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
        } if (!this.vue.getTreillis().getNoeuds().isEmpty()){  //Noeuds
            if (objProche == null){ 
                objProche = this.vue.getTreillis().getNoeuds().get(0);
                distMin = objProche.distancePoint(px, py);
            }  
            for (int i=0; i<this.vue.getTreillis().getNoeuds().size(); i++){   
                if (this.vue.getTreillis().getNoeuds().get(i).distancePoint(px, py) < distMin){
                    objProche = this.vue.getTreillis().getNoeuds().get(i);
                    distMin = objProche.distancePoint(px, py);
                }
            } 
            
        } if (!this.vue.getTreillis().getBarres().isEmpty()){ //Barres
            if (objProche == null){ 
                objProche = this.vue.getTreillis().getBarres().get(0);
                distMin = objProche.distancePoint(px, py);
            }
            for (int i=0; i<this.vue.getTreillis().getBarres().size(); i++){  
                if (this.vue.getTreillis().getBarres().get(i).distancePoint(px, py) < distMin){
                    objProche = this.vue.getTreillis().getBarres().get(i);
                    distMin = objProche.distancePoint(px, py);
                }
            }            
            
        } if(!this.segments.isEmpty()){
            if (objProche == null){ 
                objProche = this.segments.get(0);
                distMin = objProche.distancePoint(px, py);
            }
            for (int i = 0; i<segments.size(); i++){
                if(segments.get(i).distancePoint(px, py)<distMin){
                    objProche = segments.get(i);
                    distMin = objProche.distancePoint(px, py);
                }
            }
        }
        if (distMin <= distanceMax){
            return objProche;
        } else {
            return null;
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
