package com.example.spacetrader.entity;
import java.io.Serializable;

public class Ship implements Serializable{

    private ResourceType[] cargo;

    private ShipType type;

    public Ship(ShipType type) {
        this.type = type;
        this.cargo = new ResourceType[type.getCargoSize()];
    }

    public String toString() {
        return type.toString();
    }


}
