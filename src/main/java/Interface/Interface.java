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
        
        SaisiePointTerrain sP = new SaisiePointTerrain(this.fenetre);
        sP.setResizable(false);
        
        this.treillis = new Treillis();
        this.fenetre = stage;
       
//        Menu edition = new Menu ("Edition");        
//        Menu creation = new Menu ("Création");   
//        MenuItem pT = new MenuItem ("Points Terrain");    
//        MenuItem n = new MenuItem ("Noeuds");  
//        MenuItem act = new MenuItem ("Actualiser");
//        creation.getItems().addAll(n,pT,act);
//        this.choix = new MenuBar(edition, creation);
//        this.choix.prefWidthProperty().bind(this.widthProperty());   
        
        this.menuPrincipal = new MenuPrincipal();
        
        this.graph = new NeuCanvas(this);
        this.graph.setManaged(false);
        
        this.setCenter(this.graph);
        this.setTop(this.menuPrincipal);
        
        this.menuPrincipal.getCreePTerrain().setOnMouseClicked((t) -> {
            sP.show();
        });
        
        this.menuPrincipal.getActPTerrain().setOnMouseClicked((t) -> {
           ArrayList<TerrainTriangle> listTT = new ArrayList<TerrainTriangle>(getTreillis().getTerrainTriangles()); 
           for (TerrainTriangle T : listTT){
               T.getC1().getA().getPx();
           }
//           GraphicsContext gc = this.graph.getGraphicsContext2D();
//           gc.translate(this.getWidth()*0.5,this.getHeight()*0.5);
           int r = 3;
           for (int i = 0; i<listTT.size(); i++){
            //   gc.strokeOval(listP.get(i).getPx() - r, listP.get(i).getPy() - r, r, r);
            //   gc.fillOval(listP.get(i).getPx() - r, listP.get(i).getPy() - r, r, r);
           }
        });

        
           
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
    
}
