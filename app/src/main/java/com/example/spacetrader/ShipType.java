package com.example.spacetrader;

public enum ShipType {
    GNAT("Gnat");

    private String name;

    ShipType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
