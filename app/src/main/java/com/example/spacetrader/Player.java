package com.example.spacetrader;

public class Player {

    public final int skillPoints = 16;

    private String name;
    private int pilotSkillPoints;
    private int fighterSkillPoints;
    private int traderSkillPoints;
    private int engineerSkillPoints;
    private int credits;
    private Ship spaceship; //will implement spaceship class in future sprint

    public Player() {
        this.pilotSkillPoints = 0;
        this.fighterSkillPoints = 0;
        this.traderSkillPoints = 0;
        this.engineerSkillPoints = 0;
        this.credits = 1000;
        this.spaceship = new Ship(ShipType.GNAT);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void incrementPilotSkillPoints() {
        if (getSkillSum() < skillPoints) {
            pilotSkillPoints++;
        }
    }
    public void incrementFighterSkillPoints() {
        if (getSkillSum() < skillPoints) {
            fighterSkillPoints++;
        }
    }
    public void incrementTraderSkillPoints() {
        if (getSkillSum() < skillPoints) {
            traderSkillPoints++;
        }
    }
    public void incrementEngineerSkillPoints() {
        if (getSkillSum() < skillPoints) {
            engineerSkillPoints++;
        }
    }

    public void decrementPilotSkillPoints() {
        if (pilotSkillPoints > 0) {
            pilotSkillPoints--;
        }
    }
    public void decrementFighterSkillPoints() {
        if (fighterSkillPoints > 0) {
            fighterSkillPoints--;
        }
    }
    public void decrementTraderSkillPoints() {
        if (traderSkillPoints > 0) {
            traderSkillPoints--;
        }
    }
    public void decrementEngineerSkillPoints() {
        if (engineerSkillPoints > 0) {
            engineerSkillPoints--;
        }
    }

    private int getSkillSum(){
        return pilotSkillPoints + fighterSkillPoints + traderSkillPoints + engineerSkillPoints;
    }

    public int getRemainingCount() {
        return skillPoints - getSkillSum();
    }

    public int getPilotSkillPoints() {return pilotSkillPoints;}
    public int getFighterSkillPoints() {return fighterSkillPoints;}
    public int getTraderSkillPoints() {return traderSkillPoints;}
    public int getEngineerSkillPoints() {return engineerSkillPoints;}

    public String toString() {
        return "Player name: " + this.name + "\n"
                + "Pilot Skill Points: " + this.pilotSkillPoints + "\n"
                + "Fighter Skills Points: " + this.fighterSkillPoints + "\n"
                + "Trader Skills Points: " + this.traderSkillPoints + "\n"
                + "Engineer Skills Points: " + this.engineerSkillPoints + "\n"
                + "Credits: " + this.credits + "\n"
                + "Spaceship: " + this.spaceship + "\n";
    }

}
