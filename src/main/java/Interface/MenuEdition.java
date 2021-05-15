/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.io.File;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 *
 * @author Guillaume R
 */
public class MenuEdition extends HBox{
    
    private MenuPrincipal menuPrincipal;
    
    private Button enregistrer;
    private Button enregistrerSous;
    private Button telecharger;
    private Button nouveau;
    private File fichier;
    
    private int etatSauvegarde;
    
    public MenuEdition(MenuPrincipal menuPrincipal){
        
        etatSauvegarde = 0;
        
        this.menuPrincipal = menuPrincipal;
        
        this.enregistrer = new Button("Enregistrer"); 
        this.enregistrerSous = new Button("Enregistrer sous"); 
        this.telecharger = new Button("Charger"); 
        this.nouveau = new Button ("Nouveau");
        
        this.fichier = new File("TreillisMeister");
        
        HBox menu = new HBox (20, getNouveau(), enregistrerSous, enregistrer, telecharger);
        HBox.setMargin(menu, new Insets (10,10,10,20));
        
        this.getChildren().addAll(menu); this.setStyle("-fx-background-color: #e5e5e5");
        
        this.enregistrerSous.setOnAction((t) -> {
            this.menuPrincipal.getI().getControleur().changeEtat(110);
        });
        this.enregistrer.setOnAction((t) -> {
            if (etatSauvegarde == 0){
                this.menuPrincipal.getI().getControleur().changeEtat(110);
            }
            else {
                this.menuPrincipal.getI().getControleur().changeEtat(111);
            }
        });
        this.telecharger.setOnAction((t) -> {
            this.menuPrincipal.getI().getControleur().changeEtat(112);
        });
        this.nouveau.setOnAction((t) -> {
            this.menuPrincipal.getI().getControleur().changeEtat(113);
        });        
    }

    
    
    
    
    /**
     * @return the menuPrincipal
     */
    public MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
    }

    /**
     * @return the enregistrer
     */
    public Button getEnregistrer() {
        return enregistrer;
    }

    /**
     * @return the enregistrerSous
     */
    public Button getEnregistrerSous() {
        return enregistrerSous;
    }

    /**
     * @return the telecharger
     */
    public Button getTelecharger() {
        return telecharger;
    }

    /**
     * @return the fichier
     */
    public File getFichier() {
        return fichier;
    }

    /**
     * @param nomFichier the fichier to set
     */
    public void setFichier(File fichier) {
        this.fichier = fichier;
    }

    /**
     * @return the nouveau
     */
    public Button getNouveau() {
        return nouveau;
    }

    /**
     * @param etatSauvegarde the etatSauvegarde to set
     */
    public void setEtatSauvegarde(int etatSauvegarde) {
        this.etatSauvegarde = etatSauvegarde;
    }

    
}
