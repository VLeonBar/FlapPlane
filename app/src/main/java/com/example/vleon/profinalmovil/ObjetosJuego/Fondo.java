package com.example.vleon.profinalmovil.ObjetosJuego;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

/**
 * The type Fondo.
 */
public class Fondo {
    /**
     * The Posicion.
     */
    public PointF posicion, /**
     * The Posicion 2.
     */
    posicion2;
    /**
     * The Imagen.
     */
    public Bitmap imagen;

    /**
     * Instantiates a new Fondo.
     *
     * @param imagen the imagen
     * @param x      the x
     * @param y      the y
     */
    public Fondo(Bitmap imagen, float x, float y) { // Constructores
        this.imagen = imagen;
        this.posicion = new PointF(x, y);
        this.posicion2 = new PointF(posicion.x + imagen.getWidth(), y);
    }

    /**
     * Instantiates a new Fondo.
     *
     * @param imagen       the imagen
     * @param altoPantalla the alto pantalla
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
     * Mover.
     *
     * @param velocidad the velocidad
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
