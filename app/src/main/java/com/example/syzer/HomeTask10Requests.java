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
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeTask10Requests extends AppCompatActivity {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

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
    HashMap<String, String> parametersForPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_task10_requests);

        Button getListUsers = findViewById(R.id.get_list_users_id);
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

        parametersForPost = new HashMap<>();
        parametersForPost.put("name", "morpheus");
        parametersForPost.put("job", "leader");

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
        compositeDisposable.add(questInterface.getListUsers(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((v) -> testText.setText("GET LIST USERS SUCCESS")));
    }

    private void getSingleUser(){
        compositeDisposable.add(questInterface.getSingleUser(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((v) -> testText.setText("GET SINGLE USER SUCCESS")));
    }

    private void singleUserNotFound(){
        compositeDisposable.add(questInterface.singleUserNotFound(23)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {},(v) -> testText.setText("SINGLE USER NOT FOUND SUCCESS")));
    }

    private void getListResources(){
        compositeDisposable.add(questInterface.getListResource()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((v) -> testText.setText("GET LIST RESOURCES SUCCESS")));
    }

    private void getSingleResource(){
        compositeDisposable.add(questInterface.getSingleResource(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((v) -> testText.setText("GET SINGLE RESOURCE SUCCESS")));
    }

    private void singleResourceNotFound(){
        compositeDisposable.add(questInterface.singleResourceNotFound(23)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {}, (v) -> testText.setText("SINGLE RESOURCE NOT FOUND SUCCESS")));
    }

    private void createUser(){
        compositeDisposable.add(questInterface.createNewUser(parametersForPost)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> testText.setText("CREATE USER SUCCESS")));
    }

    private void updateUserPut(){
        compositeDisposable.add(questInterface.putUser(2, parametersForPutAndPatch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((v) -> testText.setText("PUT USER SUCCESS")));
    }

    private void updateUserPatch(){
        compositeDisposable.add(questInterface.patchUser(2, parametersForPutAndPatch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((v) -> testText.setText("PATCH USER SUCCESS")));
    }

    private void deleteUser(){
        compositeDisposable.add(questInterface.deleteUser(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> testText.setText("DELETE USER SUCCESS")));
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}