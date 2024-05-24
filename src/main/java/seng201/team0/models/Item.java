package seng201.team0.models;

import javafx.scene.image.Image;

/**
 * Item class represents an item in the game.
 * It contains the information of the item's name, cost, image and description
 */

public class Item {
    protected String itemName;
    protected int itemCost;
    protected Image image;
    protected String itemDescription;

    /**
     * Constructs an Item object with the specified name, cost, image, and description.
     *
     * @param itemName        the name of the item
     * @param itemCost        the cost of the item
     * @param image           the image of the item
     * @param itemDescription the description of the item
     */

    public Item(String itemName, int itemCost, Image image, String itemDescription){
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.image = image;
        this.itemDescription = itemDescription;

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

    public String getDescription(){
        return itemDescription;
    }

    }