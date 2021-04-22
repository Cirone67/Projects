package Interface;

import java.net.URL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
  
     
//        Menu edition = new Menu ("Edition");        
//        Menu creation = new Menu ("Création");   
//        MenuItem pT = new MenuItem ("Points Terrain");
//        MenuItem n = new MenuItem ("Noeuds");
//        creation.getItems().addAll(n,pT);
//        MenuBar choix = new MenuBar(edition, creation);
//        choix.prefWidthProperty().bind(stage.widthProperty());   choix.setLayoutY(10);
//        
//        BorderPane bp = new BorderPane();
//        bp.setTop(choix);
        Interface I = new Interface (stage);
        Scene scene = new Scene(I, 500, 400, true);
        //scene.getStylesheets().add(getClass().getResource("Apparence.css").toExternalForm());
        URL url = this.getClass().getResource("Apparence.css");
        System.out.println(url);
        stage.setScene(scene);
        stage.show();
        
//        pT.setOnAction((t) -> {
//            SaisiePointTerrain sP = new SaisiePointTerrain(stage);
//            sP.setResizable(false);
//            sP.show();
//        });
        
    }

    

    public static void main(String[] args) {
        Application.launch(args);
    }

}
