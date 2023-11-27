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
import me.mel0102.Route.RouteType;

import java.util.Optional;

public class RegisterRoute extends Route {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label emailOccupied;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private Button register;

    @FXML
    public void initialize() {
        UserService userService = appController.getModelRegistry().getUserController();

        register.setOnAction(e -> {

            Optional<User> existingUser = userService.find(emailField.getText());

            if (existingUser.isPresent()) {
                emailOccupied.setVisible(true);
                return;
            }

            boolean result = userService.add(
                    new User(userService.newPrimaryKey(),
                            emailField.getText(),
                            passwordField.getText(),
                            nameField.getText(),
                            surnameField.getText()
                    )
            );

            if (!result) {
                //failed to register
                return;
            }

            emailOccupied.setVisible(false);

            Optional<User> userToLogIn = userService.loginUser(emailField.getText(), passwordField.getText());
            appController.setLoggedInUser(userToLogIn);
            appController.to(RouteType.SUMMARY);
        });
    }

    public RegisterRoute(AppController appController) {
        super(appController, RouteType.REGISTER, "/register.fxml");
    }
}