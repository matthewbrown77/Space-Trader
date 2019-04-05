package com.example.spacetrader.entity;

import java.io.Serializable;

/**
 * Coordinate class represents an X,Y location in the Universe.
 */
public class Coordinate implements Serializable {

    public static final int MIN_X = 0;
    public static final int MAX_X = 100;
    public static final int MIN_Y = 0;
    public static final int MAX_Y = 100;

    private final int x;
    private final int y;

    /**
     * Constructor for the coordinate
     * If the desired coordinate is out of bounds, a coordinate will be
     * created to the closest point in bounds.
     * @param x X Coordinate
     * @param y Y Coordinate
     */
    public Coordinate(int x, int y) {
        this.x = Math.min(Math.max(MIN_X, x), MAX_X);
        this.y = Math.min(Math.max(MIN_Y, y), MAX_Y);
    }

    /**
     * Gets the distance from this coordinate to the parameter coordinate
     * @param c parameter coordinate
     * @return distance (in coordinate units) between two coordinates
     */
    public double getDistance(Coordinate c) {
        return Math.sqrt(Math.pow((x - c.x), 2) + Math.pow((y - c.y), 2));
    }

    /**
     * Gets the x coordinate
     * @return x coordinate
     */
    public int getX(){
        return x;
    }

    /**
     * Gets the y coordinate
     * @return y coordinate
     */
    public int getY(){
        return y;
    }

    /**
     * Gets the center coordinate of the grid. Used to determine the origin solarSystem.
     * @return center coordinate of the grid
     */
    public static Coordinate getCenterCoordinate() {
        return new Coordinate(((MAX_X - MIN_X) / 2) + MIN_X, ((MAX_Y - MIN_Y) / 2) + MIN_Y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Coordinate)) {
            return false;
        }
        Coordinate coordinate = (Coordinate)o;
        return ((coordinate.x == this.x) && (coordinate.y == this.y));
    }

    @Override
    public int hashCode() {
        int result = 37;
        result = (31 * result) + x;
        result = (31 * result) + y;
        return result;
    }
}

