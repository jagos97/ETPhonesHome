package etphoneshome.objects;

import javafx.scene.image.Image;

/**
 * this class is for the phone piece collectible needed to win
 * using getters and setters you get and set the width,height, image, location, hitbox and the type of phone piece
 */
public class PhonePiece extends Collectible {
    
    /**
     * type of the phone piece
     */
    private PhonePieceType phonePieceType;

    /**
     * Empty constructor that will set the sprite to the phone Antenna
     */
    public PhonePiece() {
        this.phonePieceType = PhonePieceType.ANTENNA;
    }

    /**
     * Constructor that sets up the image, location and hitbox associated with {@code PhonePiece}
     *
     * @param location       location of the phone piece
     * @param phonePieceType the type of phone piece to create
     */
    public PhonePiece(Location location, PhonePieceType phonePieceType) {
        super(location);
        this.phonePieceType = phonePieceType;
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
