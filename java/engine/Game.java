package engine;

import java.util.*;

import exceptions.*;
import model.abilities.*;
import model.effects.*;
import model.world.*;
import java.awt.*;
import java.io.*;

public class Game {

    private Player firstPlayer;
    private Player secondPlayer;
    private boolean firstLeaderAbilityUsed;
    private boolean secondLeaderAbilityUsed;
    private Object[][] board;
    private static ArrayList<Champion> availableChampions = new ArrayList<Champion>();
    private static ArrayList<Ability> availableAbilities = new ArrayList<Ability>();
    private PriorityQueue turnOrder;
    private static final int BOARDHEIGHT = 5;
    private static final int BOARDWIDTH = 5;

    public Game(Player first, Player second) {
        firstPlayer = first;
        secondPlayer = second;
        firstLeaderAbilityUsed = false;
        secondLeaderAbilityUsed = false;
        board = new Object[5][5];
        turnOrder = new PriorityQueue(6);
    }

    public void placeChampions() {
        ArrayList<Champion> team1 = firstPlayer.getTeam();
        ArrayList<Champion> team2 = secondPlayer.getTeam();
        for(int i = 0; i < team1.size(); ++i) {
            board[0][i+1] = team1.get(i);
            team1.get(i).setLocation(new Point(0, i+1));
        }
        for(int i = 0; i < team2.size(); ++i) {
            board[4][i+1] = team2.get(i);
            team2.get(i).setLocation(new Point(4, i+1));
        }
    }

    public void placeCovers() {
        int cnt = 0;
        while(cnt < 5) {
            int i = (int)(Math.random()*3)+1;
            int j = (int)(Math.random()*5);
            if(board[i][j] == null) {
                board[i][j] = new Cover(i, j);
                ++cnt;
            }
        }
    }

    public static void loadAbilities(String filePath) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line = "";
        while((line = br.readLine()) != null) {
            String[] s = line.split(",");
            if(s[0].equals("DMG")) {
                availableAbilities.add(new DamagingAbility(s[1], Integer.parseInt(s[2]), Integer.parseInt(s[4]), Integer.parseInt(s[3]), AreaOfEffect.valueOf(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7])));
            }else if(s[0].equals("HEL")) {
                availableAbilities.add(new HealingAbility(s[1], Integer.parseInt(s[2]), Integer.parseInt(s[4]), Integer.parseInt(s[3]), AreaOfEffect.valueOf(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7])));
            }else {
                Effect e = null;
                switch(s[7]) {
                    case "Disarm" : e = new Disarm(Integer.parseInt(s[8]));break;
                    case "PowerUp" : e = new PowerUp(Integer.parseInt(s[8]));break;
                    case "Shield" : e = new Shield(Integer.parseInt(s[8]));break;
                    case "Silence" : e = new Silence(Integer.parseInt(s[8]));break;
                    case "SpeedUp" : e = new SpeedUp(Integer.parseInt(s[8]));break;
                    case "Embrace" : e = new Embrace(Integer.parseInt(s[8]));break;
                    case "Root" : e = new Root(Integer.parseInt(s[8]));break;
                    case "Shock" : e = new Shock(Integer.parseInt(s[8]));break;
                    case "Dodge" : e = new Dodge(Integer.parseInt(s[8]));break;
                    case "Stun" : e = new Stun(Integer.parseInt(s[8]));break;
                    default : break;
                }
                availableAbilities.add(new CrowdControlAbility(s[1], Integer.parseInt(s[2]), Integer.parseInt(s[4]), Integer.parseInt(s[3]), AreaOfEffect.valueOf(s[5]), Integer.parseInt(s[6]), e));
            }
        }
        br.close();
    }

    public static void loadChampions(String filePath) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line = "";
        while((line = br.readLine()) != null) {
            String[] s = line.split(",");
            String name = s[1];
            int maxHP = Integer.parseInt(s[2]);
            int mana = Integer.parseInt(s[3]);
            int actions = Integer.parseInt(s[4]);
            int speed = Integer.parseInt(s[5]);
            int range = Integer.parseInt(s[6]);
            int dmg = Integer.parseInt(s[7]);
            if(s[0].equals("A")) {
                AntiHero c = new AntiHero(name, maxHP, mana, actions, speed, range, dmg);
                availableChampions.add(c);
                String t = s[8];
                String t1 = s[9];
                String t2 = s[10];
                for(int i = 0; i < availableAbilities.size(); ++i)
                    if(availableAbilities.get(i).getName().equals(t) || availableAbilities.get(i).getName().equals(t1) || availableAbilities.get(i).getName().equals(t2))
                        c.getAbilities().add(availableAbilities.get(i));
            }else if(s[0].equals("H")) {
                Hero c = new Hero(name, maxHP, mana, actions, speed, range, dmg);
                availableChampions.add(c);
                String t = s[8];
                String t1 = s[9];
                String t2 = s[10];
                for(int i = 0; i < availableAbilities.size(); ++i)
                    if(availableAbilities.get(i).getName().equals(t) || availableAbilities.get(i).getName().equals(t1) || availableAbilities.get(i).getName().equals(t2))
                        c.getAbilities().add(availableAbilities.get(i));
            }else {
                Villain c = new Villain(name, maxHP, mana, actions, speed, range, dmg);
                availableChampions.add(c);
                String t = s[8];
                String t1 = s[9];
                String t2 = s[10];
                for(int i = 0; i < availableAbilities.size(); ++i)
                    if(availableAbilities.get(i).getName().equals(t) || availableAbilities.get(i).getName().equals(t1) || availableAbilities.get(i).getName().equals(t2))
                        c.getAbilities().add(availableAbilities.get(i));
            }
        }
        br.close();
    }

    public Champion getCurrentChampion(){
        return (getTurnOrder().size() == 0)?null:(Champion) getTurnOrder().peekMin();
    }

    public Player checkGameOver(){
        if(getFirstPlayer().getTeam().size() == 0)
            return getSecondPlayer();
        if(getSecondPlayer().getTeam().size() == 0)
            return getFirstPlayer();
        return null;
    }

    public void move(Direction d) throws NotEnoughResourcesException, UnallowedMovementException {
        Point p = getCurrentChampion().getLocation();
        int x = (int)p.getX(), y = (int)p.getY();
        if(getCurrentChampion().getCurrentActionPoints() == 0){
            throw new NotEnoughResourcesException();
        }
        if(getCurrentChampion().getCondition() == Condition.ROOTED){
            throw new UnallowedMovementException();
        }
        if(d == Direction.RIGHT && (y == 4 || board[x][y+1] != null)){
            throw new UnallowedMovementException();
        }else if(d == Direction.RIGHT){
            board[x][y] = null;
            board[x][y+1] = getCurrentChampion();
            getCurrentChampion().setLocation(new Point(x, y+1));
            getCurrentChampion().setCurrentActionPoints(getCurrentChampion().getCurrentActionPoints()-1);
        }
        if(d == Direction.LEFT && (y == 0 || board[x][y-1] != null)){
            throw new UnallowedMovementException();
        }else if(d == Direction.LEFT){
            board[x][y] = null;
            board[x][y-1] = getCurrentChampion();
            getCurrentChampion().setLocation(new Point(x, y-1));
            getCurrentChampion().setCurrentActionPoints(getCurrentChampion().getCurrentActionPoints()-1);
        }
        if(d == Direction.UP && (x == 4 || board[x+1][y] != null)){
            throw new UnallowedMovementException();
        }else if(d == Direction.UP){
            board[x][y] = null;
            board[x+1][y] = getCurrentChampion();
            getCurrentChampion().setLocation(new Point(x+1, y));
            getCurrentChampion().setCurrentActionPoints(getCurrentChampion().getCurrentActionPoints()-1);
        }
        if(d == Direction.DOWN && (x == 0  || board[x-1][y] != null)){
            throw new UnallowedMovementException();
        }else if(d == Direction.DOWN){
            board[x][y] = null;
            board[x-1][y] = getCurrentChampion();
            getCurrentChampion().setLocation(new Point(x-1, y));
            getCurrentChampion().setCurrentActionPoints(getCurrentChampion().getCurrentActionPoints()-1);
        }
    }

    public void attack(Direction d) throws NotEnoughResourcesException, InvalidTargetException, ChampionDisarmedException, UnallowedMovementException {
        Champion c = getCurrentChampion();
        if(c.getCondition() == Condition.INACTIVE)
            return;
        boolean disarm = false, dodge = false, shield = false, shock = false;
        if(c.getCurrentActionPoints() < 2)
            throw new NotEnoughResourcesException();
        for(Effect e: c.getAppliedEffects()){
            if(e.getName().compareTo("Disarm") == 0){
                disarm = true;
                break;
            }
        }
        if(disarm) {
            throw new ChampionDisarmedException();
        }
        Damageable target = null;
        Point p = c.getLocation();
        if(d == Direction.UP){
            int m = Math.min(4, p.x+c.getAttackRange());
            for(int i = p.x+1; i <= m; ++i){
                if(board[i][p.y] != null){
                    if(board[i][p.y] instanceof Cover){
                        target = (Cover)board[i][p.y];
                    }
                    else{
                        target = (Champion)board[i][p.y];
                    }
                    break;
                }
            }
        }else if(d == Direction.DOWN){
            int m = Math.max(0, p.x-c.getAttackRange());
            for(int i = p.x-1; i >= m; --i){
                if(board[i][p.y] != null){
                    if(board[i][p.y] instanceof Cover){
                        target = (Cover)board[i][p.y];
                    }
                    else{
                        target = (Champion)board[i][p.y];
                    }
                    break;
                }
            }
        }else if(d == Direction.LEFT){
            int m = Math.max(0, p.y-c.getAttackRange());
            for(int i = p.y-1; i >= m; --i){
                if(board[p.x][i] != null){
                    if(board[p.x][i] instanceof Cover){
                        target = (Cover)board[p.x][i];
                    }
                    else{
                        target = (Champion)board[p.x][i];
                    }
                    break;
                }
            }
        }else if(d == Direction.RIGHT){
            int m = Math.min(4, p.y+c.getAttackRange());
            for(int i = p.y+1; i <= m; ++i){
                if(board[p.x][i] != null){
                    if(board[p.x][i] instanceof Cover){
                        target = (Cover)board[p.x][i];
                    }
                    else{
                        target = (Champion)board[p.x][i];
                    }
                    break;
                }
            }
        }
        getCurrentChampion().setCurrentActionPoints(getCurrentChampion().getCurrentActionPoints()-2);
        if(target != null){
            if (target instanceof Cover) {
                target.setCurrentHP(target.getCurrentHP() - c.getAttackDamage());
                if (target.getCurrentHP() == 0) {
                    board[target.getLocation().x][target.getLocation().y] = null;
                }
                return;
            }
            for (Effect e : ((Champion) target).getAppliedEffects()) {
                if (e.getName().compareTo("Shield") == 0) {
                    shield = true;
                }
                if (e.getName().compareTo("Dodge") == 0) {
                    dodge = true;
                }
            }
            if (getFirstPlayer().getTeam().contains(c) && getFirstPlayer().getTeam().contains(target)) {
                throw new InvalidTargetException();
            }
            if (getSecondPlayer().getTeam().contains(c) && getSecondPlayer().getTeam().contains(target)) {
                throw new InvalidTargetException();
            }
            int dmg = c.getAttackDamage();
            if (shield) {
                for (Effect e : ((Champion) target).getAppliedEffects()) {
                    if (e.getName().compareTo("Shield") == 0) {
                        e.remove(((Champion)target));
                        ((Champion) target).getAppliedEffects().remove(e);
                        break;
                    }
                }
                return;
            }
            if (dodge) {
                int r = (int) (Math.random() * 2);
                if (r == 0) {
                    return;
                }
            }
            if (c instanceof Hero && (target instanceof Villain || target instanceof AntiHero)) {
                target.setCurrentHP(target.getCurrentHP() - (dmg + (int) (dmg * 0.5)));
                if (target.getCurrentHP() == 0) {
                    if(target instanceof Villain)
                        ((Villain) target).setCondition(Condition.KNOCKEDOUT);
                    else
                        ((AntiHero) target).setCondition(Condition.KNOCKEDOUT);
                    attackHelper(target);
                }
                return;
            }
            if (c instanceof Villain && (target instanceof Hero || target instanceof AntiHero)) {
                target.setCurrentHP(target.getCurrentHP() - (dmg + (int) (dmg * 0.5)));
                if (target.getCurrentHP() == 0) {
                    if(target instanceof Hero)
                        ((Hero) target).setCondition(Condition.KNOCKEDOUT);
                    else
                        ((AntiHero) target).setCondition(Condition.KNOCKEDOUT);
                    attackHelper(target);
                }
                return;
            }
            if (c instanceof AntiHero && !(target instanceof AntiHero)) {
                target.setCurrentHP(target.getCurrentHP() - (dmg + (int) (dmg * 0.5)));
                if (target instanceof Villain)
                    ((Villain) target).setCondition(Condition.KNOCKEDOUT);
                else
                    ((Hero) target).setCondition(Condition.KNOCKEDOUT);
                if (target.getCurrentHP() == 0) {
                    attackHelper(target);
                }
                return;
            }
            target.setCurrentHP(target.getCurrentHP() - dmg);
            if (target.getCurrentHP() == 0) {
                if (target instanceof Villain)
                    ((Villain) target).setCondition(Condition.KNOCKEDOUT);
                else if(target instanceof Hero)
                    ((Hero) target).setCondition(Condition.KNOCKEDOUT);
                else
                    ((AntiHero) target).setCondition(Condition.KNOCKEDOUT);
                attackHelper(target);
            }
        }
        removeDead();
    }

    private void attackHelper(Damageable target) {
        board[target.getLocation().x][target.getLocation().y] = null;
        PriorityQueue pq = new PriorityQueue(6);
        while(!getTurnOrder().isEmpty()){
            pq.insert(getTurnOrder().remove());
        }
        while(!pq.isEmpty()){
            Comparable comp = pq.remove();
            if(comp.compareTo(target) == 0)
                continue;
            getTurnOrder().insert(comp);
        }
    }

    public void castAbility(Ability a) throws NotEnoughResourcesException, AbilityUseException, InvalidTargetException, CloneNotSupportedException {
        Champion cur = getCurrentChampion();
        if(cur.getCondition() == Condition.INACTIVE)
            return;
        checkExceptionsAbility(cur, a);
        ArrayList<Damageable> targets = new ArrayList<>();
        if(a instanceof HealingAbility){
            if(((HealingAbility)a).getCastArea().equals(AreaOfEffect.SELFTARGET)){
                targets.add(cur);
                ((HealingAbility)a).execute(targets);
            }
            else if(((HealingAbility)a).getCastArea().equals(AreaOfEffect.TEAMTARGET)){
                boolean first = true;
                if(getFirstPlayer().getTeam().contains(cur)){
                    first =false;
                }
                getTargetTeamSurroundHealing(a, cur, targets, first);
                ((HealingAbility)a).execute(targets);
            }
            else if(((HealingAbility)a).getCastArea().equals(AreaOfEffect.SURROUND)){
                targets = getDamageablesSurround(a);
                for(int i = 0; i < targets.size(); ++i){
                    if(targets.get(i) instanceof Cover){
                        targets.remove(i);
                        --i;
                    }
                    else if(!(checkFriend(cur, (Champion) targets.get(i)))){
                        targets.remove(i);
                        --i;
                    }
                }
                ((HealingAbility)a).execute(targets);
            }
        }else if(a instanceof DamagingAbility){
            if(a.getCastArea().equals(AreaOfEffect.SELFTARGET)){
                throw new InvalidTargetException();
            }
            else if(a.getCastArea().equals(AreaOfEffect.TEAMTARGET)){
                boolean first = false;
                if(getFirstPlayer().getTeam().contains(cur)){
                    first =true;
                }
                getTargetTeamSurroundHealing(a, cur, targets, first);
                removeTargetWithShield(targets);
                a.execute(targets);
            }
            else if(a.getCastArea().equals(AreaOfEffect.SURROUND)){
                targets = getDamageablesSurround(a);
                for(int i = 0 ;i < targets.size(); ++i){
                    if(!(targets.get(i) instanceof Cover)){
                        if (checkFriend(cur, (Champion) targets.get(i))) {
                            targets.remove(i);
                            --i;
                        }
                    }
                }
                for(int i = 0; i < targets.size(); ++i) {
                    if (targets.get(i) instanceof Champion) {
                        boolean shield = false;
                        for(int j = 0; j < ((Champion) targets.get(i)).getAppliedEffects().size(); ++j){
                            if(((Champion) targets.get(i)).getAppliedEffects().get(j).getName().equals("Shield")){
                                shield = true;
                                ((Champion) targets.get(i)).getAppliedEffects().remove(j);
                                break;
                            }
                        }
                        if(shield){
                            targets.remove(i);
                            --i;
                        }
                    }
                }
                a.execute(targets);
            }
        }else if (a instanceof CrowdControlAbility){
            if(a.getCastArea().equals(AreaOfEffect.SELFTARGET)){
                if(((CrowdControlAbility) a).getEffect().getType().equals(EffectType.DEBUFF)){
                    throw new InvalidTargetException();
                }
                if(((CrowdControlAbility) a).getEffect().getType().equals(EffectType.BUFF)){
                    targets.add(cur);
                    a.execute(targets);
                }
            }else if(a.getCastArea().equals(AreaOfEffect.TEAMTARGET)){
                targets = getDamageablesTeamTarget(a);
                getTargetsTeamSurroundCC(a, cur, targets);
            }else if(a.getCastArea().equals(AreaOfEffect.SURROUND)){
                targets = getDamageablesSurround(a);
                getTargetsTeamSurroundCC(a, cur, targets);
            }
        }
        cur.setMana(cur.getMana()-a.getManaCost());
        cur.setCurrentActionPoints(cur.getCurrentActionPoints()-a.getRequiredActionPoints());
        a.setCurrentCooldown(a.getBaseCooldown());
        removeDead();
    }

    private void getTargetsTeamSurroundCC(Ability a, Champion cur, ArrayList<Damageable> targets) throws CloneNotSupportedException {
        for(int i = 0; i < targets.size(); ++i) {
            if ((targets.get(i) instanceof Cover)) {
                targets.remove(i);
                --i;
            }
        }
        for(int i = 0; i < targets.size(); ++i) {
            if(((CrowdControlAbility) a).getEffect().getType().equals(EffectType.DEBUFF)){
                if(checkFriend(cur,(Champion) targets.get(i))){
                    targets.remove(i);
                    --i;
                }
            }else if(((CrowdControlAbility) a).getEffect().getType().equals(EffectType.BUFF)){
                if(!checkFriend(cur,(Champion) targets.get(i))){
                    targets.remove(i);
                    --i;
                }
            }
        }
        a.execute(targets);
    }

    private void removeTargetWithShield(ArrayList<Damageable> targets) {
        for(int i = 0; i < targets.size(); ++i) {
            if (targets.get(i) instanceof Champion) {
                boolean shield = false;
                for(int j = 0; j < ((Champion) targets.get(i)).getAppliedEffects().size(); ++j){
                    if(((Champion) targets.get(i)).getAppliedEffects().get(j).getName().equals("Shield")){
                        shield = true;
                        (((Champion)(targets.get(i))).getAppliedEffects().get(j)).remove((Champion) targets.get(i));
                        ((Champion) targets.get(i)).getAppliedEffects().remove(j);
                        break;
                    }
                }
                if(shield){
                    targets.remove(i);
                    --i;
                }
            }
        }
    }

    private void getTargetTeamSurroundHealing(Ability a, Champion cur, ArrayList<Damageable> targets, boolean first) {
        Point p = cur.getLocation();
        if(first){
            for(int i = 0; i < getSecondPlayer().getTeam().size(); ++i){
                Point targetLocation = getSecondPlayer().getTeam().get(i).getLocation();
                if(Math.abs(p.x - targetLocation.x) + Math.abs(p.y - targetLocation.y) <= a.getCastRange()){
                    targets.add(getSecondPlayer().getTeam().get(i));
                }
            }
        }else{
            for(int i = 0; i < getFirstPlayer().getTeam().size(); ++i){
                Point targetLocation = getFirstPlayer().getTeam().get(i).getLocation();
                if(Math.abs(p.x - targetLocation.x) + Math.abs(p.y - targetLocation.y) <= a.getCastRange()){
                    targets.add(getFirstPlayer().getTeam().get(i));
                }
            }
        }
    }

    public void castAbility(Ability a, Direction d) throws CloneNotSupportedException, AbilityUseException, NotEnoughResourcesException{
        Champion cur = getCurrentChampion();
        if(cur.getCondition() == Condition.INACTIVE)
            return;
        checkExceptionsAbility(cur, a);
        ArrayList<Damageable> targets = new ArrayList<>();
        if(a instanceof DamagingAbility){
            targets = getDamageablesDamaging(a, d);
            a.execute(targets);
        }else if(a instanceof HealingAbility){
            targets = getDamageablesHealing(a, d);
            a.execute(targets);
        }else if(a instanceof CrowdControlAbility){
            if(((CrowdControlAbility) a).getEffect().getType() == EffectType.BUFF){
                targets = getDamageablesHealing(a, d);
            }else{
                targets = getDamageablesDamaging(a, d);
                for(int i = 0; i < targets.size(); ++i){
                    if(targets.get(i) instanceof Cover){
                        targets.remove(i);
                        --i;
                    }
                }
            }
            a.execute(targets);
        }
        cur.setMana(cur.getMana()-a.getManaCost());
        cur.setCurrentActionPoints(cur.getCurrentActionPoints()-a.getRequiredActionPoints());
        a.setCurrentCooldown(a.getBaseCooldown());
        removeDead();
    }
    private ArrayList<Damageable> getDamageablesDamaging(Ability a, Direction d){
        ArrayList<Damageable> targets = new ArrayList<>();
        Champion cur = getCurrentChampion();
        Point p = cur.getLocation();
        if(d == Direction.UP){
            int m = Math.min(4, p.x+a.getCastRange());
            for(int i = p.x; i <= m; ++i){
                getDamageablesDamagingHelperY(targets, cur, p, i);
            }
        }else if(d == Direction.DOWN){
            int m = Math.max(0, p.x-a.getCastRange());
            for(int i = p.x; i >= m; --i){
                getDamageablesDamagingHelperY(targets, cur, p, i);
            }
        }else if(d == Direction.LEFT){
            int m = Math.max(0, p.y-a.getCastRange());
            for(int i = p.y; i >= m; --i){
                getDamageablesDamagingHelperX(targets, cur, p, i);
            }
        }else if(d == Direction.RIGHT){
            int m = Math.min(4, p.y+a.getCastRange());
            for(int i = p.y; i <= m; ++i){
                getDamageablesDamagingHelperX(targets, cur, p, i);
            }
        }
        removeTargetWithShield(targets);
        return targets;
    }

    private void getDamageablesDamagingHelperX(ArrayList<Damageable> targets, Champion cur, Point p, int i) {
        if(board[p.x][i] != null){
            if(board[p.x][i] instanceof Cover)
                targets.add((Damageable) board[p.x][i]);
            else if(board[p.x][i] instanceof Champion) {
                if(!(checkFriend(cur, (Champion) board[p.x][i])))
                    targets.add((Damageable) board[p.x][i]);
            }
        }
    }

    private void getDamageablesDamagingHelperY(ArrayList<Damageable> targets, Champion cur, Point p, int i) {
        if(board[i][p.y] != null){
            if(board[i][p.y] instanceof Cover)
                targets.add((Damageable) board[i][p.y]);
            else if(board[i][p.y] instanceof Champion) {
                if(!(checkFriend(cur, (Champion) board[i][p.y])))
                    targets.add((Damageable) board[i][p.y]);
            }
        }
    }

    private ArrayList<Damageable> getDamageablesHealing(Ability a, Direction d){
        ArrayList<model.world.Damageable> targets = new ArrayList<>();
        Champion cur = getCurrentChampion();
        Point p = cur.getLocation();
        if(d == Direction.UP){
            int m = Math.min(4, p.x+a.getCastRange());
            for(int i = p.x; i <= m; ++i){
                if(board[i][p.y] != null){
                    if(board[i][p.y] instanceof Champion) {
                        if((checkFriend(cur, (Champion) board[i][p.y])))
                            targets.add((Damageable) board[i][p.y]);
                    }
                }
            }
        }else if(d == Direction.DOWN){
            int m = Math.max(0, p.x-a.getCastRange());
            for(int i = p.x; i >= m; --i){
                if(board[i][p.y] != null){
                    if(board[i][p.y] instanceof Champion) {
                        if((checkFriend(cur, (Champion) board[i][p.y])))
                            targets.add((Damageable) board[i][p.y]);
                    }
                }
            }
        }else if(d == Direction.LEFT){
            int m = Math.max(0, p.y-a.getCastRange());
            for(int i = p.y; i >= m; --i){
                if(board[p.x][i] != null){
                    if(board[p.x][i] instanceof Champion) {
                        if((checkFriend(cur, (Champion) board[p.x][i])))
                            targets.add((Damageable) board[p.x][i]);
                    }
                }
            }
        }else if(d == Direction.RIGHT){
            int m = Math.min(4, p.y+a.getCastRange());
            for(int i = p.y; i <= m; ++i){
                if(board[p.x][i] != null){
                    if(board[p.x][i] instanceof Champion) {
                        if((checkFriend(cur, (Champion) board[p.x][i])))
                            targets.add((Damageable) board[p.x][i]);
                    }
                }
            }
        }
        return targets;
    }

    public void castAbility(Ability a, int x, int y) throws AbilityUseException, InvalidTargetException, CloneNotSupportedException, NotEnoughResourcesException {
        Champion cur = getCurrentChampion();
        for(int i = 0; i < 5; ++i) {
            for(int j = 0; j < 5; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(x+" "+y);
        if(cur.getCondition() == Condition.INACTIVE)
            return;
        checkExceptionsAbility(cur, a);
        if(x > 4 || x < 0 || y > 4 || y < 0){
            return;
        }
        if(board[x][y] == null){
            System.out.println("null cell");
            throw new InvalidTargetException();
        }
        Point p = cur.getLocation();
        a.setCurrentCooldown(a.getBaseCooldown());
        if(Math.abs(p.x-x)+Math.abs(p.y-y) > a.getCastRange()){
            System.out.println("out range");
            throw new AbilityUseException();
        }
        ArrayList<Damageable> targets = new ArrayList<>();
        if(a instanceof DamagingAbility){
            if(board[x][y] instanceof Champion && checkFriend(cur, (Champion)board[x][y])) {
                System.out.println("Friend");
                throw new InvalidTargetException();
            }
            targets.add((Damageable) board[x][y]);
            if(board[x][y] instanceof Cover) {
                a.execute(targets);
            }else {
                boolean shield = false;
                for (int i = 0; i < ((Champion) board[x][y]).getAppliedEffects().size(); ++i) {
                    if (((Champion) board[x][y]).getAppliedEffects().get(i).getName().equals("Shield")) {
                        shield = true;
                        ((Champion) board[x][y]).getAppliedEffects().remove(i);
                        break;
                    }
                }
                if (shield) {
                    cur.setMana(cur.getMana() - a.getManaCost());
                    cur.setCurrentActionPoints(cur.getCurrentActionPoints() - a.getRequiredActionPoints());
                    return;
                } else if (board[x][y] instanceof Champion) {
                    if (!checkFriend(cur, (Champion) board[x][y])) {
                        a.execute(targets);
                    }
                }
            }
            removeDead();
        }else if(a instanceof HealingAbility){
            targets.add((Damageable) board[x][y]);
            if(board[x][y] instanceof Cover)
                throw new InvalidTargetException();
            else if(board[x][y] instanceof Champion){
                if(!checkFriend(cur, (Champion)board[x][y])){
                    return;
                }else{
                    a.execute(targets);
                }
            }
        }else if(a instanceof CrowdControlAbility){
            targets.add((Damageable) board[x][y]);
            if(board[x][y] instanceof Cover)
                throw new InvalidTargetException();
            else if(board[x][y] instanceof Champion){
                if(x == p.x && y == p.y && ((CrowdControlAbility) a).getEffect().getType() == EffectType.DEBUFF){
                    throw new InvalidTargetException();
                }
                if(checkFriend(cur, (Champion)board[x][y]) && ((CrowdControlAbility) a).getEffect().getType() == EffectType.BUFF){
                    a.execute(targets);
                }else if(!checkFriend(cur, (Champion)board[x][y]) && ((CrowdControlAbility) a).getEffect().getType() == EffectType.DEBUFF){
                    a.execute(targets);
                }else{
                    throw new InvalidTargetException();
                }
            }
        }
        removeDead();
        cur.setMana(cur.getMana()-a.getManaCost());
        cur.setCurrentActionPoints(cur.getCurrentActionPoints()-a.getRequiredActionPoints());
    }

    private void checkExceptionsAbility(Champion cur, Ability a) throws NotEnoughResourcesException, AbilityUseException {
        if(cur.getMana() < a.getManaCost())
            throw new NotEnoughResourcesException();
        if(cur.getCurrentActionPoints() < a.getRequiredActionPoints())
            throw new NotEnoughResourcesException();
        if(a.getCurrentCooldown() > 0)
            throw new AbilityUseException();
        boolean silence = false;
        for(Effect e: cur.getAppliedEffects()){
            if (e.getName().equals("Silence")) {
                silence = true;
            }
        }
        if(silence){
            throw new AbilityUseException();
        }
    }

    private boolean checkFriend(Champion c, Champion t){
        if((getFirstPlayer().getTeam().contains(c) &&
                getFirstPlayer().getTeam().contains(t)) ||
                (getSecondPlayer().getTeam().contains(c) &&
                        getSecondPlayer().getTeam().contains(t))  ) {
            return true;
        }
        return false;
    }


    private ArrayList<Damageable> getDamageablesTeamTarget(Ability a) {
        ArrayList<Damageable> targets = new ArrayList<>();
        Champion cur = getCurrentChampion();
        Point p = cur.getLocation();
        int x = p.x, y = p.y;
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (board[i][j] == null) {
                    continue;
                }
                if (Math.abs(x - i) + Math.abs(y - j) <= a.getCastRange()) {
                    targets.add((Damageable) board[i][j]);
                }
            }
        }
        return targets;
    }
    private ArrayList<Damageable> getDamageablesSurround(Ability a){
        ArrayList<Damageable> targets = new ArrayList<>();
        Champion cur = getCurrentChampion();
        Point p = cur.getLocation();
        int x = p.x, y = p.y;
        if(x < 4){
            if(board[x+1][y] != null) {
                targets.add((Damageable) board[x + 1][y]);
            }
        }
        if(x < 4 && y < 4){
            if(board[x+1][y+1] != null) {
                targets.add((Damageable) board[x + 1][y + 1]);
            }
        }
        if(y < 4){
            if(board[x][y+1] != null) {
                targets.add((Damageable) board[x][y + 1]);
            }
        }
        if(x > 0 && y < 4){
            if(board[x-1][y+1] != null) {
                targets.add((Damageable) board[x - 1][y + 1]);
            }
        }
        if(x > 0){
            if(board[x-1][y] != null) {
                targets.add((Damageable) board[x - 1][y]);
            }
        }
        if(x > 0 && y > 0){
            if(board[x-1][y-1] != null) {
                targets.add((Damageable) board[x - 1][y - 1]);
            }
        }
        if(y > 0){
            if(board[x][y-1] != null) {
                targets.add((Damageable) board[x][y - 1]);
            }
        }
        if(x < 4 && y > 0){
            if(board[x+1][y-1] != null) {
                targets.add((Damageable) board[x + 1][y - 1]);
            }
        }
        return targets;
    }


    public void useLeaderAbility() throws LeaderNotCurrentException, LeaderAbilityAlreadyUsedException, CloneNotSupportedException {
        Champion cur = getCurrentChampion();
        boolean isFirst = false;
        if(getFirstPlayer().getTeam().contains(cur) && cur != getFirstPlayer().getLeader()){
            throw new LeaderNotCurrentException();
        }
        if(getSecondPlayer().getTeam().contains(cur) && cur != getSecondPlayer().getLeader()) {
            throw new LeaderNotCurrentException();
        }
        if(getFirstPlayer().getTeam().contains(cur) && isFirstLeaderAbilityUsed()){
            throw new LeaderAbilityAlreadyUsedException();
        }
        if(getSecondPlayer().getTeam().contains(cur) && isSecondLeaderAbilityUsed()){
            throw new LeaderAbilityAlreadyUsedException();
        }
        if(getFirstPlayer().getTeam().contains(cur)){
            isFirst = true;
            firstLeaderAbilityUsed = true;
        }
        if(getSecondPlayer().getTeam().contains(cur)){
            secondLeaderAbilityUsed = true;
        }
        ArrayList<Champion> targets = new ArrayList<>();
        if(cur instanceof Hero){
            if(isFirst){
                targets.addAll(getFirstPlayer().getTeam());
            }else{
                targets.addAll(getSecondPlayer().getTeam());
            }
            cur.useLeaderAbility(targets);
        }else if(cur instanceof Villain){
            if(isFirst){
                targets.addAll(getSecondPlayer().getTeam());
            }else{
                targets.addAll(getFirstPlayer().getTeam());
            }
            for(int i = 0; i < targets.size(); ++i){
                int h = targets.get(i).getCurrentHP() / targets.get(i).getMaxHP();
                int f = (int) (targets.get(i).getMaxHP() * 0.3);
                if(h >= f){
                    targets.remove(i);
                    --i;
                }
            }
            cur.useLeaderAbility(targets);
        }else{
            for(Champion c: getFirstPlayer().getTeam()){
                if(c != getFirstPlayer().getLeader()){
                    targets.add(c);
                }
            }
            for(Champion c: getSecondPlayer().getTeam()){
                if(c != getSecondPlayer().getLeader()){
                    targets.add(c);
                }
            }
            cur.useLeaderAbility(targets);
        }
    }

    public void endTurn(){
        if(!getTurnOrder().isEmpty()){
            getTurnOrder().remove();
        }
        if(getTurnOrder().isEmpty()){
            prepareChampionTurns();
        }
        while(!getTurnOrder().isEmpty()){
            while (!getTurnOrder().isEmpty() && (((Champion) (getTurnOrder().peekMin())).getCondition() == Condition.INACTIVE || hasStun(((Champion) (getTurnOrder().peekMin()))))) {
                updateStats(((Champion) (getTurnOrder().peekMin())));
                //quiz2(((Champion) (getTurnOrder().peekMin())));
                getTurnOrder().remove();
            }
            if(getTurnOrder().isEmpty()){
                prepareChampionTurns();
            }
            if(((Champion) (getTurnOrder().peekMin())).getCondition() != Condition.INACTIVE){
                break;
            }
        }
        if(!getTurnOrder().isEmpty()){
            updateStats(((Champion) (getTurnOrder().peekMin())));
        }
    }
    private void updateStats(Champion cur){
        for(int i = 0; i < cur.getAppliedEffects().size(); ++i){
            if(cur.getAppliedEffects().get(i).getDuration() == 1){
                cur.getAppliedEffects().get(i).remove(cur);
                cur.getAppliedEffects().remove(i);
                --i;
            }else{
                cur.getAppliedEffects().get(i).setDuration(cur.getAppliedEffects().get(i).getDuration() - 1);
            }
        }
        for(int i = 0; i < cur.getAbilities().size(); ++i){
            if(cur.getAbilities().get(i).getCurrentCooldown() > 0){
                cur.getAbilities().get(i).setCurrentCooldown(cur.getAbilities().get(i).getCurrentCooldown() - 1);
            }
        }
        cur.setCurrentActionPoints(cur.getMaxActionPointsPerTurn());
    }

    private boolean hasStun(Champion c) {
        for(int i = 0; i < c.getAppliedEffects().size(); ++i) {
            if(c.getAppliedEffects().get(i).getName().equals("Stun")) {
                return true;
            }
        }
        return false;
    }

    public void prepareChampionTurns(){
        for(Champion c: getFirstPlayer().getTeam()){
            if(c.getCurrentHP() == 0 || c.getCondition() == Condition.KNOCKEDOUT){
                continue;
            }
            getTurnOrder().insert(c);
        }
        for(Champion c: getSecondPlayer().getTeam()){
            if(c.getCurrentHP() == 0 || c.getCondition() == Condition.KNOCKEDOUT){
                continue;
            }
            getTurnOrder().insert(c);
        }
    }


    private void removeDead(){
        for(int i = 0; i < 5; ++i){
            for(int j = 0; j < 5; ++j){
                if(board[i][j] == null)
                    continue;
                if(board[i][j] instanceof Cover){
                    if(((Cover) board[i][j]).getCurrentHP() == 0){
                        board[i][j] = null;
                    }
                }
                if(board[i][j] instanceof Hero || board[i][j] instanceof Champion || board[i][j] instanceof Villain || board[i][j] instanceof AntiHero){
                    if(((Champion) board[i][j]).getCurrentHP() == 0 || ((Champion) board[i][j]).getCondition() == Condition.KNOCKEDOUT){
                        board[i][j] = null;
                    }
                }
            }
        }
        PriorityQueue pq = new PriorityQueue(getTurnOrder().size());
        while(!getTurnOrder().isEmpty()){
            pq.insert(getTurnOrder().remove());
        }
        while(!pq.isEmpty()){
            Comparable c = pq.remove();
            if(((Champion)c).getCurrentHP() == 0 || ((Champion)c).getCondition() == Condition.KNOCKEDOUT){
                continue;
            }
            getTurnOrder().insert(c);
        }
        for(int j = 0; j < getFirstPlayer().getTeam().size(); ++j){
            if(getFirstPlayer().getTeam().get(j).getCondition() == Condition.KNOCKEDOUT || getFirstPlayer().getTeam().get(j).getCurrentHP() == 0){
                getFirstPlayer().getTeam().remove(j);
                --j;
            }
        }
        for(int j = 0; j < getSecondPlayer().getTeam().size(); ++j){
            if(getSecondPlayer().getTeam().get(j).getCondition() == Condition.KNOCKEDOUT || getSecondPlayer().getTeam().get(j).getCurrentHP() == 0){
                getSecondPlayer().getTeam().remove(j);
                --j;
            }
        }
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public boolean isFirstLeaderAbilityUsed() {
        return firstLeaderAbilityUsed;
    }

    public boolean isSecondLeaderAbilityUsed() {
        return secondLeaderAbilityUsed;
    }

    public Object[][] getBoard() {
        return board;
    }

    public static ArrayList<Champion> getAvailableChampions() {
        return availableChampions;
    }

    public static ArrayList<Ability> getAvailableAbilities() {
        return availableAbilities;
    }

    public PriorityQueue getTurnOrder() {
        return turnOrder;
    }

    public static int getBoardheight() {
        return BOARDHEIGHT;
    }

    public static int getBoardwidth() {
        return BOARDWIDTH;
    }

}
