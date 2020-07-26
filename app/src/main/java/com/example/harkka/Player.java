package com.example.harkka;

import java.util.Date;

public abstract class Player {

    protected String name;
    protected int placement;
    protected int score;

    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
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

    public void addScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }
    public void removeLive() {
        lives--;
    }
}