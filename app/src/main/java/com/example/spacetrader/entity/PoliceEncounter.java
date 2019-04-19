package com.example.spacetrader.entity;

import java.io.Serializable;

/**
 * Represents an encounter with the police
 */
public class PoliceEncounter extends Encounter {

    /**
     * Creates a new police encounter with the player
     * @param player player
     */
    public PoliceEncounter(Player player) {
        this.player = player;
        this.finished = false;
        this.opponentShip = new Ship(ShipType.GNAT);
        this.messageToDisplay = "You encounter a police ship. They would like to search your ship.";
        this.options = new String[3];
        options[0] = "Consent";
        options[1] = "Flee";
        options[2] = "Bribe";
    }

    @Override
    public String getTitle() {
        return "Police Encounter";
    }

    @Override
    public void doOption(int option) {
        if ("Consent".equals(options[option])) {
            consent();
        } else if ("Flee".equals(options[option])) {
            flee();
        } else {
            bribe();
        }
    }

    private void bribe() {
        messageToDisplay = "You choose to bribe the police. ";
        int amount = Math.min(10000, (int) (player.getCredits() * 0.9));
        messageToDisplay = "The police were incorruptible and did not accept"
                + " your bribe. They fined you " + amount + " credits.";
        player.decrementCredits(amount);
        finished = true;
    }

    private void flee() {
        for (int i = 0; i <= player.getPilotSkillPoints(); i++) {
            if (Math.random() < 0.1) {
                messageToDisplay = "You escaped from the police!";
                finished = true;
                return;
            }
        }
        messageToDisplay = "You attempted to flee, but the police ship is still following you";
        finished = false;
    }

    private void consent() {
        messageToDisplay = "You consented to a search. ";
        boolean hasFirearms = player.getCargoCount(Resource.FIREARMS) > 0;
        boolean hasNarcotics = player.getCargoCount(Resource.NARCOTICS) > 0;
        if (hasFirearms || hasNarcotics) {
            messageToDisplay += "The police found ";
            if (hasFirearms) {
                player.removeAllCargo(Resource.FIREARMS);
                messageToDisplay += "firearms";

            }
            if (hasNarcotics) {
                player.removeAllCargo(Resource.NARCOTICS);
                if (hasFirearms) {
                    messageToDisplay += " and ";
                }
                messageToDisplay += "narcotics";
            }
            int amount =  Math.min(5000, (int) (player.getCredits() * Math.random()));
            messageToDisplay += " on your ship. They seized these items and fined you " + amount + " credits.";
            player.decrementCredits(amount);
        } else {
            messageToDisplay += "The police found no suspicious items.";
        }
        finished = true;
    }
}
