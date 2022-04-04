package ca.bcit.comp2522.termproject.christiebelal.ui;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

/**
 * Is the parent class for in-game icons.
 *
 * @author Christie Tsang
 * @author Belal Kourkmas
 * @version 2022
 */
public class Icon extends Parent {

    public Icon(){
        var background = new Rectangle(150, 65, Color.color(0, 0, 0, 0.6));
        background.setStrokeWidth(2.0);
        background.setArcWidth(10);
        background.setArcHeight(10);

        getChildren().addAll(background);

    }
}
