package com.example.uap_pam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private static final int REQUEST_TAMBAH_TANAMAN = 1;
    private RecyclerView recyclerView;
    private Button btnTambahList;
    private List<Tanaman> dataTanaman;
    private TanamanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerTanaman);
        btnTambahList = findViewById(R.id.btnTambahList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataTanaman = new ArrayList<>();
        dataTanaman.add(new Tanaman("Daun Hijau", "Rp 200.000", R.drawable.sample_plant));
        dataTanaman.add(new Tanaman("Daun Hijau", "Rp 200.000", R.drawable.sample_plant));
        dataTanaman.add(new Tanaman("Daun Hijau", "Rp 200.000", R.drawable.sample_plant));

        adapter = new TanamanAdapter(this, dataTanaman, new TanamanAdapter.OnItemClickListener() {
            @Override
            public void onDetailClick(Tanaman tanaman) {
                Toast.makeText(HomeActivity.this, "Detail: " + tanaman.getNama(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onHapusClick(Tanaman tanaman) {
                dataTanaman.remove(tanaman);
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(adapter);

        btnTambahList.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, TambahTanamanActivity.class);
            startActivityForResult(intent, REQUEST_TAMBAH_TANAMAN);
        });

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_TAMBAH_TANAMAN && resultCode == RESULT_OK && data != null) {
                String nama = data.getStringExtra("nama");
                String harga = data.getStringExtra("harga");
                int gambar = data.getIntExtra("gambar", R.drawable.sample_plant);

                Tanaman tanamanBaru = new Tanaman(nama, harga, gambar);
                dataTanaman.add(tanamanBaru);
                adapter.notifyDataSetChanged();
            }
        }
    }
}