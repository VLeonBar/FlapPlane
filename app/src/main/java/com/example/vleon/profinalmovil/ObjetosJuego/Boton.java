package com.example.vleon.profinalmovil.ObjetosJuego;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class Boton {
    private Paint p, pTextoBtn;
    private Rect rect;
    private Bitmap img;
    private String texto;

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

    public void setTexto(String texto, int tamaño, int color) {
        this.texto = texto;
        pTextoBtn.setColor(color);
        pTextoBtn.setTextSize(tamaño);
    }

    public void setColor(int col) {
        p.setColor(col);
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getTexto() {
        return texto;
    }

    public Bitmap getImg() {
        return img;
    }

    public Rect getRect() {
        return rect;
    }

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
