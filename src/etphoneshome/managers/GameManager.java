package etphoneshome.managers;

import etphoneshome.UILauncher;
import etphoneshome.entities.actor.Actor;
import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.entities.enemies.Scientist;
import etphoneshome.graphics.GraphicsRepainter;
import etphoneshome.objects.*;
import etphoneshome.sound.Sound;

import java.awt.Toolkit;
import java.util.List;
import java.util.Random;


/**
 * Class in charge of all checks within the game, checkpoints, R+P pickups, enemy kills, character damage, etc.
 */
public class GameManager {

	private int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
    /*
     * sets the things needed for the checks
     */
    private final GraphicsRepainter graphicsRepainter;
    private final EntityManager entityManager;
    private final CollectiblesManager collectiblesManager;
    private final LevelManager levelManager;
    private final FlaskManager flaskManager;
    private final ObstacleManager obstacleManager;
    private final AnimationManager animationManager;

    /**
     * character of {@code GameManager}
     */
    private Character character;

    /**
     * boolean of is the game over
     */
    private boolean gameOver = false;

    /**
     * Sound responsible for sound in the game
     */
    private Sound sound = new Sound();

    /**
     * Creates a new GameManager
     *
     * @param graphicsRepainter GraphicsRepainter
     * @param entityManager EntityManager
     * @param collectiblesManager CollectiblesManager
     * @param levelManager levelManager
     * @param flaskManager FlaskManager
     * @param obstacleManager ObstacleManager
     * @param animationManager AnimationManager
     * @param character Character
     */
    public GameManager(GraphicsRepainter graphicsRepainter, EntityManager entityManager, CollectiblesManager collectiblesManager, LevelManager levelManager, FlaskManager flaskManager, ObstacleManager obstacleManager, AnimationManager animationManager, Character character) {
        this.graphicsRepainter = graphicsRepainter;
        this.entityManager = entityManager;
        this.collectiblesManager = collectiblesManager;
        this.levelManager = levelManager;
        this.flaskManager = flaskManager;
        this.obstacleManager = obstacleManager;
        this.animationManager = animationManager;
        this.character = character;
    }

    /**
     * sets the character of {@code GameManager}
     *
     * @param character associated with {@code GameManager}
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    /**
     * Returns whether the character was hurt or not
     *
     * @return true if the character was hurt, else false
     */
    public boolean wasCharacterHurt() {
        if (!character.isInvincible()) {
            Hitbox characterHitbox = character.getHitbox();

            for (Enemy enemy : this.entityManager.getEnemyList()) {
                if (!enemy.getIsDead()) {
                    Hitbox enemyHitbox = enemy.getHitbox();
                    boolean areColliding = characterHitbox.areColliding(enemyHitbox);
                    if (areColliding) {
                        return areColliding;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks to see if an enemy was killed and if killed adds score, plays sound and bounces off enemy
     * @param oldLocation location from where ET hit the enemy
     */
    public void runEnemyCheck(Location oldLocation) {
        Hitbox oldCharacterHitbox = character.getHitbox(), newCharacterHitbox = character.getHitbox();
        oldCharacterHitbox.setLocation(oldLocation);

        for (Enemy enemy : this.entityManager.getEnemyList()) {
            Hitbox enemyHitbox = enemy.getHitbox();
            if (oldCharacterHitbox.aboveOtherHitbox(enemyHitbox) && newCharacterHitbox.areColliding(enemyHitbox)) {
                if (!enemy.getIsDead()) {
                    character.addScore(100);
                    sound.playEnemyDeath();
                }
                this.animationManager.addEnemyDeathAnimation(enemy);
                enemy.setIsDead(true);
                character.getVelocity().setVerticalVelocity(-15);
            }
        }
    }

    /**
     * Returns {@code gameOver} variable
     *
     * @return {@code gameOver}
     */
    public boolean getGameOver() {
        return this.gameOver;
    }

    /**
     * Updates the {@code gameOver} variable
     *
     * @param gameOver New value for {@code gameOver}
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Calculate ground level based on actor height and window height
     *
     * @param actor Which actor to calculate ground level for
     * @return Returns the ground level of the game
     */
    public int getGroundLevel(Actor actor) {
        return this.HEIGHT - 100 - (int) actor.getRightEntitySprite().getHeight();
    }

    /**
     * gets the centre of the character
     *
     * @return centre xcord of the character
     */
    public int getCenterXCord() {
        return this.graphicsRepainter.WIDTH / 2 - (int) this.character.getRightEntitySprite().getWidth() / 2;
    }

    /**
     * Check to see if character is on ground, if so, set character to exact ground height, set vertical velocity to 0 and set jumping boolean to false
     *
     * @param character {@code Character}
     * @param velocity  {@code Velocity} of character
     */
    public void runGroundCheck(Character character, Velocity velocity) {
        if (this.onGround() && character.isJumping()) {
            character.setJumping(false);
            velocity.setVerticalVelocity(0);
        }
    }

    /**
     * Checks if any collectibles were picked up and adds score if they were ReesesPieces 
     * and adds phone piece to the collected phone pieces
     */
    public void runCollectibleCheck() {

        //gets position and hitbox of the character
        int height = (int) this.character.getRightEntitySprite().getHeight();
        int width = (int) this.character.getRightEntitySprite().getWidth();
        Hitbox ET = new Hitbox(this.character.getLocation(), height, width);

        //iterates through the list of collectibles
        List<Collectible> collectibleList = this.collectiblesManager.getCollectiblesList();
        for (int i = collectibleList.size() - 1; i >= 0; i--) {
        	//checks if they collide
            Collectible collectible = collectibleList.get(i);
            int colHeight = (int) collectible.getHeight();
            int colWidth = (int) collectible.getWidth();
            Hitbox col = new Hitbox(collectible.getLocation(), colWidth, colHeight);
            if (ET.areColliding(col)) {
            	
            	//Resses Pieces collection
                if (collectible instanceof ReesesPieces) {
                    character.addScore(100);
                    sound.playReese();
                  
                    //phone piece collection
                } else if (collectible instanceof PhonePiece) {
                    this.levelManager.addCollectedPhonePiece(((PhonePiece) collectible).getPhonePieceType());
                    sound.playPhone();
                }
                this.collectiblesManager.removeCollectible(collectible);
            }
        }
    }

    /**Checks to see if Character was hit by a flask
     * @return true if hit by flask else return false
     */
    public boolean runFlasksCheck() {
        if (!this.character.isInvincible()) {

            //gets position and hitbox of ET
            Hitbox ET = character.getHitbox();

            //iterates through list of Flasks
            List<Flask> flaskList = this.flaskManager.getFlaskList();
            for (int i = flaskList.size() - 1; i >= 0; i--) {
                Flask flask = flaskList.get(i);
                Hitbox flaskHit = flask.getHitbox();
                if (flask.getLocation().getYcord() >= getGroundLevel(this.character)) {
                    this.flaskManager.removeFlask(flask);
                }
                if (ET.areColliding(flaskHit)) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * moves the flasks according to their velocity
     */
    public void moveFlasks() {
        for (Flask flask : this.flaskManager.getFlaskList()) {
            Location old = flask.getLocation();
            Velocity vel = flask.getVelocity();
            flask.setLocation(new Location(old.getXcord() + (int) vel.getHorizontalVelocity(), old.getYcord() + (int) vel.getVerticalVelocity()));
        }
    }

    /**
     * Throws flasks if nearby and facing each other 
     */
    public void throwFlasks() {

        for (Enemy enemy : this.entityManager.getEnemyList()) {
            if (enemy instanceof Scientist) {
                if (!enemy.getIsDead()) {
                    Scientist scientist = (Scientist) enemy;
                    if (!scientist.getThrownFlask()) {
                        if (character.getLocation().getDistance(enemy.getLocation()) < 600) {

                            //if enemy is to the right and facing character
                            if (character.getLocation().getXcord() < enemy.getLocation().getXcord()) {
                                if (!enemy.isFacingRight()) {
                                    Location newLoc = new Location(enemy.getLocation().getXcord(), enemy.getLocation().getYcord() - 10);
                                    Flask flask = new Flask(newLoc, new Velocity(-5, -10));
                                    this.flaskManager.addFlask(scientist, flask);
                                    scientist.setThrownFlask(true);
                                }
                            //if enemy is to the left and facing character
                            } else if (character.getLocation().getXcord() > enemy.getLocation().getXcord()) {
                                if (enemy.isFacingRight()) {
                                    Location newLoc = new Location((int) (enemy.getLocation().getXcord() + enemy.getRightEntitySprite().getWidth()), enemy.getLocation().getYcord() - 10);
                                    Flask flask = new Flask(newLoc, new Velocity(5, -10));
                                    this.flaskManager.addFlask(scientist, flask);
                                    scientist.setThrownFlask(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * moves, throws and checks if character was hit by a flask
     * @return true if the player was hit by a flask, and false otherwise
     */
    public boolean checkFlasks() {
        this.moveFlasks();
        this.throwFlasks();
        return this.runFlasksCheck();
    }


    /**
     * checks if character hit an obstacle
     *
     * @param character   character of {@code GameManager}
     * @param oldLocation old location of Character
     * @param newLocation new location of character
     * @return Direction of obstacle
     */
    public Direction runObstacleCollisionCheck(Character character, Location oldLocation, Location newLocation) {

        //sets initial values
        int height = (int) character.getRightEntitySprite().getHeight();
        int width = (int) character.getRightEntitySprite().getWidth();
        Hitbox oldCharacterHitbox = new Hitbox(oldLocation, height, width);
        Hitbox newCharacterHitbox = new Hitbox(newLocation, height, width);

        //iterates through the list of obstacles
        for (Obstacle obstacle : this.obstacleManager.getObstacleList()) {
            Hitbox obstacleHitbox = obstacle.getHitbox();

            //if they're colliding
            if (newCharacterHitbox.areColliding(obstacleHitbox)) {
                if (oldCharacterHitbox.toTheLeftOfOtherHitbox(obstacleHitbox)) {
                    int newX = obstacleHitbox.getTopLeftCorner().getXcord() - width - 1;
                    int newY = oldLocation.getYcord() + (int) character.getVelocity().getVerticalVelocity();
                    character.setLocation(new Location(newX, newY));
                    return Direction.LEFT_OF;

                }

                // if to the right of platform
                if (oldCharacterHitbox.toTheRightOfOtherHitbox(obstacleHitbox)) {
                    int newX = obstacleHitbox.getTopLeftCorner().getXcord() + obstacleHitbox.getWidth() + 1;
                    if (obstacle instanceof Platform) {
                        Platform platform = (Platform) obstacle;
                        int xCord = platform.getLocation().getXcord();
                        xCord += (60 * platform.getLength());
                        newX = xCord + 1;
                    }
                    int newY = oldLocation.getYcord() + (int) character.getVelocity().getVerticalVelocity();
                    character.setLocation(new Location(newX, newY));
                    return Direction.RIGHT_OF;
                }

                // if below the obstacle
                if (oldCharacterHitbox.belowOtherHitbox(obstacleHitbox)) {
                    character.getVelocity().setVerticalVelocity(0);
                    int newX = oldLocation.getXcord() + (int) character.getVelocity().getHorizontalVelocity();
                    int newY = obstacleHitbox.getTopLeftCorner().getYcord() + obstacleHitbox.getHeight();
                    character.setLocation(new Location(newX, newY));
                    return Direction.BELOW;
                }

                //if ontop the obstacle
                if (oldCharacterHitbox.aboveOtherHitbox(obstacleHitbox)) {
                    int newX = oldLocation.getXcord() + (int) character.getVelocity().getHorizontalVelocity();
                    int newY = obstacleHitbox.getTopLeftCorner().getYcord() - height - 1;
                    character.setLocation(new Location(newX, newY));
                    return Direction.ABOVE;
                }
            }
        }
        return null;
    }

    /**
     * Checks if player is on the ground or not
     *
     * @return whether the player is on the ground or not
     */
    public boolean onGround() {

        //return true if on ground level
        if (this.character.getLocation().getYcord() >= this.getGroundLevel(this.character)) {
            this.character.setLocation(new Location(this.character.getLocation().getXcord(), this.getGroundLevel(this.character)));
            return true;
        } else {
            int height = (int) this.character.getRightEntitySprite().getHeight();
            int width = (int) this.character.getRightEntitySprite().getWidth();
            Hitbox testCharacterHitbox = new Hitbox(new Location(this.character.getLocation().getXcord(), this.character.getLocation().getYcord() + 3), height, width);
            for (Obstacle obstacle : UILauncher.getObstacleManager().getObstacleList()) {
                if (obstacle instanceof Platform) {
                    Platform platform = (Platform) obstacle;
                    if (testCharacterHitbox.areColliding(platform.getHitbox())) {
                        this.character.setOnPlatform(true);
                        return true;
                    }
                } else {
                    if (testCharacterHitbox.areColliding(obstacle.getHitbox())) {
                        this.character.setOnPlatform(true);
                        return true;
                    }
                }
            }
        }

        if (this.character.isOnPlatform()) {
            this.character.setOnPlatform(false);
            this.character.setJumping(true);
        }
        return false;
    }

    /**
     * Checks to see if enemy will change direction
     *
     * @return %1 chance of changing Direction
     */
    public boolean changeDirection() {
        Random raw = new Random();
        int theOne = raw.nextInt(100) + 1;
        if (theOne <= 1) {
            return true;
        }
        return false;
    }

    /**
     * Moves enemy
     */
    public void moveEnemy() {
        for (Enemy enemy : this.entityManager.getEnemyList()) {
            if (!enemy.getIsDead()) {
                Location old = enemy.getLocation();
                Velocity vel = enemy.getVelocity();
                Platform standingOnPlatform = enemy.getStandingOnPlatform();
                if (vel.getHorizontalVelocity() == 0) {
                    vel.setHorizontalVelocity(-4);
                    enemy.setFacingRight(false);
                }
                boolean x = changeDirection();
                if (x == true) {
                    if (enemy.isFacingRight()) {
                        vel.setHorizontalVelocity(-4);
                        enemy.setFacingRight(false);
                    } else {
                        vel.setHorizontalVelocity(4);
                        enemy.setFacingRight(true);
                    }
                }
                Location newLocation = new Location(old.getXcord() + (int) vel.getHorizontalVelocity(), old.getYcord() + (int) vel.getVerticalVelocity());
                //check to see if on platform
                if (standingOnPlatform != null) {
                    if (!(newLocation.getXcord() > standingOnPlatform.getLocation().getXcord() && newLocation.getXcord() + enemy.getRightEntitySprite().getWidth() < standingOnPlatform.getLocation().getXcord() + standingOnPlatform.getRealLength() && newLocation.getYcord() + (int) enemy.getRightEntitySprite().getHeight() == standingOnPlatform.getLocation().getYcord())) {
                        if (enemy.isFacingRight()) {
                            vel.setHorizontalVelocity(-4);
                            enemy.setFacingRight(false);
                        } else {
                            enemy.setFacingRight(true);
                            vel.setHorizontalVelocity(4);
                        }
                        newLocation = new Location(old.getXcord() + (int) vel.getHorizontalVelocity(), old.getYcord() + (int) vel.getVerticalVelocity());
                    }
                }

                enemy.setLocation(newLocation);
            }
        }
    }

    public static void main(String args[]) {
        Character character = new ET();
        GraphicsRepainter graphicsRepainter = new GraphicsRepainter();
        FlaskManager flaskManager = new FlaskManager();
        LevelManager levelManager = new LevelManager();
        EntityManager entityManager = new EntityManager();
        ObstacleManager obstacleManager = new ObstacleManager();
        CollectiblesManager collectiblesManager = new CollectiblesManager(obstacleManager);
        AnimationManager animationManager = new AnimationManager(entityManager);
        GameManager gameManager = new GameManager(graphicsRepainter, entityManager, collectiblesManager, levelManager, flaskManager, obstacleManager, animationManager, character);

        System.out.println("Testing out wasCharacterHurt");

        // should be false since no entities have spawned yet
        System.out.println("Was character hurt? " + (gameManager.wasCharacterHurt())); // false = correct

        entityManager.spawnRandomEntities(5);
        // set character location to same as an enemy so that we can test if character is hurt
        character.setLocation(entityManager.getEnemyList().get(0).getLocation());
        System.out.println("Was character hurt? " + (gameManager.wasCharacterHurt())); // true = correct

        System.out.println("Is game over? " + (gameManager.getGameOver())); // false = correct

        System.out.println("Setting gameOver to true...");
        gameManager.setGameOver(true);
        System.out.println("Is game over? " + (gameManager.getGameOver())); // true = correct
    }


}
