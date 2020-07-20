package com.example.harkka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Context context;
    Spinner fileSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        fileSpinner = findViewById(R.id.fileSpinner);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.addAll(context.fileList());
        fileSpinner.setAdapter(arrayAdapter);
    }
    public void continueGame(View v) {
        String fileName = fileSpinner.getSelectedItem().toString();
//        Data.import(fileName);
    }
    public void newDartsMatch(View v) {
        Intent nextActivity = new Intent(context, GameDarts.class);
        this.startActivity(nextActivity);
    }
    public void newKillerMatch(View v) {
        Intent nextActivity = new Intent(context, Game.class);
        this.startActivity(nextActivity);
    }
}