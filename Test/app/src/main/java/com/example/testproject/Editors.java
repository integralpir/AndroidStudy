package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Editors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editors);
        getSupportActionBar().hide();
    }
}