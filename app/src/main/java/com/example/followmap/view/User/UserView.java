package com.example.followmap.view.User;

import android.os.Bundle;
import android.view.View;

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

    protected void onResume() {
        super.onResume();
        if(dbUsuarioID >= 0) {
            getDBUsuario();
        } else {
            binding.btnExcluirUsuario.setVisibility(View.GONE);
        }
    }
    private void getDBUsuario() {
        dbUsuario = db.usuarioDao().getUsuario(dbUsuarioID);
        binding.edtNomeUsuario.setText(dbUsuario.getNome());
        binding.edtEmailUsuario.setText(dbUsuario.getEmail());
        binding.edtSenhaUsuario.setText(dbUsuario.getSenha());
    }
}