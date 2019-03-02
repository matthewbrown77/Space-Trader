package com.example.spacetrader.entity;

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

    private String name;
    private int minTechLevelToProduce;
    private int minTechLevelToUse;
    private int basePrice;
    private int priceIncreasePerLevel;
    private int variance;
    private ResourceType cheapCondition;
    private ResourceType expensiveCondition;
    private int minPrice;
    private int maxPrice;

    Resource(String name, int minTechLevelToProduce, int minTechLevelToUse, int basePrice,
             int priceIncreasePerLevel, int variance, ResourceType cheapCondition,
             ResourceType expensiveCondition, int minPrice, int maxPrice) {
        this.name = name;
        this.minTechLevelToProduce = minTechLevelToProduce;
        this.minTechLevelToUse = minTechLevelToUse;
        this.basePrice = basePrice;
        this.priceIncreasePerLevel = priceIncreasePerLevel;
        this.variance = variance;
        this.cheapCondition = cheapCondition;
        this.expensiveCondition = expensiveCondition;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public int getPrice() {
        return basePrice;
    }
}
