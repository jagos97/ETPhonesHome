package etphoneshome.objects;

/**
 * This class is the parent class of ReesesPieces and PhonePieces. This class will be able to get and set the Location
 * of the Collectible.
 */
public abstract class Collectible {

    /**
     * Location associated with {@code Collectible}
     */
    private Location location;


    /**
     * Hitbox associated with the {@code Collectible}
     */
    private Hitbox hitbox;

    /**
     * empty constructor
     */
    public Collectible() {

    }

    /**
     * Constructor that sets the location of the {@code Collectible}
     *
     * @param location location assoicated with {@code Collectible}
     */
    public Collectible(Location location) {
        setLocation(location);
        if (this.getHitbox() != null) {
            this.hitbox = new Hitbox(location, 1, 1);
        }
    }

    /**
     * Set Location of the Collectible
     *
     * @param location new location of the {@code COollectible}
     */
    public void setLocation(Location location) {
        this.location = location;
        this.hitbox = new Hitbox(location, 1, 1);
    }

    /**
     * returns a copy of the location of the {@code Collectible}
     *
     * @return copy of the Location
     */
    public Location getLocation() {
        return new Location(this.location.getXcord(), this.location.getYcord());
    }

    /**
     * returns a copy of the hitbox of the {@code Collectible}
     *
     * @return a copy of the hitbox associated with {@code Collectible}
     */
    public Hitbox getHitbox() {
        return new Hitbox(this.location, 1, 1);
    }
    
    /**
     * String representation of the {@code ReesesPieces} used for testing
     * @return String to represent Collectible
     */
    public String toString() {
    	String stringRep = ("Location: " + this.getLocation().getXcord() + ", " + this.getLocation().getYcord());
    	return stringRep;
    }

    public static void main(String[] args) {
        Location testloc = new Location(100, 100);
        Collectible tester = new ReesesPieces(testloc);
        System.out.println("x cord should be 100 is: " + tester.getLocation().getXcord());
        System.out.println("y cord should be 100 is: " + tester.getLocation().getYcord());
        tester.setLocation(new Location(tester.getLocation().getXcord() + 20, tester.getLocation().getXcord() + 20));
        System.out.println("x cord should be 120 is: " + tester.getLocation().getXcord());
        System.out.println("y cord should be 120 is: " + tester.getLocation().getYcord());


        Location testloc2 = new Location(200, 200);
        Collectible tester2 = new ReesesPieces(testloc2);
        System.out.println("x cord should be 200 is: " + tester2.getLocation().getXcord());
        System.out.println("y cord should be 200 is: " + tester2.getLocation().getYcord());

        boolean a;
        a = tester.getHitbox().areColliding(tester2.getHitbox());
        System.out.println("should be false: " + a);
        tester2.setLocation(new Location(100, 120));
        System.out.println("xcord should be 100 is: " + tester2.getLocation().getXcord());
        a = tester.getHitbox().areColliding(tester2.getHitbox());
        System.out.println("should be true: " + a);

        System.exit(0);


    }

}

