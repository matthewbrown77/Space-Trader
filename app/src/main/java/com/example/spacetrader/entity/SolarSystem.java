package com.example.spacetrader.entity;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * The SolarSystem class represents a solar system in the Universe. Each solarSystem
 * has a collection of planets predetermined during game creation. Each solarSystem
 * has a distinct coordinate in the universe. Once a player is at a solarSystem, they
 * can travel to any of its planets.
 */
public class SolarSystem{
    private String name;
    private Coordinate coordinate;
    private Planet[] planets;
    final static int MAX_PLANETS = 5;
    final static int MIN_PLANETS = 3;

    /**
     * Constructor for the Solar System
     * Randomly assigns a name
     * @param coordinate Location of the Solar System.
     */
    public SolarSystem(Coordinate coordinate) {
        this.name = Names.getName();
        this.coordinate = coordinate;
        int planetNumber = MIN_PLANETS + (int)(Math.random() * (MAX_PLANETS - MIN_PLANETS + 1));
        planets = new Planet[planetNumber];
        for (int i = 0; i < planetNumber; i++) {
            planets[i] = new Planet(i);
        }
    }

    /**
     * Gets the list of planets in the solarSystem
     * @return list of planets
     */
    public List<Planet> getPlanets() {
        List<Planet> planetList = new ArrayList<>();
        for (Planet p: planets) {
            planetList.add(p);
        }
        return planetList;
    }


    public double getDistance (SolarSystem solarSystem) {
        return coordinate.getDistance(solarSystem.getCoordinate());
    }

    /**
     * Gets if the planet is contained in the current solarSystem
     * @param planet
     * @return
     */
    public boolean contains(Planet planet) {
        for (Planet p: planets) {
            if (p.equals(planet)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the SolarSystem name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the coordinates of the Solar System
     * @return coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SolarSystem)) {
            return false;
        }
        SolarSystem s = (SolarSystem) o;
        return (s.coordinate.equals(coordinate)); // two solar systems with the same coordinate must be the same
    }
}
