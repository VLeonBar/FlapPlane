package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.vleon.profinalmovil.Manejadores.FrameHandler;
import com.example.vleon.profinalmovil.Objetos.Barreras;
import com.example.vleon.profinalmovil.Objetos.Moneda;
import com.example.vleon.profinalmovil.Objetos.Nave;
import com.example.vleon.profinalmovil.Manejadores.Parallax;

public class Juego extends Escena {

    Rect bajaNave, subeNave;
    Nave nave;
    Barreras barrera;
    Moneda moneda;
    Parallax parallax;
    FrameHandler fh;
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
        moneda = new Moneda(contexto, anchoPantalla, altoPantalla, fh.getFrames(10, "monedas", "moneda", fh.partePantalla(anchoPantalla, 10)));
        nave = new Nave(contexto, anchoPantalla, altoPantalla, fh.getFrames(2, "aviones", "vuelo", fh.partePantalla(anchoPantalla, 10)), fh.partePantalla(anchoPantalla, 8), fh.partePantalla(altoPantalla, 2));
        barrera = new Barreras(contexto, altoPantalla, anchoPantalla, fh.getFrames(2, "barreras", "barrera", altoPantalla));
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
        moneda.actualizarFisica(barrera.getAlBarrerasTop());
        return idEscena;
    }

    public void dibujar(Canvas c) {
        try {
            //TODO
            parallax.dibuja(c);
            nave.dibujar(c);
            barrera.dibujar(c);
            moneda.dibujar(c);
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
//            sonidos.getEfectos().release();
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
