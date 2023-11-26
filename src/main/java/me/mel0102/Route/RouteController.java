package me.mel0102.Route;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import me.mel0102.Controller;
import me.mel0102.Route.Routes.LoginController;
import me.mel0102.Route.Routes.SummaryController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class RouteController implements Controller {
    private Stage primaryStage;

    private Scene currentScene;

    @FXML
    private VBox main;

    @FXML
    private VBox login;

    @FXML
    private LoginController loginController;


    @FXML
    private VBox summary;

    @FXML
    private SummaryController summaryController;

    private HashMap<RouteType, String> routes = new HashMap<>();

    public RouteController() {
        routes.put(RouteType.LOGIN, "/login.fxml");
        routes.put(RouteType.SUMMARY, "/summary.fxml");
    }

    public void loadStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Fly.to");
    }

    @FXML
    public void initialize() {
        System.out.println("Init");

    }

    @Override
    public void register() {
        switchTo(RouteType.SUMMARY);
    }

    public void load(String file) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(file));
        try {
            Parent view = loader.load();
            Scene scene = new Scene(view);

            this.primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void unregister() {
        primaryStage.close();
    }

    public void switchTo(RouteType type) {
        load(routes.get(type));
    }


}
