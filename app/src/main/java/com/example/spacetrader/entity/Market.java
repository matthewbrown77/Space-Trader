package com.example.spacetrader.entity;

import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The Market class controls all trading activities and resource amounts
 * on a given planet. Only one market can exist per planet. A market is dynamic
 * and can change after a player trades or conditions on the planet change.
 */
class Market implements Serializable {

    private final TechLevel techLevel;
    private final ResourceType resourceType;
    private final Government government;
    private final HashMap<Resource, Integer> resourceAmounts;

    /**
     * Constructor for the market. Uses planet's techLevel, resourceType, and name
     * to generate a new market with resources.
     * @param planet location of the market.
     */
    public Market(Planet planet) {
        String name = planet.getName();
        this.techLevel = planet.getTechLevel();
        this.resourceType = planet.getResourceType();
        this.government = planet.getGovernment();
        this.resourceAmounts = new HashMap<>();
        for (Resource res: Resource.values()) {
            if (allowedToBuy(res)) {
                resourceAmounts.put(res, (int) ((Math.random() * 10) + 3));
            }
        }
    }

    /**
     * Checks if the resource is available at the current market (i.e. amount > 0)
     * @param resource
     * @return true if resource is available, false otherwise
     */
    public boolean resourceAvailableToBuy(Resource resource) {
        if (resourceAmounts == null) {
            return false;
        }
        return resourceAmounts.containsKey(resource) && (resourceAmounts.get(resource) > 0);
    }

    /**
     * Checks if the resource can be bought at the current market
     * Note that even if a resource can be bought, it does not necessarily mean it is available
     * @param resource
     * @return true if resource can be bought, false otherwise
     */
    public boolean allowedToBuy(Resource resource) {
        return techLevel.getLevel() >= resource.getMinTechLevelToProduce();
    }

    /**
     * Checks if the resource can be sold at the current market. Note that even if a resource
     * can be sold, it does not necessarily mean it is available to sell (i.e. the player does
     * not have the resource).
     * @param resource
     * @return true if resource can be sold, false otherwise
     */
    public boolean allowedToSell(Resource resource) {
        return techLevel.getLevel() >= resource.getMinTechLevelToUse();
    }

    /**
     * Gets the price for the resource in the market
     * @param resource
     * @return int price of the good. -1 if the resource is not available to buy or sell.
     */
    public int getResourcePrice(Resource resource) {
        return resource.getPrice(techLevel, resourceType, government);
    }

    /**
     * Gets the current resource amount on the planet
     * @param resource
     * @return int resource amount. -1 if resource cannot exist on the planet.
     */
    public int getResourceAmount(Resource resource) {
        if (resourceAmounts == null) {
            return 0;
        }
        if (!resourceAmounts.containsKey(resource)) {
            Log.e("main", "Market Class: Failed to get " + resource + " amount since it is not " +
                    "available on this planet");
            return -1;
        }
        return resourceAmounts.get(resource);
    }

    /**
     * Increments the specified resource amount by 1. Does nothing if the resource cannot
     * exist on the planet.
     * @param resource to be decremented
     */
    public void incrementResourceAmount(Resource resource) {
        if (resourceAmounts == null) {
            return;
        }
        if (resourceAmounts.containsKey(resource)) {
            resourceAmounts.put(resource, resourceAmounts.get(resource) + 1);
        } else {
            Log.e("main", "Market Class: Failed to increment " + resource + " at " +
                    "the market since it cannot be bought on this planet");
        }
    }

    /**
     * Decrements the specified resource amount by 1. Does nothing if the resource cannot
     * exist on the planet or is already 0.
     * @param resource to be decremented
     */
    public void decrementResourceAmount(Resource resource) {
        if (resourceAmounts == null) {
            return;
        }
        if (!resourceAmounts.containsKey(resource)) {
            Log.e("main", "Planet Class: Failed to remove " + resource
                    + " since it does cannot be bought on the planet");
        }
        if (resourceAmounts.get(resource) >= 1) {
            resourceAmounts.put(resource, resourceAmounts.get(resource) - 1);
        } else {
            Log.e("main", "Planet Class: Failed to remove " + resource
                    + " from market since there is none left on the planet");
        }
    }
}
