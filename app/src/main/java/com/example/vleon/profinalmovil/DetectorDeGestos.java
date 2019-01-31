package com.example.vleon.profinalmovil;

import android.graphics.PointF;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class DetectorDeGestos extends GestureDetector.SimpleOnGestureListener {
    private static final String DEBUG_TAG = "GESTO";
    Ficha ficha;

    public DetectorDeGestos(Ficha ficha) {
        this.ficha=ficha;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.i("patata", "Single tap");
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        Log.i("patata", "EVENT 1 " + event1.getX() + " Y " + event1.getY() + " EVENT 2 " + event2.getX() + " Y " + event2.getY() + " VELX" + velocityX + " VELY " + velocityY);
        ficha.creaRecta(new PointF(event1.getX(), event1.getY()), new PointF(event2.getX(), event2.getY()));
        return true;
    }


}
