package etphoneshome;

import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.graphics.GraphicsRepainter;
import etphoneshome.listeners.InputListener;
import etphoneshome.managers.*;
import etphoneshome.objects.Level;


/////////////////////////////////////////////////////////////////////////////////////////////////
//TODO
//MORE EFFICIENT, LESS LAG, MOSTLY FIXING COLLISIONS CHECK HITBOX CLASS
//ADAPT GAME TO ALL SCREEN SIZES
//BETTER LEVEL LAYOUT, NOT JUST BEING ABLE TO WALK ON THE FLOOR TO BEAT LEVEL EASILY 
//MORE LEVELS
//FIX COPYRIGHT ISSUES 
/////////////////////////////////////////////////////////////////////////////////////////////////

public class UILauncher {

    /**
     * character the user will be playing as
     */
    private static Character character;

    /**
     * Variable where the Entities are store
     */
    private static EntityManager entityManager;

    /**
     * Variable that will lets us handle events
     */
    private static InputListener inputListener;

    /**
     * Class that will lets us draw on the canvas
     */
    private static GraphicsRepainter graphicsRepainter;

    /**
     * Handles most checks on the game
     */
    private static GameManager gameManager;

    /**
     * Manages background of the game
     */
    private static BackgroundManager backgroundManager;

    /**
     * Manages floor of the game
     */
    private static FloorManager floorManager;

    /**
     * Manages obstacles of the game
     */
    private static ObstacleManager obstacleManager;

    /**
     * Manages levels of the game
     */
    private static LevelManager levelManager;

    /**
     * Manages collectibles of the game
     */
    private static CollectiblesManager collectibleManager;

    /**
     * Manages the attacks of the Scientists
     */
    private static FlaskManager flaskManager;

    /**
     * Manages all game animations
     */
    private static AnimationManager animationManager;

    /**
     * Tracks whether game is in debug mode or not
     */
    private static boolean debugMode;

    public static void main(String[] args) {

        //Creates instances of all the objects needed
        UILauncher.character = new ET();
        UILauncher.entityManager = new EntityManager();
        UILauncher.obstacleManager = new ObstacleManager();
        UILauncher.collectibleManager = new CollectiblesManager(obstacleManager);
        UILauncher.levelManager = new LevelManager();
        UILauncher.flaskManager = new FlaskManager();
        UILauncher.animationManager = new AnimationManager(entityManager);
        UILauncher.graphicsRepainter = new GraphicsRepainter();
        UILauncher.backgroundManager = new BackgroundManager(graphicsRepainter);
        UILauncher.floorManager = new FloorManager(graphicsRepainter);
        UILauncher.gameManager = new GameManager(graphicsRepainter, entityManager, collectibleManager, levelManager, flaskManager, obstacleManager, animationManager, character);
        UILauncher.inputListener = new InputListener(character, backgroundManager, floorManager, gameManager, levelManager, flaskManager, animationManager);
        UILauncher.levelManager.addLevel(new Level("level-0"));
        UILauncher.levelManager.addLevel(new Level("level-1"));
        UILauncher.levelManager.addLevel(new Level("level-2"));
        UILauncher.levelManager.addLevel(new Level("level-3"));
        UILauncher.levelManager.loadLevel(1);

        //UILauncher.debugMode = true;

        //launches game
        UILauncher.graphicsRepainter.goLaunch(args);
    }

    /**
     * @return character returns the character the user is playing as (ET)
     */
    public static Character getCharacter() {
        return UILauncher.character;
    }

    /**
     * @param character sets the character that the user will be playing as
     */
    public static void setCharacter(Character character) {
        UILauncher.character = character;
        UILauncher.inputListener.setCharacter(character);
        UILauncher.gameManager.setCharacter(character);
    }

    /**
     * @return entityManager that holds all the entities associated with {@code UIlauncher}
     */
    public static EntityManager getEntityManager() {
        return UILauncher.entityManager;
    }

    /**
     * @return InputListener returns the event handler associated with {@code UILauncher}
     */
    public static InputListener getInputListener() {
        return UILauncher.inputListener;
    }

    /**
     * @return graphicsRepainter returns the drawer associated with {@code UILauncher}
     */
    public static GraphicsRepainter getGraphicsRepainter() {
        return UILauncher.graphicsRepainter;
    }

    /**
     * @return gameManager returns the game checker associated with {@code UILauncher}
     */
    public static GameManager getGameManager() {
        return UILauncher.gameManager;
    }

    /**
     * @return backgroundManager returns the background manager associated with {@code UILauncher}
     */
    public static BackgroundManager getBackgroundManager() {
        return UILauncher.backgroundManager;
    }

    /**
     * @return floorManager returns the floor manager associated with {@code UILauncher}
     */
    public static FloorManager getFloorManager() {
        return UILauncher.floorManager;
    }

    /**
     * @return backgroundManager returns the background manager associated with {@code UILauncher}
     */
    public static ObstacleManager getObstacleManager() {
        return UILauncher.obstacleManager;
    }

    /**
     * @return levelManager returns the level manager associated with {@code UILauncher}
     */
    public static LevelManager getLevelManager() {
        return UILauncher.levelManager;
    }

    /**
     * @return debugMode returns whether the program is in debug mode or not
     */
    public static boolean getDebugMode() {
        return UILauncher.debugMode;
    }

    /**
     * @return collectibleManager returns the collectibles of {@code UILauncher}
     */
    public static CollectiblesManager getCollectiblesManager() {
        return UILauncher.collectibleManager;
    }

    /**
     * @return flaskManager returns the flask manager associated with {@code UILauncher}
     */
    public static FlaskManager getFlaskManager() {
        return UILauncher.flaskManager;
    }

    /**
     * @return animationManager returns the animation manager associated with {@code UILauncher}
     */
    public static AnimationManager getAnimationManager() {
        return UILauncher.animationManager;
    }
}
