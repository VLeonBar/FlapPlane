package com.example.vleon.profinalmovil.Objetos;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

public class Fondo {
    public PointF posicion, posicion2;
    public Bitmap imagen;

    public Fondo(Bitmap imagen, float x, float y) { // Constructores
        this.imagen = imagen;
        this.posicion = new PointF(x, y);
        this.posicion2 = new PointF(posicion.x + imagen.getWidth(), y);
    }

    public Fondo(Bitmap imagen, int altoPantalla) {
        this(imagen, 0, altoPantalla - imagen.getHeight());
    }

    public void dibujar(Canvas c) {
        c.drawBitmap(imagen, posicion.x, posicion.y, null);
        c.drawBitmap(imagen, posicion2.x, posicion2.y, null);
    }

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
