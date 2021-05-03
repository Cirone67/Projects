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

    public AppuiDouble(int id, TerrainTriangle triangleAppui, int premierPoint, double posSegment) {
        super(id, triangleAppui, premierPoint, posSegment);
    }
    
    public void draw(GraphicsContext gc){  
        try {
            int r = 3;
            gc.setStroke(Color.web("#40b6ff"));
            gc.strokeOval(this.getAbs()- r, this.getOrd() - r, r, r);
            gc.fillOval(this.getAbs() - r, this.getOrd() - r, r, r);
            File file = new File("C:\\Users\\Guillaume R\\Pictures\\appuiDouble.png");
            String url = file.toURI().toURL().toString();
            Image img2 = new Image(url, 58.95, 24, false, false);
            gc.setImageSmoothing(true);        
            gc.drawImage(img2, this.getAbs()-29, this.getOrd()-22.4);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AppuiDouble.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void drawSelection(GraphicsContext gc){  
        int r = 3;
        gc.setStroke(couleurSelection); gc.setFill(couleurSelection);
        gc.strokeOval(this.getAbs()- r, this.getOrd() - r, r, r);
        gc.fillOval(this.getAbs() - r, this.getOrd() - r, r, r);
    }    
    
}
