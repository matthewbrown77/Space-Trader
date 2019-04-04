package com.example.spacetrader.entity;

import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The Player class represents the player in the game. It contains information about
 * their name, skill points, ship, and methods to get its cargo.
 */
public class Player implements Serializable {

    public final int skillPoints = 16;

    private String name;
    private int pilotSkillPoints;
    private int fighterSkillPoints;
    private int traderSkillPoints;
    private int engineerSkillPoints;
    private int credits;
    private HashMap<Resource, Double> avgPrice;
    private Ship ship;

    /**
     * Constructor for a player. Sets skill points to 0, ship to GNAT, and credits to 1000
     */
    public Player() {
        this.pilotSkillPoints = 0;
        this.fighterSkillPoints = 0;
        this.traderSkillPoints = 0;
        this.engineerSkillPoints = 0;
        this.credits = 1000;
        this.avgPrice = new HashMap<>();
        this.ship = new Ship(ShipType.GNAT);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Player Encounter methods
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Decrements the credits by the specified amount
     * @param amount
     */
    public void decrementCredits(int amount) {
        credits -= amount;
        if (credits < 0) {
            Log.e("main", "Player Class: Failed to decrement player credits by "
                    + amount + " since" +
                    "player does not have that many credits. Setting credits to 0.");
            credits = 0;
        }
    }

    /**
     * Increments the credits by the specified amount
     * @param amount
     */
    public void incrementCredits(int amount) {
        credits+= amount;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Player Ship methods
    ///////////////////////////////////////////////////////////////////////////////////////


    /**
     * Gets the ship's health (0, 100]
     * @return ship health
     */
    public int getShipHealth() {
        return ship.getShipHealth();
    }

    /**
     * Deducts the amount from the ship health
     * @param amount
     */
    public void deductShipHealth(int amount) {
        ship.deductShipHealth(amount);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Player cargo methods
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Gets the amount of the specified resource currently on the ship
     * @param resource
     * @return int amount of resource
     */
    public int getCargoCount(Resource resource) {
        return ship.getCargoCount(resource);
    }

    /**
     * Method adds requested resource to cargo load, and updates credits if successful
     * @param resource to be added
     * @return true if the add was successful, false otherwise
     */
    public boolean addCargo(Resource resource, int price) {
        if (price > credits) {
            Log.e("main", "Player Class: Failed to add " + resource + " to cargo " +
                    "since player does not have adequate credits");
            return false;
        } else if (ship.addCargo(resource)){
            credits -= price;
            if (avgPrice.containsKey(resource)) {
                avgPrice.put(resource, (avgPrice.get(resource) * (ship.getCargoCount(resource) - 1)
                        + price)/ship.getCargoCount(resource));
            } else {
                avgPrice.put(resource, (double)price);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Clears all the cargo from the cargoHold.
     */
    public void clearCargo() {
        ship.clearCargo();
    }

    /**
     * Method removes requested resource from cargo load, and updates credits if successful
     * @param resource to be removed
     * @return true if the remove was successful, false otherwise
     */
    public boolean removeCargo(Resource resource, int price) {
        if (ship.removeCargo(resource)) {
            credits += price;
            if (ship.getCargoCount(resource) == 0) {
                avgPrice.remove(resource);
            }
            return true;
        }
        return false;
    }

    /**
     * Dumps the specified cargo from the ship's cargo. No credits are added to player.
     * @param resource
     */
    public void dumpCargo(Resource resource) {
        ship.removeCargo(resource);
        if (ship.getCargoCount(resource) == 0) {
            avgPrice.remove(resource);
        } else {
            avgPrice.put(resource, (avgPrice.get(resource) * (ship.getCargoCount(resource) + 1)
                    )/ship.getCargoCount(resource));
        }
    }

    /**
     * Gets the max amount of cargo that the ship can hold
     * @return int max amount of cargo
     */
    public int getMaxCargo() {
        return ship.getMaxCargo();
    }

    /**
     * Gets the current cargo amount
     * @return
     */
    public int getCurrentCargo() {
        return ship.getCurrentCargo();
    }

    /**
     * Gets the average price the player will need to get for each resource to break even.
     * @param resource
     * @return
     */
    public double getAvgPrice(Resource resource) {
        if (avgPrice.containsKey(resource)) {
            return Math.round(100 * avgPrice.get(resource)) / 100.0;
        } else {
            Log.e("main", "Player Class: Failed to get avg price for " + resource +
                    " since it does not exist in the cargo load.");
            return -1;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Travel Methods
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Gets the current amount of fuel on the ship
     * @return int fuel on ship
     */
    public int getFuel() {
        return ship.getFuel();
    }

    /**
     * Deducts the fuel from the ship
     * @param fuel amount of fuel to deduct
     * @return true if the action is successful, false otherwise
     */
    public boolean deductFuel(int fuel) {
        return ship.deductFuel(fuel);
    }

    /**
     * Adds 1 fuel point to the ship
     * @return true if successful
     */
    public boolean incrementFuel() {
        return ship.incrementFuel();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Player Setters
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Sets the name of the player. Used while creating the player
     * @param name for the player
     */
    public void setName(String name) {
        this.name = name;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Player Getters
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Gets the pilot skill points for the player
     * @return int pilot skill points
     */
    public int getPilotSkillPoints() {return pilotSkillPoints;}

    /**
     * Gets the fighter skill points for the player
     * @return int fighter skill points
     */
    public int getFighterSkillPoints() {return fighterSkillPoints;}

    /**
     * Gets the trader skill points for the player
     * @return int trader skill points
     */
    public int getTraderSkillPoints() {return traderSkillPoints;}

    /**
     * Gets the engineer skill points for the player
     * @return int engineer skill points
     */
    public int getEngineerSkillPoints() {return engineerSkillPoints;}

    /**
     * Gets array with designated points for each skill in each respective index
     * [0] = pilot, [1] = fighter, [2] = trader, [3] = engineer
     * @return int skillPoints Array
     */
    public int[] getSkillPointsArray() {
        int []skillPoints = {pilotSkillPoints, fighterSkillPoints, traderSkillPoints,
                engineerSkillPoints};
        return skillPoints;
    }

    /**
     * Gets the player's name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's credits
     * @return int credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Gets the player's ship name
     * @return String ship name
     */
    public String getShipName() {
        return ship.toString();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Increment, Decrement, and getter methods for Player Creation
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Increments the player's pilot skill points by one if there are still points
     * left to allocate.
     */
    public void incrementPilotSkillPoints() {
        if (getSkillSum() < skillPoints) {
            pilotSkillPoints++;
        }
    }

    /**
     * Increments the player's fighter skill points by one if there are still points
     * left to allocate.
     */
    public void incrementFighterSkillPoints() {
        if (getSkillSum() < skillPoints) {
            fighterSkillPoints++;
        }
    }

    /**
     * Increments the player's trader skill points by one if there are still points
     * left to allocate.
     */
    public void incrementTraderSkillPoints() {
        if (getSkillSum() < skillPoints) {
            traderSkillPoints++;
        }
    }

    /**
     * Increments the player's engineer skill points by one if there are still points
     * left to allocate.
     */
    public void incrementEngineerSkillPoints() {
        if (getSkillSum() < skillPoints) {
            engineerSkillPoints++;
        }
    }

    /**
     * Decrements the player's pilot skill points by one if the player has at least
     * one pilot skill point
     */
    public void decrementPilotSkillPoints() {
        if (pilotSkillPoints > 0) {
            pilotSkillPoints--;
        }
    }

    /**
     * Decrements the player's fighter skill points by one if the player has at least
     * one fighter skill point
     */
    public void decrementFighterSkillPoints() {
        if (fighterSkillPoints > 0) {
            fighterSkillPoints--;
        }
    }

    /**
     * Decrements the player's trader skill points by one if the player has at least
     * one trader skill point
     */
    public void decrementTraderSkillPoints() {
        if (traderSkillPoints > 0) {
            traderSkillPoints--;
        }
    }

    /**
     * Decrements the player's engineer skill points by one if the player has at least
     * one engineer skill point
     */
    public void decrementEngineerSkillPoints() {
        if (engineerSkillPoints > 0) {
            engineerSkillPoints--;
        }
    }

    /**
     * Gets the sum of all the skillpoints for the player.
     * @return int sum
     */
    private int getSkillSum(){
        return pilotSkillPoints + fighterSkillPoints + traderSkillPoints + engineerSkillPoints;
    }

    /**
     * Gets the total amount of points that have yet to be allocated
     * @return int remaining amount of skill points
     */
    public int getRemainingCount() {
        return skillPoints - getSkillSum();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //toString Method
    ///////////////////////////////////////////////////////////////////////////////////////

    public String toString() {
        return "Player name: " + this.name + "\n"
                + "Pilot Skill Points: " + this.pilotSkillPoints + "\n"
                + "Fighter Skills Points: " + this.fighterSkillPoints + "\n"
                + "Trader Skills Points: " + this.traderSkillPoints + "\n"
                + "Engineer Skills Points: " + this.engineerSkillPoints + "\n"
                + "Credits: " + this.credits + "\n"
                + "Spaceship: " + this.ship + "\n";
    }

}
