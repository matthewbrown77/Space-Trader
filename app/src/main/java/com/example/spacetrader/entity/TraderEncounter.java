package com.example.spacetrader.entity;

/**
 * Represents an encounter with a trader
 */
public class TraderEncounter extends Encounter {

    private Resource randomItem;
    private Resource counterResource;
    private int credits;

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
    public String getTitle() {
        return "Trader Encounter";
    }

    @Override
    public void doOption(int option) {
        if ("Trade".equals(options[option])) {
            trade();
        } else if ("Attack".equals(options[option])) {
            attack();
        } else if ("Ignore".equals(options[option])){
            ignore();
        } else if ("Surrender".equals(options[option])) {
            surrender();
        } else if ("Flee".equals(options[option])) {
            flee();
        } else if (options[option].contains("credits")) {
            acceptCredits();
        } else if (options[option].contains("Accept")) {
            acceptResource();
        } else if ("Decline".equals(options[option])) {
            decline();
        }
    }

    private void trade() {
        messageToDisplay = "You elected to trade.";
        if (player.getCurrentCargo() == 0) {
            messageToDisplay += " The trader scanned your ship for items, but noticed" +
                    " that you didn't have any. The trader left. ";
            finished = true;
        } else {
            randomItem = player.getRandomCargoItem();
            counterResource = Resource.values()[(int)(Math.random() * Resource.values().length)];
            while (counterResource.equals(randomItem)) {
                counterResource = Resource.values()[(int)(Math.random() * Resource.values().length)];
            }
            double creditsYouPaid = player.getAvgPrice(randomItem);
            credits = (int)(creditsYouPaid + Math.random() * creditsYouPaid - Math.random() * creditsYouPaid);

            messageToDisplay += " The trader scanned your ship for items and noticed" +
                    " that you have " + randomItem.toString() + ". The trader is willing" +
                    " to give you " +  credits + " credits for 1 " + randomItem.toString() + " item (you paid " +
                    creditsYouPaid + " for your " + randomItem.toString() + "). ";
            messageToDisplay += "The trader has also offered to give you 1 unit of " + counterResource.toString() +
                    " for " + player.getCargoCount(randomItem) + " of your " + randomItem.toString() +
                    " items.";
            finished = false;
            options[0] = "Accept " + credits + " credits";
            options[1] = "Accept " + counterResource.toString();
            options[2] = "Decline";
        }
    }

    private void acceptCredits() {
        messageToDisplay = "You accepted to take " + credits + " credits. The trader left.";
        player.incrementCredits(credits);
        player.removeCargo(randomItem, 0);
        finished = true;
    }

    private void acceptResource() {
        messageToDisplay = "You accepted to take " + counterResource + ". The trader left.";
        player.removeCargo(randomItem, 0);
        player.addCargo(counterResource, 0);
        finished = true;
    }

    private void decline() {
        messageToDisplay = "You declined the trader's offer. The trader left.";
        finished = true;
    }

    private void attack() {
        options[0] = "Attack";
        options[1] = "Flee";
        options[2] = "Surrender";
        for (int i = 0; i <= player.getFighterSkillPoints(); i++) {
            if (Math.random() < 0.1) {
                opponentShip.deductShipHealth(5);
                messageToDisplay = "You hit the trader. ";
                finished = false;
                if ((opponentShip.getShipHealth() / 100.0) < Math.random()) {
                    messageToDisplay += "The trader fled. ";
                    finished = true;
                }
                return;
            }
        }
        messageToDisplay = "You attacked, but missed the trader ship.";
        if (Math.random() < 0.4) {
            messageToDisplay += "The trader attacked and hit you!";
            player.deductShipHealth(5);
        } else {
            messageToDisplay += "The trader attacked and missed you!";
        }
        finished = false;
    }

    private void surrender() {
        messageToDisplay = "You surrendered. The trader took";
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

    private void flee() {
        for (int i = 0; i <= player.getPilotSkillPoints(); i++) {
            if (Math.random() < 0.1) {
                messageToDisplay = "You escaped from the trader!";
                finished = true;
                return;
            }
        }
        messageToDisplay = "The attempted to flee, but the trader is still following you";
        finished = false;
    }

    private void ignore() {
        messageToDisplay = "You ignored the trader, and continued on your way.";
        finished = true;
    }
}
