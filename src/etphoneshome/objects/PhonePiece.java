package etphoneshome.objects;

import etphoneshome.graphics.SpriteURL;
import javafx.scene.image.Image;

/**
 * this class is for the phone piece collectible needed to win
 * using getters and setters you get and set the width,height, image, location, hitbox and the type of phone piece
 */
public class PhonePiece extends Collectible {

    /**
     * Dimensions of the {@code PhonePiece}
     */
    private final static int WIDTH = 30;
    private final static int HEIGHT = 70;

    /**
     * string of the image path
     */
    private String ImagePath;
    
    /**
     * type of the phone piece
     */
    private PhonePieceType phonePieceType;


    /**
     * Empty constructor that will set the sprite to the phone Antenna
     */
    public PhonePiece() {
        super.setImages(SpriteURL.PHONE_ANTENNA.getPath(), SpriteURL.PHONE_CHASSIS.getPath(), SpriteURL.PHONE_KEYPAD.getPath());
        this.phonePieceType = PhonePieceType.ANTENNA;
        this.setImage(PhonePieceType.ANTENNA);
    }

    /**
     * Constructor that sets up the image, location and hitbox associated with {@code PhonePiece}
     *
     * @param location       location of the phone piece
     * @param phonePieceType the type of phone piece to create
     */
    public PhonePiece(Location location, PhonePieceType phonePieceType) {
        super(location);
        super.setImages(SpriteURL.PHONE_ANTENNA.getPath(), SpriteURL.PHONE_CHASSIS.getPath(), SpriteURL.PHONE_KEYPAD.getPath());
        this.phonePieceType = phonePieceType;
        setImage(phonePieceType);
    }

    /**
     * Sets the image to something else
     *
     * @param index index of the phone piece image
     */
    public void setImage(int index) {
        this.ImagePath = this.getSprites()[index];
        this.setTheImage(this.ImagePath);
    }

    /**
     * Sets the image to something else
     *
     * @param type type of phone piece
     */
    public void setImage(PhonePieceType type) {
        this.setImage(type.getIndex());
    }

    /**
     * Returns current image of the {@code PhonePiece}
     *
     * @return the image of {@code PhonePiece}
     */
    public Image getImage() {
        return new Image(this.ImagePath);
    }

    /**
     * Gets width
     *
     * @return width
     */
    public static int getWidth() {
        return WIDTH;
    }

    /**
     * Gets height
     *
     * @return height
     */
    public static int getHeight() {
        return HEIGHT;
    }

    /**
     * Returns the phonePieceType of this object
     *
     * @return The phonePieceType of this object
     */
    public PhonePieceType getPhonePieceType() {
        return this.phonePieceType;
    }

    public static void main(String[] args) {

        Location testloc = new Location(100, 300);
        PhonePiece tester = new PhonePiece(testloc, PhonePieceType.ANTENNA);
        System.out.println("x cord should be 100 is: " + tester.getLocation().getXcord());
        System.out.println("ycord should be 300 is: " + tester.getLocation().getYcord());

        Location testloc2 = new Location(70, 270);
        PhonePiece tester2 = new PhonePiece(testloc2, PhonePieceType.ANTENNA);
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


}
