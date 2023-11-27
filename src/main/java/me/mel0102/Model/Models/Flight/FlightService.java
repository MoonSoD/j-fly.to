package me.mel0102.Model.Models.Flight;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.mel0102.Model.ModelService;
import me.mel0102.Storage.StorageUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FlightService implements ModelService {
    ObservableList<Flight> flights = FXCollections.observableArrayList();

    public Optional<Flight> find(int id) {
        return flights.stream().filter(fl -> fl.getId() == id).findFirst();
    }

    public void add(Flight flight) {
        boolean exists = flights.stream().anyMatch(f -> f.getId() == flight.getId());

        if (exists) {
            return;
        }

        flights.add(flight);
    }

    public void remove(Flight flight) {
        remove(flight.getId());
    }

    public void remove(int id) {
        flights.removeIf(flight -> flight.getId() == id);
    }

    public ObservableList<Flight> getAll() {
        return FXCollections.unmodifiableObservableList(flights);
    }

    @Override
    public String getTableName() {
        return "flights";
    }

    @Override
    public void load() {
        List<String> rows = StorageUtil.readTable(getTableName());

        rows.forEach(row -> {
            String[] parts = row.split(",");
            int id = Integer.parseInt(parts[0]);
            String from = parts[1];
            String to = parts[2];
            LocalDateTime departure = LocalDateTime.parse(parts[3]);

            flights.add(new Flight(id, from, to, departure));
        });
    }

    @Override
    public void save() {
        List<String> rows = flights.stream().map(Flight::toRow).toList();

        StorageUtil.saveToTable(rows, getTableName());
    }

}
