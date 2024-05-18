package seng201.team0.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Tower;
import seng201.team0.services.TowerService;
import seng201.team0.services.TrackService;
import seng201.team0.services.UIService;

import java.util.List;

public class GameArena {
    private GameEnvironment gameEnvironment;
    private TowerService towerService;
    private UIService uiService;
    private TrackService trackService;
    private Timeline timeline;
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
    Label tower1AmountLabel, tower2AmountLabel, tower3AmountLabel, tower4AmountLabel, tower5AmountLabel;
    @FXML
    Label tower1SpeedLabel, tower2SpeedLabel, tower3SpeedLabel, tower4SpeedLabel, tower5SpeedLabel;
    @FXML
    Label tower1LevelLabel, tower2LevelLabel, tower3LevelLabel, tower4LevelLabel, tower5LevelLabel;


    public GameArena(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.towerService = new TowerService(gameEnvironment);
        this.uiService = new UIService(gameEnvironment);
        this.trackService = new TrackService(gameEnvironment);
    }
    public void initialize(){
        updateUI();
        setupTowers(trackGrid);
        setupTrack(trackGrid);
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
        Label[] towerAmountLabels = {tower1AmountLabel, tower2AmountLabel, tower3AmountLabel, tower4AmountLabel, tower5AmountLabel};
        Label[] towerSpeedLabels = {tower1SpeedLabel, tower2SpeedLabel, tower3SpeedLabel, tower4SpeedLabel, tower5SpeedLabel};
        Label[] towerLevelLabels = {tower1LevelLabel, tower2LevelLabel, tower3LevelLabel, tower4LevelLabel, tower5LevelLabel};

        uiService.updateTowerLabels(towers, towerNameLabels, towerTypeLabels, towerAmountLabels, towerSpeedLabels, towerLevelLabels);
    }
    private void setupTowers(GridPane trackGrid){
        towerService.setupMainTowers(trackGrid);
    }
    private void setupTrack(GridPane trackGrid){
        trackService.setupTrackImage(trackGrid);
    }

    private void startGame(){
        trackService.initializeCarts(trackGrid);
        trackService.maxDistanceCoveredProperty().addListener((observableValue, notCompleted, nowCompleted) -> {
            if (nowCompleted){
                stopGame();
            }
        });
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> trackService.moveCarts(trackGrid)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    private void stopGame(){
        if (timeline != null){
            timeline.stop();
        }
    }
}
