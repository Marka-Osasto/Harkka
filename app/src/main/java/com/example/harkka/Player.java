package com.example.harkka;

import java.util.ArrayList;
import java.util.Date;

public abstract class Player {
    private String name;
    private int placement;
    Player(String name) {
        this.name = name;
    }

    protected String getName() {
        return name;
    }
}

class PlayerDarts extends Player {
    PlayerDarts(String name) {
        super(name);
        ArrayList<Score> scoreDarts = new ArrayList<>();
    }
}

class PlayerKiller extends Player {
    private int lives;
    private ArrayList<Score> scoreListKiller;
    PlayerKiller(String name, int lives) {
        super(name);
        scoreListKiller = new ArrayList<>();
        this.lives = lives;
    }

    protected void addScore(int score) {
        Score scoreKiller = new Score(score);
        scoreListKiller.add(scoreKiller);
    }

    protected int getLives() {
        return lives;
    }
    protected void removeLive() {
        lives--;
    }
}

class Score {
    private int score;
    private Date datetime;
    Score(int score) {
        this.score = score;
        this.datetime = new Date(System.currentTimeMillis());
    }
}