package ca.bcit.comp2522.termproject.christiebelal.ui;

import javafx.scene.paint.Color;

import static ca.bcit.comp2522.termproject.christiebelal.Variables.Variables.currentUsername;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactoryService;

public class UsernameIcon extends Icon {
    public UsernameIcon() {
        var text = getUIFactoryService().newText("user: " + currentUsername, Color.WHITE, 16.0);
        text.setTranslateX(10);
        text.setTranslateY(40);

        getChildren().addAll(text);
    }
}
