package com.example.admlab105.recorridocampus;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by usuario on 18/04/2018.
 */

public class BaseSitiosHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BaseSitios.db";

    public BaseSitiosHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase dB) {
        dB.execSQL("CREATE TABLE " + BaseSitiosContract.SitioBase.TABLE_NAME + " (" +
                BaseSitiosContract.SitioBase._ID + " INTEGER PRIMARY KEY," +
                BaseSitiosContract.SitioBase.COLUMN_NOMBRE + " TEXT" + "," +
                BaseSitiosContract.SitioBase.COLUMN_COORDENADA_X + " REAL" + "," +
                BaseSitiosContract.SitioBase.COLUMN_COORDENADA_Y + " REAL" + "," +
                BaseSitiosContract.SitioBase.COLUMN_VISITADO + " INTEGER" + " )");

        llenaBase(dB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dB, int oVersion, int nVersion) {
        dB.execSQL("DROP TABLE IF EXISTS " + BaseSitiosContract.SitioBase.TABLE_NAME);
        onCreate(dB);
    }

    void llenaBase(SQLiteDatabase dB){
        ContentValues values = new ContentValues();
        values.put(BaseSitiosContract.SitioBase.COLUMN_NOMBRE, "ECCI");
        values.put(BaseSitiosContract.SitioBase.COLUMN_COORDENADA_X, 9.937924599999999);
        values.put(BaseSitiosContract.SitioBase.COLUMN_COORDENADA_X, -84.05199019999998);
        values.put(BaseSitiosContract.SitioBase.COLUMN_VISITADO, 0);

        long newRowId = dB.insert(BaseSitiosContract.SitioBase.TABLE_NAME , null, values);
        System.out.println(newRowId);



    }
}
