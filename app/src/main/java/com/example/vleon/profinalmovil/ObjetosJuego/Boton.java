package com.example.vleon.profinalmovil.ObjetosJuego;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

/**
 * Una clase que permite introducir texto o una imagen a un rectángulo para poder interactuar con el, lo cual da una gran versatilidad sobre todo a la hora de ampliar funcionalidades.
 */
public class Boton {
    /**
     * El pincel para el rectangulo.
     */
    private Paint p, /**
     * El pincel para el texto.
     */
    pText;
    /**
     * El rectangulo.
     */
    private Rect rect;
    /**
     * La imagen.
     */
    private Bitmap img;
    /**
     * El texto.
     */
    private String texto;

    /**
     * Instancia un nuevo objeto de la clase Boton.
     *
     * @param left     el punto izquierdo del rectangulo
     * @param top      el punto superior del rectangulo
     * @param right    el punto derecho del rectangulo
     * @param bottom   el punto inferior del rectangulo
     * @param color    el color del rectangulo
     * @param typeface el typeface
     */
    public Boton(int left, int top, int right, int bottom, int color, Typeface typeface) {
        this.rect = new Rect(left, top, right, bottom);
        p = new Paint();
        p.setColor(color);
        p.setTypeface(typeface);

        pText = new Paint();
        pText.setTypeface(typeface);
        pText.setTextAlign(Paint.Align.CENTER);
        this.img = null;
    }

    /**
     * Da un valor a texto.
     *
     * @param texto el texto
     * @param size  el size del texto
     * @param color el color del texto
     */
    public void setTexto(String texto, int size, int color) {
        this.texto = texto;
        pText.setColor(color);
        pText.setTextSize(size);
    }

    /**
     * Da un valor a color.
     *
     * @param col el color
     */
    public void setColor(int col) {
        p.setColor(col);
    }

    /**
     * Da un valor a img.
     *
     * @param img la imagen
     */
    public void setImg(Bitmap img) {
        this.img = img;
    }

    /**
     * Devuelve texto.
     *
     * @return el texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Devuelve img.
     *
     * @return la imagen
     */
    public Bitmap getImg() {
        return img;
    }

    /**
     * Devuelve el rectangulo.
     *
     * @return el rectangulo
     */
    public Rect getRect() {
        return rect;
    }

    /**
     * Dibujar.
     *
     * @param c el Canvas
     */
    public void dibujar(Canvas c) {
        c.drawRect(rect, p);
        if (img != null) {
            c.drawBitmap(img, rect.left, rect.top, null);
        }
        if (texto != null) {
            c.drawText(texto, rect.centerX(), rect.centerY() - ((pText.descent() + pText.ascent()) / 2), pText);
        }
    }
}
