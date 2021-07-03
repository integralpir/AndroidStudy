package com.example.syzer.request_items;


import com.example.syzer.request_items.RequestResult;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("{number}?json")
    @Headers("Content-Type: application/json")
    Single<RequestResult> number(@Path("number") Long number);

    @GET("random/?json")
    @Headers("Content-Type: application/json")
    Single<RequestResult> number();

}
