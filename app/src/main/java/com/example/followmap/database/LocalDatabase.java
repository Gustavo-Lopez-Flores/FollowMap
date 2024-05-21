package com.example.followmap.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.followmap.dao.UsuarioDao;
import com.example.followmap.entities.Usuario;

@Database(entities = {Usuario.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase{
    public abstract UsuarioDao usuarioDao();
    private static LocalDatabase INSTANCE;

    public static LocalDatabase getDatabase(Context context) {
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDatabase.class, "MeuBD").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
