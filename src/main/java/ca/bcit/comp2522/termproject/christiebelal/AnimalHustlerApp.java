package ca.bcit.comp2522.termproject.christiebelal;

import ca.bcit.comp2522.termproject.christiebelal.components.PlayerComponent;
import ca.bcit.comp2522.termproject.christiebelal.ui.CountdownIcon;
import ca.bcit.comp2522.termproject.christiebelal.ui.CurrencyIcon;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.virtual.VirtualButton;
import javafx.scene.input.KeyCode;
import java.util.Map;

import static ca.bcit.comp2522.termproject.christiebelal.Variables.Variables.*;
import static com.almasb.fxgl.dsl.FXGL.*;


public class AnimalHustlerApp extends GameApplication {

    private Entity player;
    private Integer days;
    private Component playerComponent;
    private CountdownIcon countdownIcon;


    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(25 * 36);
        settings.setHeight(20 * 36);
    }

    protected void initGameVars(final Map<String, Object> vars){
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
        playerComponent = player.getComponent(PlayerComponent.class);
        countdownIcon = new CountdownIcon();
        loadCurrentLevel();

    }

    private void initVarListeners(){
        getWorldProperties().<Integer>addListener(MONEY, (old, newValue) -> {
            if (newValue > MAX_MONEY){
                set(MONEY, MAX_MONEY);
            }
        });
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 0);
    }

    // TODO: Reset timer when the current level ends
    private void loadCurrentLevel(){
        set(CURRENT_LEVEL, 0);
        countdownIcon.setCountdown(60, days);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
