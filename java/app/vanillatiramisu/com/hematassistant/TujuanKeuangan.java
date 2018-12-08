package app.vanillatiramisu.com.hematassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class TujuanKeuangan extends AppCompatActivity {
    
    private Tujuan mLoadTujuan;
    private String mFileName;
    int id;


    private EditText tujuan1, tujuan2, tujuan3, tujuan4, tujuan5;
    private ImageButton saveTujuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tujuan_keuangan);

        tujuan1 = (EditText) findViewById(R.id.tujuan1);
        tujuan2 = (EditText) findViewById(R.id.tujuan2);
        tujuan3 = (EditText) findViewById(R.id.tujuan3);
        tujuan4 = (EditText) findViewById(R.id.tujuan4);
        tujuan5 = (EditText) findViewById(R.id.tujuan5);
        saveTujuan = (ImageButton) findViewById(R.id.saveTujuan);

        // Load masing2 tujuan
        loadTujuan();
        
        // Save masing2 tujuan
        saveTujuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                saveTujuan();
                Toast.makeText(getApplicationContext(), "Tujuan keuangan disimpan", Toast.LENGTH_SHORT).show();
                
            }
        });
    }
    
    private void loadTujuan(){
        
        // Load tujuan1
        id = 1;
        mFileName = "" + id +Utility.FILE_EXTENSION;
        mLoadTujuan = Utility.getTujuanByFileName(getApplicationContext(),mFileName);
        if(mLoadTujuan != null){
            tujuan1.setText(mLoadTujuan.getmTujuan());
        }

        // Load tujuan1
        id = 2;
        mFileName = "" + id +Utility.FILE_EXTENSION;
        mLoadTujuan = Utility.getTujuanByFileName(getApplicationContext(),mFileName);
        if(mLoadTujuan != null){
            tujuan2.setText(mLoadTujuan.getmTujuan());
        }

        // Load tujuan1
        id = 3;
        mFileName = "" + id +Utility.FILE_EXTENSION;
        mLoadTujuan = Utility.getTujuanByFileName(getApplicationContext(),mFileName);
        if(mLoadTujuan != null){
            tujuan3.setText(mLoadTujuan.getmTujuan());
        }

        // Load tujuan1
        id = 4;
        mFileName = "" + id +Utility.FILE_EXTENSION;
        mLoadTujuan = Utility.getTujuanByFileName(getApplicationContext(),mFileName);
        if(mLoadTujuan != null){
            tujuan4.setText(mLoadTujuan.getmTujuan());
        }

        // Load tujuan1
        id = 5;
        mFileName = "" + id +Utility.FILE_EXTENSION;
        mLoadTujuan = Utility.getTujuanByFileName(getApplicationContext(),mFileName);
        if(mLoadTujuan != null){
            tujuan5.setText(mLoadTujuan.getmTujuan());
        }
        
    }
    
    private void saveTujuan(){
        String temp;
        int temp_id;
        
        temp = tujuan1.getText().toString();
        temp_id = 1;
        Utility.simpanTujuan(getApplicationContext(), new Tujuan(temp_id, temp));

        temp = tujuan2.getText().toString();
        temp_id = 2;
        Utility.simpanTujuan(getApplicationContext(), new Tujuan(temp_id, temp));

        temp = tujuan3.getText().toString();
        temp_id = 3;
        Utility.simpanTujuan(getApplicationContext(), new Tujuan(temp_id, temp));

        temp = tujuan4.getText().toString();
        temp_id = 4;
        Utility.simpanTujuan(getApplicationContext(), new Tujuan(temp_id, temp));

        temp = tujuan5.getText().toString();
        temp_id = 5;
        Utility.simpanTujuan(getApplicationContext(), new Tujuan(temp_id, temp));

        
    }

}



