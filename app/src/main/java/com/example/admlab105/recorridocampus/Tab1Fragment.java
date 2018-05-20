package com.example.admlab105.recorridocampus;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.service.carrier.CarrierMessagingService;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";
    private Button btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment,container,false);
        btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 1",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}*/


public class Tab1Fragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    int puntos = 10;

    double lat = 0.0, lon = 0.0;
    private Marker marker;
    private LinkedList<Marker> sitios;
    private BaseSitiosHelper db;

    private static final int PERMISSIONS_REQUEST_LOCATION = 1;
    boolean dobleClick = false;
    Marker ultimoTocado;

    public Tab1Fragment() {

    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapaframe);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        db = new BaseSitiosHelper(this.getContext());
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);
        ImageButton btnCoor =  view.findViewById(R.id.btnCoor);
        ImageButton btnCampus= view.findViewById(R.id.btnCampus);
        ImageButton btnTest = view.findViewById(R.id.btnTest);
        ImageButton btnTest2 = view.findViewById(R.id.btnTest2);



        btnCoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miUbic();
            }
        });
        btnCampus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { volverCampus();
            }
        });
        /*btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InfoFragment fragment = new InfoFragment();
                //FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, fragment, "tag1");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btnTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InfoFragment fragment = new InfoFragment();
                //FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, fragment, "tag1");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });*/


        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        //mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(this);

        colocaSitios();
        //mMap.getUiSettings().setZoomGesturesEnabled(false);


        List<LatLng> sitios = new ArrayList<LatLng>();
        volverCampus();

       /* for (int i = 0; i < puntos; i++) {
            //sitios.set(i, new LatLng(-34, 151)); // Tomar coordenadas de la base
            sitios.add(new LatLng(3 * i, 3 * i)); // Tomar coordenadas de la base

            // Título de cada marcador (Tomar nombre de cada sitio de la base)
            mMap.addMarker(new MarkerOptions().position(sitios.get(i)).title(String.valueOf(i + 1)));

            //mMap.moveCamera(CameraUpdateFactory.newLatLng(sitios.get(i)));
        }*/

    }
    @Override
    public void onStart(){
        super.onStart();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
                miUbic();
            }
        },10);

    }


    public void onLocationChanged(Location location){
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
    }




    private void miUbic() {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_LOCATION);
        }


        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Location location = null;
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

                if (isGPSEnabled && !isNetworkEnabled) {
                    android.location.LocationListener locationListener1 = new android.location.LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            actualizarUbic(location);
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
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1f,locationListener1 );

                    Log.d("GPS Enabled", "GPS Enabled");

                    if (locationManager != null) {
                        while (location == null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                    }
                }

                // First get location from Network Provider
                if (isNetworkEnabled) {
                    if (location == null) {
                        android.location.LocationListener locationListener2 = new android.location.LocationListener() {
                            @Override
                            public void onLocationChanged(Location location) {
                                actualizarUbic(location);
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
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,100,1f,locationListener2 );

                        Log.d("Network", "Network");

                        if (locationManager != null) {
                            while (location == null) {
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


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    private void volverCampus(){
        LatLng coord = new LatLng(9.9370, -84.0506);
        float zoomLevel = 15.8f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coord, zoomLevel));
    }

    private void actualizarUbic(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lon = location.getLongitude();
            agregarMarcador(lat, lon);
        }
    }

    private void agregarMarcador(double la, double lo) {
        LatLng coord = new LatLng(la, lo);
        //CameraUpdate miUbic = CameraUpdateFactory.newLatLngZoom(coord, 16f);
        if (marker != null) marker.remove();
        marker = mMap.addMarker(new MarkerOptions().position(coord).title("Usuario").icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        /*.icon(BitmapDescriptorFactory.fromResource(R.mipmap.cueva8bit)).draggable(true)*/;

        //mMap.animateCamera(miUbic);
    }

    public BitmapDescriptor getMarkerIcon(String color) {
        float[] hsv = new float[3];
        Color.colorToHSV(Color.parseColor(color), hsv);
        return BitmapDescriptorFactory.defaultMarker(hsv[0]);
    }


    private void colocaSitios(){
        sitios = new LinkedList<Marker>();
        Cursor c=db.obtenerLugares();
        if (c.moveToFirst()) {
            do {
                LatLng coord = new LatLng(c.getDouble(1), c.getDouble(2));
                sitios.add(mMap.addMarker(new MarkerOptions().position(coord).title(c.getString(0))));
            } while(c.moveToNext());
        }
    }
//METODO para abrir con double click en el marker los fragment de info
    /*@Override
    public boolean onMarkerClick(Marker marker) {
        if (dobleClick && marker.equals(ultimoTocado)) {
            InfoFragment fragment = new InfoFragment();
            //FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, fragment, "tag1");
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        } else {
            ultimoTocado = marker;
            this.dobleClick = true;
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    dobleClick = false;
                }
            }, 2000);
            return false;
        }
    }*/
//METODO para abrir con click en el label del marker los fragment de info
    @Override
    public void onInfoWindowClick(Marker marker) {
        Bundle arg = new Bundle();
        arg.putString("etiq", marker.getTitle());
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(arg);
        //FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment, "tag1");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

// https://developers.google.com/maps/documentation/android-api/location?hl=es-419
// RECORDAR SOLICITAR AL USUARIO LOS PERMISOS DE UBICACIÓN
//https://stackoverflow.com/questions/30253123/blue-dot-and-circle-is-not-shown-on-mylocation-using-android-fused-location-api/30255219#30255219

//https://www.sitepoint.com/requesting-runtime-permissions-in-android-m-and-n/ (permisos)




