package Interface;

import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
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
//        Scene scene = new Scene(I, 500, 400, true);
//        //scene.getStylesheets().add(getClass().getResource("Apparence.css").toExternalForm());
//        URL url = this.getClass().getResource("Apparence.css");
//        System.out.println(url);


        Scene scene = new Scene (I, 1000, 500);
        stage.setScene(scene);
        stage.show();
        
//        pT.setOnAction((t) -> {
//            SaisiePointTerrain sP = new SaisiePointTerrain(stage);
//            sP.setResizable(false);
//            sP.show();
//        });

//        Button b = new Button("test");
//        Pane pane = new Pane (b);
//        Scene scene = new Scene(pane);
//        System.out.println(getClass().getResource("App.java"));
//        String path = new File("Apparence.css").getPath();
//        System.out.println(path);
//        //scene.getStylesheets().add(getClass().getResource("Apparence.css").toExternalForm());
//        scene.getStylesheets().add(path);
//        stage.setScene(scene);
//        stage.show();
        
    }

    

    public static void main(String[] args) {
        Application.launch(args);
    }

}
