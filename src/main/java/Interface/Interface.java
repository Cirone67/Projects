/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import fr.insa.brenckle.projets.TerrainPoints;
import fr.insa.brenckle.projets.TerrainTriangle;
import fr.insa.brenckle.projets.Treillis;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Guillaume R
 */
public class Interface extends BorderPane{
    
    private Stage fenetre;
    private MenuPrincipal menuPrincipal;
    private NeuCanvas graph;
    private Treillis treillis;
    private Controleur controleur;
    
    public Interface (Stage stage){
        
        this.treillis = new Treillis();
        this.fenetre = stage;
        this.controleur = new Controleur(this);
        
        
        this.menuPrincipal = new MenuPrincipal(this);
        
        this.graph = new NeuCanvas(this);
        //this.graph.setManaged(false);
        this.setCenter(this.graph);    
        
        this.setTop(this.menuPrincipal);
        
//        this.menuPrincipal.getSaisiePointTerrain().getQuitter().setOnMouseClicked((t) -> {
//            this.controleur.changeEtat(21);
//        });

        
           
    }

    /**
     * @return the treillis
     */
    public Treillis getTreillis() {
        return treillis;
    }

    /**
     * @return the controleur
     */
    public Controleur getControleur() {
        return controleur;
    }

    /**
     * @return the fenetre
     */
    public Stage getFenetre() {
        return fenetre;
    }

    /**
     * @return the menuPrincipal
     */
    public MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
    }

    /**
     * @return the graph
     */
    public NeuCanvas getGraph() {
        return graph;
    }
    
}
