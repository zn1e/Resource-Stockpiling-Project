package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import seng201.team0.GameEnvironment;
//import seng201.team0.models.Inventory;
import javafx.scene.control.Button;
import seng201.team0.models.Item;
import javafx.scene.image.ImageView;
import seng201.team0.models.Tower;
import seng201.team0.services.InventoryService;
import seng201.team0.services.UIService;


import java.util.List;

public class ShopController {

    private final UIService uiService;
    private GameEnvironment gameEnvironment; // the game environment instance
    private InventoryService inventoryService;
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
    @FXML
    private Text descriptionLabel;
    @FXML
    private Text shopText;
    @FXML
    private Button res1Button, res2Button, res3Button, res4Button, res5Button;
    @FXML
    private Button item1InvButton, item2InvButton, item3InvButton, item4InvButton, item5InvButton;
    @FXML
    private Button sellButton;


    public ShopController(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.uiService = new UIService(gameEnvironment);
        this.inventoryService = gameEnvironment.getInventoryService();
    }

    public void initialize() {
        updateUI();
        updateInventoryButtons();
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
        sellButton.setOnAction(event -> sellSelectedObject());
        shopText.setText("");
        descriptionLabel.setText("");
    }

    private void updateUI() {
        uiService.updateGoldLabel(goldLabel);
        updateInventoryButtons();
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
            descriptionLabel.setText("");
            shopText.setText("");
            updateButtonStyles(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);
        } else {
            selectedTower = clickedTower;
            selectedItem = null;
            shopText.setText("");
            shopImage.setImage(clickedTower.getImage());
            descriptionLabel.setText("Price: "+ selectedTower.getCost());
            updateButtonStyles(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);
            updateButtonStyles(item1Button, item2Button, item3Button);
        }
    }

    private void handleItemButtonClick(int index) {
        Item clickedItem = gameEnvironment.getDefaultItems().get(index);
        if (selectedItem == clickedItem) {
            selectedItem = null;
            shopImage.setImage(null);
            descriptionLabel.setText("");
            shopText.setText("");
            updateButtonStyles(item1Button, item2Button, item3Button);
        } else {
            selectedItem = clickedItem;
            selectedTower = null;
            shopText.setText("");
            shopImage.setImage(selectedItem.getImage());
            descriptionLabel.setText("Price: "+ selectedItem.getItemCost());
            updateButtonStyles(item1Button, item2Button, item3Button);
            updateButtonStyles(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);
        }
    }

    private void buySelectedObject() {
        if (selectedTower != null) {
            int price = selectedTower.getCost();
            if (price <= gameEnvironment.getPlayerGold()) {
                inventoryService.addReserveTower(selectedTower);
                gameEnvironment.setPlayerGold(gameEnvironment.getPlayerGold() - price);
                shopText.setText("Purchase Sucessful!");
                updateUI();
            }
            else{
                shopText.setText("NOT ENOUGH MONEY LOL!");
            }
        } else if (selectedItem != null) {
            int price = selectedItem.getItemCost();
            if (price <= gameEnvironment.getPlayerGold()) {
                inventoryService.addItem(selectedItem);
                gameEnvironment.setPlayerGold(gameEnvironment.getPlayerGold() - price);
                shopText.setText("Purchase Sucessful!");
                updateUI();
            }
            else{
                shopText.setText("NOT ENOUGH MONEY LOL!");
            }

        }
    }
    private void updateInventoryButtons() {
        List<Button> resTowerButtons = List.of(res1Button, res2Button, res3Button, res4Button, res5Button);
        List<Button> itemInvButtons = List.of(item1InvButton, item2InvButton, item3InvButton, item4InvButton, item5InvButton);

        for (int i = 0; i < resTowerButtons.size(); i++) {
            Tower tower = inventoryService.getReserveTower(i);
            if (tower != null) {
                resTowerButtons.get(i).setText(tower.getName());
                resTowerButtons.get(i).setOnAction(event -> {
                    selectedTower = tower;
                    selectedItem = null;
                    updateButtonStyles(resTowerButtons.toArray(new Button[0]));
                    updateButtonStyles(itemInvButtons.toArray(new Button[0]));
                    descriptionLabel.setText("Sell Price: "+ selectedTower.getSellPrice());
                    shopImage.setImage(selectedTower.getImage());
                });
            } else {
                resTowerButtons.get(i).setText("Empty");
                resTowerButtons.get(i).setOnAction(null);
                descriptionLabel.setText("");
            }
        }

        for (int i = 0; i < itemInvButtons.size(); i++) {
            Item item = inventoryService.getItem(i);
            if (item != null) {
                itemInvButtons.get(i).setText(item.getName());
                itemInvButtons.get(i).setOnAction(event -> {
                    selectedItem = item;
                    selectedTower = null;
                    updateButtonStyles(itemInvButtons.toArray(new Button[0]));
                    updateButtonStyles(resTowerButtons.toArray(new Button[0]));
                    descriptionLabel.setText("Sell Price: "+ selectedItem.getSellPrice());
                    shopImage.setImage(selectedItem.getImage());
                });
            } else {
                itemInvButtons.get(i).setText("Empty");
                itemInvButtons.get(i).setOnAction(null);
                descriptionLabel.setText("");
            }
        }
    }
    private void sellSelectedObject() {
        if (selectedTower != null) {
            int sellPrice = selectedTower.getSellPrice();
            inventoryService.removeReserveTower(selectedTower);
            gameEnvironment.setPlayerGold(gameEnvironment.getPlayerGold() + sellPrice);
            selectedTower = null;
            updateInventoryButtons();
            updateUI();
            shopText.setText("Tower sold!");
        } else if (selectedItem != null) {
            int sellPrice = selectedItem.getSellPrice();
            inventoryService.removeItem(selectedItem);
            gameEnvironment.setPlayerGold(gameEnvironment.getPlayerGold() + sellPrice);
            selectedItem = null;
            updateInventoryButtons();
            updateUI();
            shopText.setText("Item sold!");
        }
    }
    private void backButtonClicked() {
        gameEnvironment.closeSetupScreen();
    }
}
