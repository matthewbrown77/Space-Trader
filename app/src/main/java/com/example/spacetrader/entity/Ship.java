package com.example.spacetrader.entity;
import android.util.Log;
import java.util.HashMap;

import java.io.Serializable;

/**
 * The ship class represents a ship that the player uses to travel from
 * solar system to solar system. Every ship has a cargo hold that holds
 * resources. Each ship also has a ShipType that specifies its name and
 * how much cargo it can hold.
 */
public class Ship implements Serializable{

    private int cargoAmount;
    private HashMap<Resource, Integer> cargoHold;
    private ShipType type;

    /**
     * Creates a ship of the specified type.
     * @param type of ship
     */
    public Ship(ShipType type) {
        this.type = type;
        this.cargoHold = new HashMap<>();
        this.cargoAmount = 0;
    }

    /**
     * Gets the amount of the specified resource currently in the ship's cargo hold
     * @param resource
     * @return amount of that resource in the ship's cargo hold
     */
    public int getCargoCount(Resource resource) {
        if (!cargoHold.containsKey(resource)) {
            return 0;
        } else {
            return cargoHold.get(resource);
        }
    }

    /**
     * Adds the specified resource to the cargo load if there is space.
     * @param resource to be added.
     * @return true if the operation is successful, false otherwise.
     */
    public boolean addCargo(Resource resource) {
        if (cargoAmount >= type.getCargoSize()) {
            Log.e("main", "Ship Class: Failed to add " + resource + " since cargo hold is full");
            return false;
        }
        if (cargoHold.containsKey(resource)) {
            cargoHold.put(resource, cargoHold.get(resource) + 1);
        } else {
            cargoHold.put(resource, 1);
        }
        cargoAmount++;
        return true;
    }

    /**
     * Removes the specified resource from the cargo load if it exists.
     * @param resource to be removed.
     * @return true if the operation is successful, false otherwise.
     */
    public boolean removeCargo(Resource resource) {
        if (!cargoHold.containsKey(resource)) {
            Log.e("main", "Ship Class: Failed to remove " + resource
                    + " since it does not exist in the cargo hold");
            return false;
        }
        if (cargoHold.get(resource) > 1) {
            cargoHold.put(resource, cargoHold.get(resource) - 1);
        } else {
            cargoHold.remove(resource);
        }
        cargoAmount--;
        return true;

    }

    /**
     * Gets the max amount of cargo the ship can hold
     * @return max amount of resources that the ship can hold
     */
    public int getMaxCargo() {
        return type.getCargoSize();
    }

    /**
     * Gets current cargo amount of the ship
     * @return the amount of resources contained on the ship
     */
    public int getCurrentCargo() {
        return cargoAmount;
    }

    public String toString() {
        return type.toString();
    }


}
