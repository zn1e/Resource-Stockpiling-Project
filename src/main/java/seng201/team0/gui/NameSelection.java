package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import seng201.team0.GameEnvironment;
import seng201.team0.services.NameSelectionService;

/**
 * Controller class for the name selection screen.
 * Handles the user input for player name and verification.
 */
public class NameSelection {
    /**
     * The game environment instance.
     */
    private GameEnvironment gameEnvironment;
    /**
     * The name selection service instance.
     */
    private NameSelectionService setupScreenService;
    /**
     * Buttons for player name verification and proceeding to next screen.
     */
    @FXML
    private Button verifyButton, nextButton;
    /**
     * Text field for entering player name.
     */
    @FXML
    private TextField playerNameTextField;
    /**
     * Label indicating the format instruction.
     */
    @FXML
    private Label formatLabel;

    /**
     * Constructor for the NameSelection with the specified game environment instance.
     * Creates new instance of name selection service that handles the functionality.
     * @param gameEnvironment The game environment instance to associate with this class.
     */
    public NameSelection(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.setupScreenService = new NameSelectionService(gameEnvironment);
    }

    /**
     * Initializes the controller by setting event handlers for the verify button and next button.
     * This method is automatically called by JavaFX after the fxml file is loaded and instantiated.
     */
    public void initialize(){
        setupScreenService.setVerifyButtonAction(verifyButton, playerNameTextField, formatLabel);
        setupScreenService.setNextButtonAction(nextButton, playerNameTextField);
    }

}
