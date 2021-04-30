/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 *
 * @author Guillaume R
 */
public class MenuEdition extends HBox{
    
    private Button enregistrer;
    
    public MenuEdition(MenuPrincipal menuPrincipal){
        
        this.enregistrer = new Button("Enregistrer"); this.enregistrer.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.getChildren().addAll(enregistrer);
    }
    
}
