package com.example.syzer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.syzer.data.AppDatabase;
import com.example.syzer.data.Number;
import com.example.syzer.data.NumberDao;
import com.example.syzer.recycler_view_items.ListAdapter;
import com.example.syzer.recycler_view_items.WordInList;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HistoryOfSearchActivity extends AppCompatActivity {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    AppDatabase database = App.getInstance().getDatabase();
    NumberDao dao = database.numberDao();

    RecyclerView list;
    ListAdapter listAdapter;


    List<Number> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_of_search);
        list = findViewById(R.id.word_list);

        dataList = new ArrayList<>();

        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listAdapter = new ListAdapter(dataList);
        list.setAdapter(listAdapter);
        getData();
    }

    private void getData(){
        compositeDisposable.add(dao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe((v) -> {
                Log.d("GETALL", "START");
                dataList.addAll(v);
                listAdapter.notifyDataSetChanged();
                Log.d("GETALL", "END");
            }));
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}