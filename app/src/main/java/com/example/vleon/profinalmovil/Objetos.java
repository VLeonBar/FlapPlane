package com.example.vleon.profinalmovil;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;

public class Objetos {
    private Bitmap[] skins;
    private int posX, posY;
    private int cont = 0;
    private int altoPantalla;
    Paint pincel;

    public Bitmap[] getSkins() {
        return skins;
    }

    public void setSkins(Bitmap[] skins) {
        this.skins = skins;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int cambiaImagen(int tiempoFrame) {
        long tiempoFrameAux = 0;
        int indice = 0;
        if (System.currentTimeMillis() - tiempoFrameAux > tiempoFrame) {
            indice++;
            if (indice >= skins.length) indice = 0;
            tiempoFrameAux = System.currentTimeMillis();
        }
        return indice;
    }
}
