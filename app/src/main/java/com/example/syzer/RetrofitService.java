package com.example.syzer;

import android.database.Observable;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("{number}")
    Call<String> number(@Path("number") Long number);

}
