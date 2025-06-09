package com.example.uap_pam;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

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
            dataTanaman.add(new Tanaman("Daun Baru", "Rp 250.000", R.drawable.sample_plant));
            adapter.notifyDataSetChanged();
        });
    }
}