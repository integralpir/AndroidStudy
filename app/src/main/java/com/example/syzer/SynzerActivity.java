package com.example.syzer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.syzer.data.AppDatabase;
import com.example.syzer.data.Number;
import com.example.syzer.data.NumberDao;
import com.example.syzer.dialogs.ResultDialog;
import com.example.syzer.recycler_view_items.WordInList;
import com.example.syzer.recycler_view_items.listAdapter;
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

    EditText word;
    Button enter;
    RecyclerView list;
    String enteringWord;

    List<WordInList> listForRecyclerView = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synzer);

        word = findViewById(R.id.text_editor);
        enter = findViewById(R.id.enter);
        list = findViewById(R.id.word_list);

        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.setAdapter(new listAdapter(getDataBaseInfo()));

        enter.setOnClickListener(v -> {
            enteringWord = word.getText().toString();
            Number number = new Number();
            number.id = 1;

            if (!enteringWord.isEmpty()) {
                number.number = "RANDOM";
                getSimpleRequest(enteringWord, number);
            } else {
                number.number = enteringWord;
                getDefaultRequest(number);
            }

            insertNumberIntoDataBase(number);
            list.setAdapter(new listAdapter(getDataBaseInfo()));

            /*
            Intent intent = new Intent(this, ResultDialog.class);
            intent.putExtra("result", number.fact);
            startActivity(intent);

             */
        });
    }


    private List<WordInList> getDataBaseInfo(){
        compositeDisposable.add(dao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((v) -> {
                    for (Number n : v){
                        listForRecyclerView.add(new WordInList(n.number));
                    }
                }));

        return listForRecyclerView;
    }

    private void insertNumberIntoDataBase(Number num){
        compositeDisposable.add(dao.insert(num)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe());
    }

    private void getSimpleRequest(String enteringWord, Number num){
        compositeDisposable.add(retrofitService.number(Long.parseLong(enteringWord))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((v) -> {
                    num.fact = v.getText();
                }));
    }

    private void getDefaultRequest(Number num){
        compositeDisposable.add(retrofitService.number()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((v) -> {
                    num.fact = v.getText();
                }));

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}