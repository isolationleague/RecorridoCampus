/*package com.example.admlab105.recorridocampus;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Intro_activity extends AppCompatActivity {
    Button bclose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_activity);
        bclose= (Button) findViewById(R.id.buttonClose);
        bclose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intro_activity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

}*/

package com.example.admlab105.recorridocampus;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;


public class Intro_activity extends Activity {

    MediaPlayer audioPlayer;
    Button bclose;
    TextView texto;
    ImageButton playButton;
    SeekBar seekBar;
    Handler handler;
    Runnable runnable;
    static final int PERMISSIONS_REQUEST_LOCATION=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_activity);
        bclose= (Button) findViewById(R.id.buttonClose);
        bclose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intro_activity.this, MainActivity.class);
                startActivity(i);
            }
        });
        playButton = (ImageButton) findViewById(R.id.playButton);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setClickable(false);
        seekBar.setFocusable(false);
        seekBar.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        handler = new Handler();
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(v);
            }
        });
        String txt1 = "Un paseo por el campus ";
        String txt2 = "es una aplicación para Android, diseñada como una audio guía para ";
        String txt3 = "descubrir el patrimonio arquitectónico y el arte público de la Ciudad Universitaria Rodrigo Facio Brenes";
        String txt4 = ", mientras la recorre. Por medio de audios, textos y fotografías se podrá conocer más sobre los bienes culturales que resguarda la Universidad de Costa Rica.";
        String txt5 ="La aplicación fue desarrollada en su totalidad por estudiantes del curso CI-1430 Ingeniería de Software 2 de la Escuela de Ciencias de la Computación e Informática, con la colaboración de estudiantes con horas asistente, de diversas áreas, del museo+UCR. Así, el museo+UCR se compromete con la divulgación de las colecciones de la UCR y con el apoyo a la docencia.";
        texto = (TextView) findViewById(R.id.textView2);
        texto.setText(Html.fromHtml("<p>" + "<i>" + txt1 + "</i>" + txt2 + "<i>" + txt3 + "</i>" + txt4 + "</p>" + "<p>" +  txt5  + "</p>" + "</small>"));
        almacenar();
    }

    /**
     * Configuracion del boton play del audio player, reproduccion y pausa de los archivos de audio
     * Cambio de imagen del boton al pulsarlo.
     * @param view
     */
    public void play(View view) {
        if (audioPlayer != null) {
            if (audioPlayer.isPlaying()) {
                audioPlayer.pause();
                playButton.setImageResource(R.drawable.sharp_play_arrow_black_18dp);
            } else {
                audioPlayer.start();
                playing();
                playButton.setImageResource(R.drawable.sharp_pause_black_18dp);
            }
        } else {
            initializePlayer();
            audioPlayer.start();
            playing();
            playButton.setImageResource(R.drawable.sharp_pause_black_18dp);
        }
    }


    /**
     * Detencion del evento de monitoreo de reproduccion del audio al terminar de reproducirse.
     */
    private void stopPlayer() {
        if (audioPlayer != null) {
            audioPlayer.release();
            audioPlayer = null;
            playButton.setImageResource(R.drawable.sharp_play_arrow_black_18dp);
        }
        handler.removeCallbacks(runnable);
    }

    /**
     * Detiene el audio player al terminar ejecucion
     */
    @Override
    public void onStop() {
        super.onStop();
        stopPlayer();
    }

    /**
     * Inicializacion del audio player, asignacion del audio correspondiente al sitio visitado
     */
    public void initializePlayer() {
        if (audioPlayer != null){
            audioPlayer.release();
        }
        audioPlayer = MediaPlayer.create(getApplicationContext(), R.raw.introduccion);
        //audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        seekBar.setMax(audioPlayer.getDuration());
        audioPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopPlayer();
            }
        });
    }

    /**
     * Monitoreo del audio en reproduccion de acuerdo a su tiempo de duracion
     */
    public void playing() {
        if (audioPlayer != null && audioPlayer.isPlaying() == true)
            handler.postDelayed(updateTime, 100);

    }

    /**
     * Monitoreo continuo de reproduccion cada 100ms para la barra de avance del audio reproducido
     */
    private Runnable updateTime = new Runnable() {
        @Override
        public void run() {
            if (audioPlayer != null && audioPlayer.isPlaying() == true) {
                seekBar.setProgress(audioPlayer.getCurrentPosition());
                handler.postDelayed(this, 100);
            }
        }
    };

    private void almacenar() {
        int check = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (check == PackageManager.PERMISSION_GRANTED) {
            //Do something
        } else {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1024);
            }
        }
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

}
