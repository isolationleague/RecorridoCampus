package com.example.admlab105.mapa;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapaCampus extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int SITIOS = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_campus);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /*// Edificio de la Escuela de Arquitectura
        LatLng sitio1 = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sitio1).title("xxxxxxxxxx"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sitio1));*/

        List<LatLng> sitios = new ArrayList<LatLng>();

        for (int i = 1; i <= SITIOS; i++) {
            //sitios.set(i, new LatLng(-34, 151)); // Tomar coordenadas de la base
            sitios.set(i, new LatLng(lat[i], lng[i])); // Tomar coordenadas de la base

            // Título de cada marcador (Tomar nombre de cada sitio de la base)
            mMap.addMarker(new MarkerOptions().position(sitios.get(i)).title("xxxxxxxxx"));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(sitios.get(i)));
        }
    }
}
