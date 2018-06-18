package com.example.admlab105.recorridocampus;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InfoFragment extends Fragment {

    //private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private String etiqueta;
    private BaseSitiosHelper db;
    private String texto;

    /**
     * Crea la vista principal para el despliegue de informacion de los sitios
     *
     * @param savedInstanceState: para paso de informacion entre fragmnets.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        etiqueta = this.getArguments().getString("etiq");
        db = BaseSitiosHelper.getInstance(this.getContext());
        setRetainInstance(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Despliegue de la vista principal para el despliegue de informacion de los sitios
     * Dentro de esta vista se despliega el tab layout para informacion general, fotos
     * y enlaces externos de los sitios.
     * @param savedInstanceState: para paso de informacion entre fragmnets.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment, container, false);
        //mSectionsPageAdapter = new SectionsPageAdapter(getChildFragmentManager());
        mViewPager = (ViewPager) view.findViewById(R.id.info_container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.info_tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
        TextView tv = (TextView)view.findViewById(R.id.etqSitio);
        tv.setText(etiqueta);

        //ImageButton backButton = view.findViewById(R.id.backButton);
       /* backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });*/
        /*
        TextView tv2 = (TextView)view.findViewById(R.id.cX);
        tv2.setText(String.valueOf(db.obtengaX(etiqueta)));
        TextView tv3 = (TextView)view.findViewById(R.id.cY);
        tv3.setText(String.valueOf(db.obtengaY(etiqueta)));*/

        return view;
    }

    /*public void onDetach(){
        super.onDetach();
        getActivity().getSupportFragmentManager().beginTransaction().remove(getParentFragment()).commit();
    }*/

    /**
     * Se agrgan los fragments correspondientes a las vistas de informacion general, imagenes y
     * enlaces externos de los sitios
     * @param viewPager vista para el despliegue de los fragments
     */
    private void setupViewPager(ViewPager viewPager) {
        Bundle arg = new Bundle();
        arg.putString("etiq", etiqueta);
        SectionsPageAdapter adapter = new SectionsPageAdapter(getChildFragmentManager());
        InfoTextsFragment fText= new InfoTextsFragment();
        fText.setArguments(arg);
        //
        InfoPicturesFragment fPictures = new InfoPicturesFragment();
        fPictures.setArguments(arg); // nuevo infoPictures que se le envia el parametro de la etiqueta
        //
        adapter.addFragment(fText, "Textos");
        //adapter.addFragment(new InfoPicturesFragment(), "Fotos");
        adapter.addFragment(fPictures, "Fotos");
        adapter.addFragment(new InfoLinksFragment(), "Enlaces");
        viewPager.setAdapter(adapter);
    }

    /*private void setTexto(){
        try
        {
            InputStreamReader reader= new InputStreamReader(getContext().openFileInput("res\\raw\\textos\\"+etiqueta+".txt"));
            BufferedReader br= new BufferedReader(reader);
            String texto = br.readLine();
            while(texto!=null)
            {
                texto+=br.readLine();
            }
        }
        catch (Exception ex)
        {
            texto = "No hay texto definido para el siguiente sitio";
        }
    }*/
}
