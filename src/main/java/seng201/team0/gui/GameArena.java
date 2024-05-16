package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Tower;
import seng201.team0.services.TowerService;
import seng201.team0.services.UIService;

import java.util.List;

public class GameArena {
    private GameEnvironment gameEnvironment;
    private TowerService towerService;
    private UIService uiService;
    @FXML
    GridPane trackGrid;
    @FXML
    Label goldLabel, pointsLabel, difficultyLabel, roundLabel;
    @FXML
    Button playButton;
    @FXML
    Label tower1NameLabel, tower2NameLabel, tower3NameLabel, tower4NameLabel, tower5NameLabel;
    @FXML
    Label tower1TypeLabel, tower2TypeLabel, tower3TypeLabel, tower4TypeLabel, tower5TypeLabel;
    @FXML
    Label tower1SpeedLabel, tower2SpeedLabel, tower3SpeedLabel, tower4SpeedLabel, tower5SpeedLabel;
    @FXML
    Label tower1LevelLabel, tower2LevelLabel, tower3LevelLabel, tower4LevelLabel, tower5LevelLabel;

    public GameArena(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.towerService = new TowerService(gameEnvironment);
        this.uiService = new UIService(gameEnvironment);
    }
    public void initialize(){
        updateUI();
        playButton.setOnAction(event -> startGame());
    }
    private void updateUI() {
        uiService.updateGoldLabel(goldLabel);
        uiService.updatePointsLabel(pointsLabel);
        uiService.updateDifficultyLabel(difficultyLabel);
        uiService.updateRoundLabel(roundLabel);
        updateTowerLabels();
    }

    private void updateTowerLabels() {
        List<Tower> towers = gameEnvironment.getTowerList();
        Label[] towerNameLabels = {tower1NameLabel, tower2NameLabel, tower3NameLabel, tower4NameLabel, tower5NameLabel};
        Label[] towerTypeLabels = {tower1TypeLabel, tower2TypeLabel, tower3TypeLabel, tower4TypeLabel, tower5TypeLabel};
        Label[] towerSpeedLabels = {tower1SpeedLabel, tower2SpeedLabel, tower3SpeedLabel, tower4SpeedLabel, tower5SpeedLabel};
        Label[] towerLevelLabels = {tower1LevelLabel, tower2LevelLabel, tower3LevelLabel, tower4LevelLabel, tower5LevelLabel};

        uiService.updateTowerLabels(towers, towerNameLabels, towerTypeLabels, towerSpeedLabels, towerLevelLabels);
    }
    private void startGame(){

    }
}
