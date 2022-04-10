package ca.bcit.comp2522.termproject.christiebelal.ui;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Is the parent class for in-game icons.
 *
 * @author Christie Tsang
 * @author Belal Kourkmas
 * @version 2022
 */
public class Icon extends Parent {
    private static final int ICON_WIDTH = 150;
    private static final int ICON_HEIGHT = 65;
    private static final int COLOR_RED = 0;
    private static final int COLOR_GREEN = 0;
    private static final int COLOR_BLUE = 0;
    private static final double OPACITY = .6;
    private static final double FONT_STROKE_WIDTH = 2.0;
    private static final int ROUNDED_CORNER_WIDTH = 10;
    private static final int ROUNDED_CORNER_HEIGHT = 10;
    /**
     * Constructs an icon.
     */
    public Icon() {
        var background = new Rectangle(ICON_WIDTH, ICON_HEIGHT,
                Color.color(COLOR_RED, COLOR_GREEN, COLOR_BLUE, OPACITY));
        background.setStrokeWidth(FONT_STROKE_WIDTH);
        background.setArcWidth(ROUNDED_CORNER_WIDTH);
        background.setArcHeight(ROUNDED_CORNER_HEIGHT);
        getChildren().addAll(background);

    }
}
