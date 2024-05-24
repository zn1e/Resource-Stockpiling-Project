package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import seng201.team0.GameEnvironment;
import javafx.scene.control.Button;
import seng201.team0.models.Item;
import javafx.scene.image.ImageView;
import seng201.team0.models.Tower;
import seng201.team0.services.InventoryService;
import seng201.team0.services.UIService;
import java.util.List;

/**
 * Controller class for the shop.
 * Manages the game environment, inventory items, towers, and user interface updates in the shop.
 */
public class ShopController {
/** The UI service for updating the user interface */
    private final UIService uiService;
    /** The game environment instance */
    private final GameEnvironment gameEnvironment; // the game environment instance

    /** The inventory service for managing the items and reserve towers. */
    private final InventoryService inventoryService;
    /**
     * The currently selected item. */
    private Item selectedItem;
    /** The currently selected Tower*/
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
    @FXML
    private Label towerDescriptionLabel;

    /**
     * Constructor for the ShopController class.
     * Initializes the service and game environment.
     * @param gameEnvironment The game environment instance.
     */
    public ShopController(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.uiService = new UIService(gameEnvironment);
        this.inventoryService = gameEnvironment.getInventoryService();
    }

    /**
     * Initializes the shop controller.
     * Sets up the UI and button actions.
     */
    public void initialize() {
        updateUI();
        updateInventoryButtons();
        updateButtonStyles();
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
        towerDescriptionLabel.setText("");
    }

    /**
     * Updates the user interface elements.
     */
    private void updateUI() {
        uiService.updateGoldLabel(goldLabel);
        updateInventoryButtons();
    }

    /**
     * Updates the styles of the buttons based on the selected item or tower.
     *
     * @param buttons The buttons to update the styles for.
     */
    private void updateButtonStyles(Button... buttons) {
        for (Button button : buttons) {
            if ((selectedTower != null && button == getTowerButton(selectedTower))
                    || (selectedItem != null && button == getItemButton(selectedItem))
           ) {
                button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
            } else {
                button.setStyle("");
            }
        }
    }

    /**
     * Gets the button associated with a given tower.
     *
     * @param tower The tower to get the button for.
     * @return The button associated with the tower.
     */
    private Button getTowerButton(Tower tower) {
        int index = gameEnvironment.getDefaultTowers().indexOf(tower);
        return List.of(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button).get(index);
    }

    /**
     * Gets the button associated with a given item.
     *
     * @param item The item to get the button for.
     * @return The button associated with the item.
     */
    private Button getItemButton(Item item) {
        int index = gameEnvironment.getDefaultItems().indexOf(item);
        return List.of(item1Button, item2Button, item3Button).get(index);
    }

    /**
     * Handles the click event for a tower button.
     * Updates the selected tower and the UI elements accordingly.
     * @param index The index of the selected tower.
     */
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
            towerDescriptionLabel.setText(clickedTower.getTowerDescription());
            descriptionLabel.setText("Price: "+ clickedTower.getCost());
            updateButtonStyles(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);
            updateButtonStyles(item1Button, item2Button, item3Button);
            buyButton.setDisable(false);
            sellButton.setDisable(true);
        }
    }

    /**
     * Handles the click event for an item button.
     *
     * @param index The index of the selected item.
     */
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
            shopImage.setImage(clickedItem.getImage());
            towerDescriptionLabel.setText(clickedItem.getDescription());
            descriptionLabel.setText("Price: "+ clickedItem.getItemCost());
            updateButtonStyles(item1Button, item2Button, item3Button);
            updateButtonStyles(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button);
            buyButton.setDisable(false);
            sellButton.setDisable(true);
        }
    }

    /**
     * Buys the selected object (item or tower) and updates the UI.
     * Checks if the player has enough gold and if there is enough space in the inventory.
     */
    private void buySelectedObject() {
        if (selectedTower != null) {
            int price = selectedTower.getCost();
            if (!inventoryService.isReserveTowerInvNotFull()){
                shopText.setText("Tower Inventory Full!");
            }
            else if (price <= gameEnvironment.getPlayerGold()) {
                inventoryService.addReserveTower(selectedTower);
                gameEnvironment.setPlayerGold(gameEnvironment.getPlayerGold() - price);
                shopText.setText("Purchase Successful!");
                updateUI();
            }

            else if (price > gameEnvironment.getPlayerGold()){
                shopText.setText("NOT ENOUGH MONEY LOL!");
            }


        } else if (selectedItem != null) {
            int price = selectedItem.getItemCost();
            if (price <= gameEnvironment.getPlayerGold() && inventoryService.isItemInvNotFull()) {
                inventoryService.addItem(selectedItem);
                gameEnvironment.setPlayerGold(gameEnvironment.getPlayerGold() - price);
                shopText.setText("Purchase Successful!");
                updateUI();
            }
            else if (price > gameEnvironment.getPlayerGold()){
                shopText.setText("NOT ENOUGH MONEY LOL!");
            }
            else if (!inventoryService.isItemInvNotFull()){
                shopText.setText("Item Inventory Full!");

            }

        }
    }

    /**
     * Updates the inventory buttons to reflect the current state of the inventory.
     */
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
                    buyButton.setDisable(true);
                    sellButton.setDisable(false);
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
                    buyButton.setDisable(true);
                    sellButton.setDisable(false);
                });
            } else {
                itemInvButtons.get(i).setText("Empty");
                itemInvButtons.get(i).setOnAction(null);
                descriptionLabel.setText("");
            }
        }
    }
    /**
     * Sell the selected object (item or tower) and updates the UI.
     * Adds the sell price to the players gold and removes the object from their inventory respectfully.
     */
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

    /**
     * Handles the click event for the back button.
     * Closes the inventory screen and goes back to the main screen.
     */
    private void backButtonClicked() {
        gameEnvironment.closeSetupScreen();
    }
}
