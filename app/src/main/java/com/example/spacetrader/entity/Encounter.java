package com.example.spacetrader.entity;

/**
 * Abstract class for an encounter while traveling.
 */
public abstract class Encounter {

    protected Player player;
    protected String messageToDisplay;
    protected String[] options;
    protected boolean finished;
    protected Ship opponentShip;

    /**
     * Gets the message to display for this encounter. Can change based on interaction.
     * @return String message
     */
    public String getMessage() {
        return messageToDisplay;
    }

    /**
     * Gets strings of the three options the player has during the encounter
     * @return list of three strings
     */
    public String[] getThreeOptions() {
        return options;
    }

    /**
     * Determines if the encounter is finished, and should be closed
     * @return true if the encounter is finished, false otherwise
     */
    public boolean finished() {
        return finished;
    }

    /**
     * Gets the opponent's ship health
     * @return opponent's ship health [0-100]
     */
    public int getOpponentShipHealth() {
        return opponentShip.getShipHealth();
    }

    /**
     * Does the option selected by the player
     * @param option int [0 - 2]
     */
    public abstract void doOption(int option);

}
