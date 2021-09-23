package etphoneshome.objects;

public class Hitbox {
    private Location botLeftCorner; //Change l to L for real code
    private int height, width;

    //Constructor
    public Hitbox(Location m, int h, int w) {
        botLeftCorner = m;
        height = h;
        width = w;
    }

    //Getter
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    //Collision
    boolean areColliding(Hitbox otherObject) {
        boolean colliding = false;

        int leftSideX = botLeftCorner.getXcord();
        int rightSideX = botLeftCorner.getXcord() + width;
        int otherRightSideX = otherObject.botLeftCorner.getXcord() + otherObject.getWidth(); //Make a getLocation???
        int otherLeftSideX = otherObject.botLeftCorner.getXcord();

        int topSideY = botLeftCorner.getYcord() + height;
        int bottomSideY = botLeftCorner.getYcord();
        int otherTopSideY = otherObject.botLeftCorner.getYcord() + otherObject.getHeight();
        int otherBottomSideY = otherObject.botLeftCorner.getYcord();

        //TOP SIDE
        //Top Surface Coming From Left
        if (otherRightSideX >= leftSideX && otherRightSideX <= rightSideX && otherBottomSideY <= topSideY) {
            colliding = true;
        }
        //Top Surface Coming From Right
        if (otherLeftSideX <= rightSideX && otherLeftSideX >= leftSideX && otherBottomSideY <= topSideY) {
            colliding = true;
        }

        //LEFT SIDE
        //Left Surface Coming From Top
        if (otherBottomSideY <= topSideY && otherBottomSideY >= bottomSideY && otherRightSideX >= leftSideX) {
            colliding = true;
        }
        //Left Surface Coming From Bottom
        if (otherTopSideY >= bottomSideY && otherTopSideY <= topSideY && otherRightSideX >= leftSideX) {
            colliding = true;
        }

        //BOTTOM SIDE
        //Bottom Surface Coming From left
        if (otherRightSideX >= leftSideX && otherRightSideX <= rightSideX && otherTopSideY >= bottomSideY) {
            colliding = true;
        }
        //Bottom Surface Coming From Right
        if (otherLeftSideX <= rightSideX && otherLeftSideX >= leftSideX && otherTopSideY >= bottomSideY) {
            colliding = true;
        }

        //RIGHT SIDE
        //Right Surface Coming From Top
        if (otherBottomSideY <= topSideY && otherBottomSideY >= bottomSideY && otherLeftSideX <= rightSideX) {
            colliding = true;
        }
        //Right Surface Coming From bottom
        if (otherTopSideY >= bottomSideY && otherTopSideY <= topSideY && otherLeftSideX <= rightSideX) {
            colliding = true;
        }
        return colliding;
    }

}
