package com.example.spacetrader.entity;

public enum ShipType {
    GNAT("Gnat", 15);

    private String name;
    private int cargoSize;

    ShipType(String name, int cargoSize) {
        this.name = name;
        this.cargoSize = cargoSize;
    }

    public String toString() {
        return name;
    }

    public int getCargoSize() {
        return this.cargoSize;
    }
}
