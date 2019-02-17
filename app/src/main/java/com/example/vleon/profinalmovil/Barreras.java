package com.example.vleon.profinalmovil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

import java.util.ArrayList;

public class Barreras {
    ArrayList<Rect> alBarrerasTop = new ArrayList<>();
    ArrayList<Rect> alBarrerasBot = new ArrayList<>();
    Bitmap[] skins;
    Rect barreraTop, barreraBot;
    int altoPantalla, anchoPantalla;
    int randPointY, randLocY, contCreacion = 0;
    int velMoveBarrera = 10;
    int tiempoFrame = 80;
    long tiempoFrameAux = 0;
    int indice = 0, velocidad;
    Paint pincel;

    public ArrayList<Rect> getAlBarrerasTop() {
        return alBarrerasTop;
    }

    public ArrayList<Rect> getAlBarrerasBot() {
        return alBarrerasBot;
    }


    public Barreras(int altoPantalla, int anchoPantalla, Bitmap[] skins) {
        this.altoPantalla = altoPantalla;
        this.anchoPantalla = anchoPantalla;
        this.skins = skins;
        pincel = new Paint();
        pincel.setColor(Color.rgb(59, 36, 16));
    }

    public void mueveBarrera(ArrayList<Rect> alTop, ArrayList<Rect> alBot) {
        for (Rect barrera : alTop) {
            barrera.left -= velMoveBarrera;
            barrera.right -= velMoveBarrera;
//            if (barrera.right < 0) {
//                //sube puntuacion
//                record++;
//                Log.i("patata", "" + record);
//                if (record % 15 == 0)
//                    velMoveBarrera++;
//            }
        }
        for (Rect barrera : alBot) {
            barrera.left -= velMoveBarrera;
            barrera.right -= velMoveBarrera;

        }
    }

    public void creaBarrera() {
        randPointY = (int) (Math.random() * ((altoPantalla - partePantalla(altoPantalla, 7)) * +1));
        randLocY = randPointY + partePantalla(altoPantalla, 5);

        barreraTop = new Rect(anchoPantalla, 0, partePantalla(anchoPantalla, 10) * 12, randPointY);
        alBarrerasTop.add(barreraTop);

        barreraBot = new Rect(anchoPantalla, randLocY, partePantalla(anchoPantalla, 10) * 12, altoPantalla);
        alBarrerasBot.add(barreraBot);
    }

    public void actualizarFisica() {
        cambiaImagen();
        mueveBarrera(alBarrerasTop, alBarrerasBot);
    }

    public void dibujar(Canvas c) {
        if (contCreacion == 0 || (contCreacion % 80 == 0)) {
            creaBarrera();
        }
        contCreacion++;
        for (Rect barrera : alBarrerasTop) {
//            c.drawRect(barrera, pincel);
            c.drawBitmap(skins[indice], barrera.left, barrera.bottom - skins[indice].getHeight(), null);
        }
        for (Rect barrera : alBarrerasBot) {
//            c.drawRect(barrera, pincel);
            c.drawBitmap(skins[indice], barrera.left, barrera.top, null);
        }

    }

    public void cambiaImagen() {
        if (System.currentTimeMillis() - tiempoFrameAux > tiempoFrame) {
            indice++;
            if (indice >= skins.length) indice = 0;
            tiempoFrameAux = System.currentTimeMillis();
        }
    }

    public int partePantalla(int regionpantalla, int fraccion) {
        return regionpantalla / fraccion;
    }
}
