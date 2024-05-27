package com.example.followmap.view.User;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.followmap.database.LocalDatabase;
import com.example.followmap.entities.Usuario;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Usuario>> usuarios;
    private final LocalDatabase db;

    public UserViewModel(Application application) {
        super(application);
        db = LocalDatabase.getDatabase(application);
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