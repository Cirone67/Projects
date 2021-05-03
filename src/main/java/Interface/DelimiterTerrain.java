/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Guillaume R
 */
public class DelimiterTerrain extends Stage{
    
    private MenuCreation menuCreation;
    private ChoiceBox choix;
    private TextField donnee;
    private Button ok;
    private Button valider;
    private double [] coordonnees;
    private int compte;
    
    public DelimiterTerrain (MenuCreation menuCreation){
        
        this.menuCreation = menuCreation;
        this.setTitle("Délimitation du terrain");
        this.initOwner(menuCreation.getMenuPrincipal().getI().getFenetre());
        this.initModality(Modality.WINDOW_MODAL); 
        
        //Création éléments
        this.compte = 0;
        this.choix = new ChoiceBox(); 
        this.choix.getItems().addAll("Xmin", "Xmax", "Ymin", "Ymax"); this.choix.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-font-size: 9pt");
        this.donnee = new TextField(); 
        this.ok = new Button ("Ok"); this.ok.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.valider = new Button ("Valider"); this.valider.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.coordonnees = new double[4];
        choix.getSelectionModel().selectFirst();
        
        //Mise en place
        HBox hb = new HBox(20, choix, donnee, ok);
        HBox.setMargin(choix, new Insets (10,0,0,10));
        HBox.setMargin(donnee, new Insets (10,0,0,0));
        HBox.setMargin(ok, new Insets (10, 10, 0, 0));
        VBox corps = new VBox (hb, getValider());
        VBox.setMargin(valider, new Insets (10, 10, 10, 125));
        Scene scene = new Scene(corps);
        this.setScene(scene);
        
        
        //Gestion des événements
        this.ok.setOnAction((t) -> {    
            if (donnee.getText() != ""){
                if (choix.getValue().equals("Xmin") == true){
                    coordonnees[0] = Double.parseDouble(donnee.getText());
                } else if (choix.getValue().equals("Xmax") == true){
                    coordonnees[1] = Double.parseDouble(donnee.getText());
                } else if (choix.getValue().equals("Ymin") == true){
                    coordonnees[2] = Double.parseDouble(donnee.getText());
                } else if (choix.getValue().equals("Ymax") == true){
                    coordonnees[3] = Double.parseDouble(donnee.getText());
                }
                donnee.clear();
                compte = compte + 1;
            }        
        });
        
        scene.setOnKeyPressed((t) -> {
            if ((t.getCode().getName() == "Enter")&&(donnee.focusedProperty().get() == true)&&(donnee.getText()!="")){
                if (choix.getValue().equals("Xmin") == true){
                    coordonnees[0] = Double.parseDouble(donnee.getText());
                } else if (choix.getValue().equals("Xmax") == true){
                    coordonnees[1] = Double.parseDouble(donnee.getText());
                } else if (choix.getValue().equals("Ymin") == true){
                    coordonnees[2] = Double.parseDouble(donnee.getText());
                } else if (choix.getValue().equals("Ymax") == true){
                    coordonnees[3] = Double.parseDouble(donnee.getText());
                }
                donnee.clear();
                compte = compte + 1;
            }
        });
        
    }

    /**
     * @return the coordonnees
     */
    public double[] getCoordonnees() {
        return coordonnees;
    }

    /**
     * @return the valider
     */
    public Button getValider() {
        return valider;
    }

    /**
     * @return the compte
     */
    public int getCompte() {
        return compte;
    }
    
}
