package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import com.example.vleon.profinalmovil.ObjetosJuego.Boton;
import com.example.vleon.profinalmovil.R;

public class Creditos extends Escena {
    Boton textoCreditos;
    int velocidad = 1;

    public Creditos(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, anchoPantalla, altoPantalla);
        imgFondo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.fondopantallas);
        imgFondo = Bitmap.createScaledBitmap(imgFondo, anchoPantalla, altoPantalla, false);
        textoCreditos = new Boton(0, altoPantalla, anchoPantalla, altoPantalla * 2, Color.BLUE);
        textoCreditos.setTexto("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n" +
                "", 50, Color.BLACK);
    }

    public int actualizarFisica() {
        textoCreditos.getRect().top -= fh.getDpH(velocidad, altoPantalla);
        textoCreditos.getRect().bottom -= fh.getDpH(velocidad, altoPantalla);
        if (textoCreditos.getRect().bottom <= 0) {
            textoCreditos.getRect().top = altoPantalla;
            textoCreditos.getRect().bottom = altoPantalla * 2;
        }
        return idEscena;
    }

    public void dibujar(Canvas c) {
        try {
            //Fondo de pantalla del creditos
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
            case MotionEvent.ACTION_DOWN:
                velocidad = 5;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                velocidad = 1;
                break;
        }
        int padre = super.onTouchEvent(event);
        if (padre != idEscena) return padre;
        return idEscena;
    }
}
