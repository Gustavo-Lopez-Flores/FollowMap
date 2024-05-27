package com.example.followmap.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.followmap.entities.Endereco;
import com.example.followmap.entities.EnderecoComCidade;

import java.util.List;

@Dao
public interface EnderecoDao {

    @Insert
    void insertAll(Endereco... enderecos);

    @Update
    void update(Endereco endereco);

    @Delete
    void delete(Endereco endereco);

    @Query("SELECT * FROM enderecos")
    List<Endereco> getAllEnderecos();

    @Query("SELECT * FROM enderecos WHERE enderecoId = :enderecoId LIMIT 1")
    Endereco getEndereco(int enderecoId);

    @Transaction
    @Query("SELECT * FROM enderecos")
    List<EnderecoComCidade> getEnderecosComCidade();

    @Transaction
    @Query("SELECT * FROM enderecos WHERE enderecoId = :enderecoId")
    EnderecoComCidade getEnderecoComCidadeById(int enderecoId);
}