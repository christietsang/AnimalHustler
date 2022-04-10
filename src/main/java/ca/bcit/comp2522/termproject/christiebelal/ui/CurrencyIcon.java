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
public class CurrencyIcon extends Icon {
    private static final int CURRENCY_BOX_X = 54;
    private static final int CURRENCY_BOX_Y = 45;
    private static final double CURRENCY_FONT_SIZE = 20.0;
    /**
     * Constructs a currency icon.
     */
    public CurrencyIcon() {
        var currencyTexture = texture("money.png").multiplyColor(Color.GOLD);
        var text = getUIFactoryService().newText("", Color.WHITE, CURRENCY_FONT_SIZE);
        text.textProperty().bind(getip("money").asString());
        text.setTranslateX(CURRENCY_BOX_X);
        text.setTranslateY(CURRENCY_BOX_Y);

        getChildren().addAll(currencyTexture, text);
    }
}
