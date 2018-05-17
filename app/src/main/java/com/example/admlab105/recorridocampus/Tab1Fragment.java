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
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.OverlayManager;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;



import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;


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


public class Tab1Fragment extends Fragment {
    private MapView map;
    private MyLocationNewOverlay mMyLocationOverlay;

    ArrayList<OverlayItem> anotherOverlayItemArray;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab1_fragment, container, false);

        anotherOverlayItemArray = new ArrayList<OverlayItem>();
        anotherOverlayItemArray.add(new OverlayItem("cicle", "prueba", new GeoPoint(9.9400,-84.0510)));
        anotherOverlayItemArray.add(new OverlayItem("cicle", "prueba", new GeoPoint(9.9395,-84.0510)));
        anotherOverlayItemArray.add(new OverlayItem("cicle", "prueba", new GeoPoint(9.9397,-84.0515)));
        anotherOverlayItemArray.add(new OverlayItem("cicle", "prueba", new GeoPoint(9.9397,-84.0505)));


        Context ctx = getActivity();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        map = new MapView(getActivity());
        //map = (MapView) view.findViewById(R.id.map);

        map.getTileProvider().setTileSource(TileSourceFactory.MAPNIK);

        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(19);
        GeoPoint startPoint = new GeoPoint(9.9370,-84.0510);
        mapController.setCenter(startPoint);

        //enable multitouch
        map.setMultiTouchControls(true);
        //GpsMyLocationProvider can be replaced by your own class. It provides the position information through GPS or Cell towers.
        //GpsMyLocationProvider imlp = new GpsMyLocationProvider(this.getBaseContext());
        //minimum distance for update
        //imlp.setLocationUpdateMinDistance(1000);
        //minimum time for update
        //imlp.setLocationUpdateMinTime(60000);
        //mMyLocationOverlay = new MyLocationNewOverlay(this.getBaseContext(),imlp , map, resProxyImp);

        //mMyLocationOverlay.setUseSafeCanvas(false);
        //mMyLocationOverlay.setDrawAccuracyEnabled(true);

        //map.getOverlays().add(mMyLocationOverlay);




        return /*view*//*new MapView(getActivity())*/map;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void anadirMarcador(View v){
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        System.out.println(buttonText);

        GeoPoint pointB = new GeoPoint(9.9370,-84.0510);
        addMarker(pointB, buttonText);



        //ItemizedIconOverlay markersOverlay = new ItemizedIconOverlay(new LinkedList(), myMarker, null, resProxyImp);
        //map.getOverlays().add(markersOverlay);
        //Add markers
        //OverlayItem ovm = new OverlayItem("titolo", "descrizione", new GeoPoint(s.LatitudeE6(), s.LongitudeE6()));
        //ovm.setMarker(myMarker);
        //markersOverlay.addItem(ovm);



    }

    public void anadirMarcador2(View v){
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        System.out.println(buttonText);

        GeoPoint pointA = new GeoPoint(9.9380, -84.0510);
        addCat(pointA, buttonText);

    }

    public void anadirCiclo(View v){
        Button b = (Button)v;
        addCicle();
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

    public void addCat(GeoPoint point, String nombre){
        org.osmdroid.views.overlay.Marker marker = new org.osmdroid.views.overlay.Marker(map);
        marker.setPosition(point);
        //marker.setAnchor(Marker.ANCHOR_BOTTOM, Marker.ANCHOR_CENTER);
        marker.setTitle(nombre);
        marker.setIcon(getResources().getDrawable(R.drawable.cat));
        IMapController mapController = map.getController();
        mapController.setCenter(point);
        // map.getOverlays().clear();
        map.getOverlays().add(marker);
        map.invalidate();

    }

    public void addMarker(GeoPoint point, String nombre){
        org.osmdroid.views.overlay.Marker marker = new org.osmdroid.views.overlay.Marker(map);
        marker.setPosition(point);
        //marker.setAnchor(Marker.ANCHOR_BOTTOM, Marker.ANCHOR_CENTER);
        marker.setTitle(nombre);
        //marker.setIcon(getResources().getDrawable(R.drawable.cat));
        IMapController mapController = map.getController();
        mapController.setCenter(point);
        //map.getOverlays().clear();
        map.getOverlays().add(marker);
        map.invalidate();

    }

    public void addCicle(){
        ItemizedIconOverlay<OverlayItem> anotherItemizedIconOverlay
                = new ItemizedIconOverlay<OverlayItem>(getActivity(), anotherOverlayItemArray, null);
        map.getOverlays().add(anotherItemizedIconOverlay);
    }

}

// https://developers.google.com/maps/documentation/android-api/location?hl=es-419
// RECORDAR SOLICITAR AL USUARIO LOS PERMISOS DE UBICACION
//https://stackoverflow.com/questions/30253123/blue-dot-and-circle-is-not-shown-on-mylocation-using-android-fused-location-api/30255219#30255219

//https://www.sitepoint.com/requesting-runtime-permissions-in-android-m-and-n/ (permisos)

//https://stackoverflow.com/questions/14897143/integrating-osmdroid-with-fragments
//http://devblog.blackberry.com/2013/03/android-map-blackberry-10/




