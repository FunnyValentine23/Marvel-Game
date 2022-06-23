package model.world;

import model.effects.Effect;
import model.effects.EffectType;
import model.effects.Embrace;
import model.effects.Stun;

import java.util.ArrayList;

public class AntiHero extends Champion{

    public AntiHero(String name, int maxHP, int mana, int maxActionPoints, int speed, int attackRange, int attackDamage) {
        super(name, maxHP, mana, maxActionPoints, speed, attackRange, attackDamage);
    }

    @Override
    public void useLeaderAbility(ArrayList<Champion> targets) throws CloneNotSupportedException {
        for(Champion c: targets){
            Effect s = new Stun(2);
            c.getAppliedEffects().add(s);
            s.apply(c);
        }
    }
}
