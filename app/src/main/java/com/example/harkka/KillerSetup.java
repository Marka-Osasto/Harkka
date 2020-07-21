package com.example.harkka;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class KillerSetup extends AppCompatActivity {

    int lives;
    TextView text;
    SeekBar seekBar;
    int playerCount;
    Spinner spinner;
    Context context;
    LinearLayout linearLayout;
    ArrayAdapter<Integer> arrayAdapter;
    ArrayList<String> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = KillerSetup.this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_killer_setup);
        linearLayout = new LinearLayout(context);
        arrayAdapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item);
        text = findViewById(R.id.textViewLives);
        seekBar = findViewById(R.id.liveBar);
        linearLayout = findViewById(R.id.scrollViewKiller);
        spinner = findViewById(R.id.spinner2);
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
            lives = seekBar.getProgress() + 1;
        }
        Intent nextActivity = new Intent(context, GameKiller.class);
        nextActivity.putExtra("lives", lives);
        nextActivity.putExtra("players", players);
        this.startActivity(nextActivity);
    }
}
