package seng201.team0.models;

import javafx.scene.image.Image;

/**
 * Item class represents an item in the game.
 * It contains the information of the item's name, cost, image and description
 */

public class Item {
    /**
     * String item name.
     */
    protected String itemName;
    /**
     * Int item cost.
     */
    protected int itemCost;
    /**
     * Image of an item.
     */
    protected Image image;
    /**
     * String for item description.
     */
    protected String itemDescription;

    /**
     * Constructs an Item object with the specified name, cost, image, and description.
     *
     * @param itemName        the name of the item
     * @param itemCost        the cost of the item
     * @param image           the image of the item
     * @param itemDescription the description of the item
     * @throws IllegalArgumentException if the item cost is negative
     */

    public Item(String itemName, int itemCost, Image image, String itemDescription){
        if (itemCost <0 ){
            throw new IllegalArgumentException("Item cost cannot be negative.");
        }
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.image = image;
        this.itemDescription = itemDescription;

    }

    /**
     * Gets the image.
     * @return image of the item.
     */
    public Image getImage(){
        return image;
    }

    /**
     * Gets the item cost.
     * @return An int representing the value of an item.
     */
    public int getItemCost(){
        return itemCost;}

    /**
     * Gets the item name.
     * @return String of the item name.
     */
    public String getName(){
        return itemName;
    }

    /**
     * Gets the text representation of the string of item.
     * @return String name of the item.
     */
    public String toString(){
        return itemName;
    }

    /**
     * Get the sell price.
     * @return An int representing the sell price.
     */
    public int getSellPrice(){
        return itemCost- 5;}

    /**
     * Gets the item description.
     * @return String describing the item.
     */
    public String getDescription(){
        return itemDescription;
    }

    }