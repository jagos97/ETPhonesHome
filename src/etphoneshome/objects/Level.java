package etphoneshome.objects;

import etphoneshome.entities.enemies.Enemy;
import etphoneshome.entities.enemies.Police;
import etphoneshome.entities.enemies.Scientist;
import etphoneshome.graphics.SpriteURL;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stores all items in a level including, the levelNumber, end coordinate, time-limit, background path, list of obstacles,
 * list of enemies, list of phone pieces, and level image
 */
public class Level {

    /**
     * Arrays of the objects in the level and the number of the level and the endcord to win the game
     */
    private int levelNum, endCord, timeLimit;
    private String backgroundFilePath = SpriteURL.BACKGROUND.getPath();
    private List<Obstacle> obstacles = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private List<Collectible> phonePieces = new ArrayList<>();

    private final Image level1Image = new Image(SpriteURL.LEVEL1.getPath());
    private final Image level2Image = new Image(SpriteURL.LEVEL2.getPath());
    private final Image Level3Image = new Image(SpriteURL.LEVEL3.getPath());

    private JFXPanel jfxPanel = new JFXPanel();

    /**
     * Constructor that sets the level and all the objects in it
     *
     * @param level level of the new {@code level}
     */
    public Level(Level level) {
        this.levelNum = level.getLevelNum();

        this.backgroundFilePath = level.getBackgroundFilePath();

        this.endCord = level.getEndCord();

        this.timeLimit = level.getTimeLimit();

        for (Obstacle obstacle : level.getObstacles()) {
            this.obstacles.add(obstacle);
        }

        for (Enemy enemy : level.getEnemies()) {
            if (enemy instanceof Scientist) {
                this.enemies.add(new Scientist((Scientist) enemy));
            } else if (enemy instanceof Police) {
                this.enemies.add(new Police((Police) enemy));
            }
        }

        this.phonePieces = new ArrayList<>(level.phonePieces);
    }

    /**
     * Creates a new level with the given levelName by reading information from the file with the given levelName + ".txt"
     *
     * @param levelName name of the {@code Level}
     */
    public Level(String levelName) {
        //gets the file
        File file = new File("levels" + File.separator + levelName + ".txt");
        if (!file.exists()) {
            file = new File("ET Phones Home" + File.separator + "src" + File.separator + "levels" + File.separator + levelName + ".txt");
        }

        //reads the file
        int lineNum = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {

                //number of level
                String line = scanner.nextLine().replace("\t", "  ");
                lineNum++;
                if (line.startsWith("level-num: ")) {
                    this.levelNum = Integer.valueOf(line.replace("level-num: ", ""));
                }

                // path of background sprite
                if (line.startsWith("background-sprite: ")) {
                    this.backgroundFilePath = line.replace("background-sprite: ", "");
                }

                // time limit of level
                if (line.startsWith("time-limit: ")) {
                    this.timeLimit = Integer.valueOf(line.replace("time-limit: ", ""));
                }

                //end coordinate of level
                if (line.startsWith("end-cord: ")) {
                    this.endCord = Integer.valueOf(line.replace("end-cord: ", ""));
                }

                // platforms of level
                if (line.equals("  platform:")) {
                    lineNum++;
                    int xCord = Integer.valueOf(scanner.nextLine().replace("\t", "  ").replace("    x-cord: ", ""));
                    lineNum++;
                    int yCord = Integer.valueOf(scanner.nextLine().replace("\t", "  ").replace("    y-cord: ", ""));
                    lineNum++;
                    int length = Integer.valueOf(scanner.nextLine().replace("\t", "  ").replace("    length: ", ""));
                    this.obstacles.add(new Platform(new Location(xCord, yCord), length));
                }

                //police of level
                if (line.equals("  police:")) {
                    lineNum++;
                    int xCord = Integer.valueOf(scanner.nextLine().replace("\t", "  ").replace("    x-cord: ", ""));
                    lineNum++;
                    int yCord = Integer.valueOf(scanner.nextLine().replace("\t", "  ").replace("    y-cord: ", "")) - (int) new Police().getRightEntitySprite().getHeight();
                    Police police = (new Police(new Location(xCord, yCord)));
                    this.enemies.add(police);
                }

                //scientist of level
                if (line.equals("  scientist:")) {
                    lineNum++;
                    int xCord = Integer.valueOf(scanner.nextLine().replace("\t", "  ").replace("    x-cord: ", ""));
                    lineNum++;
                    int yCord = Integer.valueOf(scanner.nextLine().replace("\t", "  ").replace("    y-cord: ", "")) - (int) new Scientist().getRightEntitySprite().getHeight();
                    this.enemies.add(new Scientist(new Location(xCord, yCord)));
                }

                //phone pieces of level
                if (line.equals("  phone-piece:")) {
                    lineNum++;
                    String typeString = scanner.nextLine().replace("    type: ", "");
                    PhonePieceType phonePieceType = PhonePieceType.ANTENNA;
                    if (PhonePieceType.valueOf(typeString) != null) {
                        phonePieceType = PhonePieceType.valueOf(typeString);
                    }
                    lineNum++;
                    int xCord = Integer.valueOf(scanner.nextLine().replace("\t", "  ").replace("    x-cord: ", ""));
                    lineNum++;
                    int yCord = Integer.valueOf(scanner.nextLine().replace("\t", "  ").replace("    y-cord: ", "")) - (int) new PhonePiece(new Location(0, 0), phonePieceType).getHeight();
                    this.phonePieces.add(new PhonePiece(new Location(xCord, yCord), phonePieceType));
                }
            }
        } catch (Exception e) {
            System.out.println("FAILED TO LOAD: " + levelName + ".txt");

            System.out.println("FAILED ON LINE (" + lineNum + ")");
            System.out.println("Exception: \n" + e.getMessage());
            levelNum = -1;
        }
    }

    /**
     * Gets the levelNum
     *
     * @return levelNum
     */
    public int getLevelNum() {
        return this.levelNum;
    }

    /**
     * Returns the backgroundFilePath paired with the {@code Level}
     *
     * @return backgroundFilePath
     */
    public String getBackgroundFilePath() {
        return this.backgroundFilePath;
    }

    /**
     * get the end coordinate of the level
     *
     * @return endCord of {@code Level}
     */
    public int getEndCord() {
        return this.endCord;
    }

    /**
     * get obstacles of the {@code level}
     *
     * @return obstacles of the {@code level}
     */
    public List<Obstacle> getObstacles() {
        return this.obstacles;
    }

    /**
     * get enemies of the {@code level}
     *
     * @return enemies of the {@code level}
     */
    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    /**
     * get phone pieces of the {@code level}
     *
     * @return phone pieces of the {@code level}
     */
    public List<Collectible> getPhonePieces() {
        return this.phonePieces;
    }

    /**
     * Returns the time limit which bounds the level
     *
     * @return timeLimit
     */
    public int getTimeLimit() {
        return this.timeLimit;
    }

    /**
     * Returns the image associated with the level
     *
     * @param i the level number
     * @return levelImage
     */
    public Image getImage(int i) {
        switch (i) {
            case 1:
                return this.level1Image;
            case 2:
                return this.level2Image;
            case 3:
                return this.Level3Image;
        }
        return this.level1Image;
    }

}
