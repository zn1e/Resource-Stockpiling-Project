package seng201.team0.services;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Item;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;

/**
 * The InventoryService class manages the inventory of items and towers in the game.
 * It provides methods to add, remove, and retrieve items and towers from the inventory.
 */
public class InventoryService {
    private static final int ITEM_INVENTORY_SIZE = 5;
    private static final int TOWER_INVENTORY_SIZE = 5;

    private ArrayList<Item> itemInventory;
    private ArrayList<Tower> towerInventory;
    private List<Tower> mainTowerInventory;
    private static InventoryService instance;
    private Item item;
    private TowerService towerService;
    private GameEnvironment gameEnvironment;
    /**
     * Constructs an InventoryService object with the specified game environment.
     *
     * @param gameEnvironment the game environment
     */

    public InventoryService(GameEnvironment gameEnvironment){
        this.itemInventory = new ArrayList<>(ITEM_INVENTORY_SIZE);
        this.towerInventory = new ArrayList<>(TOWER_INVENTORY_SIZE);
        this.gameEnvironment = gameEnvironment;
        // List<Tower> mainTowerInventory = new ArrayList<>(gameEnvironment.getTowerList());
    }
    /**
     * Adds a tower to the reserve tower inventory.
     *
     * @param tower the tower to add
     */
    public void addReserveTower(Tower tower){
        if (towerInventory.size() < TOWER_INVENTORY_SIZE){
            towerInventory.add(tower);
            System.out.println("Added " + tower + " to itemInventory. Space left: " + (TOWER_INVENTORY_SIZE - towerInventory.size()));
            System.out.println("Reserve towers: " + towerInventory);

        }
        else{
            System.out.println("Tower Inventory Full");
        }

        }
    /**
     * Adds an item to the item inventory.
     *
     * @param item the item to add
     */
    public void addItem(Item item){
        if(itemInventory.size() < ITEM_INVENTORY_SIZE){
            itemInventory.add(item);
            System.out.println("Added "+ item +" to itemInventory" + " Space left: "+ (ITEM_INVENTORY_SIZE - itemInventory.size()));
            System.out.println("Inventory: "+ itemInventory);

        }
        else{
            System.out.println("Item Inventory Full");

        }
    }
    /**
     * Checks if the item inventory is not full.
     *
     * @return true if the item inventory is not full, false otherwise
     */
    public boolean isItemInvNotFull(){
        if(itemInventory.size() < ITEM_INVENTORY_SIZE){
            return true;
        }
        else{
            return false;
        }

    }
    /**
     * Checks if the reserve tower inventory is not full.
     *
     * @return true if the reserve tower inventory is not full, false otherwise
     */
    public boolean isReserveTowerInvNotFull(){
        if(towerInventory.size() < TOWER_INVENTORY_SIZE){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Retrieves a tower from the reserve tower inventory at the specified index.
     *
     * @param index the index of the tower to retrieve
     * @return the tower at the specified index, or null if not found
     */

    public Tower getReserveTower(int index){
        if (index >= 0 && index < towerInventory.size()){
            System.out.println("Tower: "+ towerInventory.get(index));
            return towerInventory.get(index);

        }
        else{
            System.out.println("No tower found");
            return null;
        }

    }
    /**
     * Retrieves an item from the item inventory at the specified index.
     *
     * @param index the index of the item to retrieve
     * @return the item at the specified index, or null if not found
     */
    public Item getItem(int index){
        if (index>= 0 && index < itemInventory.size()){
            System.out.println("Item: "+ itemInventory.get(index));
            return itemInventory.get(index);
        }
        else{
            System.out.println("No item found");
            return null;
        }
    }
    /**
     * Retrieves the index of a tower in the reserve tower inventory.
     *
     * @param tower the tower to find the index of
     * @return the index of the tower, or -1 if not found
     */

    public int getTowerIndex(Tower tower) {
        return towerInventory.indexOf(tower);
    }
    /**
     * Retrieves the index of an item in the item inventory.
     *
     * @param item the item to find the index of
     * @return the index of the item, or -1 if not found
     */

    public int getItemIndex(Item item) {
        return itemInventory.indexOf(item);
    }
    /**
     * Removes a tower from the reserve tower inventory.
     *
     * @param tower the tower to remove
     */

    public void removeReserveTower(Tower tower){
        towerInventory.remove(tower);
        System.out.println(tower + "removed" +"Reserve Towers: "+ towerInventory);

    }
    /**
     * Removes an item from the item inventory.
     *
     * @param item the item to remove
     */
    public void removeItem(Item item){
        itemInventory.remove(item);
        System.out.println(item+ " removed, Items: "+ itemInventory);
    }
    /**
     * Retrieves the count of towers with the specified name in the reserve tower inventory.
     *
     * @param towerName the name of the tower
     * @return the count of towers with the specified name
     */
    public int getReserveTowerCount(String towerName) {
        return (int) towerInventory.stream().filter(tower -> tower.getName().equals(towerName)).count();
    }
    /**
     * Retrieves the count of items with the specified name in the item inventory.
     *
     * @param itemName the name of the item
     * @return the count of items with the specified name
     */

    public int getItemCount(String itemName) {
        return (int) itemInventory.stream().filter(item -> item.getName().equals(itemName)).count();
    }
    /**
     * Retrieves the item inventory.
     *
     * @return the item inventory
     */
    public List getItemInventory(){
        return itemInventory;
    }
    /**
     * Retrieves the reserve tower inventory.
     *
     * @return the reserve tower inventory
     */

    public List getTowerInventory(){
        return towerInventory;
    }
    /**
     * Swaps a tower between the main tower inventory and the reserve tower inventory.
     *
     * @param mainTower Tower to swap
     * @param reserveTower Tower to swap
     */
    public void SwapTower(Tower mainTower, Tower reserveTower){
        if (mainTower == null && reserveTower !=null){
            towerService.getMainTowers().add(reserveTower);
            towerInventory.remove(reserveTower); // add the reserve tower into the main towers when the main is empty
        } else if (mainTower != null && reserveTower ==null){
            towerInventory.add(mainTower);
            towerService.getMainTowers().remove(mainTower); //add the main tower into the reserve tower when the reserve is empty
        }
        else{
            towerService.getMainTowers().remove(mainTower);
            towerService.getMainTowers().add(reserveTower);
            towerInventory.remove(reserveTower);
            towerInventory.add(mainTower); //swapping the main and reserve towers if both of them are not empty

        }

    }
    /**
     * Uses an item on a tower.
     *
     * @param item  the item to use
     * @param tower the tower to use the item on
     */
    public void useItemOnTower(Item item, Tower tower) {
        if (item.getName().equals("Upgrade Item")) {
            // Upgrade the tower's properties
            tower.levelUp();
            removeItem(item);
        } else {
            System.out.println("This item cannot be used on a tower.");
        }
    }
    /**
     * Uses a gold item.
     *
     * @param item the gold item to use
     */
    public void useGoldItem(Item item) {
        if (item.getName().equals("Gold Item")) {
            gameEnvironment.addPlayerGold(5);
            removeItem(item);
        } else {
            System.out.println("This item is not a gold item.");
        }
    }

    /**
     * Uses a points item.
     * @param item the points item to use
     */
    public void usePointItem(Item item){
        if (item.getName().equals("Points Item")){
            gameEnvironment.addPlayerPoints(5);
            removeItem(item);
        } else {
            System.out.println("This item is not a points item");
        }
    }



}
