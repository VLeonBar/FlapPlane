package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.vleon.profinalmovil.Manejadores.Parallax;
import com.example.vleon.profinalmovil.ObjetosJuego.Barreras;
import com.example.vleon.profinalmovil.ObjetosJuego.Boton;
import com.example.vleon.profinalmovil.ObjetosJuego.Moneda;
import com.example.vleon.profinalmovil.ObjetosJuego.Nave;
import com.example.vleon.profinalmovil.R;

public class Juego extends Escena {

    Boton btnReanudar, btnSalir;
    Nave nave;
    Barreras barrera;
    Moneda moneda;
    Parallax parallax;
    private boolean sube = false;
    private int record = 0;
    Boolean isPlaying = true;

    public Juego(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, anchoPantalla, altoPantalla);
        //Parallax
        parallax = new Parallax(contexto, anchoPantalla, altoPantalla, 3);
        btnReanudar = new Boton(fh.partePantalla(anchoPantalla, 6), fh.partePantalla(altoPantalla, 6), fh.partePantalla(anchoPantalla, 6) * 5, fh.partePantalla(altoPantalla, 6) * 2, Color.BLUE);
        btnSalir = new Boton(fh.partePantalla(anchoPantalla, 6), fh.partePantalla(altoPantalla, 6) * 3, fh.partePantalla(anchoPantalla, 6) * 5, fh.partePantalla(altoPantalla, 6) * 4, Color.BLUE);
        btnReanudar.setTexto("Reanudar", fh.getDpH(150, altoPantalla), Color.BLACK);
        btnSalir.setTexto("Salir", fh.getDpH(150, altoPantalla), Color.BLACK);
        moneda = new Moneda(contexto, anchoPantalla, altoPantalla, fh.getFrames(10, "monedas", "moneda", fh.partePantalla(anchoPantalla, 10)));
        nave = new Nave(contexto, anchoPantalla, altoPantalla, fh.getFrames(2, "aviones", "sube", fh.partePantalla(anchoPantalla, 10)), fh.partePantalla(anchoPantalla, 8), fh.partePantalla(altoPantalla, 2));
        barrera = new Barreras(contexto, anchoPantalla, altoPantalla, fh.getFrames(2, "barreras", "barrera", altoPantalla));
    }

    public int actualizarFisica() {
        sm.registerListener(proximitySensorListener, proxSensor, 1000 * 500);
        if (isSensorOn) isPlaying = false;
        if (nave.choqueNave(barrera.getAlBarrerasTop(), barrera.getAlBarrerasBot(), moneda.getAlMonedas())) {
            if (isVibrationOn)
                vibrar(500);
            if (isSoundOn) {
                sonidos.getEfectos().play(sonidos.sonidoExplosion, 1, 1, 1, 0, 1);
                sonidos.getEfectos().release();
            }
            sm.unregisterListener(proximitySensorListener);
            return 6;
        }
        if (isPlaying) {
            parallax.actualizarFisica();
            barrera.actualizarFisica();
            nave.actualizarFisica(sube);
            moneda.actualizarFisica();
        }
        return idEscena;
    }

    public void dibujar(Canvas c) {
        try {
            pincel = new Paint();
            pincel.setColor(Color.BLACK);
            pincel.setAlpha(100);
            parallax.dibuja(c);
            moneda.dibujar(c);
            barrera.dibujar(c);
            nave.dibujar(c, sube);
            if (!isPlaying) {
                c.drawRect(0, 0, anchoPantalla, altoPantalla, pincel);
                btnReanudar.dibujar(c);
                btnSalir.dibujar(c);
            }
            super.dibujar(c);
        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    public int onTouchEvent(MotionEvent event) {
        Log.i("pepe", "" + event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isPlaying) {
                sube = true;
            } else {
                if (pulsa(btnReanudar.getRect(), event)) {
                    isPlaying = true;
                }
                if (pulsa(btnSalir.getRect(), event)) {
                    sm.unregisterListener(proximitySensorListener);
                    return 0;
                }
            }
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isPlaying) {
                sube = false;
            }
        }
        int padre = super.onTouchEvent(event);
        if (padre != idEscena) {
            if (isSoundOn) {
                sonidos.getEfectos().release();
                sm.unregisterListener(proximitySensorListener);
            }
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
