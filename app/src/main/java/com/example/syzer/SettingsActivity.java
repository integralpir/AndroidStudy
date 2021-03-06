package com.example.syzer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.syzer.data.AppDatabase;
import com.example.syzer.data.NumberDao;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SettingsActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    AppDatabase database = App.getInstance().getDatabase();
    NumberDao dao = database.numberDao();

    Button langSelector;
    Button themSelector;
    Button dropDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        langSelector = findViewById(R.id.language_selector);
        themSelector = findViewById(R.id.theme_selector);
        dropDataBase = findViewById(R.id.drop_history);

        dropDataBase.setOnClickListener(v -> {
            dropDataBase();
        });

        themSelector.setOnClickListener(this::showPopupMenuTheme);

        langSelector.setOnClickListener(this::showPopupMenuLanguage);
    }

    private void dropDataBase(){
        compositeDisposable.add(dao.deleteAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(() -> {
                Log.d("DELETE", "SUCCESS");
            }, (v) -> {
                Log.d("DELETE", v.getMessage());
            }));
    }

    private void showPopupMenuLanguage(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.language);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.russian_language:
                        Toast.makeText(getApplicationContext(), "???? ?????????????? ?????????????? ????????", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getApplicationContext(), "???? ?????????????? ?????????????? ????????", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.theme_night_item:
                        Toast.makeText(getApplicationContext(), "???? ?????????????? ???????????? ????????", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}