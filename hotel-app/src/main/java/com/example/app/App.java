package com.example.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.io.FileManager;
import com.example.model.Booking;
import com.example.model.Hotel;
import com.example.model.Room;
import com.example.model.Visitor;
import com.example.service.BookingService;

public class App 
{
    private static final Scanner scanner = new Scanner(System.in);
    private static final Hotel hotel = new Hotel("Dream Hotel");
    private static final BookingService bookingService = new BookingService();
    private static final FileManager fileManager = new FileManager();
    private static final List<Visitor> visitors = new ArrayList<>();
    private static final List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        // try {
        //     System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        // } catch (java.io.UnsupportedEncodingException e) {
        //     System.err.println("UTF-8 не підтримується: " + e.getMessage());
        // }

        int choice;
        do {
            printMenu();
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> addRoom();
                case 2 -> addVisitor();
                case 3 -> bookRoom();
                case 4 -> showStatistics();
                case 5 -> exportRooms();
                case 6 -> importRooms();
                case 7 -> listRooms();
                case 0 -> System.out.println("Exit...");
                default -> System.out.println("Wrong option!");
            }
        } while (choice != 0);
    }

    private static void printMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add a room");
        System.out.println("2. Add a visitor");
        System.out.println("3. Book a room");
        System.out.println("4. Show booking statistics");
        System.out.println("5. Export rooms");
        System.out.println("6. Import rooms");
        System.out.println("7. List rooms");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addRoom() {
        System.out.print("Enter room number: ");
        int number = Integer.parseInt(scanner.nextLine());
        System.out.print("Cost per night: ");
        double price = Double.parseDouble(scanner.nextLine());
        Room room = new Room(number, price);
        hotel.addRoom(room);
        System.out.println("Number added.");
    }

    private static void addVisitor() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Passport: ");
        String passport = scanner.nextLine();
        Visitor visitor = new Visitor(name, passport);
        visitors.add(visitor);
        System.out.println("Visitor added.");
    }

    private static void bookRoom() {
        if (visitors.isEmpty()) {
            System.out.println("There are no registered visitors.");
            return;
        }
        System.out.println("Select a visitor:");
        for (int i = 0; i < visitors.size(); i++) {
            System.out.println((i + 1) + ". " + visitors.get(i));
        }
        int visitorIndex = Integer.parseInt(scanner.nextLine()) - 1;
        Visitor visitor = visitors.get(visitorIndex);

        List<Room> availableRooms = hotel.getAvailableRooms();
        if (availableRooms.isEmpty()) {
            System.out.println("There are no rooms available.");
            return;
        }
        System.out.println("Available numbers:");
        for (int i = 0; i < availableRooms.size(); i++) {
            System.out.println((i + 1) + ". " + availableRooms.get(i));
        }
        int roomIndex = Integer.parseInt(scanner.nextLine()) - 1;
        Room room = availableRooms.get(roomIndex);

        System.out.print("Number of nights:");
        int nights = Integer.parseInt(scanner.nextLine());

        Booking booking = bookingService.bookRoom(visitor, room, nights);
        bookings.add(booking);
        System.out.println("Booking successful. Amount: " + booking.getTotalCost());
    }

    private static void showStatistics() {
        long booked = hotel.getRooms().stream().filter(r -> !r.isAvailable()).count();
        long available = hotel.getAvailableRooms().size();
        System.out.println("Rooms booked: " + booked);
        System.out.println("Available rooms: " + available);
    }

    private static void exportRooms() {
        try {
            List<Room> sortedRooms = hotel.getRooms().stream()
                .sorted((r1, r2) -> Double.compare(r1.getPricePerNight(), r2.getPricePerNight()))
                .toList();
            fileManager.exportRooms(sortedRooms, "rooms.json");
            System.out.println("Експорт завершено у файл rooms.json");
        } catch (IOException e) {
            System.out.println("Помилка експорту: " + e.getMessage());
        }
    }
    
    private static void importRooms() {
        try {
            List<Room> imported = fileManager.importRooms("rooms.json");
            for (Room r : imported) hotel.addRoom(r);
            System.out.println("Imported " + imported.size() + " rooms.");
        } catch (IOException e) {
            System.out.println("Import error: " + e.getMessage());
        }
    }

    private static void listRooms() {
        if (hotel.getRooms().isEmpty()) {
            System.out.println("There is no room.");
        } else {
            hotel.getRooms().forEach(System.out::println);
        }
    }
}

