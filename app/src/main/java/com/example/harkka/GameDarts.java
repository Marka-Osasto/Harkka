package com.example.harkka;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GameDarts extends AppCompatActivity {

    private PlayerDarts player;
    private TextView text;
    private Spinner spinner;
    private String[] multiplierArray = {"1", "2", "3"};
    private EditText scoreInput;
    private ArrayList<PlayerKiller> players = new ArrayList<>();
    private int scoreFinal;
    private int n;
    private int scorePrevious;
    private int placement;
    private ArrayList<String> placementList = new ArrayList<>();
    private ArrayList<String> scoreList = new ArrayList<>();
    private int index;
    private LinearLayout linearLayout;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        context = GameDarts.this;

        linearLayout = new LinearLayout(context);
        linearLayout = findViewById(R.id.previousScores);
        index = 0;
        n = 1;
        scorePrevious = -1;
        scoreFinal = 0;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.addAll(multiplierArray);
        text = findViewById(R.id.textView2);
        scoreInput = findViewById(R.id.score);
        spinner = findViewById(R.id.multiplierSpinner);
        spinner.setAdapter(arrayAdapter);
        Bundle extras = getIntent().getExtras();
        int lives = extras.getInt("lives");
        ArrayList<String> playerNames = extras.getStringArrayList("players");
        for (String name : playerNames) {
            PlayerKiller playerKiller = new PlayerKiller(name, lives);
            players.add(playerKiller);
        }
        placement = players.size();
        player = players.get(index);
        text.setText(player.getName() + " " + n + " throw, lives left: " + player.getLives() + ", current score: " + scoreFinal + ", first player" );

    }
}