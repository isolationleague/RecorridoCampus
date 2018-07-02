package com.example.admlab105.recorridocampus;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.database.Cursor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class InfoTextsFragment extends Fragment {
    private static final String TAG = "InfoTextsFragment";
    MediaPlayer audioPlayer;
    ImageButton playButton;
    SeekBar seekBar;
    Handler handler;
    Runnable runnable;
    private BaseSitiosHelper db;
    private String etiqueta;

    /**
     * Crea la vista principal para el despliegue de informacion general
     * portada, audios y textos de los sitios
     * Inicializa los elementos de la vista como el audio player, etc
     * @param savedInstanceState: para paso de informacion entre fragmnets.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { View view = inflater.inflate(R.layout.info_texts_fragment,container,false);

        etiqueta = this.getArguments().getString("etiq");
        db = BaseSitiosHelper.getInstance(this.getContext());

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        ArrayList<String>fotos = db.obtenerImagenesDeSitio(etiqueta);
        ImagePagerAdapter adapter = new ImagePagerAdapter(fotos);
        viewPager.setAdapter(adapter);
        playButton = view.findViewById(R.id.playButton);
        seekBar = view.findViewById(R.id.seekBar);
        seekBar.setClickable(false);
        seekBar.setFocusable(false);
        handler = new Handler();
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(v);
            }
        });
        TextView txtview = view.findViewById(R.id.textView);
        //txtview.setMovementMethod(new ScrollingMovementMethod());
        loadTextView(txtview);
        return view;
    }

    /**
     * Lectura de los archivos de texto de la base de datos para su despliegue enu el Textview
     *
     * @param txtView: vista para mostrar la informacion textual de los sitios.
     */
    public void loadTextView (TextView txtView) {
        String texto = "";
        try
        {
            String resc = db.obtengaTexto(etiqueta);
            InputStream ins = getResources().openRawResource(
                    getResources().getIdentifier(/*"edificio_de_la_facultad_de_educacion"*/resc,
                            "raw", getContext().getPackageName()));

            //InputStreamReader reader= new InputStreamReader(ins, "UTF-8"); //"res\\raw\\textos\\"+etiqueta+".txt"
            BufferedReader br= new BufferedReader(new InputStreamReader(ins, "UTF-8"));
            String line = "";
            while((line= br.readLine()) !=null)
            {
                texto+=line;
            }
        }
        catch (Exception ex)
        {
            texto = "No hay texto definido para el siguiente sitio";
        }
        txtView.setText(Html.fromHtml(texto));
        /*InputStream inputStream = getResources().openRawResource(R.raw.lorem_ipsum);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        txtView.setText(byteArrayOutputStream.toString());*/
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

    @Override
    public void onPause(){
        super.onPause();
    }


    /**
     * Inicializacion del audio player, asignacion del audio correspondiente al sitio visitado
     */
    public void initializePlayer() {
        if (audioPlayer != null){
            audioPlayer.release();
        }
        //eleccion de audio
        String audio="";
        audio = db.obtengaTexto(etiqueta);
        audio= audio +"audio";

       /* switch (etiqueta)
        {
            case "Edificio de la Escuela de Química":
                audio="quimicaaudio";
                break;
            case "Edificio de Escuela de Estudios Generales":
                audio="generalesaudio";
                break;
            case "Edificio de la Facultad de Microbiología":
                audio= "microaudio";
                break;
            case "Edificio de la Facultad de Medicina":
                audio= "medicinaaudio";
                break;
            case "Edificio de Escuela Centroamericana de Geología":
                audio= "geologiaaudio";
                break;
                default:
                    audioPlayer = MediaPlayer.create(getActivity(), R.raw.lacus_somniorum);
                    break;
        }*/
        //getResources().getIdentifier(audio, "raw", getContext().getPackageName());

        audioPlayer = MediaPlayer.create(getActivity(),getResources().getIdentifier(audio, "raw", getContext().getPackageName()) );
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

    /**
     * Clase que implementa el slideshow para mostrar imagenes en la vista de informacion general del sitio
     */
    public class ImagePagerAdapter extends PagerAdapter {
        //obener imagenes para el slideshow
       //private int[] mImages;
       private ArrayList<Integer> mImages= new ArrayList<Integer>();
        private String nombreLugar="";

        /**
         * Inicializa los valores del pageadapter y recibe como parametro los nombres de   imagenes que debe mostrar
         * @param e arraylist con los nombres de imagenes
         */
        public ImagePagerAdapter(ArrayList<String> e){
            ArrayList<Integer> fotos = new ArrayList<Integer>();
           for(int i=0;i<e.size();i++){
               String foto =e.get(i);
              // fotos[i]= getResources().getIdentifier(foto , "drawable", getContext().getPackageName());
              fotos.add(getResources().getIdentifier(foto , "drawable", getContext().getPackageName()));
           }
           mImages = fotos;
        }

        /**
         *
         * @return Cantidad de imagenes de un sitio especifico
         */
        @Override
        public int getCount() {
            return mImages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        /**
         * Define el formato de el slideshow para el muestreo de las im'agenes
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = getActivity();
            ImageView imageView = new ImageView(context);
            int padding = context.getResources().getDimensionPixelSize(
                    R.dimen.padding_small);
            imageView.setPadding(padding, padding, padding, padding);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setImageResource(mImages.get(position));
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        /**
         * Destructor del slideshow
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }
}