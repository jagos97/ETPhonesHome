package etphoneshome.graphics;

import java.util.ArrayList;

public enum AnimationFrames {

    POLICE_DEATH_LEFT(10, SpriteURL.POLICE_HURT_LEFT.getPath(), SpriteURL.POLICE_LEFT.getPath(), SpriteURL.POLICE_HURT_LEFT.getPath(), SpriteURL.POLICE_LEFT.getPath(), SpriteURL.POLICE_HURT_LEFT.getPath()),

    POLICE_DEATH_RIGHT(10, SpriteURL.POLICE_HURT_RIGHT.getPath(), SpriteURL.POLICE_RIGHT.getPath(), SpriteURL.POLICE_HURT_RIGHT.getPath(), SpriteURL.POLICE_RIGHT.getPath(), SpriteURL.POLICE_HURT_RIGHT.getPath()),

    SCIENTIST_DEATH_LEFT(10, SpriteURL.SCIENTIST_HURT_LEFT.getPath(), SpriteURL.SCIENTIST_LEFT.getPath(), SpriteURL.SCIENTIST_HURT_LEFT.getPath(), SpriteURL.SCIENTIST_LEFT.getPath(), SpriteURL.SCIENTIST_HURT_LEFT.getPath()),

    SCIENTIST_DEATH_RIGHT(10, SpriteURL.SCIENTIST_HURT_RIGHT.getPath(), SpriteURL.SCIENTIST_RIGHT.getPath(), SpriteURL.SCIENTIST_HURT_RIGHT.getPath(), SpriteURL.SCIENTIST_RIGHT.getPath(), SpriteURL.SCIENTIST_HURT_RIGHT.getPath()),

    ET_HURT_LEFT(10, SpriteURL.ET_HURT_LEFT.getPath(), SpriteURL.ET_LEFT.getPath(), SpriteURL.ET_HURT_LEFT.getPath(), SpriteURL.ET_LEFT.getPath(), SpriteURL.ET_HURT_LEFT.getPath()),

    ET_HURT_RIGHT(10, SpriteURL.ET_HURT_RIGHT.getPath(), SpriteURL.ET_RIGHT.getPath(), SpriteURL.ET_HURT_RIGHT.getPath(), SpriteURL.ET_RIGHT.getPath(), SpriteURL.ET_HURT_RIGHT.getPath()),
    
    END_GAME_ANIMATION_DOWN(5, SpriteURL.UFO.getPath(), SpriteURL.UFO.getPath(), SpriteURL.UFO_14.getPath(), SpriteURL.UFO_13.getPath(), SpriteURL.UFO_12.getPath(), SpriteURL.UFO_11.getPath(), SpriteURL.UFO_10.getPath(), SpriteURL.UFO_9.getPath(), SpriteURL.UFO_8.getPath(), SpriteURL.UFO_7.getPath(), SpriteURL.UFO_6.getPath(), SpriteURL.UFO_5.getPath(), SpriteURL.UFO_4.getPath(), SpriteURL.UFO_3.getPath(), SpriteURL.UFO_2.getPath(), SpriteURL.UFO_1.getPath(), SpriteURL.UFO_UNCUT.getPath(), SpriteURL.UFO_UNCUT.getPath()),

    END_GAME_ANIMATION_UP(5, SpriteURL.UFO_UNCUT.getPath(), SpriteURL.UFO_UNCUT.getPath(), SpriteURL.UFO_1.getPath(), SpriteURL.UFO_2.getPath(), SpriteURL.UFO_3.getPath(), SpriteURL.UFO_4.getPath(), SpriteURL.UFO_5.getPath(), SpriteURL.UFO_6.getPath(), SpriteURL.UFO_7.getPath(), SpriteURL.UFO_8.getPath(), SpriteURL.UFO_9.getPath(), SpriteURL.UFO_10.getPath(), SpriteURL.UFO_11.getPath(), SpriteURL.UFO_12.getPath(), SpriteURL.UFO_13.getPath(), SpriteURL.UFO_14.getPath(), SpriteURL.UFO.getPath(), SpriteURL.UFO.getPath(), SpriteURL.UFO.getPath(), SpriteURL.UFO.getPath());

    /**
     * Default frame length is 20
     */
    private int frameLength = 20;
    private ArrayList<String> framePaths = new ArrayList<>();

    /**
     * Constructor that uses default frameLength
     * @param framePaths The paths of all sprites used in the animation
     */
    AnimationFrames(String... framePaths) {
        for (String framePath : framePaths) {
            this.framePaths.add(framePath);
        }
    }


    /**
     * Constructor that uses custom frameLength
     * @param frameLength The frameLength of each frame in the animation
     * @param framePaths The paths of all sprites used in the animation
     */
    AnimationFrames(int frameLength, String... framePaths) {
        this.frameLength = frameLength;
        for (String framePath : framePaths) {
            this.framePaths.add(framePath);
        }
    }

    /**
     * Returns a list of paths to all images within the animation frames
     * @return framePaths
     */
    public ArrayList<String> getFramePaths() {
        return this.framePaths;
    }

    /**
     * Returns the frameLength
     * @return frameLength
     */
    public int getFrameLength() {
        return this.frameLength;
    }
}
