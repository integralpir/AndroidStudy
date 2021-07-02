package com.example.syzer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.syzer.dialogs.EditorsDialog;
import com.example.syzer.dialogs.QuestionsDialog;

import okhttp3.Interceptor;

public class MainActivity extends AppCompatActivity {

    Button synzer;
    Button settings;
    Button questions;
    Button editors;
    Button homeTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        synzer = findViewById(R.id.go_to_synzer);
        settings = findViewById(R.id.go_to_settings);
        questions = findViewById(R.id.go_to_questions);
        editors = findViewById(R.id.go_to_editors);
        homeTask = findViewById(R.id.go_to_request_home_task);

        synzer.setOnClickListener(v -> {
            Intent intent = new Intent(this, SynzerActivity.class);
            startActivity(intent);
        });

        settings.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });

        questions.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuestionsDialog.class);
            startActivity(intent);
        });

        editors.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditorsDialog.class);
            startActivity(intent);
        });

        homeTask.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeTask10Requests.class);
            startActivity(intent);
        });
    }
}