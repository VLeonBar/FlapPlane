package com.example.vleon.profinalmovil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

public class Nave {
    private Rect rect;
    private Bitmap[] skins;
    Sonidos sonidos;
    private int posX, posY, puntuacion = 0;
    int tiempoFrame = 80;
    long tiempoFrameAux = 0;
    int indice = 0, velocidad;
    private int altoPantalla, partePantalla;

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

    public Nave(int posX, int posY, Bitmap[] skins, int altoPantalla, Context contexto) {
        sonidos=new Sonidos(contexto,10);
        this.altoPantalla = altoPantalla;
        this.posX = posX;
        this.posY = posY;
        this.rect = new Rect(posX, posY, posX + skins[indice].getWidth(), posY + skins[0].getHeight());
        this.skins = skins;
    }

    public void actualizarFisica(boolean sube, int velocidad) {
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
        c.drawBitmap(skins[indice], this.getPosX(), this.getPosY(), null);
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public boolean choqueNave(ArrayList<Rect> top, ArrayList<Rect> bot, ArrayList<Rect> monedas) {
        for (Rect barrera : top) {
            if (this.getRect().intersect(barrera)) {
                sonidos.getEfectos().play(sonidos.sonidoExplosion,1,1,1,0,1);
                return true;
            }
        }
        for (Rect barrera : bot) {
            if (this.getRect().intersect(barrera)) {
                sonidos.getEfectos().play(sonidos.sonidoExplosion,1,1,1,0,1);
                return true;
            }
        }
        if (!monedas.isEmpty()) {
            for (int i = 0; i < monedas.size(); i++) {
                if (this.getRect().intersect(monedas.get(i))) {
                    this.puntuacion++;
                    monedas.remove(monedas.get(i));
                    sonidos.getEfectos().play(sonidos.sonidoInsertCoin,1,1,1,0,1);
                }
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

    public void cambiaImagen() {
        if (System.currentTimeMillis() - tiempoFrameAux > tiempoFrame) {
            indice++;
            if (indice >= skins.length) indice = 0;
            tiempoFrameAux = System.currentTimeMillis();
        }
    }
}
