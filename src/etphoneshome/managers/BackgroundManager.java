package etphoneshome.managers;

import etphoneshome.graphics.GraphicsRepainter;
import etphoneshome.objects.Level;
import etphoneshome.objects.Location;
import etphoneshome.objects.Velocity;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;

public class BackgroundManager {
    JFXPanel jfxPanel = new JFXPanel(); //this is needed for the class to run since there is an image attached

    /**
     * graphics asscoiated with {@code BackgroundManager}
     */
    private GraphicsRepainter graphicsRepainter;

    /**
     * constructor that sets the graphicsRepainter of {@code BackgroundManager}
     *
     * @param graphicsRepainter graphics of {@code Background Manager}
     */
    public BackgroundManager(GraphicsRepainter graphicsRepainter) {
        this.graphicsRepainter = graphicsRepainter;
    }

    /**
     * The velocity of the background which makes it look like you're actually moving
     */
    private Velocity backgroundVelocity = new Velocity();
    private Location backgroundLocation = new Location(0, 0);
    private Image backgroundSprite = new Image("images/backgrounds/background.jpg");

    /**
     * return velocity of background
     *
     * @return velocity of background
     */
    public Velocity getBackgroundVelocity() {
        return this.backgroundVelocity;
    }

    /**
     * returns location of background
     *
     * @return location of background
     */
    public Location getBackgroundLocation() {
        return this.backgroundLocation;
    }

    /**
     * Loads the background of a specific level
     *
     * @param level the level to load the background of
     */
    public void loadBackground(Level level) {
        this.backgroundSprite = new Image(level.getBackgroundFilePath());
    }


    /**
     * Returns the backgroundSprite
     *
     * @return backgroundSprite
     */
    public Image getBackgroundSprite() {
        return this.backgroundSprite;
    }

    /**
     * updates velocity of the background
     */
    public void updateBackgroundLocation() {
        //gets initial velocity
        int newX = this.backgroundLocation.getXcord() + (int) this.backgroundVelocity.getHorizontalVelocity();
        int newY = this.backgroundLocation.getYcord() + (int) this.backgroundVelocity.getVerticalVelocity();

        this.backgroundLocation = new Location(newX, newY);
        if (newX <= this.graphicsRepainter.WIDTH * -2) {
            newX = this.backgroundLocation.getXcord() + this.graphicsRepainter.WIDTH;
        }
        this.backgroundLocation = new Location(newX, newY);
        if (newX >= -this.graphicsRepainter.WIDTH) {
            newX = this.backgroundLocation.getXcord() - this.graphicsRepainter.WIDTH;
        }
        this.backgroundLocation = new Location(newX, newY);
    }
}
