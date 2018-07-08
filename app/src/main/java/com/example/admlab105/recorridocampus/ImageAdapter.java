package com.example.admlab105.recorridocampus;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private String lugar;
    //private BaseSitiosHelper db;
    // references to our images
    public  ArrayList<Integer> mThumbIds;

    /**
     * Inicializa los valores del contenedor de imagenes
     * @param c contexto de la aplicacion
     * @param l String que es el nombre del lugar del cual se deben cargar las imagenes
     */
    public ImageAdapter(Context c,String l) {
        mContext = c;
        lugar =l;
        mThumbIds= new ArrayList<Integer>();
        int marcador =0;
        //mThumbIds.add(R.drawable.bird0);
        switch (lugar){
            case "Edificio de la Facultad de Ciencias Económicas":
                mThumbIds.add(R.drawable.economicasp);
                mThumbIds.add(R.drawable.economicas1);
                mThumbIds.add(R.drawable.economicas2);
                mThumbIds.add(R.drawable.economicas3);
                mThumbIds.add(R.drawable.economicas4);
                mThumbIds.add(R.drawable.economicas5);
                break;
            case "Edificio de la Facultad de Ingeniería":
                mThumbIds.add(R.drawable.ingep);
                mThumbIds.add(R.drawable.inge1);
                mThumbIds.add(R.drawable.inge2);
                mThumbIds.add(R.drawable.inge3);
                mThumbIds.add(R.drawable.inge4);
                mThumbIds.add(R.drawable.inge5);
                mThumbIds.add(R.drawable.inge6);
                mThumbIds.add(R.drawable.inge7);
                mThumbIds.add(R.drawable.inge8);
                mThumbIds.add(R.drawable.inge9);
                break;
            case "Busto de Clodomiro Picado por Juan Rafael Chacón":
                mThumbIds.add(R.drawable.bclodomiropp);
                mThumbIds.add(R.drawable.bclodomirop);
                break;

            case "Instituto Confucio y  Casa del SINDEU":
                mThumbIds.add(R.drawable.sindeup);
                mThumbIds.add(R.drawable.sindeu02);
                mThumbIds.add(R.drawable.sindeu01);
                mThumbIds.add(R.drawable.sindeu);
                break;
            case "Busto del profesor Carlos Monge Alfaro":
                mThumbIds.add(R.drawable.bcarlosmonge);
                mThumbIds.add(R.drawable.bcarlosmonge1);
                mThumbIds.add(R.drawable.bcarlosmonge2);
                break;
            case "Fuente de cupido y el cisne":
                mThumbIds.add(R.drawable.fuente1);
                mThumbIds.add(R.drawable.fuente2);
                mThumbIds.add(R.drawable.fuente3);
                mThumbIds.add(R.drawable.fuente4);
                break;
            case "Estatua de Rodrigo Facio Brenes":
                mThumbIds.add(R.drawable.erodrigofp);
                mThumbIds.add(R.drawable.erodrigof);
                break;
            case "Edificio de Escuela de Estudios Generales":
                mThumbIds.add(R.drawable.generalesp);
                mThumbIds.add(R.drawable.generales1);
                mThumbIds.add(R.drawable.generales2);
                mThumbIds.add(R.drawable.generales3);
                mThumbIds.add(R.drawable.generales4);
                mThumbIds.add(R.drawable.generales5);
                mThumbIds.add(R.drawable.generales6);
                mThumbIds.add(R.drawable.generales7);
                mThumbIds.add(R.drawable.generales8);
                mThumbIds.add(R.drawable.generales9);
                break;
            case "Jardín escultórico":
                mThumbIds.add(R.drawable.jardinp);
                mThumbIds.add(R.drawable.jardin1);
                mThumbIds.add(R.drawable.jardin2);
                break;
            case "Edificio de la Escuela de Química":
                mThumbIds.add(R.drawable.quimicap);
                mThumbIds.add(R.drawable.quimica1);
                mThumbIds.add(R.drawable.quimica2);
                mThumbIds.add(R.drawable.quimica3);
                break;
            case "Escultura de Leda Astorga":
                mThumbIds.add(R.drawable.ledaastorga);
                break;
            case "Bronce. Marisel Jiménez Rittner. Biblioteca Luis Demetrio Tinoco":
                mThumbIds.add(R.drawable.bronce);
                break;
            case "Busto de Luis Demetrio Tinoco":
                mThumbIds.add(R.drawable.luisdemetriot);
                break;
            case "Busto del Lic. Fernando Baudrit Solera":
                mThumbIds.add(R.drawable.fernandobaudrit);
                break;
            case "Eva. Bronce":
                mThumbIds.add(R.drawable.evabronce);
                break;
            case "Conjunto de esculturas: bustos de Margarita Bertheau":
                mThumbIds.add(R.drawable.yolandaeunicemargarita);
                break;
            case "Esculturas de José Sancho":
                mThumbIds.add(R.drawable.portadajs);
                mThumbIds.add(R.drawable.antarticosjs);
                mThumbIds.add(R.drawable.gransierpejs);
                mThumbIds.add(R.drawable.ososamorososjs);
                mThumbIds.add(R.drawable.hachasjs);
                mThumbIds.add(R.drawable.parejajs);
                mThumbIds.add(R.drawable.reptiljs);
                mThumbIds.add(R.drawable.tropeljs);
                break;
            case "Edificio de la Facultad de Microbiología":
                mThumbIds.add(R.drawable.microbiologiap);
                mThumbIds.add(R.drawable.microbiologia01);
                mThumbIds.add(R.drawable.micro1);
                mThumbIds.add(R.drawable.micro2);
                break;
            case "Busto de Pasteur":
                 mThumbIds.add(R.drawable.pasteur);
                break;
            case "Plaza 24 de abril":
                mThumbIds.add(R.drawable.abrilp);
                mThumbIds.add(R.drawable.abril1);
                mThumbIds.add(R.drawable.abril2);
                mThumbIds.add(R.drawable.abril3);
                break;
            case "Edificio de la Facultad de Educación":
                mThumbIds.add(R.drawable.educap);
                mThumbIds.add(R.drawable.educa1);
                mThumbIds.add(R.drawable.educa2);
                mThumbIds.add(R.drawable.educa3);
                mThumbIds.add(R.drawable.educa4);
                mThumbIds.add(R.drawable.educa5);
                mThumbIds.add(R.drawable.educa6);
                mThumbIds.add(R.drawable.educa7);
                break;
            case "Edificio de la Facultad de Medicina":
                mThumbIds.add(R.drawable.medicina);
                break;
            case "Edificio de Escuela Centroamericana de Geología":
                mThumbIds.add(R.drawable.geologiap1);
                mThumbIds.add(R.drawable.geologiap2);
                break;
            case "Edificio de la Escuela de Arquitectura":
                mThumbIds.add(R.drawable.arquip);
                mThumbIds.add(R.drawable.arqui);
                break;
            case "Busto de Omar Dengo":
                mThumbIds.add(R.drawable.omardengo);
                break;
            case "Busto del Dr. Solón Núñez Frutos" :
                mThumbIds.add(R.drawable.frutos);
                break;
            case "Busto del Dr. José Joaquín Jiménez N.":
                mThumbIds.add(R.drawable.bjosejoaquinjimenez);
                break;
            case "Busto del Dr. Rafael Angel Calderón Guardia":
                mThumbIds.add(R.drawable.calderon);
                break;
            case "Busto del Ing. Fabio Baudrit Moreno" :
                mThumbIds.add(R.drawable.fabio_baudrit);
                break;
            case "Intro":
                mThumbIds.add(R.drawable.tempera_baja_calidad);
                break;
        }

    }

    public int getCount() {
        return mThumbIds.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds.get(position));
        return imageView;
    }



};