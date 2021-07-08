package com.example.syzer.data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Number {

    @PrimaryKey
    public long id;

    public String number;

    public String fact;

}
