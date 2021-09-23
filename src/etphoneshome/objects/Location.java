package etphoneshome.objects;

public class Location {
    /* This is the location class that holds 2-Dimensional coordinates and
     * calculates distances between objects of this particular class
     */
    private int xcord;
    private int ycord;

    /**
     * constructor to the location of object
     *
     * @param xCord xCordinate of object
     * @param yCord yCordinate of object
     */
    public Location(int xCord, int yCord) {
        this.xcord = xCord;
        this.ycord = yCord;
    }

    /**
     * returns the xcord of {@code Location}
     *
     * @return xcord of {@code Location}
     */
    public int getXcord() {
        return xcord;
    }

    /**
     * gets the ycord of {@code Location}
     *
     * @return ycord of {@code Location}
     */
    public int getYcord() {
        return ycord;
    }

    /**
     * sets the xcord of {@code Location}
     *
     * @param x new xcord of {@code Location}
     */
    public void setXcord(int x) {
        this.xcord = x;
    }

    /**
     * sets the ycord of {@code Location}
     *
     * @param y new ycord of {@code Location}
     */
    public void setYcord(int y) {
        this.ycord = y;
    }

    /**
     * adds to the xcord of {@code Location}
     *
     * @param x amount to add
     */
    public void addX(int x) {
        this.add(x, 0);
    }

    /**
     * adds to the ycord of {@code Location}
     *
     * @param y amount to add
     */
    public void addY(int y) {
        this.add(0, y);
    }

    /**
     * adds to the xcord and ycord of {@code Location}
     *
     * @param x amount to add to xcord
     * @param y amount to add to ycord
     */
    public void add(int x, int y) {
        this.xcord += x;
        this.ycord += y;
    }

    /**
     * returns distance between to objects
     *
     * @param m location of the other object
     * @return Distance between the 2 {@code Location}
     */
    public double getDistance(Location m) {
        /* The if statements are used to determine which value in the same axis is bigger
         * in order to not obtain negative differences
         */
        double xdif, ydif, distance;
        double x1 = m.getXcord();
        double y1 = m.getYcord();
        if (x1 > xcord) {
            xdif = x1 - xcord;
        } else if (xcord > x1) {
            xdif = xcord - x1;
        } else // x1 ==x2
        {
            xdif = 0;
        }
        if (y1 > ycord) {
            ydif = y1 - ycord;
        } else if (ycord > y1) {
            ydif = ycord - y1;
        } else // y1 == y2
        {
            ydif = 0;
        }
        //Using Pythagoras' theorem
        distance = Math.sqrt(xdif * xdif + ydif * ydif);
        return distance;
    }

    /**
     * returns a string representation of the {@code Location}
     */
    public String toString() {
        return "Location{x = " + xcord + ", y = " + ycord + "}";
    }

    public static void main(String args[]) {
        Location loc = new Location(1, 2);
        System.out.println("Object One X-Coordinate: " + loc.getXcord() + " Object Two Y-Coordiante: " + loc.getXcord());
        loc.setXcord(40);
        loc.setYcord(60);
        System.out.println("New Object One X-Coordinate: " + loc.getXcord() + " New Object One Y-Coordinate: " + loc.getYcord());
        Location loc2 = new Location(0, 0);
        Location loc3 = new Location(3, 4);
        double distance2to3 = loc2.getDistance(loc3);
        System.out.println("The distance from ObjectTwo to ObjectThree is: " + distance2to3);

    }

    /**
     * creates clone of the {@code Location} for encapsulation
     */
    public Location clone() {
        return new Location(this.xcord, this.ycord);
    }


}