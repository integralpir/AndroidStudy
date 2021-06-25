package com.example.syzer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button synzer;
    Button settings;
    Button questions;
    Button editors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        synzer = findViewById(R.id.go_to_synzer);
        settings = findViewById(R.id.go_to_settings);
        questions = findViewById(R.id.go_to_questions);
        editors = findViewById(R.id.go_to_editors);

        getRightSize(synzer);
        getRightSize(settings);
        getRightSize(questions);
        getRightSize(editors);

        synzer.setOnClickListener(v -> {
            Intent intent = new Intent(this, Synzer.class);
            startActivity(intent);
        });

        settings.setOnClickListener(v -> {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        });

        questions.setOnClickListener(v -> {
            Intent intent = new Intent(this, Questions.class);
            startActivity(intent);
        });

        editors.setOnClickListener(v -> {
            Intent intent = new Intent(this, Editors.class);
            startActivity(intent);
        });
    }

    protected void getRightSize(Button button){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels / 2;
        int height = displayMetrics.heightPixels / 2;
        button.setWidth(width);
        button.setHeight(height);
    }
}