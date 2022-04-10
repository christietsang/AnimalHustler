package ca.bcit.comp2522.termproject.christiebelal;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Returns a custom main menu.
 *
 * @author Christie Tsang
 * @author Belal Kourkmas
 * @version 2022
 */
public class MenuFactory extends SceneFactory {

    /**
     * Creates a new main menu.
     *
     * @return main menu
     */
    @NotNull
    public final FXGLMenu newMainMenu() {
        return new MainMenu();
    }
}
