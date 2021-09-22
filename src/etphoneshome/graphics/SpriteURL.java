package etphoneshome.graphics;

/**
 * Holds constant enums which hold paths to images used within the game so that if a path is changed, the game continues to run perfectly fine
 */
public enum SpriteURL {

    GENERIC_OBSTACLE("images/sprites/obstacles/genericObstacle.png"),
    REGULAR_PLATFORM("images/sprites/obstacles/platform/regularPlatform.png"),
    LEFT_END_PLATFORM("images/sprites/obstacles/platform/leftEndPlatform.png"),
    RIGHT_END_PLATFORM("images/sprites/obstacles/platform/rightEndPlatform.png"),
    SINGLE_PLATFORM("images/sprites/obstacles/platform/singlePlatform.png"),
    PLACEHOLDER_SPRITE("images/sprites/entities/PLACEHOLDERSPRITE.jpg"),
    ET_LEFT("images/sprites/entities/character/et_sprite_left_gentleman.png"),
    ET_RIGHT("images/sprites/entities/character/et_sprite_right_gentleman.png"),
    ET_HURT_LEFT("images/sprites/entities/character/et_hurt_sprite_left_gentleman.png"),
    ET_HURT_RIGHT("images/sprites/entities/character/et_hurt_sprite_right_gentleman.png"),
    POLICE_LEFT("images/sprites/entities/enemies/police_sprite_left.png"),
    POLICE_RIGHT("images/sprites/entities/enemies/police_sprite_right.png"),
    POLICE_HURT_LEFT("images/sprites/entities/enemies/police_hurt_sprite_left.png"),
    POLICE_HURT_RIGHT("images/sprites/entities/enemies/police_hurt_sprite_right.png"),
    SCIENTIST_LEFT("images/sprites/entities/enemies/scientist_sprite_left.png"),
    SCIENTIST_RIGHT("images/sprites/entities/enemies/scientist_sprite_right.png"),
    SCIENTIST_HURT_LEFT("images/sprites/entities/enemies/scientist_hurt_sprite_left.png"),
    SCIENTIST_HURT_RIGHT("images/sprites/entities/enemies/scientist_hurt_sprite_right.png"),
    PHONE("images/sprites/phone.png"),
    PHONE_ANTENNA("images/sprites/collectibles/phone pieces/phoneAntenna.png"),
    PHONE_CHASSIS("images/sprites/collectibles/phone pieces/phoneChassis.png"),
    PHONE_KEYPAD("images/sprites/collectibles/phone pieces/phoneKeypad.png"),
    RP_BROWN("images/sprites/collectibles/reese pieces/RP_brown.png"),
    RP_ORANGE("images/sprites/collectibles/reese pieces/RP_orange.png"),
    RP_YELLOW("images/sprites/collectibles/reese pieces/RP_yellow.png"),
    HEART("images/sprites/heart.png"),
    FLASK("images/sprites/flask.png"),
    GAMEOVER("images/sprites/end game sprites/gameover.png"),
    YOU_WON("images/sprites/end game sprites/you-won.png"),
    BACKGROUND("images/backgrounds/backgroundRESIZED.jpg"),
    FINISHLINE_LEVEL_0("images/sprites/end game sprites/finishLine_level_1.png"),
    FINISHLINE_LEVEL_1("images/sprites/end game sprites/finishLine_level_1.png"),
    FINISHLINE_LEVEL_2("images/sprites/end game sprites/finishLine_level_2.png"),
    FINISHLINE_LEVEL_3("images/sprites/end game sprites/finishLine_level_3.png"),
	LEVEL1("images/sprites/levels/Level1.png"),
	LEVEL2("images/sprites/levels/Level2.png"),
	LEVEL3("images/sprites/levels/Level3.png"),
    MAIN_MENU("images/menus/menu_BACKGROUND_gentlemanDLC.png"),
    INSTRUCTIONS_MENU("images/menus/menu_INSTRUCTIONS.png"),
    PAUSE_MENU("images/menus/PAUSE_MENU.png"),
    BRICK_FLOOR("images/floors/brick_floor.png"),
    UFO("images/sprites/UFOs/UFO.png"),
    UFO_UNCUT("images/sprites/UFOs/UFO_UNCUT.png"),
    UFO_1("images/sprites/UFOs/UFO_1.png"),
    UFO_2("images/sprites/UFOs/UFO_2.png"),
    UFO_3("images/sprites/UFOs/UFO_3.png"),
    UFO_4("images/sprites/UFOs/UFO_4.png"),
    UFO_5("images/sprites/UFOs/UFO_5.png"),
    UFO_6("images/sprites/UFOs/UFO_6.png"),
    UFO_7("images/sprites/UFOs/UFO_7.png"),
    UFO_8("images/sprites/UFOs/UFO_8.png"),
    UFO_9("images/sprites/UFOs/UFO_9.png"),
    UFO_10("images/sprites/UFOs/UFO_10.png"),
    UFO_11("images/sprites/UFOs/UFO_11.png"),
    UFO_12("images/sprites/UFOs/UFO_12.png"),
    UFO_13("images/sprites/UFOs/UFO_13.png"),
    UFO_14("images/sprites/UFOs/UFO_14.png");

    /**
     * Path to the image
     */
    private final String path;

    /**
     * creates a new SpriteURL constant attached to the given path
     * @param path Path to the image
     */
    SpriteURL(String path) {
        this.path = path;
    }

    /**
     * Returns the path to the image
     * @return path
     */
    public String getPath() {
        return this.path;
    }
}
