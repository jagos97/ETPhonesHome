package etphoneshome.managers;

import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.entities.enemies.Police;
import etphoneshome.entities.enemies.Scientist;
import etphoneshome.objects.Level;
import etphoneshome.objects.Location;
import etphoneshome.objects.Obstacle;
import etphoneshome.objects.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is used to manage all the entities that are in the game.
 * Using the addEnemy method adds an enemy to the list enemies.
 * Using the removeEnemy method removes an enemy from the list enemies.
 * Using getEnemyList allows you to return the list of enemies in its current state.
 */
public class EntityManager {

    private ObstacleManager obstacleManager;

    /**
     * character associated with {@code EntityManager}
     */
    private Character character;
    /**
     * List containing all of the characters in the game
     */
    private final List<Enemy> enemies = new ArrayList<>();

    /**
     * Constructor for the class
     *
     * @param character gives EntityManager the character associated with EntityManager
     * @param obstacleManager ObstacleManager
     */
    public EntityManager(ObstacleManager obstacleManager, Character character) {
        this.character = character;
        this.obstacleManager = obstacleManager;
    }

    /**
     * Adds the given enemy associated with {@code enemy} to the list enemies.
     *
     * @param enemy the {@code enemy} that is added to the list.
     */
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    /**
     * Method to remove the given enemy associated with {@code enemy} from the list enemies.
     *
     * @param enemy the {@code enemy} that is removed from the list.
     */
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    /**
     * Method to return the list {@code enemies}.
     *
     * @return The current list {@code enemies}.
     */
    public List<Enemy> getEnemyList() {
        return this.enemies;
    }

    /**
     * Method to print out enemies in random locations
     *
     * @param amount The amount of entities to be spawned
     */
    public void spawnRandomEntities(int amount) {

        Random random = new Random();
        int xCord = random.nextInt(10) + 1;
        for (int i = 0; i < amount; i++) {
            xCord += random.nextInt(10) + 1;
            int type = random.nextInt(1);
            Enemy enemy;
            if (type == 0) {
                enemy = new Police();
            } else {
                enemy = new Scientist();
            }
            enemy.setLocation(new Location(xCord, 0));
            this.addEnemy(enemy);
        }
    }

    /**
     * Loads entities from a given level
     *
     * @param level Level to load entities from
     */
    public void loadEntities(Level level) {
        this.clearEntities();
        enemyloop:
        for (Enemy enemy : level.getEnemies()) {
            this.enemies.add(enemy);
            Location location = enemy.getLocation();
            for (Obstacle obstacle : obstacleManager.getObstacleList()) {
                Platform platform = (Platform) obstacle;
                if (location.getXcord() > platform.getLocation().getXcord() && location.getXcord() < platform.getLocation().getXcord() + platform.getLength() && location.getYcord() == platform.getLocation().getYcord() + 1) {
                    enemy.setStandingOnPlatform(platform);
                    continue enemyloop;
                }
            }

            while (location.getYcord() > 0) {
                location.setYcord(location.getYcord() - 1);
                for (Obstacle obstacle : obstacleManager.getObstacleList()) {
                    Platform platform = (Platform) obstacle;
                    if (location.getXcord() > platform.getLocation().getXcord() && location.getXcord() < platform.getLocation().getXcord() + platform.getLength() && location.getYcord() == platform.getLocation().getYcord() + 1) {
                        enemy.setLocation(location);
                        enemy.setStandingOnPlatform(platform);
                        continue enemyloop;
                    }
                }
            }
            enemy.setLocation(location);
            if (location.getYcord() < 0) {
                enemy.setLocation(new Location(location.getXcord(), 0));
            }
        }
    }

    /**
     * Clears all entities from the enemies list
     */
    public void clearEntities() {
        this.enemies.clear();
    }

    // main tests the class' methods
    public static void main(String[] args) {
        // creates an enemy we can use to test the methods.
        Enemy testEnemy = new Police();
        // creates a character for the entityManager constructor
        Character testCharacter = new ET();
        EntityManager entityManager = new EntityManager(new ObstacleManager(), testCharacter);
        //spawns random enemies so we can test our methods
        entityManager.spawnRandomEntities(10);
        System.out.println("10 enemies should have spawned. Number of enemies: " + entityManager.enemies.size());
        entityManager.addEnemy(testEnemy);
        System.out.println("List should contain 11 enemies. Number of enemies: " + entityManager.enemies.size());
        entityManager.removeEnemy(testEnemy);
        System.out.println("List should contain 10 enemies. Number of enemies: " + entityManager.enemies.size());
        entityManager.getEnemyList();
    }

}

