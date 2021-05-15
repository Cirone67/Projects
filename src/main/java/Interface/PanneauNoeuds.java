/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import fr.insa.brenckle.projets.TerrainSegment;
import fr.insa.brenckle.projets.TerrainTriangle;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Guillaume R
 */
public class PanneauNoeuds extends HBox{
    
    private Button creer;
    private TextField abs;
    private TextField ord;
    private TextField lambda;
    private ChoiceBox choixNoeud;
    private MenuCreation menuCreation;
    private TerrainSegment segmentSelect;
    private int etat;
    
    public PanneauNoeuds (MenuCreation menuCreation){
        
        this.segmentSelect = null;
        this.menuCreation = menuCreation;
        this.creer = new Button ("Créer"); 
        this.abs = new TextField();
        this.ord = new TextField(); 
        this.lambda = new TextField(); 
        this.choixNoeud = new ChoiceBox(); 
        this.choixNoeud.getItems().addAll("Noeud simple", "Appui simple", "Appui double");
        this.etat = 1;
        
        //Mise en place
        VBox vbAppui = new VBox (5, new Label("Lambda:"), getLambda());
        //HBox hbAppui = new HBox (10, textLambda, creer);
        
        VBox vbAbs = new VBox (2, new Label("Abscisse:"), getAbs());
        VBox vbOrd = new VBox (2, new Label("Ordonnée:"), getOrd());
        VBox vbSimple = new VBox (5, vbAbs, vbOrd); 

        choixNoeud.getSelectionModel().select(2);
        this.getChildren().addAll(choixNoeud, vbAppui, creer);
        this.setSpacing(10);
        creer.setDisable(true);
        choixNoeud.setTranslateY(20); creer.setTranslateY(20);
        HBox.setMargin(vbSimple, new Insets (5,0,5,0));
        HBox.setMargin(choixNoeud, new Insets(0,0,0,-30));
        HBox.setMargin(creer, new Insets(0,5,0,0));
        
        
        choixNoeud.setOnAction((t) -> {
            if (((choixNoeud.getValue().toString() == "Appui simple") || (choixNoeud.getValue().toString() == "Appui double")) && (etat == 0)){
                this.getChildren().removeAll(vbSimple, creer);
                this.getChildren().addAll(vbAppui, creer);
                lambda.clear();
                choixNoeud.setTranslateY(20); creer.setTranslateY(20);
                etat = 1;
                if (segmentSelect == null){
                    creer.setDisable(true);
                }
                
            } else if ((choixNoeud.getValue().toString() == "Noeud simple") && (etat == 1)){
                this.getChildren().removeAll(vbAppui, creer);
                this.getChildren().addAll(vbSimple, creer);
                choixNoeud.setTranslateY(40); creer.setTranslateY(40);
                creer.setDisable(false);
                abs.clear(); ord.clear();
                etat = 0;                
            } 
        });
        
    }
    
    public static TerrainTriangle rechercheTriangle (TerrainSegment segmentSelect, ArrayList<TerrainTriangle> listTT){
        int k = 0; int i = 0;
                    
        while (k==0){ //recherche du triangle auquel appartient le segment
            if ((listTT.get(i).getC1() == segmentSelect)){
                k = 1;
            }
            else if ((listTT.get(i).getC2() == segmentSelect)){
                k = 1;
            } 
            else if ((listTT.get(i).getC3() == segmentSelect)){
                k = 1;
            }                        
            i = i+1;
        }
        return listTT.get(i-1);
    }
    
    public static int recherchePremierPoint (TerrainSegment segmentSelect, ArrayList<TerrainTriangle> listTT){
        int premierPoint = 0;
        int k = 0; int i = 0;
                    
        while (k==0){ //recherche du triangle auquel appartient le segment
            if ((listTT.get(i).getC1() == segmentSelect)){
                k = 1;
            }
            else if ((listTT.get(i).getC2() == segmentSelect)){
                premierPoint = 1;
                k = 1;
            } 
            else if ((listTT.get(i).getC3() == segmentSelect)){
                premierPoint = 2;
                k = 1;
            }                        
            i = i+1;
        }
        return premierPoint;
    }

    /**
     * @return the creer
     */
    public Button getCreer() {
        return creer;
    }

    /**
     * @return the abs
     */
    public TextField getAbs() {
        return abs;
    }

    /**
     * @return the ord
     */
    public TextField getOrd() {
        return ord;
    }

    /**
     * @return the lambda
     */
    public TextField getLambda() {
        return lambda;
    }

    /**
     * @return the choixNoeud
     */
    public ChoiceBox getChoixNoeud() {
        return choixNoeud;
    }

    /**
     * @return the menuCreation
     */
    public MenuCreation getMenuCreation() {
        return menuCreation;
    }

    /**
     * @return the segmentSelect
     */
    public TerrainSegment getSegmentSelect() {
        return segmentSelect;
    }

    /**
     * @return the etat
     */
    public int getEtat() {
        return etat;
    }

    /**
     * @param segmentSelect the segmentSelect to set
     */
    public void setSegmentSelect(TerrainSegment segmentSelect) {
        this.segmentSelect = segmentSelect;
    }

    
}
