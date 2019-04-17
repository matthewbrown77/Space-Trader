package com.example.spacetrader.entity;

public class AsteroidEncounter extends Encounter {

    /**
     * Creates a new asteroid encounter with the player
     * @param player player
     */
    public AsteroidEncounter(Player player) {
        this.player = player;
        this.finished = false;
        this.messageToDisplay = "While traveling through space, you encounter a group of asteroids.";
        this.options = new String[3];
        options[0] = "Turn Left";
        options[1] = "Maintain Course";
        options[2] = "Turn Right";
    }

    @Override
    public void doOption(int option) {
        if ("Turn Left".equals(options[option])) {
            messageToDisplay = "You turned left";
            getOutcome();
        } else if ("Turn Right".equals(options[option])) {
            messageToDisplay = "You turned right";
            getOutcome();
        } else {
            messageToDisplay = "You maintained your course";
            getOutcome();
        }
    }

    private void getOutcome() {
        if (Math.random() < 0.4) {
            finished = true;
        }
        for (int i = 0; i <= player.getFighterSkillPoints(); i++) {
            if (Math.random() < 0.1) {
                messageToDisplay += " and avoided the asteroid.";
                if (!finished) {
                    messageToDisplay += " There are more asteroids ahead!";
                } else {
                    messageToDisplay += " You have cleared the asteroid belt.";
                }
                return;
            }
        }
        int damage = (int)(Math.random() * 20);
        messageToDisplay += ", but an asteroid hit your ship causing " + damage + " damage.";
        player.deductShipHealth(damage);
        if (!finished) {
            messageToDisplay += " There are more asteroids ahead!";
        } else {
            messageToDisplay += " You have cleared the asteroid belt.";
        }
    }
}
