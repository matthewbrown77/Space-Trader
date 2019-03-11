package com.example.spacetrader.entity;

import android.util.Log;

public class Game {

    private static Game instance = new Game();
    public static Game getInstance() {
        return instance;
    }

    private Player player;
    private Universe universe;
    private Planet currentPlanet;

    /**
     * Constructor for the game class. Creates a new universe.
     */
    public Game () {
        this.player = new Player();
        this.universe = new Universe();
        Log.d("main", universe.toString());//will remove later.
        this.currentPlanet = new Planet();//will remove later. Testing marketplace.
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Player Methods
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Sets the player in the game. Used in createPlayer Activity.
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets name of the ship
     * @return String name
     */
    public String getPlayerShipName() {
        return player.getShipName();
    }

    /**
     * Gets array with designated points for each skill in each respective index
     * [0] = pilot, [1] = fighter, [2] = trader, [3] = engineer
     * @return int skillPoints Array
     */
    public int [] getPlayerSkillPointsArray() {
        return player.getSkillPointsArray();
    }

    /**
     * Gets the player's name
     * @return String name
     */
    public String getPlayerName() {
        return player.getName();
    }

    /**
     * Gets the player's credits
     * @return int player's credits
     */
    public int getPlayerCredits() {
        return player.getCredits();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Market
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Checks if the resource can be bought at the current location given the planet's
     * techLevel and resourceType. Note that even if a resource can be bought, it does
     * not necessarily mean it is available to purchase.
     * @param resource
     * @return true if resource can be bought, false otherwise
     */
    public boolean allowedToBuy(Resource resource) {
        return currentPlanet.allowedToBuy(resource);
    }

    /**
     * Checks if the specified resource is available on the planet (i.e. amount > 0)
     * @param resource
     * @return true if resource can be bought, false otherwise
     */
    public boolean resourceAvailableToBuy(Resource resource) {
        return currentPlanet.resourceAvailableToBuy(resource);
    }

    /**
     * Checks if the resource can be sold at the current location given the planet's
     * techLevel and resourceType. Note that even if a resource can be sold, it does
     * not necessarily mean it is available to sell (i.e. the player does not have
     * the resource).
     * @param resource
     * @return true if resource can be sold, false otherwise
     */
    public boolean allowedToSell(Resource resource) {
        return currentPlanet.allowedToSell(resource);
    }

    /**
     * Gets current location
     * @return current planet's name
     */
    public String getCurrentPlanetName() {
        return currentPlanet.getName();
    }

    /**
     * Gets the current location's resourceType
     * @return String representation of the resourceType
     */
    public String getCurrentPlanetResourceType(){
        return currentPlanet.getResourceType().toString();
    }

    /**
     * Gets the current location's techLevel
     * @return String representation of the techLevel
     */
    public String getCurrentPlanetTechLevel() {
        return currentPlanet.getTechLevel().toString();
    }

    /**
     * Gets the price for the resource at the current planet's market
     * @param resource
     * @return int price of the good. -1 if the resource is not available to buy or sell.
     */
    public int getResourcePrice(Resource resource) {
        return currentPlanet.getResourcePrice(resource);
    }

    /**
     * Gets the amount of the specified resource at the current location
     * @param resource
     * @return int resource amount
     */
    public int getResourceAmount(Resource resource) {
        return currentPlanet.getResourceAmount(resource);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Ship Inventory
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Gets the amount of cargo that the player's ship current holds
     * @param resource
     * @return int cargo amount
     */
    public int getCargoCount(Resource resource) {
        return player.getCargoCount(resource);
    }

    /**
     * Adds the specified resource to the player's cargo hold.
     * @param resource
     */
    public void addCargo(Resource resource) {
        if (!allowedToSell(resource)) {
            Log.e("main", "Game Class: Failed to add " + resource + " since it is not allowed to " +
                    "be bought at the market");
        } else if (!resourceAvailableToBuy(resource)) {
            Log.e("main", "Game Class: Failed to add " + resource + " since it is not available " +
                    "at the market");
        } else if (player.addCargo(resource, resource.getPrice(currentPlanet.getTechLevel(), currentPlanet.getResourceType()))){
            currentPlanet.decrementResourceAmount(resource);
        }
    }

    /**
     * Gets the average price the player will need to get for each resource to break even.
     * @param resource
     * @return
     */
    public double getAvgPrice(Resource resource) {
        return player.getAvgPrice(resource);
    }

    /**
     * Removes the specified cargo from the ship's cargo.
     * @param resource
     */
    public void removeCargo(Resource resource) {
        if (!allowedToSell(resource)) {
            Log.e("main", "Game Class: Failed to remove " + resource + " since it is not allowed to " +
                    "be sold at the market");
        } else if (player.removeCargo(resource, resource.getPrice(currentPlanet.getTechLevel(), currentPlanet.getResourceType()))) {
            currentPlanet.incrementResourceAmount(resource);
        }
    }

    /**
     * Dumps the specified cargo from the ship's cargo. No credits are added to player.
     * @param resource
     */
    public void dumpCargo(Resource resource) {
        player.dumpCargo(resource);
    }

    /**
     * Gets the max cargo amount that the player's ship can hold
     * @return max cargo
     */
    public int getMaxCargo() {
        return player.getMaxCargo();
    }

    /**
     * Gets current cargo amount on the player's ship
     * @return current cargo
     */
    public int getCurrentCargo() {
        return player.getCurrentCargo();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Class methods
    ///////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "GAME: " + player.toString();
    }

}
