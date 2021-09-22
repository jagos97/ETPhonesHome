package etphoneshome.objects;

/**
 * Class used to represent a generic obstacle with a location and a hitbox. Using the get location method
 * returns the location object of the {@code Obstacle}. Using the set location method sets the location
 * object of the {@code Obstacle} to the provided new location. Using the get hitbox method returns the
 * hitbox object of the {@code Obstacle}. Using the set hitbox method lets you set the hitbox object of the
 * {@code Obstacle} by either copying a provided hitbox object, or by providing a height and width.
 * {@code Obstacle} is primarily used for the {@code Platform} object.
 */
public abstract class Obstacle {
    /**
     * Location object of the {@code Obstacle} set to a default location
     */
    private Location location = new Location(0, 0);

    /**
     * Hitbox object of the {@code Obstacle} set to the default location with a default height and
     * width for a {@code Platform} object
     */
    private Hitbox hitbox = new Hitbox(location, 30, 60);

    /**
     * Empty Default constructor
     */
    public Obstacle() {

    }

    /**
     * Constructor to set the location and hitbox to the provided location and hitbox objects
     *
     * @param newLocation The new location for the {@code Obstacle}
     * @param newHitbox   The new hitbox for the {@code Obstacle}
     */
    public Obstacle(Location newLocation, Hitbox newHitbox) {
        this.setLocation(newLocation);
        this.setHitbox(newHitbox);
    }

    /**
     * Constructor to copy (by value) the location and hitbox from another {@code Obstacle}
     *
     * @param obstacleToCopy The {@code Obstacle} you wish to copy the location and hitbox from
     */
    public Obstacle(Obstacle obstacleToCopy) {
        this(obstacleToCopy.getLocation(), obstacleToCopy.getHitbox());
    }

    /**
     * Returns the location object of the {@code Obstacle}
     *
     * @return The location object of the {@code Obstacle}
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Sets the location of the {@code Obstacle} to a new location object
     *
     * @param newLocation The new location object for the {@code Obstacle}
     */
    public void setLocation(Location newLocation) {
        this.location.setXcord(newLocation.getXcord());
        this.location.setYcord(newLocation.getYcord());
    }

    /**
     * Returns the hitbox object of the {@code Obstacle}
     *
     * @return The hitbox object of the {@code Obstacle}
     */
    public Hitbox getHitbox() {
        return this.hitbox;
    }

    /**
     * Sets the hitbox of the {@code Obstacle} to the current location of the {@code Obstacle}, but
     * with the specified height and width
     *
     * @param height The new height of the hitbox
     * @param width  The new width of the hitbox
     */
    public void setHitbox(int height, int width) {
        this.hitbox = new Hitbox(this.location, height, width);
    }

    /**
     * Sets the hitbox of the {@code Obstacle} by copying (by value) the location, height and width
     * from the provided hitbox
     *
     * @param hitboxToCopy The hitbox you wish to copy the values from
     */
    public void setHitbox(Hitbox hitboxToCopy) {
        Location copy = new Location(hitboxToCopy.getTopLeftCorner().getXcord(), hitboxToCopy.getTopLeftCorner().getYcord());
        this.hitbox = new Hitbox(copy, hitboxToCopy.getHeight(), hitboxToCopy.getWidth());
    }

}