package seng201.team0;

import javafx.scene.image.Image;
import seng201.team0.models.Tower;
import seng201.team0.models.Item;


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
    private int goldGained;
    private int pointsGained;
    private int numberOfRounds; // number of rounds of game
    private int completedRounds;
    private int currentRound = 1;
    private boolean victoryFlag;
    private String roundDifficulty; // difficulty of round
    private List<Tower> towerList; // list of towers
    private final List<Tower> defaultTowers = new ArrayList<>(); // initializes an array list for default towers
    private final List<Item> defaultItems = new ArrayList<>(); // initializes an array list for default items
    private final Consumer<GameEnvironment> setupScreenLauncher1; // launch the name selection screen
    private final Consumer<GameEnvironment> setupScreenLauncher2; // launch the number of round and difficulty selection screen
    private final Consumer<GameEnvironment> setupScreenLauncher3; // launch the tower selection screen
    private final Consumer<GameEnvironment> mainScreenLauncher; // launch the main screen
    private final Consumer<GameEnvironment> shopScreenLauncher; // launch shop screen
    private final Consumer<GameEnvironment> inventoryScreenLauncher; //launch inventory screen
    private final Consumer<GameEnvironment> endScreenLauncher;
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
    public GameEnvironment(Consumer<GameEnvironment> setupScreenLauncher1,
                           Consumer<GameEnvironment> setupScreenLauncher2,
                           Consumer<GameEnvironment> setupScreenLauncher3,
                           Consumer<GameEnvironment> mainScreenLauncher,
                           Consumer<GameEnvironment> shopScreenLauncher,
                           Runnable clearScreen,
                           Consumer<GameEnvironment> inventoryScreenLauncher,
                           Consumer<GameEnvironment> endScreenLauncher){
        this.setupScreenLauncher1 = setupScreenLauncher1;
        this.setupScreenLauncher2 = setupScreenLauncher2;
        this.setupScreenLauncher3 = setupScreenLauncher3;
        this.mainScreenLauncher = mainScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.clearScreen = clearScreen;
        this.endScreenLauncher = endScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        loadDefaultTowers();
        loadDefaultItems();
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
    public void addPlayerGold(int gold){
        playerGold += gold;
        goldGained += gold;
    }
    public void addPlayerPoints(int points){
        playerPoints += points;
        pointsGained += points;
    }
    public int getGoldGained(){
        return goldGained;
    }
    public int getPointsGained(){
        return pointsGained;
    }

    public void setPlayerPoints(int playerPoints){
        this.playerPoints = playerPoints;
    }
    public int getCurrentRound(){
        return currentRound;
    }
    public int getCompletedRounds(){
        return completedRounds;
    }
    public void incrementCurrentRound(){
        currentRound++;
    }
    /**
     * Sets the number of rounds of the game.
     * @param numberOfRounds An int representing the number of rounds of the game.
     */
    public void setNumberOfRounds(int numberOfRounds){
        this.numberOfRounds = numberOfRounds;
    }
    public void incrementCompletedRounds(){
        completedRounds++;
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

    public List<Item> getDefaultItems() {
        return defaultItems;
    }
    public void setVictoryFlag(boolean flag){
        this.victoryFlag = flag;
    }
    public boolean getVictoryFlag(){
        return victoryFlag;
    }

    private void loadDefaultTowers() {
        defaultTowers.add(new Tower("Eye Tower", "eye", loadImage("images/eye_tower.png")));
        defaultTowers.add(new Tower("Brain Tower", "brain", loadImage("images/brain_tower.png")));
        defaultTowers.add(new Tower("Heart Tower", "heart", loadImage("images/heart_tower.png")));
        defaultTowers.add(new Tower("Liver Tower", "liver", loadImage("images/liver_tower.png")));
        defaultTowers.add(new Tower("Kidney Tower", "kidney", loadImage("images/kidney_tower.png")));
    }
    private void loadDefaultItems() {
        defaultItems.add(new Item("Upgrade", 50, loadImage("images/upgrade_item.png")));
        defaultItems.add(new  Item("Swap type", 50, loadImage("images/swap_item.png")));
        defaultItems.add(new Item("Repair", 50, loadImage("images/repair_item.png")));
    }

    private Image loadImage(String path) {
        return new Image(getClass().getResourceAsStream("/" + path));
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
        clearScreen.run();
        mainScreenLauncher.accept(this);
    }

    public void launchShopScreen(){
        clearScreen.run();
        shopScreenLauncher.accept(this);
    }
    public void launchEndScreen(){
        clearScreen.run();
        endScreenLauncher.accept(this);
    }

    /**
     * Closes the main screen and exit the system.
     */
    public void closeScreen(){
        System.exit(0);
    }

    public void launchInventoryScreen(){
        clearScreen.run();
        inventoryScreenLauncher.accept(this);
    }

}
