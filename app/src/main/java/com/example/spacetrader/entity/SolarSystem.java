package com.example.spacetrader.entity;

public class SolarSystem {
    private String name;
    private Coordinate coordinates;
    private Planet planet;

    /**
     * Constructor for the Solar System
     * @param name Solar System name
     * @param coordinate Location of the Solar System.
     */
    public SolarSystem(String name, Coordinate coordinate) {
        this.name = name;
        this.coordinates = coordinates;
        this.planet = new Planet(name);
    }


}
