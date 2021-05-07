/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import fr.insa.brenckle.projets.AppuiDouble;
import fr.insa.brenckle.projets.AppuiSimple;
import fr.insa.brenckle.projets.Barre;
import fr.insa.brenckle.projets.Noeud;
import fr.insa.brenckle.projets.NoeudSimple;
import fr.insa.brenckle.projets.Objet;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    private Button reinitialiserCharge;
    private TextField norme;
    private TextField angle;
    private Button calculForce;
    private Noeud noeudSelect;
    private Text prix;
    
    public MenuGestion (MenuPrincipal mP){
        
        this.menuPrincipal = mP;
        
        this.prix = new Text("0 €"); prix.setStyle("-fx-font-size: 11pt; -fx-font-family: \"Segoe UI Semibold\"");
        Label pr = new Label("Coût du treillis:"); pr.setStyle("-fx-font-size: 9pt; -fx-font-family: \"Segoe UI Semibold\"");
        VBox hbPrix = new VBox (pr, getPrix());
        Pane panePrix = new Pane(hbPrix);
        
        this.noeudSelect = null;
        this.listNoeud = new ListView<String>(); listNoeud.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        VBox noeuds = new VBox (5, new Label("Noeuds Simples"), getListNoeud());
        noeuds.setPrefHeight(100);
        noeuds.setPrefWidth(125);
        
        this.listAppuiSimple = new ListView<String>(); listAppuiSimple.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        VBox appuiSimples = new VBox (5, new Label("Appuis Simples"), getListAppuiSimple());
        appuiSimples.setPrefHeight(100);
        appuiSimples.setPrefWidth(200);
        
        this.listAppuiDouble = new ListView<String>(); listAppuiDouble.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        VBox appuiDoubles = new VBox (5, new Label("Appuis doubles"), getListAppuiDouble());
        appuiDoubles.setPrefHeight(100);
        appuiDoubles.setPrefWidth(200);
        
        this.listBarre = new ListView<String>(); listBarre.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        VBox barres = new VBox (5, new Label("Barres"), getListBarre());
        barres.setPrefHeight(100);
        barres.setPrefWidth(250);
        
        this.calculForce = new Button ("Calcul des forces"); this.calculForce.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt"); 
        
        this.angle = new TextField(); angle.setStyle("-fx-font-family: \"Segoe UI Semibold\"");
        VBox saisieAngle = new VBox ( new Label("Angle:"), getAngle());
        this.norme = new TextField(); norme.setStyle(" -fx-font-family: \"Segoe UI Semibold\"");
        VBox saisieNorme = new VBox ( new Label("Norme:"), getNorme());
        VBox vbsaisie = new VBox (5, saisieNorme, saisieAngle);
        
        this.saisieCharge = new Button ("Créer charge"); saisieCharge.setDisable(true); this.saisieCharge.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        this.reinitialiserCharge = new Button ("Réinitialiser"); this.reinitialiserCharge.setStyle("-fx-background-color: #ccc; -fx-text-color: #111; -fx-border-color: #e2e2e2; -fx-border-width: 2; -fx-padding: 3 10 3 10; -fx-font-size: 9pt");
        VBox boutonsCharge = new VBox (10, saisieCharge, reinitialiserCharge);
        
        HBox hbentrercharge = new HBox (10, vbsaisie, boutonsCharge); HBox.setMargin(boutonsCharge, new Insets (35,0,0,0));
        VBox vbcharge = new VBox (hbentrercharge, calculForce);
        VBox.setMargin(calculForce, new Insets (15,5,10,10));
        
        Separator sv1 = new Separator (Orientation.VERTICAL); Separator sv2 = new Separator (Orientation.VERTICAL); Separator sv3 = new Separator (Orientation.VERTICAL); 
        this.getChildren().addAll(noeuds, appuiSimples, appuiDoubles, sv1, barres, sv2, vbcharge, sv3, panePrix);  //rajouter indicateur du coût
        this.setSpacing(10); this.setStyle("-fx-background-color: #e5e5e5");
        HBox.setMargin(noeuds, new Insets (5,0,5,10)); HBox.setMargin(appuiSimples, new Insets (5,0,5,10));
        HBox.setMargin(appuiDoubles, new Insets (5,0,5,10)); HBox.setMargin(barres, new Insets (5,0,5,0));
        HBox.setMargin(vbcharge, new Insets (5,5,0,0)); 
        HBox.setMargin(panePrix, new Insets(30,10,30,10));
        
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
        this.reinitialiserCharge.setOnAction((t) -> {
            this.menuPrincipal.getI().getControleur().changeEtat(61);
        });
        this.calculForce.setOnAction((t) -> {
            this.menuPrincipal.getI().getControleur().changeEtat(150);
        });
        
        
    }
    public void supprimeList(Objet O){
        if (O instanceof Barre){
            for (int i=0; i<this.getListBarre().getItems().size(); i++){
                if (this.getListBarre().getItems().get(i).equals(((Barre) O).toString())){
                    this.getListBarre().getItems().remove(i);
                }
            }            
        }
        else if (O instanceof NoeudSimple){
            for (int i=0; i<this.getListNoeud().getItems().size(); i++){
                if (this.getListNoeud().getItems().get(i).equals(((NoeudSimple) O).toString())){
                    this.getListNoeud().getItems().remove(i);
                }
            }            
        }
        else if (O instanceof AppuiSimple){
            for (int i=0; i<this.getListAppuiSimple().getItems().size(); i++){
                if (this.getListAppuiSimple().getItems().get(i).equals(((AppuiSimple) O).toString())){
                    this.getListAppuiSimple().getItems().remove(i);
                }
            }            
        } 
        else if (O instanceof AppuiDouble){
            for (int i=0; i<this.getListAppuiDouble().getItems().size(); i++){
                if (this.getListAppuiDouble().getItems().get(i).equals(((AppuiDouble) O).toString())){
                    this.getListAppuiDouble().getItems().remove(i);
                }
            }            
        }         
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
    public Button getCalculForce() {
        return calculForce;
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

    /**
     * @return the prix
     */
    public Text getPrix() {
        return prix;
    }


}
