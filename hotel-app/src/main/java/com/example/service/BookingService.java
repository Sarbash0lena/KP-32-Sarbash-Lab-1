package com.example.service;

import com.example.model.Booking;
import com.example.model.Room;
import com.example.model.Visitor;

public class BookingService {
    public Booking bookRoom(Visitor visitor, Room room, int nights) {
        if (!room.isAvailable()) {
            throw new IllegalStateException("Номер уже зайнятий!");
        }
        return new Booking(visitor, room, nights);
    }
}
