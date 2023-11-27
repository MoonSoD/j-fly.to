package me.mel0102;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import me.mel0102.Model.ModelRegistry;
import me.mel0102.Model.Models.User.User;
import me.mel0102.Route.RouteController;
import me.mel0102.Route.RouteType;

import java.util.Optional;

public class AppController implements Controller {
    private RouteController routeController;

    private ModelRegistry modelRegistry;

    private Optional<User> loggedInUser;

    AppController(Stage primaryStage) {
        this.modelRegistry = new ModelRegistry();

        this.routeController = new RouteController(this);
        routeController.loadStage(primaryStage);

        primaryStage.setOnCloseRequest((WindowEvent e) -> unregister());
    }

    public void to(RouteType type) {
        this.routeController.switchTo(type);
    }

    @Override
    public void register() {
        this.routeController.register();
        this.modelRegistry.load();
    }

    @Override
    public void unregister() {
        this.routeController.unregister();
        this.modelRegistry.save();
    }

    public ModelRegistry getModelRegistry() {
        return modelRegistry;
    }

    public RouteController getRouteController() {
        return routeController;
    }

    public Optional<User> getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Optional<User> loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void logoutUser() {
        this.loggedInUser = Optional.empty();
    }
}
