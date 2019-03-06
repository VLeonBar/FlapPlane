package com.example.vleon.profinalmovil.ObjetosJuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;

import com.example.vleon.profinalmovil.Manejadores.Sonidos;
import com.example.vleon.profinalmovil.Pantallas.Escena;
import com.example.vleon.profinalmovil.R;

import java.util.ArrayList;

public class Nave extends Objetos {
    Matrix matrix = new Matrix();
    boolean isSoundOn;
    Bitmap vueltaAtras;


    public Nave(Context contexto, int anchoPantalla, int altoPantalla, Bitmap[] skins, int posX, int posY) {
        super(contexto, anchoPantalla, altoPantalla, skins);
        vueltaAtras = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.moneda);
        vueltaAtras = Bitmap.createScaledBitmap(vueltaAtras, fh.partePantalla(anchoPantalla, 10), fh.partePantalla(anchoPantalla, 10), false);
        this.setPosX(posX);
        this.setPosY(posY);
        sonidos = new Sonidos(contexto, 10);
        rect = new Rect(posX, posY, posX + skins[indice].getWidth(), posY + skins[0].getHeight());
        pincel.setColor(Color.BLACK);
        pincel.setTextSize(fh.partePantalla(anchoPantalla, 10));
    }

    public void actualizarFisica(boolean sube, int velocidad) {

        if (sube) {
            this.cambiaImagen();
            this.mueveNave(this.getPosY() - fh.getDpH(velocidad, altoPantalla));
        } else {
            indice = 0;
            this.mueveNave(this.getPosY() + fh.getDpH(velocidad, altoPantalla));
        }
    }

    public void dibujar(Canvas c, boolean sube) {
        if (sube) {
            setSkins(fh.getFrames(2, "aviones", "sube", fh.partePantalla(anchoPantalla, 10)));
//            c.save(Canvas.MATRIX_SAVE_FLAG); //Saving the canvas and later restoring it so only this image will be rotated.
//            c.rotate(-40);
//            c.drawBitmap(skins[indice], this.getPosX(), this.getPosY(), null);
//            c.restore();
        } else {
            setSkins(fh.getFrames(1, "aviones", "baja", fh.partePantalla(anchoPantalla, 10)));
//            c.save(Canvas.MATRIX_SAVE_FLAG); //Saving the canvas and later restoring it so only this image will be rotated.
//            c.rotate(40);
//            c.drawBitmap(skins[indice], posX, posY, null);
//            c.restore();
        }
        c.drawRect(rect, pincel);
        c.drawBitmap(skins[indice], this.getPosX(), this.getPosY(), null);
        c.drawText("" + puntuacion, fh.partePantalla(anchoPantalla, 8) * 4, fh.partePantalla(altoPantalla, 15) + vueltaAtras.getHeight(), pincel);
    }

    public boolean choqueNave(ArrayList<Rect> top, ArrayList<Rect> bot, ArrayList<Rect> monedas) {
        for (Rect barrera : top) {
            if (rect.intersect(barrera)) {
                return true;
            }
        }
        for (Rect barrera : bot) {
            if (rect.intersect(barrera)) {
                return true;
            }
        }
        for (int i = 0; i < monedas.size(); i++) {
            if (rect.intersect(monedas.get(i))) {
                puntuacion += 10;
                monedas.remove(monedas.get(i));
                rect = new Rect(posX, posY, posX + skins[indice].getWidth(), posY + skins[0].getHeight());
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