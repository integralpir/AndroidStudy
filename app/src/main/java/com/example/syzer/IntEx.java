package com.example.syzer;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class IntEx implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Log.d("jdjfjdjfdj", request.toString());
        return chain.proceed(request);
    }
}
