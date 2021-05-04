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
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Guillaume R
 */
public class MenuGestion extends HBox{
    
    private MenuPrincipal menuPrincipal;
    
    private ComboBox listNoeud;
    private ArrayList<String> noeudContenu;
    private ComboBox listAppuiSimple;
    private ComboBox listAppuiDouble;
    private ComboBox listBarre;
    private Button calculCharges;
    
    public MenuGestion (MenuPrincipal mP){
        
        this.menuPrincipal = mP;
        this.noeudContenu = new ArrayList<String>();
        
        this.listNoeud = new ComboBox();
        for (int i=0; i<menuPrincipal.getI().getTreillis().getNoeuds().size(); i++){
            if (menuPrincipal.getI().getTreillis().getNoeuds().get(i) instanceof NoeudSimple){
                listNoeud.getItems().add(menuPrincipal.getI().getTreillis().getNoeuds().get(i).toString());
                noeudContenu.add(menuPrincipal.getI().getTreillis().getNoeuds().get(i).toString());
            }
        }
        VBox noeuds = new VBox (5, new Label("Noeuds Simples"), getListNoeud());
        
        this.listAppuiSimple = new ComboBox();
        for (int i=0; i<menuPrincipal.getI().getTreillis().getNoeuds().size(); i++){
            if (menuPrincipal.getI().getTreillis().getNoeuds().get(i) instanceof AppuiSimple){
                listAppuiSimple.getItems().add(menuPrincipal.getI().getTreillis().getNoeuds().get(i).toString());
                noeudContenu.add(menuPrincipal.getI().getTreillis().getNoeuds().get(i).toString());
            }
        } 
        VBox appuiSimples = new VBox (5, new Label("Appuis Simples"), getListAppuiSimple());
        
        this.listAppuiDouble = new ComboBox();
        for (int i=0; i<menuPrincipal.getI().getTreillis().getNoeuds().size(); i++){
            if (menuPrincipal.getI().getTreillis().getNoeuds().get(i) instanceof AppuiDouble){
                listAppuiDouble.getItems().add(menuPrincipal.getI().getTreillis().getNoeuds().get(i).toString());
                noeudContenu.add(menuPrincipal.getI().getTreillis().getNoeuds().get(i).toString());
            }
        }
        VBox appuiDoubles = new VBox (5, new Label("Appuis doubles"), getListAppuiDouble());
        
        this.listBarre = new ComboBox();
        for (int i=0; i<menuPrincipal.getI().getTreillis().getBarres().size(); i++){
            listBarre.getItems().add(menuPrincipal.getI().getTreillis().getBarres().get(i).toString());
            noeudContenu.add(menuPrincipal.getI().getTreillis().getNoeuds().get(i).toString());
        }
        VBox barres = new VBox (5, new Label("Barres"), getListBarre());
        
        this.calculCharges = new Button ("Calcul des charges");
        
        Separator sv1 = new Separator (Orientation.VERTICAL); Separator sv2 = new Separator (Orientation.VERTICAL); 
        this.getChildren().addAll(noeuds, appuiSimples, appuiDoubles, sv1, barres, sv2, calculCharges);
        this.setSpacing(10);
        
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
    public ComboBox getListNoeud() {
        return listNoeud;
    }

    /**
     * @param listNoeud the listNoeud to set
     */
    public void setListNoeud(ComboBox listNoeud) {
        this.listNoeud = listNoeud;
    }

    /**
     * @return the listAppuiSimple
     */
    public ComboBox getListAppuiSimple() {
        return listAppuiSimple;
    }

    /**
     * @param listAppuiSimple the listAppuiSimple to set
     */
    public void setListAppuiSimple(ComboBox listAppuiSimple) {
        this.listAppuiSimple = listAppuiSimple;
    }

    /**
     * @return the listAppuiDouble
     */
    public ComboBox getListAppuiDouble() {
        return listAppuiDouble;
    }

    /**
     * @param listAppuiDouble the listAppuiDouble to set
     */
    public void setListAppuiDouble(ComboBox listAppuiDouble) {
        this.listAppuiDouble = listAppuiDouble;
    }

    /**
     * @return the listBarre
     */
    public ComboBox getListBarre() {
        return listBarre;
    }

    /**
     * @param listBarre the listBarre to set
     */
    public void setListBarre(ComboBox listBarre) {
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
