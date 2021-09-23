package etphoneshome.entities.enemies;

import etphoneshome.objects.Location;

/**
 * This class is used for the Scientist type enemy. It is a derived class from the {@code Enemy} class
 */
public class Scientist extends Enemy {
    /**
     * Default constructor
     */
    public Scientist() {

    }

    public Scientist(Scientist scientist) {
        this(scientist.getLocation());
    }

    /**
     * Constructor to set the {@code Location} of the {@code Scientist}
     *
     * @param location The {@code Location} of the {@code Scientist}
     */
    public Scientist(Location location) {
        super(location);
    }
}
