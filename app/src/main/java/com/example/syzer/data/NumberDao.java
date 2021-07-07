package com.example.syzer.data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface NumberDao {

    @Query("SELECT * FROM number")
    Single<List<Number>> getAll();

    @Query("SELECT * FROM number WHERE id = :id")
    Single<Number> getById(Long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Number number);

    @Update
    Completable update(Number number);

    @Delete
    Completable delete(Number number);

}
