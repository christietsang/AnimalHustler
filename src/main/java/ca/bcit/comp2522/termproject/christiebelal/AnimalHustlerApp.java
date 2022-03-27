package ca.bcit.comp2522.termproject.christiebelal;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.time.Timer;
import com.almasb.fxgl.time.TimerAction;
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

        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                player.translate(5, 0);
            }
        }, KeyCode.D);

        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                player.translate(-5, 0);
            }
        }, KeyCode.A);

        input.addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                player.translate(0, -5);
            }
        }, KeyCode.W);

        input.addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                player.translate(0, 5);
            }
        }, KeyCode.S);
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
    }



    public static void main(String[] args) {
        launch(args);
    }
}
