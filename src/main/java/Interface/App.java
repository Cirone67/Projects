package Interface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws FileNotFoundException {
      
//        BorderPane bp = new BorderPane();
//        bp.setTop(choix);
          
//        Scene scene = new Scene(I, 500, 400, true);
//        //scene.getStylesheets().add(getClass().getResource("Apparence.css").toExternalForm());
//        URL url = this.getClass().getResource("Apparence.css");
//        System.out.println(url);

        Interface I = new Interface (stage);
        Scene scene = new Scene (I, 1000, 500);
        stage.setScene(scene);
        stage.setTitle("Treillis Meister");
        FileInputStream inputstream = new FileInputStream("C:\\Users\\Guillaume R\\Documents\\NetBeansProjects\\Projects\\src\\main\\java\\Interface\\Bridge Logo2.png");
        Image img = new Image(inputstream);        
        stage.getIcons().add(img);
        stage.show();
    

//        Button b = new Button("test");
//        Pane pane = new Pane (b);
//        Scene scene = new Scene(pane);
//        System.out.println(getClass().getResource("App.java"));
//        String path = new File("Apparence.css").getPath();
//        System.out.println(path);
//        //scene.getStylesheets().add(getClass().getResource("Apparence.css").toExternalForm());    C:\Users\Guillaume R\Documents\NetBeansProjects\Projects\src\main\java\Interface\Apparence.css
//        scene.getStylesheets().add(path);
//        stage.setScene(scene);
//        stage.show();
        
    }  

    public static void main(String[] args) {
        Application.launch(args);
    }

}
