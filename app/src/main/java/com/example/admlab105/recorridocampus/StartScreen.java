package com.example.admlab105.recorridocampus;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.app.Activity;

public class StartScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        int loadtime = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(StartScreen.this, Intro_activity.class);
                startActivity(i);
                this.finish();
            }

            private void finish() {}}, loadtime);

    }
}