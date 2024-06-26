package com.example.followmap.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.followmap.entities.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {

    @Insert
    void insert(Usuario usuario);

    @Update
    void update(Usuario usuario);

    @Delete
    void delete(Usuario usuario);

    @Query("SELECT * FROM Usuario WHERE email = :email AND senha = :senha")
    Usuario login(String email, String senha);

    @Query("SELECT * FROM Usuario WHERE email = :email")
    Usuario getEmail(String email);

    @Query("SELECT * FROM Usuario")
    List<Usuario> getAllUsuarios();

    @Query("SELECT * FROM Usuario WHERE usuarioId = :id LIMIT 1")
    Usuario getUsuario(int id);
}


