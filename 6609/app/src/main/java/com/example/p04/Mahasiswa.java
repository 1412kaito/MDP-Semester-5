package com.example.p04;

import android.os.Parcel;
import android.os.Parcelable;

public class Mahasiswa implements Parcelable {
    private int NRP;
    private String nama;
    private String email;

    public Mahasiswa(int NRP, String nama, String email) {
        this.NRP = NRP;
        this.nama = nama;
        this.email = email;
    }

    public int getNRP() {
        return NRP;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public void setNRP(int NRP) {
        this.NRP = NRP;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Mahasiswa{\n" +
                "NRP=" + NRP +
                ", \nnama='" + nama + '\'' +
                ", \nemail='" + email + '\'' +
                "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.NRP);
        dest.writeString(this.nama);
        dest.writeString(this.email);
    }

    protected Mahasiswa(Parcel in) {
        this.NRP = in.readInt();
        this.nama = in.readString();
        this.email = in.readString();
    }

    public static final Parcelable.Creator<Mahasiswa> CREATOR = new Parcelable.Creator<Mahasiswa>() {
        @Override
        public Mahasiswa createFromParcel(Parcel source) {
            return new Mahasiswa(source);
        }

        @Override
        public Mahasiswa[] newArray(int size) {
            return new Mahasiswa[size];
        }
    };
}
