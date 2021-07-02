package com.example.syzer.request_items;


import com.example.syzer.request_items.RequestResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("{number}")
    Observable<RequestResult> number(@Path("number") Long number);

    @GET("{number}/{type}")
    Observable<RequestResult> number(@Path("number") Long number, @Path("type") String type);

}
