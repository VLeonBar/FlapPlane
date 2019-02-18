package com.example.vleon.profinalmovil.Objetos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.vleon.profinalmovil.Manejadores.Sonidos;

import java.util.ArrayList;

public class Nave extends Objetos {
    private Rect rect;
    private Bitmap[] skins;
    Sonidos sonidos;
    int puntuacion = 0;

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

    public Nave(Context contexto, int anchoPantalla, int altoPantalla, Bitmap[] skins, int posX, int posY) {
        super(contexto, anchoPantalla, altoPantalla, skins);
        this.posX = posX;
        this.posY = posY;
        velocidad = fh.partePantalla(altoPantalla, 100);
        sonidos = new Sonidos(contexto, 10);
        this.rect = new Rect(posX, posY, posX + skins[indice].getWidth(), posY + skins[0].getHeight());
    }

    public void actualizarFisica(boolean sube) {
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
//        for (Rect barrera : top) {
//            if (this.getRect().intersect(barrera)) {
//                sonidos.getEfectos().play(sonidos.sonidoExplosion,1,1,1,0,1);
//                return true;
//            }
//        }
//        for (Rect barrera : bot) {
//            if (this.getRect().intersect(barrera)) {
//                sonidos.getEfectos().play(sonidos.sonidoExplosion,1,1,1,0,1);
//                return true;
//            }
//        }
        if (monedas != null) {
            for (int i = 0; i < monedas.size(); i++) {
                if (this.getRect().intersect(monedas.get(i))) {
                    this.puntuacion++;
                    monedas.remove(monedas.get(i));
                    sonidos.getEfectos().play(sonidos.sonidoInsertCoin, 1, 1, 1, 0, 1);
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
}
