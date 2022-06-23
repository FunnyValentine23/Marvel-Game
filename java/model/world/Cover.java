package model.world;

import java.awt.*;

public class Cover implements Damageable{

    private int currentHP;
    private Point location;

    public Cover(int x, int y) {
        location = new Point(x, y);
        int low = 100;
        int high = 1000;
        this.currentHP = (int)(Math.random()*(high-low))+low;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = Math.max(0, currentHP);
    }

    public Point getLocation() {
        return location;
    }

}
