package com.example.vleon.profinalmovil.ObjetosJuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

/**
 * La clase que gestiona las barreras.
 */
public class Barreras extends Objetos {
    /**
     * La coleccion de barreras de la parte superior.
     */
    ArrayList<Rect> alBarrerasTop = new ArrayList<>();
    /**
     * La coleccion de barreras de la parte superior.
     */
    ArrayList<Rect> alBarrerasBot = new ArrayList<>();
    /**
     * La Barrera de la parte superior.
     */
    Rect barreraTop, /**
     * La Barrera de la parte inferior.
     */
    barreraBot;
    /**
     * La Moneda.
     */
    Moneda moneda;
    /**
     * El punto aleatorio hasta dónde se dibujará la barrera superior.
     */
    int randPointY, /**
     * La localización aleatoria dónde se empezará a dibujar la barrera inferior.
     */
    randLocY;

    /**
     * Devuelve la coleccon de barreras superior.
     *
     * @return la coleccon de barreras superior
     */
    public ArrayList<Rect> getAlBarrerasTop() {
        return alBarrerasTop;
    }

    /**
     * Devuelve la coleccon de barreras inferior.
     *
     * @return la coleccon de barreras inferior
     */
    public ArrayList<Rect> getAlBarrerasBot() {
        return alBarrerasBot;
    }


    /**
     * Instancia un nuevo objeto de la clase Barreras.
     *
     * @param contexto      el contexto
     * @param anchoPantalla el ancho pantalla
     * @param altoPantalla  el alto pantalla
     * @param skins         las imagenes
     */
    public Barreras(Context contexto, int anchoPantalla, int altoPantalla, Bitmap[] skins) {
        super(contexto, anchoPantalla, altoPantalla, skins);
        velocidad = 10;
    }

    /**
     * Mueve las barreras.
     *
     * @param alTop la coleccion de barreras superior
     * @param alBot la coleccion de barreras inferior
     */
    public void mueveBarrera(ArrayList<Rect> alTop, ArrayList<Rect> alBot) {
        for (Rect barrera : alTop) {
            barrera.left -= fh.getDpH(velocidad, altoPantalla);
            barrera.right -= fh.getDpH(velocidad, altoPantalla);
        }
        for (Rect barrera : alBot) {
            barrera.left -= fh.getDpH(velocidad, altoPantalla);
            barrera.right -= fh.getDpH(velocidad, altoPantalla);
        }
    }

    /**
     * Crea las barreras.
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
        }
    }
}
