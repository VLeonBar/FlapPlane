package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.example.vleon.profinalmovil.R;

/**
 * The type Ayuda.
 */
public class Ayuda extends Escena {
    /**
     * Instantiates a new Ayuda.
     *
     * @param contexto      el contexto
     * @param idEscena      el id  de la escena
     * @param anchoPantalla el ancho pantalla
     * @param altoPantalla  el alto pantalla
     */
    public Ayuda(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, anchoPantalla, altoPantalla);
        imgFondo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.fondopantallas);
        imgFondo = Bitmap.createScaledBitmap(imgFondo, anchoPantalla, altoPantalla, false);
    }

    public int actualizarFisica() {
        return idEscena;

    }

    public void dibujar(Canvas c) {
        try {
            //Fondo de pantalla del controles
            c.drawBitmap(imgFondo, 0, 0, null);
            //llama al dibujar de la clase padre para dibujar los elementos comunes a todas las clases hijas

            super.dibujar(c);


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
        int padre = super.onTouchEvent(event);
        if (padre != idEscena) return padre;
        return idEscena;
    }
}
