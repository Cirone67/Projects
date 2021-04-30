/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Guillaume R
 */
public class SousMenuPpl extends VBox {
    private Node titre;
    private Node droite;
    private Node gauche;

    public SousMenuPpl (Node titre, Node gauche, Node droite, int entreST, int hautT, int droiteT, int basT, int gaucheT, int hautST, int droiteST, int basST, int gaucheST, boolean separateur){
        this.titre = titre;
        this.gauche = gauche;
        this.droite = droite;
        if (separateur == true){
            Separator s = new Separator(Orientation.VERTICAL);
            HBox sousTitre = new HBox (entreST, this.gauche, s, this.droite);
            VBox menu = new VBox (this.titre, sousTitre);
            VBox.setMargin(titre, new Insets(hautT,droiteT,basT,gaucheT));
            VBox.setMargin(sousTitre, new Insets(hautST,droiteST,basST,gaucheST));
            this.getChildren().addAll(this.titre, sousTitre);            
            
        } else{
         HBox sousTitre = new HBox (entreST, this.gauche, this.droite);  
         VBox menu = new VBox (this.titre, sousTitre);
         VBox.setMargin(titre, new Insets(hautT,droiteT,basT,gaucheT));
         VBox.setMargin(sousTitre, new Insets(hautST,droiteST,basST,gaucheST));
         this.getChildren().addAll(this.titre, sousTitre);
        
        
    }
    
}
}
