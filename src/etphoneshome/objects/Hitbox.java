package etphoneshome.objects;

public class Hitbox {
	/**
	 * This is the location of the top left corner of the sprite
	 */
    private Location topLeftCorner; 
    /**
     * The width and height of the sprite
     */
    private int height, width;
    /**
     * Creates of copy of the parameter hitbox
     * @param hitbox Hitbox to copy from
     */
    public Hitbox(Hitbox hitbox) {
        this.topLeftCorner = new Location(hitbox.getTopLeftCorner());
        this.height = hitbox.getHeight();
        this.width = hitbox.getWidth();
    }
    /**
     * The constructor initializes the instance variables of the class 
     * @param m The location of the top left corner of the hitbox
     * @param h The height of the sprite
     * @param w The width of the sprite
     */
    public Hitbox(Location m, int h, int w) {
        topLeftCorner = m;
        height = Math.abs(h);
        width = Math.abs(w);
    }
    /**
     * @return The width
     */
    public int getWidth() {
        return width;
    }
    /**
     * @return The height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns top left corner location of hitbox
     * @return Top left corner location of hitbox
     */
    public Location getTopLeftCorner() {
        return new Location(this.topLeftCorner);
    }

    /**
     * sets the location of {@code Hitbox}
     * @param newLocation top leftHandCorner of Hitbox
     */
    public void setLocation(Location newLocation) {
        this.topLeftCorner = newLocation;
    }

    /**
     * Determines if two hitboxes are touching each other
     * @param otherHitbox 2nd hitbox
     * @return True or false statement whether the two hitboxes are making contact
     */
    public boolean areColliding(Hitbox otherHitbox) {

    	//TODO Optimize this method by checking x cords first, and
    	//ending the method if their x cords are too far apart
        int leftSideX = topLeftCorner.getXcord();
        int otherRightSideX = otherHitbox.topLeftCorner.getXcord() + otherHitbox.getWidth();
        if(leftSideX > otherRightSideX) {
        	return false;
        	}
        
        int rightSideX = topLeftCorner.getXcord() + width;
        int otherLeftSideX = otherHitbox.topLeftCorner.getXcord();
        if (rightSideX < otherLeftSideX) {
            return false;
        }
        int topSideY = topLeftCorner.getYcord();
        int otherBottomSideY = otherHitbox.topLeftCorner.getYcord() + otherHitbox.getHeight();
        if (topSideY > otherBottomSideY) {
            return false;
        }
        
        int bottomSideY = topLeftCorner.getYcord() + height;
        int otherTopSideY = otherHitbox.topLeftCorner.getYcord();
        if (bottomSideY < otherTopSideY) {
            return false;
        } 
        

        return true; // boxes overlap

    }
    /**
     * Determines if the current sprite is below the other sprite
     * @param otherHitbox 2nd hitbox
     * @return False if current sprite is below other sprite
     */
    public boolean belowOtherHitbox(Hitbox otherHitbox) {
        int topSideY = topLeftCorner.getYcord();
        int otherBottomSideY = otherHitbox.topLeftCorner.getYcord() + otherHitbox.getHeight();
        return topSideY > otherBottomSideY;
    }
    /**
     * Determines if the current sprite is above the other sprite
     * @param otherHitbox 2nd hitbox
     * @return False if current sprite is above other sprite
     */
    public boolean aboveOtherHitbox(Hitbox otherHitbox) {
        int bottomSideY = topLeftCorner.getYcord() + height;
        int otherTopSideY = otherHitbox.topLeftCorner.getYcord();
        return bottomSideY < otherTopSideY;
    }
    /**
     * Determines if the current sprite is on the left of the other sprite
     * @param otherHitbox 2nd hitbox
     * @return True if current sprite is to the left of the other sprite
     */

    public boolean toTheLeftOfOtherHitbox(Hitbox otherHitbox) {
        int rightSideX = topLeftCorner.getXcord() + width;
        int otherLeftSideX = otherHitbox.topLeftCorner.getXcord();
        return rightSideX < otherLeftSideX;
    }
    /**
     * Determines if the current sprite is the right of the other sprite
     * @param otherHitbox 2nd hitbox
     * @return True if current sprite is to the right 
     */
    public boolean toTheRightOfOtherHitbox(Hitbox otherHitbox) {
        int leftSideX = topLeftCorner.getXcord();
        int otherRightSideX = otherHitbox.topLeftCorner.getXcord() + otherHitbox.getWidth();
        return leftSideX > otherRightSideX;
    }

}
