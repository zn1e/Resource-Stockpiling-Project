package seng201.team0.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Class starts the javaFX application window
 * @author seng201 teaching team
 */
public class FXWindow extends Application {
    /**
     * Default constructor for initializing a new instance of FXWindow.
     */
    public FXWindow(){
        super();
    }
    /**
     * Opens the gui with the fxml content specified in resources/fxml/main.fxml
     * @param primaryStage The current fxml stage, handled by javaFX Application class
     * @throws IOException if there is an issue loading fxml file
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader baseloader = new FXMLLoader(getClass().getResource("/fxml/fx_wrapper.fxml"));
        Parent root = baseloader.load();
        FXWrapper fxWrapper = baseloader.getController();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Organ Factory");
        primaryStage.show();
        fxWrapper.init(primaryStage);
    }
    /**
     * Launches the FXML application, this must be called from another class (in this cass App.java) otherwise JavaFX
     * errors out and does not run
     * @param args command line arguments
     */
    public static void launchScreen(String [] args) {
        launch(args);
    }

}
