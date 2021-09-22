package etphoneshome.objects;

import etphoneshome.graphics.SpriteURL;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;

/**
 * This class is for the attack of the scientists in the game.
 * using getters and setters you get and set the width,height, image, location, hitbox and velocity
 */
public class Flask {
	
	private JFXPanel jfxPanel = new JFXPanel(); //this is needed for the class to run since there is an image attached

	/**
	 * Dimensions of all flasks
	 */
    private static final int WIDTH = 30;
    private static final int HEIGHT = 40;
    
    /**
     * velocity of the {@code Flask}
     */
    private Velocity velocity;

    /**
     * Image associated with the {@code Flask}
     */
    private Image image = new Image(SpriteURL.FLASK.getPath());

    /**
     * location and Hitbox of the {@code Flask}
     */
    private Location location;
    private Hitbox hitbox;

    /**
     * constructor that sets up its inital location and velocity
     * @param location initial location of the {@code Flask}
     * @param velocity initial velocity of the {@code Flask}
     */
    public Flask(Location location, Velocity velocity) {
        this.location = location;
        this.hitbox = new Hitbox(location,HEIGHT, WIDTH);
        this.velocity = velocity;
    }

    /**
     * returns copy of the {@code Flask} location
     * @return copy of the location
     */
    public Location getLocation() {
        return new Location(this.location);
    }

    /**
     * sets the location of the {@code Flask}
     * @param location new Location of the {@code Flask}
     */
    public void setLocation(Location location) {
        this.location = location;
        hitbox = new Hitbox(this.location,HEIGHT, WIDTH );
    }

    /**
     * returns copy of the hitbox
     * @return copy of the hitbox
     */
    public Hitbox getHitbox() {
        return new Hitbox(this.hitbox);
    }

    /**
     * gets the Image of the {@code Flask}
     * @return image
     */
    public Image getSprite() {
        return this.image;
    }

    /**
     * returns the static width of the {@code Flask}
     * @return width of {@code Flask}
     */
    public int getWidth() {
        return WIDTH;
    }

    /**
     * returns static height of {@code Flask}
     * @return height of the {@code Flask}
     */
    public int getHeight() {
        return HEIGHT;
    }

    /**
     * returns velocity of the {@code Flask}
     * @return velocity of {@code Flask}
     */
    public Velocity getVelocity() {
        return this.velocity;
    }


    public static void main(String[] args) {
        Location testloc = new Location(100, 300);
        Velocity velo = new Velocity(20 ,-3);
        Flask tester = new Flask(testloc, velo);
        System.out.println("x cord should be 100 is: " + tester.getLocation().getXcord());
        System.out.println("ycord should be 300 is: " + tester.getLocation().getYcord());
        System.out.println(" x velocity should be 20 is : " + tester.getVelocity().getHorizontalVelocity());
        System.out.println(" y velocity should be -3 is : " + tester.getVelocity().getVerticalVelocity());

        Location testloc2 = new Location(70, 270);
        Velocity velo2 = new Velocity(10, 5);
        Flask tester2 = new Flask(testloc2,velo2);
        System.out.println("x cord should be 70 is: " + tester2.getLocation().getXcord());
        System.out.println("ycord should be 270 is: " + tester2.getLocation().getYcord());
        System.out.println(" x velocity should be 10 is : " + tester2.getVelocity().getHorizontalVelocity());
        System.out.println(" y velocity should be 5 is : " + tester2.getVelocity().getVerticalVelocity());
        
        boolean a;
        a = tester.getHitbox().areColliding(tester2.getHitbox());
        System.out.println("should be true: " + a);
        tester2.setLocation(new Location(1000, tester2.getLocation().getYcord()));
        System.out.println("xcord should be 1000 is: " + tester2.getLocation().getXcord());
        a = tester.getHitbox().areColliding(tester2.getHitbox());
        System.out.println("should be false: " + a);


        System.exit(1);

    }

}
