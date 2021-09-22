package etphoneshome.entities.characters;

import etphoneshome.entities.actor.Actor;
import etphoneshome.objects.Location;


/**
 * This is used for a generic character (non-enemy) class. It is derived from the {@code Actor} class.
 */

public abstract class Character extends Actor {

    /**
     * booleans of {@code Character}
     */
    private boolean isJumping, isHoldingLeft, isHoldingRight, isHoldingUp, onPlatform, invincible;

    /**
     * score of {@code Character}
     */
    private int score = 0;

    /**
     * empty default constructor
     */
    public Character() {
    }

    /**
     * Constructor with initial Location of {@code Character}
     *
     * @param location location of {@code Character}
     */
    public Character(Location location) {
        super(location);
    }

    /**
     * Returns true if the {@code Character} is jumping
     *
     * @return {@code isJumping}
     */
    public boolean isJumping() {
        return isJumping;
    }

    /**
     * Updates the {@code isJumping} value
     *
     * @param jumping New isJumping value
     */
    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    /**
     * Returns true if the user is holding down the left key
     *
     * @return {@code isHoldingLeft}
     */
    public boolean isHoldingLeft() {
        return isHoldingLeft;
    }

    /**
     * Updates the {@code holdingLeft} value
     *
     * @param holdingLeft New holdingLeft value
     */
    public void setHoldingLeft(boolean holdingLeft) {
        isHoldingLeft = holdingLeft;
        if (holdingLeft == true) {
            this.setFacingRight(false);
        }
    }

    /**
     * Returns true if the user is holding down the right key
     *
     * @return {@code isHoldingRight}
     */
    public boolean isHoldingRight() {
        return isHoldingRight;
    }

    /**
     * Updates the {@code holdingRight} value
     *
     * @param holdingRight New holdingRight value
     */
    public void setHoldingRight(boolean holdingRight) {
        isHoldingRight = holdingRight;
    }

    /**
     * Returns true if the user is holding down the up key
     *
     * @return {@code isHoldingUp}
     */
    public boolean isHoldingUp() {
        return isHoldingUp;
    }

    /**
     * Updates the {@code holdingUp} value
     *
     * @param holdingUp New holdingUp value
     */
    public void setHoldingUp(boolean holdingUp) {
        isHoldingUp = holdingUp;
    }

    /**
     * Returns true if the {@code Character} is on a platform
     *
     * @return {@code isHoldingRight}
     */
    public boolean isOnPlatform() {
        return onPlatform;
    }

    /**
     * Updates the {@code onPlatform} value
     *
     * @param onPlatform New onPlatform value
     */
    public void setOnPlatform(boolean onPlatform) {
        this.onPlatform = onPlatform;
    }

    /**
     * Return true if the player was recently hurt and is temporarily invincible
     *
     * @return invincible value
     */
    public boolean isInvincible() {
        return this.invincible;
    }

    /**
     * Updates the invincible variable
     *
     * @param invincible New invincible variable
     */
    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    /**
     * Increments the score by the given amount {@code num}
     *
     * @param num Value to be added to score
     */
    public void addScore(int num) {
        this.score += num;
    }

    /**
     * Returns the current score of the {@code Character}
     *
     * @return current score of the {@code Character}
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Updates the score to a certain value
     *
     * @param score New score value
     */
    public void setScore(int score) {
        this.score = score;
    }

    //main tests the class methods
    public static void main(String[] args) {
        Character c = new ET();
        //test the getter and setter for isDead

        if (c.getIsDead()) //should be false originally
        {
            System.out.println("This should not have been printed. Character should be alive (but is dead here)");
        } else {
            System.out.println("Character is alive. This is the correct outcome");
        }

        c.setIsDead(true);

        if (c.getIsDead()) //should be true
        {
            System.out.println("Character is dead. This is the correct outcome.");
        } else {
            System.out.println("Character is alive. This is not the correct outcome");
        }

        System.out.println("\n");

        //test the getter and setters for location

        Location testLoc;
        Location placeIntoTestLoc = new Location(123, 234);

        testLoc = c.getLocation();  //Sets the initial (0,0) location to the testLoc location

        System.out.println("testLoc x coordinate (should be 0): " + testLoc.getXcord());
        System.out.println("testLoc y coordinate (should be 0): " + testLoc.getYcord());

        c.setLocation(placeIntoTestLoc);    //sets location in c to the new values

        testLoc = c.getLocation();  //gets the location from c which is now changed

        System.out.println("testLoc x coordinate (should be 123): " + testLoc.getXcord());
        System.out.println("testLoc y coordinate (should be 234): " + testLoc.getYcord());

    }
}