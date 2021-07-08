package com.example.syzer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.syzer.dialogs.EditorsDialog;

public class MainActivity extends AppCompatActivity {

    Button synzer;
    Button settings;
    Button history;
    Button editors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        synzer = findViewById(R.id.go_to_synzer);
        settings = findViewById(R.id.go_to_settings);
        history = findViewById(R.id.go_to_history);
        editors = findViewById(R.id.go_to_editors);

        synzer.setOnClickListener(v -> {
            Intent intent = new Intent(this, SynzerActivity.class);
            startActivity(intent);
        });

        settings.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });

        history.setOnClickListener(v -> {
            Intent intent = new Intent(this, HistoryOfSearchActivity.class);
            startActivity(intent);
        });

        editors.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditorsDialog.class);
            startActivity(intent);
        });
    }
}