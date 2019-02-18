package com.example.vleon.profinalmovil.ObjetosJuego;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

public class MonedaMenu {
    Bitmap imgFicha;
    float posX, posY;
    int cont;
    boolean bandera;
    float centroMonedaX, centroMonedaY;
    //Debe tener una hitbox determinada por un circulo.

    public Bitmap getImgFicha() {
        return imgFicha;
    }

    public void setImgFicha(Bitmap imgFicha) {
        this.imgFicha = imgFicha;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public ArrayList<PointF> creaRecta(PointF inicio, PointF fin) {
        ArrayList<PointF> recta = new ArrayList<>();
        // delta of exact value and rounded value of the dependent variable
        float d = 0;
        float x1 = (float) inicio.x, y1 = (float) inicio.y, x2 = (float) fin.x, y2 = (float) fin.y;
        float dx = Math.abs(x2 - x1);
        float dy = Math.abs(y2 - y1);

        float dx2 = 2 * dx; // slope scaling factors to
        float dy2 = 2 * dy; // avoid floating pofloat

        float ix = x1 < x2 ? 1 : -1; // increment direction
        float iy = y1 < y2 ? 1 : -1;

        float x = x1;
        float y = y1;

        if (dx >= dy) {
            while (true) {
                recta.add(new PointF(x, y));
                if (x == x2)
                    break;
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                recta.add(new PointF(x, y));
                if (y == y2)
                    break;
                y += iy;
                d += dx2;
                if (d > dy) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
        return recta;
    }

    public boolean mueveMoneda(Rect boton) {
        float posCentroFichaX = this.getPosX() + this.getImgFicha().getWidth() / 2;
        float posCentroFichaY = this.getPosY() + this.getImgFicha().getHeight() / 2;
        PointF inicio = new PointF(posCentroFichaX, posCentroFichaY);
        PointF fin = new PointF(boton.centerX(), boton.centerY());
        ArrayList<PointF> current = this.creaRecta(inicio, fin);
        if (cont < current.size() - 1) {
            cont++;
            this.setPosX(current.get(cont).x - this.getImgFicha().getWidth() / 2);
            this.setPosY(current.get(cont).y - this.getImgFicha().getHeight() / 2);
            bandera = true;
        } else bandera = false;
        return bandera;
    }

    public void dibujar(Canvas c) {
        try {
            c.drawBitmap(this.getImgFicha(), this.getPosX(), this.getPosY(), null);
        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    public MonedaMenu() {
    }

    public MonedaMenu(int posX, int posY, Bitmap imgFicha) {
        this.imgFicha = imgFicha;
        this.posX = posX;
        this.posY = posY;
        centroMonedaX = this.getPosX() + this.getImgFicha().getWidth() / 2;
        centroMonedaY = this.getPosY() + this.getImgFicha().getHeight() / 2;

    }
}
