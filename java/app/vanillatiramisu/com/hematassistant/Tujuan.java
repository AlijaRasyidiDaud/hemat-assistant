package app.vanillatiramisu.com.hematassistant;

import java.io.Serializable;

public class Tujuan implements Serializable {

    private int id_tujuan;
    private String mTujuan;

    // Konstruktor
    public Tujuan(int id_tujuan, String mTujuan) {
        this.id_tujuan = id_tujuan;
        this.mTujuan = mTujuan;
    }

    // Setter
    public void setId_tujuan(int id_tujuan) {
        this.id_tujuan = id_tujuan;
    }

    public void setmTujuan(String mTujuan) {
        this.mTujuan = mTujuan;
    }

    // Getter
    public int getId_tujuan() {
        return id_tujuan;
    }

    public String getmTujuan() {
        return mTujuan;
    }
}
