package com.example.model;

import java.util.Objects;

public class Room {

    private int number;
    private double pricePerNight;
    private boolean available = true;

    // Порожній конструктор обов’язковий для Jackson
    public Room() {
    }

    public Room(int number, double pricePerNight) {
        this.number = number;
        this.pricePerNight = pricePerNight;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Room room)) {
            return false;
        }
        return number == room.number
                && Double.compare(room.pricePerNight, pricePerNight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, pricePerNight);
    }

    @Override
    public String toString() {
        return "Кімната " + number + ", ₴" + pricePerNight + ", " + (available ? "доступна" : "заброньована");
    }
}
