package com.example.spacetrader.entity;

import android.util.Log;

import java.io.Serializable;

/**
 * The resource enum represents a specific type of item that the player can trade.
 */
public enum Resource {
    WATER("Water",0,0,30,3,4,ResourceType.LOTS_OF_WATER, ResourceType.DESERT, 30, 50),
    FURS("Furs",0,0,250,10,10,ResourceType.RICH_FAUNA, ResourceType.LIFELESS, 230, 280),
    FOOD("Food",1,0,100,5,5,ResourceType.RICH_SOIL, ResourceType.POOR_SOIL, 90, 160),
    ORE("Ore",2,2,350,20,10,ResourceType.MINERAL_RICH, ResourceType.MINERAL_POOR, 350, 420),
    GAMES("Games",3,1,250,-10,5,ResourceType.ARTISTIC, null, 160, 270),
    FIREARMS("Firearms",3,1,1250,-75,100,ResourceType.WARLIKE, null, 600, 1100),
    MEDICINE("Medicine",4,1,650,-20,10,ResourceType.LOTS_OF_HERBS, null, 400, 700),
    MACHINES("Machines",4,3,900,-30,5,null, null, 600, 800),
    NARCOTICS("Narcotics",5,0,3500,-125,150,ResourceType.WEIRD_MUSHROOMS, null, 2000, 3000),
    ROBOTS("Robots",6,4,5000,-150,100,null, null, 3500, 5000);

    private final String name;
    private final int minTechLevelToProduce;
    private final int minTechLevelToUse;
    private final int basePrice;
    private final int priceIncreasePerLevel;
    private final int variance;
    private final ResourceType cheapResourceCondition;
    private final ResourceType expensiveResourceCondition;
    private Government cheapGovernmentCondition;
    private Government expensiveGovernmentCondition;

    /**
     * Resource Constructor
     * @param name
     * @param minTechLevelToProduce
     * @param minTechLevelToUse
     * @param basePrice
     * @param priceIncreasePerLevel
     * @param variance
     * @param cheapResourceCondition
     * @param expensiveResourceCondition
     * @param minPrice
     * @param maxPrice
     */
    Resource(String name, int minTechLevelToProduce, int minTechLevelToUse, int basePrice,
             int priceIncreasePerLevel, int variance, ResourceType cheapResourceCondition,
             ResourceType expensiveResourceCondition, int minPrice, int maxPrice) {
        this.name = name;
        this.minTechLevelToProduce = minTechLevelToProduce;
        this.minTechLevelToUse = minTechLevelToUse;
        this.basePrice = basePrice;
        this.priceIncreasePerLevel = priceIncreasePerLevel;
        this.variance = variance;
        this.cheapResourceCondition = cheapResourceCondition;
        this.expensiveResourceCondition = expensiveResourceCondition;
    }

    /**
     * Gets the minimum techLevel necessary to produce the item.
     * Note that this may be different than the min techLevel to use.
     * @return techLevel
     */
    public int getMinTechLevelToProduce() {
        return minTechLevelToProduce;
    }

    /**
     * Gets the minimum techLevel necessary to use (purchase) the item.
     * Note that this may be different than the min techLevel to produce.
     * @return techLevel
     */
    public int getMinTechLevelToUse() {
        return minTechLevelToUse;
    }

    /**
     * Generates the price for the resource given the techLevel and resourceType
     * @param techLevel of the current Planet
     * @param resourceType of the current Planet
     * @return int price of the good. -1 if the resource is not available to buy or sell.
     */
    public int getPrice(TechLevel techLevel, ResourceType resourceType, Government government) {
        if ((techLevel.getLevel() < minTechLevelToProduce) && (techLevel.getLevel()
                < minTechLevelToUse)) {
            Log.e("main", "Resource Class: Failed to retrieve the price of " + name
                    + " since it is not available at the current location.");
            return -1;
        }
        int price = basePrice;
        price += priceIncreasePerLevel * techLevel.getLevel();
        price += (int)(government.getTradeFactor() * variance);
        if ((cheapResourceCondition != null) && cheapResourceCondition.equals(resourceType)) {
            price /= 10;
        }
        if ((expensiveResourceCondition != null) && expensiveResourceCondition.
                equals(resourceType)) {
            price *= 10;
        }
        return price;
    }

    @Override
    public String toString() {
        return name;
    }
}
