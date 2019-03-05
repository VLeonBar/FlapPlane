package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.vleon.profinalmovil.R;

public class Ajustes extends Escena {
    Rect sound, vibration;

    public Ajustes(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, anchoPantalla, altoPantalla);
        imgFondo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.fondopantallas);
        imgFondo = Bitmap.createScaledBitmap(imgFondo, anchoPantalla, altoPantalla, false);
        sound = new Rect(fh.partePantalla(anchoPantalla, 10) * 7, fh.partePantalla(altoPantalla, 10) * 2, fh.partePantalla(anchoPantalla, 10) * 9, fh.partePantalla(altoPantalla, 10) * 3);
        vibration = new Rect(fh.partePantalla(anchoPantalla, 10) * 7, fh.partePantalla(altoPantalla, 10) * 4, fh.partePantalla(anchoPantalla, 10) * 9, fh.partePantalla(altoPantalla, 10) * 5);
    }

    public int actualizarFisica() {
        return idEscena;
    }

    public void dibujar(Canvas c) {
        try {
            //Fondo de pantalla de ajustes
            c.drawBitmap(imgFondo, 0, 0, null);
            c.drawText("Sonido", fh.partePantalla(anchoPantalla, 10), fh.partePantalla(altoPantalla, 10) * 3, pincel2);
            c.drawText("Vibración", fh.partePantalla(anchoPantalla, 10), fh.partePantalla(altoPantalla, 10) * 5, pincel2);
            c.drawRect(sound, pincel2);
            c.drawRect(vibration, pincel2);
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
            case MotionEvent.ACTION_DOWN:
                if (isVibrationOn)
                    vibrar(100);
                if (isSoundOn)
                    sonidos.getEfectos().play(sonidos.sonidoToque, 1, 1, 1, 0, 1);
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (pulsa(sound, event)) {
                    Log.i("pepito", "AAAAAAAAAAAAA");
                    if (isSoundOn) {
                        sonidos.mediaPlayer.pause();
                        isSoundOn = false;
                    } else {
                        sonidos.mediaPlayer.start();
                        isSoundOn = true;
                    }
                    editorPreferencias.putBoolean("sonido", isSoundOn);
                } else if (pulsa(vibration, event)) {
                    if (isVibrationOn) {
                        isVibrationOn = false;
                    } else {
                        isVibrationOn = true;
                    }
                    editorPreferencias.putBoolean("vibracion", isVibrationOn);
                }
                editorPreferencias.commit();
                break;
        }
        int padre = super.onTouchEvent(event);
        if (padre != idEscena) return padre;
        return idEscena;
    }
}
