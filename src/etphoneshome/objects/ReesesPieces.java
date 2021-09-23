package etphoneshome.objects;

/**
 * This class is an extension of the Collectible class. It handles the ReesesPieces which are needed for extra points.
 * With the constructor you can sets its location and using the parent class it can also set the hitbox
 */
public class ReesesPieces extends Collectible {

    /**
     * empty constructor
     */
    public ReesesPieces() {

    }

    /**
     * sets the starting location and using the parent class sets the hitbox
     *
     * @param location starting location of the {@code ReesesPieces}
     */
    public ReesesPieces(Location location) {
        super(location);

    }
    
    /**
     * String representation of the {@code ReesesPieces} used for testing
     * @return String to represent {@code ReesesPieces}
     */
    public String toString() {
    	return super.toString() + " Reeses Pieces";
    }

    public static void main(String[] args) {
        Location testloc = new Location(100, 100);
        ReesesPieces tester = new ReesesPieces(testloc);
        System.out.println("x cord should be 100 is: " + tester.getLocation().getXcord());
        System.out.println("y cord should be 100 is: " + tester.getLocation().getYcord());
        tester.setLocation(new Location(tester.getLocation().getXcord() + 20, tester.getLocation().getXcord() + 20));
        System.out.println("x cord should be 120 is: " + tester.getLocation().getXcord());
        System.out.println("y cord should be 120 is: " + tester.getLocation().getYcord());


        Location testloc2 = new Location(200, 200);
        ReesesPieces tester2 = new ReesesPieces(testloc2);
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
