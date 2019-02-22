package com.example.spacetrader.entity;
import java.io.Serializable;

public class Ship implements Serializable{

    private int cargo;

    private ShipType type;

    public Ship(ShipType type) {
        this.type = type;
    }

    public String toString() {
        return type.toString();
    }


}
