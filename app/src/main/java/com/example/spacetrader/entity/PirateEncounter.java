package com.example.spacetrader.entity;

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
        this.messageToDisplay = "You encounter a pirate. Your opponent attacks you, but misses your ship.";
        this.options = new String[3];
        options[0] = "Attack";
        options[1] = "Flee";
        options[2] = "Surrender";
    }

    @Override
    public void doOption(int option) {
        if (options[option].equals("Attack")) attack();
        else if (options[option].equals("Flee")) flee();
        else if (options[option].equals("Surrender")) surrender();
        else if (options[option].equals("Plunder Ship")) plunder();
        else if (options[option].contains("credits")) seizeCredits();
        else if (options[option].equals("Kill pirate")) killPirate();
    }

    private void attack() {
        for (int i = 0; i <= player.getFighterSkillPoints(); i++) {
            if (Math.random() < 0.1) {
                opponentShip.deductShipHealth(5);
                messageToDisplay = "You hit the pirate. ";
                if (opponentShip.getShipHealth()/100.0 < Math.random()) {
                    messageToDisplay += "The pirate surrendered. ";
                    options[0] = "Plunder Ship";
                    options[1] = "Seize credits (" + (int) (player.getCredits() * Math.random() / 2) + ")";
                    options[2] = "Kill pirate";
                }
                finished = false;
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
        messageToDisplay = "You surrendered. ";
        if (player.getCurrentCargo() < 4) {
            int amount = (int)((4 - player.getCurrentCargo())/4.0 * player.getCredits());
            messageToDisplay += "The pirates took " + amount + " credits.";
            player.decrementCredits(amount);
        }
        finished = true;
    }

    private void plunder() {
        messageToDisplay = "You plundered the ship!";
        finished = true;
    }

    private void seizeCredits() {
        messageToDisplay = "You seized the pirate's credits";
        finished = true;
    }

    private void killPirate() {
        messageToDisplay = "You killed the pirate.";
        finished = true;
    }




}
