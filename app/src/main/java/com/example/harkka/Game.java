package com.example.harkka;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public abstract class Game {
    private ArrayList<Player> players = new ArrayList<>();
    String name;

    protected Game() {
        Player player = new Player(name)
        players.add(player);
    }
}

class GameDarts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_darts);
    }
}

class GameKiller extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_killer);
    }
}


class GameDartsMechanics extends Game {

}
class GameKillerMechanics extends Game {

}