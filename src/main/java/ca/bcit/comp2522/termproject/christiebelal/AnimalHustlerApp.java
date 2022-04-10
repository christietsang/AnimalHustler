package ca.bcit.comp2522.termproject.christiebelal;

import ca.bcit.comp2522.termproject.christiebelal.components.AnimalComponent;
import ca.bcit.comp2522.termproject.christiebelal.components.PlayerComponent;
import ca.bcit.comp2522.termproject.christiebelal.ui.CountdownIcon;
import ca.bcit.comp2522.termproject.christiebelal.ui.CurrencyIcon;
import ca.bcit.comp2522.termproject.christiebelal.ui.UsernameIcon;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.virtual.VirtualButton;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsWorld;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.Map;

import static ca.bcit.comp2522.termproject.christiebelal.AnimalHustlerType.COW;
import static ca.bcit.comp2522.termproject.christiebelal.Variables.Variables.CURRENT_LEVEL;
import static ca.bcit.comp2522.termproject.christiebelal.Variables.Variables.MONEY;
import static ca.bcit.comp2522.termproject.christiebelal.Variables.Variables.SPAWN_TIMER;
import static com.almasb.fxgl.dsl.FXGL.addUINode;
import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getInput;
import static com.almasb.fxgl.dsl.FXGL.getPhysicsWorld;
import static com.almasb.fxgl.dsl.FXGL.geti;
import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.play;
import static com.almasb.fxgl.dsl.FXGL.set;
import static com.almasb.fxgl.dsl.FXGL.setLevelFromMap;
import static com.almasb.fxgl.dsl.FXGL.spawn;

/**
 * Drives the game.
 *
 * @author Christie Tsang
 * @author Belal Kourkmas
 * @version 2022
 */
public class AnimalHustlerApp extends GameApplication {
    private static final int GAME_LENGTH_SECONDS = 10;
    private static final int COW_FLAT_BONUS = 50;
    private static final int COW_HEALTH_MULTIPLIER = 10;
    private static final int PLAYER_STARTING_X = 450;
    private static final int PLAYER_STARTING_Y = 450;
    private static final int INITIAL_SPAWN_TIMER = 10;
    private static final float MILLISECOND_CONVERSION = 1000;
    private static final int WINDOW_MARGIN = 10;
    private static final int WINDOW_WIDTH = 900;
    private static final int WINDOW_HEIGHT = 720;

    private Entity player;
    private CountdownIcon countdownIcon;

    @Override
    protected final void initSettings(final GameSettings settings) {
        settings.setWidth(WINDOW_WIDTH);
        settings.setHeight(WINDOW_HEIGHT);
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new MainMenuFactory());
    }

    protected final void initGameVars(final Map<String, Object> vars) {
        vars.put(MONEY, 0);
        vars.put(CURRENT_LEVEL, 0);
    }
    protected final void initInput() {
        moveLeft();
        moveRight();
        moveUp();
        moveDown();
    }

    protected final void initUI() {
        final int currencyIconX = 10;
        final int currencyIconY = 10;
        final int countdownIconX = 10;
        final int countdownIconY = 90;
        final int usernameIconX = 700;
        final int usernameIconY = 10;

        addUINode(new CurrencyIcon(), currencyIconX, currencyIconY);
        addUINode(countdownIcon, countdownIconX, countdownIconY);
        addUINode(new UsernameIcon(), usernameIconX, usernameIconY);
    }

    @Override
    protected final void initGame() {
        initializeValues();
        getGameWorld().addEntityFactory(new AnimalHustlerFactory());
        setLevelFromMap("AnimalHustlerMap.tmx");
        player = spawn("player", PLAYER_STARTING_X, PLAYER_STARTING_Y);
        spawnRandomCow();
        spawnCowTimer();
        player.getComponent(PlayerComponent.class);
        countdownIcon = new CountdownIcon();
        loadCurrentLevel();
        play("background.wav");
    }

    private void initializeValues() {
        SPAWN_TIMER = INITIAL_SPAWN_TIMER;
    }

    private void spawnCowTimer() {
        final int half = 2;
        getGameTimer().runOnceAfter(() -> {
            spawnRandomCow();
            if (SPAWN_TIMER % 2 == 0) {
                getGameTimer().runOnceAfter(this::spawnRandomCow,
                        Duration.millis((SPAWN_TIMER * MILLISECOND_CONVERSION / half)));
            }
            spawnCowTimer();
        }, Duration.millis((SPAWN_TIMER * MILLISECOND_CONVERSION) / half));

    }

    private void spawnRandomCow() {
        spawn("cow",
                FXGLMath.random(0, getAppWidth() - WINDOW_MARGIN),
                FXGLMath.random(0, getAppHeight() - WINDOW_MARGIN));
    }

    @Override
    protected final void initPhysics() {
        PhysicsWorld physicsWorld = getPhysicsWorld();
        physicsWorld.setGravity(0, 0);

        getGameTimer().runAtInterval(() -> getGameWorld().getEntitiesByType(COW), Duration.seconds(1));

        physicsWorld.addCollisionHandler(new CollisionHandler(COW, AnimalHustlerType.PLAYER) {
            protected void onCollisionBegin(final Entity cow, final Entity localPlayer) {
                cow.removeFromWorld();
                if (SPAWN_TIMER > 1) {
                    SPAWN_TIMER -= 1;
                }
                player.getComponent(PlayerComponent.class).increaseSpeed();
                int cowHealth = cow.getComponent(AnimalComponent.class).getHP();
                play("cash.wav");
                inc(MONEY, COW_HEALTH_MULTIPLIER * cowHealth + COW_FLAT_BONUS);
            }
        });

        physicsWorld.addCollisionHandler(new CollisionHandler(AnimalHustlerType.WALL, COW) {
            protected void onCollisionBegin(final Entity wall, final Entity cow) {
                cow.removeFromWorld();
                spawnRandomCow();
            }
        });
    }

    private void moveLeft() {
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
    }

    private void moveRight() {
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
    }

    private void moveUp() {
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
    }

    private void moveDown() {
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

    private void loadCurrentLevel() {
        set(CURRENT_LEVEL, geti(CURRENT_LEVEL) + 1);
        countdownIcon.setCountdown(GAME_LENGTH_SECONDS);
    }

    /**
     * Starts the game.
     *
     * @param args List of string arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

}
