package app.vanillatiramisu.com.hematassistant;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Utility {

    public static final String FILE_EXTENSION = ".bin";

    // Save file
    public static boolean simpanKeuangan(Context context,Keuangan keuangan){

        String fileName = keuangan.getmWaktu() + FILE_EXTENSION;

        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos = context.openFileOutput(fileName, context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(keuangan);
            fos.close();
            oos.close();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean simpanTujuan(Context context, Tujuan tujuan){

        String fileName = String.valueOf(tujuan.getId_tujuan()) + FILE_EXTENSION;

        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos = context.openFileOutput(fileName, context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(tujuan);
            fos.close();
            oos.close();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }


    // Load file
    public static Keuangan getKeuanganByFileName(Context context, String fileName){
        File file = new File(context.getFilesDir(), fileName);
        if(file.exists() && !file.isDirectory()){

            Log.v("UTILITIES", "File exsist =" + fileName);

            FileInputStream fis;
            ObjectInputStream ois;

            try {
                fis = context.openFileInput(fileName);
                ois = new ObjectInputStream(fis);
                Keuangan keuangan = (Keuangan) ois.readObject();
                fis.close();
                ois.close();

                return keuangan;

            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();

                return null;
            }
        }else {
            return null;
        }

    }

    public static Tujuan getTujuanByFileName(Context context, String fileName){

        File file = new File(context.getFilesDir(), fileName);
        if(file.exists() && !file.isDirectory()){

            Log.v("UTILITIES", "File exsist =" + fileName);

            FileInputStream fis;
            ObjectInputStream ois;

            try {
                fis = context.openFileInput(fileName);
                ois = new ObjectInputStream(fis);
                Tujuan tujuan = (Tujuan) ois.readObject();
                fis.close();
                ois.close();

                return tujuan;

            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();

                return null;
            }
        }else {
            return null;
        }
    }

}
