/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Guillaume R
 */
public class MenuPrincipal extends VBox{
    
    private Button edition;
    
    private Button creation;
    private Button creeNoeud;
    private Button creePTerrain;
    private Button creeBarre;
    private Button creeSegTerrain;
    private Button supNoeud;
    private Button actPTerrain;
    private Button supBarre;
    private Button supSegTerrain;    
    private ColorPicker couleur;
    
    private int etat;

    
    public MenuPrincipal (){
        
        this.edition = new Button ("Edition"); this.edition.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 30 3 30; -fx-font-size: 10pt");
        this.creation = new Button ("Création"); this.creation.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 30 3 30; -fx-font-size: 10pt");
        
        this.creeNoeud = new Button ("Nouveau"); this.creeNoeud.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.creeBarre = new Button ("Nouveau"); this.creeBarre.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.creePTerrain = new Button ("Nouveau"); this.creePTerrain.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.creeSegTerrain = new Button ("Nouveau"); this.creeSegTerrain.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
       
        this.supNoeud = new Button ("Supprimer"); this.supNoeud.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.supBarre = new Button ("Supprimer"); this.supBarre.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.actPTerrain = new Button ("Actualiser"); this.actPTerrain.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.supSegTerrain = new Button ("Supprimer"); this.supSegTerrain.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        
        this.couleur = new ColorPicker();
        
          HBox menu = new HBox(this.edition, this.creation); menu.setStyle("-fx-background-color: #ccc");
        
//        HBox HBpTerrain = new HBox(this.creePTerrain, this.supPTerrain);
//        VBox VBpTerrain = new VBox (new Label ("Points terrain"), HBpTerrain); 
//        BorderPane pTerrain = new BorderPane();
//        pTerrain.setTop(VBpTerrain); pTerrain.setBottom(VBpTerrain);

          SousMenuPpl pTerrain = new SousMenuPpl(new Label ("Points Terrain"), this.creePTerrain, this.actPTerrain, 5, 5, 10, 5, 50, 5, 10, 5, 20, false);   //espace entre ssTitre, marge T haut, marge T doite, marge T bas, marge T gauche, marge ST haut, ...
          SousMenuPpl segTerrain = new SousMenuPpl (new Label ("Segments Terrain"), this.creeSegTerrain, this.supSegTerrain, 5, 5, 10, 5, 30, 5, 10, 5, 10, false);
          SousMenuPpl terrain = new SousMenuPpl (new Label ("Terrain"), pTerrain, segTerrain, 0, 10, 0, 5, 150, 5, 0, 0, 0, true);
          
          SousMenuPpl noeuds = new SousMenuPpl(new Label("Noeuds"), this.creeNoeud, this.supNoeud, 5, 5, 10, 5, 60, 5, 10, 5, 10, false);
          SousMenuPpl barres = new SousMenuPpl(new Label ("Barres"), this.creeBarre, this.supBarre, 5, 5, 10, 5, 60, 5, 10, 5, 10, false);
          SousMenuPpl treillis = new SousMenuPpl (new Label ("Treillis"), noeuds, barres, 0, 10, 0, 5, 160, 5, 0, 0, 0, true);
          
          Separator sv1 = new Separator (Orientation.VERTICAL);
          Separator sv2 = new Separator (Orientation.VERTICAL);
          Separator sh = new Separator (Orientation.HORIZONTAL);
          HBox menuCreation = new HBox(30, terrain, sv1, treillis, sv2, this.couleur); menuCreation.setStyle("-fx-background-color: #e5e5e5");
          HBox.setMargin(couleur, new Insets(20, 10, 5, 20));
          
          Button en = new Button ("Enregistrer");
          HBox menuEdition = new HBox(en);
          
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
     * @return the creeNoeud
     */
    public Button getCreeNoeud() {
        return creeNoeud;
    }

    /**
     * @return the creePTerrain
     */
    public Button getCreePTerrain() {
        return creePTerrain;
    }

    /**
     * @return the creeBarre
     */
    public Button getCreeBarre() {
        return creeBarre;
    }

    /**
     * @return the creeSegTerrain
     */
    public Button getCreeSegTerrain() {
        return creeSegTerrain;
    }

    /**
     * @return the supNoeud
     */
    public Button getSupNoeud() {
        return supNoeud;
    }

    /**
     * @return the supPTerrain
     */
    public Button getActPTerrain() {
        return actPTerrain;
    }

    /**
     * @return the supBarre
     */
    public Button getSupBarre() {
        return supBarre;
    }

    /**
     * @return the supSegTerrain
     */
    public Button getSupSegTerrain() {
        return supSegTerrain;
    }

    /**
     * @return the couleur
     */
    public ColorPicker getCouleur() {
        return couleur;
    }
    
}
