package seng201.team0.models;


public class Cart {
    private int resourceCapacity;
    private double speed;

    public Cart(int resourceCapacity, double speed){
        this.resourceCapacity = resourceCapacity;
        this.speed = speed;

    }
    public int getResourceCapacity(){
        return resourceCapacity;
    }
    public double getSpeed(){
        return speed;
    }

}
