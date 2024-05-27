package com.example.followmap.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.followmap.dao.UsuarioDao;
import com.example.followmap.dao.EnderecoDao;
import com.example.followmap.dao.CidadeDao;
import com.example.followmap.entities.Usuario;
import com.example.followmap.entities.Endereco;
import com.example.followmap.entities.Cidade;

@Database(entities = {Usuario.class, Cidade.class, Endereco.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase{
    public abstract UsuarioDao usuarioDao();
    public abstract CidadeDao cidadeDao();
    public abstract EnderecoDao enderecoDao();
    private static LocalDatabase INSTANCE;

    public static LocalDatabase getDatabase(Context context) {
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDatabase.class, "MeuBD").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
