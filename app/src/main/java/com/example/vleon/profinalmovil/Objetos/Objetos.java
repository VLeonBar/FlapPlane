package com.example.vleon.profinalmovil.Objetos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.vleon.profinalmovil.Manejadores.FrameHandler;

public class Objetos {
    Bitmap[] skins;
    int posX, posY;
    int cont, indice;
    int velocidad;
    int tiempoFrame;
    long tiempoFrameAux;
    int altoPantalla, anchoPantalla;
    Context contexto;
    FrameHandler fh;
    Paint pincel;


    public Objetos(Context contexto, int anchoPantalla, int altoPantalla, Bitmap[] skins) {
        this.fh = new FrameHandler(contexto);
        this.skins = skins;
        this.posX = 0;
        this.posY = 0;
        this.cont = 0;
        this.indice = 0;
        this.velocidad = 0;
        this.tiempoFrame = 80;
        this.tiempoFrameAux = 0;
        this.altoPantalla = altoPantalla;
        this.anchoPantalla = anchoPantalla;
        this.contexto = contexto;
        this.pincel = new Paint();
    }

    public void cambiaImagen() {
        if (System.currentTimeMillis() - tiempoFrameAux > tiempoFrame) {
            indice++;
            if (indice >= skins.length) indice = 0;
            tiempoFrameAux = System.currentTimeMillis();
        }
    }

    public int cambiaIndice(int tiempoFrame) {
        long tiempoFrameAux = 0;
        int indice = 0;
        if (System.currentTimeMillis() - tiempoFrameAux > tiempoFrame) {
            indice++;
            if (indice >= skins.length) indice = 0;
            tiempoFrameAux = System.currentTimeMillis();
        }
        return indice;
    }

    public int getTiempoFrame() {
        return tiempoFrame;
    }

    public void setTiempoFrame(int tiempoFrame) {
        this.tiempoFrame = tiempoFrame;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public long getTiempoFrameAux() {
        return tiempoFrameAux;
    }

    public void setTiempoFrameAux(long tiempoFrameAux) {
        this.tiempoFrameAux = tiempoFrameAux;
    }

    public Paint getPincel() {
        return pincel;
    }

    public void setPincel(Paint pincel) {
        this.pincel = pincel;
    }

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

}
