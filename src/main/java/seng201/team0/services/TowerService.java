package seng201.team0.services;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Cart;
import seng201.team0.models.Item;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private Random random;

    /**
     * Initializes the game environment instance and setting up the main towers and their positions.
     * @param gameEnvironment The game environment instance associated with this class.
     */
    public TowerService(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.mainTowerPositions = new ArrayList<>();
        this.mainTowers = gameEnvironment.getTowerList();
        this.random = new Random();
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
            mainTowerPositions.add(position);
            ImageView towerImageView = new ImageView(tower.getImage());
            towerImageView.setFitHeight(80);
            towerImageView.setFitWidth(50);
            trackGrid.add(towerImageView, position[0], position[1]);

            // Check if the node is added.
            Integer rowIndex = GridPane.getRowIndex(towerImageView);
            Integer colIndex = GridPane.getColumnIndex(towerImageView);
            System.out.println("Tower ImageView added with rowIndex: " + rowIndex + " and colIndex: " + colIndex);
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
     * Remove tower on the grid.
     * @param towerToRemove Tower object to be removed.
     * @param trackGrid GridPane where tower is removed.
     */
    public void removeTower(Tower towerToRemove, GridPane trackGrid){
        int towerIndex = mainTowers.indexOf(towerToRemove);
        if (towerIndex != -1){
            mainTowers.remove(towerIndex);
            int[] positionToRemove = mainTowerPositions.get(towerIndex);
            mainTowerPositions.remove(towerIndex);

            Node nodeToRemove = null;
            for (Node node : trackGrid.getChildren()){
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer colIndex = GridPane.getColumnIndex(node);
                if (rowIndex != null && colIndex != null){
                    if (colIndex == positionToRemove[0] && rowIndex == positionToRemove[1]){
                        nodeToRemove = node;
                        break;
                    }
                }
            }
            if (nodeToRemove != null){
                trackGrid.getChildren().remove(nodeToRemove);
            } else{
                System.out.println("Node not found.");
            }
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

    /**
     * Event where tower resource amount is reduced.
     * @param tower Tower object which resource amount is reduced.
     */
    public void modifyTowerStat(Tower tower, Label alertLabel1){
        int resourceAmount = tower.getResourceAmount();
        int statChange = random.nextInt(resourceAmount - 250) + 250;
        tower.setResourceAmount(resourceAmount - statChange);
        alertLabel1.setText("Alert 1: Tower stat changed.");
    }

    /**
     * Event where tower breaks.
     * @param tower Tower object to be broken.
     * @param trackGrid GridPane where tower is.
     */
    public void breakTower(Tower tower, GridPane trackGrid, Label alertLabel2){
        int breakChance = random.nextInt(2);
        if (breakChance == 0){
            removeTower(tower, trackGrid);
            alertLabel2.setText("Alert 2: A tower has been removed.");
        }
    }
    public Tower getMainTower(int index){
        if (index>= 0 && index < mainTowers.size()){
            System.out.println("Main Tower: "+ mainTowers.get(index));
            return mainTowers.get(index);
        }
        else{
            System.out.println("No tower found");
            return null;
        }
    }


}
