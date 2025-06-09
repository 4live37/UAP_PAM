package com.example.uap_pam;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgTanaman;
    private TextView tvNama, tvHarga, tvDeskripsi;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgTanaman = findViewById(R.id.imgTanaman);
        tvNama = findViewById(R.id.tvNama);
        tvHarga = findViewById(R.id.tvHarga);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);
        btnUpdate = findViewById(R.id.btnUpdate);

        // Data statis (bisa diganti dengan dari Firebase/API nanti)
        tvNama.setText("Daun Hijau");
        tvHarga.setText("Rp 200.000");
        tvDeskripsi.setText("Tanaman ini berasal dari negara x, merupakan tanaman langka");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aksi tombol update, misal buka activity edit
                // Contoh: startActivity(new Intent(DetailActivity.this, EditActivity.class));
            }
        });
    }
}