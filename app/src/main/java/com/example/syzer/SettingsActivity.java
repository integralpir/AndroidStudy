package com.example.syzer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    Button langSelector;
    Button themSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        langSelector = findViewById(R.id.language_selector);
        themSelector = findViewById(R.id.theme_selector);

        themSelector.setOnClickListener(v -> {
            showPopupMenuTheme(v);
        });

        langSelector.setOnClickListener(v -> {
            showPopupMenuLanguage(v);
        });
    }

    private void showPopupMenuLanguage(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.language);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.russian_language:
                        Toast.makeText(getApplicationContext(), "Вы выбрали Русский язык", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.english_language:
                        Toast.makeText(getApplicationContext(), "English language is your choice", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }

    private void showPopupMenuTheme(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.theme);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.theme_day_item:
                        Toast.makeText(getApplicationContext(), "Вы выбрали дневную тему", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.theme_night_item:
                        Toast.makeText(getApplicationContext(), "Вы выбрали ночную тему", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }
}