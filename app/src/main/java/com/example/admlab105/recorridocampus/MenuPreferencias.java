package com.example.admlab105.recorridocampus;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuPreferencias extends AppCompatActivity{

    Button btnPref;
    Button save_user;
    String line;
    TextView user;
    AlertDialog user_input;
    EditText user_edit;
    Button btnimagen;
    ImageView imagen_usuario;
    static final int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_preferencias);
        final Switch vibracion = findViewById(R.id.switch1);
        vibracion.setChecked(true);

        /**
         *Cambia el contenido del archivo de preferencias
         */

        vibracion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
                    writeToFile("1","preferencias");
                }
                else {
                    writeToFile("0","preferencias");
                }
            }
        });

        user=findViewById(R.id.user_text);

        /**
         * Si el usuario ha cambiado el nombre carga el nombre guardado
         */

        if(!ReadFile("usuario").equals("1")){
            user.setText(ReadFile("usuario"));
        }
        else{
            user.setText("Usuario");
        }

        /**
         * Genera un AlertDialog con texto para que se modifique el nombre
         * de usuario
         */

        user_input=new AlertDialog.Builder(this).create();
        user_edit=new EditText(this);
        user_input.setTitle("Introduzca Nombre de Usuario");
        user_input.setView(user_edit);
        user_input.setButton(DialogInterface.BUTTON_POSITIVE, "Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                user.setText(user_edit.getText());
                writeToFile(user.getText().toString(),"usuario");

            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_edit.setText(user.getText());
                user_input.show();
            }
        });

        btnPref = findViewById(R.id.btnPrueba);
        btnPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String res=ReadFile("preferencias");
                System.out.println(res);
                if(res.equals("1")){
                    prueba();
                }
                else {
                    Toast.makeText(getApplicationContext(), "La Vibracion esta desactivada",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnimagen = findViewById(R.id.btn_imagen);
        btnimagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),REQUEST_CODE);
            }
        });
        File imagen=new File(getFilesDir() + File.separator + "imagen", "imagen.jpg");
        if(imagen.exists()){
            cargarimagen();
        }

    }

    /**
     * Activa la vibracion por 0.5 segundos
     */

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

    /**
     * Abre el selector de imagenes y si se selecciona una imagen la guarda
     * en la carpeta de aplicación
     */


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {

                case REQUEST_CODE:
                    if (resultCode == Activity.RESULT_OK) {
                        //data gives you the image uri. Try to convert that to bitmap
                        Uri selectedImage = data.getData();
                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                            escribirImagen(bitmap);
                            cargarimagen();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else if (resultCode == Activity.RESULT_CANCELED) {
                       // Log.e(TAG, "Selecting picture cancelled");
                    }
                    break;
            }
        } catch (Exception e) {
            //Log.e(TAG, "Exception in onActivityResult : " + e.getMessage());
        }
    }


    /**
     * Lee un archivo
     * @param location el nombre de la carpeta donde se guarda el contenido
     * @return devuelve el contenido del archivo
     */

    public String ReadFile(String location) {

        File lugar = new File(getFilesDir() + File.separator + location+"/"+location+".txt");
        if (!lugar.exists()) {
            System.out.println("No existo asi que salgo");
            return "1";
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(new File(getFilesDir() + File.separator + location+"/"+location+".txt"));
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

    /**
     * Escribe el archivo
     * @param data Lo que se va a guardar
     * @param location donde se va a guardar
     */

    private void writeToFile(String data, String location) {

        File folder = new File(getFilesDir() + File.separator + location);

        if(!folder.exists()){
            System.err.println("Se hizo el folder");
            folder.mkdir();
        }


        File pref_txt=new File(folder,location+".txt");

        if(pref_txt.exists()){
            System.out.println("El archivo existe");
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter(pref_txt);
            writer.append(data);
            //writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guarda la imagen seleccionada
     * @param image la imagen que se selecciono
     */

    private void escribirImagen(Bitmap image) {

        File folder = new File(getFilesDir() + File.separator + "imagen");

        if(!folder.exists()){
            System.err.println("Se hizo el folder");
            folder.mkdir();
        }


        File user_image=new File(folder,"imagen.jpg");

        if(user_image.exists()){
            System.out.println("El archivo existe");
        }
        FileOutputStream fos=null;

        try {
            fos= new FileOutputStream(user_image);
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga la imagen al imageview del layout
     */

    private void cargarimagen()
    {

        try {
            File f=new File(getFilesDir() + File.separator + "imagen", "imagen.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView img = findViewById(R.id.iconouser);
            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

}
