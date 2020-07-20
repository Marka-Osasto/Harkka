package com.example.harkka;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DartsSetup extends AppCompatActivity {

    int startingPoints;
    TextView text;
    EditText player1;
    EditText player2;
    EditText player3;
    EditText player4;
    EditText player5;
    EditText player6;
    EditText player7;
    EditText player8;
    EditText player9;
    EditText player10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_darts_setup);
        text = findViewById(R.id.textView1);
        player1 = findViewById(R.id.Player1);
        player2 = findViewById(R.id.Player2);
        player3 = findViewById(R.id.Player3);
        player4 = findViewById(R.id.Player4);
        player5 = findViewById(R.id.Player5);
        player6 = findViewById(R.id.Player6);
        player7 = findViewById(R.id.Player7);
        player8 = findViewById(R.id.Player8);
        player9 = findViewById(R.id.Player9);
        player10 = findViewById(R.id.Player10);

    }
    public void points501(View v) {
        text.setText("501 selected");
        startingPoints = 501;
    }
    public void points301(View v) {
        text.setText("301 selected");
        startingPoints = 301;
    }
    public void start(View v) {
        String[] players = new String[9];
        players[0] = player1.getText().toString();
        players[1] = player2.getText().toString();
        players[2] = player3.getText().toString();
        players[3] = player4.getText().toString();
        players[4] = player5.getText().toString();
        players[5] = player6.getText().toString();
        players[6] = player7.getText().toString();
        players[7] = player8.getText().toString();
        players[8] = player9.getText().toString();
        players[9] = player10.getText().toString();
        ArrayList<String> playersList = new ArrayList<>();
        for (String i : players) {
            i.trim();
            if ()
        }
    }
}
