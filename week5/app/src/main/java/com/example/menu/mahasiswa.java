package com.example.menu;

import android.os.Parcel;
import android.os.Parcelable;

public class mahasiswa implements Parcelable {
    private long nrp;
    private String nama;
    private String major;

    public mahasiswa(long nrp, String nama, String major) {
        this.nrp = nrp;
        this.nama = nama;
        this.major = major;
    }

    protected mahasiswa(Parcel in) {
        nrp = in.readLong();
        nama = in.readString();
        major = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(nrp);
        dest.writeString(nama);
        dest.writeString(major);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<mahasiswa> CREATOR = new Creator<mahasiswa>() {
        @Override
        public mahasiswa createFromParcel(Parcel in) {
            return new mahasiswa(in);
        }

        @Override
        public mahasiswa[] newArray(int size) {
            return new mahasiswa[size];
        }
    };

    public void setNrp(long nrp) {
        this.nrp = nrp;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public long getNrp() {
        return nrp;
    }

    public String getNama() {
        return nama;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public String toString() {
        return this.nrp+" - "+this.nama+" - "+this.major;
    }
}
