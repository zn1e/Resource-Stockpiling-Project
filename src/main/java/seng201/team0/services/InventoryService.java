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
    /** The maximum size of the item inventory. */
    private static final int ITEM_INVENTORY_SIZE = 5;
    /** The maximum size of the tower inventory. */
    private static final int TOWER_INVENTORY_SIZE = 5;
    /** The list of items in the inventory. */
    private ArrayList<Item> itemInventory;
    /** The list of reserve towers in the inventory. */
    private ArrayList<Tower> reserveTower;
    /**
     * The game environment instance.
     */
    private GameEnvironment gameEnvironment;
    /**
     * Boolean if a gold item is used.
     */
    private boolean goldItemUsed = false;
    /**
     * Boolean if a points item is used.
     */
    private boolean pointsItemUsed = false;
    /**
     * Constructs an InventoryService object with the specified game environment.
     * @param gameEnvironment the game environment
     */

    public InventoryService(GameEnvironment gameEnvironment){
        this.itemInventory = new ArrayList<>(ITEM_INVENTORY_SIZE);
        this.reserveTower = new ArrayList<>(TOWER_INVENTORY_SIZE);
        this.gameEnvironment = gameEnvironment;
    }


    /**
     * Adds a tower to the reserve tower inventory.
     * @param tower the tower to add
     */
    public void addReserveTower(Tower tower){
        if (reserveTower.size() < TOWER_INVENTORY_SIZE){
            reserveTower.add(tower);
            System.out.println("Added " + tower + " to itemInventory. Space left: " + (TOWER_INVENTORY_SIZE - reserveTower.size()));
            System.out.println("Reserve towers: " + reserveTower);

        }
        else{
            System.out.println("Tower Inventory Full");
        }

        }
    /**
     * Adds an item to the item inventory.
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
     * @return true if the reserve tower inventory is not full, false otherwise
     */
    public boolean isReserveTowerInvNotFull(){
        if(reserveTower.size() < TOWER_INVENTORY_SIZE){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Retrieves a tower from the reserve tower inventory at the specified index.
     * @param index the index of the tower to retrieve
     * @return the tower at the specified index, or null if not found
     */

    public Tower getReserveTower(int index){
        if (index >= 0 && index < reserveTower.size()){
            System.out.println("Tower: "+ reserveTower.get(index));
            return reserveTower.get(index);

        }
        else{
            System.out.println("No tower found");
            return null;
        }

    }
    /**
     * Retrieves an item from the item inventory at the specified index.
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
     * Removes the reserve tower from the inventory.
     * @param tower Tower to be removed.
     */
    public void removeReserveTower(Tower tower){
        reserveTower.remove(tower);
        System.out.println(tower + "removed" +"Reserve Towers: "+ reserveTower);

    }
    /**
     * Removes an item from the item inventory.
     * @param item the item to remove
     */
    public void removeItem(Item item){
        itemInventory.remove(item);
        System.out.println(item+ " removed, Items: "+ itemInventory);
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
        return reserveTower;
    }
    /**
     * Swap the position of main tower to the position of reserve tower & vice versa.
     * @param mainTower The main tower to swap.
     * @param reserveTower The reserve tower to swap with.
     */
    public void swapTower(Tower mainTower, Tower reserveTower){
        if (mainTower == null && reserveTower !=null){
            gameEnvironment.getTowerList().add(reserveTower);
            this.reserveTower.remove(reserveTower); // add the reserve tower into the main towers when the main is empty
        } else if (mainTower != null && reserveTower == null){
            this.reserveTower.add(mainTower);
            gameEnvironment.getTowerList().remove(mainTower); //add the main tower into the reserve tower when the reserve is empty
        }
        else{
            gameEnvironment.getTowerList().remove(mainTower);
            gameEnvironment.getTowerList().add(reserveTower);
            this.reserveTower.remove(reserveTower);
            this.reserveTower.add(mainTower); //swapping the main and reserve towers if both of them are not empty

        }

    }
    /**
     * Uses an item based on its type.
     * @param item  the item to use
     * @param tower the tower to use the item on, can be null if the item doesn't affect a tower
     */
    public void useItem(Item item, Tower tower) {
        switch (item.getName()) {
            case "Upgrade Item":
                if (tower != null) {
                    tower.levelUp();
                    removeItem(item);
                } else {
                    System.out.println("No tower specified to upgrade.");
                }
                break;

            case "Gold Item":
                System.out.println("gold item used");
                goldItemUsed = true;
                removeItem(item);
                break;

            case "Points Item":
                System.out.println("points item used");
                pointsItemUsed = true;
                removeItem(item);
                break;

            default:
                System.out.println("This item cannot be used.");
                break;
        }
    }

    /**
     * Sets the boolean if a gold item is used.
     * @param used Boolean that determines if a gold item is used.
     */
    public void setGoldItemUsed(boolean used){
        goldItemUsed = used;
    }

    /**
     * Sets the boolean if a point item is used.
     * @param used Boolean that determines if a points item is used.
     */
    public void setPointsItemUsed(boolean used){
        pointsItemUsed = used;
    }

    /**
     * Gets the boolean if gold item is used.
     * @return Boolean describing if gold item is used.
     */
    public boolean isGoldItemUsed(){
        return goldItemUsed;
    }

    /**
     * Gets the boolean if point item is used.
     * @return Boolean describing if point item is used.
     */
    public boolean isPointsItemUsed(){
        return pointsItemUsed;
    }


}
