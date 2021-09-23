package etphoneshome.entities.enemies;

import etphoneshome.objects.Location;

/**
 * This class is used for the Police type enemy. It is a derived class from the {@code Enemy} class
 */
public class Police extends Enemy {
    /**
     * Default constructor
     */
    public Police() {

    }

    public Police(Police police) {
        this(police.getLocation());
    }

    /**
     * Constructor to set the {@code Location} of the {@code Police}
     *
     * @param location The {@code Location} of the {@code Police}
     */
    public Police(Location location) {
        super(location);
    }
}
