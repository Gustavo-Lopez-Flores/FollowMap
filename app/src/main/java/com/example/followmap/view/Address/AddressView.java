package com.example.followmap.view.Address;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.followmap.R;
import com.example.followmap.database.LocalDatabase;
import com.example.followmap.databinding.ActivityAddressViewBinding;
import com.example.followmap.entities.Cidade;
import com.example.followmap.entities.Endereco;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class AddressView extends AppCompatActivity implements OnMapReadyCallback {
    private LocalDatabase db;
    private ActivityAddressViewBinding binding;
    private int dbEnderecoID;
    private Endereco dbEndereco;
    private List<Cidade> cidades;
    private GoogleMap mMap;
    private MapView mapView;
    private double lati = -34, longi = 151;
    private String descricao = "Marker in Sydney";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        dbEnderecoID = getIntent().getIntExtra("ENDERECO_SELECIONADO_ID", -1);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        binding.btnSalvarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarEndereco();
            }
        });

        binding.btnExcluirEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirEndereco();
            }
        });

        binding.btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    lati = Double.parseDouble(binding.edtLatitude.getText().toString());
                    longi = Double.parseDouble(binding.edtLongitude.getText().toString());
                    descricao = binding.edtDescricao.getText().toString();
                    if (mMap != null) {
                        updateMap();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(AddressView.this, "Valores de latitude e longitude inválidos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        loadCidades();
        if (dbEnderecoID >= 0) {
            getDBEndereco();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        updateMap();
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

    public void salvarEndereco() {
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

    public void excluirEndereco() {
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

    public void voltar() {
        finish();
    }

    private void updateMap() {
        LatLng latLng = new LatLng(lati, longi);
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng).title(descricao));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }
}