package com.example.syzer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Synzer extends AppCompatActivity {

    EditText word;
    Button enter;
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synzer);

        word = findViewById(R.id.text_editor);
        enter = findViewById(R.id.enter);
        list = findViewById(R.id.word_list);

        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        enter.setOnClickListener(v -> {
            String wordStr = word.getText().toString();
            Toast.makeText(this, wordStr, Toast.LENGTH_SHORT).show();
        });
    }

    private List<WordInList> mock(){
        List<WordInList> list = new ArrayList<>();
        list.add(new WordInList("привет"));
        return list;
    }
}