package etphoneshome.graphics;

import etphoneshome.UILauncher;
import etphoneshome.entities.characters.Character;
import etphoneshome.entities.characters.ET;
import etphoneshome.entities.enemies.Enemy;
import etphoneshome.listeners.InputListener;
import etphoneshome.managers.*;
import etphoneshome.objects.*;
import etphoneshome.sound.Sound;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

/**
 * Class responsible for repainting the graphics of the game
 */
public class GraphicsRepainter extends Application {

    /**
     * Width and height of the screen
     */
    public int WIDTH = 1920;
    public int HEIGHT = 1080;

    private int tick = 0;
    private int secs;
    private int mins;


    private int totalTicks = 0;
    /**
     * Current state of the game
     */
    private GameState gameState = GameState.MAIN_MENU;

    private int RENDER_RANGE;

    /**
     * images needed to play the game
     */

    private final Image GAMEOVER = new Image(SpriteURL.GAMEOVER.getPath());

    /**
     * Instances needed to draw on the stage and make the screen
     */
    private Canvas canvas = new Canvas(this.WIDTH, this.HEIGHT);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Group root = new Group();
    private Scene scene = new Scene(root);
    private Stage stage;

    private Velocity ufoVelocity = new Velocity();
    private Location ufoLocation = new Location(0, 0);

    /**
     * Instance associated with running a game loop
     */
    private Timeline timeline = new Timeline();

    private GameManager gameManager = UILauncher.getGameManager();
    private BackgroundManager backgroundManager = UILauncher.getBackgroundManager();
    private FloorManager floorManager = UILauncher.getFloorManager();
    private InputListener inputListener = UILauncher.getInputListener();
    private LevelManager levelManager = UILauncher.getLevelManager();
    private AnimationManager animationManager = UILauncher.getAnimationManager();


    /**
     * A button needed to playAgain the game
     */
    private Button playAgainButton;

    /**
     * A button to load to the next level
     */
    private Button nextLevelButton;

    /**
     * A button used for exiting the game
     */
    private Button exitButton;

    /**
     * Menu control buttons
     */
    private Button playButton;
    private Button instructionsButton;
    private Button highscoresButton;
    private Button backButton;
    private Button mainMenuButton;

    /**
     * Button for people who need to git gud
     */
    private Button giveUpButton;

    /**
     * a Class used to play the sounds of the games
     */
    private Sound sound = new Sound();

    /**
     * A label set to display the current score
     */
    private Label score = new Label();

    /**
     * To display the highscores
     */
    private HighScoresManager highScoresManager;

    public void start(Stage stage) {
        //creating stage
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.WIDTH = (int) screenSize.getWidth();
        this.HEIGHT = (int) screenSize.getHeight();
        this.stage = stage;
        this.createWindow(stage);

        Image UFOImage = new Image(SpriteURL.UFO_UNCUT.getPath());
        this.ufoLocation = new Location(-(int) UFOImage.getWidth(), (this.HEIGHT - 100) - (int) UFOImage.getHeight());

        //making character and setting it's starting point
        Character character = new ET();
        character.setHealth(3);
        UILauncher.setCharacter(character);
        character.setLocation(new Location(gameManager.getCenterXCord(), UILauncher.getGameManager().getGroundLevel(character)));

        this.highScoresManager = new HighScoresManager(character, stage, root);

        this.RENDER_RANGE = (this.WIDTH / 2) + (int) character.getRightEntitySprite().getWidth() / 2;

        //setting up the buttons
        this.setupComponents(character);


        this.registerKeyEvents();
        this.sound.playETTheme();


        Level level = levelManager.getCurrentLevel();
        this.setMinutes((int) Math.floor(level.getTimeLimit() / 60));
        this.setSeconds(level.getTimeLimit() % 60);
        gc.setFont(new Font("Courier New", 50));

        //staring the actual game
        this.startTimeline(character);
    }

    /**
     * Method used to setup components
     *
     * @param character The character object of the game
     */
    private void setupComponents(Character character) {

        // setup score label
        this.score.setText("" + character.getScore());
        this.score.setFont(new Font("Arial", 20));
        this.score.setTranslateX(100);
        this.score.setTranslateY(100);
        this.score.setTextFill(Color.WHITE);

        // creating playAgainButton 
        this.playAgainButton = new Button();
        this.playAgainButton.setText("Play Again");
        this.playAgainButton.setTranslateX(this.WIDTH / 2 - 100);
        this.playAgainButton.setTranslateY(50);
        this.playAgainButton.setPrefSize(200, 100);
        this.playAgainButton.setFont(new Font("Arial", 20));

        // creating exit button
        this.exitButton = new Button();
        this.exitButton.setText("Exit Game");
        this.exitButton.setTranslateX(this.WIDTH - this.WIDTH / 10);
        this.exitButton.setTranslateY(50);
        this.exitButton.setPrefSize(100, 50);
        this.exitButton.setFont(new Font("Arial", 14));

        // creating play button
        this.playButton = new Button();
        this.playButton.setText("Play");
        this.playButton.setTranslateX(this.WIDTH / 2 - 100);
        this.playButton.setTranslateY(100);
        this.playButton.setPrefSize(200, 100);
        this.playButton.setFont(new Font("Arial", 20));

        //creating instructions button
        this.instructionsButton = new Button();
        this.instructionsButton.setText("Instructions");
        this.instructionsButton.setTranslateX(this.WIDTH / 2 - 200);
        this.instructionsButton.setTranslateY(300);
        this.instructionsButton.setPrefSize(400, 100);
        this.instructionsButton.setFont(new Font("Arial", 20));

        //creating highscores button
        this.highscoresButton = new Button();
        this.highscoresButton.setText("High Scores");
        this.highscoresButton.setTranslateX(this.WIDTH / 2 - 150);
        this.highscoresButton.setTranslateY(500);
        this.highscoresButton.setPrefSize(300, 100);
        this.highscoresButton.setFont(new Font("Arial", 20));

        //creating back button
        this.backButton = new Button();
        this.backButton.setText("Back");
        this.backButton.setTranslateX(50);
        this.backButton.setTranslateY(50);
        this.backButton.setPrefSize(100, 50);
        this.backButton.setFont(new Font("Arial", 20));

        // creating next level button
        this.nextLevelButton = new Button();
        this.nextLevelButton.setText("Next Level");
        this.nextLevelButton.setTranslateX(this.WIDTH - this.WIDTH / 4);
        this.nextLevelButton.setTranslateY(50);
        this.nextLevelButton.setPrefSize(200, 100);
        this.nextLevelButton.setFont(new Font("Arial", 20));

        // creating button to give up
        this.giveUpButton = new Button();
        this.giveUpButton.setText("Give Up");
        this.giveUpButton.setTranslateX(this.WIDTH / 2 - 100);
        this.giveUpButton.setTranslateY(200);
        this.giveUpButton.setPrefSize(200, 100);
        this.giveUpButton.setFont(new Font("Arial", 20));

        // creating button to go to main menu
        this.mainMenuButton = new Button();
        this.mainMenuButton.setText("Main Menu");
        this.mainMenuButton.setTranslateX(this.WIDTH / 2 - 100);
        this.mainMenuButton.setTranslateY(100);
        this.mainMenuButton.setPrefSize(200, 100);
        this.mainMenuButton.setFont(new Font("Arial", 20));

        //adding buttons to the main menu
        this.root.getChildren().add(this.exitButton);
        this.root.getChildren().add(this.playButton);
        this.root.getChildren().add(this.instructionsButton);
        this.root.getChildren().add(this.highscoresButton);

        //setting up the button events
        this.setupButtonEvents(character);

    }

    /**
     * Method used to setup button events
     *
     * @param character The character object of the game
     */
    private void setupButtonEvents(Character character) {

        //exits the game
        this.exitButton.setOnMouseClicked(k -> {
            this.timeline.stop();
            this.stage.close();
        });

        this.exitButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE) {
                    stage.setFullScreen(true);
                    if (gameState == GameState.IN_GAME) {
                        if (!root.getChildren().contains(mainMenuButton)) {
                            root.getChildren().add(mainMenuButton);
                        }
                        mainMenuButton.setTranslateY(HEIGHT / 2 - 50);
                        gameState = GameState.PAUSE_MENU;
                        timeline.pause();
                        Image pauseImage = new Image(SpriteURL.PAUSE_MENU.getPath());
                        gc.drawImage(pauseImage, WIDTH / 2 - pauseImage.getWidth() / 2, HEIGHT / 2 - pauseImage.getHeight() / 2);
                    } else if (gameState == GameState.PAUSE_MENU) {
                        mainMenuButton.setTranslateY(100);
                        timeline.play();
                        root.getChildren().remove(mainMenuButton);
                        gameState = GameState.IN_GAME;
                    }
                }
            }
        });

        //resets the level
        this.playAgainButton.setOnMouseClicked(k -> {
            this.totalTicks = 0;
            resetGame(character);
            sound.playTheme();
            root.getChildren().remove(playAgainButton);
            root.getChildren().remove(giveUpButton);

        });

        //starts the game
        this.playButton.setOnMouseClicked(k -> {
            this.totalTicks = 0;
            root.getChildren().remove(playButton);
            root.getChildren().remove(instructionsButton);
            root.getChildren().remove(highscoresButton);
            this.gameState = GameState.IN_GAME;
            if (!root.getChildren().contains(score)) {
                root.getChildren().add(score);
            }
            sound.stopETTheme();
            sound.playTheme();
        });

        //adds the instructions to screen
        this.instructionsButton.setOnMouseClicked(k -> {
            root.getChildren().remove(playButton);
            root.getChildren().remove(instructionsButton);
            root.getChildren().remove(highscoresButton);
            this.gameState = GameState.INSTRUCTIONS_MENU;
            root.getChildren().add(backButton);
        });

        //displays the highscores
        this.highscoresButton.setOnMouseClicked(k -> {
            root.getChildren().remove(playButton);
            root.getChildren().remove(instructionsButton);
            root.getChildren().remove(highscoresButton);
            this.gameState = GameState.HIGHSCORES_MENU;
            root.getChildren().add(backButton);
            this.highScoresManager.paintHighScores();
        });

        //goes back to main menu
        this.backButton.setOnMouseClicked(k -> {
            root.getChildren().add(playButton);
            root.getChildren().add(instructionsButton);
            root.getChildren().add(highscoresButton);
            root.getChildren().remove(backButton);
            if (this.gameState == GameState.HIGHSCORES_MENU) {
                this.highScoresManager.hideHighScores();
            }
            this.gameState = GameState.MAIN_MENU;
        });

        //goes to next level
        this.nextLevelButton.setOnMouseClicked(k -> {
            this.totalTicks = 0;
            LevelManager levelManager = UILauncher.getLevelManager();
            int levelJustCompleted = levelManager.getCurrentLevel().getLevelNum();
            levelManager.loadLevel(levelJustCompleted + 1);     //needs check to see if level + 1 exists
            this.highScoresManager.hideHighScores();

            //resets character position and health
            character.setLocation(new Location(UILauncher.getGameManager().getCenterXCord(), UILauncher.getGameManager().getGroundLevel(character)));
            character.setIsDead(false);
            character.setHealth(3);
            character.setFacingRight(true);
            character.getVelocity().setHorizontalVelocity(0);
            character.getVelocity().setVerticalVelocity(0);

            //sets the new time limit based on the current level
            Level level = UILauncher.getLevelManager().getCurrentLevel();
            this.setMinutes((int) Math.floor(level.getTimeLimit() / 60));
            this.setSeconds(level.getTimeLimit() % 60);

            UILauncher.getCharacter().setHoldingRight(false);
            UILauncher.getCharacter().setHoldingUp(false);

            this.gameState = GameState.IN_GAME;
            timeline.play();

            root.getChildren().remove(nextLevelButton);

        });

        //runs highscores and game no longer playable
        this.giveUpButton.setOnMouseClicked(k -> {
            this.highScoresManager.showHighScores();
            root.getChildren().remove(playAgainButton);
            root.getChildren().remove(giveUpButton);
            root.getChildren().add(mainMenuButton);
            levelManager.loadLevel(1);
        });

        //goes to main menu
        this.mainMenuButton.setOnMouseClicked(k -> {
            this.gameState = GameState.MAIN_MENU;
            this.highScoresManager.hideHighScores();
            root.getChildren().remove(mainMenuButton);
            this.root.getChildren().add(this.playButton);
            this.root.getChildren().add(this.instructionsButton);
            this.root.getChildren().add(this.highscoresButton);
            resetGame(character);
            levelManager.unloadLevel();
            levelManager.loadLevel(1);
            levelManager.getCollectedPhonePieceTypes().clear();
            root.getChildren().remove(score);
            sound.stopTheme();
            sound.playETTheme();

        });

    }

    /**
     * Creates the window
     *
     * @param stage is the stage the window will br created in
     */
    public void createWindow(Stage stage) {
        this.root.getChildren().add(this.canvas);
        stage.setScene(this.scene);
        stage.setFullScreen(true);
        stage.show();
    }

    /**
     * Allows the start method be initiated in  {@code UILauncher}
     *
     * @param args for {@code UILauncher}
     */
    public void goLaunch(String[] args) {
        GraphicsRepainter.launch(args);
    }

    /**
     * Sets up the key event handlers for the game
     */
    public void registerKeyEvents() {
        this.scene.setOnKeyPressed(UILauncher.getInputListener().getKeyPressedEvent());
        this.scene.setOnKeyReleased(UILauncher.getInputListener().getKeyReleasedEvent());
    }

    /**
     * @param mins new mins value
     */
    public void setMinutes(int mins) {
        this.mins = mins;
    }

    /**
     * @param secs new secs value
     */
    public void setSeconds(int secs) {
        this.secs = secs;
    }

    /**
     * starts the timeline the game will run in
     *
     * @param character the player the user will play as
     */
    public void startTimeline(Character character) {
        this.timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);

        KeyFrame kf = new KeyFrame(Duration.millis(20), e -> {
            if (gameState == GameState.IN_GAME) {
            	//getting location and velocity of character
                Velocity velocity = character.getVelocity();
                Location oldLocation = character.getLocation();

                Level level = levelManager.getCurrentLevel();
                boolean levelBeat = true;
                for (Collectible collectible : level.getPhonePieces()) {
                    PhonePiece phonePiece = (PhonePiece) collectible;
                    if (!levelManager.getCollectedPhonePieceTypes().contains(phonePiece.getPhonePieceType())) {
                        levelBeat = false;
                        break;
                    }
                }

                if (levelBeat && oldLocation.getXcord() >= levelManager.getCurrentLevel().getEndCord()) {
                    gameState = GameState.END_GAME;
                    levelManager.setLevelComplete(true);
                    if (levelManager.getCurrentLevel().getLevelNum() == levelManager.getLevels().size() - 1) {
                        character.setHoldingRight(false);
                        character.setHoldingUp(false);
                        velocity.setHorizontalVelocity(0);
                        this.ufoVelocity.setHorizontalVelocity(10);
                    } else {
                        character.setHoldingRight(true);
                        this.inputListener.updateVelocities();
                    }
                    Location newLocation = new Location(oldLocation.getXcord() + (int) velocity.getHorizontalVelocity(), oldLocation.getYcord() + (int) velocity.getVerticalVelocity());
                    character.setLocation(newLocation);
                } else {
                    inputListener.updateVelocities();
                    Location newLocation = new Location(oldLocation.getXcord() + (int) velocity.getHorizontalVelocity(), oldLocation.getYcord() + (int) velocity.getVerticalVelocity());
                    //updates location of character based on the velocity
                    Direction direction = gameManager.runObstacleCollisionCheck(character, oldLocation, newLocation);
                    if (direction == null) {
                        character.setLocation(newLocation);
                        if (newLocation.getXcord() < 0) {
                            newLocation.setXcord(0);
                            character.setLocation(newLocation);
                        } else if (newLocation.getXcord() >= gameManager.getCenterXCord() && newLocation.getXcord() <= levelManager.getCurrentLevel().getEndCord()) {
                            backgroundManager.updateBackgroundLocation();
                            floorManager.updateFloorLocation();
                        } else if (newLocation.getXcord() >= levelManager.getCurrentLevel().getEndCord() + gameManager.getCenterXCord()) {
                            newLocation.setXcord(levelManager.getCurrentLevel().getEndCord() + gameManager.getCenterXCord());
                            character.setLocation(newLocation);
                        }
                    } else if (direction == Direction.ABOVE || direction == Direction.BELOW) {
                        backgroundManager.updateBackgroundLocation();
                        floorManager.updateFloorLocation();
                    }

                    gameManager.runEnemyCheck(oldLocation);
                    gameManager.runCollectibleCheck();
                }

                gameManager.runGroundCheck(character, velocity);

                // check if entity was hurt
                if (gameManager.checkFlasks() || gameManager.wasCharacterHurt()) {
                    character.takeSinglePointOfDamage();
                    sound.takeDamageSound();
                    if (!character.getIsDead()) {
                        character.setInvincible(true);
                        if (character.isFacingRight()) {
                            animationManager.setCharacterAnimation(new Animation(AnimationFrames.ET_HURT_RIGHT));
                        } else {
                            animationManager.setCharacterAnimation(new Animation(AnimationFrames.ET_HURT_LEFT));
                        }
                    }
                }

                // increment animation ticks
                animationManager.incrementAnimations();
                animationManager.runGarbageCollector();
                if (animationManager.getCharacterAnimation() == null) {
                    character.setInvincible(false);
                }

                // repaint view
                this.repaintBackgroundAndObstacles(character);
                this.repaintEntities(character);
                this.repaintCollectibles(character);
                this.repaintFlasks(character);
                this.paintLevelSprite(this.totalTicks);

                //Displaying the time
                if (++tick % 50 == 0) {
                    if (this.secs == 0) {
                        this.secs = 60;
                        this.mins--;
                    }
                    this.tick = 0;
                    this.secs--;
                }
                // if mins * 60 + secs >= timeLimit { END GAME}
                String m;
                if (mins == 0) {
                    m = "00:";
                } else if (mins < 10) {
                    m = "0" + mins + ":";
                } else {
                    m = "" + mins;
                }

                if (secs == 0) {
                    m += "00";
                } else if (secs < 10) {
                    m += "0" + secs;
                } else {
                    m += secs;
                }

                gc.fillText(m, this.WIDTH / 2 - 75, 50, 150);

                //Time is over
                if (mins == 0 && secs == 0) {
                    mins = 2;
                    secs = 2;
                    gc.drawImage(GAMEOVER, WIDTH / 2 - GAMEOVER.getWidth() / 2, HEIGHT / 2 - GAMEOVER.getHeight() / 2);
                    sound.playETDeath();
                    sound.stopTheme();
                    root.getChildren().add(playAgainButton);
                    timeline.pause();
                    UILauncher.getGameManager().setGameOver(true);
                }

                // update score
                this.score.setText("" + character.getScore());

                this.runHealthCheck(character);

                //Move enemy
                gameManager.moveEnemy();
                totalTicks++;
            } else if (gameState == GameState.INSTRUCTIONS_MENU) {
                gc.drawImage(new Image(SpriteURL.INSTRUCTIONS_MENU.getPath()), 0, 0);
            } else if (gameState == GameState.HIGHSCORES_MENU) {
                this.highScoresManager.paintHighScores();
            } else if (gameState == GameState.END_GAME) {

                //sets the view if you win the game
                if (levelManager.isLevelComplete() && character.getLocation().getXcord() >= levelManager.getCurrentLevel().getEndCord() + WIDTH / 2 + character.getRightEntitySprite().getWidth() / 2) {
                    Image youWonImage = new Image(SpriteURL.YOU_WON.getPath());
                    gc.drawImage(youWonImage, WIDTH / 2 - (int) youWonImage.getWidth() / 2, HEIGHT / 3 - (int) youWonImage.getHeight() / 2);
                    sound.playWin();
                    int scoreToAdd = this.mins * 60 + this.secs;
                    character.addScore(scoreToAdd);
                    score.setText("" + character.getScore());
                    timeline.pause();

                    //places next level button
                    this.root.getChildren().add(this.nextLevelButton);
                }

                Location oldLocation = character.getLocation();
                Velocity velocity = character.getVelocity();

                // repaint view
                this.repaintBackgroundAndObstacles(character);
                this.repaintEntities(character);
                if (levelManager.getCurrentLevel().getLevelNum() == levelManager.getLastLevelNum()) {
                    this.repaintUFO(character);
                }
                Location newLocation = new Location(oldLocation.getXcord() + (int) velocity.getHorizontalVelocity(), oldLocation.getYcord() + (int) velocity.getVerticalVelocity());
                character.setLocation(newLocation);
                this.runHealthCheck(character);

            } else if (gameState == GameState.MAIN_MENU) {
            	gc.drawImage(new Image(SpriteURL.MAIN_MENU.getPath()), 0, 0);
            }

            stage.show();
        });

        //starts timeline
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    /**
     * Repaint background and entities
     *
     * @param character {@code Character}
     */
    public void repaintEntities(Character character) {
        Location loc = character.getLocation();

        Animation characterAnimation = animationManager.getCharacterAnimation();
        Image sprite = character.getRightEntitySprite();

        if (characterAnimation != null) {
            sprite = characterAnimation.getSprite();
        } else if (!character.isFacingRight()) {
            sprite = character.getLeftEntitySprite();
        }

        //gets the level of the game
        Level level = levelManager.getCurrentLevel();
        if (loc.getXcord() < gameManager.getCenterXCord()) {
            gc.drawImage(sprite, loc.getXcord(), loc.getYcord());
        } else if (loc.getXcord() > level.getEndCord()) {
            gc.drawImage(sprite, loc.getXcord() - level.getEndCord() + UILauncher.getGameManager().getCenterXCord(), loc.getYcord());
        } else {
            gc.drawImage(sprite, WIDTH / 2 - character.getLeftEntitySprite().getWidth() / 2, loc.getYcord());
        }

        //debug modes sets outline around hitboxes (for testing)
        if (UILauncher.getDebugMode()) {
            int height = (int) character.getRightEntitySprite().getHeight();
            int width = (int) character.getRightEntitySprite().getWidth();
            this.drawHitbox(character, loc, height, width, Color.GREEN);
        }

        //draws enemies
        for (Enemy enemy : UILauncher.getEntityManager().getEnemyList()) {

            if (Math.abs(character.getLocation().getXcord() - enemy.getLocation().getXcord()) <= RENDER_RANGE) {
                sprite = enemy.getRightEntitySprite();
                if (animationManager.getEnemyDeathAnimation(enemy) != null) {
                    sprite = animationManager.getEnemyDeathAnimation(enemy).getSprite();
                } else if (!enemy.isFacingRight()) {
                    sprite = enemy.getLeftEntitySprite();
                }

                if (loc.getXcord() < gameManager.getCenterXCord()) {
                    gc.drawImage(sprite, enemy.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2) - levelManager.getCurrentLevel().getEndCord(), enemy.getLocation().getYcord());
                } else if (loc.getXcord() < gameManager.getCenterXCord()) {
                    gc.drawImage(sprite, enemy.getLocation().getXcord() - gameManager.getCenterXCord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), enemy.getLocation().getYcord());
                } else {
                    gc.drawImage(sprite, enemy.getLocation().getXcord() - loc.getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), enemy.getLocation().getYcord());
                }
            }


            if (UILauncher.getDebugMode()) {
                int height = (int) enemy.getRightEntitySprite().getHeight();
                int width = (int) enemy.getRightEntitySprite().getWidth();
                this.drawHitbox(character, loc, height, width, Color.RED);
            }
        }
    }

    public void repaintUFO(Character character) {
        Image UFOImage;

        Animation endGameAnimation = this.animationManager.getEndGameAnimation();
        if (endGameAnimation != null) {
            if (endGameAnimation.getAnimationFrames() == AnimationFrames.END_GAME_ANIMATION_UP) {
                character.getVelocity().setVerticalVelocity(-5);
            }

            UFOImage = endGameAnimation.getSprite();
            gc.drawImage(UFOImage, ufoLocation.getXcord(), ufoLocation.getYcord());

            // increment animation ticks
            Animation endGameAnimationOld = animationManager.getEndGameAnimation();
            animationManager.incrementAnimations();
            animationManager.runGarbageCollector();

            if (endGameAnimationOld != null && endGameAnimationOld.getAnimationFrames() == AnimationFrames.END_GAME_ANIMATION_DOWN && animationManager.getEndGameAnimation() == null) {
                character.getVelocity().setVerticalVelocity(-5);
                character.setJumping(false);
                this.animationManager.createEndGameAnimationUp();
            }

            if (endGameAnimationOld != null && endGameAnimationOld.getAnimationFrames() == AnimationFrames.END_GAME_ANIMATION_UP && animationManager.getEndGameAnimation() == null) {
                ufoVelocity.setVerticalVelocity(-5);
            }

        } else {

            this.ufoLocation = new Location((int) (this.ufoLocation.getXcord() + this.ufoVelocity.getHorizontalVelocity()), ufoLocation.getYcord() + (int) this.ufoVelocity.getVerticalVelocity());
            UFOImage = new Image(SpriteURL.UFO.getPath());

            if (ufoLocation.getXcord() >= this.gameManager.getCenterXCord() - (UFOImage.getWidth() / 2) && this.animationManager.getEndGameAnimation() == null) {
                ufoVelocity = new Velocity();
                ufoLocation = new Location(this.gameManager.getCenterXCord() - (int) (UFOImage.getWidth() / 2) - 1, ufoLocation.getYcord());
                this.animationManager.createEndGameAnimationDown();
            }

            if (character.getLocation().getYcord() < -character.getRightEntitySprite().getHeight()) {
                //you won run end game stuff as usual
                Image youWonImage = new Image(SpriteURL.YOU_WON.getPath());
                gc.drawImage(youWonImage, WIDTH / 2 - (int) youWonImage.getWidth() / 2, HEIGHT / 3 - (int) youWonImage.getHeight() / 2);
                sound.playWin();
                timeline.pause();
                this.highScoresManager.showHighScores();
                root.getChildren().add(mainMenuButton);
            }
            gc.drawImage(UFOImage, ufoLocation.getXcord(), ufoLocation.getYcord());
        }
    }

    /**
     * draws the background, obstacles of the level
     *
     * @param character character the player is using
     */
    public void repaintBackgroundAndObstacles(Character character) {
        BackgroundManager backgroundManager = UILauncher.getBackgroundManager();
        FloorManager floorManager = UILauncher.getFloorManager();

        Location backgroundLoc = backgroundManager.getBackgroundLocation();
        gc.drawImage(backgroundManager.getBackgroundSprite(), backgroundLoc.getXcord(), backgroundLoc.getYcord());

        Location floorLoc = floorManager.getFloorLocation();
        gc.drawImage(floorManager.getFloorSprite(), floorLoc.getXcord(), floorLoc.getYcord());

        Location loc = character.getLocation();

        Image finishLineSprite;

        //following if's set the finish line sprite based off the current level. Default sprite is level-0 sprite
        if (levelManager.getCurrentLevel().getLevelNum() == 1) {
            finishLineSprite = new Image(SpriteURL.FINISHLINE_LEVEL_1.getPath());
        } else if (levelManager.getCurrentLevel().getLevelNum() == 2) {
            finishLineSprite = new Image(SpriteURL.FINISHLINE_LEVEL_2.getPath());
        } else if (levelManager.getCurrentLevel().getLevelNum() == 3) {
            finishLineSprite = new Image(SpriteURL.FINISHLINE_LEVEL_3.getPath());
        } else {
            finishLineSprite = new Image(SpriteURL.FINISHLINE_LEVEL_0.getPath());
        }

        int y = gameManager.getGroundLevel(character) + (int) character.getRightEntitySprite().getHeight() - (int) finishLineSprite.getHeight();
        //draws finish line based off phone pieces collected and current level number
        if (levelManager.isLevelComplete()) { //when the background stops and player keeps moving
            gc.drawImage(finishLineSprite, (levelManager.getCurrentLevel().getEndCord()) + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2) - levelManager.getCurrentLevel().getEndCord(), y);
        } else if (levelManager.getPhonePiecesLeft() == 0 && (levelManager.getCurrentLevel().getLevelNum() == 0 || levelManager.getCurrentLevel().getLevelNum() == 3)) { //this is used to make the finish line not randomly "pop" into the window
            if (loc.getXcord() >= levelManager.getCurrentLevel().getEndCord() - 1200) {
                gc.drawImage(finishLineSprite, (levelManager.getCurrentLevel().getEndCord()) - loc.getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), y);
            }
        } else if (levelManager.getPhonePiecesLeft() == 2 && levelManager.getCurrentLevel().getLevelNum() == 1) {   //collected 1 phone piece
            if (loc.getXcord() >= levelManager.getCurrentLevel().getEndCord() - 1200) {
                gc.drawImage(finishLineSprite, (levelManager.getCurrentLevel().getEndCord()) - loc.getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), y);
            }
        } else if (levelManager.getPhonePiecesLeft() == 1 && levelManager.getCurrentLevel().getLevelNum() == 2) {   //collected 2 phone pieces
            if (loc.getXcord() >= levelManager.getCurrentLevel().getEndCord() - 1200) {
                gc.drawImage(finishLineSprite, (levelManager.getCurrentLevel().getEndCord()) - loc.getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), y);
            }
        }

        //drawing obstacles
        int count = 0;
        for (Obstacle obstacle : UILauncher.getObstacleManager().getObstacleList()) {

            if (obstacle instanceof Platform) {
                Platform platform = (Platform) obstacle;
                int rightEndCord = platform.getRealLength() + platform.getLocation().getXcord();
                if (Math.abs(loc.getXcord() - obstacle.getLocation().getXcord()) <= this.RENDER_RANGE || Math.abs(loc.getXcord() - rightEndCord) <= this.RENDER_RANGE) {
                    count++;
                    int endCord = levelManager.getCurrentLevel().getEndCord();
                    if (loc.getXcord() > endCord) {
                        gc.drawImage(new Image(SpriteURL.SINGLE_PLATFORM.getPath()), platform.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2) - endCord, platform.getLocation().getYcord());

                        if (platform.getLength() == 1) {  //single platform of length 1
                            gc.drawImage(new Image(SpriteURL.SINGLE_PLATFORM.getPath()), platform.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2) - endCord, platform.getLocation().getYcord());
                        } else if (platform.getLength() > 1) {
                            for (int i = 0; i <= platform.getLength(); i++) {
                                if (i == 0) { //left end brick
                                    gc.drawImage(new Image(SpriteURL.LEFT_END_PLATFORM.getPath()), (60 * i) + platform.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2) - endCord, platform.getLocation().getYcord());
                                } else if (i < platform.getLength() - 1) { //middle bricks
                                    gc.drawImage(new Image(SpriteURL.REGULAR_PLATFORM.getPath()), (60 * i) + platform.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2) - endCord, platform.getLocation().getYcord());
                                } else if (i == platform.getLength() - 1) { //right end bricks
                                    gc.drawImage(new Image(SpriteURL.RIGHT_END_PLATFORM.getPath()), (60 * i) + platform.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2) - endCord, platform.getLocation().getYcord());
                                }
                            }
                        }
                    } else if (loc.getXcord() < gameManager.getCenterXCord()) {
                        if (platform.getLength() == 1) {  //single platform of length 1
                            gc.drawImage(new Image(SpriteURL.SINGLE_PLATFORM.getPath()), platform.getLocation().getXcord() - gameManager.getCenterXCord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), platform.getLocation().getYcord());
                        } else if (platform.getLength() > 1) {
                            for (int i = 0; i <= platform.getLength(); i++) {
                                if (i == 0)  //left end brick
                                {
                                    gc.drawImage(new Image(SpriteURL.LEFT_END_PLATFORM.getPath()), (60 * i) + platform.getLocation().getXcord() - gameManager.getCenterXCord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), platform.getLocation().getYcord());
                                } else if (i < platform.getLength() - 1)  //middle bricks
                                {
                                    gc.drawImage(new Image(SpriteURL.REGULAR_PLATFORM.getPath()), (60 * i) + platform.getLocation().getXcord() - gameManager.getCenterXCord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), platform.getLocation().getYcord());
                                } else if (i == platform.getLength() - 1)  //right end bricks
                                {
                                    gc.drawImage(new Image(SpriteURL.RIGHT_END_PLATFORM.getPath()), (60 * i) + platform.getLocation().getXcord() - gameManager.getCenterXCord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), platform.getLocation().getYcord());
                                }
                            }
                        }
                    } else {
                        if (platform.getLength() == 1) {  //single platform of length 1
                            gc.drawImage(new Image(SpriteURL.SINGLE_PLATFORM.getPath()), platform.getLocation().getXcord() - loc.getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), platform.getLocation().getYcord());
                        } else if (platform.getLength() > 1) {
                            for (int i = 0; i <= platform.getLength(); i++) {
                                if (i == 0)  //left end brick
                                {
                                    gc.drawImage(new Image(SpriteURL.LEFT_END_PLATFORM.getPath()), (60 * i) + platform.getLocation().getXcord() - loc.getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), platform.getLocation().getYcord());
                                } else if (i < platform.getLength() - 1)  //middle bricks
                                {
                                    gc.drawImage(new Image(SpriteURL.REGULAR_PLATFORM.getPath()), (60 * i) + platform.getLocation().getXcord() - loc.getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), platform.getLocation().getYcord());
                                } else if (i == platform.getLength() - 1)  //right end bricks
                                {
                                    gc.drawImage(new Image(SpriteURL.RIGHT_END_PLATFORM.getPath()), (60 * i) + platform.getLocation().getXcord() - loc.getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), platform.getLocation().getYcord());
                                }
                            }
                        }
                    }
                    
                    /*
                    if (UILauncher.getDebugMode()) {
                        int height = 30;
                        int width = 60 * platform.getLength();
                        this.drawHitbox(character, loc, height, width, Color.ORANGE);

                    }
                    */
                }
            }
        }

    }

    /**
     * repaints the collectible's of the level, paints collected phone pieces under hearts when collected.
     *
     * @param character Character variable
     */
    public void repaintCollectibles(Character character) {
        Location loc = character.getLocation();

        //drawing collectibles
        for (Collectible collectible : UILauncher.getCollectiblesManager().getCollectiblesList()) {

            if (Math.abs(loc.getXcord() - collectible.getLocation().getXcord()) <= RENDER_RANGE) {

                if (loc.getXcord() > levelManager.getCurrentLevel().getEndCord()) {
                    gc.drawImage(collectible.getTheImage(), collectible.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2) - levelManager.getCurrentLevel().getEndCord(), collectible.getLocation().getYcord());
                } else if (loc.getXcord() <= gameManager.getCenterXCord()) {
                    gc.drawImage(collectible.getTheImage(), collectible.getLocation().getXcord() - gameManager.getCenterXCord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), collectible.getLocation().getYcord());
                } else {
                    gc.drawImage(collectible.getTheImage(), collectible.getLocation().getXcord() - loc.getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), collectible.getLocation().getYcord());
                }

                if (UILauncher.getDebugMode()) {
                    this.drawHitbox(character, collectible.getLocation(), (int) collectible.getTheImage().getHeight(), (int) collectible.getTheImage().getWidth(), Color.YELLOW);
                }
            }

        }

        int heartWidth = (int) new Image(SpriteURL.HEART.getPath()).getWidth();
        int x = -heartWidth / 2;
        for (PhonePieceType type : PhonePieceType.values()) {
            x += 25 + heartWidth;
            if (levelManager.getCollectedPhonePieceTypes().contains(type)) {
                if (type == PhonePieceType.ANTENNA) {
                    Image antenna = new Image(SpriteURL.PHONE_ANTENNA.getPath());
                    gc.drawImage(antenna, x - (antenna.getWidth() / 2), 75);
                } else if (type == PhonePieceType.CHASSIS) {
                    Image chassis = new Image(SpriteURL.PHONE_CHASSIS.getPath());
                    gc.drawImage(chassis, x - (chassis.getWidth() / 2), 75);
                } else if (type == PhonePieceType.KEYPAD) {
                    Image keypad = new Image(SpriteURL.PHONE_KEYPAD.getPath());
                    gc.drawImage(keypad, x - (keypad.getWidth() / 2), 75);
                }
            }
        }
    }

    public void repaintFlasks(Character character) {
        for (Flask flask : UILauncher.getFlaskManager().getFlaskList()) {
            gc.drawImage(flask.getSprite(), flask.getLocation().getXcord() - character.getLocation().getXcord() + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), flask.getLocation().getYcord());
        }
    }

    /**
     * Check if character is dead. If not dead, draw hearts and continue playing. If dead pause timeline and sets a playAgainButton on screen that will
     * reset the game if the user clicks it
     *
     * @param character {@code Character}
     */
    public void runHealthCheck(Character character) {
        if (!character.getIsDead()) {
            int heartWidth = (int) new Image(SpriteURL.HEART.getPath()).getWidth();
            int x = 25;
            for (int i = 0; i < character.getHealth(); i++) {
                this.gc.drawImage(new Image(SpriteURL.HEART.getPath()), x, 25);
                x += heartWidth + 25;
            }
            this.score.setTranslateX(x);
            this.score.setTranslateY(25 + new Image(SpriteURL.HEART.getPath()).getHeight() / 2 - 10);
        } else {
            gc.drawImage(GAMEOVER, WIDTH / 2 - GAMEOVER.getWidth() / 2, HEIGHT / 2 - GAMEOVER.getHeight() / 2);
            sound.playETDeath();
            sound.stopTheme();
            root.getChildren().add(playAgainButton);
            timeline.pause();
            gameManager.setGameOver(true);
            root.getChildren().add(giveUpButton);

        }
    }

    public void paintLevelSprite(int tick) {
        //will paint level sprite for 5 seconds
        if (tick <= 250) {
            int level = UILauncher.getLevelManager().getCurrentLevel().getLevelNum();
            if (level >= 0 && level <= 3) {
                gc.drawImage(UILauncher.getLevelManager().getCurrentLevel().getImage(level), WIDTH / 2 - 128 / 2, HEIGHT / 2 + 128 / 2);
            }

        }
    }

    public void resetGame(Character character) {
        //resets character position and health
        character.setLocation(new Location(UILauncher.getGameManager().getCenterXCord(), UILauncher.getGameManager().getGroundLevel(character)));
        character.setIsDead(false);
        character.setHealth(3);
        character.setFacingRight(true);
        character.getVelocity().setHorizontalVelocity(0);
        character.getVelocity().setVerticalVelocity(0);
        character.setScore(0);
        UILauncher.getGameManager().setGameOver(false);
        LevelManager levelManager = UILauncher.getLevelManager();

        //unloads and loads the current level. Fixes bug that makes enemies not work properly
        int currentLevel = levelManager.getCurrentLevel().getLevelNum();
        levelManager.unloadLevel();
        levelManager.loadLevel(currentLevel);

        UILauncher.getFlaskManager().clearFlasks();

        //resets the timer
        Level level = UILauncher.getLevelManager().getCurrentLevel();
        this.setMinutes((int) Math.floor(level.getTimeLimit() / 60));
        this.setSeconds(level.getTimeLimit() % 60);
        for (Collectible phonePiece : level.getPhonePieces()) {
            levelManager.getCollectedPhonePieceTypes().remove(((PhonePiece) phonePiece).getPhonePieceType());
        }

        //starts timeline again
        timeline.play();
    }


    /**
     * Draws hitbox using given location, height, and width
     *
     * @param character The character object of the game
     * @param loc       Top left corner {@code Location} of {@code Hitbox}
     * @param height    Height of {@code Hitbox}
     * @param width     Width of {@code Hitbox}
     * @param color     The color of the hitbox to be drawn
     */
    public void drawHitbox(Character character, Location loc, int height, int width, Color color) {
        loc = new Location(loc.getXcord() + (-character.getLocation().getXcord()) + (this.WIDTH / 2 - (int) character.getRightEntitySprite().getWidth() / 2), loc.getYcord());
        gc.setStroke(color);
        gc.setLineWidth(2);

        gc.strokeLine(loc.getXcord(), loc.getYcord(), loc.getXcord(), loc.getYcord() + height);

        gc.strokeLine(loc.getXcord(), loc.getYcord() + height, loc.getXcord() + width, loc.getYcord() + height);

        gc.strokeLine(loc.getXcord() + width, loc.getYcord() + height, loc.getXcord() + width, loc.getYcord());

        gc.strokeLine(loc.getXcord() + width, loc.getYcord(), loc.getXcord(), loc.getYcord());
    }

}
