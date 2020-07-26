package com.example.harkka;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.ListIterator;


public class GameKiller extends AppCompatActivity {

    private TextView text;
    private Spinner spinner;
    private String[] multiplierArray = {"1", "2", "3"};
    private EditText scoreInput;
    private ArrayList<PlayerKiller> players = new ArrayList<>();
    private ListIterator<PlayerKiller> iterator = players.listIterator();
    private PlayerKiller player;
    private int scoreFinal;
    private int n;
    private int scorePrevious;
    private int placement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_killer);

        n = 1;
        scorePrevious = -1;
        scoreFinal = 0;
        Context context = GameKiller.this;
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
            iterator.add(playerKiller);
        }
        placement = players.size();
        while (iterator.hasPrevious()) {
            iterator.previous();
        }
        player = iterator.next();
        text.setText(player.getName() + " " + n + " throw, lives left: " + player.getLives() + ", current score: " + scoreFinal + ", first player" );
    }

    public void next(View v) {

        int multiplier;
        int score;

        if (n >= 3) {
            multiplier = Integer.parseInt(spinner.getSelectedItem().toString());
            score = Integer.parseInt(scoreInput.getText().toString());
            scoreFinal += multiplier * score;
            if (scorePrevious >= scoreFinal) {
                player.removeLive();
            }
            scorePrevious = scoreFinal;
            if (!iterator.hasNext()) {
                while (iterator.hasPrevious()) {
                    iterator.previous();
                }
            }
            player = iterator.next();
            if (player.checkAlive()) {
                player.setPlacement(placement);
                placement--;
                while (iterator.hasNext()) {
                    player = iterator.next();
                    if (player.checkAlive()) {
                        break;
                    }
                }
                while (iterator.hasPrevious()) {
                    iterator.previous();
                }
            }
            scoreFinal = 0;
            n = 1;
        }
        else {
            multiplier = Integer.parseInt(spinner.getSelectedItem().toString());
            score = Integer.parseInt(scoreInput.getText().toString());
            scoreFinal += multiplier * score;
            n++;
        }
        if (scorePrevious == -1) {
            text.setText(player.getName() + " " + n + " throw, lives left: " + player.getLives() + ", current score: " + scoreFinal + ", first player");
        } else {
            text.setText(player.getName() + " " + n + " throw, lives left: " + player.getLives() + ", current score: " + scoreFinal + " previous score: " + scorePrevious);
        }
    }
}