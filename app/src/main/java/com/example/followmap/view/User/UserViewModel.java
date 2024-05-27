package com.example.followmap.view.User;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.followmap.database.LocalDatabase;
import com.example.followmap.entities.Usuario;

import java.util.List;

public class UserViewModel extends ViewModel {

    private final MutableLiveData<List<Usuario>> usuarios;
    private final LocalDatabase db;

    public UserViewModel() {
        db = LocalDatabase.getDatabase(getApplicationContext());
        usuarios = new MutableLiveData<>();
        carregarUsuarios();
    }

    private void carregarUsuarios() {
        List<Usuario> userList = db.usuarioDao().getAllUsuarios();
        usuarios.setValue(userList);
    }

    public LiveData<List<Usuario>> getUsuarios() {
        return usuarios;
    }
}