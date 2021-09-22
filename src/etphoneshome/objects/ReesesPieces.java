package etphoneshome.objects;

/**
 * this class is an extension of Collectible. This class makes a Reeses piece that will add score if collected
 * it has an image with height and width set to 40. with getters and setters for the image
 * using getters and setters you get and set the width,height, image, location and hitbox
 */

import etphoneshome.graphics.SpriteURL;
import javafx.scene.image.Image;

import java.util.Random;

public class ReesesPieces extends Collectible {

	/**
	 * Dimensions of the {@code ReesesPieces}
	 */
    private final static int WIDTH = 40;
    private final static int HEIGHT = 40;

    /**
     * random number generator to pick sprite
     */
    private final Random rand = new Random();

    /**
     * path of the image
     */
    private String ImagePath;


    /**
     * Empty Constructor that sets the sprite associated with {@code ReesesPieces}
     */
    public ReesesPieces() {
        super.setImages(SpriteURL.RP_BROWN.getPath(), SpriteURL.RP_ORANGE.getPath(), SpriteURL.RP_YELLOW.getPath());
        setImage();
    }

    /**
     * Places a single reesesPieces at location with a given sprite
     *
     * @param location associated with {@code ReesesPieces}
     */
    public ReesesPieces(Location location) {
        super(location);
        super.setImages(SpriteURL.RP_BROWN.getPath(), SpriteURL.RP_ORANGE.getPath(), SpriteURL.RP_YELLOW.getPath());
        setImage();
    }


    /**
     * sets the image of {@code ReesesPieces}
     */
    private void setImage() {
        int index = rand.nextInt(3);
        this.ImagePath = super.getSprites()[index];
        super.setTheImage(ImagePath);

    }

    /**
     * gets the image of the sprite
     *
     * @return image of sprite
     */
    public Image getTheImage() {
        Image copy = new Image(ImagePath);
        return copy;
    }


    public static void main(String[] args) {

        Location testloc = new Location(100, 300);
        ReesesPieces tester = new ReesesPieces(testloc);
        System.out.println("x cord should be 100 is: " + tester.getLocation().getXcord());
        System.out.println("ycord should be 300 is: " + tester.getLocation().getYcord());

        Location testloc2 = new Location(70, 270);
        ReesesPieces tester2 = new ReesesPieces(testloc2);
        System.out.println("x cord should be 70 is: " + tester2.getLocation().getXcord());
        System.out.println("ycord should be 270 is: " + tester2.getLocation().getYcord());

        boolean a;
        a = tester.getHitbox().areColliding(tester2.getHitbox());
        System.out.println("should be true: " + a);
        tester2.setLocation(new Location(1000, tester2.getLocation().getYcord()));
        System.out.println("xcord should be 1000 is: " + tester2.getLocation().getXcord());
        a = tester.getHitbox().areColliding(tester2.getHitbox());
        System.out.println("should be false: " + a);


        System.exit(1);
    }

	/**
	 * @return the width
	 */
	public static int getWidth() {
		return WIDTH;
	}

	/**
	 * @return the height
	 */
	public static int getHeight() {
		return HEIGHT;
	}

}
