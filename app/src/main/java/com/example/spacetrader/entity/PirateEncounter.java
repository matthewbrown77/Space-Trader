package com.example.spacetrader.entity;

import java.io.Serializable;

/**
 * Represents an encounter with a pirate
 */
public class PirateEncounter extends Encounter {

    /**
     * Creates a new pirate encounter with the player
     * @param player
     */
    public PirateEncounter(Player player) {
        this.player = player;
        this.finished = false;
        this.opponentShip = new Ship(ShipType.GNAT);
        this.messageToDisplay = "You encounter a pirate. Your opponent attacks you, but misses " +
                "your ship.";
        this.options = new String[3];
        options[0] = "Attack";
        options[1] = "Flee";
        options[2] = "Surrender";
    }

    @Override
    public void doOption(int option) {
        if ("Attack".equals(options[option])) {
            attack();
        } else if ("Flee".equals(options[option])) {
            flee();
        } else if ("Surrender".equals(options[option])) {
            surrender();
        }
    }

    private void attack() {
        for (int i = 0; i <= player.getFighterSkillPoints(); i++) {
            if (Math.random() < 0.1) {
                opponentShip.deductShipHealth(5);
                messageToDisplay = "You hit the pirate. ";
                finished = false;
                if ((opponentShip.getShipHealth() / 100.0) < Math.random()) {
                    messageToDisplay += "The pirate fled. ";
                    finished = true;
                }
                return;
            }
        }
        messageToDisplay = "You attacked, but missed the pirate ship.";
        if (Math.random() < 0.4) {
            messageToDisplay += "The pirate attacked and hit you!";
            player.deductShipHealth(5);
        } else {
            messageToDisplay += "The pirate attacked and missed you!";
        }
        finished = false;
    }

    private void flee() {
        for (int i = 0; i <= player.getPilotSkillPoints(); i++) {
            if (Math.random() < 0.1) {
                messageToDisplay = "You escaped from the pirate!";
                finished = true;
                return;
            }
        }
        messageToDisplay = "The attempted to flee, but the pirate is still following you";
        finished = false;
    }

    private void surrender() {
        messageToDisplay = "You surrendered. The pirates took";
        if (player.getCurrentCargo() > 0) {
            messageToDisplay += " all of your cargo";
            if (player.getCurrentCargo() < 4) {
                messageToDisplay += " and";
            }
        }
        if (player.getCurrentCargo() < 4) {
            int amount = (int) (((4 - player.getCurrentCargo()) / 4.0) * player.getCredits()
                    * Math.random());
            messageToDisplay += " " + amount + " credits.";
            player.decrementCredits(amount);
        } else {
            messageToDisplay += ".";
        }
        player.clearCargo();
        finished = true;
    }



}
