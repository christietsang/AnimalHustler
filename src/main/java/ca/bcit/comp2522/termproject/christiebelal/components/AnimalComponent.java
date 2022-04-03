package ca.bcit.comp2522.termproject.christiebelal.components;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.scene.image.Image;
import javafx.util.Duration;

import static ca.bcit.comp2522.termproject.christiebelal.Variables.Variables.SPAWN_TIMER;
import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static com.almasb.fxgl.dsl.FXGLForKtKt.image;
import static java.util.Objects.isNull;

public class AnimalComponent extends Component {

    private PhysicsComponent physics;

    private final AnimatedTexture texture;

    private final AnimationChannel animIdle;

    public AnimalComponent() {
        Image idleImage = image("brownCowIdle.png");
        animIdle = new AnimationChannel(idleImage, 4, 75, 55, Duration.seconds(2), 0, 1);
        texture = new AnimatedTexture(animIdle);
        texture.loopAnimationChannel(animIdle);
    }

    public void onAdded() {
        entity.getViewComponent().addChild(texture);
        FXGL.getGameTimer().runOnceAfter(() -> {
            if (!isNull(entity)) {
            entity.removeFromWorld();
        }}, Duration.seconds(5));
    }
}





