package seng201.team0.services;

import javafx.application.Platform;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DialogPane;
import seng201.team0.GameEnvironment;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RoundService {
    private GameEnvironment gameEnvironment;

    public RoundService(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
    }
    public void afterRound(boolean allCartsFilled){
        int currentRound = gameEnvironment.getCurrentRound();
        int numberOfRounds = gameEnvironment.getNumberOfRounds();
        if (!allCartsFilled){
            gameEnvironment.launchEndScreen();
        } else if (allCartsFilled && currentRound == numberOfRounds) {
            gameEnvironment.setVictoryFlag(true);
            gameEnvironment.launchEndScreen();
        }else {
            addRewards();
            Platform.runLater(this::selectNextRoundDifficulty);
        }
    }
    private void addRewards(){
        String roundDifficulty = gameEnvironment.getRoundDifficulty();
        if (roundDifficulty.equals("easy")){
            gameEnvironment.addPlayerGold(50);
            gameEnvironment.addPlayerPoints(10);
        } else{
            gameEnvironment.addPlayerGold(100);
            gameEnvironment.addPlayerPoints(20);
        }
        gameEnvironment.incrementCompletedRounds();
        gameEnvironment.incrementCurrentRound();
    }
    private void selectNextRoundDifficulty() {
        List<String> choices = Arrays.asList("Easy", "Hard");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Easy", choices);
        dialog.setTitle("Next Round Difficulty");
        dialog.setHeaderText("Select the difficulty for the next round:");
        dialog.setContentText("Difficulty:");

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setGraphic(null);

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(difficulty -> {
            gameEnvironment.setRoundDifficulty(difficulty.toLowerCase());
            gameEnvironment.launchMainScreen();
        });
    }
}
