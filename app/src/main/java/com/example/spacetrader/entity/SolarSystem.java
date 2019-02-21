package com.example.spacetrader.entity;
import android.graphics.Point;

import com.example.spacetrader.entity.Planet;

public class SolarSystem {
    private String name;
    private Point coordinates;
    private Planet planet;

    /**
     * Constructor for the Solar System
     * @param name Solar System name
     * @param coordinates Location of the Solar System.
     */
    public SolarSystem(String name, Point coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.planet = new Planet(name);
    }


}
