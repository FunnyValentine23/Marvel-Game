package model.effects;

import model.world.Champion;
import model.world.Condition;

public class Root extends Effect{

    public Root(int duration) {
        super("Root", duration, EffectType.DEBUFF);
    }

    @Override
    public void apply(Champion c){
        if(!(c.getCondition() == Condition.INACTIVE))
            c.setCondition(Condition.ROOTED);
    }

    @Override
    public void remove(Champion c){
        if(!(c.getCondition() == Condition.INACTIVE)){
            int cnt = 0;
            for(Effect e: c.getAppliedEffects()){
                if(e.getName().compareTo("Root") == 0){
                    ++cnt;
                }
            }
            if(cnt > 0)
                c.setCondition(Condition.ROOTED);
            else
                c.setCondition(Condition.ACTIVE);
        }
    }
}
