package com.example.syzer.data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface NumberDao {

    @Query("SELECT * FROM number")
    Observable<List<Number>> getAll();

    @Query("DELETE FROM number")
    Completable deleteAll();

    @Query("SELECT COUNT(*) FROM number")
    Long countNumber();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Number number);

    @Update
    Completable update(Number number);

    @Delete
    Completable delete(Number number);

}
