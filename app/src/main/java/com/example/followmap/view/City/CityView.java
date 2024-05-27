package com.example.followmap.view.City;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import com.example.followmap.database.LocalDatabase;
import com.example.followmap.databinding.ActivityCityViewBinding;
import com.example.followmap.entities.Cidade;

public class CityView extends AppCompatActivity {
    private LocalDatabase db;
    private ActivityCityViewBinding binding;
    private int dbCidadeID;
    private Cidade dbCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCityViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        dbCidadeID = getIntent().getIntExtra("CIDADE_SELECIONADA_ID", -1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dbCidadeID >= 0) {
            getDBCidade();
        } else {
            binding.btnExcluirCidade.setVisibility(View.GONE);
        }
    }

    private void getDBCidade() {
        new Thread(() -> {
            dbCidade = db.cidadeDao().getCidade(dbCidadeID);
            runOnUiThread(() -> {
                binding.edtCidade.setText(dbCidade.getCidade());
                binding.edtEstado.setText(dbCidade.getEstado());
            });
        }).start();
    }

    public void salvarCidade(View view) {
        String nomeCidade = binding.edtCidade.getText().toString();
        String estadoCidade = binding.edtEstado.getText().toString();
        if (nomeCidade.equals("") || estadoCidade.equals("")) {
            Toast.makeText(this, "Adicione uma cidade e estado.", Toast.LENGTH_SHORT).show();
            return;
        }

        Cidade thisCidade = new Cidade(nomeCidade, estadoCidade);

        new Thread(() -> {
            if (dbCidade != null) {
                thisCidade.setCidadeId(dbCidadeID);
                db.cidadeDao().update(thisCidade);
                runOnUiThread(() -> Toast.makeText(this, "Cidade atualizada com sucesso.", Toast.LENGTH_SHORT).show());
            } else {
                db.cidadeDao().insertAll(thisCidade);
                runOnUiThread(() -> Toast.makeText(this, "Cidade criada com sucesso.", Toast.LENGTH_SHORT).show());
            }
            finish();
        }).start();
    }

    public void excluirCidade(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Cidade")
                .setMessage("Deseja excluir essa cidade?")
                .setPositiveButton("Sim", (dialog, which) -> excluir())
                .setNegativeButton("Não", null)
                .show();
    }

    private void excluir() {
        new Thread(() -> {
            if (db.enderecoDao().getAllEnderecos().stream().anyMatch(endereco -> endereco.getCidadeId() == dbCidadeID)) {
                runOnUiThread(() -> Toast.makeText(this, "Impossível excluir cidade com endereços cadastrados", Toast.LENGTH_SHORT).show());
            } else {
                db.cidadeDao().delete(dbCidade);
                runOnUiThread(() -> Toast.makeText(this, "Cidade excluída com sucesso", Toast.LENGTH_SHORT).show());
            }
            finish();
        }).start();
    }

    public void voltar(View view) {
        finish();
    }
}