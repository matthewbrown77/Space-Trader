package com.example.spacetrader.entity;

import java.util.HashSet;

public class Coordinate {

    public final static int MIN_X = -100;
    public final static int MAX_X = 100;
    public final static int MIN_Y = -100;
    public final static int MAX_Y = 100;

    private int x;
    private int y;

    /**
     * Constructor for the coordinate
     * If desired coordinate is out of bounds, a coordinate will be
     * created to the closest point in bounds.
     * If the desired coordinate already exists, a random coordinate
     * will be generated
     * @param x X Coordinate
     * @param y Y Coordinate
     */
    public Coordinate(int x, int y) {
        this.x = Math.min(Math.max(MIN_X, x), MAX_X);
        this.y = Math.min(Math.max(MIN_Y, y), MAX_Y);
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
        return (coordinate.x == this.x && coordinate.y == this.y);
    }

    @Override
    public int hashCode() {
        int result = 37;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
}

