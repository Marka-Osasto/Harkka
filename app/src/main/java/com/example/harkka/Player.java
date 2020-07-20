package com.example.harkka;

import java.util.ArrayList;

public class Player {
    private String name;
    ArrayList<Score> scores = new ArrayList<>();
    protected Player(String name) {
        this.name = name;
    }
    protected void addScore() {

    }
}