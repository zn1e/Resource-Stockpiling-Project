package seng201.team0.services;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;

import java.util.Random;

import java.util.ArrayList;
import java.util.List;

public class TrackService {
    private GameEnvironment gameEnvironment;
    private List<Cart> carts = new ArrayList<>();
    private List<Cart> filledCarts = new ArrayList<>();
    private List<Cart> notFilledCarts = new ArrayList<>();
    private List<String> resourceTypeList = new ArrayList<>();
    private TowerService towerService;
    private Random random;
    private static final int[][] TRACK_POSITIONS = { {0,1},
            {1,1}, {2,1}, {3,1}, {4,1}, {5,1}, {6,1},{7,1}, {8,1}, {9,1}, {10,1}};
    public TrackService(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.towerService = new TowerService(gameEnvironment);
        setResourceTypeList();
    }
    public void setupTrackImage(GridPane trackGrid){
        Image trackImage = new Image(getClass().getResourceAsStream("/images/track.png"));
        for (int i = 0; i < TRACK_POSITIONS.length;i++){
            ImageView trackImageView = new ImageView(trackImage);
            trackImageView.setFitWidth(100);
            trackImageView.setFitHeight(100);
            int[] trackPosition = TRACK_POSITIONS[i];
            trackGrid.add(trackImageView, trackPosition[0], trackPosition[1]);
        }
    }
    public void initializeCarts(GridPane trackGrid){
        int numberOfCarts = random.nextInt(3,10);
        for (int i = 0; i< numberOfCarts; i++){
            int cartResourceCapacity = getRandomResourceCapacity();
            String cartResourceType = getRandomResourceType();
            Cart cart = new Cart(cartResourceCapacity, cartResourceType, 0);
            carts.add(cart);
            addCartToGrid(trackGrid, cart);
        }
    }
    private void addCartToGrid(GridPane trackGrid, Cart cart){

    }
    private void setResourceTypeList(){
        List<Tower> mainTowers = towerService.getMainTowers();
        for (Tower tower: mainTowers){
            resourceTypeList.add(tower.getResourceType());
        }
    }
    private String getRandomResourceType(){
        int randomResourceTypeIndex = random.nextInt(resourceTypeList.size()-1);
        String randomResourceType = resourceTypeList.get(randomResourceTypeIndex);
        return randomResourceType;
    }
    private int getRandomResourceCapacity(){
        String difficulty = gameEnvironment.getRoundDifficulty();
        if (difficulty.equals("easy")){
            int randomResourceCapacity = random.nextInt(250,500);
            return randomResourceCapacity;
        }else{
            int randomResourceCapacity = random.nextInt(500,1000);
            return randomResourceCapacity;
        }
    }

}
