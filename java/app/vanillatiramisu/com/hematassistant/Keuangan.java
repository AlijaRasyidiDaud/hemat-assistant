package app.vanillatiramisu.com.hematassistant;

import java.io.Serializable;
import java.util.Calendar;

public class Keuangan implements Serializable {

    private String mWaktu;
    private int mPemasukan;
    private int mTabung;
    private int mKeluaran;
    private String mCatatan;

    // Konstruktor
    public Keuangan(String mWaktu, int mPemasukan, int mTabung, int mKeluaran, String mCatatan) {
        this.mWaktu = mWaktu;
        this.mPemasukan = mPemasukan;
        this.mTabung = mTabung;
        this.mKeluaran = mKeluaran;
        this.mCatatan = mCatatan;

    }

    // Setter
    public void setmWaktu(String mWaktu) {
        this.mWaktu = mWaktu;
    }

    public void setmPemasukan(int mPemasukan) {
        this.mPemasukan = mPemasukan;
    }

    public void setmTabung(int mTabung) {
        this.mTabung = mTabung;
    }

    public void setmKeluaran(int mKeluaran) {
        this.mKeluaran = mKeluaran;
    }

    public void setmCatatan(String mCatatan) {
        this.mCatatan = mCatatan;
    }

    // Getter
    public String getmWaktu() {

        return mWaktu;
    }

    public int getmPemasukan() {
        return mPemasukan;
    }

    public int getmTabung() {
        return mTabung;
    }

    public int getmKeluaran() {
        return mKeluaran;
    }

    public String getmCatatan() {
        return mCatatan;
    }

    // Hitung nilai harian
    public int mHarian(){


        return (mPemasukan-mTabung-mKeluaran)/30;
    }

}

