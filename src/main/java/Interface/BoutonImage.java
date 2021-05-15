/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.io.InputStream;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Guillaume R
 */
public class BoutonImage extends Button{
    
    public BoutonImage(String url, double width, double height){
        
        InputStream inp = this.getClass().getResourceAsStream(url);
        if (inp == null) {
            this.setText("XX " + url);
        } else {
            Image img = new Image(inp, width, height, false, true);
            this.setGraphic(new ImageView(img));

        }        
        
    }
    
}
