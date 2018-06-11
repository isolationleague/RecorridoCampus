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
    public ImageAdapter(Context c,String l) {
        mContext = c;
        lugar =l;
        mThumbIds= new ArrayList<Integer>();
        int marcador =0;
        mThumbIds.add(R.drawable.cat);
        //mThumbIds.add(R.drawable.bird0);
        /*switch (lugar){
            case "Edificio de la Facultad de Ciencias Económica":
                mThumbIds.add(R.drawable.economicas1);
                mThumbIds.add(R.drawable.economicas2);
                mThumbIds.add(R.drawable.economicas3);
                mThumbIds.add(R.drawable.economicas4);
                mThumbIds.add(R.drawable.economicas5);
            break;
            case "Edificio de la Facultad de Ingeniería":
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
            case "Edificio de la Escuela de Arquitectura,arquitectura":

                break;
            case "Casa del SINDEU":
                mThumbIds.add(R.drawable.sindeu);
                break;
            case "Busto del profesor Carlos Monge Alfaro":
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
                mThumbIds.add(R.drawable.erodrigof);
                break;
            case "Edificio de Escuela de Estudios Generales":
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
                mThumbIds.add(R.drawable.jardin1);
                mThumbIds.add(R.drawable.jardin2);
                break;
            case "Edificio de la Escuela de Química":
                mThumbIds.add(R.drawable.quimica1);
                mThumbIds.add(R.drawable.quimica2);
                mThumbIds.add(R.drawable.quimica3);
                break;
            case "Busto de Clodomiro Picado por Juan Rafael Chacón":
                mThumbIds.add(R.drawable.bclodomirop);
                break;
            case "Edificio de la Facultad de Microbiología":
                mThumbIds.add(R.drawable.micro1);
                mThumbIds.add(R.drawable.micro2);
                break;
            case "Busto de Pasteur":

                break;
            case "Plaza 24 de abril":
                mThumbIds.add(R.drawable.abril1);
                mThumbIds.add(R.drawable.abril2);
                mThumbIds.add(R.drawable.abril3);
                break;
            case "Edificio de la Facultad de Educación":
                mThumbIds.add(R.drawable.educa1);
                mThumbIds.add(R.drawable.educa2);
                mThumbIds.add(R.drawable.educa3);
                mThumbIds.add(R.drawable.educa4);
                mThumbIds.add(R.drawable.educa5);
                mThumbIds.add(R.drawable.educa6);
                mThumbIds.add(R.drawable.educa7);

                break;

        }*/

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