package me.mel0102.Route.Routes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import me.mel0102.Route.Route;
import me.mel0102.Route.RouteType;

public class SummaryRoute extends Route {

    public SummaryRoute() {
        super(RouteType.SUMMARY, "/summary.fxml");
    }
}
