package seng201.team0.gui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
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
    private Tower selectedResTower;
    private Tower selectedMainTower;

    @FXML
    private ToggleButton main1Button, main2Button, main3Button, main4Button, main5Button;
    @FXML
    private ToggleButton res1Button, res2Button, res3Button, res4Button, res5Button;
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
        towerText.setText("");
        uiService.updateGoldLabel(goldLabel);
        updateInventoryButtons();
        //backButton.setOnAction(event -> backButtonClicked());
        swapButton.setOnAction(event -> swapButtonClicked());
        initializeButtonActions();
        //useButton.setOnAction(event -> useButtonClicked());
    }

    private void updateInventoryButtons() {
        List<ToggleButton> resTowerButtons = List.of(res1Button, res2Button, res3Button, res4Button, res5Button);
        List<Button> itemInvButtons = List.of(item1Button, item2Button, item3Button, item4Button, item5Button);
        List<ToggleButton> mainTowerButtons = List.of(main1Button, main2Button, main3Button, main4Button, main5Button);

        for (int i = 0; i < resTowerButtons.size(); i++) {
            Tower tower = inventoryService.getReserveTower(i);
            if (tower != null) {
                resTowerButtons.get(i).setText(tower.getName());
                resTowerButtons.get(i).setOnAction(event -> {
                    selectedResTower = tower;
                    selectedItem = null;

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
                    selectedResTower = null;
                });
            } else {
                itemInvButtons.get(i).setText("Empty");
                itemInvButtons.get(i).setOnAction(null);

            }

        }
        for (int i = 0; i < mainTowerButtons.size(); i++) {
            Tower tower = towerService.getMainTower(i);
            if (tower != null) {
                mainTowerButtons.get(i).setText(tower.getName());
                mainTowerButtons.get(i).setOnAction(event -> {
                    selectedResTower = tower;
                    selectedItem = null;

                });
            } else {
                mainTowerButtons.get(i).setText("Empty");
                mainTowerButtons.get(i).setOnAction(null);
            }
        }
    }
    private void initializeButtonActions(){
        main1Button.setOnAction(event -> handleMainTowerButtonClick(0));
        main2Button.setOnAction(event -> handleMainTowerButtonClick(1));
        main3Button.setOnAction(event -> handleMainTowerButtonClick(2));
        main4Button.setOnAction(event -> handleMainTowerButtonClick(3));
        main5Button.setOnAction(event -> handleMainTowerButtonClick(4));

        res1Button.setOnAction(event -> handleResTowerButtonClick(0));
        res2Button.setOnAction(event -> handleResTowerButtonClick(1));
        res3Button.setOnAction(event -> handleResTowerButtonClick(2));
        res4Button.setOnAction(event -> handleResTowerButtonClick(3));
        res5Button.setOnAction(event -> handleResTowerButtonClick(4));
    }
    private void handleMainTowerButtonClick(int index) {
        if (main1Button.isSelected() || main2Button.isSelected() || main3Button.isSelected() || main4Button.isSelected() || main5Button.isSelected()) {
            selectedMainTower = towerService.getMainTower(index);
            clearResSelections();
        } else {
            selectedMainTower = null;
        }
    }
    private void handleResTowerButtonClick(int index) {
        if (res1Button.isSelected() || res2Button.isSelected() || res3Button.isSelected() || res4Button.isSelected() || res5Button.isSelected()) {
            selectedResTower = inventoryService.getReserveTower(index);
            clearMainSelections();
        } else {
            selectedResTower = null;
        }
    }


    private void clearMainSelections() {
        main1Button.setSelected(false);
        main2Button.setSelected(false);
        main3Button.setSelected(false);
        main4Button.setSelected(false);
        main5Button.setSelected(false);
        selectedMainTower = null;
    }

    private void clearResSelections() {
        res1Button.setSelected(false);
        res2Button.setSelected(false);
        res3Button.setSelected(false);
        res4Button.setSelected(false);
        res5Button.setSelected(false);
        selectedResTower = null;
    }

    private void swapButtonClicked() {
        if (selectedMainTower != null) {
            towerService.moveMainTower(selectedMainTower);
        } else if (selectedResTower != null) {
            towerService.moveReserveToMain(selectedResTower);
        }
        updateInventoryButtons();
        clearMainSelections();
        clearResSelections();
    }
}






