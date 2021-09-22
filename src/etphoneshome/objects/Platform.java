package etphoneshome.objects;

/**
 * This class represents an obstacle that the player may jump up to and walk on. Using the get length
 * method returns the length (of standard "platform brick" units where the height is 30 pixels and
 * width is 60 pixels) of the {@code Platform}. Using the set length method sets the {@code Platform}
 * length to the specified value. The length of a {@code Platform} must be a minimum of 1. Using the
 * getRealLength method returns the length of the {@code Platform} in pixels (not platform brick units).
 */
public class Platform extends Obstacle {
    /**
     * The length (in units where height = 30 pixels and width = 60 pixels) of the {@code Platform}
     */
    private int length = 1;

    /**
     * Default constructor which calls the default constructor of {@code Obstacle}
     */
    public Platform() {
        super();
    }

    /**
     * Constructor to set the location (and hitbox automatically) and length of the {@code Platform}
     *
     * @param location  The location of the {@code Platform}
     * @param newLength The length of the {@code Platform}
     */
    public Platform(Location location, int newLength) {
        super(location, new Hitbox(new Location(location.getXcord(), location.getYcord()), 30, 60 * newLength));

        if (newLength >= 1) {
            this.length = newLength;
        } else {
            System.out.println("Platform length must be at least 1! Platform length is set to 1");
            this.length = 1;
            this.setHitbox(new Hitbox(new Location(location.getXcord(), location.getYcord()), 30, 60 * this.length));
        }
    }

    /**
     * Constructor which copies all values of a given {@code Platform} and creates a new {@code Platform}
     * from those values
     *
     * @param platformToCopy The {@code Platform} you wish to copy values from
     */
    public Platform(Platform platformToCopy) {
        this(platformToCopy.getLocation(), platformToCopy.getLength());
    }

    /**
     * Returns the length (in units where height = 30 pixels and width = 60 pixels) of the {@code Platform}
     *
     * @return The length (in units where height = 30 pixels and width = 60 pixels) of the {@code Platform}
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Returns the real length of the platform
     * @return length * 60
     */
    public int getRealLength() {
        return this.length * 60;
    }


    //MAIN METHOD USED TO TEST BOTH PLATFORM AND OBSTACLE
    public static void main(String[] args) {
        Location l1 = new Location(999, 888);
        Location l2 = new Location(111, 222);

        System.out.println("TESTING PLATFORM AND OBSTACLE CONSTRUCTORS....\n");
        Platform p1 = new Platform();   //location at 0,0 and hitbox at 0,0 with height = 30 and width = 60 and length = 1
        Platform p2 = new Platform(l1, 5);
        Platform p3 = new Platform(l2, 3);
        Platform p4 = new Platform(p3);
        Platform p5 = new Platform(l1, 0);
        Platform p6 = new Platform(l1, -1);
        System.out.println("\nPLATFORM AND OBSTACLE CONSTRUCTOR TESTING COMPLETE!\n");

        System.out.println("TESTING RESULTS OF PLATFORM AND OBSTACLE CONSTRUCTORS....\n");

        //p1
        System.out.println("Should be 0: " + p1.getLocation().getXcord());
        System.out.println("Should be 0: " + p1.getLocation().getYcord());
        System.out.println("Should be 0: " + p1.getHitbox().getTopLeftCorner().getXcord());
        System.out.println("Should be 0: " + p1.getHitbox().getTopLeftCorner().getYcord());
        System.out.println("Should be 30: " + p1.getHitbox().getHeight());
        System.out.println("Should be 60: " + p1.getHitbox().getWidth());
        System.out.println("Should be 1: " + p1.getLength());
        System.out.println("\n");

        //p2
        System.out.println("Should be 999: " + p2.getLocation().getXcord());
        System.out.println("Should be 888: " + p2.getLocation().getYcord());
        System.out.println("Should be 999: " + p2.getHitbox().getTopLeftCorner().getXcord());
        System.out.println("Should be 888: " + p2.getHitbox().getTopLeftCorner().getYcord());
        System.out.println("Should be 30: " + p2.getHitbox().getHeight());
        System.out.println("Should be 300: " + p2.getHitbox().getWidth());
        System.out.println("Should be 5: " + p2.getLength());
        System.out.println("\n");

        //p3
        System.out.println("Should be 111: " + p3.getLocation().getXcord());
        System.out.println("Should be 222: " + p3.getLocation().getYcord());
        System.out.println("Should be 111: " + p3.getHitbox().getTopLeftCorner().getXcord());
        System.out.println("Should be 222: " + p3.getHitbox().getTopLeftCorner().getYcord());
        System.out.println("Should be 30: " + p3.getHitbox().getHeight());
        System.out.println("Should be 180: " + p3.getHitbox().getWidth());
        System.out.println("Should be 3: " + p3.getLength());
        System.out.println("\n");

        //p4
        System.out.println("Should be 111: " + p4.getLocation().getXcord());
        System.out.println("Should be 222: " + p4.getLocation().getYcord());
        System.out.println("Should be 111: " + p4.getHitbox().getTopLeftCorner().getXcord());
        System.out.println("Should be 222: " + p4.getHitbox().getTopLeftCorner().getYcord());
        System.out.println("Should be 30: " + p4.getHitbox().getHeight());
        System.out.println("Should be 180: " + p4.getHitbox().getWidth());
        System.out.println("Should be 3: " + p4.getLength());
        System.out.println("\n");

        //p5
        System.out.println("Should be 999: " + p5.getLocation().getXcord());
        System.out.println("Should be 888: " + p5.getLocation().getYcord());
        System.out.println("Should be 999: " + p5.getHitbox().getTopLeftCorner().getXcord());
        System.out.println("Should be 888: " + p5.getHitbox().getTopLeftCorner().getYcord());
        System.out.println("Should be 30: " + p5.getHitbox().getHeight());
        System.out.println("Should be 60: " + p5.getHitbox().getWidth());
        System.out.println("Should be 1: " + p5.getLength());
        System.out.println("\n");

        //p6
        System.out.println("Should be 999: " + p6.getLocation().getXcord());
        System.out.println("Should be 888: " + p6.getLocation().getYcord());
        System.out.println("Should be 999: " + p6.getHitbox().getTopLeftCorner().getXcord());
        System.out.println("Should be 888: " + p6.getHitbox().getTopLeftCorner().getYcord());
        System.out.println("Should be 30: " + p6.getHitbox().getHeight());
        System.out.println("Should be 60: " + p6.getHitbox().getWidth());
        System.out.println("Should be 1: " + p6.getLength());
        System.out.println("\n");


    }

}
