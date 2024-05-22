package seng201.team0.gui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Item;
import seng201.team0.models.Tower;
import seng201.team0.services.InventoryService;
import seng201.team0.services.UIService;

import java.util.List;


public class InventoryController {
    private UIService uiService;
    private GameEnvironment gameEnvironment; // the game environment instance
    private InventoryService inventoryService;
    private Item selectedItem;
    private Tower selectedTower;
    @FXML
    private Button backButton;
    @FXML
    private Button sellButton;
    @FXML
    private Button resTower1Button, resTower2Button, resTower3Button, resTower4Button, resTower5Button;
    @FXML
    private Button item1Button, item2Button, item3Button;
    @FXML
    private Label tower1Label, tower2Label, tower3Label, tower4Label, tower5Label;
    @FXML
    private Label item1Label, item2Label, item3Label;
    @FXML
    private Label goldLabel;
    @FXML
    private Text sellText;

    public InventoryController(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.uiService = new UIService(gameEnvironment);
        this.inventoryService = new InventoryService(gameEnvironment);
    }
    public void initialize() {
        updateUI();
        List<Button> resTowerButtons = List.of(resTower1Button, resTower2Button, resTower3Button, resTower4Button, resTower5Button);
        List<Button> itemButtons = List.of(item1Button, item2Button, item3Button);

        for (int i = 0; i < resTowerButtons.size(); i++) {
            int finalI = i;
            resTowerButtons.get(i).setOnAction(event -> {
                handleResTowerButtonClick(finalI);
            });
        }
        for (int i = 0; i < itemButtons.size(); i++) {
            int finalI = i;
            itemButtons.get(i).setOnAction(event -> {
                handleItemButtonClick(finalI);
            });
        }
        backButton.setOnAction(event -> backButtonClicked());
        sellButton.setOnAction(event -> sellSelectedObject());
        sellText.setText("");
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
        return List.of(resTower1Button, resTower2Button, resTower3Button, resTower4Button, resTower5Button).get(index);
    }

    private Button getItemButton(Item item) {
        int index = gameEnvironment.getDefaultItems().indexOf(item);
        return List.of(item1Button, item2Button, item3Button).get(index);
    }

    private void handleResTowerButtonClick(int index) {
        Tower clickedTower = gameEnvironment.getDefaultTowers().get(index);
        if (selectedTower == clickedTower) {
            selectedTower = null;
            sellText.setText("");
            updateButtonStyles(resTower1Button, resTower2Button, resTower3Button, resTower4Button, resTower5Button);
        } else {
            selectedTower = clickedTower;
            selectedItem = null;
            sellText.setText("Sell Price: "+ selectedTower.getSellPrice());
            updateButtonStyles(resTower1Button, resTower2Button, resTower3Button, resTower4Button, resTower5Button);
            updateButtonStyles(item1Button, item2Button, item3Button);
        }
    }

    private void handleItemButtonClick(int index) {
        Item clickedItem = gameEnvironment.getDefaultItems().get(index);
        if (selectedItem == clickedItem) {
            selectedItem = null;
            sellText.setText("");
            updateButtonStyles(item1Button, item2Button, item3Button);
        } else {
            selectedItem = clickedItem;
            selectedTower = null;
            sellText.setText("Sell Price: "+ selectedItem.getSellPrice());
            updateButtonStyles(item1Button, item2Button, item3Button);
            updateButtonStyles(resTower1Button, resTower2Button, resTower3Button, resTower4Button, resTower5Button);
        }
    }

    private void sellSelectedObject() {
        if (selectedTower != null) {
            inventoryService.removeReserveTower(selectedTower);
            gameEnvironment.setPlayerGold(gameEnvironment.getPlayerGold() + selectedTower.getSellPrice());
            selectedTower = null;
            updateButtonStyles();
            updateUI();
        } else if (selectedItem != null) {
            inventoryService.removeItem(selectedItem);
            gameEnvironment.setPlayerGold(gameEnvironment.getPlayerGold() + selectedItem.getSellPrice());
            selectedItem = null;
            updateButtonStyles();
            updateUI();
        }
    }
    private void backButtonClicked() {
        gameEnvironment.closeSetupScreen();
    }
    private void updateUI() {
        uiService.updateGoldLabel(goldLabel);
    }

}
