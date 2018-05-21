package com.example.admlab105.recorridocampus;

import android.Manifest;
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
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
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




public class Tab1Fragment extends Fragment{
    private MapView map;
    private MyLocationNewOverlay mMyLocationOverlay;

    double lat = 0.0, lon = 0.0;
    private Marker marker;
    private LinkedList<Marker> sitios;
    private BaseSitiosHelper db;

    private static final int PERMISSIONS_REQUEST_LOCATION = 1;

    ArrayList<OverlayItem> marcadores;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        db = new BaseSitiosHelper(this.getContext());
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);
        Context ctx = getActivity();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        //map = new MapView(getActivity());
        map =  view.findViewById(R.id.map);

        map.getTileProvider().setTileSource(TileSourceFactory.MAPNIK);

        map.setBuiltInZoomControls(false);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(17);
        GeoPoint startPoint = new GeoPoint(9.9370,-84.0510);
        mapController.setCenter(startPoint);


        map.setMultiTouchControls(true);


        Button btnUCR =  view.findViewById(R.id.btnUcr);
        Button btnCat = view.findViewById(R.id.btnCat);

        ImageButton btnCampus=view.findViewById(R.id.btnCampus);
        ImageButton btnUser=view.findViewById(R.id.btnUser);

        btnUCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirMarcador();
            }
        });
        btnCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirMarcador2();
            }
        });

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

        marcadores = new ArrayList<OverlayItem>();

        ArrayList<GeoPoint> marcadores2 = new ArrayList<GeoPoint>();

        sitios = new LinkedList<Marker>();
        Cursor c=db.obtenerLugares();

        Road road;

        if (c.moveToFirst()) {
            do {
                marcadores.add(new OverlayItem(c.getString(0), "", new GeoPoint(c.getDouble(1),c.getDouble(2))));
                marcadores2.add(new GeoPoint(c.getDouble(1),c.getDouble(2)));

            } while(c.moveToNext());

            try {
                RoadManager roadManager = new OSRMRoadManager(getContext());
                road = roadManager.getRoad(marcadores2);
                Polyline roadOverlay = RoadManager.buildRoadOverlay(road);
                map.getOverlays().add(roadOverlay);
                map.invalidate();


                Drawable nodeIcon = getResources().getDrawable(R.drawable.cat);
                for (int i=0; i<road.mNodes.size(); i++){
                    RoadNode node = road.mNodes.get(i);
                    Marker nodeMarker = new Marker(map);
                    nodeMarker.setPosition(node.mLocation);
                    nodeMarker.setIcon(nodeIcon);
                    nodeMarker.setTitle("Step "+i);
                    map.getOverlays().add(nodeMarker);

                    nodeMarker.setSnippet(node.mInstructions);
                    nodeMarker.setSubDescription(Road.getLengthDurationText(getContext(), node.mLength, node.mDuration));
                    Drawable icon = getResources().getDrawable(R.drawable.default0);
                    nodeMarker.setImage(icon);

                }


            } catch (Exception e) {}

        }

        ItemizedIconOverlay.OnItemGestureListener<OverlayItem> gestureListener = new OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                //do something
                Toast.makeText(getActivity(), item.getTitle(),Toast.LENGTH_LONG).show();
                return true;
            }
            @Override
            public boolean onItemLongPress(final int index, final OverlayItem item) {
                return false;
            }
        };
        ItemizedIconOverlay<OverlayItem> mOverlay = new ItemizedIconOverlay<OverlayItem>(getActivity(),marcadores,gestureListener);



        map.getOverlays().add(mOverlay);
        marker= new Marker(map);

        RoadManager roadManager = new MapQuestRoadManager("oDJQc4K80LIhYWgAFxit5ktTbWVBoYjy"); // API key en https://developer.mapquest.com/
        roadManager.addRequestOption("routeType=pedestrian");



        //colocaSitios();
        return  view;
                //map;
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


    private void miUbic() {

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
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1f,locationListener1 );;

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
        GeoPoint user= new GeoPoint(lat, lon);
        marker.setTitle("Usuario");
        marker.setPosition(user);
        map.getOverlays().add(marker);

        //mMap.animateCamera(miUbic);
    }




    public void anadirMarcador(){

        GeoPoint pointB = new GeoPoint(9.9370,-84.0510);
        addMarker(pointB);
    }

    public void anadirMarcador2(){
        GeoPoint pointA = new GeoPoint(9.9380, -84.0510);
        addCat(pointA);
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

    public void addCat(GeoPoint point){
        org.osmdroid.views.overlay.Marker marker = new org.osmdroid.views.overlay.Marker(map);
        marker.setPosition(point);
        //marker.setAnchor(Marker.ANCHOR_BOTTOM, Marker.ANCHOR_CENTER);
        marker.setTitle("Cat");
        marker.setIcon(getResources().getDrawable(R.drawable.cat));
        IMapController mapController = map.getController();
        mapController.setCenter(point);
        // map.getOverlays().clear();
        map.getOverlays().add(marker);
        map.invalidate();

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

    /*public void addCicle(){
        ItemizedIconOverlay<OverlayItem> anotherItemizedIconOverlay
                = new ItemizedIconOverlay<OverlayItem>(getActivity(), anotherOverlayItemArray, null);
        map.getOverlays().add(anotherItemizedIconOverlay);
    }*/




}

// https://developers.google.com/maps/documentation/android-api/location?hl=es-419
// RECORDAR SOLICITAR AL USUARIO LOS PERMISOS DE UBICACION
//https://stackoverflow.com/questions/30253123/blue-dot-and-circle-is-not-shown-on-mylocation-using-android-fused-location-api/30255219#30255219

//https://www.sitepoint.com/requesting-runtime-permissions-in-android-m-and-n/ (permisos)

//https://stackoverflow.com/questions/14897143/integrating-osmdroid-with-fragments
//http://devblog.blackberry.com/2013/03/android-map-blackberry-10/

    /*private void colocaSitios(){

        sitios = new LinkedList<Marker>();
        Cursor c=db.obtenerLugares();
        if (c.moveToFirst()) {
            do {
                org.osmdroid.views.overlay.Marker marker = new org.osmdroid.views.overlay.Marker(map);
                lat=c.getDouble(1);
                lon=c.getDouble(2);
                GeoPoint sitio= new GeoPoint(lat,lon);
                marker.setPosition(sitio);
                marker.setTitle(c.getString(0));
                map.getOverlays().add(marker);
                map.invalidate();
                //sitios.add(mMap.addMarker(new MarkerOptions().position(coord).title(c.getString(0))));
            } while(c.moveToNext());
        }
    }*/

    // https://github.com/MKergall/osmbonuspack/wiki/features


