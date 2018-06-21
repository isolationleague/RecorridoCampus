package com.example.admlab105.recorridocampus;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ZoomControls;

import java.util.ArrayList;

public class FullImageActivity extends Activity {

    /**
     * Funcion de despliegue en pantalla completa de una imagen seleccionada en el grid de
     * imagenes del sitio seleccionado
     *
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

        ArrayList<String> fotos = new ArrayList<>();//***por ahora VACIO
        ImageAdapter imageAdapter = new ImageAdapter(this, etiqueta);

        /*ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageResource(imageAdapter.mThumbIds.get(position));*/
        TouchImageView touchimageView = (TouchImageView) findViewById(R.id.full_image_view);
        touchimageView.setImageResource(imageAdapter.mThumbIds.get(position));
        Button button = (Button) findViewById(R.id.buttonCloseImage);
        button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v){
                   finish();
            }
        });

    }

    /**
     * Al tocar la imagen desplegada en pantalla completa, retorna a la vista del Grid
     *
     * @param event evento de toque de la imagen
     * @return retorna a la actividad anterior
     */
 /*   @Override
    public boolean onTouchEvent(MotionEvent event) {
       //
        return super.onTouchEvent(event);
    }
*/
}