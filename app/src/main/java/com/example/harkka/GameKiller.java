package com.example.harkka;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.ListIterator;


public class GameKiller extends AppCompatActivity {

    private TextView text;
    private Spinner spinner;
    private Context context;
    private String[] multiplierArray = {"1", "2", "3"};
    private ArrayAdapter<String> arrayAdapter;
    private Button nextThrow;
    private EditText scoreInput;
    private TextView scoreText;
    private ArrayList<PlayerKiller> players = new ArrayList<>();
    private ListIterator<PlayerKiller> iterator = players.listIterator();
    private int n;
    private PlayerKiller player;
    private int scoreFinal;
    private int previousScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_killer);

        n = 1;
        scoreFinal = 0;
        previousScore = -1;
        context = GameKiller.this;
        arrayAdapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.addAll(multiplierArray);
        text = findViewById(R.id.textView2);
        scoreText = findViewById(R.id.textView4);
        scoreInput = findViewById(R.id.score);
        spinner = findViewById(R.id.multiplierSpinner);
        nextThrow = findViewById(R.id.button8);
        spinner.setAdapter(arrayAdapter);
        Bundle extras = getIntent().getExtras();
        int lives = extras.getInt("lives");
        ArrayList<String> playerNames = extras.getStringArrayList("players");

        for (String name : playerNames) {
            PlayerKiller playerKiller = new PlayerKiller(name, lives);
        }

        while (iterator.hasPrevious()) {
            iterator.previous();
        }
        player = iterator.next();
        text.setText(player.getName() + " " + n + " throw, lives left: " + player.getLives());
    }

    public void next(View v) {
        int multiplier;
        if (n >= 3) {
            if (iterator.hasNext()) {
                n = 1;
                multiplier = Integer.parseInt(spinner.getSelectedItem().toString());
                int score = Integer.parseInt(scoreInput.getText().toString());
                scoreFinal += score * multiplier;
                player.addScore(scoreFinal);
                if (previousScore >= scoreFinal) {
                    player.removeLive();
                }
                previousScore = scoreFinal;
            }
            else {
                while (iterator.hasPrevious()) {
                    iterator.previous();
                }
                player = iterator.next();
                n = 1;
                multiplier = Integer.parseInt(spinner.getSelectedItem().toString());
                int score = Integer.parseInt(scoreInput.getText().toString());
                scoreFinal += score * multiplier;
                if (previousScore >= scoreFinal) {
                    player.removeLive();
                }
                previousScore = scoreFinal;
                player.addScore(scoreFinal);
            }
            scoreFinal = 0;
            text.setText(player.getName() + " " + n + " throw, lives left: " + player.getLives());
            scoreText.setText("Previous score " + previousScore);
            player = iterator.next();
        }
        else {
            multiplier = Integer.parseInt(spinner.getSelectedItem().toString());
            int score = Integer.parseInt(scoreInput.getText().toString());
            scoreFinal += score * multiplier;
            text.setText(player.getName() + " " + n + " throw, lives left: " + player.getLives());
            n++;
        }
    }
}