package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.vleon.profinalmovil.Barreras;
import com.example.vleon.profinalmovil.Fondo;
import com.example.vleon.profinalmovil.FrameHandler;
import com.example.vleon.profinalmovil.Nave;
import com.example.vleon.profinalmovil.Parallax;
import com.example.vleon.profinalmovil.R;
import com.example.vleon.profinalmovil.Sonidos;

import java.util.ArrayList;

public class Juego extends Escena {

    Rect bajaNave, subeNave;
    Nave nave;
    Barreras barrera;
    Parallax parallax;
    FrameHandler fh;
    long tiempoAntiguo, tiempoToque;
    int velocidad = 100;
    private boolean sube = false;
    private int record = 0;


    public Juego(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, anchoPantalla, altoPantalla);
        //Parallax
        fh = new FrameHandler(contexto);
        parallax = new Parallax(contexto, anchoPantalla, altoPantalla, 3);
        subeNave = new Rect(0, 0, fh.partePantalla(anchoPantalla, 2), altoPantalla);
        bajaNave = new Rect(fh.partePantalla(anchoPantalla, 2), 0, anchoPantalla, altoPantalla);
        nave = new Nave(fh.partePantalla(anchoPantalla, 8), fh.partePantalla(altoPantalla, 2), fh.getFrames(2, "aviones", "vuelo", fh.partePantalla(anchoPantalla, 10)), altoPantalla, contexto);
        barrera = new Barreras(altoPantalla, anchoPantalla, fh.getFrames(2, "barreras", "barrera", altoPantalla), fh.getFrames(9, "monedas", "moneda", fh.partePantalla(anchoPantalla, 8)));
        tiempoToque = System.currentTimeMillis();
        tiempoAntiguo = System.currentTimeMillis();

    }

    public int actualizarFisica() {
        if (nave.choqueNave(barrera.getAlBarrerasTop(), barrera.getAlBarrerasBot(), barrera.getAlMonedas())) {
            sonidos.getEfectos().release();
            return 0;
        }
        Log.i("puntuacion", "" + nave.getPuntuacion());
        parallax.actualizarFisica();
        barrera.actualizarFisica();
        nave.actualizarFisica(sube, fh.partePantalla(altoPantalla, velocidad));
        return idEscena;
    }

    public void dibujar(Canvas c) {
        try {
            //TODO
            parallax.dibuja(c);
            nave.dibujar(c);
            barrera.dibujar(c);
            super.dibujar(c);
        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    public int onTouchEvent(MotionEvent event) {
        Log.i("pepe", "" + event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            sonidos.getEfectos().play(sonidos.sonidoMotor, 1, 1, 1, 0, 1);
            sube = true;
            //SPRITES SUBIDA
//            nave.setSkins(fh.getFrames(2, "aviones", "vuelo", fh.partePantalla(anchoPantalla, 8)));
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            sube = false;
            //SPRITES BAJADA
//            nave.setSkins(fh.getFrames(1, "aviones", "baja", fh.partePantalla(anchoPantalla, 8)));
        }
        int padre = super.onTouchEvent(event);
        if (padre != idEscena) {
            sonidos.getEfectos().stop(sonidos.sonidoMotor);
            sonidos.getEfectos().release();
            return padre;
        }
        return idEscena;
    }
}
































        /*for (JLabel barrera : alBarreras) {
            barrera.setLocation(barrera.getX() - 5, 100);
            if (barrera.getX() < 50) {
                record++;
                lblContador.setText("Puntuación : " + Integer.toString(record));
            }
            if (lblAvion.getBounds().intersects(barrera.getBounds())) {
                creaRecord(record);
                flagColision = false;
            }

        }
        for (JLabel barrera : alBarreras2) {
            barrera.setLocation(barrera.getX() - 5, barrera.getY());
            if (barrera.getX() < 50) {
                record++;
                lblContador.setText("Puntuación : " + Integer.toString(record));
            }
            if (lblAvion.getBounds().intersects(barrera.getBounds())) {
                creaRecord(record);
                flagColision = false;
            }
        }*/
