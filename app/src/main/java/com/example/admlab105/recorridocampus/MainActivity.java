package com.example.admlab105.recorridocampus;

import android.app.ActionBar;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    public ViewPager mViewPager;
    private boolean isUserClickedBackButton = false;
    public int marcador=0;

    /**
     * Creacion de la vista principal de la aplicacion
     * Define los fragments para el despliegue del mapa, y el de la informacion del recorrido
     *  y la barra pprincipal de la aplicacion
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
       // getSupportActionBar().setDisplayShowHomeEnabled(false);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                onBackPressed();
            }
        });
    }

    /**
     * Inicializaci'on y despliegue de la vista principal
     */
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    /**
     * Creacion del menu de opciones
     * @param menu: menu de opciones
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Creacion de las opciones del menu(de acuerdo al xml) y definicion de los eventos para cuando una opcion
     * es seleccionada
     * @param item: opcion del menu seleccionada
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.tuto) {
            Toast.makeText(this, "Tutorial", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.intro) {
            //Toast.makeText(this, "Introducción", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, Intro_activity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.credits) {
            Toast.makeText(this, "Créditos", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.preferencias) {
            /*MenuPreferencias menu = new MenuPreferencias();
            android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, menu, "tag1");
            transaction.addToBackStack(null);
            transaction.commit();*/
            //Fragment currentFragment = this.getSupportFragmentManager().findFragmentById(R.id.frameLayout);
            Intent i = new Intent(MainActivity.this, MenuPreferencias.class);
            startActivity(i);
            Toast.makeText(this, "Preferencias", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Agrega las vistas apropiadas para el despliegue de las opciones del tab en el menu principal
     * (Mapa y recorrido)
     * @param viewPager: vista para el despliegue de los fragments mapa o recorrido
     */
    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "Mapa");
        adapter.addFragment(new Tab2Fragment(), "Mi recorrido");
        viewPager.setAdapter(adapter);
    }

    /**
     * Manejo del evento de presionar atr'as en el telefono
     * Retorna a vistas anteriores dentro de la plicacion al presionar
     * al presionar atras dos veces desde la vista principal sale de la aplicaci'on
     */
    @Override
    public void onBackPressed() {
        Fragment currentFragment = this.getSupportFragmentManager().findFragmentById(R.id.frameLayout);
        if(!(currentFragment instanceof InfoFragment)) {
            // do something with f
            /*((Tab1Fragment) currentFragment).doSomething();*/
            if (!isUserClickedBackButton) {
                final Toast toast =Toast.makeText(this,"Presione de nuevo para salir", Toast.LENGTH_LONG);
                toast.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 500);
                isUserClickedBackButton = true;
            } else {
                super.onBackPressed();
            }

            new CountDownTimer(3000,1000){

                @Override
                public void onTick(long msUntilFinish) {}

                @Override
                public void onFinish(){
                    isUserClickedBackButton = false;
                }
            }.start();
        } else {
            super.onBackPressed();
        }
    }
}
