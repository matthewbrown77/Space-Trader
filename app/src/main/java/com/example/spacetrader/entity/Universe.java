package com.example.spacetrader.entity;
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

    private SolarSystem originSolarSystem;
    private Planet originPlanet;

    /**
     * Creates a universe with 10 solar systems, with 1 at the origin.
     */
    public Universe() {
        solarSystems = new ArrayList<>();
        originSolarSystem = new SolarSystem(new Coordinate(Coordinate.MAX_X/2,Coordinate.MAX_Y/2));
        originPlanet = originSolarSystem.getPlanets().get(0);
        solarSystems.add(originSolarSystem);
        for (int i = 0; i < 9; i++) {
            solarSystems.add(new SolarSystem(generateNewCoordinate()));
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
     * Generates a unique coordinate not already used by another SolarSystem
     * @return Coordinate
     */
    private Coordinate generateNewCoordinate() {
        Coordinate newCoordinate = new Coordinate(
                (int)(Math.random() * (Coordinate.MAX_X - Coordinate.MIN_X)) + Coordinate.MIN_X,
                (int)(Math.random() * (Coordinate.MAX_Y - Coordinate.MIN_Y)) + Coordinate.MIN_Y);
        if (solarSystemCoordinates.contains(newCoordinate)) {
            return generateNewCoordinate();
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
