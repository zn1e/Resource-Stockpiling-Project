package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seng201.team0.GameEnvironment;

import java.io.IOException;

/**
 * FXWrapper class handles the initialization and setup of JavaFX application screens.
 */
public class FXWrapper {
    @FXML
    private AnchorPane anchorPane;
    private Stage stage;

    /**
     * Initialize the game environment with the provided stage and setup screen launch methods.
     * @param stage The primary stage for the JavaFX application.
     */
    public void init(Stage stage) {
        this.stage = stage;
        new GameEnvironment(
                this::launchSetupScreen1, // launch method for name selection screen
                this::launchSetupScreen2, // launch method for number of rounds and difficulty screen
                this::launchSetupScreen3, // launch method for tower selection screen
                this::launchMainScreen,
                this::launchShopScreen,
                this::launchEndScreen,
                this::clearAnchorPane // method to clear screen
        );
    }

    /**
     * Launches the name selection setup screen with the provided game environment.
     * @param gameEnvironment The game environment containing necessary data and functionalities.
     */
    public void launchSetupScreen1(GameEnvironment gameEnvironment) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/NameSelection.fxml"));
            // provide a custom Controller with parameters
            setupLoader.setControllerFactory(param -> new NameSelection(gameEnvironment));
            Parent setupParent  = setupLoader.load();
            anchorPane.getChildren().add(setupParent);
            stage.setTitle("Organ Factory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the number of rounds and difficulty selection with the provided game environment.
     * @param gameEnvironment The game environment containing necessary data and functionalities.
     */
    public void launchSetupScreen2(GameEnvironment gameEnvironment){
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/RoundAndDifficultySelection.fxml"));
            // provide a custom Controller with parameters
            setupLoader.setControllerFactory(param -> new RoundAndDifficultySelection(gameEnvironment));
            Parent setupParent  = setupLoader.load();
            anchorPane.getChildren().add(setupParent);
            stage.setTitle("Organ Factory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Launches the tower selection with the provided game environment.
     * @param gameEnvironment The game environment containing necessary data and functionalities.
     */
    public void launchSetupScreen3(GameEnvironment gameEnvironment){
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/TowerSelection.fxml"));
            // provide a custom Controller with parameters
            setupLoader.setControllerFactory(param -> new TowerSelection(gameEnvironment));
            Parent setupParent  = setupLoader.load();
            anchorPane.getChildren().add(setupParent);
            stage.setTitle("Organ Factory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void launchShopScreen(GameEnvironment gameEnvironment){
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/Shop.fxml"));
            // provide a custom Controller with parameters
            mainScreenLoader.setControllerFactory(param -> new ShopController(gameEnvironment));
            Parent setupParent  = mainScreenLoader.load();
            anchorPane.getChildren().add(setupParent);
            stage.setTitle("Organ Factory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the screen by removing the children of the anchor pane.
     */
    public void clearAnchorPane() {
        anchorPane.getChildren().removeAll(anchorPane.getChildren());
    }

    public void launchMainScreen(GameEnvironment gameEnvironment) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/GameArena.fxml"));
            mainScreenLoader.setControllerFactory(param -> new GameArena(gameEnvironment));
            Parent setupParent  = mainScreenLoader.load();
            anchorPane.getChildren().add(setupParent);
            stage.setTitle("Organ Factory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void launchEndScreen(GameEnvironment gameEnvironment) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/EndScreen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new EndScreen(gameEnvironment));
            Parent setupParent  = mainScreenLoader.load();
            anchorPane.getChildren().add(setupParent);
            stage.setTitle("Organ Factory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
