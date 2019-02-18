package com.example.spacetrader;

public class Planet {

    private String name;
    private TechLevel techLevel;
    private ResourceType resourceType;

    public Planet(String name, TechLevel techLevel, ResourceType resourceType) {
        this.name = name;
        this.techLevel = techLevel;
        this.resourceType = resourceType;
    }
}