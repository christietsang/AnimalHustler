package ca.bcit.comp2522.termproject.christiebelal;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.time.Timer;
import javafx.scene.input.KeyCode;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class AnimalHustlerApp extends GameApplication {

    private Entity player;
    public final Timer getMasterTimer() {
        return getMasterTimer();
    }
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(25*36);
        settings.setHeight(20*36);
    }

    @Override
    protected void initInput() {
        onKeyDown(KeyCode.F, () -> {
            getNotificationService().pushNotification("Hello world");
        });

    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new AnimalHustlerFactory());
        player = spawn("player");
        Level level = setLevelFromMap("AnimalHustlerMap.tmx");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
