package etphoneshome.graphics;

import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Animation {

    private JFXPanel jfxPanel = new JFXPanel(); //this is needed for the class to run since there is an image attached

    private int tick;
    private int lastTick;
    private AnimationFrames animationFrames;
    private ArrayList<Image> frames = new ArrayList<>();

    public Animation(AnimationFrames animationFrames) {
        this.setAnimationFrames(animationFrames);
        this.lastTick = this.frames.size() * animationFrames.getFrameLength();
    }

    /**
     * Returns the current tick of the animation
     * @return tick
     */
    public int getTick() {
        return this.tick;
    }

    /**
     * Increment the tick by 1
     */
    public void incrementTick() {
        this.tick++;
    }

    /**
     * Returns the lastTick of this animation
     * @return lastTick
     */
    public int getLastTick() {
        return this.lastTick;
    }

    /**
     * Returns the sprite of the animation based on the current tick
     * @return An Image from the frames list
     */
    public Image getSprite() {
        return this.frames.get((int) Math.floor(this.tick / this.animationFrames.getFrameLength()));
    }

    /**
     * Returns the animation frames that are under use by this animation
     * @return animationFrames
     */
    public AnimationFrames getAnimationFrames() {
        return this.animationFrames;
    }

    /**
     * Sets the animation frames to a new set of animatio nframes
     * @param animationFrames new AnimationFrames
     */
    public void setAnimationFrames(AnimationFrames animationFrames) {
        this.animationFrames = animationFrames;
        this.frames.clear();
        for (String framePath : animationFrames.getFramePaths()) {
            this.frames.add(new Image(framePath));
        }
    }

}
