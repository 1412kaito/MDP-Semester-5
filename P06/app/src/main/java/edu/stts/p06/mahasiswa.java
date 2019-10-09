package edu.stts.p06;

import android.os.Parcel;
import android.os.Parcelable;

public class mahasiswa implements Parcelable {
    String nama, major;
    int nrp;

    public mahasiswa(){ }

    protected mahasiswa(Parcel in) {
        nama = in.readString();
        major = in.readString();
        nrp = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(major);
        dest.writeInt(nrp);
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getNrp() {
        return nrp;
    }

    public void setNrp(int nrp) {
        this.nrp = nrp;
    }

    @Override
    public String toString() {
        return nrp+"\n"+nama+"\n"+major;
    }
}
