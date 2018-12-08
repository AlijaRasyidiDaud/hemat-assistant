package app.vanillatiramisu.com.hematassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private InterstitialAd mInterstitialAd;


    private ImageButton btnKeuangan;
    private ImageButton btnTujuan;
    private ImageButton btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi id ad
        MobileAds.initialize(this, getString(R.string.id_app));

        // Ad inters
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.id_inters));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        btnKeuangan = (ImageButton) findViewById(R.id.btnKeuangan);
        btnTujuan = (ImageButton) findViewById(R.id.btnTujuan);
        btnAbout = (ImageButton) findViewById(R.id.btnAbout);

        btnKeuangan.setOnClickListener(this);
        btnTujuan.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnKeuangan :

                // Load Ad
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

                startActivity(new Intent(this, CatatanKeuangan.class));

                break;
            case R.id.btnTujuan :

                // Load Ad
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

                startActivity(new Intent(this, TujuanKeuangan.class));

                break;
            case R.id.btnAbout :

                // Load Ad
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

                startActivity(new Intent(this, AboutMe.class));

                break;
        }

    }

}
