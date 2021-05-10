/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import fr.insa.brenckle.projets.Terrain;
import fr.insa.brenckle.projets.Treillis;
import static fr.insa.brenckle.projets.TypeBarre.BarreDefault;
import java.io.FileNotFoundException;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Guillaume R
 */
public class Interface extends BorderPane{

    /**
     * @param terrain the terrain to set
     */
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    /**
     * @return the terrain
     */
    public Terrain getTerrain() {
        return terrain;
    }
    
    private Stage fenetre;
    private MenuPrincipal menuPrincipal;
    private NeuCanvas graph;
    private Treillis treillis;
    private Controleur controleur;
    private Terrain terrain;
    
    public Interface (Stage stage) throws FileNotFoundException{
        
        this.treillis = new Treillis();
        BarreDefault(this.treillis);
        
        this.terrain = new Terrain (0, Double.MAX_VALUE, 0, Double.MAX_VALUE);
        
        this.fenetre = stage;
        this.controleur = new Controleur(this);
        
        
        this.menuPrincipal = new MenuPrincipal(this);
        
        this.graph = new NeuCanvas(this);
        //this.graph.setManaged(false);
        this.setCenter(this.graph);    
        
        this.setTop(this.menuPrincipal);
        
        
           
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
