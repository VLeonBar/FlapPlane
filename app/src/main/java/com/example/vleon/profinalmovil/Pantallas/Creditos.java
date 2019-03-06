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

public class Creditos extends Escena {
    Boton texto, header;
    ArrayList<Boton> lineas = new ArrayList<>();
    int velocidad = 5;

    public Creditos(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, anchoPantalla, altoPantalla);
        imgFondo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.fondopantallas);
        imgFondo = Bitmap.createScaledBitmap(imgFondo, anchoPantalla, altoPantalla, false);
        header = new Boton(0, 0, anchoPantalla, fh.partePantalla(altoPantalla, 2), Color.TRANSPARENT);
        header.setTexto("GREEDY PILOT", fh.getDpH(150, altoPantalla), Color.BLACK);
        for (int i = 0; i < 14; i++) {
            texto = new Boton(0, header.getRect().bottom + fh.partePantalla(altoPantalla, 14) * i, anchoPantalla, header.getRect().bottom + fh.partePantalla(altoPantalla, 14) * (i + 1), Color.TRANSPARENT);
            lineas.add(texto);
        }
        lineas.get(0).setTexto("Código realizado por:", fh.getDpH(50,altoPantalla), Color.BLACK);
        lineas.get(1).setTexto(" Víctor León Barciela", fh.getDpH(30,altoPantalla), Color.BLACK);
        lineas.get(2).setTexto("Efectos de sonido:", fh.getDpH(50,altoPantalla), Color.BLACK);
        lineas.get(3).setTexto("Víctor León Barciela - Marta Álvarez", fh.getDpH(30,altoPantalla), Color.BLACK);
        lineas.get(4).setTexto("Música:", fh.getDpH(50,altoPantalla), Color.BLACK);
        lineas.get(5).setTexto("soundimage.org (Música libre y sin copyright)", fh.getDpH(30,altoPantalla), Color.BLACK);
        lineas.get(6).setTexto("Imágenes, Iconos y Fuentes", fh.getDpH(50,altoPantalla), Color.BLACK);
        lineas.get(7).setTexto("Víctor León Barciela - gameart2d.com - craftpix.net - flaticon.es", fh.getDpH(30,altoPantalla), Color.BLACK);
        lineas.get(8).setTexto("Mención especial y agradecimientos a:", fh.getDpH(50,altoPantalla), Color.BLACK);
        lineas.get(9).setTexto("Marta Álvarez - Javier Conde - Hadrián Villar - Jose Villar", fh.getDpH(30,altoPantalla), Color.BLACK);
        lineas.get(10).setTexto("Lucas Alonso de San Segundo - Samuel Figueirido - Francisco Bellas", fh.getDpH(30,altoPantalla), Color.BLACK);
        lineas.get(11).setTexto("Hans Zimmer - Ludovico Einaudi - Mumford and Sons", fh.getDpH(30,altoPantalla), Color.BLACK);
        lineas.get(12).setTexto("Redes Sociales:", fh.getDpH(50,altoPantalla), Color.BLACK);
        lineas.get(13).setTexto("@VLeon__", fh.getDpH(30,altoPantalla), Color.BLACK);
    }

    public int actualizarFisica() {
        header.getRect().top -= velocidad;
        header.getRect().bottom -= velocidad;
        if (header.getRect().bottom <= 0) {
            header.getRect().top = lineas.get(13).getRect().bottom;
            header.getRect().bottom = lineas.get(13).getRect().bottom + fh.partePantalla(altoPantalla, 2);
        }
        for (int i = 0; i < lineas.size(); i++) {
            lineas.get(i).getRect().top -= velocidad;
            lineas.get(i).getRect().bottom -= velocidad;
            if (lineas.get(i).getRect().bottom <= 0) {
                if (i == 0) {
                    lineas.get(i).getRect().top = header.getRect().bottom;
                    lineas.get(i).getRect().bottom = header.getRect().bottom + fh.partePantalla(altoPantalla, 20) * (i + 1);
                } else {
                    lineas.get(i).getRect().top = lineas.get(i - 1).getRect().bottom;
                    lineas.get(i).getRect().bottom = lineas.get(i - 1).getRect().bottom + fh.partePantalla(altoPantalla, 20);
                }
            }
        }
        return idEscena;
    }

    public void dibujar(Canvas c) {
        try {
            //Fondo de pantalla del creditos
            c.drawBitmap(imgFondo, 0, 0, null);
            int cont = 0;
            header.dibujar(c);
            for (Boton b : lineas) {
                b.dibujar(c);
            }
            super.dibujar(c);


        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int accion = event.getActionMasked();
        switch (accion) {
            case MotionEvent.ACTION_DOWN:
                velocidad = 10;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                velocidad = 5;
                break;
        }
        int padre = super.onTouchEvent(event);
        if (padre != idEscena) return padre;
        return idEscena;
    }
}
