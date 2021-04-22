/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import fr.insa.brenckle.projets.TerrainPoints;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 *
 * @author Guillaume R
 */
public class Interface extends BorderPane{
    private Stage fenetre;
    private MenuBar choix;
    private Canvas graph;
    
    public Interface (Stage stage){
        
        SaisiePointTerrain sP = new SaisiePointTerrain(this.fenetre);
        sP.setResizable(false);
        
        this.fenetre = stage;
       
        Menu edition = new Menu ("Edition");        
        Menu creation = new Menu ("Création");   
        MenuItem pT = new MenuItem ("Points Terrain");    
        MenuItem n = new MenuItem ("Noeuds");  
        MenuItem act = new MenuItem ("Actualiser");
        creation.getItems().addAll(n,pT,act);
        this.choix = new MenuBar(edition, creation);
        this.choix.prefWidthProperty().bind(this.widthProperty());   

        
        this.graph = new Canvas();
        this.graph.setManaged(false);
        this.graph.widthProperty().bind(
        this.widthProperty());
        this.graph.heightProperty().bind(
        this.heightProperty());
        
        this.setCenter(this.graph);
        this.setTop(this.choix);
        
        pT.setOnAction((t) -> {
            //sP.getP().clear();
            sP.show();
        });
        
        act.setOnAction((t) -> {
           ArrayList<TerrainPoints> listP = new ArrayList<TerrainPoints>(sP.getP());
           GraphicsContext gc = this.graph.getGraphicsContext2D();
           gc.translate(this.getWidth()*0.5,this.getHeight()*0.5);
           int r = 3;
           for (int i = 0; i<listP.size(); i++){
               gc.strokeOval(listP.get(i).getPx() - r, listP.get(i).getPy() - r, r, r);
               gc.fillOval(listP.get(i).getPx() - r, listP.get(i).getPy() - r, r, r);
           }
        });

        
           
    }
    
}
