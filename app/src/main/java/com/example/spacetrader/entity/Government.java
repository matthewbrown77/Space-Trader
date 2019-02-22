package com.example.spacetrader.entity;

public enum Government {
    ANARCHY("Anarchy"), CAPITALIST("Capitalist"), COMMUNIST("Communist"),
    CONFEDERACY("Confederacy"), CORPORATE("Corporate"), CYBERNETIC("Cybernetic"),
    DEMOCRACY("Democracy"), DICTATORSHIP("Dictatorship"), FASCIST("Fascist"),
    FEUDAL("Feudal"), MILITARY("Military"), MONARCHY("Monarchy"),
    PACIFIST("Pacifist"), SOCIALIST("Socialist"), SATORI("Satori"),
    TECHNOCRACY("Technocracy"), THEOCRACY("Theocracy");

    private String name;

    Government(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
