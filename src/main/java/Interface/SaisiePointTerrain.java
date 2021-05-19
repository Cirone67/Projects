/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import fr.insa.brenckle.projets.Terrain;
import fr.insa.brenckle.projets.TerrainPoints;
import static fr.insa.brenckle.projets.TerrainPoints.verifiePT;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;

/**
 *
 * @author Guillaume R
 */
public class SaisiePointTerrain extends Stage{
    
    private Label abs;
    private Label ord;
    private Button ok;
    private Button quitter;
    private Button clear;
    private TextField tabs;
    private TextField tord;
    private ArrayList<TerrainPoints> p;
    private MenuCreation menuCreation;
    
    public ArrayList<TerrainPoints> getP(){
        return this.p;
    }
    
    public SaisiePointTerrain(MenuCreation menuCreation){
    
    this.menuCreation = menuCreation;
    this.setTitle("Saisie des points terrain");
    this.initOwner(menuCreation.getMenuPrincipal().getI().getFenetre());
    this.initModality(Modality.WINDOW_MODAL);
    this.abs = new Label ("Abscisse");
    this.ord = new Label ("Ordonnée");
    this.ok = new Button ("Valider"); 
    this.quitter = new Button ("Sauvegarder et quitter"); 
    this.clear = new Button ("Réinitialiser"); 
    this.tabs = new TextField();
    this.tord = new TextField();
    this.p = new ArrayList<TerrainPoints>();
    
    ArrayList<TerrainPoints> provisoire = new ArrayList<TerrainPoints>();
    
    
    int espace = 30;
    VBox boutons = new VBox(espace,this.abs,this.ord);
    boutons.setLayoutX(25); boutons.setLayoutY(105);
    VBox text = new VBox(espace,this.tabs,this.tord);
    text.setLayoutX(100); text.setLayoutY(100);
    Group g = new Group(boutons, text);
    HBox valid = new HBox (espace, this.clear, this.quitter, this.ok);
    valid.setLayoutX(50); valid.setLayoutY(300);
    

    
    BorderPane bp = new BorderPane();
    bp.setCenter(g);  bp.setBottom(valid);
    Border bord = new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(10)));
    boutons.setBorder(bord); text.setBorder(bord);
    Border bord2 = new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(10)));
    valid.setBorder(bord2);

    Scene s = new Scene(bp);
    s.getStylesheets().add(getClass().getResource("Ressources/Apparence.css").toString()); 
    this.setScene(s);
    InputStream inp = this.getClass().getResourceAsStream("Ressources/IconeTerrain.png");
    Image img = new Image(inp);        
    this.getIcons().add(img);
    
    this.clear.setOnAction((t) -> {
        provisoire.clear();
    });
    
    this.ok.setOnAction((t) -> {
        if ((!this.tabs.getText().isBlank()) && (!this.tord.getText().isBlank())){
            TerrainPoints pT = new TerrainPoints(Double.parseDouble(this.tabs.getText()),Double.parseDouble(this.tord.getText()));
            Boolean b = verifiePT(this.menuCreation.getMenuPrincipal().getI().getTerrain(), pT);
            
            Terrain zoneDessin = new Terrain (0, this.menuCreation.getMenuPrincipal().getI().getGraph().getCanvas().getWidth(), 0, this.menuCreation.getMenuPrincipal().getI().getGraph().getCanvas().getHeight());
            Boolean dansZone = verifiePT(zoneDessin, pT);
            
            if ((b==true)&&(dansZone==true)){
                provisoire.add(pT);
                this.tabs.clear(); this.tord.clear(); this.tabs.requestFocus();                
            } else if (b==false){
                Alert f = new Alert(Alert.AlertType.WARNING);
                f.setHeaderText("Attention");
                f.setContentText("Point hors du terrain");
                f.showAndWait();                
            } else if (dansZone==false){
                Alert confirmer = new Alert(Alert.AlertType.CONFIRMATION);
                confirmer.setTitle("Attention");
                confirmer.setHeaderText("Le point saisi est hors de la zone de dessin,\n" + "êtes-vous sûr de vouloir le créer?");
                confirmer.setContentText(pT.toString());
                Optional<ButtonType> select = confirmer.showAndWait();
                if (select.get() == ButtonType.OK){
                    provisoire.add(pT);
                    this.tabs.clear(); this.tord.clear(); this.tabs.requestFocus();                    
                } else if (select.get() == ButtonType.CANCEL){
                    this.tabs.requestFocus(); 
                }
            }

        }

    });
    
    s.setOnKeyPressed((t) -> {
        if (t.getCode().getName().equals("Enter")){
            if ((this.tabs.focusedProperty().get() == true) && (!this.tabs.getText().isBlank())){
                this.tord.requestFocus();
            } else if ((this.tord.focusedProperty().get() == true) && (!this.tabs.getText().isBlank())){
                this.ok.requestFocus();
            }
        }
    });
    this.quitter.setOnAction((t) -> {
        p.addAll(provisoire);     
        provisoire.clear();
        menuCreation.getMenuPrincipal().getI().getControleur().changeEtat(21);
    });
    
    
    }

    /**
     * @return the ok
     */
    public Button getOk() {
        return ok;
    }

    /**
     * @return the quitter
     */
    public Button getQuitter() {
        return quitter;
    }

    /**
     * @return the clear
     */
    public Button getClear() {
        return clear;
    }
    
}
