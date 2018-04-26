package com.example.admlab105.recorridocampus;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    BaseSitiosHelper dB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dB = new BaseSitiosHelper(this);
        SQLiteDatabase db = dB.getReadableDatabase();

        Intent prueba = new Intent(getApplicationContext(), PruebadeBase.class);
        startActivity(prueba);

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseSitiosContract.SitioBase._ID,
                BaseSitiosContract.SitioBase.COLUMN_NOMBRE,
                BaseSitiosContract.SitioBase.COLUMN_COORDENADA_Y,
                BaseSitiosContract.SitioBase.COLUMN_COORDENADA_X,
                BaseSitiosContract.SitioBase.COLUMN_VISITADO
        };

// Filter results WHERE "title" = 'My Title'
        String selection = BaseSitiosContract.SitioBase._ID + " = 1";
        for(String a: projection){
            System.out.println(a);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
