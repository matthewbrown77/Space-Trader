package com.example.spacetrader.entity;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * The ship class represents a ship that the player uses to travel from
 * solar system to solar system. Every ship has a cargo hold that holds
 * resources. Each ship also has a ShipType that specifies its name and
 * how much cargo it can hold.
 */
public class Ship implements Serializable {

    private int cargoAmount;
    private HashMap<Resource, Integer> cargoHold;
    private ShipType type;
    private int health; //health of ship (0,100]
    private int fuel;//coordinate distance can travel

    /**
     * Creates a ship of the specified type.
     * @param type of ship
     */
    public Ship(ShipType type) {
        this.type = type;
        this.cargoHold = new HashMap<>();
        this.cargoAmount = 0;
        this.health = 100;
        this.fuel = type.getFuelCapacity();
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
     * Clears all the cargo from the cargoHold.
     */
    public void clearCargo() {
        cargoAmount = 0;
        cargoHold = new HashMap<>();
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
     * Gets the current amount of fuel on the ship
     * @return int fuel on ship
     */
    public int getFuel() {
        return fuel;
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

    /**
     * Gets the ship's health (0, 100]
     * @return ship health
     */
    public int getShipHealth() {
        return health;
    }

    /**
     * Deducts the amount from the ship health
     * @param amount
     * @return
     */
    public void deductShipHealth(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
    }

    /**
     * Deducts the fuel from the ship
     * @param f amount of fuel to deduct
     * @return true if the action is successful, false otherwise
     */
    public boolean deductFuel(int f) {
        if (fuel >= f) {
            fuel -= f;
            return true;
        } else {
            Log.e("main", "Ship Class: Failed to deduct fuel because player" +
                    " does not have enough fuel");
            return false;
        }
    }

    /**
     * Adds 1 fuel point to the ship
     * @return true if successful
     */
    public boolean incrementFuel() {
        if (fuel < type.getFuelCapacity()) {
            fuel++;
            return true;
        } else {
            Log.e("main", "Ship Class: Failed to add fuel because fuel is already full");
            return false;
        }
    }

    @Override
    public String toString() {
        return type.toString();
    }

}
