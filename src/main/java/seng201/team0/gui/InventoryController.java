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


public class InventoryController {
    private UIService uiService;
    private GameEnvironment gameEnvironment; // the game environment instance
    private InventoryService inventoryService;
    private TowerService towerService;
    private Item selectedItem;
    private Tower selectedresTower;
    private Tower selectedmainTower;

    @FXML
    private Button main1Button, main2Button, main3Button, main4Button, main5Button;
    @FXML
    private Button res1Button, res2Button, res3Button, res4Button, res5Button;
    @FXML
    private Button item1Button, item2Button, item3Button, item4Button, item5Button;
    @FXML
    private Button useButton;
    @FXML
    private Button swapButton;
    @FXML
    private Label goldLabel;
    @FXML
    private Button backButton;
    @FXML
    private Text itemText;
    @FXML
    private Text towerText;


    public InventoryController(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.uiService = new UIService(gameEnvironment);
        this.inventoryService = gameEnvironment.getInventoryService();
        this.towerService = new TowerService(gameEnvironment);

    }

    public void initialize() {

        updateInventoryButtons();
    }

    private Button getTowerButton(Tower tower) {
        int index = gameEnvironment.getDefaultTowers().indexOf(tower);
        return List.of(res1Button, res2Button, res3Button, res4Button, res5Button).get(index);
    }
    private Button getItemButton(Item item) {
        int index = gameEnvironment.getDefaultItems().indexOf(item);
        return List.of(item1Button, item2Button, item3Button).get(index);
    }

    private void updateInventoryButtons() {
        List<Button> resTowerButtons = List.of(res1Button, res2Button, res3Button, res4Button, res5Button);
        List<Button> itemInvButtons = List.of(item1Button, item2Button, item3Button, item4Button, item5Button);

        for (int i = 0; i < resTowerButtons.size(); i++) {
            Tower tower = inventoryService.getReserveTower(i);
            if (tower != null) {
                resTowerButtons.get(i).setText(tower.getName());
                resTowerButtons.get(i).setOnAction(event -> {
                    selectedresTower = tower;
                    selectedItem = null;
                    updateButtonStyles(resTowerButtons.toArray(new Button[0]));
                    updateButtonStyles(itemInvButtons.toArray(new Button[0]));
                });
            } else {
                resTowerButtons.get(i).setText("Empty");
                resTowerButtons.get(i).setOnAction(null);
            }
        }

        for (int i = 0; i < itemInvButtons.size(); i++) {
            Item item = inventoryService.getItem(i);
            if (item != null) {
                itemInvButtons.get(i).setText(item.getName());
                itemInvButtons.get(i).setOnAction(event -> {
                    selectedItem = item;
                    selectedresTower = null;
                    updateButtonStyles(itemInvButtons.toArray(new Button[0]));
                    updateButtonStyles(resTowerButtons.toArray(new Button[0]));
                });
            } else {
                itemInvButtons.get(i).setText("Empty");
                itemInvButtons.get(i).setOnAction(null);

            }
        }
    }
    private void updateButtonStyles(Button... buttons) {
        for (Button button : buttons) {
            if ((selectedresTower != null && button == getTowerButton(selectedresTower))
                    || (selectedItem != null && button == getItemButton(selectedItem))) {
                button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
            } else {
                button.setStyle("");
            }
        }
    }

}


