package com.example.vleon.profinalmovil.ObjetosJuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;

import com.example.vleon.profinalmovil.Manejadores.Sonidos;

import java.util.ArrayList;

public class Nave extends Objetos {
    int puntuacion;
    Matrix matrix = new Matrix();

    public Nave(Context contexto, int anchoPantalla, int altoPantalla, Bitmap[] skins, int posX, int posY) {
        super(contexto, anchoPantalla, altoPantalla, skins);
        this.setPosX(posX);
        this.setPosY(posY);
        puntuacion = 0;
        velocidad = fh.partePantalla(altoPantalla, 100);
        sonidos = new Sonidos(contexto, 10);
        rect = new Rect(posX, posY, posX + skins[indice].getWidth(), posY + skins[0].getHeight());
        pincel.setColor(Color.BLACK);
        pincel.setTextSize(fh.partePantalla(anchoPantalla, 10));
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

    public void dibujar(Canvas c, boolean sube) {
        if (sube) {
//            c.save(Canvas.MATRIX_SAVE_FLAG); //Saving the canvas and later restoring it so only this image will be rotated.
//            c.rotate(-40);
//            c.drawBitmap(skins[indice], this.getPosX(), this.getPosY(), null);
//            c.restore();
        } else {
//            c.save(Canvas.MATRIX_SAVE_FLAG); //Saving the canvas and later restoring it so only this image will be rotated.
//            c.rotate(40);
//            c.drawBitmap(skins[indice], posX, posY, null);
//            c.restore();
        }
        c.drawRect(rect, pincel);
        c.drawBitmap(skins[indice], this.getPosX(), this.getPosY(), null);
        c.drawText("" + puntuacion, fh.partePantalla(anchoPantalla, 2), fh.partePantalla(altoPantalla, 10), pincel);
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public boolean choqueNave(ArrayList<Rect> top, ArrayList<Rect> bot, ArrayList<Rect> monedas) {
        for (Rect barrera : top) {
            if (rect.intersect(barrera)) {
                sonidos.getEfectos().play(sonidos.sonidoExplosion, 1, 1, 1, 0, 1);
                return true;
            }
        }
        for (Rect barrera : bot) {
            if (rect.intersect(barrera)) {
                sonidos.getEfectos().play(sonidos.sonidoExplosion, 1, 1, 1, 0, 1);
                return true;
            }
        }
        for (int i = 0; i < monedas.size(); i++) {
            if (rect.intersect(monedas.get(i))) {
                puntuacion++;
                monedas.remove(monedas.get(i));
                sonidos.getEfectos().play(sonidos.sonidoInsertCoin, 1, 1, 1, 0, 1);
            }
        }
        return false;
    }

    public void mueveNave(int posY) {
        if (posY >= altoPantalla - skins[indice].getHeight()) {
            posY = altoPantalla - skins[indice].getHeight();
        } else if (posY <= 0) {
            posY = 0;
        }
        this.setPosY(posY);
        rect.top = posY;
        rect.bottom = posY + skins[indice].getHeight();
    }
}
