package seng201.team0.models;


/**
 * Tower class containing default stats such as resource amount,
 * reloading speed, resource type, level, and cost
 * @author Allen - Team 75
 *
 */
public class Tower {
    private String name;
    private String resourceType;
    private int resourceAmount;
    private int reloadSpeed;
    private int level;
    private int cost;

    /**
     *Constructor for Tower
     * @param name name of tower
     * @param resourceType resource type of tower
     * @param cost cost of tower
     * Initializes the value for resource amount, reload speed, and level of tower
     */
    public Tower(String name, String resourceType, int cost){
        this.name = name;
        this.resourceType = resourceType;
        this.resourceAmount = 500;
        this.reloadSpeed = 25;
        this.level = 1;
        this.cost = cost;
    }

    /**
     * Method for getting the name of the tower
     * @return name of tower
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the new name of tower
     * @param name new name of tower
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Method for getting the amount of resources of tower
     * @return resource amount of tower
     */
    public int getResourceAmount(){
        return resourceAmount;
    }

    /**
     * Method for getting the amount of reload speed
     * @return reload speed of tower
     */
    public int getReloadSpeed(){
        return reloadSpeed;
    }

    /**
     * Method for getting the resource type of tower
     * @return resource type of tower
     */
    public String getResourceType(){
        return resourceType;
    }

    /**
     * Sets the resource type of the tower
     * @param resourceType resource type of tower
     */
    public void setResourceType(String resourceType){
        this.resourceType = resourceType;
    }

    /**
     * Method for upgrading the level of tower
     * Increases the level of tower by 1
     * Increases the resource amount by 500
     * Increases the reload speed by 25
     */
    public void levelUp(){
        level++;
        resourceAmount += 500;
        reloadSpeed += 25;
    }

}
