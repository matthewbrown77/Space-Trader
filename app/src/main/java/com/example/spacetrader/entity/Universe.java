package com.example.spacetrader.entity;
import android.util.Log;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * Class representing the space universe. Contains solar systems.
 */
public class Universe {

    /**
     * List of solarSystems
     */
    private List<SolarSystem> solarSystems;

    /**
     * List of solarSystem coordinates. Used to prevent repeats.
     */
    private static HashSet<Coordinate> solarSystemCoordinates = new HashSet<>();

    /**
     * Origin SolarSystem upon game creation
     */
    private SolarSystem originSolarSystem;

    /**
     * Origin Planet upon game creation
     */
    private Planet originPlanet;

    /**
     * Total number of solarSystems in the universe
     */
    private final int SOLAR_SYSTEM_COUNT = 20;

    /**
     * Preferred minimum distance between any two solar systems.
     *
     * The actual distance between two solar systems may be less
     * if the universe is extremely crowded.
     */
    private final int MIN_DISTANCE_BETWEEN_SOLAR_SYSTEMS = 10;

    /**
     * Creates a universe with 10 solar systems, with 1 at the origin.
     */
    public Universe() {
        solarSystems = new ArrayList<>();
        originSolarSystem = new SolarSystem(Coordinate.getCenterCoordinate());
        solarSystemCoordinates.add(Coordinate.getCenterCoordinate());
        originPlanet = originSolarSystem.getPlanets().get(0);
        solarSystems.add(originSolarSystem);
        for (int i = 0; i < SOLAR_SYSTEM_COUNT; i++) {
            solarSystems.add(new SolarSystem(generateNewCoordinate(100)));
        }
    }

    /**
     * Gets a list of the solar systems in the universe
     * @return solarSystems
     */
    public List<SolarSystem> getSolarSystems() {
        return solarSystems;
    }

    /**
     * Gets the origin solarSystem
     * @return origin solarSystem
     */
    public SolarSystem getOriginSolarSystem() {
        return originSolarSystem;
    }

    /**
     * Gets the origin planet
     * @return origin planet
     */
    public Planet getOriginPlanet() {
        return originPlanet;
    }

    /**
     * Generates a unique coordinate at least MIN_DISTANCE_BETWEEN_SOLAR_SYSTEMS
     * away from any other solar system. Solar systems might be closer if the
     * universe is extremely crowded.
     *
     * @param maxAttempts the maximum number of attempts for finding the coordinate
     *                    before the method will terminate.
     * @return Coordinate
     */
    private Coordinate generateNewCoordinate(int maxAttempts) {
        if (maxAttempts == 0) {
            throw new RuntimeException("Universe is too crowded. Increase the " +
                    "coordinate size or decrease the amount of solar-systems generated");
        }
        int distance = maxAttempts > 10 ? MIN_DISTANCE_BETWEEN_SOLAR_SYSTEMS : 1;
        if (distance == 1) {
            Log.e("main", "Had to revert to distance 1");
        }
        Coordinate newCoordinate = new Coordinate(
                (int)(Math.random() * (Coordinate.MAX_X - Coordinate.MIN_X)) + Coordinate.MIN_X,
                (int)(Math.random() * (Coordinate.MAX_Y - Coordinate.MIN_Y)) + Coordinate.MIN_Y);
        for (Coordinate c: solarSystemCoordinates) {
            if (c.getDistance(newCoordinate) < distance) {
                return generateNewCoordinate(--maxAttempts);
            }
        }
        solarSystemCoordinates.add(newCoordinate);
        return newCoordinate;
    }

    @Override
    public String toString() {
        String returnString = "Universe:\n";
        for (SolarSystem s: solarSystems) {
            returnString += "\t" + s.toString() + "\n";
        }
        return returnString;
    }
}
