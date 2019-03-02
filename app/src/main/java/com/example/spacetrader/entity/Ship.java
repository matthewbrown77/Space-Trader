package com.example.spacetrader.entity;
import android.util.Log;

import java.io.Serializable;

public class Ship implements Serializable{

    private Resource[] cargo;

    private ShipType type;

    public Ship(ShipType type) {
        this.type = type;
        this.cargo = new Resource[type.getCargoSize()];
    }

    public Resource[] getCargo() {
        return cargo;
    }

    public void addCargo(Resource resource) {
        Log.e("main", "Will add cargo");
    }

    public String toString() {
        return type.toString();
    }


}
