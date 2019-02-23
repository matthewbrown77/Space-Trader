package com.example.spacetrader.entity;

public enum TechLevel {
    PRE_AGRICULTURE("Pre-Agriculture", 0), AGRICULTURE("Agriculture", 1),
    MEDIEVAL("Medieval", 2), RENAISSANCE("Renaissance", 3),
    EARLY_INUDSTRIAL("Early-Industrial", 4), INDUSTRIAL("Industrial", 5),
    POST_INUDSTRIAL("Post-Industrial", 6), HI_TECH("Hi-Tech", 7);

    private String name;
    private int level;

    TechLevel(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public String toString() {
        return name;
    }

}