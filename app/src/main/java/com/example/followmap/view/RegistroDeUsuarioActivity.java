package com.example.followmap.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.followmap.database.LocalDatabase;
import com.example.followmap.databinding.ActivityRegistroDeUsuarioBinding;

public class RegistroDeUsuarioActivity extends AppCompatActivity {
    private LocalDatabase db;
    private ActivityRegistroDeUsuarioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroDeUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
    }
}