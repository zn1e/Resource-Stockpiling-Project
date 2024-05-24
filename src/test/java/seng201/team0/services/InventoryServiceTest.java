package seng201.team0.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Item;
import seng201.team0.models.Tower;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class InventoryServiceTest {

    private InventoryService inventoryService;
    private GameEnvironment gameEnvironment;

    @BeforeEach
    void setUp() {
        gameEnvironment = new GameEnvironment(
                gameEnv -> {},
                gameEnv -> {},
                gameEnv -> {},
                gameEnv -> {},
                gameEnv -> {},
                gameEnv -> {},
                gameEnv -> {},
                () -> {}
        );
        gameEnvironment.setTowerList(new ArrayList<>());
        inventoryService = new InventoryService(gameEnvironment);
    }

    @Test
    void addReserveTower() {
        Tower tower = new Tower("The Awesomest", "awesome resource", gameEnvironment.loadImage("images/image.png"),
                "A powerful tower");
        inventoryService.addReserveTower(tower);
        assertEquals(1, inventoryService.getTowerInventory().size());
        assertTrue(inventoryService.getTowerInventory().contains(tower));
    }

    @Test
    void addItem() {
        Item item = new Item("CatBag", 10,gameEnvironment.loadImage("images/image.png"), "A bag filled with cats");
        inventoryService.addItem(item);
        assertEquals(1, inventoryService.getItemInventory().size());
        assertTrue(inventoryService.getItemInventory().contains(item));
    }

    @Test
    void isItemInvNotFull() {
        assertTrue(inventoryService.isItemInvNotFull());
        for (int i = 0; i < 5; i++) {
            inventoryService.addItem(new Item("NOTFILLED! " + i, 10,gameEnvironment.loadImage("images/image.png"), "NOT FILLED!"));
        }
        assertFalse(inventoryService.isItemInvNotFull());
    }

    @Test
    void isReserveTowerInvNotFull() {
        assertTrue(inventoryService.isReserveTowerInvNotFull());
        for (int i = 0; i < 5; i++) {
            inventoryService.addReserveTower(new Tower("OneTower " + i, "Lonely",gameEnvironment.loadImage("images/image.png"), "one is the loneliest number"));
        }
        assertFalse(inventoryService.isReserveTowerInvNotFull());
    }

    @Test
    void removeReserveTower() {
        Tower tower = new Tower("Useless Tower", "Trash", gameEnvironment.loadImage("images/image.png"), "A trash tower");
        inventoryService.addReserveTower(tower);
        inventoryService.removeReserveTower(tower);
        assertEquals(0, inventoryService.getTowerInventory().size());
        assertFalse(inventoryService.getTowerInventory().contains(tower));
    }

    @Test
    void removeItem() {
        Item item = new Item("Useless Item", 10, gameEnvironment.loadImage("images/image.png"), "A useless item that must be removed");
        inventoryService.addItem(item);
        inventoryService.removeItem(item);
        assertEquals(0, inventoryService.getItemInventory().size());
        assertFalse(inventoryService.getItemInventory().contains(item));
    }

    @Test
    void swapTower() {
        Tower mainTower = new Tower("Cat Tower", "Cats", gameEnvironment.loadImage("images/image.png"), "Cat main Tower");
        Tower reserveTower = new Tower("Dog Tower", "Dog", gameEnvironment.loadImage("images/image.png"),"Dog reserve tower");

        inventoryService.addReserveTower(reserveTower);
        gameEnvironment.getTowerList().add(mainTower);

        inventoryService.swapTower(mainTower, reserveTower);

        assertFalse(gameEnvironment.getTowerList().contains(mainTower));
        assertTrue(gameEnvironment.getTowerList().contains(reserveTower));
        assertTrue(inventoryService.getTowerInventory().contains(mainTower));
        assertFalse(inventoryService.getTowerInventory().contains(reserveTower));
    }

    @Test
    void useItem() {
        Tower tower = new Tower("Tower to test", "testy", gameEnvironment.loadImage("images/image.png"), "testy tower");
        Item upgradeItem = new Item("Upgrade Item", 10, gameEnvironment.loadImage("images/image.png"), "Upgrades tower");
        Item goldItem = new Item("Gold Item", 10, gameEnvironment.loadImage("images/image.png"), "Upgrades Gold gain!");
        Item pointsItem = new Item("Points Item", 10, gameEnvironment.loadImage("images/image.png"), "Upgrades Points gain!");

        // Test Upgrade Item
        inventoryService.addItem(upgradeItem);
        inventoryService.useItem(upgradeItem, tower);
        assertEquals(2, tower.getLevel());
        assertFalse(inventoryService.getItemInventory().contains(upgradeItem));

        // Test Gold Item
        inventoryService.addItem(goldItem);
        inventoryService.useItem(goldItem, null);
        assertTrue(inventoryService.isGoldItemUsed());
        assertFalse(inventoryService.getItemInventory().contains(goldItem));

        // Test Points Item
        inventoryService.addItem(pointsItem);
        inventoryService.useItem(pointsItem, null);
        assertTrue(inventoryService.isPointsItemUsed());
        assertFalse(inventoryService.getItemInventory().contains(pointsItem));
    }

    @Test
    void isGoldItemUsed() {
        Item goldItem = new Item("Gold Item", 10, gameEnvironment.loadImage("images/image.png"), "Upgrades gold gain!");
        inventoryService.addItem(goldItem);
        inventoryService.useItem(goldItem, null);
        assertTrue(inventoryService.isGoldItemUsed());
    }

    @Test
    void isPointsItemUsed() {
        Item pointsItem = new Item("Points Item", 10, gameEnvironment.loadImage("images/image.png"), "Upgrades points gain!");
        inventoryService.addItem(pointsItem);
        inventoryService.useItem(pointsItem, null);
        assertTrue(inventoryService.isPointsItemUsed());
    }
}
