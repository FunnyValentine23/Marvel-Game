package engine;

import model.world.*;
import java.util.*;

public class Player {

    private String name;
    private Champion leader;
    private ArrayList<Champion> team;

    public Player(String name) {
        this.name = name;
        team = new ArrayList<>();
    }

    public Champion getLeader() {
        return leader;
    }

    public void setLeader(Champion leader) {
        this.leader = leader;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Champion> getTeam() {
        return team;
    }
}
