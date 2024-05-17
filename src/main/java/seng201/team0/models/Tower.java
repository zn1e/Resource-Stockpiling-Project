package seng201.team0.models;


/**
 * Tower class containing default stats such as resource amount,
 * reloading speed, resource type, level, and cost.
 */
public class Tower {
    private String name; // name of tower
    private String resourceType; // resource type of tower
    private int resourceAmount; // resource amount of tower
    private int reloadSpeed; // reload speed of tower
    private int level; // level of tower
    private int cost; // cost of tower

    /**
     *Constructor for Tower.
     * @param name A String name of tower
     * @param resourceType An int resource type of tower
     * Initializes the value for resource amount, reload speed, level of tower, and cost (all int type).
     */
    public Tower(String name, String resourceType){
        this.name = name;
        this.resourceType = resourceType;
        this.resourceAmount = 500;
        this.reloadSpeed = 25;
        this.level = 1;
        this.cost = 60;
    }

    /**
     * Method for getting the name of the tower.
     * @return A string representing the name of tower.
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of tower.
     * @param name A string representing the new name of tower.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Method for getting the amount of resources of tower.
     * @return An int representing the resource amount of tower.
     */
    public int getResourceAmount(){
        return resourceAmount;
    }

    /**
     * Method for getting the amount of reload speed.
     * @return An int representing the reload speed of tower.
     */
    public int getReloadSpeed(){
        return reloadSpeed;
    }

    /**
     * Method for getting the resource type of tower.
     * @return A string representing the resource type of tower.
     */
    public String getResourceType(){
        return resourceType;
    }

    public int getLevel(){
        return level;
    }
    /**
     * Sets the resource type of the tower.
     * @param resourceType A String representing the new resource type of tower.
     */
    public void setResourceType(String resourceType){
        this.resourceType = resourceType;
    }

    /**
     * Method for upgrading the level of tower.
     * Increases the level of tower by 1, resource amount by 500, and reload speed by 25.
     */
    public void levelUp(){
        level++;
        resourceAmount += 500;
        reloadSpeed += 25;
    }

    @Override
    public String toString() {
        return name;
    }
}
