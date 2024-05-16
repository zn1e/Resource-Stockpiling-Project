package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import seng201.team0.GameEnvironment;
import seng201.team0.services.SetupScreenService;

/**
 * Controller class for the name selection screen.
 * Handles the user input for player name and verification.
 */
public class NameSelection {
    private GameEnvironment gameEnvironment; // the game environment instance
    private SetupScreenService setupScreenService;
    @FXML
    private Button verifyButton, nextButton; // buttons for player name verification and proceeding to next screen
    @FXML
    private TextField playerNameTextField; // text field for entering player name

    /**
     * Constructor for the NameSelectionController with the specified game environment instance.
     * @param gameEnvironment The game environment instance to associate with this controller.
     */
    public NameSelection(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.setupScreenService = new SetupScreenService(gameEnvironment);
    }

    /**
     * Initializes the controller by setting event handlers for the verify button and next button.
     * This method is automatically called by JavaFX after the fxml file is loaded and instantiated.
     */
    public void initialize(){
        setupScreenService.setVerifyButtonAction(verifyButton, playerNameTextField);
        setupScreenService.setNextButtonAction(nextButton, playerNameTextField);
    }


}
