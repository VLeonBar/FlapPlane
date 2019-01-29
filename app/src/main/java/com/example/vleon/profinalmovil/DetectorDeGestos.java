package com.example.vleon.profinalmovil;

import android.graphics.PointF;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class DetectorDeGestos extends GestureDetector.SimpleOnGestureListener {
    private static final String DEBUG_TAG = "GESTO";
    Circulo circle1 = new Circulo(200, 200, 100);
    Circulo circle2 = new Circulo(500, 500, 100);

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.i(DEBUG_TAG, "Single tap");
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

        colisionCirculos(event2, velocityX, velocityY);
        return true;
    }

    public void colisionCirculos(MotionEvent event2, float velocityX, float velocityY) {
        PointF d = PuntoMasCercanoALinea(circle1.getPosX(), circle1.getPosY(),
                circle1.getPosX() + event2.getX(), circle1.getPosY() + event2.getY(), circle2.getPosX(), circle2.getPosY());
        double distanciaMasCorta = Math.pow(circle2.getPosX() - d.x, 2) + Math.pow((circle2.getPosY() - d.y), 2);
        if (distanciaMasCorta <= Math.pow(circle1.getRadio() + circle2.getRadio(), 2)) {
            Log.i(DEBUG_TAG, "Colision");

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
        } else {
            Log.i(DEBUG_TAG, "No colision");

        }
    }

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

}
