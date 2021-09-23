package etphoneshome;

import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.graphics.GraphicsRepainter;
import etphoneshome.listeners.InputListener;
import etphoneshome.managers.*;
import etphoneshome.objects.Level;
import etphoneshome.sound.Sound;

public class UILauncher {
	
	private static Sound sound = new Sound();

    public static void main(String[] args) {
    	
    	sound.playMenu();
    	
    	//sets up required componentsd
        Character character = new ET();
        ObstacleManager obstacleManager = new ObstacleManager();
        CollectiblesManager collectiblesManager = new CollectiblesManager(obstacleManager);
        EntityManager entityManager = new EntityManager(obstacleManager, character);
        GraphicsRepainter graphicsRepainter = new GraphicsRepainter(character, entityManager, collectiblesManager, new ObstacleManager());
        InputListener inputListener = new InputListener(character, entityManager, new ObstacleManager());
        LevelManager levelManager = new LevelManager(obstacleManager, entityManager, collectiblesManager);
        GameManager gameManager = new GameManager(inputListener, graphicsRepainter, entityManager, character, collectiblesManager, levelManager);
        levelManager.addLevel(new Level("level-0"));
        levelManager.loadLevel(0);

        while (!gameManager.getGameOver()) {
            gameManager.nextTurn();
        }

        
    }
    
    public static Sound getSound() {
    	return sound;
    }

}
