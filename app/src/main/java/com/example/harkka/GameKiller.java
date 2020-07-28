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
    private TextView view;


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
        view = findViewById(R.id.typeView);
        scoreInput = findViewById(R.id.score);
        spinner = findViewById(R.id.multiplierSpinner);
        spinner.setAdapter(arrayAdapter);
        view.setText("Killer");
        Bundle extras = getIntent().getExtras();
        int lives = extras.getInt("lives");
        ArrayList<String> playerNames = extras.getStringArrayList("players");
        //Creates player objects
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
            //Removes a life if player's score is equal or less than the previous's
            if (scorePrevious >= scoreFinal) {
                player.removeLive();
                if (!player.checkAlive()) {
                    addToPlacementList();
                    players.remove(index);
                    index--;
                }
            }
            scorePrevious = scoreFinal;
            index++;
            //Starts list from the beginning when index gets to end
            if (index == players.size()) {
                index = 0;
            }
            //Saves game data to string for exporting
            Date current = Calendar.getInstance().getTime();
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy,HH:mm");
            String scoreInfo = player.getName() + "," + scoreFinal + "," + df.format(current);
            scoreList.add(scoreInfo);
            //Creates buttons where players can browse previous scores
            Button button = new Button(context);
            button.setText(player.getName() + " score: " + scoreFinal);
            linearLayout.addView(button);
            player = players.get(index);
            //Ends game if there is only one player remaining
            if (players.size() == 1) {
                button = new Button(context);
                current = Calendar.getInstance().getTime();
                df = new SimpleDateFormat("dd-MM-yyyy,HH:mm");
                scoreInfo = player.getName() + "," + scoreFinal + "," + df.format(current);
                scoreList.add(scoreInfo);
                button.setText(player.getName() + " score: " + scoreFinal);
                linearLayout.addView(button);
                addToPlacementList();

                Intent intent = new Intent(GameKiller.this, ScoreScreen.class);
                intent.putExtra("placements", placementList);
                intent.putExtra("data", scoreList);
                intent.putExtra("type", 1);
                this.finish();
                GameKiller.this.startActivity(intent);
            }
            
            scoreFinal = 0;
            n = 1;
        }
        else {
            multiplier = Integer.parseInt(spinner.getSelectedItem().toString());
            //Checks if score entry is empty
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

    // Adds attributes of PlayerKiller to a list as strings
    private void addToPlacementList() {
        player.setPlacement(placement);
        String playerInfo;
        playerInfo = player.getPlacement() + ". " + player.getName();
        placementList.add(playerInfo);
        placement--;
    }

}