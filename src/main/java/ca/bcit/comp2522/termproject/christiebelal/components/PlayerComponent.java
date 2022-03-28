package ca.bcit.comp2522.termproject.christiebelal.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.image;

public class PlayerComponent extends Component {

    private PhysicsComponent physics;

    private AnimatedTexture textureUp;
    private AnimatedTexture textureDown;
    private AnimatedTexture textureLeft;
    private AnimatedTexture textureRight;

    private AnimationChannel animIdleDown, animIdleRight, animIdleUp, animIdleLeft,
            animWalkUp, animWalkDown, animWalkRight, animWalkLeft;

    public PlayerComponent() {
        Image imageDown = image("ACharDown.png");
        Image imageUp = image("ACharUp.png");
        Image imageRight = image("ACharRight.png");
        Image imageLeft = image("ACharLeft.png");


        animIdleDown = new AnimationChannel(imageDown, 2, 72, 72, Duration.seconds(1), 0, 0);
        animWalkDown = new AnimationChannel(imageDown, 2, 72, 72, Duration.seconds(0.66), 0, 3);

        animIdleUp = new AnimationChannel(imageUp, 2, 72, 72, Duration.seconds(1), 0, 0);
        animWalkUp = new AnimationChannel(imageUp, 2, 72, 72, Duration.seconds(0.66), 0, 3);

        animIdleRight = new AnimationChannel(imageRight, 2, 72, 72, Duration.seconds(1), 0, 0);
        animWalkRight = new AnimationChannel(imageRight, 2, 72, 72, Duration.seconds(0.66), 0, 3);

        animIdleLeft = new AnimationChannel(imageLeft, 2, 72, 72, Duration.seconds(1), 0, 0);
        animWalkLeft = new AnimationChannel(imageLeft, 2, 72, 72, Duration.seconds(0.66), 0, 3);

        textureUp = new AnimatedTexture(animIdleUp);
        textureDown = new AnimatedTexture(animIdleDown);
        textureLeft = new AnimatedTexture(animIdleLeft);
        textureRight = new AnimatedTexture(animIdleRight);
        textureUp.loop();

    }

    @Override
    public void onUpdate(double tpf) {
        if (physics.getVelocityX() > 0 && physics.isMovingX()) {
            if (textureRight.getAnimationChannel() != animWalkRight) {
                textureRight.loopAnimationChannel(animWalkRight);
            }
        }
        if (physics.getVelocityX() < 0 && physics.isMovingX()) {
            if (textureLeft.getAnimationChannel() != animWalkLeft) {
                textureLeft.loopAnimationChannel(animWalkLeft);
            }
        }
        if (physics.getVelocityY() < 0 && physics.isMovingY()) {
            if (textureUp.getAnimationChannel() != animWalkUp) {
                textureUp.loopAnimationChannel(animWalkUp);
            }
        }
        if (physics.getVelocityY() > 0 && physics.isMovingY()) {
            if (textureDown.getAnimationChannel() != animWalkDown) {
                textureDown.loopAnimationChannel(animWalkDown);
            }
        } else {
            if (textureDown.getAnimationChannel() != animIdleDown) {
                textureDown.loopAnimationChannel(animIdleDown);
            }
        }
    }
    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(0, 0));
        entity.getViewComponent().addChild(textureDown);
    }

        public void left() {
//        getEntity().setScaleX(-1);
        physics.setVelocityX(-170);
    }

    public void right() {
//        getEntity().setScaleX(1);
        physics.setVelocityX(170);
    }

    public void up() {
//        getEntity().setScaleY(1);
        physics.setVelocityY(-170);
    }

    public void down() {
//        getEntity().setScaleY(-1);
        physics.setVelocityY(170);
    }

    public void stop() {
        physics.setVelocityX(0);
        physics.setVelocityY(0);
    }
}
