package com.example.followmap.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.EdgeToEdge;

import com.example.followmap.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }
}