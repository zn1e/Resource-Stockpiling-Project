package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Inventory;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Tower;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
public class ShopController {

    private GameEnvironment gameEnvironment; // the game environment instance
    private Inventory itemInventory;
    private Inventory towerInventory;
    private Object selectedObject;

    @FXML
    private Button backButton;
    @FXML
    private Button tower1Button, tower2Button, tower3Button, tower4Button, tower5Button;
    @FXML
    private Button item1Button, item2Button, item3Button;
    @FXML
    private Label goldLabel;

    public ShopController(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    public void initialise() {
        List<Button> towerButtons = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);
        List<Button> itemButtons = List.of(item1Button, item2Button, item3Button);

        for (int i = 0; i < towerButtons.size(); i++) {
            int finalI = i;
            towerButtons.get(i).setOnAction(event -> {
                selectedObject = gameEnvironment.getDefaultTowers().get(finalI);
                //updateSelectedObjectImage();
                updateButtonStyles(towerButtons, finalI);
            });
        }
        for (int i = 0; i < itemButtons.size(); i++) {
            int finalI = i;
            itemButtons.get(i).setOnAction(event -> {
                selectedObject = gameEnvironment.getDefaultItems().get(finalI);
                //updateSelectedObjectImage();
                updateButtonStyles(itemButtons, finalI);
            });
        }
        backButton.setOnAction(event -> backButtonClicked());
        updateUI();

    }
    private void updateUI(){
        goldLabel.setText(String.valueOf(gameEnvironment.getPlayerGold()));
    }
    private void updateButtonStyles(List<Button> buttons, int selectedIndex) {
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if (i == selectedIndex) {
                button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
            } else {
                button.setStyle("");
            }
        }
    }

    private void backButtonClicked(){
        gameEnvironment.launchMainScreen();
    }
    }
