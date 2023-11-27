package me.mel0102.Route;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import me.mel0102.AppController;

import java.io.IOException;

public abstract class Route {
    protected AppController appController;
    private RouteType type;
    private String path;
    private Scene scene;

    public Route(AppController appController, RouteType type, String path) {
        this.appController = appController;
        this.type = type;
        this.path = path;
        load();
    }

    public void load() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(this.getPath()));
        loader.setController(this);

        try {
            Parent view = loader.load();

            this.scene = new Scene(view);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Scene getScene() {
        return this.scene;
    }

    public RouteType getType() {
        return type;
    }

    public String getPath() {
        return path;
    }
}