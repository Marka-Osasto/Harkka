package com.example.harkka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.ListIterator;

public class ScoreScreen extends AppCompatActivity {

    private LinearLayout linearLayout;
    private ArrayList<String> placements = new ArrayList<>();
    private ListIterator<String> iterator;
    private Button button;
    private ArrayList<String> scoreInfo;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);


        Intent intent = getIntent();
        placements = intent.getStringArrayListExtra("placements");
        scoreInfo = intent.getStringArrayListExtra("data");
        type = intent.getIntExtra("type", 0);

        final Context context = ScoreScreen.this;
        linearLayout = new LinearLayout(context);
        linearLayout = findViewById(R.id.score_layout);

        if (type == 0) {
            iterator = placements.listIterator();
            while (iterator.hasNext()) {
                String playerInfo = iterator.next();
                button = new Button(context);
                button.setText(playerInfo);
                linearLayout.addView(button);
            }
        }
        else if (type == 1) {
            iterator = placements.listIterator(placements.size());
            while (iterator.hasPrevious()) {
                String playerInfo = iterator.previous();
                button = new Button(context);
                button.setText(playerInfo);
                linearLayout.addView(button);
            }
        }


        button = new Button(context);
        button.setText("Return to main menu");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScoreScreen.this.finish();
            }
        });
        linearLayout.addView(button);
        button = new Button(context);
        button.setText("Export to CSV");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        linearLayout.addView(button);
    }
}