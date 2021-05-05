/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import fr.insa.brenckle.projets.Noeud;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Guillaume R
 */
public class MenuGestion extends HBox{
    
    private MenuPrincipal menuPrincipal;
    
    private ListView<String> listNoeud;
    private ListView<String> listAppuiSimple;
    private ListView<String> listAppuiDouble;
    private ListView<String> listBarre;
    private Button saisieCharge;
    private TextField norme;
    private TextField angle;
    private Button calculCharges;
    private Noeud noeudSelect;
    
    public MenuGestion (MenuPrincipal mP){
        
        this.menuPrincipal = mP;
        
        this.noeudSelect = null;
        this.listNoeud = new ListView<String>(); listNoeud.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        VBox noeuds = new VBox (5, new Label("Noeuds Simples"), getListNoeud());
        noeuds.setPrefHeight(100);
        
        this.listAppuiSimple = new ListView<String>(); listAppuiSimple.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        VBox appuiSimples = new VBox (5, new Label("Appuis Simples"), getListAppuiSimple());
        appuiSimples.setPrefHeight(100);
        
        this.listAppuiDouble = new ListView<String>(); listAppuiDouble.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        VBox appuiDoubles = new VBox (5, new Label("Appuis doubles"), getListAppuiDouble());
        appuiDoubles.setPrefHeight(100);
        
        this.listBarre = new ListView<String>(); listBarre.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        VBox barres = new VBox (5, new Label("Barres"), getListBarre());
        barres.setPrefHeight(100);
        
        this.calculCharges = new Button ("Calcul des charges"); this.calculCharges.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        
        this.angle = new TextField(); VBox saisieAngle = new VBox ( new Label("Angle:"), getAngle());
        this.norme = new TextField(); VBox saisieNorme = new VBox ( new Label("Norme:"), getNorme());
        VBox vbsaisie = new VBox (5, saisieNorme, saisieAngle);
        this.saisieCharge = new Button ("Créer charge"); saisieCharge.setDisable(true);
        HBox hbentrercharge = new HBox (10, vbsaisie, getSaisieCharge()); HBox.setMargin(saisieCharge, new Insets (35,0,0,0));
        VBox vbcharge = new VBox (hbentrercharge, calculCharges);
        VBox.setMargin(calculCharges, new Insets (15,5,10,10));
        
        Separator sv1 = new Separator (Orientation.VERTICAL); Separator sv2 = new Separator (Orientation.VERTICAL); 
        this.getChildren().addAll(noeuds, appuiSimples, appuiDoubles, sv1, barres, sv2, vbcharge);
        this.setSpacing(10); this.setStyle("-fx-background-color: #e5e5e5");
        HBox.setMargin(noeuds, new Insets (5,0,5,10)); HBox.setMargin(appuiSimples, new Insets (5,0,5,10));
        HBox.setMargin(appuiDoubles, new Insets (5,0,5,10)); HBox.setMargin(barres, new Insets (5,0,5,0));
        HBox.setMargin(vbcharge, new Insets (5,5,0,0)); 
        
        //Gestion des événements
        this.listNoeud.setOnMouseClicked((t) -> {
            this.menuPrincipal.getI().getControleur().changeEtat(71);
        });       
        this.listAppuiSimple.setOnMouseClicked((t) -> {
            this.menuPrincipal.getI().getControleur().changeEtat(72);
        }); 
        this.listAppuiDouble.setOnMouseClicked((t) -> {
            this.menuPrincipal.getI().getControleur().changeEtat(73);
        });        
         this.listBarre.setOnMouseClicked((t) -> {
            this.menuPrincipal.getI().getControleur().changeEtat(74);
        });    
        this.saisieCharge.setOnAction((t) -> {
            this.menuPrincipal.getI().getControleur().changeEtat(60);
        });
        
        
    }

    /**
     * @return the menuPrincipal
     */
    public MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
    }

    /**
     * @param menuPrincipal the menuPrincipal to set
     */
    public void setMenuPrincipal(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }

    /**
     * @return the listNoeud
     */
    public ListView<String> getListNoeud() {
        return listNoeud;
    }

    /**
     * @param listNoeud the listNoeud to set
     */
    public void setListNoeud(ListView<String> listNoeud) {
        this.listNoeud = listNoeud;
    }

    /**
     * @return the listAppuiSimple
     */
    public ListView<String> getListAppuiSimple() {
        return listAppuiSimple;
    }

    /**
     * @param listAppuiSimple the listAppuiSimple to set
     */
    public void setListAppuiSimple(ListView<String> listAppuiSimple) {
        this.listAppuiSimple = listAppuiSimple;
    }

    /**
     * @return the listAppuiDouble
     */
    public ListView<String> getListAppuiDouble() {
        return listAppuiDouble;
    }

    /**
     * @param listAppuiDouble the listAppuiDouble to set
     */
    public void setListAppuiDouble(ListView<String> listAppuiDouble) {
        this.listAppuiDouble = listAppuiDouble;
    }

    /**
     * @return the listBarre
     */
    public ListView<String> getListBarre() {
        return listBarre;
    }

    /**
     * @param listBarre the listBarre to set
     */
    public void setListBarre(ListView<String> listBarre) {
        this.listBarre = listBarre;
    }

    /**
     * @return the calculCharges
     */
    public Button getCalculCharges() {
        return calculCharges;
    }

    /**
     * @param calculCharges the calculCharges to set
     */
    public void setCalculCharges(Button calculCharges) {
        this.calculCharges = calculCharges;
    }

    /**
     * @return the saisieCharge
     */
    public Button getSaisieCharge() {
        return saisieCharge;
    }

    /**
     * @return the noeudSelect
     */
    public Noeud getNoeudSelect() {
        return noeudSelect;
    }

    /**
     * @param noeudSelect the noeudSelect to set
     */
    public void setNoeudSelect(Noeud noeudSelect) {
        this.noeudSelect = noeudSelect;
    }

    /**
     * @return the norme
     */
    public TextField getNorme() {
        return norme;
    }

    /**
     * @return the angle
     */
    public TextField getAngle() {
        return angle;
    }

}
