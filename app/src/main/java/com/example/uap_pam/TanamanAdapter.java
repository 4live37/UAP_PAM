package com.example.uap_pam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TanamanAdapter extends RecyclerView.Adapter<TanamanAdapter.ViewHolder> {

    private Context context;
    private List<Tanaman> list;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onDetailClick(Tanaman tanaman);
        void onHapusClick(Tanaman tanaman);
    }

    public TanamanAdapter(Context context, List<Tanaman> list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tanaman, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tanaman t = list.get(position);
        holder.nama.setText(t.getNama());
        holder.harga.setText(t.getHarga());
        holder.gambar.setImageResource(t.getGambar());

        holder.btnDetail.setOnClickListener(v -> listener.onDetailClick(t));
        holder.btnHapus.setOnClickListener(v -> listener.onHapusClick(t));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, harga;
        ImageView gambar;
        Button btnDetail, btnHapus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.txtNamaTanaman);
            harga = itemView.findViewById(R.id.txtHargaTanaman);
            gambar = itemView.findViewById(R.id.imgTanaman);
            btnDetail = itemView.findViewById(R.id.btnDetail);
            btnHapus = itemView.findViewById(R.id.btnHapus);
        }
    }
}