package com.example.syzer.data;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Number.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NumberDao numberDao();
}
