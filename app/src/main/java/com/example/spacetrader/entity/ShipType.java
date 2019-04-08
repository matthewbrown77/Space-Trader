package com.example.spacetrader.entity;

import java.io.Serializable;

/**
 * The ShipType enum is an attribute of a ship. Each shipType has a different name,
 * and cargoSize
 */
public enum ShipType {
    FLEA("Flea", 5, 20),
    GNAT("Gnat", 15, 20),
    FIREFLY("Firefly", 20, 17),
    MOSQUITO("Mosquito", 20, 13),
    BUMBLEBEE("Bumblebee", 20, 15),
    BEETLE("Beetle", 50, 14),
    HORNET("Gnat", 20, 16),
    GRASSHOPPER("Grasshopper", 30, 15),
    TERMITE("Termite", 60, 13),
    WASP("Wasp", 35, 14);

    private final String name;
    private final int cargoSize;
    private final int fuelCapacity;

    /**
     * ShipType constructor
     * @param name ship's name
     * @param cargoSize ship's max cargo size
     * @param fuelCapacity ship's fuel capacity
     */
    ShipType(String name, int cargoSize, int fuelCapacity) {
        this.name = name;
        this.cargoSize = cargoSize;
        this.fuelCapacity = fuelCapacity;
    }

    /**
     * Gets the max cargo size of the ship
     * @return int max cargo Size
     */
    public int getCargoSize() {
        return this.cargoSize;
    }

    /**
     * Gets the fuel capacity of the ship
     * @return int fuelCapacity
     */
    public int getFuelCapacity() {
        return fuelCapacity;
    }

    /**
     * Gets the price for the ship.
     * @return price of the ship
     */
    public int getPrice() {
        return (500 * cargoSize) + (200 * fuelCapacity);
    }

    @Override
    public String toString() {
        return name;
    }

}
