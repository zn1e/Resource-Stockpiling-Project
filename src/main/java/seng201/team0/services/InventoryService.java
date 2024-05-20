package seng201.team0.services;

import seng201.team0.models.Item;
import seng201.team0.models.Tower;


public class InventoryService {
    private static final int ITEM_INVENTORY_SIZE = 10;
    private static final int TOWER_INVENTORY_SIZE = 5;

    private Item[] itemInventory;
    private Tower[] towerInventory;
    private int towerCount = 0;
    private int itemCount = 0;

    public InventoryService(){
        itemInventory = new Item[ITEM_INVENTORY_SIZE];
        towerInventory = new Tower[TOWER_INVENTORY_SIZE];
    }
    public void addReserveTower(Tower tower){
        if(towerCount < TOWER_INVENTORY_SIZE){
            towerInventory[towerCount] = tower;
            towerCount ++;
            System.out.println("Added "+ tower +" to towerInventory" + " Space left: "+ (TOWER_INVENTORY_SIZE -towerCount));
        }
        else{
            System.out.println("Tower Inventory Full");
        }

        }

    public void addItem(Item item){
        if(itemCount < ITEM_INVENTORY_SIZE){
            itemInventory[itemCount] = item;
            itemCount ++;
            System.out.println("Added "+ item +" to itemInventory" + " Space left: "+ (ITEM_INVENTORY_SIZE -itemCount));
        }
        else{
            System.out.println("Item Inventory Full");
        }
    }

}
