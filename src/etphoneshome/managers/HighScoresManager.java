package etphoneshome.managers;

import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.graphics.SpriteURL;
import etphoneshome.player.Player;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * this class is responsible for showing the highscores of the top 10 players
 * setPlayers will get all the previous players from the file and put them into PlayerManager
 * AddCurrentPlayer will add the player that just played
 * and SaveHighscores will write the players and score in the file again while runHighScore will run the 3 in unison
 */

public class HighScoresManager {


    /**
     * file path assocciated with the Highscores text file which contains the top scores
     */
    private static String fileName = new File("HighScores\\Highscores.txt").exists() ? "HighScores\\Highscores.txt" : "ET Phones Home\\src\\HighScores\\Highscores.txt";
    private static File highscores = new File(fileName);

    /**
     * size of screen
     */
    private static double WIDTH, HEIGHT;
    /**
     * PlayerManager of the {@code HighScoresManager}
     */
    private PlayerManager playerManager = new PlayerManager();

    
    /**
     * Character associated with the {@code HighScoresManager}
     */
    private Character character;

    /**
     * objects neeeded to display highscores
     */
    private Stage stage;
    private Group root;
    private List<Label> playerLabels = new ArrayList<>();
    private TextField playerName;

    /**
     * constructor which will add the character whose score we will add
     *
     * @param character character of the {@code HighScoresManager}
     * @param stage stage of GraphicsRepainter
     * @param root Group of GraphicsRepainter
     */
    public HighScoresManager(Character character, Stage stage, Group root) {
        this.character = character;
        this.stage = stage;
        this.root = root;
        this.setPlayers();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = (int) screenSize.getWidth();
        HEIGHT = (int) screenSize.getHeight();
        
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
     * displays the highscores on the screen using paintHighScores method
     */
    public void showHighScores() {
    	//prompts user for their name
        this.playerName = new TextField();
        this.playerName.setPromptText("Please Enter your name");
        this.playerName.setPrefWidth(200);
        Image youWonImage = new Image(SpriteURL.YOU_WON.getPath());
        this.playerName.setTranslateX(WIDTH / 2 - this.playerName.getMinWidth() / 2 - this.playerName.getPrefWidth() / 2);
        this.playerName.setTranslateY(HEIGHT / 3 + youWonImage.getHeight() / 2 + 25);
        //will display score once user writes their name and presses enter
        this.playerName.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    String name = playerName.getText();
                    runHighScores(name);
                    paintHighScores();
                    root.getChildren().remove(playerName);
                    stage.show();
                }
            }
        });
        root.getChildren().add(this.playerName);
        stage.show();
    }

    /**
     * adds player labels to the screen
     */
    public void paintHighScores() {
        double xCord = (WIDTH / 2);
        int y = (int) (HEIGHT * 0.3);
        double adjustedX = 0;
        Font font = new Font("Arial", 30);
        for (Player player : playerManager.getPlayerList()) {
            Label playerLabel = new Label(player.toString());
            root.getChildren().add(playerLabel);
            playerLabel.impl_processCSS(false);

            double width = playerLabel.prefWidth(-1)+50;
            
            adjustedX = xCord - width ;
            root.getChildren().remove(playerLabel);
            playerLabel.setTranslateX(adjustedX);
            playerLabel.setTranslateY(y);
            playerLabel.setFont(font);
            playerLabel.setTextFill(Color.LIMEGREEN);
            root.getChildren().add(playerLabel);

            y += HEIGHT * 0.05;
            this.playerLabels.add(playerLabel);
        }
    }

    /**
     * removes highscores from the screen
     */
    public void hideHighScores() {
        for (Label playerLabel : this.playerLabels) {
            this.root.getChildren().remove(playerLabel);
        }
        this.playerLabels.clear();
        if (this.playerName != null) {
            this.root.getChildren().remove(this.playerName);
            this.playerName = null;
        }
        this.stage.show();
    }

    /**
     * runs all the processes of {@code HighScoresManager} to do with reading and writing to file
     *
     * @param name name given by the user
     */
    private void runHighScores(String name) {
        addCurrentPlayer(name);
        saveHighScores();
    }


    public static void main(String[] args) {
        HighScoresManager highScoresManager = new HighScoresManager(new ET(), null, null);
        highScoresManager.character.setScore(1200);
        String name = "Tester";
        highScoresManager.runHighScores(name);
        for (Player player : highScoresManager.playerManager.getPlayerList()) {
            System.out.println(player.toString());
        }
    }


}