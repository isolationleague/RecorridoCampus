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
}
