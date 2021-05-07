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
public class AppuiSimple extends Appui{

    public AppuiSimple(int id, TerrainTriangle triangleAppui, int premierPoint, double posSegment) {
        super(id, triangleAppui, premierPoint, posSegment);
    }

    public AppuiSimple(int id, int idtriangleAppui, int premierPoint, double posSegment, ArrayList<TerrainTriangle> terrainTriangle) {
        super(id, idtriangleAppui, premierPoint, posSegment, terrainTriangle);
    }
    
        public AppuiSimple(int idtriangleAppui, int premierPoint, double posSegment, ArrayList<TerrainTriangle> terrainTriangle){
        super(idtriangleAppui, premierPoint, posSegment, terrainTriangle);
    }
    
    public void draw(GraphicsContext gc){  
        try {
            
            File file = new File("C:\\Users\\Guillaume R\\Documents\\NetBeansProjects\\Projects\\src\\main\\java\\Interface\\appuiSimple2.png");
            String url = file.toURI().toURL().toString();
            Image img2 = new Image(url, 24, 24, false, false); 
            gc.setImageSmoothing(true);
            gc.drawImage(img2, this.getAbs()-12, this.getOrd()-22.2);
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(AppuiSimple.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void drawSelection(GraphicsContext gc){  
        int r = 3;
        gc.setStroke(couleurSelection); gc.setFill(Color.web("#2ba7ef", 0.4));
        gc.fillRect(this.getAbs()-12, this.getOrd()-22.2, 24, 24);
    }    
    
}
