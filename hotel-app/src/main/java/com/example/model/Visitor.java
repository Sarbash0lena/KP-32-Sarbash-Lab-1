package com.example.model;

public class Visitor {
    private final String name;
    private final String passport;

    public Visitor(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    @Override
    public String toString() {
        return name + " (паспорт: " + passport + ")";
    }
}
