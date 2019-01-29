package com.example.vleon.profinalmovil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

public class Juego extends Escena {
    ArrayList<Ficha> fichasJuego = new ArrayList<>();
    Bitmap imgGradaIzq, imgGradaDch, imgLogo;
    int anchoDecimo, altoMedio, anchoTercio, altoSexto, anchoMedio;
    float posCentroFichaX, posCentroFichaY;
    Ficha ficha;

    public Juego(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, anchoPantalla, altoPantalla);
        imgFondo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.fondo_game_edit);
        imgFondo = Bitmap.createScaledBitmap(imgFondo, anchoPantalla, altoPantalla, false);
        anchoDecimo = anchoPantalla / 10;
        anchoTercio = anchoPantalla / 3;
        anchoMedio = anchoPantalla / 2;
        altoMedio = altoPantalla / 2;
        altoSexto = altoMedio / 3;
        //Ficha
        ficha = new Ficha(0, 0, new BitmapFactory().decodeResource(contexto.getResources(), R.drawable.bandera9));
        ficha.setImgFicha(Bitmap.createScaledBitmap(ficha.getImgFicha(), altoSexto / 2, altoSexto / 2, false));
        ficha.setPosX(anchoMedio - ficha.getImgFicha().getWidth() / 2);
        ficha.setPosY(altoSexto * (float) 5.5 - ficha.getImgFicha().getHeight() / 2);
        posCentroFichaX = ficha.getPosX() + ficha.getImgFicha().getWidth() / 2;
        posCentroFichaY = ficha.getPosY() + ficha.getImgFicha().getHeight() / 2;
        imgLogo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.icono);
        imgLogo = Bitmap.createScaledBitmap(imgLogo, anchoDecimo * 6, altoPantalla / 3, false);
        imgGradaDch = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.gradadch_main);
        imgGradaDch = Bitmap.createScaledBitmap(imgGradaDch, anchoDecimo, altoMedio, false);
        imgGradaIzq = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.gradaizq_main);
        imgGradaIzq = Bitmap.createScaledBitmap(imgGradaIzq, anchoDecimo, altoMedio, false);
    }

    public int actualizarFisica() {
        return idEscena;
    }

    public void dibujar(Canvas c) {
        try {
            //Fondo de pantalla del JUEGO
            c.drawBitmap(imgFondo, 0, 0, null);
            c.drawBitmap(imgFondo, 0, 0, null);
            c.drawBitmap(imgLogo, anchoDecimo * 2, altoMedio, null);
            c.drawBitmap(imgGradaDch, anchoDecimo * 9, altoMedio, null);
            c.drawBitmap(imgGradaIzq, 0, altoMedio, null);
            c.drawLine(0, 0, anchoPantalla, 0, pincel);
            c.drawLine(0, altoSexto * 3, anchoPantalla, altoSexto * 3, pincel);
            c.drawCircle(anchoMedio, altoSexto * (float) 5.5, altoSexto / (float) 2.5, pincel);
            c.drawBitmap(ficha.getImgFicha(), ficha.getPosX(), ficha.getPosY(), null);
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
