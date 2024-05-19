package seng201.team0.models;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Cart {
    private int resourceCapacity;
    private String resourceType;
    private int currentPosition;
    private int currentLoad;
    private int speed;
    private VBox cartContainer;

    public Cart(int resourceCapacity, String resourceType, int position){
        this.resourceCapacity = resourceCapacity;
        this.resourceType = resourceType;
        this.currentPosition = position;
        this.currentLoad = 0;
        this.speed = 10;
    }
    public int getResourceCapacity(){
        return resourceCapacity;
    }
    public String getResourceType(){
        return resourceType;
    }
    public int getCurrentPosition(){
        return currentPosition;
    }
    public void setCurrentPosition(int position){
        this.currentPosition = position;
    }
    public int getCurrentLoad(){
        return currentLoad;
    }
    public int getSpeed(){
        return speed;
    }
    public void addResources(int amount){
        currentLoad  = Math.min(currentLoad + amount, resourceCapacity);
        updateLoadLabel();
    }
    public boolean isFull(){
        return currentLoad == resourceCapacity;
    }
    public void setCartContainer(VBox cartContainer){
        this.cartContainer = cartContainer;
    }
    private void updateLoadLabel(){
        if (cartContainer != null && cartContainer.getChildren().get(0) instanceof Label) {
            Label loadLabel = (Label) cartContainer.getChildren().get(0);
            loadLabel.setText("Load: " + currentLoad);
            loadLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }
}
