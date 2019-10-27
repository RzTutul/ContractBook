package com.rakib.phonebook;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ContractPojo.class},version = 1)
public abstract class ContractDatabse extends RoomDatabase {
public abstract ContractDao getContratDao();
private static   ContractDatabse db;


    public static ContractDatabse getInstance(Context context)
    {
        if (db == null) {
            db = Room.databaseBuilder(context, ContractDatabse.class, "Contract_DB").allowMainThreadQueries().build();

            return db;
        }
        return db;
    }
}
