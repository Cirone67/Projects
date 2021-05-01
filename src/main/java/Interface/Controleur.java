/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

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
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Guillaume R
 */
public class Controleur {
    
    private int etat;
    private Interface vue;
    private ArrayList<Objet> selection;
    
    public Controleur(Interface vue){
        this.etat = 10;
        this.vue = vue;       
    }
    
    public void changeEtat (int etat){
        this.etat = etat;
        if (this.etat == 19){
            this.vue.getMenuPrincipal().getMenuCreation().getCreePTerrain().setDisable(false);
        }
        else if (this.etat == 20){
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
        }
        else if (this.etat == 30){
            
            ArrayList<TerrainPoints> listPT = new ArrayList<TerrainPoints>(this.vue.getMenuPrincipal().getMenuCreation().getSaisiePointTerrain().getP()); //Points rentrés par l'utilisateur
            boolean b = verifieForme(listPT);
            listPT = CompletePoint(listPT, b);
            ArrayList<TerrainPoints> listPTr = new ArrayList<TerrainPoints>(TrianglePoint(listPT, b)); //Points complétant ceux de l'utilisateur 
           
            ArrayList<TerrainSegment> listST = new ArrayList<TerrainSegment>(creationSegment(listPT, listPTr, b)); //1ère liste de segments
            ArrayList<TerrainSegment> listSTr = new ArrayList<TerrainSegment>(creationSegmentTriangle(listPT, listPTr, b)); //2ème liste de segments pour trianguler
            listSTr = Suppsegmendouble(listST, listSTr); //Supprime les doublons de segments
            
            ArrayList<TerrainTriangle> listTT = new ArrayList<TerrainTriangle>(Creationtriangle(listST, listSTr, b)); //Génère les rectangles
            
            Treillis treillis = new Treillis(listTT);
            this.vue.setTreillis(treillis);
            this.vue.getGraph().redraw();
        }
    }
     
    void clicDansInterface (MouseEvent t){
        if (this.etat == 10){
            //TO DO sélection
        }
        else if (this.etat == 20){
            //TO DO saisie des points terrain
        }
        else if (this.etat == 30){
            //TO DO génération automatique des points terrains
            //quand cette étape est validée, autoriser l'ajout manuel de segments
            this.changeEtat(10);
        }
        else if (this.etat == 311){
            //TO DO ajout de segment manuellement --> clic sur 1er pt
            this.changeEtat(312);
        } 
        else if (this.etat == 312){
            //TO DO ajout segment manuel --> clic sur 2ème pt --> création du segment si absence d'erreur
            this.changeEtat(311);
        }
        else if (this.etat == 40){
            //TO DO saisie des noeuds
        }
        else if (this.etat == 51){
            //TO DO ajout de barre --> clic sur 1er noeud
            this.changeEtat(52);
        }
        else if (this.etat == 52){
            //TO DO ajout de barre --> clic sur 2eme noeud --> création de la barre si absence d'erreur
            this.changeEtat(51);
        }
    }    
    
    
    
}
