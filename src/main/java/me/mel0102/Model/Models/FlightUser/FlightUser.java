package me.mel0102.Model.Models.FlightUser;

import me.mel0102.Model.Model;
import me.mel0102.Model.Models.Flight.Flight;
import me.mel0102.Model.Models.User.User;

public class FlightUser extends Model {
    private User user;
    private Flight flight;

    public FlightUser(User user, Flight flight) {
        this.user = user;
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public Flight getFlight() {
        return flight;
    }

    @Override
    public String toRow() {
        return String.format("%d,%d", user.getId(), flight.getId());
    }
}
