package etphoneshome.managers;

import etphoneshome.UILauncher;
import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.graphics.GraphicsRepainter;
import etphoneshome.listeners.InputListener;
import etphoneshome.objects.Collectible;
import etphoneshome.objects.PhonePiece;
import etphoneshome.objects.ReesesPieces;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Class in charge of all checks within the game, checkpoints, R+P pickups, enemy kills, character damage, etc.
 */
public class GameManager {

    /**
     * Objects needed to run the checks on the game
     */
    private final InputListener inputListener;
    private final GraphicsRepainter graphicsRepainter;
    private final EntityManager entityManager;
    private Character character;
    private CollectiblesManager collectiblesManager;
    private LevelManager levelManager;

    /**
     * boolean that sets the status of the game
     */
    private boolean gameOver = false;

    /**
     * Constructor that sets all the instance variables needed to run the checks
     *
     * @param inputListener       needed to run the next turn of the game
     * @param graphicsRepainter   to print the new state of the game
     * @param entityManager       to get the list of enemies and update it
     * @param character           character of the {@code GameManager}
     * @param collectiblesManager to get the list of collectibles and update it
     * @param levelManager to get the levels
     */
    public GameManager(InputListener inputListener, GraphicsRepainter graphicsRepainter, EntityManager entityManager, Character character, CollectiblesManager collectiblesManager, LevelManager levelManager) {
        this.inputListener = inputListener;
        this.graphicsRepainter = graphicsRepainter;
        this.entityManager = entityManager;
        this.character = character;
        this.collectiblesManager = collectiblesManager;
        this.levelManager = levelManager;
    }


    /**
     * Invokes the {@code processInput} method from the {@code InputListener} class and checks if the character is hurt.
     * If the character is hurt then it is set to dead and {@code gameOver} is set to true.
     * Checks to see if any collectibles were picked and then checks if user won the game
     * Next the game state is printed. If the User won the game then sets {@code gameOver} to true.
     */
    public void nextTurn() {

        //user input and check if character is dead
        this.inputListener.processInput();
        if (this.wasCharacterHurt()) {
            UILauncher.getSound().playETDeath();
            character.setIsDead(true);
            this.setGameOver(true);
            System.out.println("Gameover, you died!");
        }

        if (!this.gameOver) {
            //checks if any collectibles were picked up
            this.runCollectibleCheck();
            this.graphicsRepainter.printState();

            //checks if character won the game
            if (character.getCanWin() && character.getLocation().getXcord() == 30) {
                this.setGameOver(true);
                UILauncher.getSound().playWin();
                System.out.println("Congratulations you won!!!!!");
                HighScoreManager highscore = new HighScoreManager(character);
                highscore.showHighscores();
                boolean validmove = false;
                while (validmove != true) {
                    Scanner reader = new Scanner(System.in);
                    try {
                        System.out.print("Would you like to play again? (Y/N): ");
                        String choice = reader.next();
                        if (choice.length() > 1) {
                            System.out.println("Please choose (Y/N)");
                            continue;
                        }
                        if (choice.toUpperCase().equals("Y")) {
                            validmove = true;
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            this.gameOver = false;
                            levelManager.unloadLevel();
                            levelManager.loadLevel(0);

                        } else if (choice.toUpperCase().equals("N")) {
                            validmove = true;
                            System.out.println("Thanks for playing!");
                            System.exit(0);
                        } else {
                            System.out.println("Please choose (Y/N)");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter 'a' for left, 'w' for up, 's' for down and 'd' for right ");
                        reader.next();
                    }
                }
            }
        } else {
            boolean validmove = false;
            while (validmove != true) {
                Scanner reader = new Scanner(System.in);
                try {
                    System.out.print("Would you like to play again? (Y/N): ");
                    String choice = reader.next();
                    if (choice.length() > 1) {
                        System.out.println("Please choose (Y/N)");
                        continue;
                    }
                    if (choice.toUpperCase().equals("Y")) {
                        validmove = true;
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        this.gameOver = false;
                        this.levelManager.unloadLevel();
                        this.levelManager.loadLevel(0);
                    } else if (choice.toUpperCase().equals("N")) {
                        validmove = true;
                        System.out.println("Thanks for playing!");
                        System.exit(0);
                    } else {
                        System.out.println("Please choose (Y/N)");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter 'a' for left, 'w' for up, 's' for down and 'd' for right ");
                    reader.next();
                }
            }
        }
    }

    /**
     * Returns whether the character was hurt or not
     *
     * @return true if the character was hurt, else false
     */
    public boolean wasCharacterHurt() {
        for (Enemy enemy : this.entityManager.getEnemyList()) {
            if (enemy.getLocation().getDistance(character.getLocation()) == 0) {
                return true;
            }
        }
        return false;
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
     * Checks if any {@code Collectibles} were picked up using {@code CollectibleManager} to get the list of Collectibles
     * If {@code ReesesPieces} was picked up then add 100 {@code Score} to {@code Character}
     * if {@code PhonePiece} was picked up then set {@code canWin} of {@code Character} to true
     */
    public void runCollectibleCheck() {
        List<Collectible> collectibleList = collectiblesManager.getCollectiblesList();

        //loop through all the collectibles inthe list
        for (int i = collectibleList.size() - 1; i >= 0; i--) {
            Collectible collectible = collectibleList.get(i);

            //check for ReesesPieces
            if (collectible instanceof ReesesPieces) {
                if (collectible.getLocation().getDistance(character.getLocation()) == 0) {
                    character.addScore(100);
                    System.out.println("ReesesPieces picked up!!! Your score is: " + character.getScore());
                    collectiblesManager.removeCollectible(collectible);
                    UILauncher.getSound().playReese();
                }
            }
            //check for PhonePiece
            else if (collectible instanceof PhonePiece) {
                if (collectible.getLocation().getDistance(character.getLocation()) == 0) {
                    character.setCanWin(true);
                    System.out.println("Phone piece collected you can win!!");
                    collectiblesManager.removeCollectible(collectible);
                    UILauncher.getSound().playPhonePickUp();
                }
            }
        }
    }

    public static void main(String args[]) {
        Character character = new ET();
        ObstacleManager obstacleManager = new ObstacleManager();
        EntityManager entityManager = new EntityManager(obstacleManager, character);
        InputListener inputListener = new InputListener(character, entityManager, obstacleManager);
        CollectiblesManager collectiblesManager = new CollectiblesManager(obstacleManager);
        GraphicsRepainter graphicsRepainter = new GraphicsRepainter(character, entityManager, collectiblesManager, obstacleManager);
        LevelManager levelManager = new LevelManager(obstacleManager, entityManager, collectiblesManager)
;
        GameManager gameManager = new GameManager(inputListener, graphicsRepainter, entityManager, character, collectiblesManager , levelManager);

        System.out.println("Testing out wasCharacterHurt");

        // should be false since no entities have spawned yet
        System.out.println("Was character hurt? " + (gameManager.wasCharacterHurt())); // false = correct

        entityManager.spawnRandomEntities(5);
        // set character location to same as an enemy so that we can test if character is hurt
        character.setLocation(entityManager.getEnemyList().get(0).getLocation().clone());
        System.out.println("Was character hurt? " + (gameManager.wasCharacterHurt())); // true = correct

        System.out.println("Is game over? " + (gameManager.getGameOver())); // false = correct

        System.out.println("Setting gameOver to true...");
        gameManager.setGameOver(true);
        System.out.println("Is game over? " + (gameManager.getGameOver())); // true = correct

        System.out.println("Testing out nextTurn method");
        gameManager.nextTurn();
    }


}
