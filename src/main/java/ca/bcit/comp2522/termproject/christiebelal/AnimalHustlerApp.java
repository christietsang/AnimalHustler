package ca.bcit.comp2522.termproject.christiebelal;

import ca.bcit.comp2522.termproject.christiebelal.components.PlayerComponent;
import ca.bcit.comp2522.termproject.christiebelal.ui.CountdownIcon;
import ca.bcit.comp2522.termproject.christiebelal.ui.CurrencyIcon;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.virtual.VirtualButton;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsWorld;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.util.Duration;

import java.util.Map;

import static ca.bcit.comp2522.termproject.christiebelal.AnimalHustlerType.COW;
import static ca.bcit.comp2522.termproject.christiebelal.Variables.Variables.*;
import static com.almasb.fxgl.dsl.FXGL.*;


public class AnimalHustlerApp extends GameApplication {

    private Entity player;
    private Entity cow;
    private Integer days;
    private Component playerComponent;
    private CountdownIcon countdownIcon;
//    private double spawnTimer;


    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(900);
        settings.setHeight(720);
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new MySceneFactory());
    }

    protected void initGameVars(final Map<String, Object> vars) {
        vars.put(MONEY, 0);
        vars.put(CURRENT_LEVEL, 0);
    }

    // TODO: Is it possible to refactor this into a separate class?
    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).left();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
                System.out.println(getGameWorld().getCollidingEntities(player));

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
                System.out.println(getGameWorld().getCollidingEntities(player));

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
                System.out.println(getGameWorld().getCollidingEntities(player));

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
                System.out.println(getGameWorld().getCollidingEntities(player));
            }
        }, KeyCode.S, VirtualButton.DOWN);
    }

    protected void initUI() {
        addUINode(new CurrencyIcon(), 10, 10);
        addUINode(countdownIcon, 10, 90);

    }

    @Override
    protected void initGame() {
        initVarListeners();
        days = 10;
        getGameWorld().addEntityFactory(new AnimalHustlerFactory());
        setLevelFromMap("AnimalHustlerMap.tmx");
        player = spawn("player", 450, 450);
        Entity cow = spawn("cow",
                FXGLMath.random(0, getAppWidth() - 10),
                FXGLMath.random(0, getAppHeight() - 10));
        spawnCowTimer();
        playerComponent = player.getComponent(PlayerComponent.class);

        countdownIcon = new CountdownIcon();
        loadCurrentLevel();
    }

    private void spawnCowTimer() {
            getGameTimer().runOnceAfter(() -> {
                Entity cow = spawn("cow",
                        FXGLMath.random(0, getAppWidth() - 10),
                        FXGLMath.random(0, getAppHeight() - 10));
//                System.out.println(SPAWN_TIMER);
//                inc(SPAWN_TIMER, -1);
                spawnCowTimer();
            }, Duration.seconds(SPAWN_TIMER));

    }

    private void initVarListeners() {
        getWorldProperties().<Integer>addListener(MONEY, (old, newValue) -> {
            if (newValue > MAX_MONEY) {
                set(MONEY, MAX_MONEY);
            }
        });
    }

    @Override
    protected void initPhysics() {
        PhysicsWorld physicsWorld = getPhysicsWorld();
        physicsWorld.setGravity(0, 0);

        getGameTimer().runAtInterval(() -> {
            getGameWorld().getEntitiesByType(COW);
                }, Duration.seconds(1));

        physicsWorld.addCollisionHandler(new CollisionHandler(COW, AnimalHustlerType.PLAYER) {
            protected void onCollisionBegin(Entity cow, Entity player) {
                cow.removeFromWorld();
                if (SPAWN_TIMER > 1) {
                    SPAWN_TIMER -= 1;
                }
            }
        });

        physicsWorld.addCollisionHandler(new CollisionHandler(AnimalHustlerType.WALL, COW) {
            protected void onCollisionBegin(Entity wall, Entity cow) {
                cow.removeFromWorld();
                spawn("cow",
                        FXGLMath.random(0, getAppWidth() - 10),
                        FXGLMath.random(0, getAppHeight() - 10));
            }
        });


    }

    // TODO: Reset timer when the current level ends
    private void loadCurrentLevel() {
        set(CURRENT_LEVEL, geti(CURRENT_LEVEL) + 1);
        countdownIcon.setCountdown(60, days);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
