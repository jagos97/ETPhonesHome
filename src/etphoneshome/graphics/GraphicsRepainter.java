package etphoneshome.graphics;

import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.managers.CollectiblesManager;
import etphoneshome.managers.EntityManager;
import etphoneshome.managers.ObstacleManager;
import etphoneshome.objects.*;


/**
 * Class responsible for repainting the graphics of the game
 * print state will print enemies and collectibles that are nearby
 */
public class GraphicsRepainter {

    /**
     * Necessary objects to get the status of the game
     */
    private final EntityManager entityManager;
    private final Character character;
    private final CollectiblesManager collectiblesManager;
    private final ObstacleManager obstacleManager;

    /**
     * Constructor that sets all needed objects
     *
     * @param character           character the user is playing as
     * @param entityManager       where the enemies are stored in
     * @param collectiblesManager where the collectibles are stored in
     * @param obstacleManager     used to check for platforms above character
     */
    public GraphicsRepainter(Character character, EntityManager entityManager, CollectiblesManager collectiblesManager, ObstacleManager obstacleManager) {
        this.character = character;
        this.entityManager = entityManager;
        this.collectiblesManager = collectiblesManager;
        this.obstacleManager = obstacleManager;
    }

    /**
     * Prints the state of the current game
     */
    public void printState() {

        System.out.println("");
        System.out.println(character.getLocation().toString());

        // loop thru enemies in entity manager and print how far away they are from the character
        boolean enemiesNearby = false;
        for (Enemy enemy : this.entityManager.getEnemyList()) {
            if (enemy.getLocation().getDistance(this.character.getLocation()) <= 5) {
                System.out.println("Enemy " + enemy.getLocation().getDistance(this.character.getLocation()) + " meters from you");
                enemiesNearby = true;
            }
        }

        if (!enemiesNearby) {
            System.out.println("There are no enemies nearby");
        }

        //checks if there is a platform above the player
        int characterXCord = character.getLocation().getXcord();
        int characterYCord = character.getLocation().getYcord();
        for (Obstacle obstacle : this.obstacleManager.getObstacleList()) {
            if (obstacle instanceof Platform) {
                Platform platform = (Platform) obstacle;
                if (characterXCord >= platform.getLocation().getXcord() && characterXCord <= platform.getLocation().getXcord() + platform.getLength() && characterYCord < platform.getLocation().getYcord()) {
                    System.out.println("There is a platform above you");
                    break;
                }
            }
        }

        //loop through the collectibles to see what type there are and if they are close, prints the distance
        for (Collectible collectible : this.collectiblesManager.getCollectiblesList()) {
            if (collectible instanceof ReesesPieces) {
                if (collectible.getLocation().getDistance(this.character.getLocation()) <= 5) {
                    System.out.println("ReesesPieces " + collectible.getLocation().getDistance(this.character.getLocation()) + " meters away from you");
                }
            } else if (collectible instanceof PhonePiece) {
                Location loc = collectible.getLocation();
                if (loc.getXcord() != characterXCord) {
                    System.out.println("PhonePiece is " + (Math.abs(loc.getXcord() - characterXCord)) + " meters away from you!");
                }
                if (loc.getXcord() == characterXCord) {
                    System.out.println("PhonePiece is above you!");
                }
            }
        }

        System.out.println("");
    }

    public static void main(String[] args) {
        Character character = new ET();
        EntityManager entityManager = new EntityManager(new ObstacleManager(), character);
        ObstacleManager obstacleManager = new ObstacleManager();
        CollectiblesManager collectiblesManager = new CollectiblesManager(obstacleManager);
        // Create objects made for GraphicRepainter constructor
        entityManager.spawnRandomEntities(5);
        // spawn random entities so we can test printState


        GraphicsRepainter graphicsRepainter = new GraphicsRepainter(character, entityManager, collectiblesManager, obstacleManager);

        for (int i = 0; i < 5; i++) {
            // move character forward in-case no enemies are within 5m so as to test the <= 5m check
            character.getLocation().setXcord(character.getLocation().getXcord() + 1);
            // rest printState
            graphicsRepainter.printState();
            System.out.println("");
        }

    }

}
