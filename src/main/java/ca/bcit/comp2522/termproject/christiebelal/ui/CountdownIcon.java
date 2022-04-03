package ca.bcit.comp2522.termproject.christiebelal.ui;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;


import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.getGameTimer;


public class CountdownIcon extends Icon{
    private final Text countdownTimer = getUIFactoryService().newText("", Color.WHITE, 21.0);
    private final BooleanProperty timerCondition = new SimpleBooleanProperty();
    private final IntegerProperty countdown = new SimpleIntegerProperty(0);
    private final BooleanBinding isCountdownGreaterZero = countdown.greaterThan(0);

    public CountdownIcon(){
        countdownTimer.setTranslateX(5);
        countdownTimer.setTranslateY(40);

        timerCondition.bind(isCountdownGreaterZero);
        countdownTimer.textProperty().bind(countdown.asString("%d seconds"));
        countdownTimer.visibleProperty().bind(isCountdownGreaterZero);
        getChildren().addAll(countdownTimer);

    }

    public void setCountdown(int seconds, int days){
        countdown.set(seconds);

        getGameTimer().runAtIntervalWhile(() -> {
            if (countdown.get() > 0){
                countdown.set(countdown.get() - 1);
                if (countdown.get() == 0){
                    VBox content = new VBox(
                            getUIFactoryService().newText("Days left until start of school: " + days),
                            getUIFactoryService().newText("Savings: "),
                            getUIFactoryService().newText("Goal: ")
                    );

                    Button btnClose = getUIFactoryService().newButton("Continue to next day...");
                    btnClose.setPrefWidth(300);

                    getDialogService().showBox("Today's Summary:", content, btnClose);
                }
            }
        }, Duration.seconds(1), timerCondition);
    }
}
