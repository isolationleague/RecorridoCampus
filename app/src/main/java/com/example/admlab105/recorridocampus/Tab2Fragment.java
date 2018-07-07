package com.example.admlab105.recorridocampus;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.OverlayItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";
    private Button btn;
    private ArrayList<String> sitios;
    private ArrayList<Integer> visitados;
    private BaseSitiosHelper db;
    String line;
    SeekBar seekBar;
    ImageView img;
    TextView txtview2;
    TextView txtview;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment,container,false);

        db = BaseSitiosHelper.getInstance(this.getContext().getApplicationContext());

        sitios = new ArrayList<>();
        visitados = new ArrayList<>();

        Cursor c = db.obtenerLugares();

        if (c.moveToFirst()) {
            do {
                sitios.add(c.getString(0));
                visitados.add(new Integer(c.getInt(4)));
            } while (c.moveToNext());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, sitios);
        ListView l = (ListView) view.findViewById(R.id.lista);
        View header = getLayoutInflater().inflate(R.layout.tab2_fragment_content, null);
        header.setOnClickListener(null);
        l.addHeaderView(header);
        l.setAdapter(arrayAdapter);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle arg = new Bundle();
                if(visitados.get(i).equals(new Integer(1))){
                    arg.putString("etiq", sitios.get(i));
                    InfoFragment fragment = new InfoFragment();
                    fragment.setArguments(arg);
                    //FragmentManager fm = getFragmentManager();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.seVa, fragment, "tag1");
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else {
                    String text = "Sitio no visitado ";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(getContext(), text, duration);
                    toast.show();
                }
            }
        });
        txtview = view.findViewById(R.id.textView4);
        txtview2 = view.findViewById(R.id.textView3);

        seekBar = view.findViewById(R.id.seekBar2);
        seekBar.setClickable(false);
        seekBar.setFocusable(false);
        seekBar.setMax(c.getCount());

        seekBar.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        img = view.findViewById(R.id.pic);
        loadUserInfo();
        /*btn= (Button) view.findViewById(R.id.btn2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 2",Toast.LENGTH_SHORT).show();
            }
        });*/
       //getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        return view;
    }

    public void loadUserInfo(){

        int desbloqueados = 0;
        for (int i = 0; i < visitados.size(); i++) {
            if(visitados.get(i).equals(new Integer(1))) desbloqueados++;
        }
        String str = "Te faltan " + (visitados.size() - desbloqueados) + " sitios por visitar";
        txtview.setText(str);
        seekBar.setProgress(desbloqueados);
        String name = ReadFile("usuario");
        if( !name.equals("") ){
            txtview2.setText(name);
        } else txtview2.setText("Usuario");
        try {
            File f = new File(getContext().getFilesDir() + File.separator + "imagen", "imagen.jpg");
            if (f.exists()) {
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                img.setImageBitmap(b);
            } else img.setImageResource(R.drawable.icon_app);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String ReadFile(String location) {
        File dir = new File(getContext().getFilesDir() + File.separator + location+"/"+location+".txt");
        if (!dir.exists()) {
            return "";
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(new File(getContext().getFilesDir() + File.separator + location+"/"+location+".txt"));
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                fileInputStream.close();
                line = stringBuilder.toString();
                bufferedReader.close();
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            }
            return line;
        }
    }

   @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint( isVisibleToUser );
        if (getFragmentManager() != null) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        loadUserInfo();
    }
}
