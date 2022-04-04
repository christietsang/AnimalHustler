package ca.bcit.comp2522.termproject.christiebelal;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

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
    private TextField badLogin;

    public MainMenu(MenuType type) {
        super(MenuType.MAIN_MENU);
        username = new TextField();
        username.setPromptText("Username");
        username.setTranslateX(368);
        username.setTranslateY(270);
        username.setPrefWidth(204);
        username.setPrefHeight(39);

        password = new TextField();
        password.setPromptText("Password");
        password.setTranslateX(368);
        password.setTranslateY(430);
        password.setPrefWidth(204);
        password.setPrefHeight(39);
        addChild(username);
        addChild(password);

        Button loginButton = new Button("Login");
        loginButton.setPrefSize(142, 80);
        loginButton.setTranslateX(490);
        loginButton.setTranslateY(560);

        Text badLogin = new Text("Account not found");
        badLogin.resize(142, 80);
        badLogin.setFont(Font.font("Lucida Sans Unicode", FontPosture.REGULAR, 20));
        badLogin.setTranslateX(380);
        badLogin.setTranslateY(520);

        // Login button
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String usernameString = username.getText();
                String passwordString = password.getText();
                removeChild(badLogin);
//                System.out.printf("Username: %s\nPassword: %s", usernameString, passwordString);
                try {
                    if (
                            DatabaseHandler.checkUserNamePassword(usernameString, passwordString)) {
                        fireNewGame();
                    } else {
                        addChild(badLogin);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        addChild(loginButton);

        Button createButton = new Button("Create Account");
        createButton.setPrefSize(142, 80);
        createButton.setTranslateX(319);
        createButton.setTranslateY(560);


        // Create account button
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String usernameString = username.getText();
                String passwordString = password.getText();
                removeChild(badLogin);
//                System.out.printf("Username: %s\nPassword: %s", usernameString, passwordString);
                try {
                    if (
                            DatabaseHandler.CreateUserName(usernameString, passwordString)) {
                        currentUsername = usernameString;
                        fireNewGame();
                    } else {
                        addChild(badLogin);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        addChild(createButton);
    }

    @Override
    protected Button createActionButton(StringBinding stringBinding, Runnable runnable) {
        return null;
    }

    @Override
    protected Button createActionButton(String s, Runnable runnable) {
        return null;
    }

    @Override
    protected Node createBackground(double w, double h) {
        return FXGL.texture("background/menu.png");
    }

    @Override
    protected Node createProfileView(String s) {
        return null;
    }

    @Override
    protected Node createTitleView(String s) {
        return new Rectangle();
    }

    @Override
    protected Node createVersionView(String s) {
        return new Rectangle();
    }
}
