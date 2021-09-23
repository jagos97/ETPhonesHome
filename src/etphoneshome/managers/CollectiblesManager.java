package etphoneshome.managers;

import etphoneshome.objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is used to manage all the collectibles that are in the game.
 * Using the addCollectible method adds a collectible to the list collectibles.
 * Using the removeCollectible method removes a collectible from the list collectibles.
 * Using getCollectiblesList allows you to return the list of collectibles in its current state.
 * Using clearList will remove all the collectibles from the collecitbles arraylist
 */
public class CollectiblesManager {

    private ObstacleManager obstacleManager;

    private final List<Collectible> collectibles = new ArrayList<Collectible>();

    public CollectiblesManager(ObstacleManager obstacleManager) {
        this.obstacleManager= obstacleManager;
    }

    /**
     * method to add collectible to the list collectibles.
     *
     * @param collectible is the collectible that is added
     */
    public void addCollectible(Collectible collectible) {
        collectibles.add(collectible);

    }

    /**
     * method to remove collectible from the list collectibles.
     *
     * @param collectible is the collectible that is removed from the list.
     */
    public void removeCollectible(Collectible collectible) {
        collectibles.remove(collectible);

    }

    /**
     * method to return the list collectibles in its current state.
     *
     * @return returns list collectibles.
     */
    public List<Collectible> getCollectiblesList() {
        return this.collectibles;

    }

    /**
     * Spawns {@code ReesesPieces} at random points on the map
     *
     * @param num number of {@code ReesesPieces}
     */
    public void spawnRandomReesesPieces(int num) {
        Random random = new Random();
        int xCord = 5;
        for (int i = 0; i < num; i++) {
            xCord = random.nextInt(10) + 1 + xCord;
            Location location = new Location(xCord, 0);
            this.addCollectible(new ReesesPieces(location));
        }

        //PLatform reesesPieces
        for(Obstacle obstacle : obstacleManager.getObstacleList()) {
            int rand = random.nextInt(4) + 1;
            if(rand == 2) {
                if(obstacle instanceof Platform) {
                    Platform platform = (Platform) obstacle;
                    int x = platform.getLocation().getXcord() + (new Random().nextInt(platform.getLength()));
                    int y = platform.getLocation().getYcord() + 1;
                    Location location = new Location(x,y);
                    ReesesPieces piece = new ReesesPieces(location);
                    collectibles.add(piece);
                }
            }
        }
    }

    /**
     * Load the collectibles from a given level
     *
     * @param level The level to load the collectibles from
     */
    public void loadCollectibles(Level level) {
        this.clearCollectibles();
        for (Collectible collectible : level.getPhonePieces()) {
            this.collectibles.add(collectible);
        }

        this.spawnRandomReesesPieces(10);
    }
    
    /**
     * clears the current list of collectibles
     */
    public void clearCollectibles() {
    	this.collectibles.clear();
    }
    
    public static void main(String[] args) {
    	CollectiblesManager manager = new CollectiblesManager(new ObstacleManager());
    	manager.spawnRandomReesesPieces(10);
    	manager.addCollectible(new PhonePiece(new Location(4,4),PhonePieceType.ANTENNA));
    	manager.addCollectible(new PhonePiece(new Location(4,4),PhonePieceType.KEYPAD));
    	manager.addCollectible(new PhonePiece(new Location(4,4),PhonePieceType.CHASSIS));
    	for (Collectible collectible : manager.getCollectiblesList()) {
    		if (collectible instanceof ReesesPieces) {
    			System.out.println("should be a Reeses Piece " + collectible.toString());
    		}
    		else {
    			System.out.println("Should be a phone piece " + collectible.toString());
    		}
    	}
    	manager.clearCollectibles();
    	
    	System.out.println("List should be empty, nothing else should be printed after this.");
    	for (Collectible collectible : manager.getCollectiblesList()) {
    		System.out.println(collectible.toString());
    	}
    	
    	
    }

}

