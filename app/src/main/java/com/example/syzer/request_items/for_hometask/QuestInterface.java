package com.example.syzer.request_items.for_hometask;

import java.util.HashMap;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QuestInterface {
    @GET("/api/users")
    Single<ListUsers> getListUsers(@Query("page") int page);

    @GET("/api/users/{id}")
    Single<User> getSingleUser(@Path("id") int id);

    @GET("/api/users/{id}")
    Completable singleUserNotFound(@Path("id") int id);

    @GET("/api/unknown")
    Single<ListResources> getListResource();

    @GET("/api/unknown/{id}")
    Single<Resource> getSingleResource(@Path("id") int id);

    @GET("/api/unknown/{id}")
    Completable singleResourceNotFound(@Path("id") int id);

    @POST("/api/users")
    Completable createNewUser(@Body HashMap<String, String> parameters);

    @PUT("/api/users/{id}")
    Single<User> putUser(@Path("id") int id, @Body HashMap<String, String> parameters);

    @PATCH("/api/users/{id}")
    Single<User> patchUser(@Path("id") int id, @Body HashMap<String, String> parameters);

    @DELETE("/api/users/{id}")
    Completable deleteUser(@Path("id") int id);
}
