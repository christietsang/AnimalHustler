package ca.bcit.comp2522.termproject.christiebelal.ui;

import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGL.getip;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactoryService;
import static com.almasb.fxgl.dsl.FXGLForKtKt.texture;

/**
 * Handles the GUI for in-game currency.
 *
 * @author Christie Tsang
 * @author Belal Kourkmas
 * @version 2022
 */
public class CurrencyIcon extends Icon{
    public CurrencyIcon(){
        var currencyTexture = texture("money.png").multiplyColor(Color.GOLD);
        var text = getUIFactoryService().newText("", Color.WHITE, 32.0);
        text.textProperty().bind(getip("money").asString());
        text.setTranslateX(54);
        text.setTranslateY(45);

        getChildren().addAll(currencyTexture,text);
    }
}
