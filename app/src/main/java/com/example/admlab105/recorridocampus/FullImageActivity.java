package com.example.admlab105.recorridocampus;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.ArrayList;

public class FullImageActivity extends Activity {

    /**
     * Funcion de despliegue en pantalla completa de una imagen seleccionada en el grid de
     * imagenes del sitio seleccionado
     * @param savedInstanceState estado de instanciacion
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullimage);

        // get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        String etiqueta = i.getExtras().getString("etiq");

        ArrayList<String>fotos = new ArrayList<>();//***por ahora VACIO
        ImageAdapter imageAdapter = new ImageAdapter(this,etiqueta);

        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageResource(imageAdapter.mThumbIds.get(position));

    }

    /**
     * Al tocar la imagen desplegada en pantalla completa, retorna a la vista del Grid
     * @param event evento de toque de la imagen
     * @return retorna a la actividad anterior
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.finish();
        return super.onTouchEvent(event);
    }
}