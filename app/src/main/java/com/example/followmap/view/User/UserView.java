package com.example.followmap.view.User;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.followmap.database.LocalDatabase;
import com.example.followmap.databinding.ActivityUserViewBinding;
import com.example.followmap.entities.Usuario;

public class UserView extends AppCompatActivity {
    private LocalDatabase db;
    private ActivityUserViewBinding binding;
    private int dbUsuarioID;
    private Usuario dbUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());

        dbUsuarioID = getIntent().getIntExtra("USUARIO_SELECIONADO_ID", -1);
    }

}