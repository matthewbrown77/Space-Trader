package com.example.spacetrader.entity;

import java.io.Serializable;

public class Market implements Serializable {

    private String name;
    private TechLevel techLevel;
    private ResourceType resourceType;
    private int[] resourceAmounts;

    public Market(String name, TechLevel techLevel, ResourceType resourceType) {
        this.name = name;
        this.techLevel = techLevel;
        this.resourceType = resourceType;
        resourceAmounts = new int [Resource.values().length];
        for (int i = 0; i < resourceAmounts.length; i++) {
            resourceAmounts[i] = (int)(Math.random() * 10 + 3);
        }
    }

    public boolean resourceAvailableToBuy(Resource resource) {
        return techLevel.getLevel() >= resource.getMinTechLevelToProduce();
    }

    public boolean resourceAvailableToSell(Resource resource) {
        return techLevel.getLevel() >= resource.getMinTechLevelToUse();
    }

    public int getResourcePrice(Resource resource) {
        return resource.getPrice(techLevel, resourceType);
    }

    public int getResourceAmount(Resource resource) {
        int index = -1;
        Resource[] resources = Resource.values();
        for(int i = 0; i < resources.length; i++) {
            if (resources[i].equals(resource)) {
                index = i;
                break;
            }
        }
        return resourceAmounts[index];
    }

    public void incrementResourceAmount(Resource resource) {
        int index = -1;
        Resource[] resources = Resource.values();
        for(int i = 0; i < resources.length; i++) {
            if (resources[i].equals(resource)) {
                index = i;
                break;
            }
        }
        resourceAmounts[index]++;
    }

    public void decrementResourceAmount(Resource resource) {
        int index = -1;
        Resource[] resources = Resource.values();
        for(int i = 0; i < resources.length; i++) {
            if (resources[i].equals(resource)) {
                index = i;
                break;
            }
        }
        resourceAmounts[index]--;
    }
}
