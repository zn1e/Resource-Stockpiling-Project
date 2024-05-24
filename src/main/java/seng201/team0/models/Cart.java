package seng201.team0.models;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Cart {
    /**
     * Resource capacity of the cart.
     */
    private int resourceCapacity;
    /**
     * Resource type of cart.
     */
    private String resourceType;
    /**
     * Current position of cart.
     */
    private int currentPosition;
    /**
     * Load of cart at current position.
     */
    private int currentLoad;
    /**
     * Speed of cart.
     */
    private int speed;
    /**
     * Vbox container of the cart.
     */
    private VBox cartContainer;

    /**
     * Initializes the resource capacity, resource type, position, current load, and speed of the cart.
     * @param resourceCapacity An int describing the resource capacity of cart.
     * @param resourceType A String describing the resource type of cart.
     * @param position An int describing the position of cart.
     */
    public Cart(int resourceCapacity, String resourceType, int position){
        this.resourceCapacity = resourceCapacity;
        this.resourceType = resourceType;
        this.currentPosition = position;
        this.currentLoad = 0;
        this.speed = 10;
    }

    /**
     * Gets the resource capacity of cart.
     * @return An int representing the value of cart's resource capacity.
     */
    public int getResourceCapacity(){
        return resourceCapacity;
    }

    /**
     * Gets the resource type of cart.
     * @return A String representing the resource type of cart.
     */
    public String getResourceType(){
        return resourceType;
    }

    /**
     * Gets the current position of cart.
     * @return An int representing the cart's current position.
     */
    public int getCurrentPosition(){
        return currentPosition;
    }

    /**
     * Sets the current position of cart.
     * @param position An int describing the position of cart.
     */
    public void setCurrentPosition(int position){
        this.currentPosition = position;
    }

    /**
     * Gets the current load of cart at current position.
     * @return An int describing the current load of cart.
     */
    public int getCurrentLoad(){
        return currentLoad;
    }

    /**
     * Gets the speed of the cart.
     * @return An int representing the speed of cart.
     */
    public int getSpeed(){
        return speed;
    }

    /**
     * Add resources to the load of cart.
     * @param amount An int representing the amount of resources added.
     */
    public void addResources(int amount){
        currentLoad  = Math.min(currentLoad + amount, resourceCapacity);
        updateLoadLabel();
    }

    /**
     *Checks if the cart load is full.
     * @return A boolean describing if the cart is full.
     */
    public boolean isFull(){
        return currentLoad == resourceCapacity;
    }

    /**
     * Sets the vbox container of the cart.
     * @param cartContainer A vbox containing the cart's labels.
     */
    public void setCartContainer(VBox cartContainer){
        this.cartContainer = cartContainer;
    }

    /**
     * Update the load label of the cart.
     */
    private void updateLoadLabel(){
        if (cartContainer != null && cartContainer.getChildren().get(0) instanceof Label) {
            Label loadLabel = (Label) cartContainer.getChildren().get(0);
            loadLabel.setText("Ld: " + currentLoad);
            loadLabel.setMinWidth(80);
            loadLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }
}
