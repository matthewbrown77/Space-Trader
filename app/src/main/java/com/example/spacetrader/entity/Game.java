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

    public String getPlayerShipName() {
        return player.getShipName();
    }

    public int [] getPlayerSkillPointsArray() {
        return player.getSkillPointsArray();
    }

    public String getPlayerName() {
        return player.getName();
    }

    public int getPlayerCredits() {
        return player.getCredits();
    }

    public String toString() {
        return "GAME: " + player.toString();
    }

}
