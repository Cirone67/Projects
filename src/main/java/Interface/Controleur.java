/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import fr.insa.brenckle.projets.Objet;
import fr.insa.brenckle.projets.TerrainPoints;
import fr.insa.brenckle.projets.TerrainTriangle;
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
        if (this.etat == 20){
            this.vue.getMenuPrincipal().getSaisiePointTerrain().show();
        }
        else if (this.etat == 21){
            ArrayList<TerrainPoints> listPT = new ArrayList<TerrainPoints>(this.vue.getMenuPrincipal().getSaisiePointTerrain().getP());
            for (TerrainPoints TP: listPT){
                GraphicsContext context = this.vue.getGraph().getGraphicsContext2D();
                TP.draw(context);
            }
            this.vue.getMenuPrincipal().getSaisiePointTerrain().close();
        }
        else if (this.etat == 30){
            //TO DO génération terrain
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
