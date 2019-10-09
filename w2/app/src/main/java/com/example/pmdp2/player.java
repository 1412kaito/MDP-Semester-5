package com.example.pmdp2;

import android.os.Parcel;
import android.os.Parcelable;

public class player implements Parcelable, Comparable<player> {
    private String nama;
    private Integer skor;

    public player(String nama, Integer skor) {
        this.nama = nama;
        this.skor = skor;
    }

    protected player(Parcel in) {
        nama = in.readString();
        if (in.readByte() == 0) {
            skor = null;
        } else {
            skor = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        if (skor == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(skor);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<player> CREATOR = new Creator<player>() {
        @Override
        public player createFromParcel(Parcel in) {
            return new player(in);
        }

        @Override
        public player[] newArray(int size) {
            return new player[size];
        }
    };

    public String toString() {
        return nama + " - " + skor;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getSkor() {
        return skor;
    }

    public void setSkor(Integer skor) {
        this.skor = skor;
    }

    public int compareTo(player o) {
        if (this.skor < o.skor) return -1;
        else if (this.skor > o.skor) return 1;
        return 0;
    }
}
