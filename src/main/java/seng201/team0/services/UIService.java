package seng201.team0.services;

import javafx.scene.control.Label;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Tower;

import java.util.List;

public class UIService {
    /**
     * The game environment instance.
     */
    private GameEnvironment gameEnvironment;

    /**
     * Constructor for this class with specified game environment instance.
     * @param gameEnvironment The game environment instance.
     */
    public UIService(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Update the gold labels.
     * @param goldLabel Label describing the player gold.
     */

    public void updateGoldLabel(Label goldLabel) {
        goldLabel.setText(String.valueOf(gameEnvironment.getPlayerGold()));
    }

    /**
     * Update the points label.
     * @param pointsLabel Label describing player points.
     */
    public void updatePointsLabel(Label pointsLabel) {
        pointsLabel.setText(String.valueOf(gameEnvironment.getPlayerPoints()));
    }

    /**
     * Update the difficulty label.
     * @param difficultyLabel Label describing the round difficulty.
     */
    public void updateDifficultyLabel(Label difficultyLabel) {
        difficultyLabel.setText(gameEnvironment.getRoundDifficulty().toUpperCase());
    }

    /**
     * Update the round label.
     * @param roundLabel Label describing the current round and number of rounds.
     */
    public void updateRoundLabel(Label roundLabel) {
        roundLabel.setText(String.format("%d of %d", gameEnvironment.getCurrentRound(), gameEnvironment.getNumberOfRounds()));
    }

    /**
     * Update the tower labels.
     * @param towers List of towers.
     * @param nameLabels Label array containing the name labels of towers.
     * @param typeLabels Label array containing the resource type labels of towers.
     * @param amountLabels Label array containing the resource amount labels of towers.
     * @param speedLabels Label array containing the reload speed labels of towers.
     * @param levelLabels Label array containing the level labels of towers.
     */
    public void updateTowerLabels(List<Tower> towers, Label[] nameLabels, Label[] typeLabels, Label[] amountLabels, Label[] speedLabels, Label[] levelLabels) {
        for (int i = 0; i < nameLabels.length; i++) {
            if (i < towers.size()) {
                Tower tower = towers.get(i);
                nameLabels[i].setText(tower.getName());
                typeLabels[i].setText("Rsrc: "+ tower.getResourceType());
                amountLabels[i].setText("Amnt: " + tower.getResourceAmount());
                speedLabels[i].setText("Reload spd: "+ tower.getReloadSpeed());
                levelLabels[i].setText("Lv. " +tower.getLevel());
            } else {
                nameLabels[i].setText("");
                typeLabels[i].setText("");
                amountLabels[i].setText("");
                speedLabels[i].setText("");
                levelLabels[i].setText("");
            }
        }
    }
}