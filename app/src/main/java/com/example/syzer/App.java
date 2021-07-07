package com.example.syzer;

import android.app.Application;


import androidx.room.Room;

import com.example.syzer.data.AppDatabase;
import com.example.syzer.request_items.RetrofitService;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    public static App instance;

    private AppDatabase database;

    private RetrofitService retrofitService;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();

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

    public static App getInstance(){
        return instance;
    }

    public AppDatabase getDatabase(){
        return database;
    }

    public RetrofitService getRetrofitService(){
        return retrofitService;
    }
}
