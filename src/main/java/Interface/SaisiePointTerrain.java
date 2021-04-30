/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import fr.insa.brenckle.projets.TerrainPoints;
import static fr.insa.brenckle.projets.TerrainPoints.CompletePoint;
import static fr.insa.brenckle.projets.TerrainPoints.verifieForme;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
    private ArrayList<TerrainPoints> p = new ArrayList< TerrainPoints>(); 
    
    public ArrayList<TerrainPoints> getP(){
        return this.p;
    }
    
    public SaisiePointTerrain(Stage sp){
        
    this.setTitle("Saisie des points terrain");
    this.initOwner(sp);
    this.initModality(Modality.WINDOW_MODAL);
    this.abs = new Label ("Abscisse");
    this.ord = new Label ("Ordonée");
    this.ok = new Button ("Valider");
    this.quitter = new Button ("Sauvegarder et quitter");
    this.clear = new Button ("Réinitialiser");
    this.tabs = new TextField();
    this.tord = new TextField();
    this.p = new ArrayList<TerrainPoints>();
    
    
    
    int espace = 50;
    VBox boutons = new VBox(espace,this.abs,this.ord);
    boutons.setLayoutX(25); boutons.setLayoutY(105);
    VBox text = new VBox(espace,this.tabs,this.tord);
    text.setLayoutX(100); text.setLayoutY(100);
    Group g = new Group(boutons, text);
    HBox valid = new HBox (espace, this.getClear(), this.getQuitter(), this.getOk());
    valid.setLayoutX(50); valid.setLayoutY(300);
    

    
    BorderPane bp = new BorderPane();
    bp.setCenter(g);  bp.setBottom(valid);
    Border bord = new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(10)));
    boutons.setBorder(bord); text.setBorder(bord);
    Border bord2 = new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(10)));
    valid.setBorder(bord2);

    Scene s = new Scene(bp);
    this.setScene(s);
    
    this.clear.setOnAction((t) -> {
        this.p.clear();
    });
    
    this.ok.setOnAction((t) -> {
        if ((this.tabs.getText() != "")&&(this.tord.getText() != "")){
            TerrainPoints pT = new TerrainPoints(Double.parseDouble(this.tabs.getText()),Double.parseDouble(this.tord.getText()));
            this.p.add(pT);
            this.tabs.clear(); this.tord.clear(); this.tabs.requestFocus();
        }

    });
    
    s.setOnKeyPressed((t) -> {
        if (t.getCode().getName() == "Enter"){
            if ((this.tabs.focusedProperty().get() == true) && (this.tabs.getText() != "")){
                this.tord.requestFocus();
            } else if ((this.tord.focusedProperty().get() == true) && (this.tabs.getText() != "")){
                this.ok.requestFocus();
            }
        }
    });
    
//  this.quitter.setOnAction((e) -> {
//        Alert f = new Alert(AlertType.INFORMATION);
//        f.setHeaderText("Information");
//        boolean b = verifieForme(p);
//        if (b == true){
//            f.setContentText("Vous avez rentré une forme 'spéciale'");
//            f.showAndWait();
//        }else{
//         f.setContentText("Vous avez rentré une forme polygonale classique");
//         f.showAndWait();            
//        }
//        this.p = CompletePoint(this.p, b);
//   });
    
    
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
