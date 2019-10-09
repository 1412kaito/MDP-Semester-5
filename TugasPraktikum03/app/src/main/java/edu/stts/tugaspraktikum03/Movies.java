package edu.stts.tugaspraktikum03;

import android.os.Parcel;
import android.os.Parcelable;

public class Movies implements Parcelable {
    String nama; String tempat;
    String[] jam;
    String sinopsis;
    public Movies(String nama, String tempat, String[] jam, String sinopsis){
        this.nama = nama;
        this.jam = jam;
        this.sinopsis = sinopsis;
        this.tempat = tempat;
    }
    protected Movies(Parcel in) {
        nama = in.readString();
        tempat = in.readString();
        jam = in.createStringArray();
        sinopsis = in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(tempat);
        dest.writeStringArray(jam);
        dest.writeString(sinopsis);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }
        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
