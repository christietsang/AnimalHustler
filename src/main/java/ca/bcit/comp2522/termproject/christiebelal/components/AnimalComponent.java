package ca.bcit.comp2522.termproject.christiebelal.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;

import static ca.bcit.comp2522.termproject.christiebelal.Variables.Variables.SPAWN_TIMER;
import static com.almasb.fxgl.dsl.FXGLForKtKt.image;
import static com.almasb.fxgl.dsl.FXGLForKtKt.spawn;
import static java.util.Objects.isNull;

/**
 * Handles the behavior of spawned animals.
 *
 * @author Christie Tsang
 * @author Belal Kourkmas
 * @version 2022
 */
public class AnimalComponent extends Component {
    private static final int FRAMES_PER_ROW = 4;
    private static final int FRAME_WIDTH = 75;
    private static final int FRAME_HEIGHT = 55;
    private static final int START_FRAME = 0;
    private static final int END_FRAME = 1;
    private static final int SPAWN_X = 64;
    private static final int SPAWN_Y = 64;
    private static final float HEALTH_TICKS = 10;
    private static final int HEALTH_TICK = 10;
    private static final int TO_MILLISECONDS = 1000;
    private static final int OFFSET = 100;

//    private PhysicsComponent physics;

    private final AnimatedTexture texture;

    /**
     * Handles animations for animals.
     */
    public AnimalComponent() {
        Image idleImage = image("brownCowIdle.png");
        AnimationChannel animIdle = new AnimationChannel(idleImage, FRAMES_PER_ROW, FRAME_WIDTH, FRAME_HEIGHT,
                Duration.seconds(2), START_FRAME, END_FRAME);
        texture = new AnimatedTexture(animIdle);
        texture.loopAnimationChannel(animIdle);
    }

    /**
     * Handles behavior when animals are added to the game.
     */
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
        FXGL.getGameTimer().runAtInterval(() -> {
                    if (!isNull(entity)) {
                        var hp = entity.getComponent(HealthIntComponent.class);
                        if (hp.getValue() > 1 && !isNull(entity)) {
                            hp.damage(1);
                        } else if (!isNull(entity)) {
                            Point2D explosionSpawnPoint = entity.getCenter().subtract(SPAWN_X, SPAWN_Y);
                            spawn("explosion", explosionSpawnPoint);
                            entity.removeFromWorld();
                        }
                    }
                },
                Duration.millis((SPAWN_TIMER * TO_MILLISECONDS / HEALTH_TICKS) + OFFSET), HEALTH_TICK);
    }

    /**
     * Returns the health points of each animal.
     *
     * @return health points as an integer
     */
    public int getHP() {
        var hp = entity.getComponent(HealthIntComponent.class);
        return hp.getValue();
    }
}





