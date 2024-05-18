package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Tower;

import java.util.List;

public class GameArena {
    private GameEnvironment gameEnvironment;
    @FXML
    GridPane trackGrid;
    @FXML
    Label goldLabel, pointsLabel, difficultyLabel, roundLabel;
    @FXML
    Button playButton;
    @FXML
    Button shopButton;
    @FXML
    Button inventoryButton;
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
    }
    public void initialize(){
        updateUI();
        playButton.setOnAction(event -> startGame());
        shopButton.setOnAction(event -> shopButtonClicked());

    }
    private void updateUI(){
        goldLabel.setText(String.valueOf(gameEnvironment.getPlayerGold()));
        pointsLabel.setText(String.valueOf(gameEnvironment.getPlayerPoints()));
        difficultyLabel.setText(gameEnvironment.getRoundDifficulty());
        roundLabel.setText(String.format("%d of %d", gameEnvironment.getCurrentRound(), gameEnvironment.getNumberOfRounds()));
        updateTowerLabels();
    }
    private void updateTowerLabels(){
        List<Tower> towers = gameEnvironment.getTowerList();
        Label[] towerNameLabels = {tower1NameLabel, tower2NameLabel, tower3NameLabel, tower4NameLabel, tower5NameLabel};
        Label[] towerTypeLabels = {tower1TypeLabel, tower2TypeLabel, tower3TypeLabel, tower4TypeLabel, tower5TypeLabel};
        Label[] towerSpeedLabels = {tower1SpeedLabel, tower2SpeedLabel, tower3SpeedLabel, tower4SpeedLabel, tower5SpeedLabel};
        Label[] towerLevelLabels = {tower1LevelLabel, tower2LevelLabel, tower3LevelLabel, tower4LevelLabel, tower5LevelLabel};

        for (int i = 0 ; i < towerNameLabels.length; i++){
            if (i < towers.size()){
                towerNameLabels[i].setText(towers.get(i).getName());
                towerTypeLabels[i].setText(towers.get(i).getResourceType());
                towerSpeedLabels[i].setText(String.valueOf(towers.get(i).getReloadSpeed()));
                towerLevelLabels[i].setText(String.valueOf(towers.get(i).getLevel()));
            } else{
                towerNameLabels[i].setText("");
                towerTypeLabels[i].setText("");
                towerSpeedLabels[i].setText("");
                towerLevelLabels[i].setText("");
            }
        }
    }
    private void startGame(){

    }
    private void shopButtonClicked(){
        //gameEnvironment.launchShopScreen();
    }
}
