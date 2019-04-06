package com.example.spacetrader.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The SolarSystem class represents a solar system in the Universe. Each solarSystem
 * has a collection of planets predetermined during game creation. Each solarSystem
 * has a distinct coordinate in the universe. Once a player is at a solarSystem, they
 * can travel to any of its planets.
 */
public class SolarSystem implements Serializable {
    private final String name;
    private final Coordinate coordinate;
    private final List<Planet> planets;
    private static final int MAX_PLANETS = 5;
    private static final int MIN_PLANETS = 3;

    /**
     * Constructor for the Solar System
     * Randomly assigns a name
     * @param coordinate Location of the Solar System.
     */
    public SolarSystem(Coordinate coordinate) {
        this.name = Names.getName();
        this.coordinate = coordinate;
        this.planets = new ArrayList<>();
        int planetNumber = MIN_PLANETS + (int) (Math.random() * ((MAX_PLANETS - MIN_PLANETS) + 1));
        for (int i = 0; i < planetNumber; i++) {
            planets.add(new Planet(i));
        }
    }

    /**
     * Determines if the planet is in the solar system
     * @param planet planet to be searched for
     * @return true if the planet is in the solar system, false otherwise
     */
    public boolean contains(Planet planet) {
        return planets.contains(planet);
    }

    /**
     * Gets the index of the planet in the solar system
     * @param planet planet to be searched for
     * @return index of the planet
     */
    public int indexOf(Planet planet) {
        return planets.indexOf(planet);
    }

    /**
     * Gets the coordinates of the Solar System
     * @return coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Gets the list of planets in the solarSystem
     * @return list of planets
     */
    public List<Planet> getPlanets() {
        return Collections.unmodifiableList(planets);
    }

    /**
     * Gets the distance between this SolarSystem and the parameter SolarSystem
     * @param solarSystem
     * @return distance
     */
    public double getDistance (SolarSystem solarSystem) {
        return coordinate.getDistance(solarSystem.getCoordinate());
    }

    /**
     * Gets the SolarSystem name
     * @return name
     */
    public String getName() {
        return name;
    }

    public int getXCoordinate() {
        return coordinate.getX();
    }

    public int getYCoordinate() {
        return coordinate.getY();
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
        return (s.coordinate.equals(coordinate));
        // two solar systems with the same coordinate must be the same
    }
}
