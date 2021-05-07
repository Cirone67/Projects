/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

import static fr.insa.brenckle.projets.Objet.couleurSelection;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author brenc
 */
public class AppuiDouble extends Appui{
    
    public AppuiDouble(int id, int idtriangleAppui, int premierPoint, double posSegment, ArrayList<TerrainTriangle> terrainTriangle) {
        super(id, idtriangleAppui, premierPoint, posSegment, terrainTriangle);
    }
    
    public AppuiDouble(int idtriangleAppui, int premierPoint, double posSegment, ArrayList<TerrainTriangle> terrainTriangle){
        super(idtriangleAppui, premierPoint, posSegment, terrainTriangle);
    }

    public AppuiDouble(int id, TerrainTriangle triangleAppui, int premierPoint, double posSegment) {
        super(id, triangleAppui, premierPoint, posSegment);
    }
    
        public AppuiDouble( TerrainTriangle triangleAppui, int premierPoint, double posSegment) {
        super(triangleAppui, premierPoint, posSegment);
    }
    
    public void draw(GraphicsContext gc){  
        try {
            File file = new File("C:\\Users\\Guillaume R\\Documents\\NetBeansProjects\\Projects\\src\\main\\java\\Interface\\appuiDouble.png");
            String url = file.toURI().toURL().toString();
            Image img2 = new Image(url, 50, 24, false, false);
            gc.setImageSmoothing(true);        
            gc.drawImage(img2, this.getAbs()-25.5, this.getOrd()-23.5);
//        gc.strokeOval(this.getAbs()-3*0.5, this.getOrd()-3, 3, 3);
//        gc.fillOval(this.getAbs()-3*0.5, this.getOrd()-3, 3, 3); 
        } catch (MalformedURLException ex) {
            Logger.getLogger(AppuiDouble.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void drawSelection(GraphicsContext gc){  
        gc.setStroke(couleurSelection); gc.setFill(Color.web("#2ba7ef", 0.4));
        gc.fillRect(this.getAbs()-25.5, this.getOrd()-23.5, 50, 24);
    }    
    
}
