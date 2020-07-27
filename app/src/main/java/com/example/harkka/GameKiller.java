package com.example.harkka;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class GameKiller extends AppCompatActivity {

    private TextView text;
    private Spinner spinner;
    private String[] multiplierArray = {"1", "2", "3"};
    private EditText scoreInput;
    private ArrayList<PlayerKiller> players = new ArrayList<>();
    private PlayerKiller player;
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

        context = GameKiller.this;

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

    // Moves the game to the next throw. If player has thrown 3 darts then the game moves to the next player
    public void next(View v) {

        int multiplier;
        int score;

        if (n >= 3) {
            multiplier = Integer.parseInt(spinner.getSelectedItem().toString());
            score = Integer.parseInt(scoreInput.getText().toString());
            scoreFinal += multiplier * score;
            if (scorePrevious >= scoreFinal) {
                player.removeLive();
                if (!player.checkAlive()) {
                    player.setPlacement(placement);
                    addToPlacementList();
                    placement--;
                }
            }
            scorePrevious = scoreFinal;
            index++;
            if (index == players.size()) {
                index = 0;
            }
            Button button = new Button(context);
            Date current = Calendar.getInstance().getTime();
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy,HH:mm");
            String scoreInfo = player.getName() + "," + scoreFinal + "," + df.format(current);
            scoreList.add(scoreInfo);
            button.setText(player.getName() + " score: " + scoreFinal);
            linearLayout.addView(button);
            player = players.get(index);
            
            player = nextAlivePlayer();
            scoreFinal = 0;
            n = 1;
        }
        else {
            multiplier = Integer.parseInt(spinner.getSelectedItem().toString());
            try {
                score = Integer.parseInt(scoreInput.getText().toString());
            }
            catch (Exception NumberFormatException) {
                score = 0;
                n--;
            }
            scoreFinal += multiplier * score;
            n++;
        }
        if (scorePrevious == -1) {
            text.setText(player.getName() + " " + n + " throw, lives left: " + player.getLives() + ", current score: " + scoreFinal + ", first player");
        } else {
            text.setText(player.getName() + " " + n + " throw, lives left: " + player.getLives() + ", current score: " + scoreFinal + " previous score: " + scorePrevious);
        }

    }

    // Returns next alive player. If there isn't any expect the current one the game ends.
    private PlayerKiller nextAlivePlayer() {

        if (placement == 1) {
            player.setPlacement(placement);
            addToPlacementList();

            Intent intent = new Intent(GameKiller.this, ScoreScreen.class);
            intent.putExtra("placements", placementList);
            intent.putExtra("data", scoreList);
            this.finish();
            GameKiller.this.startActivity(intent);
        }

        for (int i = index; i < players.size(); i++) {
            player = players.get(i);
            if (player.checkAlive()) {
                return player;
            }
        }
        for (int i = 0; i <= index; i++) {
            player = players.get(i);
            if (player.checkAlive()) {
                return player;
            }
        }

        return player = players.get(index);
    }

    // Adds attributes of PlayerKiller to a list as strings
    private void addToPlacementList() {
        String playerInfo;
        playerInfo = player.getPlacement() + ". " + player.getName();
        placementList.add(playerInfo);
    }

}