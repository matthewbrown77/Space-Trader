package com.example.spacetrader.entity;

/**
 * The TechLevel Enum represents the sophistication of the planet. These are ranked
 * from pre-agriculture to hi-tech. The TechLevel helps to determine which goods can be
 * bought and sold at each planet and their prices.
 */
public enum TechLevel {
    PRE_AGRICULTURE("Pre-Agriculture", 0), AGRICULTURE("Agriculture", 1),
    MEDIEVAL("Medieval", 2), RENAISSANCE("Renaissance", 3),
    EARLY_INDUSTRIAL("Early-Industrial", 4), INDUSTRIAL("Industrial", 5),
    POST_INDUSTRIAL("Post-Industrial", 6), HI_TECH("Hi-Tech", 7);

    /**
     * Name of the techLevel
     */
    private String name;

    /**
     * Integer representation of the techLevel used in calculations (0 - 7)
     */
    private int level;

    /**
     * TechLevel constructor
     * @param name
     * @param level
     */
    TechLevel(String name, int level) {
        this.name = name;
        this.level = level;
    }

    /**
     * Gets the integer representation of the techLevel (0 - 7)
     * @return int level
     */
    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return name;
    }

}