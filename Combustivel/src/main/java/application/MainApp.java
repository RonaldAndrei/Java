package application;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.PrincipalController;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/Principal.fxml"));
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Principal.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Etanol X Gasolina");
        stage.getIcons().add(new Image("/fxml/icon.png"));
        stage.setScene(scene);
        
        PrincipalController principalController = loader.getController();
        
        scene.getWindow().setOnCloseRequest((WindowEvent ev) -> {principalController.gravar();});
        stage.show();
    }
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
    '    *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
