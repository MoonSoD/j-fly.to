package me.mel0102.Route;

import javafx.stage.Stage;
import me.mel0102.AppController;
import me.mel0102.Controller;
import me.mel0102.Route.Routes.LoginRoute;
import me.mel0102.Route.Routes.RegisterRoute;
import me.mel0102.Route.Routes.SummaryRoute;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RouteController implements Controller {
    private Stage primaryStage;

    private RouteType currentRoute;

    private List<Route> routes;

    private final AppController appController;

    public RouteController(AppController appController) {
        this.appController = appController;
    }

    public void loadStage(Stage primaryStage) {
        this.primaryStage = primaryStage;

        this.routes = Arrays.asList(
                new LoginRoute(appController),
                new SummaryRoute(appController),
                new RegisterRoute(appController)
        );
    }

    @Override
    public void register() {
        primaryStage.setTitle("Fly.to");

        routes.forEach(Route::load);

        switchTo(RouteType.REGISTER);
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