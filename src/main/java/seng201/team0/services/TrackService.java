package seng201.team0.services;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
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
    private BooleanProperty maxDistanceCovered = new SimpleBooleanProperty(false);
    private Random random;
    private static final int[][] TRACK_POSITIONS = { {0,1},
            {1,1}, {2,1}, {3,1}, {4,1}, {5,1}, {6,1},{7,1}, {8,1}, {9,1}, {10,1}};
    public TrackService(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.towerService = new TowerService(gameEnvironment);
        setResourceTypeList();
        random = new Random();
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
        int numberOfCarts = random.nextInt(8)+ 3;
        System.out.println("Number of carts: " + numberOfCarts);
        for (int i = 0; i < numberOfCarts; i++){
            int cartResourceCapacity = getRandomResourceCapacity();
            String cartResourceType = getRandomResourceType();
            Cart cart = new Cart(cartResourceCapacity, cartResourceType, 0);
            carts.add(cart);
            addCartToGrid(trackGrid, cart);
        }
    }
    private void addCartToGrid(GridPane trackGrid, Cart cart){
        VBox cartContainer = new VBox();
        Label cartLoadLabel = new Label("Load: " + cart.getCurrentLoad());
        Label cartResourceLabel = new Label("Rrsc: " + cart.getResourceType());
        Label cartSpeedLabel = new Label("Spd: " + cart.getSpeed());

        cartLoadLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        cartResourceLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        cartSpeedLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        ImageView cartImageView = new ImageView(cartImage(cart));
        cartImageView.setFitHeight(50);
        cartImageView.setFitWidth(50);

        cartContainer.getChildren().addAll(cartLoadLabel, cartResourceLabel, cartSpeedLabel, cartImageView);
        int[] startPosition = TRACK_POSITIONS[cart.getCurrentPosition()];
        trackGrid.add(cartContainer, startPosition[0], startPosition[1]);
        cartContainer.setUserData(cart);
        cart.setCartContainer(cartContainer);
    }
    private Image cartImage(Cart cart){
        if (cart.isFull()){
            return new Image(getClass().getResourceAsStream("/images/full_cart.png"));
        } else{
            return new Image(getClass().getResourceAsStream("/images/empty_cart.png"));
        }
    }
    public void moveCarts(GridPane trackGrid){
        Timeline cartTimeline = new Timeline();
        int delay = 0;
        List<Cart> cartsToRemove = new ArrayList<>();

        for (Cart cart: carts){
            KeyFrame moveCart = new KeyFrame(Duration.seconds(delay), event -> {
                int nextPosition = cart.getCurrentPosition() + 1;
                if (nextPosition < TRACK_POSITIONS.length){
                    cart.setCurrentPosition(nextPosition);
                    updateCartPosition(trackGrid, cart);
                    checkTowerInteraction(cart);
                }else{
                    cartsToRemove.add(cart);
                    trackGrid.getChildren().removeIf(node -> node.getUserData() == cart);
                    checkCartLoad(cart);
                }
            });
            cartTimeline.getKeyFrames().add(moveCart);
            delay+=2;
        }
        cartTimeline.setOnFinished(event -> {
            carts.removeAll(cartsToRemove);

            if (carts.isEmpty()){
                maxDistanceCovered.set(true);
            }
        });
        cartTimeline.setCycleCount(1);
        cartTimeline.play();
    }
    private void updateCartPosition(GridPane trackGrid, Cart cart){
        VBox cartContainer = (VBox) trackGrid.getChildren().filtered(node -> node.getUserData() == cart).get(0);
        trackGrid.getChildren().remove(cartContainer);
        addCartToGrid(trackGrid, cart);
    }
    private void checkTowerInteraction(Cart cart){
        int[] cartPosition = TRACK_POSITIONS[cart.getCurrentPosition()];
        towerService.towerInteraction(cart, cartPosition);
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
    private void checkCartLoad(Cart cart){
        if (cart.isFull()){
            filledCarts.add(cart);
        } else{
            notFilledCarts.add(cart);
        }
    }
    public BooleanProperty maxDistanceCoveredProperty(){
        return maxDistanceCovered;
    }
}