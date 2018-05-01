package com.example.admlab105.recorridocampus;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int puntos = 30;
    double lat = 0.0, lon = 0.0;
    private Marker marker;

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


        /*List<LatLng> sitios = new ArrayList<LatLng>();

        for (int i = 0; i < puntos; i++) {
            //sitios.set(i, new LatLng(-34, 151)); // Tomar coordenadas de la base
            sitios.add(new LatLng(3*i, 3*i)); // Tomar coordenadas de la base

            // Título de cada marcador (Tomar nombre de cada sitio de la base)
            mMap.addMarker(new MarkerOptions().position(sitios.get(i)).title(String.valueOf(i+1)));

            //mMap.moveCamera(CameraUpdateFactory.newLatLng(sitios.get(i)));
            miUbic();
        }*/
    }

    /*List<LatLng> points=new ArrayList<LatLng>();
for (int i = 0 ; i < pointX.length - 1; i++){
    points.add(new LatLng(pointX[i],pointY[i]));
};*/

    /**
     * Metodo que consigue la ubicacion actual de el usuario mediante varios parametros
     */
    private void miUbic() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
          /*  ActivityCompat.requestPermissions(
                    this,
                    new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                    999
            );
            return;*/
        }

        LocationManager locationManager= (LocationManager) getSystemService(Context.LOCATION_SERVICE);;
        Location location= null;
        try {

            // getting GPS status
            boolean isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            boolean isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                int i;
                // location service disabled
            } else {
                // if GPS Enabled get lat/long using GPS Services

                if (isGPSEnabled && !isNetworkEnabled ) {
                    LocationListener locationListener1 = new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                        }

                        @Override
                        public void onStatusChanged(String s, int i, Bundle bundle) {
                        }

                        @Override
                        public void onProviderEnabled(String s) {
                        }

                        @Override
                        public void onProviderDisabled(String s) {
                        }
                    };
                    locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener1, null);

                    Log.d("GPS Enabled", "GPS Enabled");

                    if (locationManager != null) {
                        while (location==null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                    }
                }

                // First get location from Network Provider
                if (isNetworkEnabled) {
                    if (location == null) {
                        LocationListener locationListener2 = new LocationListener() {
                            @Override
                            public void onLocationChanged(Location location) {
                            }

                            @Override
                            public void onStatusChanged(String s, int i, Bundle bundle) {
                            }

                            @Override
                            public void onProviderEnabled(String s) {
                            }

                            @Override
                            public void onProviderDisabled(String s) {
                            }
                        };
                        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER,locationListener2, null);

                        Log.d("Network", "Network");

                        if (locationManager != null) {
                            while (location==null){
                                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            }
                        }
                    }

                }
            }
        } catch (Exception e) {
            // e.printStackTrace();
            Log.e("Error : Location",
                    "Impossible to connect to LocationManager", e);
        }

        actualizarUbic(location);

    }

    private void actualizarUbic(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lon = location.getLongitude();
            agregarMarcador(lat, lon);
        }
    }

    private void agregarMarcador(double la, double lo ) {
        LatLng coord = new LatLng(la, lo);
        CameraUpdate miUbic = CameraUpdateFactory.newLatLngZoom(coord, 20f);
        if (marker != null) marker.remove();
        marker = mMap.addMarker(new MarkerOptions().position(coord).title("Primera cueva")
                /*.icon(BitmapDescriptorFactory.fromResource(R.mipmap.cueva8bit)).draggable(true)*/);

        mMap.animateCamera(miUbic);
    }


}

    // https://developers.google.com/maps/documentation/android-api/location?hl=es-419
    // RECORDAR SOLICITAR AL USUARIO LOS PERMISOS DE UBICACIÓN
    //https://stackoverflow.com/questions/30253123/blue-dot-and-circle-is-not-shown-on-mylocation-using-android-fused-location-api/30255219#30255219
