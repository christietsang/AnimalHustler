package ca.bcit.comp2522.termproject.christiebelal;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;

/**
 * Returns a custom main menu.
 *
 * @author Christie Tsang
 * @author Belal Kourkmas
 * @version 2022
 */
public class MySceneFactory extends SceneFactory {

    @Override
    public FXGLMenu newMainMenu() {
        return new MainMenu(MenuType.MAIN_MENU);
    }

}