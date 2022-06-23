package model.abilities;

import model.world.Damageable;

import java.util.ArrayList;

abstract public class Ability {

    private String name;
    private int manaCost;
    private int baseCooldown;
    private int currentCooldown;
    private int castRange;
    private int requiredActionPoints;
    private AreaOfEffect castArea;

    public Ability(String name, int cost, int baseCooldown, int castRange, AreaOfEffect castArea, int requiredActionPointsPerTurn){
        this.name = name;
        this.manaCost = cost;
        this.baseCooldown = baseCooldown;
        this.castRange = castRange;
        this.requiredActionPoints = requiredActionPointsPerTurn;
        this.castArea = castArea;
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getBaseCooldown() {
        return baseCooldown;
    }

    public int getCurrentCooldown() {
        return currentCooldown;
    }

    public int getCastRange() {
        return castRange;
    }

    public int getRequiredActionPoints() {
        return requiredActionPoints;
    }

    public AreaOfEffect getCastArea() {
        return castArea;
    }

    public void setCurrentCooldown(int currentCooldown) {
        this.currentCooldown = currentCooldown;
        if(this.currentCooldown < 0)
            this.currentCooldown = 0;
        if(this.currentCooldown > baseCooldown)
            this.currentCooldown = baseCooldown;
    }
    abstract public void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException;
}
