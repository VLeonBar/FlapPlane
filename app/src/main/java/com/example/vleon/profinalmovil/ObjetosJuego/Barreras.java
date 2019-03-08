package com.example.vleon.profinalmovil.ObjetosJuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * The type Barreras.
 */
public class Barreras extends Objetos {
    /**
     * The Al barreras top.
     */
    ArrayList<Rect> alBarrerasTop = new ArrayList<>();
    /**
     * The Al barreras bot.
     */
    ArrayList<Rect> alBarrerasBot = new ArrayList<>();
    /**
     * The Barrera top.
     */
    Rect barreraTop, /**
     * The Barrera bot.
     */
    barreraBot;
    /**
     * The Moneda.
     */
    Moneda moneda;
    /**
     * The Rand point y.
     */
    int randPointY, /**
     * The Rand loc y.
     */
    randLocY;

    /**
     * Devuelve al barreras top.
     *
     * @return the al barreras top
     */
    public ArrayList<Rect> getAlBarrerasTop() {
        return alBarrerasTop;
    }

    /**
     * Devuelve al barreras bot.
     *
     * @return the al barreras bot
     */
    public ArrayList<Rect> getAlBarrerasBot() {
        return alBarrerasBot;
    }


    /**
     * Instantiates a new Barreras.
     *
     * @param contexto      the contexto
     * @param anchoPantalla the ancho pantalla
     * @param altoPantalla  the alto pantalla
     * @param skins         the skins
     */
    public Barreras(Context contexto, int anchoPantalla, int altoPantalla, Bitmap[] skins) {
        super(contexto, anchoPantalla, altoPantalla, skins);
        velocidad = 10;
    }

    /**
     * Mueve barrera.
     *
     * @param alTop the al top
     * @param alBot the al bot
     */
    public void mueveBarrera(ArrayList<Rect> alTop, ArrayList<Rect> alBot) {
        for (Rect barrera : alTop) {
            barrera.left -= velocidad;
            barrera.right -= velocidad;
        }
        for (Rect barrera : alBot) {
            barrera.left -= velocidad;
            barrera.right -= velocidad;
        }
    }

    /**
     * Crea barrera.
     */
    public void creaBarrera() {
        randPointY = (int) (Math.random() * ((altoPantalla - fh.partePantalla(altoPantalla, 7)) * +1));
        randLocY = randPointY + fh.partePantalla(altoPantalla, 5);

        barreraTop = new Rect(anchoPantalla, 0, fh.partePantalla(anchoPantalla, 10) * 12, randPointY);
        alBarrerasTop.add(barreraTop);

        barreraBot = new Rect(anchoPantalla, randLocY, fh.partePantalla(anchoPantalla, 10) * 12, altoPantalla);
        alBarrerasBot.add(barreraBot);
    }

    /**
     * Actualizar fisica.
     */
    public void actualizarFisica() {
        cambiaImagen();
        mueveBarrera(alBarrerasTop, alBarrerasBot);
        if (cont % 80 == 0) {
            creaBarrera();
        }
        cont++;
    }

    /**
     * Dibujar.
     *
     * @param c el Canvas
     */
    public void dibujar(Canvas c) {

        for (int i = 0; i < alBarrerasTop.size(); i++) {
            if (alBarrerasTop.get(i).right < -20) {
                alBarrerasTop.remove(i);
                alBarrerasBot.remove(i);
                puntuacion++;
            }
            c.drawBitmap(skins[indice], alBarrerasTop.get(i).left, alBarrerasTop.get(i).bottom - skins[indice].getHeight(), null);
            c.drawBitmap(skins[indice], alBarrerasBot.get(i).left, alBarrerasBot.get(i).top, null);
            //todo
        }
    }
}
