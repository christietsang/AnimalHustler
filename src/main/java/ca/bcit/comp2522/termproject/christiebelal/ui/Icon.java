package ca.bcit.comp2522.termproject.christiebelal.ui;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class Icon extends Parent {

    public Icon(){
        var background = new Rectangle(120, 65, Color.color(0, 0, 0, 0.6));
        background.setStrokeWidth(2.0);
        background.setArcWidth(10);
        background.setArcHeight(10);

        getChildren().addAll(background);

    }
}
