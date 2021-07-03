package com.example.syzer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.syzer.recycler_view_items.WordInList;
import com.example.syzer.recycler_view_items.listAdapter;
import com.example.syzer.request_items.RequestResult;
import com.example.syzer.request_items.RetrofitService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SynzerActivity extends AppCompatActivity {

    EditText word;
    Button enter;
    RecyclerView list;
    String enteringWord;
    RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synzer);
        getRequest();

        word = findViewById(R.id.text_editor);
        enter = findViewById(R.id.enter);
        list = findViewById(R.id.word_list);

        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.setAdapter(new listAdapter(mock()));

        enter.setOnClickListener(v -> {
            enteringWord = word.getText().toString();
            Toast.makeText(this, enteringWord, Toast.LENGTH_SHORT).show();
            getSimpleRequest(enteringWord);
        });
    }

    private List<WordInList> mock(){
        List<WordInList> list = new ArrayList<>();
        list.add(new WordInList("recycler"));
        list.add(new WordInList("view"));
        list.add(new WordInList("наконец"));
        list.add(new WordInList("заработал"));
        return list;
    }

    private void getRequest(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = (new Retrofit.Builder())
                .baseUrl("http://numbersapi.com/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        retrofitService = retrofit.create(RetrofitService.class);
    }

    private void getSimpleRequest(String enteringWord){
        retrofitService.number(Long.parseLong(enteringWord))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<RequestResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull RequestResult requestResult) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}