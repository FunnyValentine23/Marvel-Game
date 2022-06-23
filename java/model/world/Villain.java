package model.world;

import java.util.ArrayList;

public class Villain extends Champion{

    public Villain(String name, int maxHP, int mana, int maxActionPoints, int speed, int attackRange, int attackDamage) {
        super(name, maxHP, mana, maxActionPoints, speed, attackRange, attackDamage);
    }

    @Override
    public void useLeaderAbility(ArrayList<Champion> targets) {
        for (Champion target: targets) {
            int h = target.getCurrentHP() / target.getMaxHP();
            int f = (int) (target.getMaxHP() * 0.3);
            if(h < f) {
                target.setCondition(Condition.KNOCKEDOUT);
            }
        }
    }
}
