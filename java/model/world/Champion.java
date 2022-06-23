package model.world;

import java.awt.*;
import java.util.*;
import model.abilities.*;
import model.effects.*;

abstract public class Champion implements Damageable, Comparable {

    private String name;
    private int maxHP;
    private int currentHP;
    private int maxMana;
    private int mana;
    private int maxActionPointsPerTurn;
    private int currentActionPoints;
    private int attackRange;
    private int attackDamage;
    private int speed;
    private ArrayList<Ability> abilities;
    private ArrayList<Effect> appliedEffects;
    private Condition condition;
    private Point location;

    public Champion(String name, int maxHP, int mana, int maxActionPointsPerTurn, int speed, int attackRange, int attackDamage) {
        this.name = name;
        this.maxHP = maxHP;
        this.maxMana = mana;
        this.mana = mana;
        this.maxActionPointsPerTurn = maxActionPointsPerTurn;
        this.speed = speed;
        this.attackRange = attackRange;
        this.attackDamage = attackDamage;
        this.currentHP = maxHP;
        if(this.currentHP < 0)
            this.currentHP = 0;
        if(this.currentHP > maxHP)
            this.currentHP = maxHP;
        this.currentActionPoints = maxActionPointsPerTurn;
        if(this.currentActionPoints < 0)
            this.currentActionPoints = 0;
        if(this.currentActionPoints > maxActionPointsPerTurn)
            this.currentActionPoints = maxActionPointsPerTurn;
        abilities = new ArrayList<>();
        appliedEffects = new ArrayList<>();
        condition = Condition.ACTIVE;
    }

    public int compareTo(Object o) {
        Champion c = (Champion)o;
        if(speed==c.speed)
            return name.compareTo(c.name);
        return -1 * (speed-c.speed);
    }

    abstract public void useLeaderAbility(ArrayList<Champion> targets) throws CloneNotSupportedException;

    public String getName() {
        return name;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
        if(this.currentHP < 0)
            this.currentHP = 0;
        if(this.currentHP > maxHP)
            this.currentHP = maxHP;
    }

    public int getMaxMana(){
        return maxMana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxActionPointsPerTurn() {
        return maxActionPointsPerTurn;
    }

    public void setMaxActionPointsPerTurn(int maxActionPointsPerTurn) {
        this.maxActionPointsPerTurn = maxActionPointsPerTurn;
    }

    public int getCurrentActionPoints() {
        return currentActionPoints;
    }

    public void setCurrentActionPoints(int currentActionPoints) {
        this.currentActionPoints = currentActionPoints;
        if(this.currentActionPoints < 0)
            this.currentActionPoints = 0;
        if(this.currentActionPoints > maxActionPointsPerTurn)
            this.currentActionPoints = maxActionPointsPerTurn;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public ArrayList<Effect> getAppliedEffects() {
        return appliedEffects;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

}
