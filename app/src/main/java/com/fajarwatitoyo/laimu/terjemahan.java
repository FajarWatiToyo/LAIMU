package com.fajarwatitoyo.laimu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class terjemahan extends AppCompatActivity {
    private SQLiteDatabase db = null;
    private Cursor kamusCursor = null;
    private EditText txtIndonesia;
    private EditText txtLaimu;
    private DataKamus datakamus = null;
    public static final String INDONESIA = "indonesia";
    public static final String LAIMU = "laimu";
    private Button btnKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terjemahan);
        datakamus = new DataKamus(this);
        db = datakamus.getWritableDatabase();
        datakamus.createTable(db);
        datakamus.generateData(db);

        setContentView(R.layout.activity_terjemahan);
        txtIndonesia = (EditText) findViewById(R.id.txtIndonesia);
        txtLaimu = (EditText) findViewById(R.id.txtLaimu);
        btnKeluar = findViewById(R.id.btnKeluar);

    }

    public void getTerjemahkan(View view) {
        String result = "";
        String indonesiaword = txtIndonesia.getText().toString();
        kamusCursor = db.rawQuery("SELECT ID, INDONESIA, LAIMU " + "FROM kamus where INDONESIA='" + indonesiaword
                + "' ORDER BY INDONESIA", null);

        if (kamusCursor.moveToFirst()) {
            result = kamusCursor.getString(2);
            for (; !kamusCursor.isAfterLast(); kamusCursor.moveToNext()) {
                result = kamusCursor.getString(2);
            }
        }
        if (result.equals("")) {
            result = "Kata Tidak Ditemukan";
        }
        txtLaimu.setText(result);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        kamusCursor.close();
        db.close();

        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aksi yang dijalankan saat tombol Keluar ditekan
                Toast.makeText(terjemahan.this, "Tombol Keluar Ditekan", Toast.LENGTH_SHORT).show();
                finish(); // Menutup aktivitas

            }
        });
    }
}