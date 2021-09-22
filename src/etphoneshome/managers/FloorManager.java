package etphoneshome.managers;

import etphoneshome.graphics.GraphicsRepainter;
import etphoneshome.graphics.SpriteURL;
import etphoneshome.objects.Location;
import etphoneshome.objects.Velocity;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;

import java.awt.*;

public class FloorManager {
    JFXPanel jfxPanel = new JFXPanel(); //this is needed for the class to run since there is an image attached

    /**
     * graphics asscoiated with {@code FloorManager}
     */
    private GraphicsRepainter graphicsRepainter;

    /**
     * The velocity of the floor which makes it look like you're actually moving
     */
    private Velocity floorVelocity = new Velocity();
    private Location floorLocation;
    private Image floorSprite = new Image(SpriteURL.BRICK_FLOOR.getPath());

    /**
     * constructor that sets the graphicsRepainter of {@code FloorManager}
     *
     * @param graphicsRepainter graphics of {@code Floor Manager}
     */
    public FloorManager(GraphicsRepainter graphicsRepainter) {
        this.graphicsRepainter = graphicsRepainter;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        floorLocation = new Location(0, (int) screenSize.getHeight() - 100);
    }

    /**
     * return velocity of floor
     *
     * @return velocity of floor
     */
    public Velocity getFloorVelocity() {
        return this.floorVelocity;
    }

    /**
     * returns location of floor
     *
     * @return location of floor
     */
    public Location getFloorLocation() {
        return this.floorLocation;
    }


    /**
     * Returns the floorSprite
     *
     * @return floorSprite
     */
    public Image getFloorSprite() {
        return this.floorSprite;
    }

    /**
     * updates velocity of the floor
     */
    public void updateFloorLocation() {
        //gets initial velocity
        int newX = this.floorLocation.getXcord() + (int) this.floorVelocity.getHorizontalVelocity();
        int newY = this.floorLocation.getYcord() + (int) this.floorVelocity.getVerticalVelocity();

        this.floorLocation = new Location(newX, newY);
        if (newX >= 0) {
            newX = this.floorLocation.getXcord() - this.graphicsRepainter.WIDTH/2;
        }
        this.floorLocation = new Location(newX, newY);
        if (newX <= -this.graphicsRepainter.WIDTH) {
            newX = this.floorLocation.getXcord() + this.graphicsRepainter.WIDTH/2;
        }
        this.floorLocation = new Location(newX, newY);
    }
}
