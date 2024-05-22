package seng201.team0.models;

import javafx.scene.image.Image;

public class Item {
    protected String itemName;
    protected int itemCost;
    protected Image image;


    public Item(String itemName, int itemCost, Image image){
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.image = image;

    }
    public Image getImage(){
        return image;
    }
    public int getItemCost(){
        return itemCost;}

    public String getName(){
        return itemName;
    }

    public String toString(){

        return itemName;
    }
    public int getSellPrice(){
        return itemCost- 5;}

    public void useResourceUpgrade(Tower tower){
        tower.resourceUpgrade(200);
        System.out.println(tower + "'s resource amount upgraded to "+ tower.getResourceAmount());

    }
    public void useReloadUpgrade(Tower tower){
        tower.reloadUpgrade(10);
        System.out.println(tower + "'s reload speed upgraded to "+ tower.getReloadSpeed());

    }
    public void useSwap(Tower tower1, Tower tower2){
        tower1.swapTower(tower2);
        System.out.println(tower1 + "swapped with" + tower2);

    }
}

