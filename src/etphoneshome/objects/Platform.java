package etphoneshome.objects;

/**
 * This class represents a platform that an {@code Actor} can stand on. Using the getLength method returns the
 * length of the platform.
 */
public class Platform extends Obstacle {

    /**
     * Number of individual bricks in the {@code Platform} (defaults to 1)
     */
    private int length = 1;

    /**
     * Constructor to set the length and {@code Location} of the {@code Platform}
     *
     * @param platformLocation The starting {@code Location} of the {@code Platform}
     * @param length           The length of the {@code Platform}
     */
    public Platform(Location platformLocation, int length) {
        super(platformLocation);
        this.length = length;
    }

    /**
     * Returns the length of the {@code Platform}
     *
     * @return The length of the {@code Platform}
     */
    public int getLength() {
        return this.length;
    }

    //main method for testing
/*    public static void main(String[] args)
    {
        Platform p1 = new Platform(new Location(100,100), 4);
        Platform p2 = new Platform(new Location(200,200),1);
        Platform p3 = new Platform(new Location(200,200),0);
        Platform p4 = new Platform(new Location(200,200),-1);
        Platform p5 = new Platform(new Location(200,200),2);
        Platform p6 = new Platform(new Location(333,444),10);

        System.out.println("Should be 4: " + p1.getLength());
        System.out.println("Should be 30: " + p1.getHEIGHT());
        System.out.println("Should be 60: " + p1.getWIDTH());

        System.out.println("Should be 100: " + p1.getBricks().get(0).getLocation().getXcord());
        System.out.println("Should be 100: " + p1.getBricks().get(0).getLocation().getYcord());

        System.out.println("Should be 160: " + p1.getBricks().get(1).getLocation().getXcord());
        System.out.println("Should be 100: " + p1.getBricks().get(1).getLocation().getYcord());

        System.out.println("Should be 220: " + p1.getBricks().get(2).getLocation().getXcord());
        System.out.println("Should be 100: " + p1.getBricks().get(2).getLocation().getYcord());

        System.out.println("Should be 280: " + p1.getBricks().get(3).getLocation().getXcord());
        System.out.println("Should be 100: " + p1.getBricks().get(3).getLocation().getYcord());

        System.out.println("Should be 4: " + p1.getLength());
        System.out.println("Should be 1: " + p2.getLength());
        System.out.println("Should be 2: " + p5.getLength());
        System.out.println("Should be 10: " + p6.getLength());
        System.out.println("Should be 1: " + p3.getLength());
        System.out.println("Should be 1: " + p4.getLength());

        System.out.println("Should be 333: " + p6.getBricks().get(0).getLocation().getXcord());
        System.out.println("Should be 444: " + p6.getBricks().get(0).getLocation().getYcord());
        System.out.println("Should be 393: " + p6.getBricks().get(1).getLocation().getXcord());
        System.out.println("Should be 444: " + p6.getBricks().get(1).getLocation().getYcord());
        System.out.println("Should be 453: " + p6.getBricks().get(2).getLocation().getXcord());
        System.out.println("Should be 444: " + p6.getBricks().get(2).getLocation().getYcord());
        System.out.println("Should be 513: " + p6.getBricks().get(3).getLocation().getXcord());
        System.out.println("Should be 444: " + p6.getBricks().get(3).getLocation().getYcord());
        System.out.println("Should be 573: " + p6.getBricks().get(4).getLocation().getXcord());
        System.out.println("Should be 444: " + p6.getBricks().get(4).getLocation().getYcord());
        System.out.println("Should be 633: " + p6.getBricks().get(5).getLocation().getXcord());
        System.out.println("Should be 444: " + p6.getBricks().get(5).getLocation().getYcord());
        System.out.println("Should be 693: " + p6.getBricks().get(6).getLocation().getXcord());
        System.out.println("Should be 444: " + p6.getBricks().get(6).getLocation().getYcord());
        System.out.println("Should be 753: " + p6.getBricks().get(7).getLocation().getXcord());
        System.out.println("Should be 444: " + p6.getBricks().get(7).getLocation().getYcord());
        System.out.println("Should be 813: " + p6.getBricks().get(8).getLocation().getXcord());
        System.out.println("Should be 444: " + p6.getBricks().get(8).getLocation().getYcord());
        System.out.println("Should be 873: " + p6.getBricks().get(9).getLocation().getXcord());
        System.out.println("Should be 444: " + p6.getBricks().get(9).getLocation().getYcord());

    }
*/
}
