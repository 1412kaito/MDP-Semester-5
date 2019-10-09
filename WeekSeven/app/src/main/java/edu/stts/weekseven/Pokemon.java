package edu.stts.weekseven;

import android.os.Parcel;
import android.os.Parcelable;

public class Pokemon implements Parcelable {
    private String detail, nama;
    private int dexNo, gambar;

    public Pokemon(String detail, String nama, int dexNo, int gambar) {
        this.detail = detail;
        this.nama = nama;
        this.dexNo = dexNo;
        this.gambar = gambar;
    }

    protected Pokemon(Parcel in) {
        detail = in.readString();
        nama = in.readString();
        dexNo = in.readInt();
        gambar = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(detail);
        dest.writeString(nama);
        dest.writeInt(dexNo);
        dest.writeInt(gambar);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

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
