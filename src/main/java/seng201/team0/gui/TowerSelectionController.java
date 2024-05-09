package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import seng201.team0.GameEnvironment;

import java.util.ArrayList;
import java.util.List;

public class TowerSelectionController {

    private GameEnvironment gameEnvironment; // the game environment instance

    @FXML
    private Button nextButton; // button for proceeding to next screen
    @FXML
    private ToggleButton towerOneToggleButton;
    @FXML
    private ToggleButton towerTwoToggleButton;
    @FXML
    private ToggleButton towerThreeToggleButton;
    @FXML
    private ToggleButton towerFourToggleButton;
    @FXML
    private ToggleButton towerFiveToggleButton;

    private int toggledCount = 0;
    private List<ToggleButton> selectedTowers = new ArrayList<>();


    /**
     * Constructor for the TowerSelectionController with the specified game environment instance.
     * @param gameEnvironment The game environment instance to associate with this controller.
     */
    public TowerSelectionController(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Initializes the controller by setting up UI components and event handlers.
     * This method is automatically called by JavaFX after the fxml file is loaded and instantiated.
     */
    public void initialize(){
        towerOneToggleButton.setOnAction(event -> updateSelectedTowerList(towerOneToggleButton));
        towerTwoToggleButton.setOnAction(event -> updateSelectedTowerList(towerTwoToggleButton));
        towerThreeToggleButton.setOnAction(event -> updateSelectedTowerList(towerThreeToggleButton));
        towerFourToggleButton.setOnAction(event -> updateSelectedTowerList(towerFourToggleButton));
        towerFiveToggleButton.setOnAction(event -> updateSelectedTowerList(towerFiveToggleButton));
        nextButton.setDisable(true); // Set the next button as disabled
    }


    private void updateSelectedTowerList(ToggleButton towerToggleButton) {
        if (towerToggleButton.isSelected()) {
            selectedTowers.add(towerToggleButton);
        } else {
            selectedTowers.remove(towerToggleButton);
        }
        boolean threeTowersSelected = selectedTowers.size() >= 3;
        nextButton.setDisable(!threeTowersSelected);
    }


    private void nextButtonClicked(){
            //gameEnvironment.setTowerList(); make towers first
            //gameEnvironment.launchMaingame();
            }
        }




