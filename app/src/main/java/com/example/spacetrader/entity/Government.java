package com.example.spacetrader.entity;

/**
 * The Government enum represents the government type of a planet.
 */
public enum Government {
    ANARCHY("Anarchy", 2, 6, 10.0), CAPITALIST("Capitalist", 4, 7, -0.4), COMMUNIST("Communist", 4, 6, 1.0),
    CONFEDERACY("Confederacy", 2, 7, 0.2), CORPORATE("Corporate", 5, 7, -0.3), CYBERNETIC("Cybernetic", 7, 7, 0.2),
    DEMOCRACY("Democracy", 2, 7, -0.3), DICTATORSHIP("Dictatorship", 2, 6, 0.5), FASCIST("Fascist", 4, 7, 0.3),
    FEUDAL("Feudal", 1, 3,  0.4), MILITARY("Military", 3, 7, 0.2), MONARCHY("Monarchy", 2, 6, 0.1),
    PACIFIST("Pacifist", 1, 3, -0.3), SOCIALIST("Socialist", 1, 4, 1.3), SATORI("Satori", 1, 3,  -1.0),
    TECHNOCRACY("Technocracy", 7, 7, 0.2), THEOCRACY("Theocracy", 1, 3, -0.4), NONE("None", 0, 1, -1.0);

    private String name;
    private int minTechLevel;
    private int maxTechLevel;
    private double tradeFactor;

    /**
     * Government constructor
     * @param name
     * @param minTechLevel
     * @param maxTechLevel
     * @param tradeFactor
     */
    Government(String name, int minTechLevel, int maxTechLevel, double tradeFactor) {
        this.name = name;
        this.minTechLevel = minTechLevel;
        this.maxTechLevel = maxTechLevel;
        this.tradeFactor = tradeFactor;
    }

    /**
     * Gets tradeFactor for government. The trade factor makes goods more or less expensive.
     * e.g. 1.5 tradeFactor increases the good price by 1.5 * variance
     * e.g. -0.7 tradeFactor decreases the good prices by 0.7 * variance
     * @return tradeFactor
     */
    public double getTradeFactor() {
        return tradeFactor;
    }

    /**
     * Gets min techLevel required for government to exist
     * @return min techLevel
     */
    public int getMinTechLevel() {
        return minTechLevel;
    }

    /**
     * Gets max techLevel that government can exist in
     * @return max techLevel
     */
    public int getMaxTechLevel() {
        return maxTechLevel;
    }

    @Override
    public String toString() {
        return name;
    }
}
