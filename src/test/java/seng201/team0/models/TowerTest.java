package seng201.team0.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameEnvironment;

import static org.junit.jupiter.api.Assertions.*;

class TowerTest {

    private Tower tower;
    private GameEnvironment gameEnvironment;

    @BeforeEach
    void setUp() {
        gameEnvironment = new GameEnvironment(
                gameEnv -> {},
                gameEnv -> {},
                gameEnv -> {},
                gameEnv -> {},
                gameEnv -> {},
                gameEnv -> {},
                gameEnv -> {},
                () -> {}
        );
        tower = new Tower("Test Tower", "Gold", gameEnvironment.loadImage("images/image.png"), "A powerful tower");
    }

    @Test
    void levelUp() {
        int initialLevel = tower.getLevel();
        int initialResourceAmount = tower.getResourceAmount();
        tower.levelUp();
        assertEquals(initialLevel + 1, tower.getLevel());
        assertEquals(initialResourceAmount + 500, tower.getResourceAmount());
    }
}
