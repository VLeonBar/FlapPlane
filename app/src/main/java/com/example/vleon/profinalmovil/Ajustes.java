package com.example.vleon.profinalmovil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

public class Ajustes extends Escena {
    public Ajustes(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, anchoPantalla, altoPantalla);
        imgFondo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.bandera2);
        imgFondo = Bitmap.createScaledBitmap(imgFondo, anchoPantalla, altoPantalla, false);
    }
    public void actualizarFisica() {

    }

    public void dibujar(Canvas c) {
        try {
            //Fondo de pantalla de ajustes
            c.drawBitmap(imgFondo, 0, 0, null);



        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }
    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int accion = event.getActionMasked();
        switch (accion) {

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                break;
        }

        return idEscena;
    }
}
