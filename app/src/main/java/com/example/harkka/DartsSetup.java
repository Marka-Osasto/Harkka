package com.example.harkka;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DartsSetup extends AppCompatActivity {

    int startingPoints;
    TextView text;
    Spinner spinner;
    int playerCount;
    Context context;
    LinearLayout linearLayout;
    ArrayAdapter<Integer> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = DartsSetup.this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_darts_setup);
        linearLayout = new LinearLayout(context);
        arrayAdapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item);
        text = findViewById(R.id.textView1);
        spinner = findViewById(R.id.spinner);
        linearLayout = findViewById(R.id.scrollLayout);
        spinner.setPrompt("Player count");
        Integer[] array = {2, 3, 4, 5, 6, 7, 8, 9, 10};
        arrayAdapter.addAll(array);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                playerCount = Integer.parseInt(spinner.getSelectedItem().toString());
                for (int n = 1; n <= playerCount; n++) {
                    Button button = new Button(context);
                    button.setText("Player " + n);
                    linearLayout.addView(button);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
    }
}
