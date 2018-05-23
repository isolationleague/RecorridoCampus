package com.example.admlab105.recorridocampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import android.database.Cursor;

import java.util.ArrayList;


public class InfoPicturesFragment extends Fragment {
    private static final String TAG = "InfoPicturesFragment";

    private GridView grid00;
    private String etiqueta;
    private BaseSitiosHelper db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        etiqueta = this.getArguments().getString("etiq");
        View view = inflater.inflate(R.layout.info_pictures_fragment, container, false);
        grid00 = (GridView) view.findViewById(R.id.grid_images);
        //se obtienen la lista de imagenes segun la etiqueta
        db = BaseSitiosHelper.getInstance(this.getContext());
        Cursor cursor = db.obtenerImagenesDeSitio(etiqueta);
        ArrayList<String> fotos = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String nombreFoto = cursor.getString(0);
                fotos.add(nombreFoto);
            } while (cursor.moveToNext());
        }

            grid00.setAdapter(new ImageAdapter(getActivity(), fotos));
            grid00.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    // Sending image id to FullScreenActivity
                    Intent i = new Intent(getActivity(), FullImageActivity.class);
                    // passing array index
                    i.putExtra("id", position);
                    startActivity(i);

                }
            });
            return view;

    }
}