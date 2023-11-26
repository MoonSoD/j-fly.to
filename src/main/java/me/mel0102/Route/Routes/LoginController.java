package me.mel0102.Route.Routes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginController {

    @FXML
    private Button login;

    @FXML
    public void initialize() {
        System.out.println("Init login cont");
        login.setOnAction((e) -> System.out.println("Logging in"));
    }

    public LoginController() {}

}
