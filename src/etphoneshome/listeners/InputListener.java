package etphoneshome.listeners;

import etphoneshome.entities.characters.Character;
import etphoneshome.managers.*;
import etphoneshome.objects.Direction;
import etphoneshome.objects.Flask;
import etphoneshome.objects.Velocity;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * This class gets input from the user and updates the character based on that input
 */

public class InputListener {

    /**
     * character associated with {@code InputLIstener}
     */
    private Character character;

    /**
     * Stores backgroundManager to be used to update background velocity/location
     */
    private BackgroundManager backgroundManager;

    /**
     * Stores floorManager to be used to update floor velocity/location
     */
    private FloorManager floorManager;

    /**
     * Stores gameManager to be used to calculate ground level
     */
    private GameManager gameManager;

    /**
     * Stores levelManager used to check if level is complete
     */
    private LevelManager levelManager;

    /**
     * Used to update the velocity of flasks (gravity)
     */
    private FlaskManager flaskManager;

    /**
     * Used to update direction of character animation
     */
    private AnimationManager animationManager;

    /**
     * Constructor for the class
     *
     * @param character         gives InputListener the character associated with InputListener
     * @param backgroundManager stores the backgroundManager in order to use it later
     * @param gameManager       stores the gameManager object used to check the ground level
     * @param floorManager		stores the floormanager in order to move the floor
     * @param levelManager		stores the levelManager in order to run cecks
     * @param flaskManager   	stores the flasks to move them
     * @param animationManager  stores the animations to change sprites
     */
    public InputListener(Character character, BackgroundManager backgroundManager, FloorManager floorManager, GameManager gameManager, LevelManager levelManager, FlaskManager flaskManager, AnimationManager animationManager) {
    	this.character = character;
        this.backgroundManager = backgroundManager;
        this.floorManager = floorManager;
        this.gameManager = gameManager;
        this.levelManager = levelManager;
        this.flaskManager = flaskManager;
        this.animationManager = animationManager;
    }

    /**
     * Sets the character of {@code InputListener}
     *
     * @param character character of {@code InputListener}
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    public static void main(String[] args) {
        // add new testing code
    }


    /**
     * Generate {@code EventHandler} for the key pressed down event
     *
     * @return {@code EventHandler} for the key pressed down event
     */
    public EventHandler<KeyEvent> getKeyPressedEvent() {

        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (!levelManager.isLevelComplete()) {
                    String input = e.getText().toLowerCase();
                    if (input.equals("w") || input.equals("up")) {
                        character.setHoldingUp(true);
                    }
                    if (input.equals("a") || input.equals("left")) {
                        character.setHoldingLeft(true);
                    }
                    if (input.equals("d") || input.equals("right")) {
                        character.setHoldingRight(true);
                    }
                }
            }
        };
    }

    /**
     * Generate {@code EventHandler} for the key released event
     *
     * @return {@code EventHandler} for the key released event
     */
    public EventHandler<KeyEvent> getKeyReleasedEvent() {

        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (!levelManager.isLevelComplete()) {
                    String input = e.getText().toLowerCase();
                    if (input.equals("w")) {
                        character.setHoldingUp(false);
                    }
                    if (input.equals("a")) {
                        character.setHoldingLeft(false);
                    }
                    if (input.equals("d")) {
                        character.setHoldingRight(false);
                    }
                }
            }
        };
    }

    /**
     * Update the character's velocity based on the tick of the game
     */
    public void updateVelocities() {
        Velocity velocity = this.character.getVelocity();

        //gets direction the character is facing
        if (velocity.getHorizontalVelocity() > 0) {
            if (!this.character.isFacingRight()) {
                animationManager.flipCharacterAnimationFrames(Direction.EAST);
            }
            this.character.setFacingRight(true);
        } else if (velocity.getHorizontalVelocity() < 0) {
            if (this.character.isFacingRight()) {
                animationManager.flipCharacterAnimationFrames(Direction.WEST);
            }
            this.character.setFacingRight(false);
        }

        if (this.character.isHoldingRight() && velocity.getHorizontalVelocity() >= 0 && velocity.getHorizontalVelocity() <= 10) {
            //changes velocity if moving right
            velocity.changeHorizontalVelocity(1);
        } else if (!this.character.isHoldingRight() && velocity.getHorizontalVelocity() > 0) {
            double newVelocity = velocity.getHorizontalVelocity() - 1 < 0 ? 0 : velocity.getHorizontalVelocity() - 1;
            velocity.setHorizontalVelocity(newVelocity);
        }

        if (this.character.isHoldingLeft() && velocity.getHorizontalVelocity() <= 0 && velocity.getHorizontalVelocity() >= -10) {
            //changes velocity is moving left
            velocity.changeHorizontalVelocity(-1);
        } else if (!this.character.isHoldingLeft() && velocity.getHorizontalVelocity() < 0) {
            double newVelocity = velocity.getHorizontalVelocity() + 1 > 0 ? 0 : velocity.getHorizontalVelocity() + 1;
            velocity.setHorizontalVelocity(newVelocity);
        }

        // gravity
        if (this.character.isJumping()) {
            velocity.changeVerticalVelocity(1);
        }

        // jumping
        if (this.character.isHoldingUp() && !character.isJumping()) {
            this.character.setJumping(true);
            velocity.setVerticalVelocity(-20);
        }

        //move floor background and flasks
        this.updateBackgroundVelocity();
        this.updateFloorVelocity();
        this.updateFlaskVelocities();

    }

    /**
     * changes the velocity of the current flasks
     */
    public void updateFlaskVelocities() {
        for (Flask flask : this.flaskManager.getFlaskList()) {
            flask.getVelocity().changeVerticalVelocity(1);
        }
    }

    /**
     * moves the background of the stage
     */
    private void updateBackgroundVelocity() {
        backgroundManager.getBackgroundVelocity().setHorizontalVelocity(this.character.getVelocity().getHorizontalVelocity() / -2.0);
    }

    /**
     * moves the background of the stage
     */
    private void updateFloorVelocity() {
        floorManager.getFloorVelocity().setHorizontalVelocity(this.character.getVelocity().getHorizontalVelocity() / -1.0);
    }
}



