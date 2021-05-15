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

    /**
     * @return the menuEdition
     */
    public MenuEdition getMenuEdition() {
        return menuEdition;
    }

    /**
     * @return the menuGestion
     */
    public MenuGestion getMenuGestion() {
        return menuGestion;
    }
    
    private Interface I;
    
    private Button edition;
    private Button creation;
    private Button gestion;
    private MenuCreation menuCreation;
    private MenuEdition menuEdition;
    private MenuGestion menuGestion;
    
    private int etat;

    
    public MenuPrincipal (Interface I) {
        
        this.I = I;
        this.menuCreation = new MenuCreation(this);
        this.menuEdition = new MenuEdition(this);
        this.menuGestion = new MenuGestion(this);
              
        this.edition = new Button ("Fichier"); this.edition.setId("boutonMenu");
        this.creation = new Button ("Création"); this.creation.setId("boutonMenu");
        this.gestion = new Button ("Gestion"); this.gestion.setId("boutonMenu");
        
          ToolBar menu = new ToolBar(this.edition, this.creation, this.gestion); 
          
          Separator sh = new Separator (Orientation.HORIZONTAL);
          
          this.getChildren().addAll(menu, menuCreation, sh);
          
          this.etat = 1;
          
          this.edition.setOnMouseClicked((t) -> {
              if (etat==1){
                  this.getChildren().removeAll(menuCreation, sh);
                  this.getChildren().addAll(menuEdition, sh);
                  etat = 0;
              } else if (etat == 2){
                  this.getChildren().removeAll(menuGestion, sh);
                  this.getChildren().addAll(menuEdition, sh);
                  etat = 0;                  
              }
           });
          
          this.creation.setOnMouseClicked((t) -> {
              if (etat == 0){
                  this.getChildren().removeAll(menuEdition, sh);
                  this.getChildren().addAll(menuCreation, sh);
                  etat = 1;
              } else if (etat == 2){
                  this.getChildren().removeAll(menuGestion, sh);
                  this.getChildren().addAll(menuCreation, sh);
                  etat = 1;                  
              }
          });
          this.gestion.setOnMouseClicked((t) -> {
              if (etat == 0){
                  this.getChildren().removeAll(menuEdition, sh);
                  this.getChildren().addAll(menuGestion, sh);
                  etat = 2;                  
              } else if (etat == 1){
                  this.getChildren().removeAll(menuCreation, sh);
                  this.getChildren().addAll(menuGestion, sh);
                  etat = 2;                  
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
