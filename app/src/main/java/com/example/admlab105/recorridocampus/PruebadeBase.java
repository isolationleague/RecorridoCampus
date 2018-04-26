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

public class PruebadeBase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebade_base);
        final BaseSitiosHelper db = new BaseSitiosHelper(this);
        final Button button = findViewById(R.id.button);
        final EditText text = findViewById(R.id.etext);
        final Button bAddU = findViewById(R.id.buttonaddU);

        bAddU.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
               db.agregarUsuario(text.getText().toString());

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Cursor c=db.verUsuario();
               String nombre="";
               String puntos="";
                if (c.moveToFirst()) {
                    do {
                        nombre= c.getString(0);
                        puntos = c.getString(1);
                        Toast.makeText(context,nombre,Toast.LENGTH_SHORT);
                    } while(c.moveToNext());
                }

                db.close();
            }
        });

    }
}
