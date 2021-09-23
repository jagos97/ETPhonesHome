package etphoneshome.managers;

import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.objects.Player;

import java.io.*;
import java.util.Scanner;

/**
 * this class is responsible for showing the highscores of the top 10 players
 * setPlayers will get all the previous players from the file and put them into PlayerManager
 * AddCurrentPlayer will add the player that just played
 * and SaveHighscores will write the players and score in the file again while showHighScore will run the 3 in unison
 */

public class HighScoreManager {


    /**
     * file path assocciated with the Highscores text file which contains the top scores
     */
    private static String fileName = new File("HighScores\\Highscores.txt").exists() ? "HighScores\\Highscores.txt" : "ET Phones Home - Text Based\\src\\HighScores\\Highscores.txt";
    private static File highscores = new File(fileName);

    /**
     * PlayerManager of the {@code HighScoresManager}
     */
    private PlayerManager playerManager = new PlayerManager();

    
    /**
     * Character associated with the {@code HighScoresManager}
     */
    private Character character;

    /**
     * constructor which will add the character whose score we will add
     *
     * @param character character of the {@code HighScoresManager}
     */
    public HighScoreManager(Character character) {
        this.character = character;
        this.setPlayers();
    }

    /**
     * Sets the players into {@code PlayerManager}
     */
    private void setPlayers() {
        this.playerManager.getPlayerList().clear();
        String line;
        try {
            BufferedReader highscoreFile = new BufferedReader(new FileReader(fileName));
            while ((line = highscoreFile.readLine()) != null) {
                String[] variables = line.split(" ");
                Player player = new Player(Integer.parseInt(variables[0]), variables[1]);
                this.playerManager.addPlayer(player);
            }
            highscoreFile.close();

        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * adds the current player to the highscore
     *
     * @param name name given by the user
     */
    private void addCurrentPlayer(String name) {
        Player player = new Player(character.getScore(), name);
        playerManager.addPlayer(player);
    }

    /**
     * writes the highscores and names of the players into the txt file
     */
    private void saveHighScores() {

        PrintWriter writer;
        try {
            writer = new PrintWriter(highscores);
            for (Player player : playerManager.getPlayerList()) {
                writer.println(player.getScore() + " " + player.getName());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * prints the highscores
     */
    public void showHighscores() {
    	Scanner reader = new Scanner(System.in);
    	String name;
    	System.out.print("Please enter your name: ");
    	name= reader.nextLine();
    	runHighScores(name);
    	for(Player player : this.playerManager.getPlayerList()) {
    		System.out.println(player.toString());
    	}
    }

    /**
     * runs all the processes of {@code HighScoresManager} to do with reading and writing to file
     *
     * @param name name given by the user
     */
    public void runHighScores(String name) {
        addCurrentPlayer(name);
        saveHighScores();
        
    }


    public static void main(String[] args) {
        HighScoreManager highScoresManager = new HighScoreManager(new ET());
        highScoresManager.character.addScore(19000);
        String name = "Test";
        highScoresManager.showHighscores();
    }


}