package ca.bcit.comp2522.termproject.christiebelal.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * Handles the behavior of main character.
 *
 * @author Christie Tsang
 * @author Belal Kourkmas
 * @version 2022
 */
public class PlayerComponent extends Component {
    private static final int FRAME_WIDTH_HEIGHT = 72;
    private static final double ANIMATION_DURATION = 0.66;
    private static final int ENDING_FRAME = 3;
    private static final int NUM_SPEED = 300;
    private static final int SPEED_INCREMENT = 10;
    private static final int MSG_DURATION = 3;
    private int speed = NUM_SPEED;

    private PhysicsComponent physics;

    private final AnimatedTexture textureUp;
    private final AnimatedTexture textureDown;
    private final AnimatedTexture textureLeft;
    private final AnimatedTexture textureRight;
    private final AnimationChannel animIdleDown;
    private final AnimationChannel animIdleRight;
    private final AnimationChannel animIdleUp;
    private final AnimationChannel animIdleLeft;
    private final AnimationChannel animWalkUp;
    private final AnimationChannel animWalkDown;
    private final AnimationChannel animWalkRight;
    private final AnimationChannel animWalkLeft;

    /**
     * Handles animations for the character.
     */
    public PlayerComponent() {
        Image imageDown = image("ACharDown.png");
        Image imageUp = image("ACharUp.png");
        Image imageRight = image("ACharRight.png");
        Image imageLeft = image("ACharLeft.png");

        animWalkUp = new AnimationChannel(imageUp, 2, FRAME_WIDTH_HEIGHT, FRAME_WIDTH_HEIGHT,
                Duration.seconds(ANIMATION_DURATION), 0, ENDING_FRAME);
        animWalkDown = new AnimationChannel(imageDown, 2, FRAME_WIDTH_HEIGHT, FRAME_WIDTH_HEIGHT,
                Duration.seconds(ANIMATION_DURATION), 0, ENDING_FRAME);
        animWalkLeft = new AnimationChannel(imageLeft, 2, FRAME_WIDTH_HEIGHT, FRAME_WIDTH_HEIGHT,
                Duration.seconds(ANIMATION_DURATION), 0, ENDING_FRAME);
        animWalkRight = new AnimationChannel(imageRight, 2, FRAME_WIDTH_HEIGHT, FRAME_WIDTH_HEIGHT,
                Duration.seconds(ANIMATION_DURATION), 0, ENDING_FRAME);

        animIdleUp = new AnimationChannel(imageUp, 2, FRAME_WIDTH_HEIGHT, FRAME_WIDTH_HEIGHT,
                Duration.seconds(1), 0, 0);
        animIdleDown = new AnimationChannel(imageDown, 2, FRAME_WIDTH_HEIGHT, FRAME_WIDTH_HEIGHT,
                Duration.seconds(1), 0, 0);
        animIdleLeft = new AnimationChannel(imageLeft, 2, FRAME_WIDTH_HEIGHT, FRAME_WIDTH_HEIGHT,
                Duration.seconds(1), 0, 0);
        animIdleRight = new AnimationChannel(imageRight, 2, FRAME_WIDTH_HEIGHT, FRAME_WIDTH_HEIGHT,
                Duration.seconds(1), 0, 0);
        textureUp = new AnimatedTexture(animIdleUp);
        textureDown = new AnimatedTexture(animIdleDown);
        textureLeft = new AnimatedTexture(animIdleLeft);
        textureRight = new AnimatedTexture(animIdleRight);

    }

    /**
     * Increases player speed.
     */
    public void increaseSpeed() {
        speed += SPEED_INCREMENT;
    }

    /**
     * Animates the character walking in each direction.
     *
     * @param tpf is the time per frame for animation, must be a double
     */
    @Override
    public void onUpdate(final double tpf) {
        if (this.physics.getVelocityX() > 0 && physics.isMovingX()) {
            texturesRight(textureRight, textureLeft, animWalkRight);
        }
        if (physics.getVelocityX() < 0 && physics.isMovingX()) {
            texturesRight(textureLeft, textureRight, animWalkLeft);
        }
        if (physics.getVelocityY() < 0 && physics.isMovingY()) {
            texturesUp(textureUp, textureLeft, textureRight, animWalkUp);
        }
        if (physics.getVelocityY() > 0 && physics.isMovingY()) {
            texturesDown(textureDown, textureUp, textureLeft, textureRight, animWalkDown);
        }
    }

    private void tutorial() {
        getNotificationService().setBackgroundColor(Color.BLUE);
        getGameTimer().runOnceAfter(() -> getNotificationService().pushNotification("WASD to Move"),
                Duration.millis(1));
        getGameTimer().runOnceAfter(() -> getNotificationService().pushNotification("Feed the cows to get money!"),
                Duration.seconds(MSG_DURATION));
    }

    private void texturesDown(final AnimatedTexture localTextureDown, final AnimatedTexture localTextureUp,
                              final AnimatedTexture localTextureLeft, final AnimatedTexture localTextureRight,
                              final AnimationChannel localAnimWalkDown) {
        if (!entity.getViewComponent().getChildren().contains(localTextureDown)) {
            entity.getViewComponent().removeChild(localTextureUp);
            entity.getViewComponent().removeChild(localTextureLeft);
            entity.getViewComponent().removeChild(localTextureRight);
            entity.getViewComponent().addChild(localTextureDown);
        }
        if (localTextureDown.getAnimationChannel() != localAnimWalkDown) {
            localTextureDown.loopAnimationChannel(localAnimWalkDown);
        }
    }

    private void texturesUp(final AnimatedTexture localTextureUp, final AnimatedTexture localTextureLeft, final
    AnimatedTexture localTextureRight, final AnimationChannel localAnimWalkUp) {
        texturesDown(localTextureUp, textureDown, localTextureLeft, localTextureRight, localAnimWalkUp);
    }

    private void texturesRight(final AnimatedTexture localTextureRight, final AnimatedTexture localTextureLeft, final
    AnimationChannel localAnimWalkRight) {
        texturesUp(localTextureRight, textureUp, localTextureLeft, localAnimWalkRight);
    }

    /**
     * Handles behavior of character when first spawned.
     */
    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(0, 0));
        entity.getViewComponent().addChild(textureDown);
        tutorial();
    }

    /**
     * Increases player speed when moving to the left.
     */
    public void left() {
//        getEntity().setScaleX(-1);
        physics.setVelocityX(speed * -1);
    }

    /**
     * Increases player speed when moving to the right.
     */
    public void right() {
//        getEntity().setScaleX(1);
        physics.setVelocityX(speed);
    }

    /**
     * Increases player speed when moving up the map.
     */
    public void up() {
//        getEntity().setScaleY(1);
        physics.setVelocityY(speed * -1);
    }

    /**
     * Increases player speed when moving down the map.
     */
    public void down() {
//        getEntity().setScaleY(-1);
        physics.setVelocityY(speed);
    }

    /**
     * Stops character movement and animation.
     */
    public void stop() {
        physics.setVelocityX(0);
        physics.setVelocityY(0);
        textureDown.loopAnimationChannel(animIdleDown);
        textureUp.loopAnimationChannel(animIdleUp);
        textureLeft.loopAnimationChannel(animIdleLeft);
        textureRight.loopAnimationChannel(animIdleRight);
    }
}
