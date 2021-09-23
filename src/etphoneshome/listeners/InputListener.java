package etphoneshome.listeners;

import etphoneshome.UILauncher;
import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.managers.EntityManager;
import etphoneshome.managers.ObstacleManager;
import etphoneshome.objects.Location;
import etphoneshome.objects.Obstacle;
import etphoneshome.objects.Platform;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class gets input from the user and updates the character based on that input
 * nextMove gets input from the user on which direction they would like to move 
 * and processInput takes that input and uses it to move the character and prints updates based on it
 */

public class InputListener {

    /**
     * entityManager object, received from UILauncher
     */
    private final EntityManager entityManager;

    /**
     * obstacleManager object used to alllow jumping onto platforms
     */
    private final ObstacleManager obstacleManager;

    /**
     * character associated with {@code InputListener}
     */
    private Character character;

    /**
     * location associated with the character
     */
    private Location location;


    /**
     * Constructor for the class
     *
     * @param character       gives InputListener the character associated with InputListener
     * @param entityManager   entityManager used to jump and kill on enemies
     * @param obstacleManager obstacleManager used to jump onto platforms
     */
    public InputListener(Character character, EntityManager entityManager, ObstacleManager obstacleManager) {
        this.character = character;
        this.location = character.getLocation();
        this.entityManager = entityManager;
        this.obstacleManager = obstacleManager;
    }


    /**
     * Uses method nextMove to get input from the user and processes the input and updates the character based on it
     */
    public void processInput() {

        char move = nextMove();
        Platform standingOnplatform = character.getStandingOnPlatform();

        // movement to the left
        if (move == 'a') {
            int initialX1 = location.getXcord();
            location.setXcord(initialX1 - 1);
            character.setLocation(location);
            int newX = initialX1 - 1;
            if (standingOnplatform != null && !(newX >= standingOnplatform.getLocation().getXcord() && newX <= standingOnplatform.getLocation().getXcord() + standingOnplatform.getLength())) {
                location.setYcord(0);
                character.setLocation(location);
                System.out.println("You have walked off the platform and have returned to the ground");
                character.setStandingOnPlatform(null);
            }

            // movement up
        } else if (move == 'w') {
            int characterXCord = character.getLocation().getXcord();
            int characterYCord = character.getLocation().getYcord();
            for (Obstacle obstacle : this.obstacleManager.getObstacleList()) {
                if (obstacle instanceof Platform) {
                    Platform platform = (Platform) obstacle;
                    if (characterXCord >= platform.getLocation().getXcord() && characterXCord <= platform.getLocation().getXcord() + platform.getLength() && platform.getLocation().getYcord() > characterYCord) {
                        location.setYcord(platform.getLocation().getYcord() + 1);
                        System.out.println("You have moved onto the platform above you");
                        character.setLocation(location);
                        character.setStandingOnPlatform(platform);
                        return;
                    }
                }
            }

            Enemy enemy = null;
            for (Enemy loopEnemy : this.entityManager.getEnemyList()) {
                if (loopEnemy.getLocation().getYcord() == location.getYcord()) {
                    if (loopEnemy.getLocation().getXcord() - location.getXcord() == 1) {
                        System.out.println("There was an enemy in front of you, you jumped on his head and now he is dead (you moved 1 right)");
                        UILauncher.getSound().playEnemyDeath();
                        enemy = loopEnemy;
                        location.addX(1);
                        break;
                    } else if (loopEnemy.getLocation().getXcord() - location.getXcord() == -1) {
                        System.out.println("There was an enemy behind you, you jumped on his head and now he is dead (you moved 1 left)");
                        UILauncher.getSound().playEnemyDeath();
                        enemy = loopEnemy;
                        location.addX(-1);
                        break;
                    }
                }
            }

            if (enemy == null) {
                System.out.println("You jump, but there are no enemies nearby to kill,\nyou land back down on the ground");
            } else {
                enemy.setIsDead(true);
                entityManager.removeEnemy(enemy);
            }

            // movement down
        } else if (move == 's') {

            if (character.getLocation().getYcord() > 0) {
                location.setYcord(0);
                System.out.println("You have lowered down off the platform onto the ground");
                character.setStandingOnPlatform(null);
            }

            // movement to the right
        } else {
            int initialX2 = location.getXcord();
            location.setXcord(initialX2 + 1);
            character.setLocation(location);
            int newX = initialX2 + 1;
            if (standingOnplatform != null && !(newX >= standingOnplatform.getLocation().getXcord() && newX <= standingOnplatform.getLocation().getXcord() + standingOnplatform.getLength())) {
                location.setYcord(0);
                character.setLocation(location);
                System.out.println("You have walked off the platform and have returned to the ground");
            }
        }

        character.setLocation(location);

    }

    /**
     * Gets the direction the user wants to move. If user enters invalid entry it will prompt them to try again
     *
     * @return direction the user wants to move
     */

    private char nextMove() {

        //sets basic values
        char move = 'q';
        boolean validmove = false;
        int yCord = location.getYcord();
        int xCord = location.getXcord();

        //while the move is invalid it will loop until it is
        while (validmove != true) {
            Scanner reader = new Scanner(System.in);
            try {
                System.out.print("Which direction would you like to move?: ");
                String choice = reader.next();
                if (choice.length() > 1) {
                    System.out.println("Please select a valid direction ('w','a','s','d') ");
                    continue;
                }
                move = choice.charAt(0);
                ;
                if (move == 'a' || move == 'w' || move == 'd' || move == 's') {
                    if (move != 's' && move != 'a') {
                        validmove = true;

                        //making sure user doesn't go below ground
                    } else if (move == 's' && yCord != 0) {
                        validmove = true;

                        //making sure user doesn't exit the leave game field on the left
                    } else if (move == 'a' && xCord != 0) {
                        validmove = true;

                        //if user tries to go below ground
                    } else if (move == 's' && yCord == 0) {
                        System.out.println("Cannot move down. Try another direction.");

                        //if user tries to leave the game field
                    } else if (move == 'a' && xCord == 0) {
                        System.out.println("Cannot move left. Try another direction");
                    }
                } else {
                    System.out.println("Please select a valid direction ('w','a','s','d') ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter 'a' for left, 'w' for up, 's' for down and 'd' for right ");
                reader.next();
            }
        }
        return move;

    }

    public static void main(String[] args) {

        Character tester = new ET();
        EntityManager entityManager = new EntityManager(new ObstacleManager(), tester);
        Location locate = tester.getLocation();
        boolean tryagain = true;
        Scanner reader = new Scanner(System.in);
        InputListener input = new InputListener(tester, entityManager, new ObstacleManager());
        entityManager.spawnRandomEntities(20);

        while (tryagain) {
            int locationX = locate.getXcord();
            int locationY = locate.getYcord();

            input.processInput();
            if (locate.getXcord() == locationX + 1) {
                System.out.println("If you moved right or jumped and killed enemy to right then test was successful");
            } else if (locate.getXcord() == locationX - 1) {
                System.out.println("if you moved left or jumped and killed enemy to left then test successful");
            } else {
                System.out.println("if you jumped and no enemy nearby then test sucessful");
            }
            // TODO: TEST IF GOES ONTO PLATFORM
            // TODO: TEST IF LOWERS THRU PLATFORM ONTO GROUND OR LOWER PLATFORM?

            System.out.print("would you like to try again? Enter n if you would like to stop.: ");
            String choice = reader.next();
            char move = choice.charAt(0);
            if (choice.length() == 1 && move == 'n') {
                tryagain = false;
            }

        }
        reader.close();
        System.exit(0);
    }

}





