package com.example.admlab105.recorridocampus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by usuario on 18/04/2018.
 */

public class BaseSitiosHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BaseSitios.db";
    Context context;

    public BaseSitiosHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase dB) {
        context = this.context;

        dB.execSQL("CREATE TABLE " + BaseSitiosContract.SitioBase.TABLE_NAME + " (" +
                BaseSitiosContract.SitioBase._ID + " INTEGER PRIMARY KEY," +
                BaseSitiosContract.SitioBase.COLUMN_NOMBRE + " TEXT" + "," +
                BaseSitiosContract.SitioBase.COLUMN_COORDENADA_X + " REAL" + "," +
                BaseSitiosContract.SitioBase.COLUMN_COORDENADA_Y + " REAL" + "," +
                BaseSitiosContract.SitioBase.COLUMN_VISITADO + " INTEGER" + " )");

        dB.execSQL("CREATE TABLE " + BaseSitiosContract.Usuario.TABLE_NAME + " (" +
                BaseSitiosContract.Usuario._ID + " INTEGER PRIMARY KEY," +
                BaseSitiosContract.Usuario.USUARIO_NOMBRE + " TEXT" + "," +
                BaseSitiosContract.Usuario.USUARIO_PUNTOS + " INTEGER" + " )");

        dB.execSQL("CREATE TABLE " + BaseSitiosContract.Foto.TABLE_NAME + " (" +
                BaseSitiosContract.Foto._ID + " INTEGER PRIMARY KEY," +
                BaseSitiosContract.Foto.ID_SITIO + " INTEGER " + "," +
                BaseSitiosContract.Foto.RUTA + " TEXT " + " )");

        dB.execSQL("CREATE TABLE " + BaseSitiosContract.Video.TABLE_NAME + " (" +
                BaseSitiosContract.Video._ID + " INTEGER PRIMARY KEY," +
                BaseSitiosContract.Video.ID_SITIO + " INTEGER " + "," +
                BaseSitiosContract.Video.RUTA + " TEXT " + " )");

        dB.execSQL("CREATE TABLE " + BaseSitiosContract.Audio.TABLE_NAME + " (" +
                BaseSitiosContract.Audio._ID + " INTEGER PRIMARY KEY," +
                BaseSitiosContract.Audio.ID_SITIO + " INTEGER " + "," +
                BaseSitiosContract.Audio.RUTA + " TEXT " + " )");

        dB.execSQL("CREATE TABLE " + BaseSitiosContract.Texto.TABLE_NAME + " (" +
                BaseSitiosContract.Texto._ID + " INTEGER PRIMARY KEY," +
                BaseSitiosContract.Texto.ID_SITIO + " INTEGER " + "," +
                BaseSitiosContract.Texto.RUTA + " TEXT " + " )");

        try {
            llenaBase(dB);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase dB, int oVersion, int nVersion) {
        dB.execSQL("DROP TABLE IF EXISTS " + BaseSitiosContract.SitioBase.TABLE_NAME);
        onCreate(dB);
    }

    void llenaBase(SQLiteDatabase dB) throws FileNotFoundException {
    /*   ContentValues values = new ContentValues();
        values.put(BaseSitiosContract.SitioBase.COLUMN_NOMBRE, "ECCI");
        values.put(BaseSitiosContract.SitioBase.COLUMN_COORDENADA_X, 9.937924599999999);
        values.put(BaseSitiosContract.SitioBase.COLUMN_COORDENADA_Y, -84.05199019999998);
        values.put(BaseSitiosContract.SitioBase.COLUMN_VISITADO, 0);
        long newRowId = dB.insert(BaseSitiosContract.SitioBase.TABLE_NAME, null, values);
        System.out.println(newRowId);
        */

        File file = new File("coordenadas.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        try {
            while ((st = br.readLine()) != null)
             st.split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    }

    public void agregarUsuario(String nombre) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            //db.execSQL("INSERT INTO Usuario VALUES('"+nombre+"','"+0+"')");
            ContentValues values = new ContentValues();
            values.put(BaseSitiosContract.Usuario.USUARIO_NOMBRE, nombre);
            values.put(BaseSitiosContract.Usuario.USUARIO_PUNTOS, 0);
            long newRowId = db.insert(BaseSitiosContract.Usuario.TABLE_NAME, null, values);
            db.close();
        } else {
            Toast.makeText(context, "No se pudo abrir base de datos", Toast.LENGTH_SHORT);

        }
    }

    public String[] obtenerLugares() {
        String[] lugares = null;

        return lugares;
    }

    public Cursor verUsuario() {
        SQLiteDatabase db = getReadableDatabase();
        String nombre = "", puntos = "";
        Cursor c=null;

        if (db != null) {
            return c = db.rawQuery(" SELECT nombre,puntos FROM Usuario ", null);

        }
        //Toast.makeText(context ,"",Toast.LENGTH_SHORT).show();
        return c;
    }
}