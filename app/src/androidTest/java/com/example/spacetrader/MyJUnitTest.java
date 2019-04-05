package com.example.spacetrader;

import com.example.spacetrader.entity.Resource;
import com.example.spacetrader.entity.Ship;
import com.example.spacetrader.entity.ShipType;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class MyJUnitTest {

    private Ship ship;
    private Resource resourceToAdd;

    @Before
    public void setUp() {
        ship = new Ship(ShipType.values()[new Random().nextInt(ShipType.values().length)]);
        resourceToAdd = Resource.values()[new Random().nextInt(Resource.values().length)];
    }

    @Test
    public void testNull() {
        resourceToAdd = null;
        assertFalse(ship.addCargo(resourceToAdd));
        assertEquals(ship.getCurrentCargo(), 0);
    }

    @Test
    public void testAddFirstResource() {
        assertTrue(ship.addCargo(resourceToAdd));
        assertEquals(ship.getCargoCount(resourceToAdd), 1);
        assertEquals(ship.getCurrentCargo(), 1);
    }

    @Test
    public void testAddSecondResource() {
        ship.addCargo(resourceToAdd);
        assertTrue(ship.addCargo(resourceToAdd));
        assertEquals(ship.getCargoCount(resourceToAdd), 2);
        assertEquals(ship.getCurrentCargo(), 2);
    }

    @Test
    public void testAddToFullShip() {
        while (ship.getCurrentCargo() < ship.getMaxCargo()) {
            ship.addCargo(resourceToAdd);
        }
        assertFalse(ship.addCargo(resourceToAdd));
        assertEquals(ship.getCargoCount(resourceToAdd), ship.getMaxCargo());
        assertEquals(ship.getCurrentCargo(), ship.getMaxCargo());
    }



}