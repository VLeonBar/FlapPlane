package com.example.vleon.profinalmovil;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

public class Barreras {
    ArrayList<Rect> alBarrerasTop = new ArrayList<>();
    ArrayList<Rect> alBarrerasBot = new ArrayList<>();
    ArrayList<Rect> alMonedas = new ArrayList<>();
    Bitmap[] skins, skinsMoneda;
    Rect barreraTop, barreraBot, moneda;
    int altoPantalla, anchoPantalla;
    int randPointY, randLocY, contCreaBarrera = 0, contCreaMoneda = 0;
    int velMoveBarrera = 10;
    int tiempoFrame = 80;
    long tiempoFrameAux = 0;
    int indice = 0;
    boolean pintaMoneda = false;
    Paint pincel;

    public ArrayList<Rect> getAlBarrerasTop() {
        return alBarrerasTop;
    }

    public ArrayList<Rect> getAlBarrerasBot() {
        return alBarrerasBot;
    }


    public Barreras(int altoPantalla, int anchoPantalla, Bitmap[] skins, Bitmap[] skinsMoneda) {
        this.altoPantalla = altoPantalla;
        this.anchoPantalla = anchoPantalla;
        this.skins = skins;
        this.skinsMoneda = skinsMoneda;
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
        for (Rect moneda : alMonedas) {
            moneda.left -= velMoveBarrera;
            moneda.right -= velMoveBarrera;
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

    public void creaMoneda() {
        randPointY = (int) (Math.random() * ((altoPantalla - partePantalla(altoPantalla, 7)) * +1));
        randLocY = randPointY + partePantalla(altoPantalla, 10);

        moneda = new Rect(anchoPantalla, randPointY, anchoPantalla + skinsMoneda[indice].getWidth(), randPointY + skinsMoneda[indice].getHeight());
        alMonedas.add(moneda);
    }

    public void actualizarFisica() {
        cambiaImagen(skins);
        cambiaImagen(skinsMoneda);
        mueveBarrera(alBarrerasTop, alBarrerasBot);
    }

    public ArrayList<Rect> getAlMonedas() {
        return alMonedas;
    }

    public void dibujar(Canvas c) {
        if (contCreaBarrera % 80 == 0) {
            creaBarrera();
        }
        if (contCreaMoneda % 36 == 0 && (int) (Math.random() * 6 + 1) == 1) {
            creaMoneda();
        }
        contCreaBarrera++;
        contCreaMoneda++;
        for (int i = 0; i < alBarrerasTop.size(); i++) {
            c.drawBitmap(skins[indice], alBarrerasTop.get(i).left, alBarrerasTop.get(i).bottom - skins[indice].getHeight(), null);
            c.drawBitmap(skins[indice], alBarrerasBot.get(i).left, alBarrerasBot.get(i).top, null);
            //todo
            for (Rect moneda : alMonedas) {
                if (moneda.intersect(alBarrerasBot.get(i)) && moneda.intersect(alBarrerasTop.get(i))) {
                    Log.i("MONEDA", "NO PINTO MONEDA");
                } else {
                    Log.i("MONEDA", "PINTO MONEDA");
                    c.drawRect(moneda, pincel);
                    c.drawBitmap(skinsMoneda[indice], moneda.left, moneda.top, null);
                }
            }
        }
    }

    public void cambiaImagen(Bitmap[] skins) {
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
