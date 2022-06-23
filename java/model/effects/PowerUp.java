package model.effects;

import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.Champion;

public class PowerUp extends Effect{

    public PowerUp(int duration) {
        super("PowerUp", duration, EffectType.BUFF);
    }

    @Override
    public void apply(Champion c){
        int i = 0;
        for(; i < c.getAbilities().size(); ++i){
            if(c.getAbilities().get(i) instanceof DamagingAbility){
                int dmg = ((DamagingAbility) c.getAbilities().get(i)).getDamageAmount();
                ((DamagingAbility) c.getAbilities().get(i)).setDamageAmount((int) (dmg+dmg*0.2));
            }
            if(c.getAbilities().get(i) instanceof HealingAbility){
                int heal = ((HealingAbility) c.getAbilities().get(i)).getHealAmount();
                ((HealingAbility) c.getAbilities().get(i)).setHealAmount((int) (heal+heal*0.2));
            }
        }

    }

    @Override
    public void remove(Champion c){
        int i = 0;
        for(; i < c.getAbilities().size(); ++i){
            if(c.getAbilities().get(i) instanceof DamagingAbility){
                int dmg = ((DamagingAbility) c.getAbilities().get(i)).getDamageAmount();
                ((DamagingAbility) c.getAbilities().get(i)).setDamageAmount((int)(dmg/1.20));
            }
            if(c.getAbilities().get(i) instanceof HealingAbility){
                int heal = ((HealingAbility) c.getAbilities().get(i)).getHealAmount();
                ((HealingAbility) c.getAbilities().get(i)).setHealAmount((int)(heal/1.20));
            }
        }
    }
}

