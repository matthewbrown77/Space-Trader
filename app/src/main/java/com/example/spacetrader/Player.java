package com.example.spacetrader;

public class Player {

    private String name;
    private int pilotSkillPoints;
    private int fighterSkillPoints;
    private int traderSkillPoints;
    private int engineerSkillPoints;
    private String difficulty; //should change to enum if we choose to implement difficulty levels

    public Player() {
        this.pilotSkillPoints = 0;
        this.fighterSkillPoints = 0;
        this.traderSkillPoints = 0;
        this.engineerSkillPoints = 0;
    }

    public void incrementPilotSkillPoints() { pilotSkillPoints++; }
    public void incrementFighterSkillPoints() { fighterSkillPoints++; }
    public void incrementTraderSkillPoints() { traderSkillPoints++; }
    public void incrementEngineerSkillPoints() { engineerSkillPoints++; }

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

    public int getPilotSkillPoints() {return pilotSkillPoints;}
    public int getFighterSkillPoints() {return fighterSkillPoints;}
    public int getTraderSkillPoints() {return traderSkillPoints;}
    public int getEngineerSkillPoints() {return engineerSkillPoints;}

}
