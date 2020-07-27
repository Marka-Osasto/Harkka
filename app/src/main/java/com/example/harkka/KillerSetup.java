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

public class KillerSetup extends AppCompatActivity {

    private int lives;
    private TextView text;
    private Spinner liveSpinner;
    private int playerCount;
    private Spinner playerSpinner;
    private Context context;
    private LinearLayout linearLayout;
    private ArrayAdapter<Integer> playerAdapter;
    private ArrayAdapter<Integer> liveAdapter;
    private ArrayList<String> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = KillerSetup.this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_killer_setup);
        linearLayout = new LinearLayout(context);
        playerAdapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item);
        liveAdapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item);
        text = findViewById(R.id.textViewLives);
        liveSpinner = findViewById(R.id.spinner3);
        linearLayout = findViewById(R.id.scrollViewKiller);
        playerSpinner = findViewById(R.id.spinner2);
        playerSpinner.setPrompt("Player count");

        Integer[] playerArray = {2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] liveArray = {1, 2, 3, 4, 5, 6};

        playerAdapter.addAll(playerArray);
        playerSpinner.setAdapter(playerAdapter);

        liveAdapter.addAll(liveArray);
        liveSpinner.setAdapter(liveAdapter);

        playerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                linearLayout.removeAllViews();
                playerCount = Integer.parseInt(playerSpinner.getSelectedItem().toString());
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
    
    // Starts the game when "Start"-button is pressed.
    public void start(View v) {
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
            lives = Integer.parseInt(liveSpinner.getSelectedItem().toString());
        }
        Intent nextActivity = new Intent(context, GameKiller.class);
        nextActivity.putExtra("lives", lives);
        nextActivity.putExtra("players", players);
        this.finish();
        this.startActivity(nextActivity);
    }
}
