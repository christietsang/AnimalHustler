package ca.bcit.comp2522.termproject.christiebelal;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.level.Level;
import javafx.scene.input.KeyCode;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class AnimalHustlerApp extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {

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
        Level level = setLevelFromMap("AnimalHustlerPrototype.tmx");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
