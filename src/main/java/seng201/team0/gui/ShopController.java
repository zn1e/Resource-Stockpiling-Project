package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Inventory;
import javafx.scene.control.Button;
import seng201.team0.models.Item;
import javafx.scene.image.ImageView;
import seng201.team0.models.Tower;
import seng201.team0.services.UIService;


import java.util.List;

public class ShopController {

    private final UIService uiService;
    private GameEnvironment gameEnvironment; // the game environment instance
    private Inventory itemInventory;
    private Inventory towerInventory;
    private Item selectedItem;
    private Tower selectedTower;

    @FXML
    private Button backButton;
    @FXML
    private Button buyButton;
    @FXML
    private Button tower1Button, tower2Button, tower3Button, tower4Button, tower5Button;
    @FXML
    private Button item1Button, item2Button, item3Button;
    @FXML
    Label goldLabel;
    @FXML
    private ImageView shopImage;

    public ShopController(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.uiService = new UIService(gameEnvironment);
    }

    public void initialize() {
        updateUI();
        List<Button> towerButtons = List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);
        List<Button> itemButtons = List.of(item1Button, item2Button, item3Button);

        for (int i = 0; i < towerButtons.size(); i++) {
            int finalI = i;
            towerButtons.get(i).setOnAction(event -> {
                handleTowerButtonClick(finalI);
            });
        }
        for (int i = 0; i < itemButtons.size(); i++) {
            int finalI = i;
            itemButtons.get(i).setOnAction(event -> {
                handleItemButtonClick(finalI);
            });
        }
        backButton.setOnAction(event -> backButtonClicked());
        buyButton.setOnAction(event -> buySelectedObject());
    }

    private void updateUI(){
        goldLabel.setText(String.valueOf(gameEnvironment.getPlayerGold()));
    }
    private void updateButtonStyles(Button... buttons) {
        for (Button button : buttons) {
            if ((selectedTower != null && button == getTowerButton(selectedTower))
                    || (selectedItem != null && button == getItemButton(selectedItem))) {
                button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
            } else {
                button.setStyle("");
            }
        }
    }

    private Button getTowerButton(Tower tower) {
        int index = gameEnvironment.getDefaultTowers().indexOf(tower);
        return List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button).get(index);
    }

    private Button getItemButton(Item item) {
        int index = gameEnvironment.getDefaultItems().indexOf(item);
        return List.of(item1Button, item2Button, item3Button).get(index);
    }

    private void handleTowerButtonClick(int index) {
        Tower clickedTower = gameEnvironment.getDefaultTowers().get(index);
        if (selectedTower == clickedTower) {
            selectedTower = null;
            shopImage.setImage(null);
            updateButtonStyles(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);
        } else {
            selectedTower = clickedTower;
            selectedItem = null;
            shopImage.setImage(clickedTower.getImage());
            updateButtonStyles(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);
            updateButtonStyles(item1Button, item2Button, item3Button);
        }
    }
    private void handleItemButtonClick(int index) {
        Item clickedItem = gameEnvironment.getDefaultItems().get(index);
        if (selectedItem == clickedItem) {
            selectedItem = null;
            shopImage.setImage(null);
            updateButtonStyles(item1Button, item2Button, item3Button);
        } else {
            selectedItem = clickedItem;
            selectedTower = null;
            shopImage.setImage(selectedItem.getImage());
            updateButtonStyles(item1Button, item2Button, item3Button);
            updateButtonStyles(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);
        }
    }
    private void backButtonClicked(){
        gameEnvironment.closeSetupScreen();
    }
    private void buySelectedObject(){
         //checkplayer gold see if they have enough//
    }
    }
