/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

import static fr.insa.brenckle.projets.Objet.couleurSelection;
import java.io.InputStream;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author brenc
 */
public class AppuiSimple extends Appui{

    public AppuiSimple(int id, TerrainTriangle triangleAppui, int premierPoint, double posSegment) {
        super(id, triangleAppui, premierPoint, posSegment);
    }
    
        public AppuiSimple( TerrainTriangle triangleAppui, int premierPoint, double posSegment) {
        super(triangleAppui, premierPoint, posSegment);
    }
        
    public AppuiSimple(int id, int idtriangleAppui, int premierPoint, double posSegment, ArrayList<TerrainTriangle> terrainTriangle) {
        super(id, idtriangleAppui, premierPoint, posSegment, terrainTriangle);
    }
    
        public AppuiSimple(int idtriangleAppui, int premierPoint, double posSegment, ArrayList<TerrainTriangle> terrainTriangle){
        super(idtriangleAppui, premierPoint, posSegment, terrainTriangle);
    }
    
    public void draw(GraphicsContext gc, double largeur){  
            double y = largeur - this.getOrd();
            InputStream inp = this.getClass().getResourceAsStream("appuiSimple2.png");
            Image img2 = new Image(inp, 24, 24, false, false); 
            gc.setImageSmoothing(true);
            gc.drawImage(img2, this.getAbs()-13, y-0.5);
//        gc.strokeOval(this.getAbs()-3*0.5, y-3, 3, 3);
//        gc.fillOval(this.getAbs()-3*0.5, y-3, 3, 3);     
    }
    
    public void drawSelection(GraphicsContext gc, double largeur){  
        double y = largeur - this.getOrd();
        gc.setStroke(couleurSelection); gc.setFill(Color.web("#2ba7ef", 0.4));
        gc.fillRect(this.getAbs()-12, y-22.2, 24, 24);
    }    
    
}
