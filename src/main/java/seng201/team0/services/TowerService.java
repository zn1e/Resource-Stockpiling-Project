package seng201.team0.services;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;

public class TowerService {
    private List<Tower> towers;
    private List<int[]> positions;
    private static final int[][] INITIAL_POSITIONS = { {0,1}, {2,3}, {0,5}, {2,7}, {0,9}};
    private GameEnvironment gameEnvironment;

    public TowerService(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.positions = new ArrayList<>();
        this.towers = gameEnvironment.getTowerList();
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
    private int[] getNextAvailablePosition() {
        for (int i = 0; i < INITIAL_POSITIONS.length; i++) {
            if (!positions.contains(INITIAL_POSITIONS[i])) {
                return INITIAL_POSITIONS[i];
            }
        }
        return null;
    }
}
