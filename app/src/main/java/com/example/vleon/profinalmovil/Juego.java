package com.example.vleon.profinalmovil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.ArrayList;

public class Juego extends Escena {
    ArrayList<Ficha> fichasJuego = new ArrayList<>();
    Ficha ficha;
    GestureDetector detectorDeGestos;
    Bitmap imgGradaIzq, imgGradaDch, imgLogo;
    int anchoDecimo, altoMedio, anchoQuinto, altoSexto, anchoMedio,altoOctavo,altoDecimo;
    int ancho_1 = 1, ancho_2 = 2, alto_1 = 1, alto_2 = 2;
    float posCentroFichaX, posCentroFichaY;
    Rect[] casillas = new Rect[9];

    public Juego(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, anchoPantalla, altoPantalla);
        imgFondo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.fondo_game_edit);
        imgFondo = Bitmap.createScaledBitmap(imgFondo, anchoPantalla, altoPantalla, false);
        anchoDecimo = anchoPantalla / 10;
        anchoQuinto = anchoPantalla / 5;
        anchoMedio = anchoPantalla / 2;
        altoMedio = altoPantalla / 2;
        altoSexto = altoMedio / 3;
        altoOctavo = altoPantalla / 8;
        altoDecimo = altoPantalla / 10;

        //Ficha
        ficha = new Ficha(0, 0, new BitmapFactory().decodeResource(contexto.getResources(), R.drawable.bandera9));
        ficha.setImgFicha(Bitmap.createScaledBitmap(ficha.getImgFicha(), altoDecimo / 2, altoDecimo / 2, false));
        ficha.setPosX(anchoMedio - ficha.getImgFicha().getWidth() / 2);
        ficha.setPosY(altoSexto * (float) 5.5 - ficha.getImgFicha().getHeight() / 2);
        GestureDetector detectorDeGestos = new GestureDetector(new DetectorDeGestos(ficha));
        posCentroFichaX = ficha.getPosX() + ficha.getImgFicha().getWidth() / 2;
        posCentroFichaY = ficha.getPosY() + ficha.getImgFicha().getHeight() / 2;
        //Gradas e icono central
        imgLogo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.icono);
        imgLogo = Bitmap.createScaledBitmap(imgLogo, anchoDecimo * 6, altoPantalla / 3, false);
        imgGradaDch = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.gradadch_main);
        imgGradaDch = Bitmap.createScaledBitmap(imgGradaDch, anchoDecimo, altoMedio, false);
        imgGradaIzq = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.gradaizq_main);
        imgGradaIzq = Bitmap.createScaledBitmap(imgGradaIzq, anchoDecimo, altoMedio, false);
        //Rectángulos - Botones

        for (int i = 0; i < casillas.length; i++) {
            casillas[i] = new Rect(anchoQuinto * ancho_1, altoOctavo * alto_1, anchoQuinto * ancho_2, altoOctavo * alto_2);
            ancho_1++;
            ancho_2++;
            if ((i + 1) % 3 == 0) {
                ancho_1 = 1;
                ancho_2 = 2;
                alto_1++;
                alto_2++;
            }
        }
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
            for (int i = 0; i < casillas.length; i++) {
                c.drawRect(casillas[i], pincel);
            }
            c.drawLine(0, 0, anchoPantalla, 0, pincel);
//            c.drawLine(0, altoSexto * 3, anchoPantalla, altoSexto * 3, pincel);
            c.drawCircle(anchoMedio, altoOctavo * (float) 5.5, altoOctavo / (float) 2.5, pincel);
            c.drawBitmap(ficha.getImgFicha(), ficha.getPosX(), ficha.getPosY(), null);
            super.dibujar(c);


        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    public int onTouchEvent(MotionEvent event) {
        detectorDeGestos.onTouchEvent(event);
        int padre = super.onTouchEvent(event);
        if (padre != idEscena) return padre;
        return idEscena;
    }
}
