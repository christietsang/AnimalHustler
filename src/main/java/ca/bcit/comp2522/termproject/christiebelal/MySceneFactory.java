package ca.bcit.comp2522.termproject.christiebelal;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;

public class MySceneFactory extends SceneFactory {

    @Override
    public FXGLMenu newMainMenu() {
        return new MainMenu(MenuType.MAIN_MENU);
    }

//    @Override
//    public FXGLMenu newGameMenu() {
//        return new MainMenu(MenuType.GAME_MENU);
//    }
}