package seng201.team0.models;


public class Cart {
    private int resourceCapacity;
    private String resourceType;
    private int currentPosition;

    public Cart(int resourceCapacity, String resourceType, int position){
        this.resourceCapacity = resourceCapacity;
        this.resourceType = resourceType;
        this.currentPosition = position;
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
}
