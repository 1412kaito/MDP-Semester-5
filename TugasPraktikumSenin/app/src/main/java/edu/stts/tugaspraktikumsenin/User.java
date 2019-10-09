package edu.stts.tugaspraktikumsenin;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class User implements Parcelable {
    private ArrayList<History> histories;
    private String nama, hp, password;
    private long uang, point;

    public User(String nama, String hp, String password) {
        histories = new ArrayList<>();
        this.nama = nama;
        this.hp = hp;
        this.password = password;
        this.uang = 0L;
        this.point = 0L;
    }

    public ArrayList<History> getHistories() {
        return histories;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUang() {
        return uang;
    }

    public void setUang(long uang) {
        this.uang = uang;
    }

    public long getPoint() {
        return point;
    }

    public void setPoint(long point) {
        this.point = point;
    }

    @NonNull
    @Override
    public String toString() {
        return this.nama + " - " + this.hp;
    }

    //PARCELABLE IMPLEMENTATION
    protected User(Parcel in) {
        histories = in.createTypedArrayList(History.CREATOR);
        nama = in.readString();
        hp = in.readString();
        password = in.readString();
        uang = in.readLong();
        point = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(histories);
        dest.writeString(nama);
        dest.writeString(hp);
        dest.writeString(password);
        dest.writeLong(uang);
        dest.writeLong(point);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
