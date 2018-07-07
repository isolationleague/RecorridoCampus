package com.example.admlab105.recorridocampus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class Creditos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);

        TextView texto = findViewById(R.id.content);

        String txt1= "Estudiantes del curso Ingeniería de Software 2";

        String txt2= "Maria Fernanda Carmona Ulate";
        String txt3="Javier Alonso Fernández Aguilar";
        String txt4="Josué Fernando Pereira Leiva";
        String txt5="Pablo Quirós Víquez";
        String txt6="Juan Manuel Torres Vega";
        String txt7="Kevin Yegeni Waltam Vásquez";

        String txt8="Profesor del curso Ingeniería de Software 2";
        String txt9="Alan Calderón Castro";

        String txt9_1="Encargado de Desarrollo y de Diseño";
        String txt9_2="Mariano Chinchilla";

        String txt10="Coordinadora del museo+UCR";
        String txt11="Eugenia Zavaleta Ochoa";

        String txt12="Definición de contenidos y textos";
        String txt13="Laura M. Raabe Cercone, curadora de Artes, museo+UCR";

        String txt14="Asistentes del museo+UCR";
        String txt15="Daniela Rodríguez Minsky, diseño gráfico y audios";
        String txt16="Carlos Garita Viquez, fotografías y recopilación de contenido";

        String txt17="Referencias utilizadas";

        String txt18="Bibliografía";
        String txt19="Andrade, Grettel M. “Arte público en la Universidad de Costa Rica Un arte para todos y todas. \nEscena. Revista de las artes, Volumen 75, Número 1, 2015, pp. 139-166.";

        String txt20="Fonseca, Virginia de. “Dos artistas, dos estéticas, dos valores nacionales: \nJuan Portuguez y Crisanto Badilla”, en Káñina, Vol. II, N° 3-4, 1978, pp. 167-172.";

        String txt21="Fumero Páez, Alejo. Juan R. Chacón: un capítulo de la escultura costarricense. San José, C.R.: \nEditorial de la Universidad de Costa Rica, Comisión Nacional de Conmemoraciones Históricas, MCJD, 1998.";

        String txt22="Monge Picado, María José et al. El retablo de la corte de Carlos Jiménez. \nSan José, C.R.: Museos del Banco Central, 2017.";
        String txt23="“La sala multiuso: un espacio vivo”. Sin datos de publicación, 2007.";

        String txt24="Artículos digitales";

        String txt25="Corrales Arias, Adriano.“Apuntes sobre la obra de Leda Astorga”. \nEn: https://semanariouniversidad.com/suplementos/forja/apuntes-sobre-la-obra-de-leda-astorga/";

        String txt26="Fonseca Calvo, María Eugenia. “Esculturas de José Sancho son patrimonio de la UCR”. En:";
        String txt27="https://www.ucr.ac.cr/noticias/2014/08/18/esculturas-de-jose-sancho-son-patrimonio-de-la-ucr.html";

        String txt28="Fonseca Calvo, María Eugenia. “Paseo Escultórico, una vía para el deleite de los sentidos”. \nEn: https://www.ucr.ac.cr/noticias/2014/09/01/paseo-escultorico-una-via-para-el-deleite-de-los-sentidos.html";
        String txt29="Hurtado Oviedo, Víctor. “Los redondos mundos de Leda Astorga”. \nEn: https://www.nacion.com/archivo/los-redondos-mundos-de-leda-astorga/HSOEGZES4BHXFFJM2E7SNFOYVE/story/";

        String txt30="Marín Castro, Andrea. “Fuente “Cupido y el Cisne” tendrá nueva cara”. \nEn: https://semanariouniversidad.com/universitarias/fuente-cupido-y-el-cisne-tendr-nueva-cara/";

        String txt31="Marín Castro, Andrea. “Fuente “Cupido y el Cisne” tendrá nueva cara”. \nEn: https://www.ucr.ac.cr/noticias/2013/11/11/fuente-cupido-y-el-cisne-tendra-nueva-cara.html";

        String txt32="Orozco Abarca, Sergio. “La mítica fuente ‘Cupido y el cisne’ vuelve a dar luz a la UCR”. \nEn: https://www.nacion.com/viva/cultura/la-mitica-fuente-cupido-y-el-cisne-vuelve-a-dar-luz-a-la-ucr/HO5B3SYEV5B7HJM67ZH5YNUL6U/story/";

        String txt33="Sitios";

        String txt34="http://museo.ucr.ac.cr/rfb/";
        String txt35="http://josesanchoescultor.com/";
        String txt36="http://www.ccss.sa.cr/patrimonio/index.php/coleccion-de-arte/category/14-juan-portuguez-fucigna";
        String txt37="https://si.cultura.cr/personas/leda-astorga-mora.html";
        String txt38="https://www.ucr.ac.cr/acerca-u/historia-simbolos/rectores";
        String txt39="https://www.editorialcostarica.com/escritores.cfm";

        String txt40="Fuentes primarias";

        String txt41="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/106521.html";
        String txt42="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/347501.html";
        String txt43="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/496261.html";
        String txt44="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/445301.html";
        String txt45="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/535501.html";
        String txt46="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/521801.html";
        String txt47="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/515851.html";
        String txt48="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/456901.html";
        String txt49="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/114561.html";
        String txt50="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/105941.html";
        String txt51="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/60222.html";
        String txt52="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/69421.html";
        String txt53="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/367261.html";
        String txt54="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/368501.html";
        String txt55="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/376711.html";
        String txt56="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/384031.html";
        String txt57="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/69421.html";
        String txt58="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/588531.html";
        String txt59="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/492981.html";
        String txt60="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/488971.html";
        String txt61="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/490941.html";
        String txt62="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/472511.html";
        String txt63="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/457011.html";
        String txt64="http://www.cu.ucr.ac.cr/busqueda/acuerdo/NumeroAcuerdo/158271.html";

        texto.setText(Html.fromHtml("<p><strong>" + txt1 + "</strong></p>" + "<p>" + "</p>"+"<p>" + txt2 + "</p>"+"<p>" + txt3 + "</p>"+"<p>" + txt4 + "</p>"
        +"<p>" + txt5 + "</p>"+"<p>" + txt6 + "</p>"+"<p>" + txt7 + "</p>"+"<p>" + "</p>"+"<p><strong>" + txt8 + "</strong></p>"+"<p>" + txt9 + "</p>"+"<p><strong>" + txt9_1 + "</strong></p>"+"<p>" + txt9_2 + "</p>"
        +"<p>" + "</p><strong>"+txt10 + "</strong></p>"+"<p>" + txt11 + "</p>" + "<p>" + "</p><strong>"+txt12 + "</strong></p>"+"<p>" + txt13 + "</p>"+"<p>" + "</p>"
        + "</p><strong>"+txt14 + "</strong></p>"+"<p>" + txt15 + "</p>"+ "</p>"+txt16 + "</p>"+"<p>" + "</p>"+"<p><strong>" + txt17 + "</strong></p>" + "<p>" + "</p>"
        + "</p><strong>"+txt18 + "</strong></p>"+"<p>" + txt19 + "</p>"+ "<p>" + "</p>"+"<p>" + txt20 + "</p>"+"<p>" + "</p>"+"<p>" + txt21 + "</p>"
        +"<p>" + txt22 + "</p>"+"<p>" + txt23 + "</p>"+"<p>" + "</p>"+"<p><strong>" + txt24 + "</strong></p>"+  "<p>" + "</p>"+"<p>" + txt25 + "</p>"
        +"<p>" + txt26 + "</p>"+"<p>" + txt27 + "</p>" + "<p>" + "</p>" + "<p>" + txt28 + "</p>"+"<p>" + txt29 + "</p>"+"<p>" + "</p>"
        +"<p>" + txt30 + "</p>"+"<p>" + "</p>"+"<p>" + txt31 + "</p>"+"<p>" + "</p>"+"<p>" + txt32 + "</p>"+"<p>" + "</p>"
                +"<p><strong>" + txt33 + "</strong></p>"+ "<p>" + "</p>"+"<p>" + txt34 + "</p>"+"<p>" + txt35 + "</p>"+"<p>" + txt36 + "</p>"+
                        "<p>" + txt37 + "</p>"+"<p>" + txt38 + "</p>"+"<p>" + txt39 + "</p>" + "<p>" + "</p>"
        +"<p><strong>" + txt40 + "</strong></p>"+"<p>" + "</p>"+"<p>" + txt41 + "</p>" + "</p>"+"<p>" + txt42 + "</p>"+ "</p>"+"<p>" + txt43 + "</p>"
                        + "</p>"+"<p>" + txt44 + "</p>"+ "</p>"+"<p>" + txt45 + "</p>"+ "</p>"+"<p>" + txt46 + "</p>"+ "</p>"+"<p>" + txt47 + "</p>"
                + "</p>"+"<p>" + txt48 + "</p>"+ "</p>"+"<p>" + txt49 + "</p>"+ "</p>"+"<p>" + txt50 + "</p>"+ "</p>"+"<p>" + txt51 + "</p>"
                + "</p>"+"<p>" + txt52 + "</p>"+ "</p>"+"<p>" + txt53 + "</p>"+ "</p>"+"<p>" + txt54 + "</p>"+ "</p>"+"<p>" + txt55 + "</p>"
                + "</p>"+"<p>" + txt56 + "</p>"+ "</p>"+"<p>" + txt57 + "</p>"+ "</p>"+"<p>" + txt58 + "</p>"+ "</p>"+"<p>" + txt59 + "</p>"
                + "</p>"+"<p>" + txt60 + "</p>"+ "</p>"+"<p>" + txt61 + "</p>"+ "</p>"+"<p>" + txt62 + "</p>"
                        + "</p>"+"<p>" + txt63 + "</p>"+ "</p>"+"<p>" + txt64 + "</p>" ));
        texto.setMovementMethod(new ScrollingMovementMethod()); //"<p>" + txt1 + "</p>" + "<p>" + "</p>"

    }
}
