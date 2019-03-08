package com.example.vleon.profinalmovil.ObjetosJuego;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

/**
 * The type Boton.
 */
public class Boton {
    private Paint p, pTextoBtn;
    private Rect rect;
    private Bitmap img;
    private String texto;

    /**
     * Instantiates a new Boton.
     *
     * @param left     the left
     * @param top      the top
     * @param right    the right
     * @param bottom   the bottom
     * @param color    the color
     * @param typeface the typeface
     */
    public Boton(int left, int top, int right, int bottom, int color, Typeface typeface) {
        this.rect = new Rect(left, top, right, bottom);
        p = new Paint();
        p.setColor(color);
        p.setTypeface(typeface);

        pTextoBtn = new Paint();
        pTextoBtn.setTypeface(typeface);
        pTextoBtn.setTextAlign(Paint.Align.CENTER);
        this.img = null;
    }

    /**
     * Da un valor a texto.
     *
     * @param texto  the texto
     * @param tamaño the tamaño
     * @param color  the color
     */
    public void setTexto(String texto, int tamaño, int color) {
        this.texto = texto;
        pTextoBtn.setColor(color);
        pTextoBtn.setTextSize(tamaño);
    }

    /**
     * Da un valor a color.
     *
     * @param col the col
     */
    public void setColor(int col) {
        p.setColor(col);
    }

    /**
     * Da un valor a img.
     *
     * @param img the img
     */
    public void setImg(Bitmap img) {
        this.img = img;
    }

    /**
     * Devuelve texto.
     *
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Devuelve img.
     *
     * @return the img
     */
    public Bitmap getImg() {
        return img;
    }

    /**
     * Devuelve rect.
     *
     * @return the rect
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
            c.drawText(texto, rect.centerX(), rect.centerY() - ((pTextoBtn.descent() + pTextoBtn.ascent()) / 2), pTextoBtn);
        }
    }
}
