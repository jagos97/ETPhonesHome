package etphoneshome.managers;

import etphoneshome.entities.enemies.Enemy;
import etphoneshome.entities.enemies.Police;
import etphoneshome.entities.enemies.Scientist;
import etphoneshome.graphics.Animation;
import etphoneshome.graphics.AnimationFrames;
import etphoneshome.objects.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to handle and hold on to all current animations in the game
 */
public class AnimationManager {

    private EntityManager entityManager;

    private Animation characterAnimation = null, endGameAnimation = null;
    private HashMap<Enemy, Animation> enemyDeathAnimations = new HashMap<>();

    public AnimationManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Increment all animations by a single tick
     */
    public void incrementAnimations() {
        if (this.characterAnimation != null) {
            this.characterAnimation.incrementTick();
        }

        if (this.endGameAnimation != null) {
            this.endGameAnimation.incrementTick();
        }

        for (Animation animation : this.enemyDeathAnimations.values()) {
            animation.incrementTick();
        }
    }

    /**
     * Flip the animation frames of the character animation based on it's new direction
     * @param direction the new direction the character is facing
     */
    public void flipCharacterAnimationFrames(Direction direction) {
        if (this.characterAnimation != null) {
            String animationFramesName = this.characterAnimation.getAnimationFrames().name();
            if (direction == Direction.WEST) {
                this.characterAnimation.setAnimationFrames(AnimationFrames.valueOf(animationFramesName.replace("RIGHT", "LEFT")));
            } else if (direction == Direction.EAST) {
                this.characterAnimation.setAnimationFrames(AnimationFrames.valueOf(animationFramesName.replace("LEFT", "RIGHT")));
            }
        }
    }

    /**
     * Set the characterAnimation to a new animation
     * @param characterAnimation the new characterAnimation
     */
    public void setCharacterAnimation(Animation characterAnimation) {
        this.characterAnimation = characterAnimation;
    }

    /**
     * Returns the stored characterAnimation
     * @return the stored characterAnimation
     */
    public Animation getCharacterAnimation() {
        return this.characterAnimation;
    }

    /**
     * Create a new enemy death animation based on the type of Enemy and the direction its facing
     * @param enemy the enemy that is dying
     */
    public void addEnemyDeathAnimation(Enemy enemy) {
        if (enemy instanceof Police && !enemy.getIsDead()) {
            if (enemy.isFacingRight()) {
                this.enemyDeathAnimations.put(enemy, new Animation(AnimationFrames.POLICE_DEATH_RIGHT));
            } else {
                this.enemyDeathAnimations.put(enemy, new Animation(AnimationFrames.POLICE_DEATH_LEFT));
            }
        } else if (enemy instanceof Scientist && !enemy.getIsDead()) {
            if (enemy.isFacingRight()) {
                this.enemyDeathAnimations.put(enemy, new Animation(AnimationFrames.SCIENTIST_DEATH_RIGHT));
            } else {
                this.enemyDeathAnimations.put(enemy, new Animation(AnimationFrames.SCIENTIST_DEATH_LEFT));
            }
        }
    }

    public Animation getEnemyDeathAnimation(Enemy enemy) {
        return this.enemyDeathAnimations.get(enemy);
    }
    public void createEndGameAnimationDown() {
    	this.endGameAnimation = new Animation(AnimationFrames.END_GAME_ANIMATION_DOWN);
    }
    public void createEndGameAnimationUp() {
        this.endGameAnimation = new Animation(AnimationFrames.END_GAME_ANIMATION_UP);
    }

    public Animation getEndGameAnimation() {
        return this.endGameAnimation;
    }

    /**
     * Check to see if any animations are finished and if so, delete them
     */
    public void runGarbageCollector() {
        if (this.characterAnimation != null) {
            if (this.characterAnimation.getTick() >= this.characterAnimation.getLastTick()) {
                this.characterAnimation = null;
            }
        }

        if (this.endGameAnimation != null) {
            if (this.endGameAnimation.getTick() >= this.endGameAnimation.getLastTick()) {
                this.endGameAnimation = null;
            }
        }

        List<Enemy> tobeRemoved = new ArrayList<>();
        for (Map.Entry<Enemy, Animation> animationEntry : this.enemyDeathAnimations.entrySet()) {
            Animation enemyAnimation = animationEntry.getValue();
            if (enemyAnimation.getTick() >= enemyAnimation.getLastTick()) {
                tobeRemoved.add(animationEntry.getKey());
            }
        }

        for (Enemy enemy : tobeRemoved) {
            this.enemyDeathAnimations.remove(enemy);
            this.entityManager.removeEnemy(enemy);
        }
    }

}
