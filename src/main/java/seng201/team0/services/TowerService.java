package seng201.team0.services;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;

public class TowerService {
    /**
     * List of main towers.
     */
    private List<Tower> mainTowers;
    /**
     * List of reserve towers.
     */
    private List<Tower> reserveTowers;
    /**
     * List of positions of main towers.
     */
    private List<int[]> mainTowerPositions;
    /**
     * Coordinates of the position in grid pane.
     */
    private static final int[][] INITIAL_POSITIONS = { {2,0}, {4,2}, {6,0}, {8,2}, {10,0}};
    /**
     * The game environment instance.
     */
    private GameEnvironment gameEnvironment;

    /**
     * Initializes the game environment instance and setting up the main towers and their positions.
     * @param gameEnvironment The game environment instance associated with this class.
     */
    public TowerService(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.mainTowerPositions = new ArrayList<>();
        this.mainTowers = gameEnvironment.getTowerList();
        setInitialPositions();
    }

    /**
     * Sets initial positions of the main towers.
     */
    private void setInitialPositions() {
        for (int i = 0; i < INITIAL_POSITIONS.length; i++) {
            if (i < mainTowers.size()) {
                this.mainTowerPositions.add(INITIAL_POSITIONS[i]);
            }
        }
    }

    /**
     * Gets the position of main towers.
     * @return A list containing the main towers positions.
     */
    public List<int[]> getMainTowerPositions(){
        return mainTowerPositions;
    }

    /**
     * Gets the list of main towers.
     * @return A list containing the main towers.
     */
    public List<Tower> getMainTowers(){
        return mainTowers;
    }

    /**
     * Gets the list of reserve towers.
     * @return A list containing the reserve towers.
     */
    public List<Tower> getReserveTowers(){
        return reserveTowers;
    }

    /**
     * Gets the next available position.
     * @return An array containing the available positions.
     */
    private int[] getNextAvailablePosition() {
        for (int i = 0; i < INITIAL_POSITIONS.length; i++) {
            if (!mainTowerPositions.contains(INITIAL_POSITIONS[i])) {
                return INITIAL_POSITIONS[i];
            }
        }
        return null;
    }

    /**
     * Places the main towers on initial positions with their image representation.
     * @param trackGrid GridPane on which to place tower images.
     */
    public void setupMainTowers(GridPane trackGrid){
        for (int i = 0; i < mainTowers.size(); i++){
            Tower tower = mainTowers.get(i);
            int[] position = INITIAL_POSITIONS[i];
            ImageView towerImageView = new ImageView(tower.getImage());
            towerImageView.setFitHeight(80);
            towerImageView.setFitWidth(50);
            trackGrid.add(towerImageView, position[0], position[1]);
        }
    }

    /**
     * Places new tower on available position with its image representation.
     * @param newTower The tower object to be added on the position.
     * @param trackGrid GridPane on which to place the new tower.
     */
    public void addNewTower(Tower newTower, GridPane trackGrid){
        int[] newPosition = getNextAvailablePosition();
        if (newPosition != null){
            mainTowers.add(newTower);
            mainTowerPositions.add(newPosition);

            ImageView newTowerImageView = new ImageView(newTower.getImage());
            newTowerImageView.setFitHeight(80);
            newTowerImageView.setFitWidth(50);
            trackGrid.add(newTowerImageView, newPosition[0], newPosition[1]);
        }
    }

    /**
     * Handles the tower interaction of the cart at specified position.
     * @param cart The cart containing its own property.
     * @param cartPosition Int array containing the current position of the cart.
     */
    public void towerInteraction(Cart cart, int[] cartPosition){
        for (int i = 0; i < mainTowerPositions.size(); i++){
            int[] towerPositions = mainTowerPositions.get(i);
            if (cartPosition[0] == towerPositions[0]){
                Tower tower = mainTowers.get(i);
                if (!cart.isFull() && tower.getResourceType().equals((cart.getResourceType()))){
                    cart.addResources(tower.getResourceAmount());
                }
            }
        }
    }
}
