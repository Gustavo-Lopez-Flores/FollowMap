package com.example.followmap.view.User;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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

        binding.btnSalvarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarUsuario();
            }
        });

        binding.btnExcluirUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirUsuario();
            }
        });

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    public void salvarUsuario() {
        String nome = binding.edtNomeUsuario.getText().toString().trim();
        String email = binding.edtEmailUsuario.getText().toString().trim();
        String senha = binding.edtSenhaUsuario.getText().toString().trim();

        if(nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.setError("Email inválido.");
            return;
        } else {
            binding.tilEmail.setError(null);
        }

        Usuario thisUsuario = new Usuario(nome, email, senha);

        if (dbUsuario != null) {
            thisUsuario.setUsuarioId(dbUsuarioID);
            db.usuarioDao().update(thisUsuario);
            Toast.makeText(this, "Dados do usuário atualizados com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            db.usuarioDao().insert(thisUsuario);
            Toast.makeText(this, "Usuário criado com sucesso.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void excluirUsuario() {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Usuário")
                .setMessage("Deseja excluir esse usuário?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    private void excluir() {
        db.usuarioDao().delete(dbUsuario);
        Toast.makeText(this, "Usuário excluído com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }
}