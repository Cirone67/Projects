/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.io.FileNotFoundException;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;

/**
 *
 * @author Guillaume R
 */
public class MenuPrincipal extends VBox{
    
    private Interface I;
    
    private Button edition;
    private Button creation;
    private MenuCreation menuCreation;
    private MenuEdition menuEdition;
    
    private int etat;

    
    public MenuPrincipal (Interface I) throws FileNotFoundException{
        
        this.I = I;
        this.menuCreation = new MenuCreation(this);
        this.menuEdition = new MenuEdition(this);
              
        this.edition = new Button ("Edition"); this.edition.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 30 3 30; -fx-font-size: 10pt");
        this.creation = new Button ("Création"); this.creation.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 30 3 30; -fx-font-size: 10pt");
        
          ToolBar menu = new ToolBar(this.edition, this.creation); menu.setStyle("-fx-background-color: #ccc");
          
          Separator sh = new Separator (Orientation.HORIZONTAL);
          
          this.getChildren().addAll(menu, menuCreation, sh);
          
          this.etat = 1;
          
          this.edition.setOnMouseClicked((t) -> {
              if (etat==1){
                  this.getChildren().removeAll(menuCreation, sh);
                  this.getChildren().addAll(menuEdition, sh);
                  etat = 0;
              }
           });
          
          this.creation.setOnMouseClicked((t) -> {
              if (etat == 0){
                  this.getChildren().removeAll(menuEdition, sh);
                  this.getChildren().addAll(menuCreation, sh);
                  etat = 1;
              }
          });

          

    }

    /**
     * @return the edition
     */
    public Button getEdition() {
        return edition;
    }

    /**
     * @return the creation
     */
    public Button getCreation() {
        return creation;
    }

    /**
     * @return the I
     */
    public Interface getI() {
        return I;
    }

    /**
     * @return the menuCreation
     */
    public MenuCreation getMenuCreation() {
        return menuCreation;
    }
    
}
