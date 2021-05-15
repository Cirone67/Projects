package Interface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.scene.Scene;
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
        
    }  

    public static void main(String[] args) {
        Application.launch(args);
    }

}
