package ca.bcit.comp2522.termproject.christiebelal.ui;

import ca.bcit.comp2522.termproject.christiebelal.AnimalHustlerApp;
import ca.bcit.comp2522.termproject.christiebelal.DatabaseHandler;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;


import java.sql.SQLException;
import java.util.ArrayList;

import static ca.bcit.comp2522.termproject.christiebelal.Variables.Variables.MONEY;
import static ca.bcit.comp2522.termproject.christiebelal.Variables.Variables.currentUsername;
import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.getGameTimer;

/**
 * Handles the countdown clock GUI.
 *
 * @author Christie Tsang
 * @author Belal Kourkmas
 * @version 2022
 */
public class CountdownIcon extends Icon {
    private final Text countdownTimer = getUIFactoryService().newText("", Color.WHITE, 21.0);
    private final BooleanProperty timerCondition = new SimpleBooleanProperty();
    private final IntegerProperty countdown = new SimpleIntegerProperty(0);
    private final BooleanBinding isCountdownGreaterZero = countdown.greaterThan(0);

    public CountdownIcon() {
        countdownTimer.setTranslateX(5);
        countdownTimer.setTranslateY(40);

        countdownTimer.textProperty().bind(countdown.asString("   %d seconds"));
        countdownTimer.visibleProperty().bind(isCountdownGreaterZero);

        timerCondition.bind(isCountdownGreaterZero);
        getChildren().addAll(countdownTimer);

    }

    public void setCountdown(int seconds) {
        countdown.set(seconds);

        getGameTimer().runAtIntervalWhile(() -> {
            if (countdown.get() > 0) {
                countdown.set(countdown.get() - 1);
                if (countdown.get() == 0) {
                    ArrayList<String> top = new ArrayList<>();
                    try {
                        sendScore();
                        top = getScores();
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    StringBuilder allScores = new StringBuilder();
                    for (int i = 0; i < top.size(); i += 2) {
                        allScores.append(String.format("\t\t\t\t%d: %-20.20s \t %20s\n", (i/2 + 1), top.get(i), top.get(i+1)));
                    }
                    VBox content = new VBox(
                            getUIFactoryService().newText(String.format("\t\t\t\t\t\t  Savings: %d", geti("money"))),
                            getUIFactoryService().newText("\n"),
                            getUIFactoryService().newText(String.format("\t\t\t\t\t        HIGH SCORES")),
                            getUIFactoryService().newText(String.format("%s", allScores)));
                    Button btnClose = getUIFactoryService().newButton("Return to main menu");
                    btnClose.setPrefWidth(300);
                    btnClose.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            getGameController().gotoMainMenu();
                        }
                    });
                    getDialogService().showBox("Your Score:", content, btnClose);
                }
            }
        }, Duration.seconds(1), timerCondition);
    }
    private void sendScore() throws SQLException, ClassNotFoundException {
        DatabaseHandler.addScore(currentUsername, geti("money"));
    }
    private ArrayList<String> getScores() throws SQLException, ClassNotFoundException {
        return DatabaseHandler.getTopScores();
    }
}
