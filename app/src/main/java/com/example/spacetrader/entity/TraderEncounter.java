package com.example.spacetrader.entity;

/**
 * Represents an encounter with a trader
 */
public class TraderEncounter extends Encounter {

    /**
     * Creates a new police encounter with the player
     * @param player player
     */
    public TraderEncounter(Player player) {
        this.player = player;
        this.finished = false;
        this.opponentShip = new Ship(ShipType.GNAT);
        this.messageToDisplay = "You encounter a trader. They would like to trade with you.";
        this.options = new String[3];
        options[0] = "Trade";
        options[1] = "Attack";
        options[2] = "Ignore";
    }

    @Override
    public void doOption(int option) {
        if ("Trade".equals(options[option])) {
            trade();
        } else if ("Attack".equals(options[option])) {
            attack();
        } else {
            ignore();
        }
    }

    private void trade() {
        messageToDisplay = "You elected to trade.";
        finished = true;
    }

    private void attack() {
        messageToDisplay = "You attacked the trader";
        finished = true;
    }

    private void ignore() {
        messageToDisplay = "You ignored the trader, and continued on your way.";
        finished = true;
    }
}
