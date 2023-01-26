package com.example.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

public abstract class RoomDB extends RoomDatabase {
    private static RoomDB db;

    private static String DB_NAME = "words";

    public synchronized static RoomDB getInstance(Context context)
    {
        if (db == null)
        {
            db = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }

    public abstract MainDao mainDao();
}

