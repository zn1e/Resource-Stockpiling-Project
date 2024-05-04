package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seng201.team0.GameEnvironment;

import java.io.IOException;

public class FXWrapper {
    @FXML
    private AnchorPane anchorPane;
    private Stage stage;

    public void init(Stage stage) {
        this.stage = stage;
        new GameEnvironment(this::launchSetupScreen, this::clearAnchorPane);
    }

    public void launchSetupScreen(GameEnvironment gameEnvironment) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/NameSelection.fxml"));
            // provide a custom Controller with parameters
            setupLoader.setControllerFactory(param -> new NameSelectionController(gameEnvironment));
            Parent setupParent  = setupLoader.load();
            anchorPane.getChildren().add(setupParent);
            stage.setTitle("<Game Name>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearAnchorPane() {
        anchorPane.getChildren().removeAll(anchorPane.getChildren());
    }

    /**
    public void launchMainScreen(RocketManager rocketManager) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/main_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new MainScreenController(rocketManager));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Rocket Manager Main Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     **/

}
