package ca.bcit.comp2522.termproject.christiebelal;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.StringBinding;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

import static ca.bcit.comp2522.termproject.christiebelal.Variables.Variables.currentUsername;

/**
 * Handles the main menu log in.
 *
 * @author Christie Tsang
 * @author Belal Kourkmas
 * @version 2022
 */
public class MainMenu extends FXGLMenu {
    private TextField username;
    private TextField password;
    private Text badLogin;

    /**
     * Constructs a main menu.
     */
    public MainMenu() {
        super(MenuType.MAIN_MENU);
        username = createUsername();
        password = createPassword();
        badLogin = createBadLogin();
        addChild(username);
        addChild(password);
        Button loginButton = createLoginButton();
        Button createButton = createCreateButton();
        addChild(loginButton);
        // Login button
        loginHandler(loginButton);
        // Create account button
        createAccountHandler(createButton);
    }

    private void loginHandler(final Button loginButton) {
        loginButton.setOnAction(actionEvent -> {
            String usernameString = username.getText();
            String passwordString = password.getText();
            removeChild(badLogin);
            try {
                if (DatabaseHandler.checkUserNamePassword(usernameString, passwordString)) {
                    currentUsername = usernameString;
                    fireNewGame();
                } else {
                    addChild(badLogin);
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void createAccountHandler(final Button createButton) {
        // Create account button
        createButton.setOnAction(actionEvent -> {
            String usernameString = username.getText();
            String passwordString = password.getText();
            removeChild(badLogin);
            try {
                if (
                        DatabaseHandler.CreateUserName(usernameString, passwordString)) {
                    currentUsername = usernameString;
                    fireNewGame();
                } else {
                    addChild(badLogin);
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        });
        addChild(createButton);
    }


    private Text createBadLogin() {
        final int textSizeWidth = 142;
        final int textSizeHeight = 80;
        final int textFontSize = 20;
        final int textPositionX = 380;
        final int textPositionY = 520;
        badLogin = new Text("Account not found");
        badLogin.resize(textSizeWidth, textSizeHeight);
        badLogin.setFont(Font.font("Lucida Sans Unicode", FontPosture.REGULAR, textFontSize));
        badLogin.setTranslateX(textPositionX);
        badLogin.setTranslateY(textPositionY);
        return badLogin;
    }

    private Button createCreateButton() {
        final int buttonSizeWidth = 142;
        final int buttonSizeHeight = 80;
        final int buttonPositionX = 319;
        final int buttonPositionY = 560;
        Button createButton = new Button("Create Account");
        createButton.setPrefSize(buttonSizeWidth, buttonSizeHeight);
        createButton.setTranslateX(buttonPositionX);
        createButton.setTranslateY(buttonPositionY);
        return createButton;
    }

    private Button createLoginButton() {
        final int buttonSizeWidth = 142;
        final int buttonSizeHeight = 80;
        final int buttonPositionX = 490;
        final int buttonPositionY = 560;
        Button loginButton = new Button("Login");
        loginButton.setPrefSize(buttonSizeWidth, buttonSizeHeight);
        loginButton.setTranslateX(buttonPositionX);
        loginButton.setTranslateY(buttonPositionY);
        return loginButton;
    }

    private TextField createUsername() {
        final int textFieldWidth = 204;
        final int textFieldHeight = 39;
        final int textFieldPositionX = 368;
        final int textFieldPositionY = 270;
        username = new TextField();
        username.setPromptText("Username");
        username.setTranslateX(textFieldPositionX);
        username.setTranslateY(textFieldPositionY);
        username.setPrefWidth(textFieldWidth);
        username.setPrefHeight(textFieldHeight);
        return username;
    }

    private TextField createPassword() {
        final int textFieldWidth = 204;
        final int textFieldHeight = 39;
        final int textFieldPositionX = 368;
        final int textFieldPositionY = 430;
        password = new TextField();
        password.setPromptText("Password");
        password.setTranslateX(textFieldPositionX);
        password.setTranslateY(textFieldPositionY);
        password.setPrefWidth(textFieldWidth);
        password.setPrefHeight(textFieldHeight);
        return password;
    }

    @Override
    protected final Button createActionButton(@NotNull final StringBinding stringBinding,
                                              @NotNull final Runnable runnable) {
        return null;
    }

    @Override
    protected final Button createActionButton(@NotNull final String s, @NotNull final Runnable runnable) {
        return null;
    }

    @Override
    protected final Node createBackground(final double w, final double h) {
        return FXGL.texture("background/menu.png");
    }

    @Override
    protected final Node createProfileView(@NotNull final String s) {
        return null;
    }

    @Override
    protected final Node createTitleView(@NotNull final String s) {
        return new Rectangle();
    }

    @Override
    protected final Node createVersionView(@NotNull final String s) {
        return new Rectangle();
    }
}
