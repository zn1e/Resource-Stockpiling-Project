package seng201.team0.services;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DialogPane;
import seng201.team0.GameEnvironment;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RoundService {
    /**
     * The game environment instance.
     */
    private GameEnvironment gameEnvironment;
    /**
     * The inventory service instance.
     */
    private InventoryService inventoryService;

    /**
     * Constructor for this class with the specified game environment instance.
     * @param gameEnvironment The game environment instance associated with this class.
     */
    public RoundService(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.inventoryService = new InventoryService(gameEnvironment);
    }

    /**
     * Handles the interaction after each round.
     * This method checks if the round is successful or not.
     * @param allCartsFilled Boolean describing if all carts were filled.
     */
    public void afterRound(boolean allCartsFilled){
        int currentRound = gameEnvironment.getCurrentRound();
        int numberOfRounds = gameEnvironment.getNumberOfRounds();
        int numberOfTowers = gameEnvironment.getTowerList().size();
        if (!allCartsFilled){
            gameEnvironment.launchEndScreen();
        } else if (numberOfTowers == 0) {
            Platform.runLater(this::showNoTowersAlert);
            gameEnvironment.launchEndScreen();
        } else if (allCartsFilled && currentRound == numberOfRounds) {
            gameEnvironment.setVictoryFlag(true);
            gameEnvironment.incrementCompletedRounds();
            gameEnvironment.launchEndScreen();
        }else {
            addRewards();
            Platform.runLater(this::selectNextRoundDifficulty); // wait animation to ends before calling the method
        }
    }

    /**
     * Add rewards to the player based on the completed round difficulty.
     */
    private void addRewards() {
        String roundDifficulty = gameEnvironment.getRoundDifficulty();
        if (roundDifficulty.equals("easy")) {
            gameEnvironment.addPlayerGold(50);
            gameEnvironment.addPlayerPoints(10);
            if (!inventoryService.isGoldItemUsed()){
                gameEnvironment.addPlayerGold(75);
                inventoryService.setGoldItemUsed(true);
            }
            if (!inventoryService.isPointsItemUsed()){
                inventoryService.setPointsItemUsed(true);
                gameEnvironment.addPlayerPoints(5);
            }
        } else {
            gameEnvironment.addPlayerGold(100);
            gameEnvironment.addPlayerPoints(20);
            if (!inventoryService.isGoldItemUsed()){
                inventoryService.setGoldItemUsed(true);
                gameEnvironment.addPlayerGold(90);
            }
            if (!inventoryService.isPointsItemUsed()){
                inventoryService.setPointsItemUsed(true);
                gameEnvironment.addPlayerPoints(10);
            }
        }
        gameEnvironment.incrementCompletedRounds();
        gameEnvironment.incrementCurrentRound();
    }

    /**
     * Opens a choice dialog box asking the player for the difficulty of next round.
     */
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

    /**
     * Show alert when there's no more towers.
     */
    private void showNoTowersAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Working Towers!");
        alert.setHeaderText(null);
        alert.setContentText("No more working towers!");
        alert.showAndWait();
    }
}
