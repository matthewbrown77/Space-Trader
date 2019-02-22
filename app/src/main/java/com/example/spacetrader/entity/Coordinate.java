package com.example.spacetrader.entity;

public class Coordinate {

    public final int MIN_X = -100;
    public final int MAX_X = 100;
    public final int MIN_Y = -100;
    public final int MAX_Y = 100;

    // private static Coordinate

    private int x;
    private int y;

    /**
     * Constructor for the coordinate
     * If desired coordinate is out of bounds, a coordinate will be
     * created to the closest point in bounds.
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
}

