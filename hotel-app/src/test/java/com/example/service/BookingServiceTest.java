package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.model.Booking;
import com.example.model.Room;
import com.example.model.Visitor;

public class BookingServiceTest {

    private BookingService bookingService;
    private Visitor visitor;
    private Room room;

    @BeforeEach
    public void setUp() {
        bookingService = new BookingService();
        visitor = new Visitor("Анна", "AA123456");
        room = new Room(101, 500.0);
    }

    @Test
    public void testBookRoomMarksRoomUnavailable() {
        assertTrue(room.isAvailable());
        bookingService.bookRoom(visitor, room, 3);
        assertFalse(room.isAvailable());
    }

    @Test
    public void testBookRoomReturnsCorrectBooking() {
        Booking booking = bookingService.bookRoom(visitor, room, 2);
        assertEquals(visitor, booking.getVisitor());
        assertEquals(room, booking.getRoom());
        assertEquals(2, booking.getNights());
        assertEquals(1000.0, booking.getTotalCost());
    }

    @Test
    public void testRoomTotalCostCalculation() {
        Booking booking = bookingService.bookRoom(visitor, room, 4);
        assertEquals(2000.0, booking.getTotalCost());
    }

    @Test
    void cannotBookAlreadyBookedRoom() {
        Room room = new Room(404, 300.0);
        Visitor firstVisitor = new Visitor("Іван", "AA123456");
        Visitor secondVisitor = new Visitor("Марія", "BB654321");

        BookingService service = new BookingService();
        Booking firstBooking = service.bookRoom(firstVisitor, room, 2);

        assertThrows(IllegalStateException.class, () -> {
            service.bookRoom(secondVisitor, room, 1);
        });
    }

    @Test
    void calculatesCorrectTotalCostWithFractionalPrice() {
        Room room = new Room(505, 199.99);
        Visitor visitor = new Visitor("Леся", "CC789012");

        Booking booking = new Booking(visitor, room, 3);
        double expected = 199.99 * 3;
        assertEquals(expected, booking.getTotalCost(), 0.001);
    }

}
