package etphoneshome.objects;

/**
 * This class is used to represent the location for the {@code Obstacle}. Using
 * the get location method returns the location object of the {@code Obstacle}
 */
public class Obstacle {
    /**
     * The {@code Location} of the {@code Obstacle}
     */
    private Location location;

    /**
     * Default constructor (needed for platform class to work)
     */
    public Obstacle() {

    }

    /**
     * Constructor to set the {@code Location} object of the {@code Obstacle}
     *
     * @param location origin location of obstacle
     */
    public Obstacle(Location location) {
        this.location = new Location(location.getXcord(), location.getYcord());
    }

    /**
     * Returns the {@code Location} of the {@code Obstacle}
     *
     * @return The {@code Location} of the {@code Obstacle}
     */
    public Location getLocation() {
        return this.location;
    }

    /*
    public static void main(String[] args)
    {
        Location l1 = new Location(100,120);
        Location l2 = new Location(200,203);
        Location encapsTest = new Location(999, 876);

        Obstacle o1 = new Obstacle(l1);
        Obstacle o2 = new Obstacle(l2);
        Obstacle encapsObs = new Obstacle(encapsTest);

        System.out.println("Should be 100: " + o1.getLocation().getXcord());
        System.out.println("Should be 120: " + o1.getLocation().getYcord());

        System.out.println("Should be 200: " + o2.getLocation().getXcord());
        System.out.println("Should be 203: " + o2.getLocation().getYcord());

        System.out.println("Should be 999: " + encapsObs.getLocation().getXcord());
        System.out.println("Should be 876: " + encapsObs.getLocation().getYcord());

        encapsTest.setXcord(444);
        encapsTest.setYcord(747);

        System.out.println("Should be 444: " + encapsTest.getXcord());
        System.out.println("Should be 747: " + encapsTest.getYcord());


        System.out.println("Should be 100: " + o1.getLocation().getXcord());
        System.out.println("Should be 120: " + o1.getLocation().getYcord());

        System.out.println("Should be 200: " + o2.getLocation().getXcord());
        System.out.println("Should be 203: " + o2.getLocation().getYcord());

        System.out.println("Should be 999: " + encapsObs.getLocation().getXcord());
        System.out.println("Should be 876: " + encapsObs.getLocation().getYcord());
    }
    */
}
