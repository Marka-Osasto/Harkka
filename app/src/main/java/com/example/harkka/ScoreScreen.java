package com.example.harkka;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Output;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ListIterator;

public class ScoreScreen extends AppCompatActivity {

    private LinearLayout linearLayout;
    private ArrayList<String> placements = new ArrayList<>();
    private ListIterator<String> iterator;
    private Button button;
    private ArrayList<String> scoreInfo;
    private String type;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);


        Intent intent = getIntent();
        placements = intent.getStringArrayListExtra("placements");
        scoreInfo = intent.getStringArrayListExtra("data");
        type = intent.getStringExtra("type");
        context = ScoreScreen.this;
        linearLayout = new LinearLayout(context);
        linearLayout = findViewById(R.id.score_layout);
        //Checks if game mode is killer or darts and creates iterator moving the list in correct order
        if (type.equals("Darts")) {
            iterator = placements.listIterator();
            while (iterator.hasNext()) {
                String playerInfo = iterator.next();
                button = new Button(context);
                button.setText(playerInfo);
                linearLayout.addView(button);
            }
        }
        else if (type.equals("Killer")) {
            iterator = placements.listIterator(placements.size());
            while (iterator.hasPrevious()) {
                String playerInfo = iterator.previous();
                button = new Button(context);
                button.setText(playerInfo);
                linearLayout.addView(button);
            }
        }

        //Creates score screen buttons
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
                Export.exportToCSV(placements, scoreInfo, type, context);
                ScoreScreen.this.finish();
            }
        });
        linearLayout.addView(button);
    }
}
//Exports game data to a CSV file
class Export {
    static void exportToCSV(ArrayList<String> placementList, ArrayList<String> scoreInfo, String type, Context context) {
        Date current = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH,mm");
        String fileName = type + sdf.format(current) + ".csv";
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(context.openFileOutput(fileName, context.MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (String placement : placementList) {
            try {
                osw.write(placement + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            osw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String score : scoreInfo) {
            try {
                osw.write(score + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}