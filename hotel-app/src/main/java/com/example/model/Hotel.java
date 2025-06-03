package com.example.model;

import java.util.*;

public class Hotel {
    private final String name;
    private final List<Room> rooms = new ArrayList<>();

    public Hotel(String name) {
        this.name = name;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Room> getAvailableRooms() {
        return rooms.stream().filter(Room::isAvailable).toList();
    }
}
