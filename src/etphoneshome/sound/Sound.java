package etphoneshome.sound;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * This class is responsible for playing sounds to play the game
 *
 */
public class Sound extends JFrame {
	
	/**
	 * needed for the jFrame
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * files for the sounds of the game
	 */
    private static String mainTheme = new File("sounds\\MainSongET.wav").exists() ? "sounds\\MainSongET.wav" : "ET Phones Home - Text Based\\src\\sounds\\MainSongET.wav";

    private static String damage = new File("sounds\\TakeDamage.wav").exists() ? "sounds\\TakeDamage.wav" : "ET Phones Home - Text Based\\src\\sounds\\TakeDamage.wav";

    private static String dead = new File("sounds\\DeathET.wav").exists() ? "sounds\\DeathET.wav" : "ET Phones Home - Text Based\\src\\sounds\\DeathET.wav";
  
    private static String win = new File("sounds\\Winning.wav").exists() ? "sounds\\Winning.wav" : "ET Phones Home - Text Based\\src\\sounds\\Winning.wav";
   
    private static String ReesePickUp = new File("sounds\\ReesePickUp.wav").exists() ? "sounds\\ReesePickUp.wav" : "ET Phones Home - Text Based\\src\\sounds\\ReesePickUp.wav";

    private static String phonePickUp = new File("sounds\\PhonePickUp.wav").exists() ? "sounds\\PhonePickUp.wav" : "ET Phones Home - Text Based\\src\\sounds\\PhonePickUp.wav";
    
    private static String enemyDeath = new File("sounds\\EnemyDeath.wav").exists() ? "sounds\\EnemyDeath.wav" : "ET Phones Home - Text Based\\src\\sounds\\EnemyDeath.wav";

    private static String menu = new File("sounds\\ETTheme.wav").exists() ? "sounds\\ETTheme.wav" : "ET Phones Home - Text Based\\src\\sounds\\ETTheme.wav";

    
    public void playETDeath() {
    	InputStream in;
		try {
			in = new FileInputStream(new File(dead));
			AudioStream audio = new AudioStream(in);
	        AudioPlayer.player.start(audio);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
    
    public void playDamage() {
    	InputStream in;
		try {
			in = new FileInputStream(new File(damage));
			AudioStream audio = new AudioStream(in);
	        AudioPlayer.player.start(audio);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
    
    public void playWin() {
    	InputStream in;
		try {
			in = new FileInputStream(new File(win));
			AudioStream audio = new AudioStream(in);
	        AudioPlayer.player.start(audio);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
    
    public void playPhonePickUp() {
    	InputStream in;
		try {
			in = new FileInputStream(new File(phonePickUp));
			AudioStream audio = new AudioStream(in);
	        AudioPlayer.player.start(audio);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
        
    public void playReese() {
    	InputStream in;
		try {
			in = new FileInputStream(new File(ReesePickUp));
			AudioStream audio = new AudioStream(in);
	        AudioPlayer.player.start(audio);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
    
    public void playEnemyDeath() {
    	InputStream in;
		try {
			in = new FileInputStream(new File(enemyDeath));
			AudioStream audio = new AudioStream(in);
	        AudioPlayer.player.start(audio);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
    public void playMain() {
    	File soundFile = new File(mainTheme);
    	try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			
		} catch (UnsupportedAudioFileException | IOException e) {
			
			e.printStackTrace();
		} catch (LineUnavailableException e) {
		
			e.printStackTrace();
		}
    }
    
    public void playMenu() {
    	File soundFile = new File(menu);
    	try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			
		} catch (UnsupportedAudioFileException | IOException e) {
			

		} catch (LineUnavailableException e) {
		
			e.printStackTrace();
		}
    }

    
    public static void main(String[] args) {
    	Scanner reader = new Scanner(System.in);
    	Sound sound = new Sound();
    	String song = null;
    try {

    	while (song != "n") {
    		System.out.println("What song/sound would you like to play?");
    		System.out.println("mainTheme");
    		System.out.println("damage");
    		System.out.println("death");
    		System.out.println("win");
    		System.out.println("reese");
    		System.out.println("phone");
    		System.out.println("enemyDeath");
    		System.out.print("Or type \"n\" to leave: ");
    		song = reader.nextLine();    		
    		switch (song) {
    			case "mainTheme":	
    				sound.playMain(); break;
    			case "damage":
    				sound.playDamage(); break;
    			case "death":
    				sound.playETDeath(); break;
    			case "win":
    				sound.playWin(); break;
    			case "reese":
    				sound.playReese(); break;
    			case "phone":
    				sound.playPhonePickUp(); break;
    			case "enemyDeath":
    				sound.playEnemyDeath(); break;
    			case "n":
    				reader.close();
    				System.exit(0);
    			default:
    				System.out.println("invalid input please try again");
    				
    		}
    		
    	}
        
    } catch(Exception ex) {
        System.out.println("Error with playing sound.");
        ex.printStackTrace();
        reader.close();
        System.exit(0);

    }
 

 }

}