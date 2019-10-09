package edu.stts.hwcustomlistview;

public class Pokemon {
    private String detail, nama;
    private int dexNo, gambar;

    public Pokemon(String detail, String nama, int dexNo, int gambar) {
        this.detail = detail;
        this.nama = nama;
        this.dexNo = dexNo;
        this.gambar = gambar;
    }

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

    @Override
    public String toString() {
        return "Pokemon{" +
                "nama='" + nama + '\'' +
                ", dexNo=" + dexNo +
                '}';
    }
}
