package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import seng201.team0.GameEnvironment;

/**
 * Controller class for the name selection screen.
 * Handles the user input for player name and verification.
 */
public class NameSelectionController {
    private GameEnvironment gameEnvironment; // the game environment instance
    private boolean nameVerified; // indicates if the player name is verified
    @FXML
    private Button verifyButton, nextButton; // buttons for player name verification and proceeding to next screen
    @FXML
    private TextField playerNameTextField; // text field for entering player name

    /**
     * Constructor for the NameSelectionController with the specified game environment instance.
     * @param gameEnvironment The game environment instance to associate with this controller.
     */
    public NameSelectionController(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Initializes the controller by setting event handlers for the verify button and next button.
     * This method is automatically called by JavaFX after the fxml file is loaded and instantiated.
     */
    public void initialize(){
        // set event handler for verify button
        verifyButton.setOnAction(event-> verifyButtonClicked());
        // set event handler for next button
        nextButton.setOnAction(event -> nextButtonClicked());
    }

    /**
     * Handles the action when verify button is clicked.
     * Player name is verified if the input is 3-15 characters long and is alphanumeric.
     * It also changes the text for verify button accordingly.
     */
    private void verifyButtonClicked(){
        String name = playerNameTextField.getText();
        if (name.length() >= 3 && name.length() <= 15 && name.matches("^[a-zA-Z0-9]+$")){
            nameVerified = true;
            verifyButton.setText("OK");
        } else{
            nameVerified = false;
            verifyButton.setText("Try again");
        }
    }

    /**
     * Handles the action when next button is clicked.
     * It sets the player name in the game environment class.
     */
    private void nextButtonClicked(){
        if (nameVerified && playerNameTextField.getText() != null){
            gameEnvironment.setPlayerName(playerNameTextField.getText());
            gameEnvironment.launchSetupScreen2();
        }
    }

}
