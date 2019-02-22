package com.example.spacetrader.entity;
import java.util.List;
import java.util.ArrayList;

public class Universe {

    private List<SolarSystem> solarSystems;

    public Universe() {
        solarSystems = new ArrayList<SolarSystem>();
        solarSystems.add(new SolarSystem("SS1", new Coordinate(0,0)));
    }

    @Override
    public String toString() {
        String returnString = "";
        for (SolarSystem s: solarSystems) {
            returnString += s.toString();
        }
        return returnString;
    }


}
