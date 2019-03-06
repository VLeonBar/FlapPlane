package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import com.example.vleon.profinalmovil.Manejadores.Parallax;
import com.example.vleon.profinalmovil.ObjetosJuego.Barreras;
import com.example.vleon.profinalmovil.ObjetosJuego.Boton;
import com.example.vleon.profinalmovil.ObjetosJuego.Moneda;
import com.example.vleon.profinalmovil.ObjetosJuego.Nave;
import com.example.vleon.profinalmovil.R;

import java.util.ArrayList;

public class Juego extends Escena {

    Boton btnReanudar, btnSalir, textoCabecera;
    ArrayList<Boton> botones = new ArrayList<>();
    Nave nave;
    Barreras barrera;
    Moneda moneda;
    Parallax parallax;
    boolean sube = false;
    int record = 0;
    Boolean isPlaying = true;
    long last, now;
    int velocidad;

    public Juego(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, anchoPantalla, altoPantalla);
        //Manejo Movimiento Nave
        now = System.currentTimeMillis();
        last = System.currentTimeMillis();
        velocidad = fh.getDpH(20, altoPantalla);
        //Parallax
        parallax = new Parallax(contexto, anchoPantalla, altoPantalla, 3);
        textoCabecera = new Boton(0, fh.partePantalla(altoPantalla, 6), anchoPantalla, fh.partePantalla(altoPantalla, 6) * 2, Color.TRANSPARENT, typeface2);
        textoCabecera.setTexto("PAUSA", 150, Color.BLACK);
        botones.add(textoCabecera);
        btnReanudar = new Boton(fh.partePantalla(anchoPantalla, 6) * 2, fh.partePantalla(altoPantalla, 6) * 2, fh.partePantalla(anchoPantalla, 6) * 4, fh.partePantalla(altoPantalla, 6) * 3, Color.TRANSPARENT, typeface1);
        btnSalir = new Boton(fh.partePantalla(anchoPantalla, 6) * 2, fh.partePantalla(altoPantalla, 6) * 4, fh.partePantalla(anchoPantalla, 6) * 4, fh.partePantalla(altoPantalla, 6) * 5, Color.TRANSPARENT, typeface1);
        btnReanudar.setImg(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(contexto.getResources(), R.drawable.boton_play), btnReanudar.getRect().width(), btnReanudar.getRect().height(), false));
        btnSalir.setImg(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(contexto.getResources(), R.drawable.boton_exit), btnSalir.getRect().width(), btnSalir.getRect().height(), false));
        botones.add(btnReanudar);
        botones.add(btnSalir);
        moneda = new Moneda(contexto, anchoPantalla, altoPantalla, fh.getFrames(10, "monedas", "moneda", fh.partePantalla(anchoPantalla, 10)));
        nave = new Nave(contexto, anchoPantalla, altoPantalla, fh.getFrames(2, "aviones", "sube", fh.partePantalla(anchoPantalla, 10)), fh.partePantalla(anchoPantalla, 8), fh.partePantalla(altoPantalla, 2));
        barrera = new Barreras(contexto, anchoPantalla, altoPantalla, fh.getFrames(2, "barreras", "barrera", altoPantalla));
    }

    public int actualizarFisica() {
        now = System.currentTimeMillis();
        if (now - last > 300) {
            velocidad += fh.getDpH(5, altoPantalla);
        }
        sm.registerListener(proximitySensorListener, proxSensor, 1000 * 500);
        if (isSensorOn) isPlaying = false;
        if (nave.choqueNave(barrera.getAlBarrerasTop(), barrera.getAlBarrerasBot(), moneda.getAlMonedas())) {
            if (isVibrationOn)
                vibrar(500);
            if (isSoundOn) {
                sonidos.getEfectos().autoPause();
                sonidos.getEfectos().play(sonidos.sonidoExplosion, 1, 1, 1, 0, 1);
            }
            sm.unregisterListener(proximitySensorListener);
            return 6;
        }
        if (isPlaying) {
            parallax.actualizarFisica();
            barrera.actualizarFisica();
            nave.actualizarFisica(sube, velocidad);
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
                for (Boton b : botones) {
                    b.dibujar(c);
                }
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
                if (isSoundOn) {
                    sonidos.getEfectos().play(sonidos.sonidoMotor, 1, 1, 1, 0, 1);
                }
                sube = true;
                velocidad = fh.getDpH(20, altoPantalla);
                ;
                last = System.currentTimeMillis();
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
                velocidad = fh.getDpH(20, altoPantalla);
                ;
                last = System.currentTimeMillis();
            }
        }
        int padre = super.onTouchEvent(event);
        if (padre != idEscena) {
            if (isSoundOn) {
                sonidos.getEfectos().autoPause();
                sm.unregisterListener(proximitySensorListener);
            }
            return padre;
        }
        return idEscena;
    }
}