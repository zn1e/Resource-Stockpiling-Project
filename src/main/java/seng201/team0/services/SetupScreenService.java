package seng201.team0.services;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import seng201.team0.GameEnvironment;

public class SetupScreenService {
    private GameEnvironment gameEnvironment;
    private boolean nameVerified;
    public SetupScreenService(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
    }
    public void setVerifyButtonAction(Button verifyButton, TextField playerNameTextField){
        verifyButton.setOnAction(event -> verifyButtonClicked(verifyButton,playerNameTextField));
    }
    public void setNextButtonAction(Button nextButton, TextField playerNameTextField){
        nextButton.setOnAction(event -> nextButtonClicked(playerNameTextField));
    }

    /**
     * Handles the action when verify button is clicked.
     * Changes the text of button if the input is verified or not.
     * @param verifyButton The verify button confirming if the input is accepted.
     * @param playerNameTextField The text field containing the player name text.
     */
    private void verifyButtonClicked(Button verifyButton, TextField playerNameTextField){
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
     * @param playerNameTextField The text field containing the text of player name.
     */
    private void nextButtonClicked(TextField playerNameTextField){
        if (nameVerified && playerNameTextField.getText() != null){
            gameEnvironment.setPlayerName(playerNameTextField.getText());
            gameEnvironment.launchSetupScreen2();
        }
    }
}
