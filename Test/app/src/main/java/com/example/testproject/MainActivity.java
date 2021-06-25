package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton goToSettings;
    ImageButton goToEditors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToSettings = findViewById(R.id.go_to_settings);
        goToEditors = findViewById(R.id.go_to_editors);
        getSupportActionBar().hide();

        goToSettings.setOnClickListener(v -> {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        });

        goToEditors.setOnClickListener(v -> {
            Intent intent = new Intent(this, Editors.class);
            startActivity(intent);
        });
    }
}