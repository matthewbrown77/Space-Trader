package com.example.spacetrader.entity;

import java.io.Serializable;

public class Player implements Serializable {

    public final int skillPoints = 16;

    private String name;
    private int pilotSkillPoints;
    private int fighterSkillPoints;
    private int traderSkillPoints;
    private int engineerSkillPoints;
    private int credits;
    private Ship ship;
    private Planet currentPlanet;

    public Player() {
        this.pilotSkillPoints = 0;
        this.fighterSkillPoints = 0;
        this.traderSkillPoints = 0;
        this.engineerSkillPoints = 0;
        this.credits = 1000;
        this.ship = new Ship(ShipType.GNAT);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Manipulating Player Cargo
    ///////////////////////////////////////////////////////////////////////////////////////

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
            return false;
        } else if (ship.addCargo(resource)){
            credits -= price;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method removes requested resource from cargo load, and updates credits if successful
     * @param resource to be removed
     * @return true if the remove was successful, false otherwise
     */
    public boolean removeCargo(Resource resource, int price) {
        if (ship.removeCargo(resource)) {
            credits += price;
            return true;
        }
        return false;
    }

    public int getMaxCargo() {
        return ship.getMaxCargo();
    }

    public int getCurrentCargo() {
        return ship.getCurrentCargo();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Player Setters
    ///////////////////////////////////////////////////////////////////////////////////////

    public void setName(String name) {
        this.name = name;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Player Getters
    ///////////////////////////////////////////////////////////////////////////////////////

    public int getPilotSkillPoints() {return pilotSkillPoints;}

    public int getFighterSkillPoints() {return fighterSkillPoints;}

    public int getTraderSkillPoints() {return traderSkillPoints;}

    public int getEngineerSkillPoints() {return engineerSkillPoints;}

    public int[] getSkillPointsArray() {
        int []skillPoints = {pilotSkillPoints, fighterSkillPoints, traderSkillPoints, engineerSkillPoints};
        return skillPoints;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public String getShipName() {
        return ship.toString();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Increment, Decrement, and getter methods for Player Creation
    ///////////////////////////////////////////////////////////////////////////////////////

    public void incrementPilotSkillPoints() {
        if (getSkillSum() < skillPoints) {
            pilotSkillPoints++;
        }
    }
    public void incrementFighterSkillPoints() {
        if (getSkillSum() < skillPoints) {
            fighterSkillPoints++;
        }
    }
    public void incrementTraderSkillPoints() {
        if (getSkillSum() < skillPoints) {
            traderSkillPoints++;
        }
    }
    public void incrementEngineerSkillPoints() {
        if (getSkillSum() < skillPoints) {
            engineerSkillPoints++;
        }
    }

    public void decrementPilotSkillPoints() {
        if (pilotSkillPoints > 0) {
            pilotSkillPoints--;
        }
    }
    public void decrementFighterSkillPoints() {
        if (fighterSkillPoints > 0) {
            fighterSkillPoints--;
        }
    }
    public void decrementTraderSkillPoints() {
        if (traderSkillPoints > 0) {
            traderSkillPoints--;
        }
    }
    public void decrementEngineerSkillPoints() {
        if (engineerSkillPoints > 0) {
            engineerSkillPoints--;
        }
    }

    private int getSkillSum(){
        return pilotSkillPoints + fighterSkillPoints + traderSkillPoints + engineerSkillPoints;
    }

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
