package edu.stts.tugaspraktikumsenin;

import android.os.Parcel;
import android.os.Parcelable;

public class History implements Parcelable {
    public enum KIND {
        PAYMENT, INBOUND_TRANSFER, OUTBOUND_TRANSFER, CASHBACK, TOPUP
    }
    private KIND kind;
    private long amount;
    private String tujuan;

    private History(KIND k, long amount, String tujuan) {
        this.kind = k;
        this.amount = amount;
        this.tujuan = tujuan;
    }

    private History(KIND k, long amount) {
        this.kind = k;
        this.amount = amount;
        this.tujuan = k.name();
    }

    protected History(Parcel in) {
        amount = in.readLong();
        tujuan = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(amount);
        dest.writeString(tujuan);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<History> CREATOR = new Creator<History>() {
        @Override
        public History createFromParcel(Parcel in) {
            return new History(in);
        }

        @Override
        public History[] newArray(int size) {
            return new History[size];
        }
    };

    public static History TopUp(long amount){
        return new History(KIND.TOPUP, amount);
    }

    public static History PayMerchant(String namaMerchant, long amount){
        return new History(KIND.PAYMENT, amount, namaMerchant);
    }

    public static History Point(String namaMerchant, long amount){
        return new History(KIND.CASHBACK, amount, namaMerchant);
    }

    public static History OutboundTransfer(String namaUser, long amount){
        return new History(KIND.OUTBOUND_TRANSFER, amount, namaUser);
    }

    public static History InboundTransfer(String namaUser, long amount){
        return new History(KIND.INBOUND_TRANSFER, amount, namaUser);
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public KIND getKind() {
        return kind;
    }

    public void setKind(KIND kind) {
        this.kind = kind;
    }
}
