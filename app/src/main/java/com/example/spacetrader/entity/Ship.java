package com.example.spacetrader.entity;

public class Ship {

    private int cargo;

    private ShipType type;

    public Ship(ShipType type) {
        this.type = type;
    }

    public String toString() {
        return type.toString();
    }


}
