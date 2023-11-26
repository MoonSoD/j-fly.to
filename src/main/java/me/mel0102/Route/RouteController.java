package me.mel0102.Route;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import me.mel0102.Controller;
import me.mel0102.Route.Routes.LoginRoute;
import me.mel0102.Route.Routes.SummaryRoute;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RouteController implements Controller {
    private Stage primaryStage;

    private RouteType currentRoute;

    private List<Route> routes;

    @FXML
    private Button logout;

    @FXML
    public void initialize() {
        System.out.println("init!");
        logout.setOnAction(e -> System.out.println("btn click"));
    }

    public RouteController() {}

    public void loadStage(Stage primaryStage) {
        this.primaryStage = primaryStage;

        this.routes = Arrays.asList(
                new LoginRoute(),
                new SummaryRoute()
        );
    }

    @Override
    public void register() {
        primaryStage.setTitle("Fly.to");

        routes.forEach(Route::load);


        switchTo(RouteType.SUMMARY);
        primaryStage.show();
    }

    @Override
    public void unregister() {
        primaryStage.close();
    }

    Optional<Route> getRouteByType(RouteType type) {
        return routes
                .stream()
                .filter(route -> route.getType() == type)
                .findFirst();
    }

    public void switchTo(RouteType type) {
        Optional<Route> route = getRouteByType(type);

        if (route.isEmpty()) {
            System.out.println("Route not found!");
            return;
        }

        currentRoute = route.get().getType();
        this.primaryStage.setScene(route.get().getScene());
    }

    public RouteType getCurrentRoute() {
        return currentRoute;
    }
}
