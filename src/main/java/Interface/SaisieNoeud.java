/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import fr.insa.brenckle.projets.Noeud;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Guillaume R
 */
public class SaisieNoeud extends Stage{
    
    private Button valider;
    private Button sauvegarder;
    private Button reinitialiser;
    private TextField abs;
    private TextField ord;
    private ChoiceBox choixNoeud;
    private ArrayList<Noeud> listNoeud;
    
    public SaisieNoeud(Stage stage){
        
        this.valider = new Button ("Valider");
        this.sauvegarder = new Button ("Sauvegarder et quittet");
        this.reinitialiser = new Button ("Réinitialiser");
        this.abs = new TextField();
        this.ord = new TextField();
        this.choixNoeud = new ChoiceBox();
        this.listNoeud = new ArrayList<Noeud>();
        
    }
    
}
