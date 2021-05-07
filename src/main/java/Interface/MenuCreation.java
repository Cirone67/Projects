/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import fr.insa.brenckle.projets.TypeBarre;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    
    private ChoiceBox typeBarre;
    private Button actPTerrain;
    private Button supBarre;
    private Button supSegTerrain; 
    
    private Button genererTerrain;
    private Button delimiterTerrain;
    private ColorPicker couleur;
    private Button selection;
    private Button suppression;
    private Button reinitialiser;
    
    private SaisiePointTerrain saisiePointTerrain;
    private PanneauNoeuds panneauNoeuds;
    private DelimiterTerrain delimite;
    
    public MenuCreation (MenuPrincipal menuPrincipal) throws FileNotFoundException{
        
        //Création des éléments
        this.menuPrincipal = menuPrincipal;
        
        this.genererTerrain = new Button ("Générer"); this.genererTerrain.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.delimiterTerrain = new Button ("Délimiter"); this.delimiterTerrain.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");       
        
        this.creeNoeud = new Button ("Nouveau"); this.creeNoeud.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.creeBarre = new Button ("Nouveau"); this.creeBarre.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.creePTerrain = new Button ("Nouveau"); this.creePTerrain.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.creeSegTerrain = new Button ("Nouveau"); this.creeSegTerrain.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
       
        this.typeBarre = new ChoiceBox (); 
//        BarreDefault(this.menuPrincipal.getI().getTreillis());
        if (!this.menuPrincipal.getI().getTreillis().getTypeBarre().isEmpty()){
            for (TypeBarre TB: this.menuPrincipal.getI().getTreillis().getTypeBarre()){
                typeBarre.getItems().add(TB.getNom());
            }
        }
        typeBarre.getSelectionModel().selectFirst(); this.typeBarre.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 1 1 1 1; -fx-font-size: 9pt");
        
        
        this.supBarre = new Button ("Supprimer"); this.supBarre.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.actPTerrain = new Button ("Réinitialiser"); this.actPTerrain.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.supSegTerrain = new Button ("Supprimer"); this.supSegTerrain.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        
        this.reinitialiser = new Button ("Réinitialiser"); this.reinitialiser.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        
        this.couleur = new ColorPicker(); this.couleur.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 1 10 1 10; -fx-font-size: 9pt");
        this.selection = new Button(); this.selection.setStyle("-fx-background-color: #ccc; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10");
        FileInputStream inputstream = new FileInputStream("C:\\Users\\Guillaume R\\Documents\\NetBeansProjects\\Projects\\src\\main\\java\\Interface\\mouseCursor.png");
        Image img = new Image(inputstream, 16, 24, false, false); ImageView select = new ImageView(img);
        this.selection.setGraphic(select);
        this.suppression = new Button(); this.suppression.setStyle("-fx-background-color: #ccc; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 6 3 6");
        FileInputStream inputstream2 = new FileInputStream("C:\\Users\\Guillaume R\\Documents\\NetBeansProjects\\Projects\\src\\main\\java\\Interface\\delete.png");
        Image img2 = new Image(inputstream2, 24, 24, false, false); ImageView suppress = new ImageView(img2);
        this.suppression.setGraphic(suppress); suppression.setDisable(true);
        HBox hbSelect = new HBox (10, selection, getSuppression()); 
        VBox outils = new VBox (15, hbSelect, couleur);
        hbSelect.setAlignment(Pos.TOP_CENTER);
        
        this.saisiePointTerrain = new SaisiePointTerrain(this);
        saisiePointTerrain.setResizable(false); 
        this.delimite = new DelimiterTerrain(this);
        delimite.setResizable(false);
        this.panneauNoeuds = new PanneauNoeuds(this);
        
        //Mise en place
          VBox actionTerrain = new VBox (10, this.getDelimiterTerrain(), this.getGenererTerrain());
          VBox.setMargin(this.delimiterTerrain, new Insets (0,0,0,15));
          VBox.setMargin(this.genererTerrain, new Insets (0,0,0,19));
          SousMenuPpl pTerrain = new SousMenuPpl(new Label ("Points Terrain"), this.getCreePTerrain(), this.getActPTerrain(), 5, 5, 10, 5, 55, 5, 10, 5, 20, false);   //espace entre ssTitre, marge T haut, marge T doite, marge T bas, marge T gauche, marge ST haut, ...
          Label terr = new Label ("Terrain"); terr.setStyle("-fx-font-size: 11pt; -fx-font-family: \"Segoe UI Semibold\"; -fx-opacity: 0.8");
          SousMenuPpl terrain = new SousMenuPpl (terr, pTerrain, actionTerrain, 0, 10, 0, 5, 175, 5, 0, 0, 0, true);
          
          //SousMenuPpl noeuds = new SousMenuPpl(new Label("Noeuds"), this.getCreeNoeud(), this.getSupNoeud(), 5, 5, 10, 5, 60, 5, 10, 5, 10, false);
          SousMenuPpl barres = new SousMenuPpl(new Label ("Barres"), this.creeBarre, this.typeBarre, 5, -10, 10, 15, 60, 0, -10, 5, 10, false);
          Label treill = new Label ("Treillis"); treill.setStyle("-fx-font-size: 11pt; -fx-font-family: \"Segoe UI Semibold\"; -fx-opacity: 0.8");
          SousMenuPpl treillis = new SousMenuPpl (treill, getPanneauNoeuds(), barres, 0, 10, 0, 5, 290, 0, 0, 0, 0, true); 
 
          
          Separator sv1 = new Separator (Orientation.VERTICAL);
          Separator sv2 = new Separator (Orientation.VERTICAL); Separator sv3 = new Separator (Orientation.VERTICAL);
          Separator sh = new Separator (Orientation.HORIZONTAL);
          this.getChildren().addAll(outils, sv1, terrain, sv2, treillis, sv3, reinitialiser); this.setStyle("-fx-background-color: #e5e5e5");
          this.setSpacing(30);
          this.setMargin(terrain, new Insets(0, 0, 5, -30));
          this.setMargin(outils, new Insets(20, -5, 5, 25));
          this.setMargin(reinitialiser, new Insets(30, 2, 10, -5));
          
          
          //Gestion événements
          this.creePTerrain.setOnAction((t) -> {
              this.menuPrincipal.getI().getControleur().changeEtat(20);
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
          this.selection.setOnAction((t) -> {
              this.menuPrincipal.getI().getControleur().changeEtat(10);
          });
          this.panneauNoeuds.getCreer().setOnAction((t) -> {
              this.menuPrincipal.getI().getControleur().changeEtat(40);
          });
          this.creeBarre.setOnAction((t) -> {
              this.menuPrincipal.getI().getControleur().changeEtat(51);
          });
          this.reinitialiser.setOnAction((t) -> {
              this.menuPrincipal.getI().getControleur().changeEtat(100);
          });
          this.couleur.setOnAction((t) -> {
              this.menuPrincipal.getI().getControleur().changeCouleur(this.couleur.getValue());
          });
          this.suppression.setOnAction((t) -> {
              this.menuPrincipal.getI().getControleur().changeEtat(101);
          });
          this.actPTerrain.setOnAction((t) -> {
              this.menuPrincipal.getI().getControleur().changeEtat(102);
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
    public ChoiceBox getSupNoeud() {
        return typeBarre;
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
    public ChoiceBox getTypeBarre() {
        return typeBarre;
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

    /**
     * @return the panneauNoeuds
     */
    public PanneauNoeuds getPanneauNoeuds() {
        return panneauNoeuds;
    }

    /**
     * @return the delimiterTerrain
     */
    public Button getDelimiterTerrain() {
        return delimiterTerrain;
    }

    /**
     * @return the suppression
     */
    public Button getSuppression() {
        return suppression;
    }

    /**
     * @return the reinitialiser
     */
    public Button getReinitialiser() {
        return reinitialiser;
    }
    
}
