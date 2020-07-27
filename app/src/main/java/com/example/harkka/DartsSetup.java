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

    int startingPoints;
    TextView text;
    Spinner spinner;
    int playerCount;
    Context context;
    LinearLayout linearLayout;
    ArrayAdapter<Integer> arrayAdapter;
    ArrayList<String> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = DartsSetup.this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_darts_setup);
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
        startingPoints = 501;
    }
    // Sets starting points to 301
    public void points301(View v) {
        text.setText("301 selected");
        startingPoints = 301;
    }
    // Starts darts game
    public void start(View v) {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            if (linearLayout.getChildAt(i) instanceof EditText) {
                String child = ((EditText) linearLayout.getChildAt(i)).getText().toString();
                players.add(child);
            }
        }
        Intent nextActivity = new Intent(context, GameDarts.class);
        nextActivity.putExtra("points", startingPoints);
        nextActivity.putExtra("players", players);
        this.finish();
        this.startActivity(nextActivity);
    }
}
