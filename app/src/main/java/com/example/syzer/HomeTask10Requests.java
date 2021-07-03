package com.example.syzer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.syzer.request_items.for_hometask.ListResources;
import com.example.syzer.request_items.for_hometask.ListUsers;
import com.example.syzer.request_items.for_hometask.QuestInterface;
import com.example.syzer.request_items.for_hometask.Resource;
import com.example.syzer.request_items.for_hometask.User;
import com.google.gson.Gson;

import java.util.HashMap;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeTask10Requests extends AppCompatActivity {

    Button getListUsers;
    Button getSingleUser;
    Button singleUserNotFound;
    Button getListResources;
    Button getSingleResource;
    Button singleResourceNotFound;
    Button createUser;
    Button updateUserPut;
    Button updateUserPatch;
    Button deleteButton;

    TextView testText;
    QuestInterface questInterface;

    HashMap<String, String> parametersForPutAndPatch;
    String nameForPost;
    String jobForPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_task10_requests);

        getListUsers = findViewById(R.id.get_list_users_id);
        getSingleUser = findViewById(R.id.get_single_user);
        singleUserNotFound = findViewById(R.id.single_user_not_found_id);
        getListResources = findViewById(R.id.get_list_resources_id);
        getSingleResource = findViewById(R.id.get_single_resources_id);
        singleResourceNotFound = findViewById(R.id.single_resource_not_found_id);
        createUser = findViewById(R.id.post_new_user_id);
        updateUserPut = findViewById(R.id.put_user_id);
        updateUserPatch = findViewById(R.id.patch_user_id);
        deleteButton = findViewById(R.id.delete_button_id);

        testText = findViewById(R.id.test_text);
        get10Requests();

        getListUsers.setOnClickListener(v -> getListUsers());
        getSingleUser.setOnClickListener(v -> getSingleUser());
        singleUserNotFound.setOnClickListener(v -> singleUserNotFound());
        getListResources.setOnClickListener(v -> getListResources());
        getSingleResource.setOnClickListener(v -> getSingleResource());
        singleResourceNotFound.setOnClickListener(v -> singleResourceNotFound());
        createUser.setOnClickListener(v -> createUser());
        updateUserPut.setOnClickListener(v -> updateUserPut());
        updateUserPatch.setOnClickListener(v -> updateUserPatch());
        deleteButton.setOnClickListener(v -> deleteUser());
    }

    private void get10Requests(){
        parametersForPutAndPatch = new HashMap<>();
        parametersForPutAndPatch.put("name", "morpheus");
        parametersForPutAndPatch.put("job", "zion resident");

        nameForPost = "morpheus";
        jobForPost = "leader";

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        questInterface = retrofit.create(QuestInterface.class);
    }

    private void getListUsers(){
        questInterface.getListUsers(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ListUsers>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ListUsers listUsers) {
                        testText.setText("GET LIST USERS SUCCESS");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    private void getSingleUser(){
        questInterface.getSingleUser(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull User user) {
                        testText.setText("GET SINGLE USER SUCCESS");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    private void singleUserNotFound(){
        questInterface.singleUserNotFound(23)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        testText.setText("SINGLE USER NOT FOUND SUCCESS");
                    }
                });
    }

    private void getListResources(){
        questInterface.getListResource()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ListResources>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ListResources listResources) {
                        testText.setText("GET LIST RESOURCE SUCCESS");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    private void getSingleResource(){
        questInterface.getSingleResource(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Resource>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Resource resource) {
                        testText.setText("GET SINGLE RESOURCE SUCCESS");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    private void singleResourceNotFound(){
        questInterface.singleResourceNotFound(23)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        testText.setText("SINGLE RESOURCE NOT FOUND SUCCESS");
                    }
                });
    }

    private void createUser(){
        questInterface.createNewUser(nameForPost, jobForPost)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {


                    }

                    @Override
                    public void onComplete() {
                        testText.setText("POST USER SUCCESS");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    private void updateUserPut(){
        questInterface.putUser(2, parametersForPutAndPatch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull User user) {
                        testText.setText("PUT USER SUCCESS");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    private void updateUserPatch(){
        questInterface.patchUser(2, parametersForPutAndPatch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull User user) {
                        testText.setText("PATCH USER SUCCESS");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    private void deleteUser(){
        questInterface.deleteUser(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        testText.setText("DELETE SUCCESS");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

}