package com.example.harkka;

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

    public void setScore(int score) {
        this.score = score;
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