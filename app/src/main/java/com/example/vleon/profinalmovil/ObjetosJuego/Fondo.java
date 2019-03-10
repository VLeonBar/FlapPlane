package com.example.vleon.profinalmovil.ObjetosJuego;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

/**
 * Clase Fondo, permite colocar una imagen y darle movimiento.
 */
public class Fondo {
    /**
     * La posicion de la imagen.
     */
    public PointF posicion, /**
     * La segunda posicion de la misma imagen .
     */
    posicion2;
    /**
     * La imagen.
     */
    public Bitmap imagen;

    /**
     * Instancia un nuevo objeto de la clase Fondo.
     *
     * @param imagen la imagen
     * @param x      la posicion x
     * @param y      la posicion y
     */
    public Fondo(Bitmap imagen, float x, float y) { // Constructores
        this.imagen = imagen;
        this.posicion = new PointF(x, y);
        this.posicion2 = new PointF(posicion.x + imagen.getWidth(), y);
    }

    /**
     * Instancia un nuevo objeto de la clase Fondo.
     *
     * @param imagen       la imagen
     * @param altoPantalla el alto de pantalla
     */
    public Fondo(Bitmap imagen, int altoPantalla) {
        this(imagen, 0, altoPantalla - imagen.getHeight());
    }

    /**
     * Dibujar.
     *
     * @param c el Canvas
     */
    public void dibujar(Canvas c) {
        c.drawBitmap(imagen, posicion.x, posicion.y, null);
        c.drawBitmap(imagen, posicion2.x, posicion2.y, null);
    }

    /**
     * Mueve las imagenes.
     *
     * @param velocidad la velocidad
     */
    public void mover(int velocidad) { // Desplazamiento
        posicion.x -= velocidad;
        posicion2.x -= velocidad;
        if (posicion.x + imagen.getWidth() < 0) {
            posicion.x = posicion2.x + imagen.getWidth();
        }
        if (posicion2.x + imagen.getWidth() < 0) {
            posicion2.x = posicion.x + imagen.getWidth();
        }
    }
}
