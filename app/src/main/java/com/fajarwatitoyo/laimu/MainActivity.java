package com.fajarwatitoyo.laimu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button translateButton;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        translateButton = findViewById(R.id.translateButton);
        exitButton = findViewById(R.id.exitButton);

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aksi yang dijalankan saat tombol Terjemahan ditekan
                Intent intent = new Intent(MainActivity.this, terjemahan.class);
                startActivity(intent);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aksi yang dijalankan saat tombol Keluar ditekan
                Toast.makeText(MainActivity.this, "Tombol Keluar Ditekan", Toast.LENGTH_SHORT).show();
                finish(); // Menutup aktivitas
            }
        });
    }

    }