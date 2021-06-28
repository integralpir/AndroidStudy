package com.example.syzer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SynzerActivity extends AppCompatActivity {

    EditText word;
    Button enter;
    RecyclerView list;
    String enteringWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synzer);
        initRetrofit();


        word = findViewById(R.id.text_editor);
        enter = findViewById(R.id.enter);
        list = findViewById(R.id.word_list);

        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.setAdapter(new listAdapter(mock()));


        enter.setOnClickListener(v -> {
            enteringWord = word.getText().toString();
            Toast.makeText(this, enteringWord, Toast.LENGTH_SHORT).show();
        });
    }

    private void initRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new IntEx())
                .build();

        Retrofit retrofit = (new Retrofit.Builder())
                .baseUrl("http://numbersapi.com/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(client)
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        service.number(123L).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String a = "";
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(call.toString(), t.getMessage());
            }
        });
    }

    private List<WordInList> mock(){
        List<WordInList> list = new ArrayList<>();
        list.add(new WordInList("enteringWord"));
        return list;
    }
}