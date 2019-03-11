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
 * La Escena de Ayuda, en la cual aparecen una serie de explicaciones de como jugar.
 *
 * @author Victor Leon Barciela
 */
public class Ayuda extends Escena {
    /**
     * El texto de la cabecera.
     */
    Boton textHeader, /**
     * El texto del pie de pagina.
     */
    textFooter, /**
     * El texto de la ayuda.
     */
    textAyuda, /**
     * El marco de imagenes centrales.
     */
    frameImagenes;
    /**
     * Las diferentes ventanas de ayuda.
     */
    ArrayList<Boton> ventanas = new ArrayList<>();
    /**
     * Las imagenes que van en el marco central.
     */
    Bitmap[] imagenes;
    /**
     * Los textos de ayuda.
     */
    String[] textos = new String[7];
    private int i = 0;

    /**
     * Instancia un nuevo objeto de la clase Ayuda.
     *
     * @param contexto      the contexto
     * @param idEscena      the id escena
     * @param anchoPantalla the ancho pantalla
     * @param altoPantalla  the alto pantalla
     */
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
    /**
     * Actualiza las físicas.
     *
     * @return int el id de la escena
     */
    public int actualizarFisica() {
        return idEscena;

    }
    /**
     * Se encarga de dibujar todos los elementos que se indiquen en el lienzo.
     *
     * @param c el Canvas
     */
    public void dibujar(Canvas c) {
        try {
            c.drawBitmap(imgFondo, 0, 0, null);
            for (Boton b : ventanas) {
                b.dibujar(c);
            }
            super.dibujar(c);
        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }
    /**
     * Evento OnTouch, se lanza cuando se toca la pantalla.
     *
     * @param event el evento
     * @return int el id de la escena.
     */
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
