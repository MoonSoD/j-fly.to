package me.mel0102.Model;

import me.mel0102.Model.Models.Flight.FlightService;
import me.mel0102.Model.Models.FlightUser.FlightUserService;
import me.mel0102.Model.Models.User.UserService;

public class ModelRegistry {
    private FlightService flightController;
    private UserService userController;
    private FlightUserService flightUserController;

    public ModelRegistry() {
        this.flightController = new FlightService();
        this.userController = new UserService();
        this.flightUserController = new FlightUserService(flightController, userController);
    }

    public void load() {
        flightController.load();
        userController.load();
        flightUserController.load();
    }

    public void save() {
        flightController.save();
        userController.save();
        flightUserController.save();
    }

    public FlightService getFlightController() {
        return flightController;
    }

    public UserService getUserController() {
        return userController;
    }

    public FlightUserService getFlightUserController() {
        return flightUserController;
    }
}
