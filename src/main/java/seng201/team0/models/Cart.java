package seng201.team0.models;

public class Cart {
    private String size;
    private int resourceCapacity;
    private int speed;

    public Cart(String size, int resourceCapacity, int speed){
        this.size = size;
        this.resourceCapacity = resourceCapacity;
        this.speed = speed;
    }
    public String getSize(){
        return this.size;
    }
    public int getResourceCapacity(){
        return this.resourceCapacity;
    }
    public int getSpeed(){
        return this.speed;
    }

}
