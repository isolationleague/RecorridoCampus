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

import android.content.Intent;
        import android.os.Handler;
        import android.os.Bundle;
        import android.app.Activity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Intro_activity extends Activity {

    Button bclose;
    TextView texto;
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
        String txt1 = "Un paseo por el campus ";
        String txt2 = "es una aplicación para Android, diseñada como una audio guía para ";
        String txt3 = "descubrir el patrimonio arquitectónico y el arte público de la Ciudad Universitaria Rodrigo Facio Brenes";
        String txt4 = ", mientras la recorre. Por medio de audios, textos y fotografías se podrá conocer más sobre los bienes culturales que resguarda la Universidad de Costa Rica.";
        String txt5 ="La aplicación fue desarrollada en su totalidad por estudiantes del curso CI-1430 Ingeniería de software 2 de la Escuela de Ciencias de la Computación e Informática, con la colaboración de estudiantes con horas asistente, de diversas áreas, del museo+UCR. Así, el museo+UCR se compromete con la divulgación de las colecciones de la UCR y con el apoyo a la docencia.";
        texto = (TextView) findViewById(R.id.textView2);
        texto.setText(Html.fromHtml("<p>" + "<i>" + txt1 + "</i>" + txt2 + "<i>" + txt3 + "</i>" + txt4 + "</p>" + "<p>" +  txt5  + "</p>" + "</small>"));
        texto.setMovementMethod(new ScrollingMovementMethod());

    }
}
