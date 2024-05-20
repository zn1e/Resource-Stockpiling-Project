package seng201.team0.services;

import javafx.scene.control.Label;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Tower;

import java.util.List;

public class UIService {
    private GameEnvironment gameEnvironment;

    public UIService(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    public void updateGoldLabel(Label goldLabel) {
        goldLabel.setText(String.valueOf(gameEnvironment.getPlayerGold()));
    }

    public void updatePointsLabel(Label pointsLabel) {
        pointsLabel.setText(String.valueOf(gameEnvironment.getPlayerPoints()));
    }

    public void updateDifficultyLabel(Label difficultyLabel) {
        difficultyLabel.setText(gameEnvironment.getRoundDifficulty().toUpperCase());
    }

    public void updateRoundLabel(Label roundLabel) {
        roundLabel.setText(String.format("%d of %d", gameEnvironment.getCurrentRound(), gameEnvironment.getNumberOfRounds()));
    }

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