package com.example.vleon.profinalmovil.Objetos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

public class Moneda extends Objetos {
    ArrayList<Rect> alMonedas;
    Rect rect;
    private int randPointY;

    public Moneda(Context contexto, int anchoPantalla, int altoPantalla, Bitmap[] skins) {
        super(contexto, anchoPantalla, altoPantalla, skins);
        alMonedas = new ArrayList<>();
    }

    public ArrayList<Rect> getAlMonedas() {
        return alMonedas;
    }

    public void setAlMonedas(ArrayList<Rect> alMonedas) {
        this.alMonedas = alMonedas;
    }

    public void creaMoneda(ArrayList<Rect> alBarreras) {
        randPointY = (int) (Math.random() * ((altoPantalla - fh.partePantalla(altoPantalla, 7)) * +1));
        posY = randPointY + fh.partePantalla(altoPantalla, 10);
        for (Rect barrera : alBarreras) {
            if (barrera.left < fh.partePantalla(anchoPantalla, 2)) {
                if ((int) (Math.random() * 6 + 1) == 1) {
                    rect = new Rect(anchoPantalla, randPointY, anchoPantalla + skins[indice].getWidth(), randPointY + skins[indice].getHeight());
                }
            }
        }
        alMonedas.add(rect);
    }

    public void mueveMoneda() {
        for (Rect moneda : alMonedas) {
            moneda.left -= velocidad;
            moneda.right -= velocidad;
        }

    }

    public void actualizarFisica(ArrayList<Rect> alBarreras) {
        creaMoneda(alBarreras);
        mueveMoneda();
    }

    public void dibujar(Canvas c) {
        c.drawRect(rect, pincel);
        c.drawBitmap(skins[indice], rect.left, rect.top, null);
    }
}

/*
cambiaImagenMoneda(skinsMoneda);
 if (contCreaMoneda % 36 == 0 && (int) (Math.random() * 6 + 1) == 1) {
            creaMoneda();
        }
                contCreaMoneda++;
public void cambiaImagenMoneda(Bitmap[] skins) {
        if (System.currentTimeMillis() - tiempoFrameAuxMoneda > tiempoFrameMoneda) {
            indicemoneda++;
            if (indicemoneda >= skins.length) indicemoneda = 0;
            tiempoFrameAuxMoneda = System.currentTimeMillis();
        }
    }
for (Rect moneda : alMonedas) {
        if (moneda.intersect(alBarrerasBot.get(i)) || moneda.intersect(alBarrerasTop.get(i))) {
        Log.i("MONEDA", "NO PINTO MONEDA");
        } else {
        Log.i("MONEDA", "PINTO MONEDA");
//                    c.drawRect(moneda, pincel);
        c.drawBitmap(skinsMoneda[indicemoneda], moneda.left, moneda.top, null);
        }
        }*/
