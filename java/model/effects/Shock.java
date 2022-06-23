package model.effects;

import model.world.Champion;

public class Shock extends Effect{

    public Shock(int duration) {
        super("Shock", duration, EffectType.DEBUFF);
    }

    @Override
    public void apply(Champion c) {
        c.setSpeed((int)(c.getSpeed()-c.getSpeed()*0.10));
        c.setAttackDamage((int) (c.getAttackDamage()-c.getAttackDamage()*0.10));
        c.setMaxActionPointsPerTurn(c.getMaxActionPointsPerTurn()-1);
        c.setCurrentActionPoints(c.getCurrentActionPoints()-1);
    }

    @Override
    public void remove(Champion c) {
        c.setSpeed((int)(c.getSpeed()/0.90));
        c.setAttackDamage((int)(c.getAttackDamage()/0.90));
        c.setMaxActionPointsPerTurn(c.getMaxActionPointsPerTurn()+1);
        c.setCurrentActionPoints(c.getCurrentActionPoints()+1);
    }
}

