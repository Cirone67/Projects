/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;

/**
 *
 * @author Guillaume R
 */
public class MenuCreation extends HBox{
    
    private MenuPrincipal menuPrincipal;
    
    private Button creeNoeud;
    private Button creePTerrain;
    private Button creeBarre;
    private Button creeSegTerrain;
    
    private Button supNoeud;
    private Button actPTerrain;
    private Button supBarre;
    private Button supSegTerrain; 
    
    private Button genererTerrain;
    private Button delimiterTerrain;
    private ColorPicker couleur;
    
    private SaisiePointTerrain saisiePointTerrain;
    private DelimiterTerrain delimite;
    
    public MenuCreation (MenuPrincipal menuPrincipal){
        
        //Création des éléments
        this.menuPrincipal = menuPrincipal;
        
        this.genererTerrain = new Button ("Générer"); this.genererTerrain.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 1 10 1 10; -fx-font-size: 9pt");
        this.delimiterTerrain = new Button ("Délimiter"); this.delimiterTerrain.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 1 10 1 10; -fx-font-size: 9pt");       
        
        this.creeNoeud = new Button ("Nouveau"); this.creeNoeud.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.creeBarre = new Button ("Nouveau"); this.creeBarre.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.creePTerrain = new Button ("Nouveau"); this.creePTerrain.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.creeSegTerrain = new Button ("Nouveau"); this.creeSegTerrain.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
       
        this.supNoeud = new Button ("Supprimer"); this.supNoeud.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.supBarre = new Button ("Supprimer"); this.supBarre.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.actPTerrain = new Button ("Actualiser"); this.actPTerrain.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.supSegTerrain = new Button ("Supprimer"); this.supSegTerrain.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        
        this.couleur = new ColorPicker();  
        
        this.saisiePointTerrain = new SaisiePointTerrain(this.getMenuPrincipal().getI().getFenetre());
        saisiePointTerrain.setResizable(false); 
        this.delimite = new DelimiterTerrain(this);
        delimite.setResizable(false);
        
        //Mise en place
          HBox titreTerrain = new HBox(15, new Label("Terrain"), this.delimiterTerrain, this.getGenererTerrain());
          
          SousMenuPpl pTerrain = new SousMenuPpl(new Label ("Points Terrain"), this.getCreePTerrain(), this.getActPTerrain(), 5, 5, 10, 5, 50, 5, 10, 5, 20, false);   //espace entre ssTitre, marge T haut, marge T doite, marge T bas, marge T gauche, marge ST haut, ...
          SousMenuPpl segTerrain = new SousMenuPpl (new Label ("Segments Terrain"), this.getCreeSegTerrain(), this.getSupSegTerrain(), 5, 5, 10, 5, 30, 5, 10, 5, 10, false);
          SousMenuPpl terrain = new SousMenuPpl (titreTerrain, pTerrain, segTerrain, 0, 10, 0, 5, 160, 5, 0, 0, 0, true);
          
          SousMenuPpl noeuds = new SousMenuPpl(new Label("Noeuds"), this.getCreeNoeud(), this.getSupNoeud(), 5, 5, 10, 5, 60, 5, 10, 5, 10, false);
          SousMenuPpl barres = new SousMenuPpl(new Label ("Barres"), this.getCreeBarre(), this.getSupBarre(), 5, 5, 10, 5, 60, 5, 10, 5, 10, false);
          SousMenuPpl treillis = new SousMenuPpl (new Label ("Treillis"), noeuds, barres, 0, 10, 0, 5, 160, 5, 0, 0, 0, true);
          
          Separator sv1 = new Separator (Orientation.VERTICAL);
          Separator sv2 = new Separator (Orientation.VERTICAL);
          Separator sh = new Separator (Orientation.HORIZONTAL);
          this.getChildren().addAll(terrain, sv1, treillis, sv2, this.couleur); this.setStyle("-fx-background-color: #e5e5e5");
          this.setSpacing(30);
          this.setMargin(couleur, new Insets(20, 10, 5, 20));   
          
          
          //Gestion événements
          this.creePTerrain.setOnAction((t) -> {
              this.menuPrincipal.getI().getControleur().changeEtat(20);
          });
          this.saisiePointTerrain.getQuitter().setOnAction((t) -> {
              this.menuPrincipal.getI().getControleur().changeEtat(21);
          });
          this.genererTerrain.setOnAction((t) -> {
              this.menuPrincipal.getI().getControleur().changeEtat(30);
          }); 
          this.delimiterTerrain.setOnAction((t) -> {
              delimite.show();
          });
          this.delimite.getValider().setOnAction((t) -> {
            if (delimite.getCompte()<4){
                Alert f = new Alert(Alert.AlertType.WARNING);
                f.setHeaderText("Attention");
                f.setContentText("Données manquantes");
                f.showAndWait();
            } else { 
                this.delimite.close();  
                this.menuPrincipal.getI().getControleur().changeEtat(19);
            }              
          });
        
        
        
    }

    /**
     * @return the menuPrincipal
     */
    public MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
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
     * @return the actPTerrain
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
     * @return the genererTerrain
     */
    public Button getGenererTerrain() {
        return genererTerrain;
    }

    /**
     * @return the couleur
     */
    public ColorPicker getCouleur() {
        return couleur;
    }

    /**
     * @return the saisiePointTerrain
     */
    public SaisiePointTerrain getSaisiePointTerrain() {
        return saisiePointTerrain;
    }

    /**
     * @return the delimite
     */
    public DelimiterTerrain getDelimite() {
        return delimite;
    }
    
}
