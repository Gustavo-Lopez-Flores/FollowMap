package com.example.followmap.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class LocalDatabase extends RoomDatabase{
    private static LocalDatabase INSTANCE;

    public static LocalDatabase getDatabase(Context context) {
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDatabase.class, "MeuBD").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
