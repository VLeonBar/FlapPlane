package com.example.vleon.profinalmovil.ObjetosJuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

public class Moneda extends Objetos {
    ArrayList<Rect> alMonedas = new ArrayList<>();
    Rect rect;
    private int randPointY;
    private boolean flag;

    public Moneda(Context contexto, int anchoPantalla, int altoPantalla, Bitmap[] skins) {
        super(contexto, anchoPantalla, altoPantalla, skins);
        velocidad = 10;
    }

    public ArrayList<Rect> getAlMonedas() {
        return alMonedas;
    }

    public void creaMoneda() {
        randPointY = (int) (Math.random() * ((altoPantalla - fh.partePantalla(altoPantalla, 7)) * +1));
        posY = randPointY + fh.partePantalla(altoPantalla, 10);
        posX = anchoPantalla + fh.partePantalla(anchoPantalla, 2);
        if ((int) (Math.random() * 3 + 1) == 1) {
            rect = new Rect(posX, randPointY, posX + skins[0].getWidth(), randPointY + skins[0].getHeight());
            alMonedas.add(rect);
        }
    }

    public void mueveMoneda() {
        if (!alMonedas.isEmpty()) {
            for (Rect moneda : alMonedas) {
                moneda.left -= velocidad;
                moneda.right -= velocidad;
            }
        }
    }


    public void actualizarFisica() {
        cambiaImagen();
        if (cont % 80 == 0) {
            creaMoneda();
        }
        cont++;
        if (!alMonedas.isEmpty()) {
            mueveMoneda();
        }
    }

    public void dibujar(Canvas c) {
        for (Rect moneda : alMonedas) {
            c.drawRect(moneda, pincel);
            c.drawBitmap(skins[indice], moneda.left, moneda.top, null);
        }
        Log.i("indice", "" + indice);
    }
}