package com.example.vleon.profinalmovil.ObjetosJuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

/**
 * La clase Moneda.
 */
public class Moneda extends Objetos {
    /**
     * La colecicon de monedas.
     */
    ArrayList<Rect> alMonedas = new ArrayList<>();
    /**
     * El rectangulo de la moneda.
     */
    Rect rect;
    /**
     * El punto en el eje Y aleatorio dónde se empieza a dibujar la moneda.
     */
    private int randPointY;

    /**
     * Instancia un nuevo objeto de la clase Moneda.
     *
     * @param contexto      the contexto
     * @param anchoPantalla the ancho pantalla
     * @param altoPantalla  the alto pantalla
     * @param skins         the skins
     */
    public Moneda(Context contexto, int anchoPantalla, int altoPantalla, Bitmap[] skins) {
        super(contexto, anchoPantalla, altoPantalla, skins);
        velocidad = 10;
    }

    /**
     * Devuelve la coleccion de monedas.
     *
     * @return the al monedas
     */
    public ArrayList<Rect> getAlMonedas() {
        return alMonedas;
    }

    /**
     * Crea la moneda.
     */
    public void creaMoneda() {
        randPointY = (int) (Math.random() * ((altoPantalla - fh.partePantalla(altoPantalla, 7)) * +1));
        posY = randPointY + fh.partePantalla(altoPantalla, 10);
        posX = anchoPantalla + fh.partePantalla(anchoPantalla, 2);
        if ((int) (Math.random() * 3 + 1) == 1) {
            rect = new Rect(posX, randPointY, posX + skins[0].getWidth(), randPointY + skins[0].getHeight());
            alMonedas.add(rect);
        }
    }

    /**
     * Mueve la moneda.
     */
    public void mueveMoneda() {
        if (!alMonedas.isEmpty()) {
            for (Rect moneda : alMonedas) {
                moneda.left -= velocidad;
                moneda.right -= velocidad;
            }
        }
    }


    /**
     * Actualizar fisica, gira la imagen de la moneda y la mueve.
     */
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

    /**
     * Dibuja la moneda.
     *
     * @param c el Canvas
     */
    public void dibujar(Canvas c) {
        for (Rect moneda : alMonedas) {
            c.drawBitmap(skins[indice], moneda.left, moneda.top, null);
        }
    }
}