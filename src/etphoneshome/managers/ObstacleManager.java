package etphoneshome.managers;

import etphoneshome.objects.Level;
import etphoneshome.objects.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class ObstacleManager {
    /**
     * This class is used to manage all the obstacles that are in the game.
     * Using the addObstacle method adds an obstacle to the list obstacles.
     * Using the removeObstacle method removes an obstacle from the list obstacles.
     * Using getObstacleList allows you to return the list of obstacles in its current state.
     */
    private final List<Obstacle> obstacles = new ArrayList<>();

    /**
     * method to add obstacle to the list obstacles.
     *
     * @param obstacle obstacle that is added to list obstacles
     */
    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    /**
     * method to remove obstacle from list obstacles
     *
     * @param obstacle obstacle that is removed from list obstacle
     */
    public void removeObstacle(Obstacle obstacle) {
        obstacles.remove(obstacle);

    }

    /**
     * method to get list obstacles in its current state
     *
     * @return returns list obstacles.
     */
    public List<Obstacle> getObstacleList() {
        return this.obstacles;
    }

    /**
     * Load all obstacles from level
     *
     * @param level The given level to load obstacles from
     */
    public void loadObstacles(Level level) {
        this.clearObstacles();
        for (Obstacle obstacle : level.getObstacles()) {
            this.addObstacle(obstacle);
        }
    }

    /**
     * Clears all the obstacles
     */
    public void clearObstacles() {
        this.obstacles.clear();
    }
}
