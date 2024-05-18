package seng201.team0;

import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * GameEnvironment class represents the environment setup for the game.
 * It includes methods to launch setup screen, main screen, and clear screen.
 */
public class GameEnvironment {
    private String playerName; // name of the player
    private int playerGold = 50;
    private int playerPoints;
    private int numberOfRounds; // number of rounds of game
    private int currentRound = 1;
    private String roundDifficulty; // difficulty of round
    private List<Tower> towerList; // list of towers
    private final List<Tower> defaultTowers = new ArrayList<>(); // initializes an array list for default towers
    private final Consumer<GameEnvironment> setupScreenLauncher1; // launch the name selection screen
    private final Consumer<GameEnvironment> setupScreenLauncher2; // launch the number of round and difficulty selection screen
    private final Consumer<GameEnvironment> setupScreenLauncher3; // launch the tower selection screen
    private final Consumer<GameEnvironment> mainScreenLauncher; // launch the main screen
    private final Runnable clearScreen; // clear the screen

    /**
     * Constructor for Game Environment class.
     * Initializes the setup screens launcher, main screen launcher, and clear screen functionality.
     * Also, initializes the list of default towers with unique resource type, and default base stats.
     * Launches the name selection screen.
     * @param setupScreenLauncher1 Consumer function to launch name selection screen.
     * @param setupScreenLauncher2 Consumer function to launch number of round and difficulty selection screen.
     * @param setupScreenLauncher3 Consumer function to launch tower selection screen.
     * @param mainScreenLauncher Consumer function to launch main screen.
     * @param clearScreen Runnable to clear the screen.
     *
     */
    public GameEnvironment(Consumer<GameEnvironment> setupScreenLauncher1, Consumer<GameEnvironment> setupScreenLauncher2,
                           Consumer<GameEnvironment> setupScreenLauncher3, Consumer<GameEnvironment> mainScreenLauncher,
                           Runnable clearScreen){
        this.setupScreenLauncher1 = setupScreenLauncher1;
        this.setupScreenLauncher2 = setupScreenLauncher2;
        this.setupScreenLauncher3 = setupScreenLauncher3;
        this.mainScreenLauncher = mainScreenLauncher;
        this.clearScreen = clearScreen;
        defaultTowers.addAll(List.of(new Tower("Tower 1", "eye"), new Tower("Tower 2", "brain"),
                new Tower("Tower 3", "heart"), new Tower("Tower 4", "liver"),
                new Tower("Tower 5", "kidney")));
        launchSetupScreen1();
    }

    /**
     * Method for getting the player name.
     * @return A string representing the player name.
     */
    public String getPlayerName(){
        return playerName;
    }

    /**
     * Sets the new name of player.
     * @param name A string representing the new player name.
     */
    public void setPlayerName(String name){
        this.playerName = name;
    }
    public int getPlayerGold(){
        return playerGold;
    }
    public void setPlayerGold(int playerGold){
        this.playerGold = playerGold;
    }
    public int getPlayerPoints(){
        return playerPoints;
    }
    public void setPlayerPoints(int playerPoints){
        this.playerPoints = playerPoints;
    }
    public int getCurrentRound(){
        return currentRound;
    }
    /**
     * Sets the number of rounds of the game.
     * @param numberOfRounds An int representing the number of rounds of the game.
     */
    public void setNumberOfRounds(int numberOfRounds){
        this.numberOfRounds = numberOfRounds;
    }
    public int getNumberOfRounds(){
        return numberOfRounds;
    }
    /**
     * Sets the round difficulty.
     * @param roundDifficulty A string representing the round difficulty.
     */
    public void setRoundDifficulty(String roundDifficulty){
        this.roundDifficulty = roundDifficulty;
    }
    public String getRoundDifficulty(){
        return roundDifficulty;
    }
    /**
     * Retrieve the list of towers.
     * @return A list of towers.
     */
    public List<Tower> getTowerList(){
        return towerList;
    }

    /**
     * Sets the new list of towers.
     * @param towerList A list containing the newly added towers.
     */
    public void setTowerList(List<Tower> towerList){
        this.towerList = towerList;
    }

    /**
     * Retrieve the list of default towers.
     * @return A list containing the default towers.
     */
    public List<Tower> getDefaultTowers(){
        return defaultTowers;
    }

    /**
     * Launches the name selection screen.
     */
    public void launchSetupScreen1(){
        setupScreenLauncher1.accept(this);
    }

    /**
     * Clears the screen then launches the number of round and difficulty selection screen.
     */
    public void launchSetupScreen2(){
        clearScreen.run();
        setupScreenLauncher2.accept(this);
    }

    /**
     * Clears the screen then launches the tower selection screen.
     */
    public void launchSetupScreen3(){
        clearScreen.run();
        setupScreenLauncher3.accept(this);
    }

    /**
     * Closes the setup screen.
     */
    public void closeSetupScreen(){
        clearScreen.run();
        launchMainScreen();
    }

    /**
     * Launches the main screen.
    **/
    public void launchMainScreen(){
        mainScreenLauncher.accept(this);
    }


    /**
     * Closes the main screen and exit the system.
     */
    public void closeMainScreen(){
        System.exit(0);
    }

}
