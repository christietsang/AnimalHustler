package ca.bcit.comp2522.termproject.christiebelal.ui;

import javafx.scene.paint.Color;

import static ca.bcit.comp2522.termproject.christiebelal.Variables.Variables.currentUsername;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactoryService;

/**
 * Defines a username Icon.
 *
 * @author Belal Kourkmas
 * @author Christie Tsang
 * @version 2022
 */
public class UsernameIcon extends Icon {
    private static final double FONT_SIZE = 16.0;
    private static final int TEXT_X = 10;
    private static final int TEXT_Y = 40;

    /**
     * Constructs a username icon.
     */
    public UsernameIcon() {
        var text = getUIFactoryService().newText("user: " + currentUsername, Color.WHITE, FONT_SIZE);
        text.setTranslateX(TEXT_X);
        text.setTranslateY(TEXT_Y);

        getChildren().addAll(text);
    }
}
