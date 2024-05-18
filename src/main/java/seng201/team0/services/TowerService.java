package seng201.team0.services;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;

public class TowerService {
    private List<Tower> mainTowers;
    private List<Tower> reserveTowers;
    private List<int[]> positions;
    private static final int[][] INITIAL_POSITIONS = { {1,0}, {3,2}, {5,0}, {7,2}, {9,0}};
    private GameEnvironment gameEnvironment;

    public TowerService(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.positions = new ArrayList<>();
        this.mainTowers = gameEnvironment.getTowerList();
        setInitialPositions();
    }
    private void setInitialPositions(){
        for (int i = 0; i < INITIAL_POSITIONS.length; i++){
            this.positions.add(INITIAL_POSITIONS[i]);
        }
    }
    public List<int[]> getPositions(){
        return positions;
    }
    public List<Tower> getMainTowers(){
        return mainTowers;
    }
    public List<Tower> getReserveTowers(){
        return reserveTowers;
    }
    private int[] getNextAvailablePosition() {
        for (int i = 0; i < INITIAL_POSITIONS.length; i++) {
            if (!positions.contains(INITIAL_POSITIONS[i])) {
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

}
