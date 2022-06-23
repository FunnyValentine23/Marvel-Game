package model.effects;

import model.world.Champion;
import model.world.Condition;

public class Stun extends Effect{

    public Stun(int duration) {
        super("Stun", duration, EffectType.DEBUFF);
    }

    @Override
    public void apply(Champion c){
        c.setCondition(Condition.INACTIVE);
    }

    @Override
    public void remove(Champion c) {
        int stun = 0, root = 0;
        for (Effect e : c.getAppliedEffects()) {
            if(e.getName().compareTo("Stun") == 0)
                ++stun;
            if (e.getName().compareTo("Root") == 0) {
                ++root;
            }
        }
        if(stun > 0){
            c.setCondition(Condition.INACTIVE);
        }else if(root > 0){
            c.setCondition(Condition.ROOTED);
        }else{
            c.setCondition(Condition.ACTIVE);
        }
    }
}
