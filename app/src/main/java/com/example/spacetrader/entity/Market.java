package com.example.spacetrader.entity;

import java.io.Serializable;

public class Market implements Serializable {

    private String name;
    private TechLevel techLevel;
    private ResourceType resourceType;

    public Market(String name, TechLevel techLevel, ResourceType resourceType) {
        this.name = name;
        this.techLevel = techLevel;
        this.resourceType = resourceType;
    }

    public boolean resourceAvailableToBuy(Resource resource) {
        return resource.getMinTechLevelToProduce() >= techLevel.getLevel();
    }

    public boolean resourceAvailableToSell(Resource resource) {
        return resource.getMinTechLevelToUse() >= techLevel.getLevel();
    }
}
