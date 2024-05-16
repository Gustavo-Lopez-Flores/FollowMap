package com.example.followmap.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.followmap.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        binding.btnEntrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Intent it = new Intent(MainActivity.this, ...);
//            }
//        });

        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, RegistroDeUsuarioActivity.class);
                startActivity(it);
            }
        });
    }
}