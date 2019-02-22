package com.example.spacetrader.entity;

/**
 * Game class to store all information regarding the gameplay.
 *
 */
public class Game {

    private Player player;
    private Universe universe;

    public Game (Player player) {
        this.player = player;
        this.universe = new Universe();
    }

    public String toString() {
        return "GAME: " + player.toString();
    }

}
