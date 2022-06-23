package model.world;

import model.effects.Effect;
import model.effects.EffectType;
import model.effects.Embrace;

import java.util.ArrayList;

public class Hero extends Champion {

    public Hero(String name, int maxHP, int mana, int maxActionPoints, int speed, int attackRange, int attackDamage) {
        super(name, maxHP, mana, maxActionPoints, speed, attackRange, attackDamage);
    }

    @Override
    public void useLeaderAbility(ArrayList<Champion> targets) throws CloneNotSupportedException {
        for (Champion target : targets) {
            ArrayList<Effect> e = target.getAppliedEffects();
            for (int i = 0; i < e.size(); ++i) {
                if (e.get(i).getType() == EffectType.DEBUFF) {
                    e.get(i).remove(target);
                    e.remove(e.get(i));
                    --i;
                }
            }
            new Embrace(2).apply(target);
            e.add(new Embrace(2));
        }
    }
}
