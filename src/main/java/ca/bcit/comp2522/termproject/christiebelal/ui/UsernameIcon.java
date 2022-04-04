package ca.bcit.comp2522.termproject.christiebelal.ui;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.Text;

import static ca.bcit.comp2522.termproject.christiebelal.Variables.Variables.currentUsername;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactoryService;

public class UsernameIcon extends Parent {

    public UsernameIcon() {
        var background = new Rectangle(150, 65, Color.color(0, 0, 0, 0.6));
        background.setStrokeWidth(2.0);
        background.setArcWidth(10);
        background.setArcHeight(10);
        var text = getUIFactoryService().newText(currentUsername, Color.WHITE, 32.0);
        text.setTranslateX(100);
        text.setTranslateY(20);
        getChildren().addAll(text);

    }
}
