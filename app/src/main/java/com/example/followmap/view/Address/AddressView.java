package com.example.followmap.view.Address;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.followmap.database.LocalDatabase;
import com.example.followmap.databinding.ActivityAddressViewBinding;
import com.example.followmap.entities.Cidade;
import com.example.followmap.entities.Endereco;

import java.util.List;

public class AddressView extends AppCompatActivity {
    private LocalDatabase db;
    private ActivityAddressViewBinding binding;
    private int dbEnderecoID;
    private Endereco dbEndereco;
    private List<Cidade> cidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        dbEnderecoID = getIntent().getIntExtra("ENDERECO_SELECIONADO_ID", -1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCidades();
        if (dbEnderecoID >= 0) {
            getDBEndereco();
        }
    }

    private void loadCidades() {
        cidades = db.cidadeDao().getAllCidades();
        ArrayAdapter<Cidade> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cidades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerCidades.setAdapter(adapter);
    }

    private void getDBEndereco() {
        dbEndereco = db.enderecoDao().getEndereco(dbEnderecoID);
        binding.edtDescricao.setText(dbEndereco.getDescricao());
        binding.edtLatitude.setText(String.valueOf(dbEndereco.getLatitude()));
        binding.edtLongitude.setText(String.valueOf(dbEndereco.getLongitude()));
        int position = -1;
        for (int i = 0; i < cidades.size(); i++) {
            if (cidades.get(i).getCidadeId() == dbEndereco.getCidadeId()) {
                position = i;
                break;
            }
        }
        if (position >= 0) {
            binding.spinnerCidades.setSelection(position);
        }
    }

    public void salvarEndereco(View view) {
        String descricao = binding.edtDescricao.getText().toString();
        String latitudeStr = binding.edtLatitude.getText().toString();
        String longitudeStr = binding.edtLongitude.getText().toString();
        Cidade cidadeSelecionada = (Cidade) binding.spinnerCidades.getSelectedItem();

        if (descricao.equals("") || latitudeStr.equals("") || longitudeStr.equals("") || cidadeSelecionada == null) {
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        double latitude = Double.parseDouble(latitudeStr);
        double longitude = Double.parseDouble(longitudeStr);

        Endereco endereco = new Endereco(descricao, latitude, longitude, cidadeSelecionada.getCidadeId());

        if (dbEndereco != null) {
            endereco.setEnderecoId(dbEnderecoID);
            db.enderecoDao().update(endereco);
            Toast.makeText(this, "Endereço atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            db.enderecoDao().insertAll(endereco);
            Toast.makeText(this, "Endereço criado com sucesso.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void excluirEndereco(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Endereço")
                .setMessage("Deseja excluir esse endereço?")
                .setPositiveButton("Sim", (dialog, which) -> excluir())
                .setNegativeButton("Não", null)
                .show();
    }

    private void excluir() {
        db.enderecoDao().delete(dbEndereco);
        Toast.makeText(this, "Endereço excluído com sucesso.", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void voltar(View view) {
        finish();
    }
}