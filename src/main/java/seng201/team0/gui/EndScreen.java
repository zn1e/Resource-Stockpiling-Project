package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.GameEnvironment;

public class EndScreen {
    private GameEnvironment gameEnvironment;
    @FXML
    Label resultLabel, playerNameLabel, roundsLabel, completedRoundsLabel, goldGainedLabel, pointsGainedLabel;
    @FXML
    Button quitButton;
    public EndScreen(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
    }
    public void initialize(){
        updateLabels();
        quitButton.setOnAction(event -> gameEnvironment.closeScreen());
    }
    private void updateLabels(){
        boolean victory = gameEnvironment.getVictoryFlag();
        if (!victory){
            resultLabel.setText("DEFEAT!");
        }
        System.out.println("what?");
        playerNameLabel.setText("Player Name: " + gameEnvironment.getPlayerName());
        roundsLabel.setText("# of rounds: " + gameEnvironment.getNumberOfRounds());
        completedRoundsLabel.setText("# of rounds completed: " + gameEnvironment.getCompletedRounds());
        goldGainedLabel.setText("Gold gained: " + gameEnvironment.getGoldGained());
        pointsGainedLabel.setText("Points gained: " + gameEnvironment.getPointsGained());
    }
}
