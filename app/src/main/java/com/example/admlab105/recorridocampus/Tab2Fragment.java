package com.example.admlab105.recorridocampus;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";
    private Button btn;
    private ArrayList<String> sitios;
    private BaseSitiosHelper db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment,container,false);

        db = BaseSitiosHelper.getInstance(this.getContext().getApplicationContext());

        sitios = new ArrayList<>();
        Cursor c = db.obtenerLugares();


        if (c.moveToFirst()) {
            do {
                sitios.add(c.getString(0));
            } while (c.moveToNext());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, sitios);
        ListView l = (ListView) view.findViewById(R.id.lista);
        l.setAdapter(arrayAdapter);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle arg = new Bundle();
                arg.putString("etiq", sitios.get(i));
                InfoFragment fragment = new InfoFragment();
                fragment.setArguments(arg);
                //FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.seVa, fragment, "tag1");
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        /*btn= (Button) view.findViewById(R.id.btn2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 2",Toast.LENGTH_SHORT).show();
            }
        });*/
        return view;
    }
}
