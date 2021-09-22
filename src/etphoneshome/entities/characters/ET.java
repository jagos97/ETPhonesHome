package etphoneshome.entities.characters;

import etphoneshome.graphics.SpriteURL;
import etphoneshome.objects.Location;

/**
 * This class is used as the player's class. It is a derived class from the {@code Character} class.
 */
public class ET extends Character {

    /**
     * default constructor that sets the left and right sprites of {@code ET}
     */
    public ET() {
        this.setRightEntitySprite(SpriteURL.ET_RIGHT.getPath());
        this.setLeftEntitySprite(SpriteURL.ET_LEFT.getPath());
    }

    /**
     * Constructor that sets initial Location and sprites of {@code ET}
     *
     * @param location starting location of {@code Character}
     */
    public ET(Location location) {
        super(location);
        this.setRightEntitySprite(SpriteURL.ET_RIGHT.getPath());
        this.setLeftEntitySprite(SpriteURL.ET_LEFT.getPath());
    }

}