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
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    
    private Stage sParent;
    private int etat;
    private Label abs;
    private Label ord;
    private Button ok;
    private Button annuler;
    private Button clear;
    private TextField tabs;
    private TextField tord;
    private ArrayList<TerrainPoints> p = new ArrayList< TerrainPoints>(); 
    
    public ArrayList<TerrainPoints> getP(){
        return this.p;
    }
    
    public SaisiePointTerrain(Stage sp){
    this.etat = 1;    
    this.setTitle("Saisie des points terrain");
    this.sParent = sp;
    this.initOwner(this.sParent);
    this.initModality(Modality.WINDOW_MODAL);
    this.abs = new Label ("Abscisse");
    this.ord = new Label ("Ordonée");
    this.ok = new Button ("Valider");
    this.annuler = new Button ("Sauvegarder et quitter");
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
    HBox valid = new HBox (espace,this.clear, this.annuler, this.ok);
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
        
        TerrainPoints pT = new TerrainPoints(Double.parseDouble(this.tabs.getText()),Double.parseDouble(this.tord.getText()));
        this.p.add(pT);
        this.tabs.clear(); this.tord.clear();
    });
    
    this.annuler.setOnAction((e) -> {
        Alert f = new Alert(AlertType.INFORMATION);
        f.setHeaderText("Information");
        this.close();
        boolean b = verifieForme(p);
        if (b == true){
            f.setContentText("Vous avez rentré une forme 'spéciale'");
            f.showAndWait();
        }else{
         f.setContentText("Vous avez rentré une forme polygonale classique");
         f.showAndWait();            
        }
        this.p = CompletePoint(this.p, b);
    });
    
    
    }
    
}
