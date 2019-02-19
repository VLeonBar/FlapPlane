package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.vleon.profinalmovil.Manejadores.FrameHandler;
import com.example.vleon.profinalmovil.Manejadores.Parallax;
import com.example.vleon.profinalmovil.ObjetosJuego.Barreras;
import com.example.vleon.profinalmovil.ObjetosJuego.Moneda;
import com.example.vleon.profinalmovil.ObjetosJuego.Nave;

import java.util.Timer;

public class Juego extends Escena {

    Rect bajaNave, subeNave;
    Nave nave;
    Barreras barrera;
    Moneda moneda;
    Parallax parallax;
    private boolean sube = false;
    private int record = 0;


    public Juego(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, anchoPantalla, altoPantalla);
        //Parallax
        parallax = new Parallax(contexto, anchoPantalla, altoPantalla, 3);
        subeNave = new Rect(0, 0, fh.partePantalla(anchoPantalla, 2), altoPantalla);
        bajaNave = new Rect(fh.partePantalla(anchoPantalla, 2), 0, anchoPantalla, altoPantalla);
        moneda = new Moneda(contexto, anchoPantalla, altoPantalla, fh.getFrames(10, "monedas", "moneda", fh.partePantalla(anchoPantalla, 10)));
        nave = new Nave(contexto, anchoPantalla, altoPantalla, fh.getFrames(2, "aviones", "sube", fh.partePantalla(anchoPantalla, 10)), fh.partePantalla(anchoPantalla, 8), fh.partePantalla(altoPantalla, 2));
        barrera = new Barreras(contexto, anchoPantalla, altoPantalla, fh.getFrames(2, "barreras", "barrera", altoPantalla));
    }

    public int actualizarFisica() {
        if (nave.choqueNave(barrera.getAlBarrerasTop(), barrera.getAlBarrerasBot(), moneda.getAlMonedas())) {
            sonidos.getEfectos().release();
            return 0;
        }
        Log.i("puntuacion", "" + nave.getPuntuacion());
        parallax.actualizarFisica();
        barrera.actualizarFisica();
        nave.actualizarFisica(sube);
        moneda.actualizarFisica();
        return idEscena;
    }

    public void dibujar(Canvas c) {
        try {
            parallax.dibuja(c);
            barrera.dibujar(c);
            moneda.dibujar(c);
            nave.dibujar(c, sube);
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
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            sube = false;
        }
        int padre = super.onTouchEvent(event);
        if (padre != idEscena) {
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
