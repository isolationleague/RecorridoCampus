package com.example.admlab105.recorridocampus;

import android.provider.BaseColumns;

/**
 * Created by usuario on 18/04/2018.
 */

public final class BaseSitiosContract {
    private BaseSitiosContract(){}
    public static class SitioBase implements BaseColumns {
        public static final String TABLE_NAME = "sitio";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_COORDENADA_X = "coordenadaX";
        public static final String COLUMN_COORDENADA_Y = "coordenadaY";
        public static final String COLUMN_VISITADO = "visitado";
    }

    public static class Usuario implements  BaseColumns{
        public static final String TABLE_NAME = "Usuario";
        public static final String USUARIO_NOMBRE ="nombre";
        public static final String USUARIO_PUNTOS ="puntos";
    }

    public static class Foto implements BaseColumns{
        public static final String TABLE_NAME = "Foto";
        public static final String ID_SITIO = "id_sitio";
        public static final String RUTA ="ruta";
    }

    public static class Video implements BaseColumns{
        public static final String TABLE_NAME = "Video";
        public static final String ID_SITIO = "id_sitio";
        public static final String ID_VIDEO = "id_video";
        public static final String RUTA ="ruta";
    }

    public static class Audio implements BaseColumns{
        public static final String TABLE_NAME = "Audio";
        public static final String ID_SITIO = "id_sitio";
        public static final String ID_AUDIO = "id_audio";
        public static final String RUTA ="ruta";
    }

    public static class Texto implements BaseColumns{
        public static final String TABLE_NAME = "Texto";
        public static final String ID_SITIO = "id_sitio";
        public static final String ID_TEXTO = "id_texto";
        public static final String RUTA ="ruta";
    }

}
