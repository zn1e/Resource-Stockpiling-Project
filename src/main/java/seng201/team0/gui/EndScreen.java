package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.GameEnvironment;

/**
 * Class that handles the end screen functionalities.
 */
public class EndScreen {
    /**
     * The game environment instance.
     */
    private GameEnvironment gameEnvironment;
    /**
     * Labels for end screen.
     */
    @FXML
    Label resultLabel, playerNameLabel, roundsLabel, completedRoundsLabel, goldGainedLabel, pointsGainedLabel;
    /**
     * Button for quitting the game.
     */
    @FXML
    Button quitButton;

    /**
     * Constructor for EndScreen with specified game environment instance.
     * @param gameEnvironment The game environment instance to associate with this class.
     */
    public EndScreen(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Initializes the screen by setting up the UI components and event handlers.
     */
    public void initialize(){
        updateLabels();
        quitButton.setOnAction(event -> gameEnvironment.closeScreen());
    }

    /**
     * Update the labels of the UI. Displays the result of the game and the stats of the player.
     */
    private void updateLabels(){
        boolean victory = gameEnvironment.getVictoryFlag();
        if (!victory){
            resultLabel.setText("DEFEAT!");
        }
        playerNameLabel.setText("Player Name: " + gameEnvironment.getPlayerName());
        roundsLabel.setText("# of rounds: " + gameEnvironment.getNumberOfRounds());
        completedRoundsLabel.setText("# of rounds completed: " + gameEnvironment.getCompletedRounds());
        goldGainedLabel.setText("Gold gained: " + gameEnvironment.getGoldGained());
        pointsGainedLabel.setText("Points gained: " + gameEnvironment.getPointsGained());
    }
}
