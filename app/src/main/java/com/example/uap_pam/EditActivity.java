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

public class EditActivity extends AppCompatActivity {

    EditText etNama, etHarga, etDeskripsi;
    Button btnSimpan;
    ApiService apiService;
    int tanamanId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etNama = findViewById(R.id.etNama);
        etHarga = findViewById(R.id.etHarga);
        etDeskripsi = findViewById(R.id.etDeskripsi);
        btnSimpan = findViewById(R.id.btnSimpan);

        apiService = ApiClient.getClient().create(ApiService.class);

        tanamanId = getIntent().getIntExtra("id", -1);
        String nama = getIntent().getStringExtra("plant_name");
        String harga = getIntent().getStringExtra("price");
        String deskripsi = getIntent().getStringExtra("description");

        etNama.setText(nama);
        etHarga.setText(harga);
        etDeskripsi.setText(deskripsi);

        btnSimpan.setOnClickListener(v -> {
            String namaBaru = etNama.getText().toString();
            String hargaBaru = etHarga.getText().toString();
            String deskripsiBaru = etDeskripsi.getText().toString();

            if (namaBaru.isEmpty() || hargaBaru.isEmpty() || deskripsiBaru.isEmpty()) {
                Toast.makeText(this, "Semua field wajib diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            updateTanaman(tanamanId, namaBaru, deskripsiBaru, hargaBaru);
        });
    }

    private void updateTanaman(int id, String nama, String deskripsi, String harga) {
        Plant tanaman = new Plant(nama, deskripsi, harga);
        Call<Plant> call = apiService.updatePlant(id, tanaman);
        call.enqueue(new Callback<Plant>() {
            @Override
            public void onResponse(Call<Plant> call, Response<Plant> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EditActivity.this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditActivity.this, "Gagal simpan: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Plant> call, Throwable t) {
                Toast.makeText(EditActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}