package me.mel0102;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import me.mel0102.Model.ModelRegistry;
import me.mel0102.Model.Models.Flight.Flight;
import me.mel0102.Model.Models.User.User;

import java.time.LocalDateTime;


public class Main extends Application {

    private AppController appController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        appController = new AppController(primaryStage);
        appController.register();

    }

    private void exitProgram(WindowEvent e) {
        //modelRegistry.save();
        System.exit(0);
    }
}