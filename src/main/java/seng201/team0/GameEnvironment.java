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
    private String name; // Name of game environment.
    private List<Tower> towerList; // List of towers.
    private final List<Tower> defaultTowers = new ArrayList<>(); // Initializes an array list for default towers.
    private final Consumer<GameEnvironment> setupScreenLauncher; // Launch the setup screen.
    private final Consumer<GameEnvironment> mainScreenLauncher; // Launch the main screen.
    private final Runnable clearScreen; // Clear the screen.

    /**
     * Constructor for Game Environment class.
     * Initializes the setup screen launcher, main screen launcher, and clear screen functionality.
     * Also, initializes the list of default towers with unique resource type, and default base stats.
     * @param setupScreenLauncher Consumer function to launch setup screen.
     * @param mainScreenLauncher Consumer function to launch main screen.
     * @param clearScreen Runnable to clear the screen.
     * It also launches the setup screen.
     */
    public GameEnvironment(Consumer<GameEnvironment> setupScreenLauncher, Consumer<GameEnvironment> mainScreenLauncher, Runnable clearScreen){
        this.setupScreenLauncher = setupScreenLauncher;
        this.mainScreenLauncher = mainScreenLauncher;
        this.clearScreen = clearScreen;
        defaultTowers.addAll(List.of(new Tower("Eye Tower", "eye"), new Tower("Brain Tower", "brain"),
                new Tower("Heart Tower", "heart"), new Tower("Kidney Tower", "kidney"),
                new Tower("Liver Tower", "liver")));
        launchSetupScreen();
    }

    /**
     * Method for getting the name of game environment.
     * @return A string representing the name of game environment.
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the new name of game environment.
     * @param name A string representing the new name of game environment.
     */
    public void setName(String name){
        this.name = name;
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
     * Launches the setup screen.
     */
    public void launchSetupScreen(){
        setupScreenLauncher.accept(this);
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
     */
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
