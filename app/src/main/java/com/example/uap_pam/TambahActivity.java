package com.example.uap_pam;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {

    EditText etNama, etHarga, etDeskripsi;
    Button btnTambah;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.etNama);
        etHarga = findViewById(R.id.etHarga);
        etDeskripsi = findViewById(R.id.etDeskripsi);
        btnTambah = findViewById(R.id.btnTambah);
        apiService = ApiClient.getClient().create(ApiService.class);

        btnTambah.setOnClickListener(v -> {
            String nama = etNama.getText().toString();
            String harga = etHarga.getText().toString();
            String deskripsi = etDeskripsi.getText().toString();

            if (nama.isEmpty() || harga.isEmpty() || deskripsi.isEmpty()) {
                Toast.makeText(this, "Semua field wajib diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            tambahTanaman(nama, harga, deskripsi);
        });
    }

    private void tambahTanaman(String nama, String harga, String deskripsi) {
        Plant tanaman = new Plant(nama, deskripsi, harga);
        Call<Plant> call = apiService.createPlant(tanaman);
        call.enqueue(new Callback<Plant>() {
            @Override
            public void onResponse(Call<Plant> call, Response<Plant> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(TambahActivity.this, "Berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    finish(); // kembali ke halaman sebelumnya
                } else {
                    Toast.makeText(TambahActivity.this, "Gagal tambah: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Plant> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}