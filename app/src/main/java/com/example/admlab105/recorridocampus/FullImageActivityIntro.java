package com.example.admlab105.recorridocampus;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class FullImageActivityIntro extends Activity {

    /**
     * Funcion de despliegue en pantalla completa de una imagen o posibles imagenes varias de la introducción de la aplicación
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

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
       ArrayList<Integer> fotos = new ArrayList<Integer>();
        fotos.add(R.drawable.tempera_baja_calidad);
        ImagePagerAdapter adapter = new ImagePagerAdapter(fotos,position);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);

/*
        ArrayList<String>fotos = new ArrayList<>();//***por ahora VACIO
        ImageAdapter imageAdapter = new ImageAdapter(this,etiqueta);

        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageResource(imageAdapter.mThumbIds.get(position));
*/
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(this, Intro_activity.class);
        // passing array index
        startActivity(i);
        this.finish();
    }

    /**
     * Clase que implementa el slideshow para mostrar imagenes en la vista de informacion general del sitio
     */
    public class ImagePagerAdapter extends PagerAdapter {
        //obener imagenes para el slideshow
        //private int[] mImages;
        private ArrayList<Integer> mImages= new ArrayList<Integer>();
        private String nombreLugar="";
        int pos=0;
        /**
         * Inicializa los valores del pageadapter y recibe como parametro los nombres de   imagenes que debe mostrar
         * @param e arraylist con los nombres de imagenes
         */
        public ImagePagerAdapter(ArrayList<Integer> e,int position){
            mImages = e;
            pos=position;
        }

        /**
         *
         * @return Cantidad de imagenes de un sitio especifico
         */
        @Override
        public int getCount() {
            return mImages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        /**
         * Define el formato de el slideshow para el muestreo de las im'agenes
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = getApplicationContext();
            TouchImageView imageView = new TouchImageView(context);
            int padding = context.getResources().getDimensionPixelSize(
                    R.dimen.padding_small);
            imageView.setPadding(padding, padding, padding, padding);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setImageResource(mImages.get(position));
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        /**
         * Destructor del slideshow
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);

        }


    }

}