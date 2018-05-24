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
    private BaseSitiosHelper db;
    // references to our images
    public  ArrayList<Integer> mThumbIds;
    public ImageAdapter(Context c,ArrayList<String> fotos) {
        mContext = c;
        mThumbIds= new ArrayList<Integer>();
        int marcador =0;
        mThumbIds.add(R.drawable.bird0);
          /*   cursor=mContext.;    btenerLugares();
            if (cursor.moveToFirst()) {
                do {
                    LatLng coord = new LatLng(c.getDouble(1), c.getDouble(2));
                    sitios.add(mMap.addMarker(new MarkerOptions().position(coord).title(c.getString(0))));
                } while(cursor.moveToNext());
            }*/

         for(int i=0;i<fotos.size();i++) {
              mThumbIds.add(mContext.getResources().getIdentifier(fotos.get(i), "drawable", mContext.getPackageName()));
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