package me.mel0102.Model.Models.User;

import me.mel0102.Model.Model;
import me.mel0102.Model.Models.Flight.Flight;

import java.util.ArrayList;
import java.util.List;

public class User extends Model {
    private int id;
    private String email;

    private String password;
    private String name;
    private String surname;
    private List<Flight> flights;

    public User(int id, String email, String password, String name, String surname, List<Flight> flights) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.flights = flights;
    }

    public User(int id, String email, String password, String name, String surname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.flights = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    @Override
    public String toRow() {
        return String.format("%d,%s,%s,%s,%s", id, email, password, name, surname);
    }
}
