
package seng201.team0.models;

public class Inventory {
    private static final int ITEM_INVENTORY_SIZE = 10;
    private static final int TOWER_INVENTORY_SIZE = 5;

    private Item[] itemInventory;
    private Tower[] towerInventory;


    public Inventory(){
        itemInventory = new Item[ITEM_INVENTORY_SIZE];
        towerInventory = new Tower[TOWER_INVENTORY_SIZE];
    }

}
