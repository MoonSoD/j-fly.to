package me.mel0102.Model.Models.Flight;

import me.mel0102.Model.Model;

import java.time.LocalDateTime;

public class Flight extends Model {
    private int id;
    private String from;
    private String to;
    private LocalDateTime departure;

    public Flight(int id, String from, String to, LocalDateTime departure) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.departure = departure;
    }

    public int getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    @Override
    public String toRow() {
        return String.format("%d,%s,%s,%s", id, from, to, departure.toString());
    }
}
