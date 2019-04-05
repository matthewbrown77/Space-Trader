package com.example.spacetrader.entity;

import java.io.Serializable;

/**
 * The Planet class represents a planet that the player can travel to.
 * Every planet has a name, techLevel, resourceType, government, and market
 */
public class Planet implements Serializable {

    private final String name;
    private final TechLevel techLevel;
    private final ResourceType resourceType;
    private Government government;
    private final Market market;
    private final int position; //position in solar system 0 - 5

    /**
     * Planet constructor
     * Will create a random techLevel, resourceType, and Government
     */
    public Planet(int pos) {
        this.name = Names.getName();
        this.techLevel = TechLevel.values()[(int) (Math.random() * TechLevel.values().length)];
        this.resourceType = ResourceType.values()[(int) (Math.random() *
                ResourceType.values().length)];
        this.government = Government.values()[(int) (Math.random() * Government.values().length)];
        while ((techLevel.getLevel() < government.getMinTechLevel()) ||
                (techLevel.getLevel() > government.getMaxTechLevel())) {
            this.government = Government.values()[(int) (Math.random() *
                    Government.values().length)];
        }
        this.market = new Market(this);
        this.position = pos;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Market Methods
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Checks if the resource is available at the planet's market (i.e. amount > 0)
     *
     * @param resource
     * @return true if resource is available, false otherwise
     */
    public boolean resourceAvailableToBuy(Resource resource) {
        return market.resourceAvailableToBuy(resource);
    }

    /**
     * Checks if the resource can be bought at the current location given the planet's
     * techLevel and resourceType. Note that even if a resource can be bought, it does
     * not necessarily mean it is available to purchase.
     *
     * @param resource
     * @return true if resource can be bought, false otherwise
     */
    public boolean allowedToBuy(Resource resource) {
        return market.allowedToBuy(resource);
    }

    /**
     * Checks if the resource can be sold at the current location given the planet's
     * techLevel and resourceType. Note that even if a resource can be sold, it does
     * not necessarily mean it is available to sell (i.e. the player does not have
     * the resource).
     *
     * @param resource
     * @return true if resource can be sold, false otherwise
     */
    public boolean allowedToSell(Resource resource) {
        return market.allowedToSell(resource);
    }

    /**
     * Gets the price for the resource at the current planet's market
     *
     * @param resource
     * @return int price of the good. -1 if the resource is not available to buy or sell.
     */
    public int getResourcePrice(Resource resource) {
        return market.getResourcePrice(resource);
    }

    /**
     * Gets the amount of the specified resource at the planet
     *
     * @param resource
     * @return int resource amount
     */
    public int getResourceAmount(Resource resource) {
        return market.getResourceAmount(resource);
    }

    /**
     * Increments the amount of the specified resource at the planet's market
     *
     * @param resource
     */
    public void incrementResourceAmount(Resource resource) {
        market.incrementResourceAmount(resource);
    }

    /**
     * Decrements the amount of the specified resource at the planet's market
     *
     * @param resource
     */
    public void decrementResourceAmount(Resource resource) {
        market.decrementResourceAmount(resource);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //Planet getter methods
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Gets the name of the planet
     *
     * @return String name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the planet's position in its solar system
     *
     * @return
     */
    public int getPosition() {
        return position;
    }

    /**
     * Gets the resourceType of the planet
     *
     * @return ResourceType
     */
    public ResourceType getResourceType() {
        return this.resourceType;
    }

    /**
     * Gets the techLevel of the planet
     *
     * @return techLevel
     */
    public TechLevel getTechLevel() {
        return this.techLevel;
    }

    /**
     * Gets the governmentType of the planet
     *
     * @return governmentType
     */
    public Government getGovernment() {
        return this.government;
    }

    /**
     * Gets the color of the planet
     *
     * @return color of the planet
     */
    public int getColor() {
        return resourceType.getColor();
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Planet)) {
            return false;
        }
        Planet p = (Planet) o;
        return (p.name.equals(name) && p.techLevel.equals(techLevel)
                && p.resourceType.equals(resourceType) && p.government.equals(government));
    }

    @Override
    public String toString() {
        return name;
    }

}