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

    private AnimatedTexture texture;

    private AnimationChannel animIdle, animWalkUp, animWalkDown, animWalkRight, animWalkLeft;

    public PlayerComponent() {
        Image imageDown = image("ACharDown.png");
        Image imageUp = image("ACharUp.png");
        Image imageRight = image("ACharRight.png");
        Image imageLeft = image("ACharLeft.png");

        animIdle = new AnimationChannel(imageDown, 2, 72, 72, Duration.seconds(1), 0, 0);
        animWalkDown = new AnimationChannel(imageDown, 2, 72, 72, Duration.seconds(0.66), 0, 3);

        texture = new AnimatedTexture(animIdle);
        texture.loop();

    }

    @Override
    public void onUpdate(double tpf) {
        if (physics.isMovingX()) {
            if (texture.getAnimationChannel() != animWalkDown) {
                texture.loopAnimationChannel(animWalkDown);
            }
        } else {
            if (texture.getAnimationChannel() != animIdle) {
                texture.loopAnimationChannel(animIdle);
            }
        }
    }
    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(0, 0));
        entity.getViewComponent().addChild(texture);
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
