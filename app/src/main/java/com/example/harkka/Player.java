package com.example.harkka;

public abstract class Player {

    protected String name;
    protected int placement;

    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPlacement() {
        return placement;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
    }
}

class PlayerDarts extends Player {
    PlayerDarts(String name) {
        super(name);
    }
}

class PlayerKiller extends Player {

    private int lives;

    PlayerKiller(String name, int lives) {
        super(name);
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }
    public boolean checkAlive() {
        return lives > 0;
    }
    public void removeLive() {
        lives--;
    }
}