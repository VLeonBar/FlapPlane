package com.example.vleon.profinalmovil;

import android.graphics.Point;
import android.graphics.PointF;

import java.util.ArrayList;

public class Circulo {

    int radio;
    float posX, posY;

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

    public boolean colision(Circulo c1, Circulo c2) {

        double xDif = c1.posX - c2.posX;
        double yDif = c1.posY - c2.posY;
        ;
        double distanceSquared = xDif * xDif + yDif * yDif;
        boolean collision = distanceSquared < (c1.radio + c2.radio) * (c1.radio + c2.radio);
        return collision;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
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

    public Circulo(int radio, float posX, float posY) {
        this.radio = radio;
        this.posX = posX;
        this.posY = posY;
    }
    /*
    if (distanciaMasCorta <= Math.pow(circle1.getRadio() + circle2.getRadio(), 2)) {
            Log.i("patata", "Colision");

            double backdist = Math.sqrt(Math.pow(circle1.getRadio() + circle2.getRadio(), 2) - distanciaMasCorta);
            double movementvectorlength = Math.sqrt(Math.pow(event2.getX() + velocityX, 2) + Math.pow(event2.getY() + velocityY, 2));
            double c_x = d.x - backdist * (event2.getX() / movementvectorlength);
            double c_y = d.y - backdist * (event2.getY() / movementvectorlength);


            double collisiondist = Math.sqrt(Math.pow(circle2.getPosX() - c_x, 2) + Math.pow(circle2.getPosY() - c_y, 2));
            double n_x = (circle2.getPosX() - c_x) / collisiondist;
            double n_y = (circle2.getPosY() - c_y) / collisiondist;
            double p = 2 * (event2.getX() + velocityX * n_x + event2.getY() + velocityY * n_y);
            double w_x = event2.getX() + velocityX - p * n_x - p * n_x;
            double w_y = event2.getY() + velocityY - p * n_y - p * n_y;
            double vect_x_c1 = event2.getX() + velocityX - p * n_x;
            double vect_y_c1 = event2.getY() + velocityY - p * n_y;
            double vect_x_c2 = circle2.getPosX() + p * n_x;
            double vect_y_c2 = circle2.getPosY() + p * n_y;






            private PointF PuntoMasCercanoALinea(float punto1_x, float punto1_y,
                                         float punto2_x, float punto2_y, float x0, float y0) {
        float A1 = punto2_y - punto1_y;
        float B1 = punto1_x - punto2_x;
        double C1 = (punto2_y - punto1_y) * punto1_x + (punto1_x - punto2_x) * punto1_y;
        double C2 = -B1 * x0 + A1 * y0;
        double det = A1 * A1 - -B1 * B1;
        float cx = 0;
        float cy = 0;
        if (det != 0) {
            cx = (float) ((A1 * C1 - B1 * C2) / det);
            cy = (float) ((A1 * C2 - -B1 * C1) / det);
        } else {
            cx = x0;
            cy = y0;
        }
        return new PointF(cx, cy);
    }
    */
}
