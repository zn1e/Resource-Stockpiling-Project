package seng201.team0.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;
import seng201.team0.services.RoundService;
import seng201.team0.services.TowerService;
import seng201.team0.services.TrackService;
import seng201.team0.services.UIService;

import java.util.List;

/**
 * Controller class for the game arena.
 * Manages the game environment, tower setup, track setup, and user interface upates.
 */

public class GameArena {
    /** The game environment instance. */
    private GameEnvironment gameEnvironment;
    /** The tower service for managing towers. */
    private TowerService towerService;
    /** The UI service for updating the user interface. */
    private UIService uiService;
    /** The track service for managing the track. */
    private TrackService trackService;
    /** The round service for manaing the game rounds. */
    private RoundService roundService;
    /** The timeline for animating the game. */
    private Timeline timeline;
    /**
     * The track where the carts and tower are set.
     */
    @FXML
    GridPane trackGrid;
    /**
     * Labels for gold, points, difficulty, and rounds.
     */
    @FXML
    Label goldLabel, pointsLabel, difficultyLabel, roundLabel;
    /**
     * Button for starting the round.
     */
    @FXML
    Button playButton;
    /**
     * Button for shop.
     */
    @FXML
    Button shopButton;
    /**
     * Button for inventory.
     */
    @FXML
    Button inventoryButton;
    /**
     * Progress bar based on the current round.
     */
    @FXML
    ProgressBar progressBar;
    /**
     * Labels for tower names.
     */
    @FXML
    Label tower1NameLabel, tower2NameLabel, tower3NameLabel, tower4NameLabel, tower5NameLabel;
    /**
     * Labels for tower types.
     */
    @FXML
    Label tower1TypeLabel, tower2TypeLabel, tower3TypeLabel, tower4TypeLabel, tower5TypeLabel;
    /**
     * Labels for resource amount of tower.
     */
    @FXML
    Label tower1AmountLabel, tower2AmountLabel, tower3AmountLabel, tower4AmountLabel, tower5AmountLabel;
    /**
     * Labels for tower speed.
     */
    @FXML
    Label tower1SpeedLabel, tower2SpeedLabel, tower3SpeedLabel, tower4SpeedLabel, tower5SpeedLabel;
    /**
     * Labels for tower levels.
     */
    @FXML
    Label tower1LevelLabel, tower2LevelLabel, tower3LevelLabel, tower4LevelLabel, tower5LevelLabel;
    /**
     * Labels for alerts.
     */
    @FXML
    Label alert1Label, alert2Label;

    /**
     * Constructor for the GameArena class.
     * Initializes the services and game environment
     * @param gameEnvironment The game environment instance.
     */

    public GameArena(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.towerService = new TowerService(gameEnvironment);
        this.uiService = new UIService(gameEnvironment);
        this.trackService = new TrackService(gameEnvironment);
        this.roundService = new RoundService(gameEnvironment);

        this.trackGrid = new GridPane();
    }

    /**
     * Initializes the game arena.
     * Sets up the UI, towers, track, and button actions.
     */
    public void initialize(){
        updateUI();
        setupTowers(trackGrid);
        setupTrack(trackGrid);
        playButton.setOnAction(event -> startGame());
        shopButton.setOnAction(event -> shopButtonClicked());
        inventoryButton.setOnAction(event ->inventoryButtonClicked());
    }

    /**
     * Updates the user interface elements
     */
    private void updateUI() {
        uiService.updateGoldLabel(goldLabel);
        uiService.updatePointsLabel(pointsLabel);
        uiService.updateDifficultyLabel(difficultyLabel);
        uiService.updateRoundLabel(roundLabel);
        updateTowerLabels();
        progressBar.setStyle("-fx-accent: green");
        increaseProgress();
    }

    /**
     * Updates the labels for the towers.
     */

    private void updateTowerLabels() {
        List<Tower> towers = gameEnvironment.getTowerList();
        Label[] towerNameLabels = {tower1NameLabel, tower2NameLabel, tower3NameLabel, tower4NameLabel, tower5NameLabel};
        Label[] towerTypeLabels = {tower1TypeLabel, tower2TypeLabel, tower3TypeLabel, tower4TypeLabel, tower5TypeLabel};
        Label[] towerAmountLabels = {tower1AmountLabel, tower2AmountLabel, tower3AmountLabel, tower4AmountLabel, tower5AmountLabel};
        Label[] towerSpeedLabels = {tower1SpeedLabel, tower2SpeedLabel, tower3SpeedLabel, tower4SpeedLabel, tower5SpeedLabel};
        Label[] towerLevelLabels = {tower1LevelLabel, tower2LevelLabel, tower3LevelLabel, tower4LevelLabel, tower5LevelLabel};

        uiService.updateTowerLabels(towers, towerNameLabels, towerTypeLabels, towerAmountLabels, towerSpeedLabels, towerLevelLabels);
    }

    /**
     * Sets up the main towers on the track grid.
     * @param trackGrid The track grid to set up the towers on.
     */
    private void setupTowers(GridPane trackGrid){
        towerService.setupMainTowers(trackGrid);
    }

    /**
     * Sets up the track image on the track grid.
     * @param trackGrid The track grid to set up the track image on.
     */
    private void setupTrack(GridPane trackGrid){
        trackService.setupTrackImage(trackGrid);
    }

    /**
     * Starts the game, initializing carts and disabling buttons.
     */

    private void startGame(){
        shopButton.setDisable(true);
        inventoryButton.setDisable(true);
        playButton.setDisable(true);
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

    /**
     * Stops the game, re-enabling buttons and performing after-round interactions.
     */
    private void stopGame() {
        if (timeline != null) {
            timeline.stop();
            afterRoundInteraction();
            shopButton.setDisable(false);
            inventoryButton.setDisable(false);
            playButton.setDisable(false);
        }
    }

    /**
     * Handles interactions after a round is completed.
     */
    private void afterRoundInteraction(){
        List<Cart> notFilledCarts = trackService.getNotFilledCarts();
        boolean allCartsFilled = notFilledCarts.isEmpty();
        Label[] alertLabels = {alert1Label, alert2Label};
        trackService.triggerRandomEvents(trackGrid, alertLabels);
        roundService.afterRound(allCartsFilled);
        increaseProgress();
    }

    /**
     * Launches the shop screen when the shop button is clicked.
     */

    private void shopButtonClicked(){
        gameEnvironment.launchShopScreen();
    }

    /**
     * Launches the inventory screen when the inventory button is clicked.
     */
    private void inventoryButtonClicked(){
        System.out.println("Inventory Screen");
        gameEnvironment.launchInventoryScreen();
    }

    /**
     * Increases the progress bar based on the current round.
     */
    private void increaseProgress(){
        int currentRound = gameEnvironment.getCurrentRound();
        int numberOfRounds = gameEnvironment.getNumberOfRounds();
        progressBar.setProgress((double) currentRound / numberOfRounds);
    }

}
