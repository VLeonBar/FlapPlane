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

/**
 * La Escena de Creditos, en la que se ven los créditos mediante un scroll vertical.
 * @author Victor Leon Barciela
 */
public class Creditos extends Escena {
    /**
     * El texto.
     */
    Boton texto, /**
     * La cabecera.
     */
    header;
    /**
     * Coleccion de botones para facilitar el manejo.
     */
    ArrayList<Boton> lineas = new ArrayList<>();
    /**
     * Velocidad del scroll
     */
    int velocidad = fh.getDpH(7, altoPantalla);

    /**
     * Instancia un nuevo objeto de la clase Creditos.
     *
     * @param contexto      el contexto
     * @param idEscena      la id escena
     * @param anchoPantalla el ancho pantalla
     * @param altoPantalla  el alto pantalla
     */
    public Creditos(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, anchoPantalla, altoPantalla);
        imgFondo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.fondopantallas);
        imgFondo = Bitmap.createScaledBitmap(imgFondo, anchoPantalla, altoPantalla, false);
        header = new Boton(0, 0, anchoPantalla, fh.partePantalla(altoPantalla, 2), Color.TRANSPARENT, typeface2);
        header.setTexto("GREEDY PILOT", fh.getDpH(150, altoPantalla), Color.BLACK);
        for (int i = 0; i < 15; i++) {
            texto = new Boton(0, header.getRect().bottom + fh.partePantalla(altoPantalla, 15) * i, anchoPantalla, header.getRect().bottom + fh.partePantalla(altoPantalla, 15) * (i + 1), Color.TRANSPARENT, typeface1);
            lineas.add(texto);
        }
        lineas.get(0).setTexto("Código realizado por:", fh.getDpH(50, altoPantalla), Color.BLACK);
        lineas.get(1).setTexto(" Víctor León Barciela", fh.getDpH(30, altoPantalla), Color.BLACK);
        lineas.get(2).setTexto("Efectos de sonido:", fh.getDpH(50, altoPantalla), Color.BLACK);
        lineas.get(3).setTexto("Victor Leon Barciela - Marta Alvarez", fh.getDpH(30, altoPantalla), Color.BLACK);
        lineas.get(4).setTexto("Música:", fh.getDpH(50, altoPantalla), Color.BLACK);
        lineas.get(5).setTexto("soundimage.org (Música libre y sin copyright)", fh.getDpH(30, altoPantalla), Color.BLACK);
        lineas.get(6).setTexto("Imágenes, Iconos y Fuentes", fh.getDpH(50, altoPantalla), Color.BLACK);
        lineas.get(7).setTexto("Victor Leon Barciela - gameart2d.com - craftpix.net - flaticon.es - 1001fonts.com", fh.getDpH(30, altoPantalla), Color.BLACK);
        lineas.get(8).setTexto("Mención especial y agradecimientos a:", fh.getDpH(50, altoPantalla), Color.BLACK);
        lineas.get(9).setTexto("Marta Alvarez - Javier Conde - Hadrian Villar - Jose Villar", fh.getDpH(30, altoPantalla), Color.BLACK);
        lineas.get(10).setTexto("Lucas Alonso - Samuel Figueirido - Curro Bellas", fh.getDpH(30, altoPantalla), Color.BLACK);
        lineas.get(11).setTexto("Nieves Barciela - Pablo León B - Pablo León H", fh.getDpH(30, altoPantalla), Color.BLACK);
        lineas.get(12).setTexto("Hans Zimmer - Ludovico Einaudi - Mumford and Sons", fh.getDpH(30, altoPantalla), Color.BLACK);
        lineas.get(13).setTexto("Redes Sociales:", fh.getDpH(50, altoPantalla), Color.BLACK);
        lineas.get(14).setTexto("@VLeon__", fh.getDpH(30, altoPantalla), Color.BLACK);
    }

    public int actualizarFisica() {
        header.getRect().top -= fh.getDpH(velocidad, altoPantalla);
        header.getRect().bottom -= fh.getDpH(velocidad, altoPantalla);
        if (header.getRect().bottom <= 0) {
            header.getRect().top = lineas.get(14).getRect().bottom;
            header.getRect().bottom = lineas.get(14).getRect().bottom + fh.partePantalla(altoPantalla, 2);
        }
        for (int i = 0; i < lineas.size(); i++) {
            lineas.get(i).getRect().top -= fh.getDpH(velocidad, altoPantalla);
            lineas.get(i).getRect().bottom -= fh.getDpH(velocidad, altoPantalla);
            if (lineas.get(i).getRect().bottom <= 0) {
                if (i == 0) {
                    lineas.get(i).getRect().top = header.getRect().bottom;
                    lineas.get(i).getRect().bottom = header.getRect().bottom + fh.partePantalla(altoPantalla, 15) * (i + 1);
                } else {
                    lineas.get(i).getRect().top = lineas.get(i - 1).getRect().bottom;
                    lineas.get(i).getRect().bottom = lineas.get(i - 1).getRect().bottom + fh.partePantalla(altoPantalla, 15);
                }
            }
        }
        return idEscena;
    }

    public void dibujar(Canvas c) {
        try {
            c.drawBitmap(imgFondo, 0, 0, null);
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
        int accion = event.getActionMasked();
        switch (accion) {
            case MotionEvent.ACTION_DOWN:
                velocidad = fh.getDpH(1, altoPantalla);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                velocidad = fh.getDpH(7, altoPantalla);
                break;
        }
        int padre = super.onTouchEvent(event);
        if (padre != idEscena) return padre;
        return idEscena;
    }
}
