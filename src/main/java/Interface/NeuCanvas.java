/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

/**
 *
 * @author Guillaume R
 */
public class NeuCanvas extends Pane {
    
    private Interface I;
    private Canvas canvas;
    
    public NeuCanvas (Interface I){
        this.I = I;
        this.canvas = new Canvas (this.getWidth(), this.getHeight());
        this.getChildren().add(this.canvas);
        this.canvas.heightProperty().bind(this.heightProperty());
        this.canvas.widthProperty().bind(this.widthProperty());
        
        this.heightProperty().addListener((o) -> {
           this.redraw(); 
        });
        this.widthProperty().addListener((o) -> {
            this.redraw();
        });     
    }
    
    public void redraw(){
        //TO DO
    }
    
}
