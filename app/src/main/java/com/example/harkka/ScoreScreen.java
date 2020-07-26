package com.example.harkka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.ListIterator;

public class ScoreScreen extends AppCompatActivity {

    private LinearLayout linearLayout;
    private ArrayList<String> placements = new ArrayList<>();
    private ListIterator<String> iterator;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);


        Intent intent = getIntent();
        placements = intent.getStringArrayListExtra("placements");
        iterator = placements.listIterator(placements.size());

        Context context = ScoreScreen.this;

        linearLayout = new LinearLayout(context);
        linearLayout = findViewById(R.id.score_layout);

        while (iterator.hasPrevious()) {
            String playerInfo = iterator.previous();
            button = new Button(context);
            button.setText(playerInfo);
            linearLayout.addView(button);
        }
    }
}