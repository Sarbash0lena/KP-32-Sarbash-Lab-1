package com.example.model;

public class Booking {
    private final Visitor visitor;
    private final Room room;
    private final int nights;

    public Booking(Visitor visitor, Room room, int nights) {
        this.visitor = visitor;
        this.room = room;
        this.nights = nights;
        this.room.setAvailable(false);
    }

    public double getTotalCost() {
        return room.getPricePerNight() * nights;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public Room getRoom() {
        return room;
    }

    public int getNights() {
        return nights;
    }
}
