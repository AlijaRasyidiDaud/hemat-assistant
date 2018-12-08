package app.vanillatiramisu.com.hematassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.Calendar;

public class CatatanKeuangan extends AppCompatActivity implements View.OnClickListener {

    private String mFileName;
    private Keuangan mLoadKeuangan;
    private String kodeWaktu;

    private TextView pemasukan, tabung, keluaran, harian, catatan, tanggal;
    private ImageButton saveCatat, cariArsip;
    private Spinner listBulan, listTahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan_keuangan);

        tanggal = (TextView) findViewById(R.id.bulanCatatan);
        pemasukan = (TextView) findViewById(R.id.no_pemasukan);
        tabung = (TextView) findViewById(R.id.no_tabung);
        keluaran = (TextView) findViewById(R.id.no_keluaran);
        harian = (TextView) findViewById(R.id.no_harian);
        catatan = (TextView) findViewById(R.id.catatan);

        saveCatat = (ImageButton) findViewById(R.id.saveKeuangan);
        cariArsip = (ImageButton) findViewById(R.id.cariArsip);
        saveCatat.setOnClickListener(this);
        cariArsip.setOnClickListener(this);

        listBulan = (Spinner) findViewById(R.id.pilih_bulan);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.pilihBulan, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listBulan.setAdapter(adapter1);

        listTahun = (Spinner) findViewById(R.id.pilih_tahun);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.pilihTahun, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listTahun.setAdapter(adapter2);


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

        // Load data
        mFileName = kodeWaktu + Utility.FILE_EXTENSION;
        if(mFileName != null && !mFileName.isEmpty() && mFileName.endsWith(Utility.FILE_EXTENSION)){
            mLoadKeuangan = Utility.getKeuanganByFileName(getApplicationContext(), mFileName);
            if(mLoadKeuangan != null){ // Load data keuangan
                tanggal.setText(mLoadKeuangan.getmWaktu());
                pemasukan.setText(penambahTitik(mLoadKeuangan.getmPemasukan()));
                tabung.setText(penambahTitik(mLoadKeuangan.getmTabung()));
                keluaran.setText(penambahTitik(mLoadKeuangan.getmKeluaran()));
                harian.setText(penambahTitik(mLoadKeuangan.mHarian()));
                catatan.setText(mLoadKeuangan.getmCatatan());
            }else{ // Jika tidak hanya tampilkan tanggal
                tanggal.setText(kodeWaktu);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveKeuangan :
                Intent saveKeuangan = new Intent(getApplicationContext(), SimpanCatatan.class);
                saveKeuangan.putExtra("mode", true);
                startActivity(saveKeuangan);
                finish();

                break;
            case R.id.cariArsip :
                String bulanArsip = listBulan.getSelectedItem().toString();
                String tahunArsip = listTahun.getSelectedItem().toString();

                if(!bulanArsip.isEmpty() && !tahunArsip.isEmpty()){

                    String kodeArsip = bulanArsip + " " + tahunArsip;

                    Intent cariArsip = new Intent(getApplicationContext(), SimpanCatatan.class);
                    cariArsip.putExtra("cariArsip", kodeArsip);
                    startActivity(cariArsip);
                    finish();

                }else {
                    Toast.makeText(getApplicationContext(), "Masukan data bulan dan tahun yang ingin di cari", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }

    private String penambahTitik(int i){

        int ribu = 1000;
        int juta = 1000000;
        int milyar = 1000000000;
        String tempJ, tempR, tempS;

        if((i/milyar)!=0){

            if((((i%milyar)/juta)/100) == 0){
                if((((i%milyar)/juta)/10) == 0){
                    tempJ ="00"+(i%milyar)/juta;
                }else {
                    tempJ ="0"+(i%milyar)/juta;
                }
            }else {
                tempJ =""+ (i%milyar)/juta;
            }

            if(((i%juta)/ribu/100) == 0){
                if(((i%juta)/ribu/10) == 0){
                    tempR ="00"+(i%juta)/ribu;
                }else {
                    tempR ="0"+(i%juta)/ribu;
                }
            }else {
                tempR =""+ (i%juta)/ribu;
            }

            if((i%ribu/100) == 0){
                if((i%ribu/10) == 0){
                    tempS ="00"+i%ribu;
                }else {
                    tempS ="0"+i%ribu;
                }
            }else {
                tempS =""+ i%ribu;
            }

            return ": Rp "+i/milyar+"."+tempJ+"."+tempR+"."+tempS;
        }else if((i/juta)!=0){

            if(((i%juta)/ribu/100) == 0){
                if(((i%juta)/ribu/10) == 0){
                    tempR ="00"+(i%juta)/ribu;
                }else {
                    tempR ="0"+(i%juta)/ribu;
                }
            }else {
                tempR =""+ (i%juta)/ribu;
            }

            if((i%ribu/100) == 0){
                if((i%ribu/10) == 0){
                    tempS ="00"+i%ribu;
                }else {
                    tempS ="0"+i%ribu;
                }
            }else {
                tempS =""+ i%ribu;
            }

            return ": Rp "+i/juta+"."+tempR+"."+tempS;
        }else if((i/ribu)!=0){

            if((i%ribu/100) == 0){
                if((i%ribu/10) == 0){
                    tempS ="00"+i%ribu;
                }else {
                    tempS ="0"+i%ribu;
                }
            }else {
                tempS =""+ i%ribu;
            }

            return ": Rp "+i/ribu+"."+tempS;
        }else{
            return ": Rp "+i;
        }

    }
}
