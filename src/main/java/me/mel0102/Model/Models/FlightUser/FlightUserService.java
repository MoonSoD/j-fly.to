package me.mel0102.Model.Models.FlightUser;

import me.mel0102.Model.ModelService;
import me.mel0102.Model.Models.Flight.Flight;
import me.mel0102.Model.Models.Flight.FlightService;
import me.mel0102.Model.Models.User.User;
import me.mel0102.Model.Models.User.UserService;
import me.mel0102.Storage.StorageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightUserService implements ModelService {
    private FlightService flightController;
    private UserService userController;

    public FlightUserService(FlightService flightController, UserService userController) {
        this.flightController = flightController;
        this.userController = userController;
    }

    @Override
    public void load() {
        List<String> rows = StorageUtil.readTable(getTableName());

        rows.forEach(row -> {
            String[] parts = row.split(",");
            int userId = Integer.parseInt(parts[0]);
            int flightId = Integer.parseInt(parts[1]);

            Optional<User> user = userController.find(userId);
            Optional<Flight> flight = flightController.find(flightId);

            if (user.isEmpty() || flight.isEmpty()) {
                return;
            }

            user.get().getFlights().add(flight.get());
        });
    }

    @Override
    public void save() {
        List<FlightUser> flightUsers = new ArrayList<>();

        userController.getUsers().forEach(u -> {
            u.getFlights().forEach(f -> {
                flightUsers.add(new FlightUser(u, f));
            });
        });

        List<String> rows = flightUsers.stream().map(FlightUser::toRow).toList();

        StorageUtil.saveToTable(rows, getTableName());
    }

    @Override
    public String getTableName() {
        return "flight_user";
    }

}
