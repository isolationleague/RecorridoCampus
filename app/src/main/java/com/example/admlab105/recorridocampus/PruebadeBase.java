package com.example.admlab105.recorridocampus;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
public class PruebadeBase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebade_base);
        final BaseSitiosHelper db =  BaseSitiosHelper.getInstance(this.getApplicationContext());
        final Button button = findViewById(R.id.button);
        final Button bcargar = findViewById(R.id.bcargar);
        final EditText text = findViewById(R.id.etext);
        final Button bAddU = findViewById(R.id.buttonaddU);
        final Button bconsultLugares = findViewById(R.id.bconsultarlugares);

        /*boton agregar usuario*/
       /* bAddU.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
               db.agregarUsuario(text.getText().toString());

            }
        });
        */

        /*Boton ver lugares*/
        bconsultLugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c=db.obtenerLugares();
                String nombre="";
                String cx="";
                String cy="";
                int cantidadFilas = c.getCount();
                if (c.moveToFirst()) {
                    do {

                        nombre= c.getString(0);
                        cx = c.getString(1);
                        cy = c.getString(2);

                        Toast.makeText(context,nombre,Toast.LENGTH_SHORT).show();

                    } while(c.moveToNext());
                }

            }
        });

        /*Boton cargar base*/
        bcargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // db.cargar();
            }
        });

        /*Boton agregar usuario*/
       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Cursor c=db.verUsuario();
               String nombre="";
               String puntos="";
                if (c.moveToFirst()) {
                    do {
                        nombre= c.getString(0);
                        puntos = c.getString(1);

                    } while(c.moveToNext());
                }
                Toast.makeText(context,nombre,Toast.LENGTH_SHORT).show();
            }
        });

        */


    }
}
