package com.example.followmap.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.followmap.entities.Cidade;

import java.util.List;

@Dao
public interface CidadeDao {

    @Insert
    void insertAll(Cidade... cidades);

    @Update
    void update(Cidade cidade);

    @Delete
    void delete(Cidade cidade);

    @Query("SELECT * FROM cidades")
    List<Cidade> getAllCidades();

    @Query("SELECT * FROM cidades WHERE cidadeId = :cidadeId LIMIT 1")
    Cidade getCidade(int cidadeId);
}
