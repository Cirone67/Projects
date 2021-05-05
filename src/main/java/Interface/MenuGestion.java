/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import fr.insa.brenckle.projets.AppuiDouble;
import fr.insa.brenckle.projets.AppuiSimple;
import fr.insa.brenckle.projets.Noeud;
import fr.insa.brenckle.projets.NoeudSimple;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Guillaume R
 */
public class MenuGestion extends HBox{
    
    private MenuPrincipal menuPrincipal;
    
    private ListView<String> listNoeud;
    private ArrayList<String> noeudContenu;
    private ListView<String> listAppuiSimple;
    private ListView<String> listAppuiDouble;
    private ListView<String> listBarre;
    private Button calculCharges;
    
    public MenuGestion (MenuPrincipal mP){
        
        this.menuPrincipal = mP;
        this.noeudContenu = new ArrayList<String>();
        
        this.listNoeud = new ListView<String>(); listNoeud.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        for (int i=0; i<menuPrincipal.getI().getTreillis().getNoeuds().size(); i++){
            if (menuPrincipal.getI().getTreillis().getNoeuds().get(i) instanceof NoeudSimple){
                listNoeud.getItems().add(menuPrincipal.getI().getTreillis().getNoeuds().get(i).toString());
                noeudContenu.add(menuPrincipal.getI().getTreillis().getNoeuds().get(i).toString());
            }
        }
        VBox noeuds = new VBox (5, new Label("Noeuds Simples"), getListNoeud());
        noeuds.setPrefHeight(100);
        
        this.listAppuiSimple = new ListView<String>(); listAppuiSimple.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        for (int i=0; i<menuPrincipal.getI().getTreillis().getNoeuds().size(); i++){
            if (menuPrincipal.getI().getTreillis().getNoeuds().get(i) instanceof AppuiSimple){
                listAppuiSimple.getItems().add(menuPrincipal.getI().getTreillis().getNoeuds().get(i).toString());
                noeudContenu.add(menuPrincipal.getI().getTreillis().getNoeuds().get(i).toString());
            }
        } 
        VBox appuiSimples = new VBox (5, new Label("Appuis Simples"), getListAppuiSimple());
        appuiSimples.setPrefHeight(100);
        
        this.listAppuiDouble = new ListView<String>(); listAppuiDouble.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        for (int i=0; i<menuPrincipal.getI().getTreillis().getNoeuds().size(); i++){
            if (menuPrincipal.getI().getTreillis().getNoeuds().get(i) instanceof AppuiDouble){
                listAppuiDouble.getItems().add(menuPrincipal.getI().getTreillis().getNoeuds().get(i).toString());
                noeudContenu.add(menuPrincipal.getI().getTreillis().getNoeuds().get(i).toString());
            }
        }
        VBox appuiDoubles = new VBox (5, new Label("Appuis doubles"), getListAppuiDouble());
        appuiDoubles.setPrefHeight(100);
        
        this.listBarre = new ListView<String>(); listBarre.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        for (int i=0; i<menuPrincipal.getI().getTreillis().getBarres().size(); i++){
            listBarre.getItems().add(menuPrincipal.getI().getTreillis().getBarres().get(i).toString());
            noeudContenu.add(menuPrincipal.getI().getTreillis().getNoeuds().get(i).toString());
        }
        VBox barres = new VBox (5, new Label("Barres"), getListBarre());
        barres.setPrefHeight(100);
        
        this.calculCharges = new Button ("Calcul des charges"); this.calculCharges.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        
        Separator sv1 = new Separator (Orientation.VERTICAL); Separator sv2 = new Separator (Orientation.VERTICAL); 
        this.getChildren().addAll(noeuds, appuiSimples, appuiDoubles, sv1, barres, sv2, calculCharges);
        this.setSpacing(10); this.setStyle("-fx-background-color: #e5e5e5");
        HBox.setMargin(noeuds, new Insets (5,0,5,10)); HBox.setMargin(appuiSimples, new Insets (5,0,5,10));
        HBox.setMargin(appuiDoubles, new Insets (5,0,5,10)); HBox.setMargin(barres, new Insets (5,0,5,0));
        HBox.setMargin(calculCharges, new Insets (50,5,20,10)); 
        
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
     * @return the noeudContenu
     */
    public ArrayList<String> getNoeudContenu() {
        return noeudContenu;
    }
}
