package me.mel0102;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import me.mel0102.Model.ModelRegistry;
import me.mel0102.Route.RouteController;
import me.mel0102.Route.RouteType;

public class AppController implements Controller {
    private RouteController routeController;

    private ModelRegistry modelRegistry;

    AppController(Stage primaryStage) {
        this.routeController = new RouteController();
        this.routeController.loadStage(primaryStage);

        this.modelRegistry = new ModelRegistry();

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
}
