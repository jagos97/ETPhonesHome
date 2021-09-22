package etphoneshome.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

import java.io.File;

/**
 * class that handles all sounds in the game
 * using the play methods you can play the sounds and stop methods to stop them
 */
public class Sound {

    /**
     * Variables for the sounds/music in the game
     * Thanks to @naisusumeru for their song "Town Hall Tower" on Beepbox.co
     */
    private static String theme = new File("sounds\\MainSongET.wav").exists() ? "sounds\\MainSongET.wav" : "ET Phones Home\\src\\sounds\\MainSongET.wav";
    private static Media themeMedia = new Media(new File(theme).toURI().toString());
    private static MediaPlayer themePlayer = new MediaPlayer(themeMedia);

    private static String damage = new File("sounds\\TakeDamage.wav").exists() ? "sounds\\TakeDamage.wav" : "ET Phones Home\\src\\sounds\\TakeDamage.wav";
    private static Media damageMedia = new Media(new File(damage).toURI().toString());
    private static MediaPlayer damagePlayer = new MediaPlayer(damageMedia);

    private static String dead = new File("sounds\\DeathET.wav").exists() ? "sounds\\DeathET.wav" : "ET Phones Home\\src\\sounds\\DeathET.wav";
    private static Media deadMedia = new Media(new File(dead).toURI().toString());
    private static MediaPlayer deadPlayer = new MediaPlayer(deadMedia);

    private static String win = new File("sounds\\Winning.wav").exists() ? "sounds\\Winning.wav" : "ET Phones Home\\src\\sounds\\Winning.wav";
    private static Media winMedia = new Media(new File(win).toURI().toString());
    private static MediaPlayer winPlayer = new MediaPlayer(winMedia);

    private static String ReesePickUp = new File("sounds\\ReesePickUp.wav").exists() ? "sounds\\ReesePickUp.wav" : "ET Phones Home\\src\\sounds\\ReesePickUp.wav";
    private static Media ReesePickUpMedia = new Media(new File(ReesePickUp).toURI().toString());
    private static MediaPlayer reesePlayer = new MediaPlayer(ReesePickUpMedia);

    private static String phonePickUp = new File("sounds\\PhonePickUp.wav").exists() ? "sounds\\PhonePickUp.wav" : "ET Phones Home\\src\\sounds\\PhonePickUp.wav";
    private static Media phonePickUpMedia = new Media(new File(phonePickUp).toURI().toString());
    private static MediaPlayer phonePlayer = new MediaPlayer(phonePickUpMedia);
    
    private static String enemyDeath = new File("sounds\\EnemyDeath.wav").exists() ? "sounds\\EnemyDeath.wav" : "ET Phones Home\\src\\sounds\\EnemyDeath.wav";
    private static Media enemyDeathMedia = new Media(new File(enemyDeath).toURI().toString());
    private static MediaPlayer enemyDeathPlayer = new MediaPlayer(enemyDeathMedia);

    private static String ETTheme = new File("sounds\\ETTheme.mp3").exists() ? "sounds\\ETTheme.mp3" : "ET Phones Home\\src\\sounds\\ETTheme.mp3";
    private static Media ETThemeMedia = new Media(new File(ETTheme).toURI().toString());
    private static MediaPlayer ETThemePlayer = new MediaPlayer(ETThemeMedia);
    

    /**
     * empty constructor that sets cycle of the main songs to indefinte
     */
    public Sound() {
    	themePlayer.setCycleCount(MediaPlayer.INDEFINITE);
    	ETThemePlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    /**
     * plays song that should play while playing the levels
     */
    public void playTheme() {
        themePlayer.play();
    }

    /**
     * stops the theme from playing
     */
    public void stopTheme() {
        themePlayer.stop();
    }

    /**
     * sound that should play when taking damage
     */
    public void takeDamageSound() {
        checkStatus(damagePlayer);
        damagePlayer.play();

    }

    /**
     * sound that should play on death
     */
    public void playETDeath() {
        checkStatus(deadPlayer);
        deadPlayer.play();
    }

    /**
     * sound that should play when they beat a level
     */
    public void playWin() {
        checkStatus(winPlayer);
        winPlayer.play();
    }

    /**
     * sound that should play when they collect a reeses pieces
     */
    public void playReese() {
        checkStatus(reesePlayer);
        reesePlayer.play();
    }

    /**
     * sound that should play when the collect a phone piece
     */
    public void playPhone() {
        checkStatus(phonePlayer);
        phonePlayer.play();
    }

    /**
     * sound that plays when enemy dies
     */
    public void playEnemyDeath() {
    	checkStatus(enemyDeathPlayer);
    	enemyDeathPlayer.play();
    }
    
    /**
     * song that plays in the main menu
     */
    public void playETTheme() {
    	checkStatus(ETThemePlayer);
    	ETThemePlayer.play();
    }
    
    /**
     * stops the song that plays in the main menu
     */
    public void stopETTheme() {
    	ETThemePlayer.stop();
    }
    
    /**
     * checks to see if the current media is playing and stops it if it is
     * @param media1 media of the sound that needs to be checked
     */
    private void checkStatus(MediaPlayer media1) {
        if (media1.getStatus() == Status.PLAYING) {
            media1.stop();
        }
   
    }  

}
