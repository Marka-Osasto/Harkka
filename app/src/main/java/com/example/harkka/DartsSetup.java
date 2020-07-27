package com.example.harkka;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DartsSetup extends AppCompatActivity {

    private int startingScore;
    private TextView text;
    private Spinner spinner;
    private int playerCount;
    private Context context;
    private LinearLayout linearLayout;
    private ArrayAdapter<Integer> arrayAdapter;
    private ArrayList<String> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        context = DartsSetup.this;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_darts_setup);
        startingScore = 0;
        linearLayout = new LinearLayout(context);
        arrayAdapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item);
        text = findViewById(R.id.textView1);
        spinner = findViewById(R.id.spinner);
        linearLayout = findViewById(R.id.scrollView);
        spinner.setPrompt("Player count");
        Integer[] array = {2, 3, 4, 5, 6, 7, 8, 9, 10};
        arrayAdapter.addAll(array);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                linearLayout.removeAllViews();
                playerCount = Integer.parseInt(spinner.getSelectedItem().toString());
                for (int n = 1; n <= playerCount; n++) {
                    EditText nameField = new EditText(context);
                    nameField.setHint("Set player " + n + " name");
                    linearLayout.addView(nameField);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    // Sets starting points to 501
    public void points501(View v) {
        text.setText("501 selected");
        startingScore = 501;
    }
    // Sets starting points to 301
    public void points301(View v) {
        text.setText("301 selected");
        startingScore = 301;
    }
    // Starts darts game
    public void start(View v) {
        if (startingScore == 0) {
            text.setText("You haven't selected starting points!");
        }
        else {
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                if (linearLayout.getChildAt(i) instanceof EditText) {
                    String child = ((EditText) linearLayout.getChildAt(i)).getText().toString();
                    child = child.trim();
                    if (child.isEmpty()) {
                        text.setText("Name all players!");
                        players.clear();
                        return;
                    }
                    players.add(child);
                }
            }
            Intent nextActivity = new Intent(context, GameDarts.class);
            nextActivity.putExtra("score", startingScore);
            nextActivity.putExtra("players", players);
            this.finish();
            this.startActivity(nextActivity);
        }
    }
}
