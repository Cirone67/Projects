package Interface;

import java.io.InputStream;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage){

        Interface I = new Interface (stage);
        Scene scene = new Scene (I, 1000, 500);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("Ressources/Apparence.css").toString());    
        stage.setTitle("Treillis Meister");
        InputStream inp = this.getClass().getResourceAsStream("Ressources/Bridge Logo2.png");
        Image img = new Image(inp);        
        stage.getIcons().add(img);
        stage.show();
        
        stage.setOnCloseRequest((t) -> {  //A FAIRE bouton annuler
            if (I.getMenuPrincipal().getMenuEdition().getEtatSauvegarde() != 1){
                t.consume();
                Alert confirmer = new Alert(Alert.AlertType.CONFIRMATION);
                ButtonType enregistrer = new ButtonType("Enregistrer");
                ButtonType enregistrerSous = new ButtonType("Enregistrer sous");
                ButtonType ignorer = new ButtonType("Ignorer");
                ButtonType annuler = new ButtonType("Annuler");
                confirmer.setTitle("Attention");
                confirmer.setHeaderText("Enregistrer vos modifications?");
                confirmer.getButtonTypes().clear();
                confirmer.getButtonTypes().addAll(enregistrer, enregistrerSous, annuler, ignorer);
                Optional<ButtonType> select = confirmer.showAndWait();
                
                if (select.get() == enregistrerSous){
                   I.getControleur().changeEtat(110);
                   stage.close();
                } else if (select.get() == enregistrer){
                   I.getControleur().changeEtat(111);
                   stage.close(); 
                }
                else if (select.get() == ignorer){
                   stage.close(); 
                } else if (select.get() == annuler){
                    
                }              
            }           
        });
        
    }  

    public static void main(String[] args) {
        Application.launch(args);
    }

}
