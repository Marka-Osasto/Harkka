package com.example.harkka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
    }
    public void newDartsMatch(View v) {
        Intent nextActivity = new Intent(context, DartsSetup.class);
        this.startActivity(nextActivity);
    }
    public void newKillerMatch(View v) {
        Intent nextActivity = new Intent(context, KillerSetup.class);
        this.startActivity(nextActivity);
    }
}