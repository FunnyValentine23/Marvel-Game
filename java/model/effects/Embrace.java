package model.effects;

import model.world.Champion;

public class Embrace extends Effect{

    public Embrace(int duration) {
        super("Embrace", duration, EffectType.BUFF);
    }

    @Override
    public void apply(Champion c) {
        c.setCurrentHP((int)(c.getMaxHP()*0.2) + c.getCurrentHP());
        c.setMana((int)(c.getMana()*1.2));
        c.setSpeed(c.getSpeed() + (int)(c.getSpeed()*0.2));
        c.setAttackDamage(c.getAttackDamage() + (int)(c.getAttackDamage()*0.2));
    }

    @Override
    public void remove(Champion c){
        c.setAttackDamage((int)(c.getAttackDamage()/1.20));
        c.setSpeed((int)(c.getSpeed()/1.20));
    }

}
