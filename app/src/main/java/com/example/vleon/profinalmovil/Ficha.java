package com.example.vleon.profinalmovil;

import android.graphics.Bitmap;
import android.graphics.PointF;

import java.util.ArrayList;

public class Ficha {
    Bitmap imgFicha;
    float posX, posY;
    int radio;
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

    public boolean colision(Ficha f1, Ficha f2) {

        double xDif = f1.posX - f2.posX;
        double yDif = f1.posY - f2.posY;
        double distanceSquared = xDif * xDif + yDif * yDif;
        boolean collision = distanceSquared < (f1.radio + f2.radio) * (f1.radio + f2.radio);
        return collision;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public Ficha() {
    }

    public Ficha(int posX, int posY, Bitmap imgFicha) {
        this.imgFicha = imgFicha;
        this.posX = posX;
        this.posY = posY;

    }
}
