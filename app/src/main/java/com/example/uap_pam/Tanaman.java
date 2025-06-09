package com.example.uap_pam;

public class Tanaman {
    private String nama;
    private String harga;
    private int gambar; // resource id

    public Tanaman(String nama, String harga, int gambar) {
        this.nama = nama;
        this.harga = harga;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public String getHarga() {
        return harga;
    }

    public int getGambar() {
        return gambar;
    }
}