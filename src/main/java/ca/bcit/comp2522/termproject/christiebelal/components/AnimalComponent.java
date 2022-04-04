package ca.bcit.comp2522.termproject.christiebelal.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.scene.image.Image;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.image;
import static java.util.Objects.isNull;

/**
 * Handles the behavior of spawned animals.
 *
 * @author Christie Tsang
 * @author Belal Kourkmas
 * @version 2022
 */
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
        FXGL.getGameTimer().runAtInterval(() -> {
                    if (!isNull(entity)) {
                        var hp = entity.getComponent(HealthIntComponent.class);
                        if (hp.getValue() > 1 && !isNull(entity)) {
                            hp.damage(1);
                        } else if (!isNull(entity)) {
                            entity.removeFromWorld();
                        }
                    }
                },
                Duration.millis((SPAWN_TIMER * 1000 / 10) + 100), 10);
    }
}





