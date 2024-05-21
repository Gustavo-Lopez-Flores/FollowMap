package com.example.followmap.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.followmap.database.LocalDatabase;
import com.example.followmap.databinding.ActivityUsuarioViewBinding;
import com.example.followmap.entities.Usuario;

public class UsuarioView extends AppCompatActivity {
    private LocalDatabase db;
    private ActivityUsuarioViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsuarioViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());

        binding.btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        binding.btnFazerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void registrarUsuario() {
        String nome = binding.edtNome.getText().toString().trim();
        String email = binding.edtEmail.getText().toString().trim();
        String senha = binding.edtSenha.getText().toString().trim();
        String confirmaSenha = binding.edtConfirmaSenha.getText().toString().trim();

        if(nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!senha.equals(confirmaSenha)){
            Toast.makeText(this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.setError("Email inválido.");
            return;
        } else {
            binding.tilEmail.setError(null);
        }

        if(db.usuarioDao().getEmail(email)!=null){
            Toast.makeText(this, "E-mail já cadastrado!", Toast.LENGTH_SHORT).show();
            return;
        }else{
            Usuario usuario = new Usuario(nome, email, senha);
            db.usuarioDao().insert(usuario);
            Toast.makeText(this, "Usuário cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
        }
    }
}