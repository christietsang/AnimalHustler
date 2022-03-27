package ca.bcit.comp2522.termproject.christiebelal;

import ca.bcit.comp2522.termproject.christiebelal.components.PlayerComponent;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.virtual.VirtualButton;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.time.Timer;
import com.almasb.fxgl.time.TimerAction;
import javafx.geometry.BoundingBox;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import static ca.bcit.comp2522.termproject.christiebelal.AnimalHustlerType.*;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class AnimalHustlerApp extends GameApplication {

    private Entity player;
    private Integer days;
    private Component playerComponent;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(25 * 36);
        settings.setHeight(20 * 36);
    }

    @Override
    protected void initInput() {
        onKeyDown(KeyCode.F, () -> {
            getNotificationService().pushNotification("Hello world");
            getNotificationService().pushNotification("Hi");
        });
        Input input = getInput();

        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).left();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.A, VirtualButton.LEFT);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).right();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.D, VirtualButton.RIGHT);

        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).up();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.W, VirtualButton.UP);

        getInput().addAction(new UserAction("down") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).down();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.S, VirtualButton.DOWN);
    }

    @Override
    protected void initGame() {
        days = 10;
        getGameWorld().addEntityFactory(new AnimalHustlerFactory());
        Level level = setLevelFromMap("AnimalHustlerMap.tmx");
        player = spawn("player", 450, 450);

        getGameTimer().runAtInterval(()-> {
            days--;
            getNotificationService().pushNotification("Your day is over.  You have " + days + " days until CST begins.");
        }, Duration.seconds(120));
        playerComponent = player.getComponent(PlayerComponent.class);
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 0);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
