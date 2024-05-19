package seng201.team0.models;

import javafx.scene.image.Image;

public class Item {
    private String itemName;
    private int itemCost;
    private Image image;

    public Item(String itemName, int itemCost, Image image){
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.image = image;


    }
    public Image getImage(){
        return image;
    }


}
