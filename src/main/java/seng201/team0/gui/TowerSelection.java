package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Tower;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TowerSelection {
    /**
     * The game environment instance.
     */
    private GameEnvironment gameEnvironment;
    /**
     * Selected tower arrays containing three towers.
     */
    private final Tower[] selectedTowers = new Tower[3];
    /**
     * Initializes the selected tower index.
     */
    private int selectedTowerIndex = -1;
    /**
     * Flag for checking the number of selected towers.
     */
    private boolean selectedTowersFlag;
    /**
     * Button for proceeding to next screen.
     */
    @FXML
    private Button startButton;
    /**
     * Tower buttons.
     */
    @FXML
    private Button tower1Button, tower2Button, tower3Button, tower4Button, tower5Button;
    /**
     * Selected tower buttons.
     */
    @FXML
    private Button selectedTower1Button, selectedTower2Button, selectedTower3Button;


    /**
     * Constructor for the TowerSelection with the specified game environment instance.
     *
     * @param gameEnvironment The game environment instance to associate with this class.
     */
    public TowerSelection(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Initializes the controller by setting up UI components and event handlers.
     * This method is automatically called by JavaFX after the fxml file is loaded and instantiated.
     * The code is similar from tutorial 2.
     */
    public void initialize() {
        List<Button> towerButtons = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);
        List<Button> selectedTowerButtons = List.of(selectedTower1Button, selectedTower2Button, selectedTower3Button);
        for (int i = 0; i < towerButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            towerButtons.get(i).setOnAction(event -> {
                selectedTowerIndex = finalI;
                towerButtons.forEach(button -> {
                    if (button == towerButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }
        for (int i = 0; i < selectedTowerButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            selectedTowerButtons.get(i).setOnAction(event -> {
                if (selectedTowerIndex != -1) {
                    selectedTowerButtons.get(finalI).setText(gameEnvironment.getDefaultTowers().get(selectedTowerIndex).getName());
                    selectedTowers[finalI] = gameEnvironment.getDefaultTowers().get(selectedTowerIndex);
                }
            });
        }
        startButton.setOnAction(event -> startButtonClicked());
    }

    /**
     * Method for checking if the number of selected towers is equal to 3.
     */
    private void confirmSelectedTowers(){
        if (selectedTowers.length == 3){
            selectedTowersFlag = true;
        }else{
            selectedTowersFlag = false;
        }
    }

    /**
     * Method for handling the event when start button is clicked.
     */
    @FXML
    private void startButtonClicked() {
        confirmSelectedTowers();
        if (selectedTowersFlag){
            gameEnvironment.setTowerList(Arrays.stream(selectedTowers).filter((Objects::nonNull)).toList());
            gameEnvironment.closeSetupScreen();
        }
    }
}




