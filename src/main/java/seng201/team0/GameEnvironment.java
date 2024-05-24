package seng201.team0;

import javafx.scene.image.Image;
import seng201.team0.models.Tower;
import seng201.team0.models.Item;
import seng201.team0.services.InventoryService;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * GameEnvironment class represents the environment setup for the game.
 * It includes methods to launch setup screen, main screen, and clear screen.
 */
public class GameEnvironment {
    /**
     * The player name.
     */
    private String playerName;
    /**
     * Player gold default to 50.
     */
    private int playerGold = 50;
    /**
     * Player points.
     */
    private int playerPoints;
    /**
     * Gold gained by player.
     */
    private int goldGained;
    /**
     * Points gained by player.
     */
    private int pointsGained;
    /**
     * Number of rounds of game.
     */
    private int numberOfRounds;
    /**
     * Number of completed rounds.
     */
    private int completedRounds;
    /**
     * The current round default to 1.
     */
    private int currentRound = 1;
    /**
     * Flag indicating the result of game.
     */
    private boolean victoryFlag;
    /**
     * The difficulty of round.
     */
    private String roundDifficulty;
    /**
     * List of towers.
     */
    private List<Tower> towerList;
    /**
     * The inventory service instance.
     */
    private InventoryService inventoryService;
    /**
     * Initializes an array list for default towers.
     */
    private final List<Tower> defaultTowers = new ArrayList<>();
    /**
     * Initializes an array list for default items
     */
    private final List<Item> defaultItems = new ArrayList<>();
    /**
     * Launch the name selection screen.
     */
    private final Consumer<GameEnvironment> setupScreenLauncher1;
    /**
     * Launch the number of round and difficulty selection screen.
     */
    private final Consumer<GameEnvironment> setupScreenLauncher2;
    /**
     * Launch the tower selection screen.
     */
    private final Consumer<GameEnvironment> setupScreenLauncher3;
    /**
     * Launch the main screen
     */
    private final Consumer<GameEnvironment> mainScreenLauncher;
    /**
     * Launch shop screen.
     */
    private final Consumer<GameEnvironment> shopScreenLauncher;
    /**
     * Launch inventory screen.
     */
    private final Consumer<GameEnvironment> inventoryScreenLauncher;
    /**
     * Launch the end screen.
     */
    private final Consumer<GameEnvironment> endScreenLauncher;
    /**
     * Clears the screen.
     */
    private final Runnable clearScreen;
    /**
     * Constructor for Game Environment class.
     * Initializes the setup screens launcher, main screen launcher, and clear screen functionality.
     * Also, initializes the list of default towers with unique resource type, and default base stats.
     * Launches the name selection screen.
     * @param setupScreenLauncher1 Consumer function to launch name selection screen.
     * @param setupScreenLauncher2 Consumer function to launch number of round and difficulty selection screen.
     * @param setupScreenLauncher3 Consumer function to launch tower selection screen.
     * @param mainScreenLauncher Consumer function to launch main screen.
     * @param shopScreenLauncher Consumer function to launch shop screen.
     * @param endScreenLauncher Consumer function to launch end screen.
     * @param inventoryScreenLauncher Consumer function to launch inventory screen.
     * @param clearScreen Runnable to clear the screen.
     *
     */
    public GameEnvironment(Consumer<GameEnvironment> setupScreenLauncher1,
                           Consumer<GameEnvironment> setupScreenLauncher2,
                           Consumer<GameEnvironment> setupScreenLauncher3,
                           Consumer<GameEnvironment> mainScreenLauncher,
                           Consumer<GameEnvironment> shopScreenLauncher,
                           Consumer<GameEnvironment> endScreenLauncher,
                           Consumer<GameEnvironment> inventoryScreenLauncher,
                           Runnable clearScreen){
        this.setupScreenLauncher1 = setupScreenLauncher1;
        this.setupScreenLauncher2 = setupScreenLauncher2;
        this.setupScreenLauncher3 = setupScreenLauncher3;
        this.mainScreenLauncher = mainScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.clearScreen = clearScreen;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.endScreenLauncher = endScreenLauncher;
        loadDefaultTowers();
        loadDefaultItems();
        launchSetupScreen1();
        inventoryService = new InventoryService(this);
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

    /**
     * Gets the player gold.
     * @return An int having the value of player gold.
     */
    public int getPlayerGold(){
        return playerGold;
    }

    /**
     * Sets the player gold.
     * @param playerGold An int having the new value of player gold.
     */
    public void setPlayerGold(int playerGold){
        this.playerGold = playerGold;
    }

    /**
     * Gets the player points.
     * @return An int with the value of player points.
     */
    public int getPlayerPoints(){
        return playerPoints;
    }

    /**
     * Add player gold.
     * @param gold An int representing the value to be added on the player gold.
     */
    public void addPlayerGold(int gold){
        playerGold += gold;
        goldGained += gold;
    }

    /**
     * Add player points.
     * @param points An int representing the value to be added on the player points.
     */
    public void addPlayerPoints(int points){
        playerPoints += points;
        pointsGained += points;
    }

    /**
     * Gets the gold gained by player.
     * @return An int with value of player's gold gained.
     */
    public int getGoldGained(){
        return goldGained;
    }

    /**
     * Gets the points gained by the player.
     * @return An int with value of player's points gained.
     */
    public int getPointsGained(){
        return pointsGained;
    }

    /**
     * Gets the current round.
     * @return An int with value of current round.
     */
    public int getCurrentRound(){
        return currentRound;
    }

    /**
     * Gets the number of completed rounds.
     * @return An int with value of completed rounds.
     */
    public int getCompletedRounds(){
        return completedRounds;
    }

    /**
     * Increment the current round.
     */
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

    /**
     * Increment the number of completed rounds.
     */
    public void incrementCompletedRounds(){
        completedRounds++;
    }

    /**
     * Gets the number of rounds.
     * @return An int with value of number of rounds.
     */
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

    /**
     * Gets the round difficulty.
     * @return A string representing the round difficulty.
     */
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
     * Gets the list of default items.
     * @return List of default items.
     */
    public List<Item> getDefaultItems() {
        return defaultItems;
    }

    /**
     * Sets the result of the game.
     * @param flag Boolean representing the result of the game.
     */
    public void setVictoryFlag(boolean flag){
        this.victoryFlag = flag;
    }

    /**
     * Gets the result of the game.
     * @return A boolean representing the result of the game.
     */
    public boolean getVictoryFlag(){
        return victoryFlag;
    }

    /**
     * Load default towers.
     */
    private void loadDefaultTowers() {
        defaultTowers.add(new Tower("Eye Tower", "eye", loadImage("images/eye_tower.png")
        , "Fill carts with eyes!"));
        defaultTowers.add(new Tower("Brain Tower", "brain", loadImage("images/brain_tower.png"),
                "Fill carts with brains!"));
        defaultTowers.add(new Tower("Heart Tower", "heart", loadImage("images/heart_tower.png"),
                "Fill carts with hearts!"));
        defaultTowers.add(new Tower("Liver Tower", "liver", loadImage("images/liver_tower.png"),
                "Fill carts with liver!"));
        defaultTowers.add(new Tower("Kidney Tower", "kidney", loadImage("images/kidney_tower.png"),
                "Fill carts with kidneys!"));
    }

    /**
     * Load default items.
     */
    private void loadDefaultItems() {
        defaultItems.add(new Item("Upgrade Item", 50, loadImage("images/upgrade_item.png"),
                "Upgrades resource capacity"));
        defaultItems.add(new  Item("Points Item", 50, loadImage("images/Points.PNG"),
                "Up your points gain!"));
        defaultItems.add(new Item("Gold Item", 50, loadImage("images/Gold.PNG"),
                "Up your gold gain!"));
    }

    /**
     * Load images of tower.
     * @param path String representing the path of the image.
     * @return The image of tower.
     */
    public Image loadImage(String path) {
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

    /**
     * Launch the shop screen.
     */

    public void launchShopScreen(){
        clearScreen.run();
        shopScreenLauncher.accept(this);
    }

    /**
     * Launch the end screen.
     */
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

    /**
     * Clears screen and launches the inventory
     */
    public void launchInventoryScreen(){
        clearScreen.run();
        inventoryScreenLauncher.accept(this);
    }

    /**
     * Retrieves the instance of the InventoryService.
     * @return the instance of the InventoryService
     */
    public InventoryService getInventoryService() {
        return inventoryService;
    }

}
