package com.example.syzer.dialogs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.syzer.R;

public class ResultDialog extends AppCompatActivity {

    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_dialog);
        Bundle arguments = getIntent().getExtras();

        resultText = findViewById(R.id.result_view);
        resultText.setText(arguments.get("result").toString());
    }
}