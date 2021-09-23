package etphoneshome.entities.actor;

import etphoneshome.objects.Location;

/**
 * This class is a super class for any entity in the game (characters, player, enemies). Using the
 * getter and setter for location will return or set the location object for the {@code Actor}.
 * Using the getter and setter for isDead will return or set the status of if the {@code Actor} is
 * currently dead or alive. Using the getter and setter for health will return or set the current
 * health/number of lives of the {@code Actor}. Using the takeSinglePointOfDamage method will apply
 * one damage point to the {@code Actor}'s health/number of lives.
 */
public abstract class Actor {

    /**
     * Status of whether this {@code Actor} is dead
     */
    private boolean isDead = false;    //Should be set to true if the Actor dies

    /**
     * {@code Location} associated with the {@code Actor}
     */
    private Location location = new Location(0, 0);

    /**
     * Amount of lives/health associated with the {@code Actor}
     */
    private int health = 1;     //default value of 1 life given

    /**
     * Default constructor
     */
    public Actor() {

    }

    /**
     * Constructor to set the {@code Location} of the {@code Actor}
     *
     * @param location The {@code Location} of the {@code Actor}
     */
    public Actor(Location location) {
        this.setLocation(location);
    }

    /**
     * Returns the {@code Location} associated with the {@code Actor}
     *
     * @return The {@code Location} associated with the {@code Actor}
     */
    public Location getLocation() {

        return this.location;
    }

    /**
     * Sets the {@code Location}associated with the {@code Actor}
     *
     * @param newLocation The new {@code Location} object
     */
    public void setLocation(Location newLocation) {
        this.location = new Location(newLocation.getXcord(), newLocation.getYcord());
    }

    /**
     * Returns the status of whether the {@code Actor} is dead
     *
     * @return The current {@code isDead} status of the {@code Actor}
     */
    public boolean getIsDead() {
        return this.isDead;
    }

    /**
     * Sets the status of whether the {@code Actor} is dead to a new status
     *
     * @param newStatus The new status of whether the {@code Actor} is dead
     */
    public void setIsDead(boolean newStatus) {
        this.isDead = newStatus;
    }

    /**
     * Returns the current health of the {@code Actor}
     *
     * @return The current health of the {@code Actor}
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Sets the current health of the {@code Actor} and checks if the {@code Actor} is dead. Updates isDead accordingly.
     *
     * @param newHealth The new health amount of the {@code Actor}
     */
    public void setHealth(int newHealth) {
        if (newHealth >= 0) {
            this.health = newHealth;
        } else {
            System.out.println("Health cannot be negative! Health is unchanged");
        }

        if (this.getHealth() <= 0) {
            this.setIsDead(true);
        } else {
            this.setIsDead(false);
        }
    }

    /**
     * Applies a single point of damage to the {@code Actor}'s health. Updates isDead accordingly.
     */
    public void takeSinglePointOfDamage() {
        this.health = this.health - 1;

        if (this.getHealth() <= 0) {
            this.setIsDead(true);
        } else {
            this.setIsDead(false);
        }
    }

    //main tests the class methods
/*    public static void main(String[] args)
    {
        Location l1 = new Location(987,654);

        actor a1 = new actor();
        actor a2 = new actor(l1);
        actor a3 = new actor(l1);

        l1.setXcord(111);
        l1.setYcord(222);

        System.out.println("Should be 111: " + l1.getXcord());
        System.out.println("Should be 222: " + l1.getYcord());

        System.out.println("Should be 987: " + a2.getLocation().getXcord());
        System.out.println("Should be 654: " + a2.getLocation().getYcord());

        System.out.println("Should be 0: " + a1.getLocation().getXcord());
        System.out.println("Should be 0: " + a1.getLocation().getYcord());

        a1.setHealth(5);
        a2.setHealth(0);
        a3.setHealth(-1);

        System.out.println("Should be 5: " + a1.getHealth());
        System.out.println("Should be 0: " + a2.getHealth());
        System.out.println("Should be 1: " + a3.getHealth());

        System.out.println("Should be false: " + a1.getIsDead());
        System.out.println("Should be true: " + a2.getIsDead());
        System.out.println("Should be false: " + a3.getIsDead());

        a3.takeSinglePointOfDamage();

        System.out.println("Should be true: " + a3.getIsDead());

        a1.takeSinglePointOfDamage();
        a1.takeSinglePointOfDamage();
        a1.takeSinglePointOfDamage();
        a1.takeSinglePointOfDamage();

        System.out.println("Should be false: " + a1.getIsDead());

        a1.takeSinglePointOfDamage();

        System.out.println("Should be true: " + a1.getIsDead());

        a1.setHealth(2);

        System.out.println("Should be false: " + a1.getIsDead());

        a1.setIsDead(true);

        System.out.println("Should be true: " + a1.getIsDead());
    }
*/
}


