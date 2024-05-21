package com.example.followmap.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.followmap.database.LocalDatabase;
import com.example.followmap.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private LocalDatabase db;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());

        binding.btnFazerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fazerLogin();
            }
        });
        
        binding.btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, UsuarioView.class);
                startActivity(it);
            }
        });
    }

    private void fazerLogin() {
    }
}