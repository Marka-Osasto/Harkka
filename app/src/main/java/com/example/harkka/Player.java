package com.example.harkka;


//Contains player info containing Player class and its subclasses
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

    private int score;

    PlayerDarts(String name, int score) {
        super(name);
        this.score = score;
    }

    public void removeScore(int score) {
        this.score -= score;
    }

    public int getScore() {
        return score;
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