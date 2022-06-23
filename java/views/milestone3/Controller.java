package views.milestone3;

import engine.Game;
import engine.Player;
import javafx.application.Application;
import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Controller {

    static class ChampNameLoc{
        String name;
        Point loc;
        public ChampNameLoc(String name, Point loc){
            this.name = name;
            this.loc = loc;
        }
    }

    static Game g;
    static Player player1;
    static Player player2;

    public static ArrayList<ChampNameLoc> getChampNameLoc(){
        ArrayList<ChampNameLoc> a = new ArrayList<>();
        for(int i = 0; i < 5; ++i){
            for(int j = 0; j < 5; ++j){
                if(g.getBoard()[i][j] instanceof Champion){
                    a.add(new ChampNameLoc(((Champion) g.getBoard()[i][j]).getName(), ((Champion) g.getBoard()[i][j]).getLocation()));
                }
            }
        }
        return a;
    }
    public static String leaderAbilityInfo() {
        String t = "";
        if (g.getFirstPlayer().getLeader() instanceof Hero) {
            t += "Removes all negative effects from the player’s entire team and adds an Embrace effect " +'\n'+
                    "to them which lasts for 2 turns.";
        } else if (g.getFirstPlayer().getLeader() instanceof Villain) {
            t += "Immediately eliminates (knocks out) all enemy champions with less than 30% health " +'\n'+
                    "points.";
        } else
            t += "All champions on the board except for the leaders of each team will be stunned " +'\n'+
                    "for 2 turns.";
        return t;
    }
    public static ArrayList<Cover> getCoverLoc(){
        ArrayList<Cover> a = new ArrayList<>();
        for(int i = 0; i < 5; ++i){
            for(int j = 0; j < 5; ++j){
                if(g.getBoard()[i][j] instanceof Cover){
                    a.add(((Cover) g.getBoard()[i][j]));
                }
            }
        }
        return a;
    }

    public static String team1Info(String s){
        String t = "";
        if(Controller.g.getFirstPlayer().getName().equals(s)) {
            for (int i = 0; i < g.getFirstPlayer().getTeam().size(); ++i) {
                t += "\n"+"Name : " + Controller.g.getFirstPlayer().getTeam().get(i).getName() + "\n";
                t += " HP : " + Controller.g.getFirstPlayer().getTeam().get(i).getCurrentHP() +
                        "/" + Controller.g.getFirstPlayer().getTeam().get(i).getMaxHP() + "\n";
                t += "Mana : " + Controller.g.getFirstPlayer().getTeam().get(i).getMana() + "\n";
                t += "Max Action Points: " + Controller.g.getFirstPlayer().getTeam().get(i).getMaxActionPointsPerTurn() + "\n";
                t += "Current Action Points: " + Controller.g.getFirstPlayer().getTeam().get(i).getCurrentActionPoints() + "\n";
                t += "Speed: " + Controller.g.getFirstPlayer().getTeam().get(i).getSpeed() + "\n";
                t += "Attack Range: " + Controller.g.getFirstPlayer().getTeam().get(i).getAttackRange() + "\n";
                t += "Attack Damage " + Controller.g.getFirstPlayer().getTeam().get(i).getAttackDamage() + "\n";
                t += "Ability 1: " + Controller.g.getFirstPlayer().getTeam().get(i).getAbilities().get(0).getName() + "-CoolDown: " +
                        Controller.g.getFirstPlayer().getTeam().get(i).getAbilities().get(0).getCurrentCooldown() + "\n";
                t += "Ability 2: " + Controller.g.getFirstPlayer().getTeam().get(i).getAbilities().get(1).getName() + "-CoolDown: " +
                        Controller.g.getFirstPlayer().getTeam().get(i).getAbilities().get(1).getCurrentCooldown() + "\n";
                t += "Ability 3: " + Controller.g.getFirstPlayer().getTeam().get(i).getAbilities().get(2).getName() + "-CoolDown: " +
                        Controller.g.getFirstPlayer().getTeam().get(i).getAbilities().get(2).getCurrentCooldown() + "\n";
                t += "Applied Effects: ";
                for (int j = 0; j < Controller.g.getFirstPlayer().getTeam().get(i).getAppliedEffects().size(); ++j) {
                    t += "" + Controller.g.getFirstPlayer().getTeam().get(i).getAppliedEffects().get(j).getName() +
                            "(" + Controller.g.getFirstPlayer().getTeam().get(i).getAppliedEffects().get(j).getDuration() + ")" + ", " +"\n";
                }
            }
        }
        return t;
    }
    public static String team2Info(String s){
        String t = "";
        if(Controller.g.getSecondPlayer().getName().equals(s)) {
            for (int i = 0; i < g.getSecondPlayer().getTeam().size(); ++i) {
                t += "\n"+"\n"+"Name : " + Controller.g.getSecondPlayer().getTeam().get(i).getName() + "\n";
                t += " HP : " + Controller.g.getSecondPlayer().getTeam().get(i).getCurrentHP() +
                        "/" + Controller.g.getSecondPlayer().getTeam().get(i).getMaxHP() + "\n";
                t += "Mana : " + Controller.g.getSecondPlayer().getTeam().get(i).getMana() + "\n";
                t += "Max Action Points: " + Controller.g.getSecondPlayer().getTeam().get(i).getMaxActionPointsPerTurn() + "\n";
                t += "Current Action Points: " + Controller.g.getSecondPlayer().getTeam().get(i).getCurrentActionPoints() + "\n";
                t += "Speed: " + Controller.g.getSecondPlayer().getTeam().get(i).getSpeed() + "\n";
                t += "Attack Range: " + Controller.g.getSecondPlayer().getTeam().get(i).getAttackRange() + "\n";
                t += "Attack Damage " + Controller.g.getSecondPlayer().getTeam().get(i).getAttackDamage() + "\n";
                t += "Ability 1: " + Controller.g.getSecondPlayer().getTeam().get(i).getAbilities().get(0).getName() + "-CoolDown: " +
                        Controller.g.getSecondPlayer().getTeam().get(i).getAbilities().get(0).getCurrentCooldown() + "\n";
                t += "Ability 2: " + Controller.g.getSecondPlayer().getTeam().get(i).getAbilities().get(1).getName() + "-CoolDown: " +
                        Controller.g.getSecondPlayer().getTeam().get(i).getAbilities().get(1).getCurrentCooldown() + "\n";
                t += "Ability 3: " + Controller.g.getSecondPlayer().getTeam().get(i).getAbilities().get(2).getName() + "-CoolDown: " +
                        Controller.g.getSecondPlayer().getTeam().get(i).getAbilities().get(2).getCurrentCooldown() + "\n";
                t += "Applied Effects: ";
                for (int j = 0; j < Controller.g.getSecondPlayer().getTeam().get(i).getAppliedEffects().size(); ++j) {
                    t += "" + Controller.g.getSecondPlayer().getTeam().get(i).getAppliedEffects().get(j).getName() +
                            "(" + Controller.g.getSecondPlayer().getTeam().get(i).getAppliedEffects().get(j).getDuration() + ")" + ", "+"\n";
                }
            }
        }
        return t;
    }
    public static String championInfo(){
        String t = "";

        t += "Name : " + Controller.g.getCurrentChampion().getName() + "\n";
        t += " HP : " + Controller.g.getCurrentChampion().getCurrentHP()+"/"+ Controller.g.getCurrentChampion().getMaxHP() + "\n";
        t += "Mana : " + Controller.g.getCurrentChampion().getMana() + "\n";
        t += "Max Action Points: " + Controller.g.getCurrentChampion().getMaxActionPointsPerTurn() + "\n";
        t += "Current Action Points: " + Controller.g.getCurrentChampion().getCurrentActionPoints() +"\n";
        t += "Speed: " + Controller.g.getCurrentChampion().getSpeed() + "\n";
        t += "Attack Range: " + Controller.g.getCurrentChampion().getAttackRange() + "\n";
        t += "Attack Damage " + Controller.g.getCurrentChampion().getAttackDamage() + "\n";
        t += "Ability 1: " + Controller.g.getCurrentChampion().getAbilities().get(0).getName() + "-CoolDown: " +
                Controller.g.getCurrentChampion().getAbilities().get(0).getCurrentCooldown()+"\n";
        t += "Ability 2: " + Controller.g.getCurrentChampion().getAbilities().get(1).getName() + "-CoolDown: " +
                Controller.g.getCurrentChampion().getAbilities().get(1).getCurrentCooldown()+"\n";
        t += "Ability 3: " + Controller.g.getCurrentChampion().getAbilities().get(2).getName() + "-CoolDown: " +
                Controller.g.getCurrentChampion().getAbilities().get(2).getCurrentCooldown()+"\n";
        t += "Applied Effects: ";
        for(int i = 0 ; i<Controller.g.getCurrentChampion().getAppliedEffects().size(); ++i){
            t += "" + Controller.g.getCurrentChampion().getAppliedEffects().get(i).getName()+
                    "("+Controller.g.getCurrentChampion().getAppliedEffects().get(i).getDuration()+")"+", ";
        }



        return t;
    }

    public static void chooseChampions(ArrayList<String> champions, boolean first) throws Exception {
        for(int i = 0 ;i< Game.getAvailableChampions().size(); ++i){
            if(champions.contains(Game.getAvailableChampions().get(i).getName())){
                if(first){
                    g.getFirstPlayer().getTeam().add(Game.getAvailableChampions().get(i));
                }
                else{
                    g.getSecondPlayer().getTeam().add(Game.getAvailableChampions().get(i));
                }
            }
        }
    }
    public static void place(){
        g.placeChampions();
        g.placeCovers();
        g.prepareChampionTurns();
    }
    public static void chooseLeader(String name,boolean first){

        for(int i = 0 ;i< Game.getAvailableChampions().size(); ++i){
            if(Game.getAvailableChampions().get(i).getName().equals(name)){
                if(first){
                    g.getFirstPlayer().setLeader(Game.getAvailableChampions().get(i));
                }
                else{
                    g.getSecondPlayer().setLeader(Game.getAvailableChampions().get(i));
                }
            }
        }
    }
    public static void playerNames(String pn1, String pn2){
        player1 = new Player(pn1);
        player2 = new Player(pn2);
    }



    public static void initGame() throws Exception {
        g = new Game(player1, player2);
        g.loadAbilities("Abilities.csv");
        g.loadChampions("Champions.csv");
    }

    public static String getdeetsAbility1(String s){
        String t = "";
        for(int i = 0; i < g.getAvailableChampions().size(); ++i){
            if(g.getAvailableChampions().get(i).getName().equals(s)){
                Ability a = g.getCurrentChampion().getAbilities().get(0);
                    t+= "-Name: " + a.getName() + "\n";
                    t+= "-Mana Cost: " + a.getManaCost() + "\n";
                    t+= "-BaseCoolDown: " + a.getBaseCooldown() + "\n";
                    t+= "-CurrentCoolDown: " + a.getCurrentCooldown()  + "\n";
                    t+= "-Range: " + a.getCastRange() + "\n";
                    t+= "-Area of Effect: " + a.getCastArea() + "\n";
                    t+= "-Required ActionPoints: " + a.getRequiredActionPoints() + "\n";
                    if(a instanceof DamagingAbility){
                        t+= "-Type: DamagingAbility"  + "\n";
                        t+= "Damage Amount: " + ((DamagingAbility) a).getDamageAmount() + "\n";
                    }
                    else if(a instanceof HealingAbility){
                        t+= "-Type: HealingAbility"  + "\n";
                        t+= "Heal Amount: " + ((HealingAbility) a).getHealAmount() + "\n";
                    }
                    else if(a instanceof CrowdControlAbility){
                        t+= "-Type: CrowdControlAbility"  + "\n";
                        t+= "Effect Name: " + ((CrowdControlAbility) a).getEffect().getName() + "\n";
                        t+= "       Duration: " + ((CrowdControlAbility) a).getEffect().getDuration() + "\n";
                        t+= "       EffectType: " + ((CrowdControlAbility) a).getEffect().getType()+ "\n";
                    }


            }
        }

        return t ;
    }
    public static String getdeetsAbility2(String s){
        String t = "";
        for(int i = 0; i < g.getAvailableChampions().size(); ++i){
            if(g.getAvailableChampions().get(i).getName().equals(s)){
                Ability a = g.getCurrentChampion().getAbilities().get(1);
                t+= "-Name: " + a.getName() + "\n";
                t+= "-Mana Cost" + a.getManaCost() + "\n";
                t+= "-BaseCoolDown: " + a.getBaseCooldown() + "\n";
                t+= "-CurrentCoolDown: " + a.getCurrentCooldown()  + "\n";
                t+= "-Range: " + a.getCastRange() + "\n";
                t+= "-Area of Effect: " + a.getCastArea() + "\n";
                t+= "-Required ActionPoints: " + a.getRequiredActionPoints() + "\n";
                if(a instanceof DamagingAbility){
                    t+= "-Type: DamagingAbility"  + "\n";
                    t+= "Damage Amount: " + ((DamagingAbility) a).getDamageAmount() + "\n";
                }
                else if(a instanceof HealingAbility){
                    t+= "-Type: HealingAbility"  + "\n";
                    t+= "Heal Amount: " + ((HealingAbility) a).getHealAmount() + "\n";
                }
                else if(a instanceof CrowdControlAbility){
                    t+= "-Type: CrowdControlAbility"  + "\n";
                    t+= "Effect Name: " + ((CrowdControlAbility) a).getEffect().getName() + "\n";
                    t+= "       Duration: " + ((CrowdControlAbility) a).getEffect().getDuration() + "\n";
                    t+= "       EffectType: " + ((CrowdControlAbility) a).getEffect().getType()+ "\n";
                }


            }
        }

        return t ;
    }
    public static String getdeetsAbility3(String s){
        String t = "";
        for(int i = 0; i < g.getAvailableChampions().size(); ++i){
            if(g.getAvailableChampions().get(i).getName().equals(s)){
                Ability a = g.getCurrentChampion().getAbilities().get(2);
                t+= "-Name: " + a.getName() + "\n";
                t+= "-Mana Cost" + a.getManaCost() + "\n";
                t+= "-BaseCoolDown: " + a.getBaseCooldown() + "\n";
                t+= "-CurrentCoolDown: " + a.getCurrentCooldown()  + "\n";
                t+= "-Range: " + a.getCastRange() + "\n";
                t+= "-Area of Effect: " + a.getCastArea() + "\n";
                t+= "-Required ActionPoints: " + a.getRequiredActionPoints() + "\n";
                if(a instanceof DamagingAbility){
                    t+= "-Type: DamagingAbility"  + "\n";
                    t+= "Damage Amount: " + ((DamagingAbility) a).getDamageAmount() + "\n";
                }
                else if(a instanceof HealingAbility){
                    t+= "-Type: HealingAbility"  + "\n";
                    t+= "Heal Amount: " + ((HealingAbility) a).getHealAmount() + "\n";
                }
                else if(a instanceof CrowdControlAbility){
                    t+= "-Type: CrowdControlAbility"  + "\n";
                    t+= "Effect Name: " + ((CrowdControlAbility) a).getEffect().getName() + "\n";
                    t+= "       Duration: " + ((CrowdControlAbility) a).getEffect().getDuration() + "\n";
                    t+= "       EffectType: " + ((CrowdControlAbility) a).getEffect().getType()+ "\n";
                }


            }
        }

        return t ;
    }
    public static ArrayList<Integer> getdeets(){
        ArrayList<Integer> a = new ArrayList<>();
        a.add(g.getCurrentChampion().getMaxHP());
        a.add(g.getCurrentChampion().getCurrentHP());
        a.add(g.getCurrentChampion().getMana());
        a.add(g.getCurrentChampion().getMaxMana());
        a.add(g.getCurrentChampion().getCurrentActionPoints());
        a.add(g.getCurrentChampion().getAttackDamage());
        a.add(g.getCurrentChampion().getAttackRange());
        return a;
    }

    public static void main(String[] args) throws Exception {
         Application.launch(Main.class, args);
    }

}
