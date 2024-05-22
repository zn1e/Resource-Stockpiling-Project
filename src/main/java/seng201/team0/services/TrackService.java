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
    /**
     * The game environment instance.
     */
    private GameEnvironment gameEnvironment;
    /**
     * List of carts.
     */
    private List<Cart> carts = new ArrayList<>();
    /**
     * List of filled carts.
     */
    private List<Cart> filledCarts = new ArrayList<>();
    /**
     * List of not filled carts.
     */
    private List<Cart> notFilledCarts = new ArrayList<>();
    /**
     * List of resource type.
     */
    private List<String> resourceTypeList = new ArrayList<>();
    /**
     * Tower service instance.
     */
    private TowerService towerService;
    /**
     * Boolean property describing if max distance is covered.
     */
    private BooleanProperty maxDistanceCovered = new SimpleBooleanProperty(false);
    /**
     * Random instance.
     */
    private Random random;
    /**
     * Constant track positions.
     */
    private static final int[][] TRACK_POSITIONS = { {0,1},
            {1,1}, {2,1}, {3,1}, {4,1}, {5,1}, {6,1},{7,1}, {8,1}, {9,1}, {10,1}, {11,1}};

    /**
     * Constructor for TrackService with specified game environment instance.
     * Initializes the tower service, random instance, and resource type list.
     * @param gameEnvironment
     */
    public TrackService(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.towerService = new TowerService(gameEnvironment);
        setResourceTypeList();
        random = new Random();
    }

    /**
     * Get the list of not filled carts.
     * @return The list containing the not filled carts.
     */
    public List<Cart> getNotFilledCarts(){
        return notFilledCarts;
    }

    /**
     * Adds the track image.
     * @param trackGrid GridPane where track image is placed.
     */
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

    /**
     * Initializes the carts in the first position with its randomized properties.
     * @param trackGrid GridPane where the cart is placed.
     */
    public void initializeCarts(GridPane trackGrid){
        int numberOfCarts = getRandomNumberOfCarts();
        System.out.println("Number of carts: " + numberOfCarts);
        for (int i = 0; i < numberOfCarts; i++){
            int cartResourceCapacity = getRandomResourceCapacity();
            String cartResourceType = getRandomResourceType();
            Cart cart = new Cart(cartResourceCapacity, cartResourceType, 0);
            carts.add(cart);
            addCartToGrid(trackGrid, cart);
        }
    }

    /**
     * Add carts to the grid with their corresponding labels.
     * @param trackGrid GridPane where the cart is added.
     * @param cart Cart object to be added on grid.
     */
    private void addCartToGrid(GridPane trackGrid, Cart cart){
        VBox cartContainer = new VBox();
        Label cartLoadLabel = new Label("Ld: " + cart.getCurrentLoad() + "/" + cart.getResourceCapacity());
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

    /**
     * Gets the image of cart based on its current load.
     * @param cart The cart object.
     * @return Image describing the current state of cart.
     */
    private Image cartImage(Cart cart){
        if (cart.isFull()){
            return new Image(getClass().getResourceAsStream("/images/full_cart.png"));
        } else{
            return new Image(getClass().getResourceAsStream("/images/empty_cart.png"));
        }
    }

    /**
     * Move the carts in the grid using animation.
     * @param trackGrid GridPane where the carts move.
     */
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
            cartTimeline.stop();
            }
        });
        cartTimeline.setCycleCount(1);
        cartTimeline.play();
    }

    /**
     * Update the cart position on the grid.
     * @param trackGrid GridPane where the cart moves.
     * @param cart The cart object being moved.
     */
    private void updateCartPosition(GridPane trackGrid, Cart cart){
        VBox cartContainer = (VBox) trackGrid.getChildren().filtered(node -> node.getUserData() == cart).get(0);
        trackGrid.getChildren().remove(cartContainer);
        addCartToGrid(trackGrid, cart);
    }

    /**
     * Handles the interaction of the cart to the tower.
     * @param cart The cart object being handled.
     */
    private void checkTowerInteraction(Cart cart){
        int[] cartPosition = TRACK_POSITIONS[cart.getCurrentPosition()];
        towerService.towerInteraction(cart, cartPosition);
    }

    /**
     * Sets the resource type list based on the resources of the main towers.
     */
    private void setResourceTypeList(){
        List<Tower> mainTowers = towerService.getMainTowers();
        for (Tower tower: mainTowers){
            resourceTypeList.add(tower.getResourceType());
        }
    }

    /**
     * Gets random resource type for the cart.
     * @return String resource type of the cart.
     */
    private String getRandomResourceType(){
        int randomResourceTypeIndex = random.nextInt(resourceTypeList.size()-1);
        String randomResourceType = resourceTypeList.get(randomResourceTypeIndex);
        return randomResourceType;
    }

    /**
     * Gets random resource capacity of cart based on round difficulty.
     * @return Int describing the resource capacity of the cart.
     */
    private int getRandomResourceCapacity(){
        String difficulty = gameEnvironment.getRoundDifficulty();
        if (difficulty.equals("easy")){
            int randomResourceCapacity = random.nextInt(250) + 250;
            return randomResourceCapacity;
        }else{
            int randomResourceCapacity = random.nextInt(500) + 500;
            return randomResourceCapacity;
        }
    }

    /**
     * Checks the current state of the cart load.
     * @param cart The cart object being checked.
     */
    private void checkCartLoad(Cart cart){
        if (cart.isFull()){
            filledCarts.add(cart);
        } else{
            notFilledCarts.add(cart);
        }
    }

    /**
     * Generates random number of carts based on difficulty.
     * @return Int random number of carts.
     */
    private int getRandomNumberOfCarts(){
        String difficulty = gameEnvironment.getRoundDifficulty();
        if (difficulty.equals("easy")){
            int numberOfCarts = random.nextInt(2) + 3;
            return numberOfCarts;
        }else{
            int numberOfCarts = random.nextInt(4) + 6;
            return numberOfCarts;
        }
    }

    /**
     * Trigger random events after round.
     * @param trackGrid GridPane where random events occurred.
     */
    public void triggerRandomEvents(GridPane trackGrid){
        List<Tower> mainTowers = towerService.getMainTowers();
        for (Tower tower: mainTowers){
            int statChangeChance = random.nextInt(100);
            if (statChangeChance < 20){
                towerService.modifyTowerStat(tower);
            }
            int breakTowerChance = random.nextInt(100);
            if (breakTowerChance < 30){
                towerService.breakTower(tower, trackGrid);
            }
        }
    }

    /**
     * Gets the current state of the distance covered.
     * @return A boolean describing if the max distance is covered.
     */
    public BooleanProperty maxDistanceCoveredProperty(){
        return maxDistanceCovered;
    }
}
