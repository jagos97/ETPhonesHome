package etphoneshome.objects;

/**
 * This class is used to control the horizontal and vertical velocity for any {@code Actor}.
 */
public class Velocity {
    /**
     * The amount of horizontal velocity. A negative or positive will indicate direction
     */
    private double horizontalVelocity = 0;  //Velocity is a vector so negative velocity is possible

    /**
     * The amount of vertical velocity. A negative or positive will indicate direction
     */
    private double verticalVelocity = 0;

    /**
     * Simple default constructor which will use the default horizontal/vertical velocity values of 0
     */
    public Velocity()   //default constructor
    {

    }

    /**
     * Constructor to immediately set both vertical and horizontal velocity
     *
     * @param horizontalVelocity The amount of horizontal velocity
     * @param verticalVelocity   The amount of vertical velocity
     */
    public Velocity(double horizontalVelocity, double verticalVelocity) {
        this.horizontalVelocity = horizontalVelocity;
        this.verticalVelocity = verticalVelocity;
    }

    /**
     * Returns the amount of horizontal velocity
     *
     * @return the amount of horizontal velocity
     */
    public double getHorizontalVelocity() {
        return this.horizontalVelocity;
    }

    /**
     * Returns the amount of vertical velocity
     *
     * @return the amount of vertical velocity
     */
    public double getVerticalVelocity() {
        return this.verticalVelocity;
    }

    /**
     * Sets the amount of horizontal velocity
     *
     * @param amount the amount of horizontal velocity
     */
    public void setHorizontalVelocity(double amount) {
        this.horizontalVelocity = amount;
    }

    /**
     * Sets the amount of vertical velocity
     *
     * @param amount the amount of vertical velocity
     */
    public void setVerticalVelocity(double amount) {
        this.verticalVelocity = amount;
    }

    /**
     * Changes the horizontal velocity by amount and the sign of amount
     *
     * @param amount The amount the horizontal velocity changes and in which direction
     */
    public void changeHorizontalVelocity(double amount) {
        this.horizontalVelocity += amount;
    }

    /**
     * Changes the vertical velocity by amount and the sign of amount
     *
     * @param amount The amount the vertical velocity changes and in which direction
     */
    public void changeVerticalVelocity(double amount) {
        this.verticalVelocity += amount;
    }


    //main method used for testing
    public static void main(String[] args) {
        Velocity v1 = new Velocity();
        Velocity v2 = new Velocity(10, -1);

        System.out.println("Should be 0: " + v1.getHorizontalVelocity());
        System.out.println("Should be 0: " + v1.getVerticalVelocity());

        System.out.println("Should be 10: " + v2.getHorizontalVelocity());
        System.out.println("Should be -1: " + v2.getVerticalVelocity());

        v1.setHorizontalVelocity(100);
        v1.setVerticalVelocity(15);

        System.out.println("Should be 100: " + v1.getHorizontalVelocity());
        System.out.println("Should be 15: " + v1.getVerticalVelocity());

        v1.changeHorizontalVelocity(20);
        v1.changeVerticalVelocity(-16);

        System.out.println("Should be 120: " + v1.getHorizontalVelocity());
        System.out.println("Should be -0: " + v1.getVerticalVelocity());
    }
}
