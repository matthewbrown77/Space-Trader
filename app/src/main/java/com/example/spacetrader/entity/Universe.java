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

    /**
     * Creates a universe with 10 solar systems.
     */
    public Universe() {
        solarSystems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            solarSystems.add(new SolarSystem(generateNewCoordinate()));
        }
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
