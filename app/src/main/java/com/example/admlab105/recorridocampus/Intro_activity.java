package com.example.admlab105.recorridocampus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Intro_activity extends Activity {
    Button bclose;
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        bclose= (Button) findViewById(R.id.buttonClose);
        bclose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intro_activity.this, MainActivity.class);
                startActivity(i);
                finish();

            }
            private void finish() {};

        });



    }


}
