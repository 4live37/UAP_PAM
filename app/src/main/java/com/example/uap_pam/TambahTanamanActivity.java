package com.example.uap_pam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TambahTanamanActivity extends AppCompatActivity {

    private EditText etNama, etHarga;
    private Button btnSimpan;
    private ImageView imgTanaman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_tanaman);

        etNama = findViewById(R.id.etNamaTanaman);
        etHarga = findViewById(R.id.etHargaTanaman);
        btnSimpan = findViewById(R.id.btnSimpanTanaman);
        imgTanaman = findViewById(R.id.imgTambahTanaman); // Dummy gambar tetap (sample_plant)

        btnSimpan.setOnClickListener(v -> {
            String nama = etNama.getText().toString();
            String harga = etHarga.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("nama", nama);
            resultIntent.putExtra("harga", harga);
            resultIntent.putExtra("gambar", R.drawable.sample_plant);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}