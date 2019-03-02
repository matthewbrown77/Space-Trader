package com.example.spacetrader.entity;

import android.util.Log;

import java.io.Serializable;

/**
 * Game class to store all information regarding the gameplay.
 *
 */
public class Game implements Serializable {

    private Player player;
    private Universe universe;
    private Planet currentPlanet;

    public Game (Player player) {
        this.player = player;
        this.universe = new Universe();
        Log.d("main", universe.toString());//will remove later.
        this.currentPlanet = new Planet();//will remove later. Testing marketplace.
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Player Methods
    ///////////////////////////////////////////////////////////////////////////////////////

    public String getPlayerShipName() {
        return player.getShipName();
    }

    public int [] getPlayerSkillPointsArray() {
        return player.getSkillPointsArray();
    }

    public String getPlayerName() {
        return player.getName();
    }

    public int getPlayerCredits() {
        return player.getCredits();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Market
    ///////////////////////////////////////////////////////////////////////////////////////

    public boolean resourceAvailableToBuy(Resource resource) {
        return currentPlanet.resourceAvailableToBuy(resource);
    }

    public boolean resourceAvailableToSell(Resource resource) {
        return currentPlanet.resourceAvailableToSell(resource);
    }

    public String getCurrentPlanetName() {
        return currentPlanet.getName();
    }

    public String getCurrentPlanetResourceType(){
        return currentPlanet.getResourceType().toString();
    }

    public String getCurrentPlanetTechLevel() {
        return currentPlanet.getTechLevel().toString();
    }

    public int getResourcePrice(Resource resource) {
        return currentPlanet.getResourcePrice(resource);
    }

    public int getResourceAmount(Resource resource) {
        return currentPlanet.getResourceAmount(resource);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Ship Inventory
    ///////////////////////////////////////////////////////////////////////////////////////

    public int getCargoCount(Resource resource) {
        return player.getCargoCount(resource);
    }

    public void addCargo(Resource cargo) {
        if (resourceAvailableToBuy(cargo) && currentPlanet.getResourceAmount(cargo) > 0) {
            if (player.addCargo(cargo, cargo.getPrice(currentPlanet.getTechLevel(), currentPlanet.getResourceType()))){
                currentPlanet.decrementResourceAmount(cargo);
            }
        }
    }

    public void removeCargo(Resource cargo) {
        if (resourceAvailableToSell(cargo)) {
            if (player.removeCargo(cargo, cargo.getPrice(currentPlanet.getTechLevel(), currentPlanet.getResourceType()))) {
                currentPlanet.incrementResourceAmount(cargo);
            }

        }
    }

    public int getMaxCargo() {
        return player.getMaxCargo();
    }

    public int getCurrentCargo() {
        return player.getCurrentCargo();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Class methods
    ///////////////////////////////////////////////////////////////////////////////////////

    public String toString() {
        return "GAME: " + player.toString();
    }

}
