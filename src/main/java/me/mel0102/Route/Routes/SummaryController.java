package me.mel0102.Route.Routes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import me.mel0102.Route.RouteController;
import me.mel0102.Route.RouteType;

public class SummaryController {

    private RouteController routeController;

    @FXML
    private Button logout;

    @FXML
    public void initialize() {
        System.out.println("Init summary cont");
        logout.setOnAction((e) -> routeController.switchTo(RouteType.LOGIN));
    }

    public SummaryController() {}

}
