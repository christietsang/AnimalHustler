package ca.bcit.comp2522.termproject.christiebelal.ui;

import ca.bcit.comp2522.termproject.christiebelal.DatabaseHandler;
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


import java.sql.SQLException;
import java.util.ArrayList;

import static ca.bcit.comp2522.termproject.christiebelal.Variables.Variables.currentUsername;
import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * Handles the countdown clock GUI.
 *
 * @author Christie Tsang
 * @author Belal Kourkmas
 * @version 2022
 */
public class CountdownIcon extends Icon {
    private static final double FONT_SIZE = 21.0;
    private static final int COUNTDOWN_X = 5;
    private static final int COUNTDOWN_Y = 40;
    private static final int CLOSE_BUTTON_WIDTH = 300;

    private final BooleanProperty timerCondition = new SimpleBooleanProperty();
    private final IntegerProperty countdown = new SimpleIntegerProperty(0);

    /**
     * Constructs a countdown icon.
     */
    public CountdownIcon() {
        Text countdownTimer = getUIFactoryService().newText("", Color.WHITE, FONT_SIZE);
        countdownTimer.setTranslateX(COUNTDOWN_X);
        countdownTimer.setTranslateY(COUNTDOWN_Y);

        countdownTimer.textProperty().bind(countdown.asString("   %d seconds"));
        BooleanBinding isCountdownGreaterZero = countdown.greaterThan(0);
        countdownTimer.visibleProperty().bind(isCountdownGreaterZero);

        timerCondition.bind(isCountdownGreaterZero);
        getChildren().addAll(countdownTimer);

    }

    /**
     * Sets a countdown times.
     * @param seconds amount of seconds to count down
     */
    public void setCountdown(final int seconds) {
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
                        allScores.append(String.format("\t\t\t\t%d: %-20.20s \t %20s\n", (i / 2 + 1), top.get(i), "$"
                                + top.get(i + 1)));
                    }
                    VBox scoreBox = createScoreBox(allScores);
                    Button btnClose = createMainMenuButton();
                    getDialogService().showBox("RESULTS", scoreBox, btnClose);
                    getAudioPlayer().stopAllSoundsAndMusic();
                }
            }
        }, Duration.seconds(1), timerCondition);
    }

    private Button createMainMenuButton() {
        Button btnClose = getUIFactoryService().newButton("Return to main menu");
        btnClose.setPrefWidth(CLOSE_BUTTON_WIDTH);
        btnClose.setOnAction(actionEvent -> getGameController().gotoMainMenu());
        return btnClose;
    }

    private void sendScore() throws SQLException, ClassNotFoundException {
        DatabaseHandler.addScore(currentUsername, geti("money"));
    }

    private VBox createScoreBox(final StringBuilder allScores) {
        return new VBox(
        getUIFactoryService().newText(String.format("\t\t\t\t\t\t  Score: $%d",
                geti("money"))),
                getUIFactoryService().newText("\n"),
                getUIFactoryService().newText("\t\t\t\t\t        HIGH SCORES"),
                getUIFactoryService().newText(String.format("%s", allScores)));
    }

    private ArrayList<String> getScores() throws SQLException, ClassNotFoundException {
        return DatabaseHandler.getTopScores();
    }
}
