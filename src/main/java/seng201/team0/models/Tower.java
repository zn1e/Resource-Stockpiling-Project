package seng201.team0.models;


import javafx.scene.image.Image;

/**
 * Tower class containing default stats such as resource amount,
 * reloading speed, resource type, level, and cost.
 */
public class Tower {
    /**
     * Name of tower.
     */
    private String name;
    /**
     * Resource type of tower.
     */
    private String resourceType;
    /**
     * Resource amount of tower.
     */
    private int resourceAmount;
    /**
     * Reload speed of tower.
     */
    private int reloadSpeed;
    /**
     * Level of tower.
     */
    private int level;
    /**
     * Cost of tower.
     */
    private int cost;
    /**
     * Image of tower.
     */
    private Image image;
    /**
     * Description of tower.
     */
    private String towerDescription;

    /**
     * Constructor for Tower.
     * @param name             A String describing the name of tower
     * @param resourceType     A String describing the resource type of tower
     * @param towerDescription A string describing the tower's description
     * @param image  A string path of the image.
     * Initializes the value for resource amount, reload speed, level of tower, and cost (all int type).
     */
    public Tower(String name, String resourceType, Image image, String towerDescription) {
        this.name = name;
        this.resourceType = resourceType;
        this.resourceAmount = 500;
        this.reloadSpeed = 1;
        this.level = 1;
        this.cost = 60;
        this.image = image;
        this.towerDescription = towerDescription;
    }

    /**
     * Method for getting the name of the tower.
     * @return A string representing the name of tower.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of tower.
     * @param name A string representing the new name of tower.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for getting the amount of resources of tower.
     * @return An int representing the resource amount of tower.
     */
    public int getResourceAmount() {
        return resourceAmount;
    }

    /**
     * Sets new resource amount of tower.
     * @param resourceAmount An int containing the value of resource amount of tower.
     */
    public void setResourceAmount(int resourceAmount) {
        this.resourceAmount = resourceAmount;
    }

    /**
     * Method for getting the amount of reload speed.
     * @return An int representing the reload speed of tower.
     */
    public int getReloadSpeed() {
        return reloadSpeed;
    }

    /**
     * Method for getting the resource type of tower.
     * @return A string representing the resource type of tower.
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * Get the tower level.
     * @return An int describing the value of tower level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the resource type of the tower.
     * @param resourceType A String representing the new resource type of tower.
     */

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * Gets the image of tower.
     * @return An image of the tower.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Gets the cost of tower.
     * @return An int cost of the tower.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Method for upgrading the level of tower.
     * Increases the level of tower by 1, resource amount by 500, and reload speed by 25.
     */
    public void levelUp() {
        level++;
        resourceAmount += 500;
    }

    /**
     * Method for getting the sell price of the tower
     * @return the sell price of the tower
     */
    public int getSellPrice() {
        return cost - 5;
    }

    /**
     * Gets the tower description.
     * @return String of the tower description.
     */
    public String getTowerDescription() {
        return towerDescription;
    }

    /**
     * Gets the text representation of the string tower name.
     * @return String in text representation of the tower name.
     */
    public String toString() {
        return name;
    }
}


