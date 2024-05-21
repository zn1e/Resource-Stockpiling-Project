package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import seng201.team0.GameEnvironment;

/**
 * Controller class for the number of rounds and difficulty selection screen.
 * Handles the user input for the number of rounds and difficulty.
 */
public class RoundAndDifficultySelection {
    /**
     * The game environment instance.
     */
    private GameEnvironment gameEnvironment;
    /**
     * The number of rounds.
     */
    private int numberOfRounds;
    /**
     * Button for proceeding to next screen.
     */
    @FXML
    private Button nextButton;
    /**
     * Toggle buttons for round difficulty.
     */
    @FXML
    private ToggleButton easyToggleButton, hardToggleButton;
    /**
     * Toggle group for round difficulty toggle buttons.
     */
    @FXML
    private ToggleGroup difficultyToggleGroup;
    /**
     * Slider for the number of rounds.
     */
    @FXML
    private Slider roundSlider;
    /**
     * The selected toggle button
     */
    private ToggleButton selectedButton;

    /**
     * Constructor for the RoundAndDifficultySelection with the specified game environment instance.
     * @param gameEnvironment The game environment instance to associate with this class.
     */
    public RoundAndDifficultySelection(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Initializes the controller by setting up UI components and event handlers.
     * This method is automatically called by JavaFX after the fxml file is loaded and instantiated.
     */
    public void initialize(){
        // initializes toggle group for the difficulty selection
        initToggleGroup();
        // add listener to round slider to update number of rounds
        roundSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                updateNumberOfRounds(newValue.intValue()));
        // event handler for next button
        nextButton.setOnAction(event -> nextButtonClicked());
        // set the easy difficulty as default
        easyToggleButton.setSelected(true);
        // initialize round value
        numberOfRounds = (int) roundSlider.getValue();
    }

    /**
     * Method called by round slider listener to update the number of rounds.
     * @param newValue An int representing the value of number of rounds.
     */
    private void updateNumberOfRounds(int newValue){
        numberOfRounds = newValue;
    }

    /**
     * Initializes the toggle group for difficulty selection.
     * Creates a new instance of toggle group and add the easy and hard toggle buttons
     * to the group.
     */
    private void initToggleGroup(){
        difficultyToggleGroup = new ToggleGroup();
        easyToggleButton.setToggleGroup(difficultyToggleGroup);
        hardToggleButton.setToggleGroup(difficultyToggleGroup);
    }

    /**
     * Handles the event when next button is clicked.
     * It sets the number of rounds and round difficulty in the game environment class.
     * Launches the tower selection screen.
     */
    private void nextButtonClicked(){
        selectedButton = (ToggleButton) difficultyToggleGroup.getSelectedToggle();
        gameEnvironment.setNumberOfRounds(numberOfRounds);
        gameEnvironment.setRoundDifficulty(selectedButton.getText().toLowerCase());
        gameEnvironment.launchSetupScreen3();
    }
}
