package com.example.spacetrader.entity;

/**
 * The shipType enum is an attribute of a ship. Each shipType has a different name,
 * and argoSize
 */
public enum ShipType {
    GNAT("Gnat", 15);

    private String name;
    private int cargoSize;

    ShipType(String name, int cargoSize) {
        this.name = name;
        this.cargoSize = cargoSize;
    }

    /**
     * Gets the max cargo size of the ship
     * @return int max cargo Size
     */
    public int getCargoSize() {
        return this.cargoSize;
    }

    public String toString() {
        return name;
    }
}
