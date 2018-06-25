package com.example.admlab105.recorridocampus;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuPreferencias extends AppCompatActivity{

    Button btnPref;
    String line;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_preferencias);
        //View view = inflater.inflate(R.layout.activity_menu_preferencias, container, false);
        final Switch vibracion = findViewById(R.id.switch1);
        vibracion.setChecked(true);

        vibracion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
                    writeToFile("1");
                }
                else {
                    writeToFile("0");
                }
            }
        });


        btnPref = findViewById(R.id.btnPrueba);
        btnPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String res=ReadFile();
                System.out.println(res);
                if(res.equals("1")){
                    prueba();
                }
                else {
                    Toast.makeText(getApplicationContext(), "La Vibracion esta desactivada",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void prueba(){
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
        }else{
            //deprecated in API 26
            v.vibrate(500);
        }

    }

    public String ReadFile() {

        File lugar = new File(getFilesDir() + File.separator + "preferencias/" + "preferencias.txt");
        if (!lugar.exists()) {
            System.out.println("No existo asi que salgo");
            return "1";
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(new File(getFilesDir() + File.separator + "preferencias/" + "preferencias.txt"));
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);// + System.getProperty("line.separator"));
                }
                fileInputStream.close();
                line = stringBuilder.toString();

                bufferedReader.close();
            } catch (FileNotFoundException ex) {
                //Log.d(TAG, ex.getMessage());
            } catch (IOException ex) {
                //Log.d(TAG, ex.getMessage());
            }
            System.out.println("Es un " + line);
            return line;
        }
    }

    private void writeToFile(String data) {

        File folder = new File(getFilesDir() + File.separator + "preferencias");

        if(!folder.exists()){
            System.err.println("Se hizo el folder");
            folder.mkdir();
        }


        File archivoimagen=new File(folder,"preferencias.txt");

        if(archivoimagen.exists()){
            System.out.println("El archivo existe");
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter(archivoimagen);
            writer.append(data);
            //writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
package com.example.jay.myapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        final Switch switchu = findViewById(R.id.switch1);
        final TextView text1 = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchu.isChecked()==true){
                    writeToFile("1");
                    //.setText("suo");
                }
                else {
                    writeToFile("0");
                }

                 text1.setText(ReadFile());
            }
        });
        switchu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    writeToFile("1");
                }
                if (isChecked==false){
                    writeToFile("0");
                }
            }
        });

    }

    public String ReadFile(){
        String line = null;

        try {
            FileInputStream fileInputStream = new FileInputStream (new File(getFilesDir()+ File.separator+"preferencias/" + "preferencias.txt"));

            File lugar = new File (getFilesDir()+ File.separator+"preferencias/" + "preferencias.txt");

            if(lugar.exists()){
                System.err.println("Existo");
            }

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(line + System.getProperty("line.separator"));
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            //Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            //Log.d(TAG, ex.getMessage());
        }
        System.out.println(line);
        return line;
    }


    private void writeToFile(String data) {

            File folder = new File(getFilesDir() + File.separator + "preferencias");

            if(!folder.exists()){
                System.err.println("Se hizo el folder");
                folder.mkdir();
            }


            File archivoimagen=new File(folder,"preferencias.txt");
            FileWriter writer = null;
        try {
            writer = new FileWriter(archivoimagen);
            writer.append(data);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}


 */