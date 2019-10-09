package com.example.dicodingsubmission;

public class Pokemon {
//    private tidak bisa diakses dari class lain
    private String detail, nama;
    private int dexNo, gambar;

    public Pokemon(String detail, String nama, int dexNo, int gambar) {
        this.detail = detail;
        this.nama = nama;
        this.dexNo = dexNo;
        this.gambar = gambar;
    }

//    jika data diset sekali saja
//    setter tidak perlu

    public String getDetail() {
        return detail;
    }

    public String getNama() {
        return nama;
    }

    public int getDexNo() {
        return dexNo;
    }

    public int getGambar() {
        return gambar;
    }
}
