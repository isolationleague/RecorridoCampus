package com.example.admlab105.recorridocampus;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import android.util.Log;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Esta clase se encargar de poblar la base de datos y prooveer metodos de acceso a la base.
 */

public class BaseSitiosHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BaseSitios.db";
    Context context;

    public BaseSitiosHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    /**
     * Crear las tablas y cargar la base, se ejecuta solo una vez
     * @param dB base de datos
     */
    public void onCreate(SQLiteDatabase dB) {

        dB.execSQL("CREATE TABLE " + BaseSitiosContract.SitioBase.TABLE_NAME + " (" +
                BaseSitiosContract.SitioBase._ID + " INTEGER PRIMARY KEY," +
                BaseSitiosContract.SitioBase.COLUMN_NOMBRE + " TEXT" + "," +
                BaseSitiosContract.SitioBase.COLUMN_COORDENADA_X + " REAL" + "," +
                BaseSitiosContract.SitioBase.COLUMN_COORDENADA_Y + " REAL" + "," +
                BaseSitiosContract.SitioBase.COLUMN_VISITADO + " INTEGER" + " )");

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

        llenaBase(dB);
    }

    /**
     * Este metodo actualiza la base de datos
     * @param dB base de datos que se quiere actualizar
     * @param oVersion numero anterior de version de base de datos
     * @param nVersion numero actual de version de base de datos.
     */
    public void onUpgrade(SQLiteDatabase dB, int oVersion, int nVersion) {
        dB.execSQL("DROP TABLE IF EXISTS " + BaseSitiosContract.SitioBase.TABLE_NAME);
       // dB.execSQL("DROP TABLE IF EXISTS " + BaseSitiosContract.Usuario.TABLE_NAME);
        dB.execSQL("DROP TABLE IF EXISTS " + BaseSitiosContract.Foto.TABLE_NAME);
        dB.execSQL("DROP TABLE IF EXISTS " + BaseSitiosContract.Video.TABLE_NAME);
        dB.execSQL("DROP TABLE IF EXISTS " + BaseSitiosContract.Texto.TABLE_NAME);
        dB.execSQL("DROP TABLE IF EXISTS " + BaseSitiosContract.Audio.TABLE_NAME);
        onCreate(dB);
    }

    /**
     * Llena la base de datos con la informacion de sitios en archivo llamado coordenadas
     * @param dB base de datos
     */
    void llenaBase(SQLiteDatabase dB) {

        //coordenadas
        try
        {
            InputStream fraw =  context.getResources().openRawResource(R.raw.coordenadas);
            BufferedReader br = new BufferedReader(new InputStreamReader(fraw));
            String linea ="";
            String[] sitioPartes = null;
            int count = 0;
            while ((linea = br.readLine()) != null) {
                sitioPartes = linea.split(",");    //nombre,coordenada x y coordenada y numero de fotos
                ContentValues values = new ContentValues();
                values.put(BaseSitiosContract.SitioBase.COLUMN_NOMBRE, sitioPartes[0]);
                values.put(BaseSitiosContract.SitioBase.COLUMN_COORDENADA_X, sitioPartes[1]);
                values.put(BaseSitiosContract.SitioBase.COLUMN_COORDENADA_Y, sitioPartes[2]);
                values.put(BaseSitiosContract.SitioBase.COLUMN_VISITADO, 0);
                long newRowId = dB.insert(BaseSitiosContract.SitioBase.TABLE_NAME, null, values);

                //carga las imagenes de cada sitio
                int cantidadFotos = Integer.parseInt(sitioPartes[3]);
                for(int i=0;i<cantidadFotos;i++){
                    values.put(BaseSitiosContract.Foto.ID_SITIO, newRowId);
                    values.put(BaseSitiosContract.Foto.RUTA,"");
                    long iRowId = dB.insert(BaseSitiosContract.Foto.TABLE_NAME, null, values);

                }


                count ++;
            }

            fraw.close();
            br.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde recurso raw");
        }




    }


    /**
     * Devuelve un cursor que da acceso a todas las tuplas en los campos nombre ,coordenadaX y coordenadaY de la tabla sitio.
     * @return cursor
     */
    public Cursor obtenerLugares() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c=null;

        if (db != null) {
            c = db.rawQuery(" SELECT nombre,coordenadaX,coordenadaY FROM sitio ", null);
        }
        return c;
    }

}