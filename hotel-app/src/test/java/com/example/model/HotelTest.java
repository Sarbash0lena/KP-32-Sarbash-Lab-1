package com.example.model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class HotelTest {

    @Test
    public void testAddRoomIncreasesSize() {
        Hotel hotel = new Hotel("Test Hotel");
        Room room = new Room(1, 100);
        hotel.addRoom(room);
        assertEquals(1, hotel.getRooms().size());
    }

    @Test
    public void testGetAvailableRoomsOnlyReturnsAvailable() {
        Hotel hotel = new Hotel("Test Hotel");
        Room room1 = new Room(1, 100);
        Room room2 = new Room(2, 150);
        room2.setAvailable(false);
        hotel.addRoom(room1);
        hotel.addRoom(room2);

        List<Room> available = hotel.getAvailableRooms();
        assertEquals(1, available.size());
        assertTrue(available.contains(room1));
    }
}
