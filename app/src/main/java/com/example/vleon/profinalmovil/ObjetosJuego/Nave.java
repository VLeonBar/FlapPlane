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

/**
 * La clase que gestiona el movimiento y las fisicas de la Nave.
 */
public class Nave extends Objetos {

    /**
     * Instancia un nuevo objeto de la clase Nave.
     *
     * @param contexto      el contexto
     * @param anchoPantalla el ancho pantalla
     * @param altoPantalla  el alto pantalla
     * @param skins         las imagenes
     * @param posX          la posicion en el eje x
     * @param posY          la posicion en el eje y
     */
    public Nave(Context contexto, int anchoPantalla, int altoPantalla, Bitmap[] skins, int posX, int posY) {
        super(contexto, anchoPantalla, altoPantalla, skins);
        this.setPosX(posX);
        this.setPosY(posY);
        sonidos = new Sonidos(contexto, 10);
        rect = new Rect(posX, posY, posX + skins[indice].getWidth(), posY + skins[0].getHeight());
        pincel.setColor(Color.BLACK);
        pincel.setTextSize(fh.partePantalla(anchoPantalla, 10));
    }

    /**
     * Actualizar fisica, actualiza la posicion de la nave.
     *
     * @param sube      indica si la nave sube
     * @param velocidad indica la velocidad de la nave
     */
    public void actualizarFisica(boolean sube, int velocidad) {

        if (sube) {
            this.cambiaImagen();
            this.mueveNave(this.getPosY() - fh.getDpH(velocidad, altoPantalla));
        } else {
            indice = 0;
            this.mueveNave(this.getPosY() + fh.getDpH(velocidad, altoPantalla));
        }
    }

    /**
     * Dibuja la nave.
     *
     * @param c    el Canvas
     * @param sube indica si la nave sube o baja
     */
    public void dibujar(Canvas c, boolean sube) {
        if (sube) {
            setSkins(fh.getFrames(2, "aviones", "sube", fh.partePantalla(anchoPantalla, 10)));
        } else {
            setSkins(fh.getFrames(1, "aviones", "baja", fh.partePantalla(anchoPantalla, 10)));
        }
        c.drawBitmap(skins[indice], this.getPosX(), this.getPosY(), null);
        c.drawText("" + puntuacion, fh.partePantalla(anchoPantalla, 8) * 4, fh.partePantalla(altoPantalla, 15), pincel);
    }

    /**
     * Indica el choque de la nave.
     *
     * @param top     la coleccion de barreras de la parte superior
     * @param bot     la coleccion de barreras de la parte inferior
     * @param monedas la coleccion de monedas
     * @return boolean true si la nave choca contra una barrera, false si lo contrario
     */
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

    /**
     * Mueve nave.
     *
     * @param posY la posicion en el eje y
     */
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