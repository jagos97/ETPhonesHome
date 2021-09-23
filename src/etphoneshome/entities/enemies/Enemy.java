package etphoneshome.entities.enemies;

import etphoneshome.entities.actor.Actor;
import etphoneshome.objects.Location;
import etphoneshome.objects.Platform;

/**
 * This is used as the parent class for the enemies. It is derived from the {@code Actor} class.
 */

public abstract class Enemy extends Actor {

    private Platform standingOnPlatform;

    /**
     * Default constructor
     */
    public Enemy() {

    }

    /**
     * Constructor to set the {@code Enemy}'s {@code Location}
     *
     * @param location The {@code Enemy}'s {@code Location}
     */
    public Enemy(Location location) {
        super(location);
    }

    public void setStandingOnPlatform(Platform platform) {
        this.standingOnPlatform = platform;
    }

    public Platform getStandingOnPlatform() {
        return standingOnPlatform;
    }

    //main tests the class methods
/*    public static void main(String[] args)
    {
        Location l1 = new Location(987,654);

        Enemy a1 = new Enemy();
        Enemy a2 = new Enemy(l1);
        Enemy a3 = new Enemy(l1);

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
