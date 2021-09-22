package etphoneshome.managers;

import etphoneshome.UILauncher;
import etphoneshome.objects.Collectible;
import etphoneshome.objects.Level;
import etphoneshome.objects.Location;
import etphoneshome.objects.Obstacle;
import etphoneshome.objects.PhonePiece;
import etphoneshome.objects.PhonePieceType;
import etphoneshome.objects.Platform;
import etphoneshome.objects.ReesesPieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CollectiblesManager {
    /**
     * This class is used to manage all the collectibles that are in the game.
     * Using the addCollectible method adds a collectible to the list collectibles.
     * Using the removeCollectible method removes a collectible from the list collectibles.
     * Using getCollectiblesList allows you to return the list of collectibles in its current state.
     * Using spawnRandomPieces will spawn a set number of RessesPieces 
     * Using loadCollectibles will get all the phone pieces in the level and spawn random Reeses Pieces on the floor
     * Using clearCollectibles will clear all current collectibles from the arraylist
     */
	
	/**
	 * list of all collectibles associated with {@code CollectiblesManager}
	 */
    private final List<Collectible> collectibles = new ArrayList<Collectible>();
    
    /**
     * Used to get the list of obstacles to spawn ReesesPieces on them
     */
    private ObstacleManager obstacles;
    
    
    /**
     * Constructor that sets up the ObstacleManager
     * @param obstacles obstacleManager of {@code CollectiblesManager}
     */
    public CollectiblesManager(ObstacleManager obstacles) {
    	this.obstacles = obstacles;
    }

    /**
     * empty default constructor
     */
    public CollectiblesManager() {
    	
	}

	/**
     * Method to add collectible to the list collectibles.
     *
     * @param collectible is the collectible that is added
     */
    public void addCollectible(Collectible collectible) {
        collectibles.add(collectible);

    }

    /**
     * Method to remove collectible from the list collectibles.
     *
     * @param collectible is the collectible that is removed from the list.
     */
    public void removeCollectible(Collectible collectible) {
        collectibles.remove(collectible);

    }

    /**
     * Method to return the list collectibles in its current state.
     *
     * @return returns list collectibles.
     */
    public List<Collectible> getCollectiblesList() {
        return this.collectibles;
    }

    /**
     * Spawn given number of reeses pieces at random locations on the ground
     * and spawns with a 25% chance a reesesPieces at every platform
     *
     * @param num The amount of reeses pieces to spawn in the game on the ground
     */
    public void spawnRandomReesesPieces(int num) {
    	//ground ReesesPieces
        Random random = new Random();
        int xCord = UILauncher.getGraphicsRepainter().WIDTH / 2 + 70;
        for (double i = 0; i < num; i++) {
            xCord = random.nextInt(1920) + xCord;
            Location location = new Location(xCord, UILauncher.getGraphicsRepainter().HEIGHT - 100 - 40);
            this.addCollectible(new ReesesPieces(location));
        }
        //PLatform reesesPieces
        for(Obstacle obstacle : obstacles.getObstacleList()) {
        	int rand = random.nextInt(4) + 1;
        	if(rand == 2) {
        		if(obstacle instanceof Platform) {
        			obstacle = (Platform)obstacle;
        			int x = obstacle.getLocation().getXcord() + ReesesPieces.getWidth()/2;
        			int y = obstacle.getLocation().getYcord() - ReesesPieces.getHeight() -10;
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
     * Clears the collectibles list
     */
    public void clearCollectibles() {
        this.collectibles.clear();
    }
    
    
    public static void main(String[] args) {
    	PhonePiece tester1 = new PhonePiece(new Location(200,300), PhonePieceType.ANTENNA);
    	ReesesPieces tester2 = new ReesesPieces(new Location(200,300));
    	PhonePiece tester3 = new PhonePiece(new Location(200,300), PhonePieceType.CHASSIS);
    	ReesesPieces tester4 = new ReesesPieces(new Location(200,300));
    	ReesesPieces tester5 = new ReesesPieces(new Location(200,300));
    	ReesesPieces tester6 = new ReesesPieces(new Location(200,300));
    	PhonePiece tester7 = new PhonePiece(new Location(200,300), PhonePieceType.KEYPAD);
    	ReesesPieces tester8 = new ReesesPieces(new Location(200,300));
    	PhonePiece tester9 = new PhonePiece(new Location(200,300), PhonePieceType.ANTENNA);
    	ReesesPieces tester10 = new ReesesPieces(new Location(200,300));
    	
    	CollectiblesManager manager = new CollectiblesManager();
    	
    	manager.addCollectible(tester1);
    	manager.addCollectible(tester2);
    	manager.addCollectible(tester3);
    	manager.addCollectible(tester4);
    	manager.addCollectible(tester5);
    	manager.addCollectible(tester6);
    	manager.addCollectible(tester7);
    	manager.addCollectible(tester8);
    	manager.addCollectible(tester9);
    	manager.addCollectible(tester10);
  
    	for(Collectible collectible : manager.getCollectiblesList()) {
    		if(collectible instanceof ReesesPieces) {
    			System.out.println("Should be a Reeses Pieces is: " + collectible.getClass().getName());
    		}
    		else {
    			System.out.println("Should be a phone piece is: " + collectible.getClass().getName());
    		}
    		System.out.println(collectible.getLocation().getXcord() + ", " + collectible.getLocation().getYcord());
    	}
    	System.exit(0);
    }
    
}

