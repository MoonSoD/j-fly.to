package me.mel0102.Route.Routes;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.mel0102.AppController;
import me.mel0102.Model.Models.Flight.Flight;
import me.mel0102.Model.Models.Flight.FlightService;
import me.mel0102.Route.Route;
import me.mel0102.Route.RouteController;
import me.mel0102.Route.RouteType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SummaryRoute extends Route {

    @FXML
    private Button logout;

    @FXML
    private TableView<Flight> flightTable;

    @FXML
    private TableColumn<Flight, String> from;

    @FXML
    private TableColumn<Flight, String> to;

    @FXML
    private TableColumn<Flight, String> departure;

    private ObservableList<Flight> flights;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm");

    @FXML
    public void initialize() {
        logout.setOnAction(e -> {
            appController.logoutUser();
            appController.to(RouteType.LOGIN);
        });

        from.setCellValueFactory(new PropertyValueFactory<Flight, String>("from"));
        to.setCellValueFactory(new PropertyValueFactory<Flight, String>("to"));
        departure.setCellValueFactory(foo ->
                new SimpleStringProperty(foo.getValue().getDeparture().format(formatter))
        );
        flightTable.setItems(flights);


        System.out.println("Loaded summary");
    }

    public SummaryRoute(AppController appController) {
        super(appController, RouteType.SUMMARY, "/summary.fxml");

        FlightService flightService = appController.getModelRegistry().getFlightController();
        this.flights = flightService.getAll();
    }
}