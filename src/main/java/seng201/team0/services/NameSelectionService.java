package seng201.team0.services;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import seng201.team0.GameEnvironment;

public class NameSelectionService {
    /**
     * The game environment instance.
     */
    private GameEnvironment gameEnvironment;
    /**
     * Describes if the name is verified.
     */
    private boolean nameVerified;

    /**
     * Constructor for this class with specified game environment instance.
     * @param gameEnvironment The game environment instance associated with this class.
     */
    public NameSelectionService(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Handles the event when verify button is clicked.
     * @param verifyButton Button for verifying the inputted name.
     * @param playerNameTextField Text field containing the player's name.
     * @param formatLabel The label containing the instruction of required format.
     */
    public void setVerifyButtonAction(Button verifyButton, TextField playerNameTextField, Label formatLabel){
        verifyButton.setOnAction(event -> verifyButtonClicked(verifyButton, playerNameTextField, formatLabel));
    }

    /**
     * Handles the event when next button is clicked.
     * @param nextButton Button for proceeding to next screen.
     * @param playerNameTextField Text field containing the player's name.
     */
    public void setNextButtonAction(Button nextButton, TextField playerNameTextField){
        nextButton.setOnAction(event -> nextButtonClicked(playerNameTextField));
    }

    /**
     * Handles the action when verify button is clicked.
     * Changes the text of button if the input is verified or not.
     * @param verifyButton The verify button confirming if the input is accepted.
     * @param playerNameTextField The text field containing the player name text.
     * @param formatLabel The label containing the instruction of required format.
     */
    private void verifyButtonClicked(Button verifyButton, TextField playerNameTextField, Label formatLabel){
        String name = playerNameTextField.getText();
        if (name.length() >= 3 && name.length() <= 15 && name.matches("^[a-zA-Z0-9]+$")){
            nameVerified = true;
            verifyButton.setText("OK");
        } else{
            nameVerified = false;
            verifyButton.setText("Try again");
            formatLabel.setTextFill(Color.RED);
        }
    }

    /**
     * Handles the action when next button is clicked.
     * It sets the player name in the game environment class.
     * @param playerNameTextField The text field containing the text of player name.
     */
    private void nextButtonClicked(TextField playerNameTextField){
        if (nameVerified && playerNameTextField.getText() != null){
            gameEnvironment.setPlayerName(playerNameTextField.getText());
            gameEnvironment.launchSetupScreen2();
        }
    }
}
