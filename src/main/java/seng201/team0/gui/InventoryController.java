package seng201.team0.gui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Item;
import seng201.team0.models.Tower;
import seng201.team0.services.InventoryService;
import seng201.team0.services.TowerService;
import seng201.team0.services.UIService;

import java.util.List;

/**
 * Controller class for the inventory screen.
 * Manages the game environment, inventory items, towers, and user interface updates.
 */

public class InventoryController {
    /** The UI service for updating the user interface.*/
    private UIService uiService;
    /** The game environment instance. */
    private GameEnvironment gameEnvironment; // the game environment instance
    /** The inventory service for managing items and reserve towers. */
    private InventoryService inventoryService;
    /** The tower service for managing the main towers*/
    private TowerService towerService;
    /** The currently selected items. */
    private Item selectedItem;
    /** The currently selected reserve tower */
    private Tower selectedResTower;
    /** The currently selected main tower */
    private Tower selectedMainTower;

    @FXML
    private Button main1Button, main2Button, main3Button, main4Button, main5Button;
    @FXML
    private Button res1Button, res2Button, res3Button, res4Button, res5Button;
    @FXML
    private Button item1Button, item2Button, item3Button, item4Button, item5Button;
    @FXML
    private Button useButton;
    @FXML
    private Label goldLabel;
    @FXML
    private Button backButton;
    @FXML
    private Text itemText;
    @FXML
    private Text towerText;
    @FXML
    private Text backText;

    /**
     * Constructor for the InventoryController class.
     * Initializes the services and game environment.
     * @param gameEnvironment The game environment instance.
     */
    public InventoryController(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.uiService = new UIService(gameEnvironment);
        this.inventoryService = gameEnvironment.getInventoryService();
        this.towerService = new TowerService(gameEnvironment);
    }

    /**
     * Initializes the inventory controller.
     * Sets up the UI and button actions.
     */
    public void initialize() {
        towerText.setText("");
        uiService.updateGoldLabel(goldLabel);
        updateInventoryButtons();
        backButton.setOnAction(event -> backButtonClicked());
        useButton.setOnAction(event -> useButtonClicked());
        backText.setText("");
        System.out.println(towerService.getMainTowers());
    }

    /**
     * Handles the click event for a main tower button.
     * Swaps towers if a reserve tower is selected, otherwise sets the selected main tower.
     *
     * @param selectedTower The selected main tower.
     */
    private void handleMainTowerButtonClick(Tower selectedTower) {
        if (selectedResTower != null) {
            towerText.setText("Swapped "+ selectedResTower + " and " +selectedTower);
            inventoryService.swapTower(selectedTower, selectedResTower);
            selectedResTower = null;
            selectedMainTower = null;
            updateInventoryButtons();
        } else {
            selectedMainTower = selectedTower;
            updateInventoryButtons();
        }
    }

    /**
     * Handles the click event for a reserve tower button.
     * Swaps towers if a main tower is selected, otherwise sets the selected reserve tower.
     * @param selectedTower The selected reserve tower.
     */
    private void handleResTowerButtonClick(Tower selectedTower) {
        if (selectedMainTower != null) {
            towerText.setText("Swapped "+ selectedMainTower + " and " +selectedTower);
            inventoryService.swapTower(selectedMainTower, selectedTower);
            selectedMainTower = null;
            selectedResTower = null;
            updateInventoryButtons();
        } else {
            selectedResTower = selectedTower;
            updateInventoryButtons();
        }

    }

    /**
     * Updates the inventory buttons to reflect the current state of the inventory.
     */
    private void updateInventoryButtons() {
        List<Button> resTowerButtons = List.of(res1Button, res2Button, res3Button, res4Button, res5Button);
        List<Button> itemInvButtons = List.of(item1Button, item2Button, item3Button, item4Button, item5Button);
        List<Button> mainTowerButtons = List.of(main1Button, main2Button, main3Button, main4Button, main5Button);

        for (int i = 0; i < resTowerButtons.size(); i++) {
            Tower tower = inventoryService.getReserveTower(i);
            if (tower != null) {
                resTowerButtons.get(i).setText(tower.getName());
                resTowerButtons.get(i).setOnAction(event -> handleResTowerButtonClick(tower));
            } else {
                resTowerButtons.get(i).setText("Empty");
                resTowerButtons.get(i).setOnAction(event -> handleResTowerButtonClick(null));
            }
        }

        for (int i = 0; i < itemInvButtons.size(); i++) {
            Item item = inventoryService.getItem(i);
            if (item != null) {
                itemInvButtons.get(i).setText(item.getName());
                itemInvButtons.get(i).setOnAction(event -> {
                    selectedItem = item;
                    selectedResTower = null;
                    selectedMainTower = null;
                    itemText.setText(item +" selected");
                });
            } else {
                itemInvButtons.get(i).setText("Empty");
                itemInvButtons.get(i).setOnAction(null);
                itemText.setText("");

            }

        }
        for (int i = 0; i < mainTowerButtons.size(); i++) {
            Tower tower = towerService.getMainTower(i);
            if (tower != null) {
                mainTowerButtons.get(i).setText(tower.getName());
                mainTowerButtons.get(i).setOnAction(event -> handleMainTowerButtonClick(tower));
            } else {
                mainTowerButtons.get(i).setText("Empty");
                mainTowerButtons.get(i).setOnAction(event -> handleMainTowerButtonClick(null));
            }
        }
    }

    /**
     * Handles the click event for the use button.
     * Uses the selected item on the selected tower and updates the inventory buttons.
     */
    private void useButtonClicked() {
        if (selectedItem != null) {
            inventoryService.useItem(selectedItem, selectedMainTower);
            itemText.setText(selectedItem + " Used!");
            updateInventoryButtons();
        } else {
            System.out.println("No item selected.");
        }
        uiService.updateGoldLabel(goldLabel);
    }

    /**
     * Checks if the main tower list is empty.
     * @return true if the main tower list is empty, false otherwise.
     */

    private boolean isMainTowerListEmpty(){
        if (towerService.getMainTowers().size() >= 1){
            return false;
        }
        else{
            return true;
        }

    }

    /**
     * Handles the click event for the back button.
     * Closes the setup screen if there is at least one main tower, otherwise displays an alert.
     */
    private void backButtonClicked() {
        if (!isMainTowerListEmpty()){
            gameEnvironment.closeSetupScreen();
        }
        else{
            backText.setText("At least ONE MAIN TOWER!");

        }

    }
}







