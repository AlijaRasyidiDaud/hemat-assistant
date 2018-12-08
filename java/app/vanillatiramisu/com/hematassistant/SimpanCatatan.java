package app.vanillatiramisu.com.hematassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SimpanCatatan extends AppCompatActivity {

    private String mFileName;
    private Keuangan mLoadKeuangan;
    private String kodeWaktu;
    private String kodeArsip;
    private boolean mode;

    private String tKodeTanggal;
    private int tMasukan;
    private int tTabung;
    private int tKeluaran;
    private String tCatatan;

    private EditText pemasukan, tabung, keluaran, catatan;
    private ImageButton save;
    private TextView tampilTanggal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpan_catatan);

        tampilTanggal = (TextView) findViewById(R.id.tampilTanggal);
        pemasukan = (EditText) findViewById(R.id.input_pemasukan);
        tabung = (EditText) findViewById(R.id.input_tabung);
        keluaran = (EditText) findViewById(R.id.input_keluaran);
        catatan = (EditText) findViewById(R.id.input_catatan);
        save = (ImageButton) findViewById(R.id.btnSave);

        // Set kode waktu
        Calendar c = Calendar.getInstance();
        int kodeBulan = c.get(Calendar.MONTH);
        int kodeTahun = c.get(Calendar.YEAR);

        switch (kodeBulan){
            case 0 :
                kodeWaktu ="Januari " + kodeTahun;

                break;
            case 1 :
                kodeWaktu ="Februari " + kodeTahun;

                break;
            case 2 :
                kodeWaktu ="Maret " + kodeTahun;

                break;
            case 3 :
                kodeWaktu ="April " + kodeTahun;

                break;
            case 4 :
                kodeWaktu ="Mei " + kodeTahun;

                break;
            case 5 :
                kodeWaktu ="Juni " + kodeTahun;

                break;
            case 6 :
                kodeWaktu ="Juli " + kodeTahun;

                break;
            case 7 :
                kodeWaktu ="Agustus " + kodeTahun;

                break;
            case 8 :
                kodeWaktu ="September " + kodeTahun;

                break;
            case 9 :
                kodeWaktu ="Oktober " + kodeTahun;

                break;
            case 10 :
                kodeWaktu ="November " + kodeTahun;

                break;
            case 11 :
                kodeWaktu ="Desember " + kodeTahun;

                break;
        }

        // Load
        mode = getIntent().getBooleanExtra("mode", false);
        if(mode){

            mFileName = kodeWaktu + Utility.FILE_EXTENSION;
            if(mFileName != null && !mFileName.isEmpty() && mFileName.endsWith(Utility.FILE_EXTENSION)){
                mLoadKeuangan = Utility.getKeuanganByFileName(getApplicationContext(), mFileName);
                if(mLoadKeuangan != null){
                    tKodeTanggal = mLoadKeuangan.getmWaktu();
                    tMasukan = mLoadKeuangan.getmPemasukan();
                    tTabung = mLoadKeuangan.getmTabung();
                    tKeluaran= mLoadKeuangan.getmKeluaran();
                    tCatatan = mLoadKeuangan.getmCatatan();

                    // set Text
                    pemasukan.setText("" + tMasukan);
                    tabung.setText("" + tTabung);
                    keluaran.setText("" + tKeluaran);
                    catatan.setText(tCatatan);

                }else{
                    tKodeTanggal = kodeWaktu;
                }
            }

        }else {

            kodeArsip = getIntent().getStringExtra("cariArsip");
            mFileName = kodeArsip + Utility.FILE_EXTENSION;

            if(mFileName != null && !mFileName.isEmpty() && mFileName.endsWith(Utility.FILE_EXTENSION)){
                mLoadKeuangan = Utility.getKeuanganByFileName(getApplicationContext(), mFileName);
                if(mLoadKeuangan != null){
                    tKodeTanggal = mLoadKeuangan.getmWaktu();
                    tMasukan = mLoadKeuangan.getmPemasukan();
                    tTabung = mLoadKeuangan.getmTabung();
                    tKeluaran= mLoadKeuangan.getmKeluaran();
                    tCatatan = mLoadKeuangan.getmCatatan();

                    // set Text
                    pemasukan.setText("" + tMasukan);
                    tabung.setText("" + tTabung);
                    keluaran.setText("" + tKeluaran);
                    catatan.setText(tCatatan);

                }else{
                    tKodeTanggal = kodeArsip;
                }
            }
        }


        // Set tanggal;
        tampilTanggal.setText(tKodeTanggal);

        // Sasve catatan
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDataCatatan();
            }
        });
    }

    private void saveDataCatatan(){

        // Ambil input
        try{
            tMasukan = Integer.parseInt(pemasukan.getText().toString());
            tTabung = Integer.parseInt(tabung.getText().toString());
            tKeluaran = Integer.parseInt(keluaran.getText().toString());
        } catch (NumberFormatException e){
            e.printStackTrace();
        }

        tCatatan = catatan.getText().toString();

        if(Utility.simpanKeuangan(getApplicationContext(), new Keuangan(tKodeTanggal, tMasukan, tTabung, tKeluaran, tCatatan))){
            Toast.makeText(getApplicationContext(), "Catatan keuangan disimpan", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Catatan tidak tersimpan, coba periksa besar memori penyimpanan yang tersisa", Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(getApplicationContext(), CatatanKeuangan.class));
        finish();
    }

}
