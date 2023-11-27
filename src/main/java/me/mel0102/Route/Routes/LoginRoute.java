package me.mel0102.Route.Routes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import me.mel0102.AppController;
import me.mel0102.Model.Models.User.User;
import me.mel0102.Model.Models.User.UserService;
import me.mel0102.Route.Route;
import me.mel0102.Route.RouteController;
import me.mel0102.Route.RouteType;

import java.util.Optional;

public class LoginRoute extends Route {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label wrongCredentials;

    @FXML
    private Button login;

    private String email;

    private String password;

    @FXML
    public void initialize() {
        emailField.setOnAction(e -> email = emailField.getText());
        passwordField.setOnAction(e -> password = passwordField.getText());


        UserService userService = appController.getModelRegistry().getUserController();

        login.setOnAction(e -> {
            Optional<User> userToLogIn = userService.loginUser(emailField.getText(), passwordField.getText());

            if (userToLogIn.isPresent()) {
                wrongCredentials.setVisible(false);
                appController.setLoggedInUser(userToLogIn);
                appController.to(RouteType.SUMMARY);
            } else {
                wrongCredentials.setVisible(true);
            }
        });
    }

    public LoginRoute(AppController appController) {
        super(appController, RouteType.LOGIN, "/login.fxml");
    }
}