package edu.stts.tugaspraktikum03;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Ticket implements Parcelable {
    Movies m; int jam, jumlah;
    public Ticket(Movies m, int jam, int jumlah){
        this.m = m; this.jam = jam;
        this.jumlah = jumlah;
    }
    @NonNull
    @Override
    public String toString() {
        String app = (jumlah==1)?" ticket.":" tickets.";
        return this.m.nama+" - "+this.m.tempat + "\n" + this.m.jam[jam] + " - " + this.jumlah +app;
    }
    protected Ticket(Parcel in) {
        m = in.readParcelable(Movies.class.getClassLoader());
        jam = in.readInt();
        jumlah = in.readInt();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(m, flags);
        dest.writeInt(jam);
        dest.writeInt(jumlah);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }
        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };
}
