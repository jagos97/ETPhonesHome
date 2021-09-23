package etphoneshome.entities.characters;

import etphoneshome.entities.actor.Actor;
import etphoneshome.objects.Location;
import etphoneshome.objects.Platform;

/**
 * This is used for a generic character (non-enemy) class. It is derived from the {@code Actor} class.
 */
public abstract class Character extends Actor {
    /**
     * The score associated with the {@code Character}
     */
    private int score = 0;

    /**
     * Boolean to see if {@code Character} completed all objectives and ready to beat the level
     */
    private boolean canWin;

    private Platform standingOnPlatform;

    /**
     * Default constructor
     */
    public Character() {

    }

    /**
     * Constructor to set the {@code Location} of {@code Character}
     *
     * @param location The {@code Location} of {@code Character}
     */
    public Character(Location location) {
        super(location);
    }

    /**
     * Adds {@code amount} to the score of the {@code Character}
     *
     * @param amount The amount to add to the score of the {@code Character}
     */
    public void addScore(int amount) {
        this.score += amount;
    }

    /**
     * Returns the score of the {@code Character}
     *
     * @return The score of the {@code Character}
     */
    public int getScore() {
        return this.score;
    }

    /**
     * sets the canWin status of {@code Character}
     *
     * @param canWin new canWin status of {@code Character}
     */
    public void setCanWin(boolean canWin) {
        this.canWin = canWin;
    }


    /**
     * gets the canWin status of {@code Character}
     *
     * @return canWIn status of {@code Character}
     */
    public boolean getCanWin() {
        return this.canWin;
    }

    public void setStandingOnPlatform(Platform platform) {
        this.standingOnPlatform = platform;
    }

    public Platform getStandingOnPlatform() {
        return standingOnPlatform;
    }

    //main tests the class methods
/*	public static void main(String[] args)
	{
        Location l1 = new Location(987,654);

        Character c1 = new Character();
        Character c2 = new Character(l1);
        Character c3 = new Character(l1);

        l1.setXcord(111);
        l1.setYcord(222);

        System.out.println("Should be 111: " + l1.getXcord());
        System.out.println("Should be 222: " + l1.getYcord());

        System.out.println("Should be 987: " + c2.getLocation().getXcord());
        System.out.println("Should be 654: " + c2.getLocation().getYcord());

        System.out.println("Should be 0: " + c1.getLocation().getXcord());
        System.out.println("Should be 0: " + c1.getLocation().getYcord());

        c1.setHealth(5);
        c2.setHealth(0);
        c3.setHealth(-1);

        System.out.println("Should be 5: " + c1.getHealth());
        System.out.println("Should be 0: " + c2.getHealth());
        System.out.println("Should be 1: " + c3.getHealth());

        System.out.println("Should be false: " + c1.getIsDead());
        System.out.println("Should be true: " + c2.getIsDead());
        System.out.println("Should be false: " + c3.getIsDead());

        c3.takeSinglePointOfDamage();

        System.out.println("Should be true: " + c3.getIsDead());

        c1.takeSinglePointOfDamage();
        c1.takeSinglePointOfDamage();
        c1.takeSinglePointOfDamage();
        c1.takeSinglePointOfDamage();

        System.out.println("Should be false: " + c1.getIsDead());

        c1.takeSinglePointOfDamage();

        System.out.println("Should be true: " + c1.getIsDead());

        c1.setHealth(2);

        System.out.println("Should be false: " + c1.getIsDead());

        c1.setIsDead(true);

        System.out.println("Should be true: " + c1.getIsDead());

        System.out.println("Should be 0: " + c1.getScore());
        System.out.println("Should be 0: " + c2.getScore());
        System.out.println("Should be 0: " + c3.getScore());

        c1.addScore(50);

        System.out.println("Should be 50: " + c1.getScore());
    }
*/
}
