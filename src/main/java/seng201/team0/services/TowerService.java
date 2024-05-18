package seng201.team0.services;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;

public class TowerService {
    private List<Tower> mainTowers;
    private List<Tower> reserveTowers;
    private List<int[]> mainTowerPositions;
    private static final int[][] INITIAL_POSITIONS = { {1,0}, {3,2}, {5,0}, {7,2}, {9,0}};
    private GameEnvironment gameEnvironment;

    public TowerService(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.mainTowerPositions = new ArrayList<>();
        this.mainTowers = gameEnvironment.getTowerList();
        setInitialPositions();
    }
    private void setInitialPositions() {
        for (int i = 0; i < INITIAL_POSITIONS.length; i++) {
            if (i < mainTowers.size()) {
                this.mainTowerPositions.add(INITIAL_POSITIONS[i]);
            }
        }
    }
    public List<int[]> getMainTowerPositions(){
        return mainTowerPositions;
    }
    public List<Tower> getMainTowers(){
        return mainTowers;
    }
    public List<Tower> getReserveTowers(){
        return reserveTowers;
    }
    private int[] getNextAvailablePosition() {
        for (int i = 0; i < INITIAL_POSITIONS.length; i++) {
            if (!mainTowerPositions.contains(INITIAL_POSITIONS[i])) {
                return INITIAL_POSITIONS[i];
            }
        }
        return null;
    }
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
