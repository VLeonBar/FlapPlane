package com.example.vleon.profinalmovil;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

public class Nave {
    private Rect rect;
    private Bitmap[] skins;
    private int posX, posY;
    private int cont = 0;
    int tiempoFrame=80;
    long tiempoFrameAux =0;
    int indice=0,velocidad;
    private int altoPantalla,partePantalla;

    public Bitmap[] getSkins() {
        return skins;
    }

    public Rect getRect() {
        return rect;
    }

    public void setSkins(Bitmap[] skins) {
        this.skins = skins;
        this.rect.top = posY;
        this.rect.bottom = posY + skins[indice].getHeight();
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
        this.rect.top = posY;
    }

    public Nave(int posX, int posY, Bitmap[] skins,int altoPantalla) {
        this.altoPantalla=altoPantalla;
        this.posX = posX;
        this.posY = posY;
        this.rect = new Rect(posX, posY, posX + skins[indice].getWidth(), posY + skins[0].getHeight());
        this.skins = skins;
    }

    public void actualizarFisica(boolean sube,int velocidad) {
        cambiaImagen();
        if (sube) {
            this.mueveNave(this.getPosY() - velocidad);
            this.cambiaImagen();
        } else {
            this.mueveNave(this.getPosY() + velocidad);
            this.cambiaImagen();
        }
    }

    public void dibujar(Canvas c) {
        c.drawBitmap(skins[indice],this.getPosX(),this.getPosY(),null);
    }
    public boolean choqueNave(ArrayList<Rect> top, ArrayList<Rect> bot) {
        for (Rect barrera : top) {
            if (this.getRect().intersect(barrera)) {
                return true;
            }
        }
        for (Rect barrera : bot) {
            if (this.getRect().intersect(barrera)) {
                return true;
            }
        }
        return false;
    }
    public void mueveNave(int posY) {
        if (posY >= altoPantalla - this.getSkins()[indice].getHeight()) {
            posY = altoPantalla - this.getSkins()[indice].getHeight();
        } else if (posY <= 0) {
            posY = 0;
        }
        this.setPosY(posY);
        this.getRect().bottom = posY + this.getSkins()[indice].getHeight();
    }
    public void cambiaImagen(){
        if (System.currentTimeMillis()- tiempoFrameAux >tiempoFrame) {
            indice++;
            if (indice>= skins.length)indice=0;
            tiempoFrameAux =System.currentTimeMillis();
        }
    }
}
