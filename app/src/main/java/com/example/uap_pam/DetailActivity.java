package com.example.uap_pam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uap_pam.ApiClient;
import com.example.uap_pam.ApiService;
import com.example.uap_pam.Plant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private TextView textName, textDesc, textPrice;
    private ImageView imagePlant;
    private Button btnUpdate, btnDelete;

    private ApiService apiService;
    private int plantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textName = findViewById(R.id.textName);
        textDesc = findViewById(R.id.textDesc);
        textPrice = findViewById(R.id.textPrice);
        imagePlant = findViewById(R.id.imagePlant);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        apiService = ApiClient.getClient().create(ApiService.class);
        plantId = getIntent().getIntExtra("plant_id", -1);

        loadPlantDetail(plantId);

        btnUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, EditActivity.class);
            intent.putExtra("plant_id", plantId);
            startActivity(intent);
        });

        btnDelete.setOnClickListener(v -> {
            deletePlant(plantId);
        });
    }

    private void loadPlantDetail(int id) {
        apiService.getPlant(id).enqueue(new Callback<Plant>() {
            @Override
            public void onResponse(Call<Plant> call, Response<Plant> response) {
                if (response.isSuccessful()) {
                    Plant plant = response.body();
                    if (plant != null) {
                        textName.setText(plant.getPlant_name());
                        textDesc.setText(plant.getDescription());
                        textPrice.setText("Rp " + plant.getPrice());
                        imagePlant.setImageResource(R.drawable.sample_plant); // Ganti dengan Glide jika dari URL
                    }
                } else {
                    Toast.makeText(DetailActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Plant> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Terjadi kesalahan jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deletePlant(int id) {
        apiService.deletePlant(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DetailActivity.this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DetailActivity.this, "Gagal menghapus", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Error koneksi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}