package com.example.followmap.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.followmap.dao.UsuarioDao;
import com.example.followmap.dao.EnderecoDao;
import com.example.followmap.dao.CidadeDao;
import com.example.followmap.entities.Usuario;
import com.example.followmap.entities.Endereco;
import com.example.followmap.entities.Cidade;

@Database(entities = {Usuario.class, Cidade.class, Endereco.class}, version = 2)
public abstract class LocalDatabase extends RoomDatabase {
    public abstract UsuarioDao usuarioDao();
    public abstract CidadeDao cidadeDao();
    public abstract EnderecoDao enderecoDao();
    private static LocalDatabase INSTANCE;

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Cidade` (`cidadeId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `cidade` TEXT, `estado` TEXT)");
            database.execSQL("CREATE TABLE IF NOT EXISTS `Endereco` (`enderecoId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `descricao` TEXT, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `cidadeId` INTEGER NOT NULL, FOREIGN KEY(`cidadeId`) REFERENCES `Cidade`(`cidadeId`) ON UPDATE NO ACTION ON DELETE CASCADE)");
        }
    };

    public static LocalDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (LocalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    LocalDatabase.class, "MeuBD")
                            .addMigrations(MIGRATION_1_2)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
