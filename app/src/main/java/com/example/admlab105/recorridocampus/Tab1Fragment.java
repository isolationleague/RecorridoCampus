package com.example.admlab105.recorridocampus;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.MapQuestRoadManager;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.bonuspack.routing.RoadNode;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayControlView;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.OverlayManager;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import java.util.LinkedList;
import java.util.List;

import static android.content.Context.VIBRATOR_SERVICE;


public class Tab1Fragment extends Fragment {
    private MapView map;
    private MyLocationNewOverlay mMyLocationOverlay;

    double lat = 0.0, lon = 0.0;
    private Marker marker;
    private LinkedList<Marker> sitios;
    private BaseSitiosHelper db;

    private static final int PERMISSIONS_REQUEST_LOCATION = 1;

    ArrayList<OverlayItem> marcadores;
    ArrayList<GeoPoint> marcadores2;
    ArrayList<Marker> marcadores3;

    ArrayList<Double> radios;
    boolean dentroDeRadio;

    TextView nombreSitioCercano;

    Handler handler;
    Handler cercania;

    GeoPoint user;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        almacenar();// Petición de permiso para external storage

        db = BaseSitiosHelper.getInstance(this.getContext().getApplicationContext());
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);
        Context ctx = getActivity();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        map = view.findViewById(R.id.map);


        map.getTileProvider().setTileSource(TileSourceFactory.MAPNIK);

        map.setBuiltInZoomControls(false);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(17);
        GeoPoint startPoint = new GeoPoint(9.9370, -84.0510);
        mapController.setCenter(startPoint);


        map.setMultiTouchControls(true);

        ImageButton btnCampus = view.findViewById(R.id.btnCampus);
        ImageButton btnUser = view.findViewById(R.id.btnUser);
        ImageButton btnCerca = view.findViewById(R.id.btnCerca);
        nombreSitioCercano = view.findViewById(R.id.nombreSitioText);


        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miUbic();
            }
        });

        btnCampus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverCampus();
            }
        });

        btnCerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculoCercania();
            }
        });

        marcadores = new ArrayList<OverlayItem>();
        marcadores2 = new ArrayList<GeoPoint>();
        marcadores3 = new ArrayList<Marker>();
        radios = new ArrayList<Double>();
        dentroDeRadio= false;

        sitios = new LinkedList<Marker>();
        Cursor c = db.obtenerLugares();


        if (c.moveToFirst()) {
            do {

                OverlayItem item = new OverlayItem(c.getString(0), "", new GeoPoint(c.getDouble(1), c.getDouble(2)));
                radios.add(c.getDouble(3));
                Drawable newMarker = this.getResources().getDrawable(R.drawable.sitio);
                item.setMarker(newMarker);
                marcadores.add(item);

             } while (c.moveToNext());

        }


        ItemizedIconOverlay.OnItemGestureListener<OverlayItem> gestureListener = new OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(final int index, final OverlayItem item) {

                Marker mark = new Marker(map);
                mark.setTitle(item.getTitle());
                String distancia = "Está a " + (int) user.distanceToAsDouble(item.getPoint()) + " mts. de distancia";
                mark.setSnippet(distancia);
                GeoPoint geo = new GeoPoint(item.getPoint().getLatitude(), item.getPoint().getLongitude());
                mark.setPosition(geo);
                mark.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

                mark.showInfoWindow();
                map.invalidate();
                return true;
            }

            @Override
            public boolean onItemLongPress(final int index, final OverlayItem item) {
                if (estaDentroDeRadio(item)) {
                    iniciarActivity(item);
                } else {
                    String mensaje = " Se encuentra muy lejos de este punto, acérquese más";
                    Toast.makeText(getActivity(), mensaje, Toast.LENGTH_LONG).show();
                }
                return true;
            }
        };
        ItemizedIconOverlay<OverlayItem> mOverlay = new ItemizedIconOverlay<OverlayItem>(getActivity(), marcadores, gestureListener);


        map.getOverlays().add(mOverlay);
        volverCampus();
        marker = new Marker(map);

        final Handler cercania = new Handler();
        final Runnable actualizador = new Runnable() {
            @Override
            public void run() {
                //System.out.println("El handler se ejecuto");
                cercaniaActiva();
                miUbic();
                cercania.postDelayed(this, 1000);
            }
        };
        actualizador.run();

        return  view;

    }

    public void calculoCercania(){
       int cercania=(int)user.distanceToAsDouble(marcadores.get(0).getPoint());
       String nombre=marcadores.get(0).getTitle();
        for(int i=1;i<marcadores.size();++i){
            int cercania2=(int)user.distanceToAsDouble(marcadores.get(i).getPoint());
            if(cercania2<cercania){
            cercania=cercania2;
            nombre=marcadores.get(i).getTitle();
            }
        }
        Toast.makeText(getActivity(), "El sitio más cercano es "+nombre+" que esta a "+cercania+" mts. de usted",Toast.LENGTH_LONG).show();
    }

    public void cercaniaActiva(){
            if (marcadores.size() != 0) {
                OverlayItem aux = marcadores.get(0);

                System.out.println("Me estoy ejecutando");
                if (user != null) {
                    int cercania = (int) user.distanceToAsDouble(marcadores.get(0).getPoint());
                    String nombre = marcadores.get(0).getTitle();
                    for (int i = 1; i < marcadores.size(); ++i) {
                        int cercania2 = (int) user.distanceToAsDouble(marcadores.get(i).getPoint());
                        if (cercania2 < cercania) {
                            cercania = cercania2;
                            nombre = marcadores.get(i).getTitle();
                            aux = marcadores.get(i);
                        }
                    }
                    if (estaDentroDeRadio(aux) && !dentroDeRadio) {
                        activarVibracion();
                        dentroDeRadio = true;
                    }
                    if (!estaDentroDeRadio(aux)) {
                        dentroDeRadio = false;
                    }

                    nombreSitioCercano.setText(nombre);
                }
            }
        }



    public boolean estaDentroDeRadio(OverlayItem item){
        int distancia = (int) user.distanceToAsDouble(item.getPoint());

        if (distancia > radios.get(marcadores.indexOf(item))) {
            return false;
        }
        return true;
    }

    public void iniciarActivity(final OverlayItem item){
        Bundle arg = new Bundle();
        arg.putString("etiq", item.getTitle());
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(arg);
        //FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment, "tag1");
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void onClick(Marker mark){
        Toast.makeText(getActivity(),mark.getTitle() ,
                Toast.LENGTH_LONG).show();
    }

    private void volverCampus(){
        IMapController mapController = map.getController();
        mapController.setZoom(17);
        GeoPoint startPoint = new GeoPoint(9.9370,-84.0510);
        mapController.setCenter(startPoint);
    }

    private void almacenar() {
        int check = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (check == PackageManager.PERMISSION_GRANTED) {
            //Do something
        } else {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1024);
        }
    }

    private void miUbic() {

        try {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

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
                            //fragment_updater();
                            //cercaniaActiva();

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
                                try {
                                    actualizarUbic(location);
                                }catch (IllegalStateException ise){}
                                //fragment_updater();
                                //cercaniaActiva();
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
        } catch (NullPointerException npe) {}


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

    private void actualizarUbic(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lon = location.getLongitude();
            agregarMarcador(lat, lon);
        }
    }

    private void agregarMarcador(double la, double lo) {
        lat=la;
        lon=lo;
        //CameraUpdate miUbic = CameraUpdateFactory.newLatLngZoom(coord, 16f);
        if (marker != null) {
            marker.remove(map);
        }
        user= new GeoPoint(lat, lon);
        marker.setTitle("Usuario");
        marker.setPosition(user);
        marker.setIcon(getResources().getDrawable(R.drawable.ubicacion));
        map.getOverlays().add(marker);
        map.invalidate();


    }



    @Override
    public void onResume(){
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onPause(){
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }

    public void addMarker(GeoPoint point){
        org.osmdroid.views.overlay.Marker marker = new org.osmdroid.views.overlay.Marker(map);
        marker.setPosition(point);
        //marker.setAnchor(Marker.ANCHOR_BOTTOM, Marker.ANCHOR_CENTER);
        marker.setTitle("UCR");
        //marker.setIcon(getResources().getDrawable(R.drawable.cat));
        IMapController mapController = map.getController();
        mapController.setCenter(point);
        //map.getOverlays().clear();
        map.getOverlays().add(marker);
        map.invalidate();

    }

    public void activarVibracion() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) this.getContext().getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            ((Vibrator) this.getContext().getSystemService(VIBRATOR_SERVICE)).vibrate(1000);
        }
    }
}

// https://github.com/MKergall/osmbonuspack/wiki/features

// mostrar cuadros de texto
//https://help.openstreetmap.org/questions/61347/osmdroid-how-do-i-show-and-hide-markers-description-on-click
//https://stackoverflow.com/questions/23108709/show-marker-details-with-image-onclick-marker-openstreetmap?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
