package com.example.harkka;

import java.util.ArrayList;

public abstract class Player {
    private String name;
    Player(String name) {
        this.name = name;
    }
}
class PlayerDarts extends Player {
    PlayerDarts(String name) {
        super(name);
        ArrayList<ScoreDarts> scoreDarts = new ArrayList<>();
    }
}
class PlayerKiller extends Player {
    PlayerKiller(String name) {
        super(name);
        ArrayList<ScoreKiller> scoreKiller = new ArrayList<>();
    }
}