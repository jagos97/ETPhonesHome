package etphoneshome.managers;

import etphoneshome.UILauncher;
import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.entities.enemies.Police;
import etphoneshome.entities.enemies.Scientist;
import etphoneshome.objects.Level;
import etphoneshome.objects.Location;
import etphoneshome.objects.Obstacle;
import etphoneshome.objects.Platform;

import java.awt.*;
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

    private int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    /**
     * List containing all of the characters in the game
     */
    private final List<Enemy> enemies = new ArrayList<>();

    /**
     * Adds the given {@code Enemy} to the list enemies.
     *
     * @param enemy the {@code Enemy} that is added to the list.
     */
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    /**
     * Method to remove the given {@code Enemy} from the list enemies.
     *
     * @param enemy the {@code Enemy} that is removed from the list.
     */
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    /**
     * Method to return the list.
     *
     * @return The current list.
     */
    public List<Enemy> getEnemyList() {
        return this.enemies;
    }

    /**
     * Spawns {@code Enemy} in random locations around the map
     *
     * @param amount The amount of {@code Enemy} to be spawned
     */
    public void spawnRandomEntities(double amount) {
        Random random = new Random();
        int xCord = UILauncher.getGraphicsRepainter().WIDTH / 2 + 70;
        for (double i = 0; i < amount; i++) {
            xCord = random.nextInt(1920) + (int) new Police().getLeftEntitySprite().getWidth() + xCord;
            double type = random.nextInt(2);
            Enemy enemy;
            if (type == 0) {
                enemy = new Police(new Location(xCord, UILauncher.getGraphicsRepainter().HEIGHT - 100 - (int) new Police().getLeftEntitySprite().getHeight()));
            } else {
                enemy = new Scientist(new Location(xCord, UILauncher.getGraphicsRepainter().HEIGHT - 100 - (int) new Police().getLeftEntitySprite().getHeight()));
            }
            enemy.setLocation(new Location(xCord, UILauncher.getGraphicsRepainter().HEIGHT - 100 - (int) new Police().getLeftEntitySprite().getHeight()));
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
            for (Obstacle obstacle : UILauncher.getObstacleManager().getObstacleList()) {
                Platform platform = (Platform) obstacle;
                if (location.getXcord() > platform.getLocation().getXcord() && location.getXcord() < platform.getLocation().getXcord() + platform.getRealLength() && location.getYcord() + (int) enemy.getRightEntitySprite().getHeight() == platform.getLocation().getYcord()) {
                    enemy.setStandingOnPlatform(platform);
                    continue enemyloop;
                }
            }

            while (location.getYcord() - enemy.getRightEntitySprite().getHeight() < UILauncher.getGraphicsRepainter().HEIGHT - 100) {
                location.setYcord(location.getYcord() + 1);
                for (Obstacle obstacle : UILauncher.getObstacleManager().getObstacleList()) {
                    Platform platform = (Platform) obstacle;
                    if (location.getXcord() > platform.getLocation().getXcord() && location.getXcord() < platform.getLocation().getXcord() + platform.getRealLength() && location.getYcord() + (int) enemy.getRightEntitySprite().getHeight() == platform.getLocation().getYcord()) {
                        enemy.setLocation(location);
                        enemy.setStandingOnPlatform(platform);
                        continue enemyloop;
                    }
                }
            }
            enemy.setLocation(location);
            if (location.getYcord() - enemy.getRightEntitySprite().getHeight() > UILauncher.getGraphicsRepainter().HEIGHT - 100) {
                enemy.setLocation(new Location(location.getXcord(), UILauncher.getGraphicsRepainter().HEIGHT - 100 + (int) enemy.getRightEntitySprite().getHeight()));
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
        EntityManager entityManager = new EntityManager();
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

