package seng201.team0.services;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Item;
import seng201.team0.models.Tower;

import java.util.ArrayList;


public class InventoryService {
    private static final int ITEM_INVENTORY_SIZE = 10;
    private static final int TOWER_INVENTORY_SIZE = 5;

    private ArrayList<Item> itemInventory;
    private ArrayList<Tower> towerInventory;

    public InventoryService(GameEnvironment gameEnvironment){
        itemInventory = new ArrayList<>(ITEM_INVENTORY_SIZE);
        towerInventory = new ArrayList<>(TOWER_INVENTORY_SIZE);


    }
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

    public int getTowerIndex(Tower tower) {
        return towerInventory.indexOf(tower);
    }

    public int getItemIndex(Item item) {
        return itemInventory.indexOf(item);
    }

    public void removeReserveTower(Tower tower){
        towerInventory.remove(tower);
        System.out.println(tower + "removed" +"Reserve Towers: "+ towerInventory);

    }
    public void removeItem(Item item){
        itemInventory.remove(item);
        System.out.println(item+ " removed, Items: "+ itemInventory);
    }
    public int getReserveTowerCount(String towerName) {
        return (int) towerInventory.stream().filter(tower -> tower.getName().equals(towerName)).count();
    }


    public int getItemCount(String itemName) {
        return (int) itemInventory.stream().filter(item -> item.getName().equals(itemName)).count();
    }



}
