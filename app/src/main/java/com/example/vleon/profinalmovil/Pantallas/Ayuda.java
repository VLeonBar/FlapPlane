package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import com.example.vleon.profinalmovil.ObjetosJuego.Boton;
import com.example.vleon.profinalmovil.R;

import java.util.ArrayList;

public class Ayuda extends Escena {
    Boton textHeader, textFooter, textAyuda, frameImagenes;
    ArrayList<Boton> ventanas = new ArrayList<>();
    Bitmap[] imagenes;
    String[] textos = new String[7];
    private int i = 0;

    public Ayuda(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {

        super(contexto, idEscena, anchoPantalla, altoPantalla);
        textos[0] = "Pulsa la pantalla para elevar el avión";
        textos[1] = "Suelta para hacer que el avión baje";
        textos[2] = "Evita las columnas eléctricas para no perder";
        textos[3] = "Coge monedas para sumar mayor puntuación";
        textos[4] = "Intenta conseguir el mayor número de puntos";
        textos[5] = "Al perder, escribe tus iniciales para guardar tus puntos";
        textos[6] = "Podrás ver tus puntos en la pantalla de récords";

        imgFondo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.fondopantallas);
        imgFondo = Bitmap.createScaledBitmap(imgFondo, anchoPantalla, altoPantalla, false);
        textHeader = new Boton(0, fh.partePantalla(altoPantalla, 40) * 1, anchoPantalla, fh.partePantalla(altoPantalla, 12) * 1, Color.TRANSPARENT, typeface2);
        textHeader.setTexto("CÓMO JUGAR", fh.getDpH(120, altoPantalla), Color.BLACK);
        textAyuda = new Boton(0, fh.partePantalla(altoPantalla, 12) * 10, anchoPantalla, fh.partePantalla(altoPantalla, 20) * 19, Color.WHITE, typeface2);
        textAyuda.setTexto(textos[0], fh.getDpH(50, altoPantalla), Color.BLACK);
        textFooter = new Boton(0, textAyuda.getRect().bottom, anchoPantalla, altoPantalla, Color.TRANSPARENT, typeface2);
        textFooter.setTexto("TOCA PARA CONTINUAR", fh.getDpH(30, altoPantalla), Color.BLACK);
        frameImagenes = new Boton(fh.partePantalla(anchoPantalla, 8), textHeader.getRect().bottom, anchoPantalla, textAyuda.getRect().top, Color.TRANSPARENT, typeface2);
        imagenes = fh.getFrames(7, "imgsayuda", "ayuda", frameImagenes.getRect().height());
        frameImagenes.setImg(imagenes[0]);
        ventanas.add(textHeader);
        ventanas.add(frameImagenes);
        ventanas.add(textAyuda);
        ventanas.add(textFooter);
    }

    public int actualizarFisica() {
        return idEscena;

    }

    public void dibujar(Canvas c) {
        try {
            //Fondo de pantalla del controles
            c.drawBitmap(imgFondo, 0, 0, null);
            for (Boton b : ventanas) {
                b.dibujar(c);
            }
            //llama al dibujar de la clase padre para dibujar los elementos comunes a todas las clases hijas

            super.dibujar(c);


        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int accion = event.getActionMasked();
        switch (accion) {

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                i++;
                if (i > imagenes.length - 1)
                    i = 0;
                frameImagenes.setImg(imagenes[i]);
                textAyuda.setTexto(textos[i], fh.getDpH(50, altoPantalla), Color.BLACK);
                break;
        }
        int padre = super.onTouchEvent(event);
        if (padre != idEscena) return padre;
        return idEscena;
    }
}
