package com.example.spacetrader.entity;
import android.util.Log;

import java.io.Serializable;

public class Ship implements Serializable{

    private Resource[] cargo;
    private int cargoLoad;

    private ShipType type;

    public Ship(ShipType type) {
        this.type = type;
        this.cargo = new Resource[type.getCargoSize()];
        this.cargoLoad = 0;
    }

    public int getCargoCount(Resource resource) {
        int count = 0;
        for (int i = 0; i < cargo.length; i++) {
            if (cargo[i] != null && cargo[i].equals(resource)) {
                count++;
            }
        }
        return count;
    }

    public boolean addCargo(Resource resource) {
        for (int i = 0; i < cargo.length; i++) {
            if (cargo[i] == null) {
                cargo[i] = resource;
                Log.e("main", "Will add cargo");
                cargoLoad++;
                return true;
            }
        }
        Log.e("main", "Cargo is full");
        return false;
    }

    public boolean removeCargo(Resource resource) {
        for (int i = 0; i < cargo.length; i++) {
            if (cargo[i] != null && cargo[i].equals(resource)) {
                Log.e("main", "Will remove cargo");
                cargo[i] = null;
                cargoLoad--;
                return true;
            }
        }
        Log.e("main", "Item does not exist in cargo hold");
        return false;
    }

    public int getMaxCargo() {
        return type.getCargoSize();
    }

    public int getCurrentCargo() {
        return cargoLoad;
    }

    public String toString() {
        return type.toString();
    }


}
