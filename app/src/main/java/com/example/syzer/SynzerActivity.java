package com.example.syzer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.syzer.data.AppDatabase;
import com.example.syzer.data.Number;
import com.example.syzer.data.NumberDao;
import com.example.syzer.dialogs.ResultDialog;
import com.example.syzer.recycler_view_items.WordInList;
import com.example.syzer.recycler_view_items.ListAdapter;
import com.example.syzer.request_items.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SynzerActivity extends AppCompatActivity {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    RetrofitService retrofitService = App.getInstance().getRetrofitService();
    AppDatabase database = App.getInstance().getDatabase();
    NumberDao dao = database.numberDao();

    TextView result;
    EditText word;
    Button enter;

    String enteringWord;

    List<Number> numberList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synzer);

        word = findViewById(R.id.text_editor);
        enter = findViewById(R.id.enter);
        result = findViewById(R.id.result_txt);


        enter.setOnClickListener(v -> {
            enteringWord = word.getText().toString();

            if (!enteringWord.isEmpty()) getSimpleRequest(enteringWord);
            else getDefaultRequest();
        });
    }

    private void getSimpleRequest(String enteringWord){
        compositeDisposable.add(retrofitService.number(Long.parseLong(enteringWord))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((v) -> {
                    Number number = new Number();
                    number.number = v.getNumber().toString();
                    number.fact = v.getText();
                    dao.insert(number);
                    result.setText(v.getText());
                    Log.d("INSERT", "SUCCESS");
                }));
    }

    private void getDefaultRequest(){
        compositeDisposable.add(retrofitService.number()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((v) -> {
                    result.setText(v.getText());
                }));

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}