package com.example.vleon.profinalmovil.ObjetosJuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.vleon.profinalmovil.Manejadores.FrameHandler;
import com.example.vleon.profinalmovil.Manejadores.Sonidos;

/**
 * The type Objetos.
 */
public class Objetos {
    /**
     * Las imagenes.
     */
    Bitmap[] skins;
    /**
     * El Rectangulo que ocupa el objeto.
     */
    Rect rect;
    /**
     * Objeto de la clase Sonidos.
     */
    Sonidos sonidos;
    /**
     * La Posicion en el eje x.
     */
    int posX, /**
     * La Posicion en el eje y.
     */
    posY;
    /**
     * El Cont.
     */
    int cont, /**
     * El Indice.
     */
    indice;
    /**
     * La Velocidad.
     */
    int velocidad;
    /**
     * El Tiempo frame, utilizado para el contro temporal de los sprites.
     */
    int tiempoFrame;
    /**
     * El Tiempo frame auxiliar, utilizado para el contro temporal de los sprites.
     */
    long tiempoFrameAux;
    /**
     * El Alto pantalla.
     */
    int altoPantalla, /**
     * El Ancho pantalla.
     */
    anchoPantalla;
    /**
     * El Contexto.
     */
    Context contexto;
    /**
     * Objeto de la clase FrameHandler
     */
    FrameHandler fh;
    /**
     * el Pincel.
     */
    Paint pincel;
    /**
     * La puntuacion.
     */
    public static int puntuacion;

    /**
     * Instancia un nuevo objeto de la clase Objetos.
     *
     * @param contexto      el contexto
     * @param anchoPantalla el ancho pantalla
     * @param altoPantalla  el alto pantalla
     * @param skins         las imagenes
     */
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
        this.puntuacion = 0;
        this.altoPantalla = altoPantalla;
        this.anchoPantalla = anchoPantalla;
        this.contexto = contexto;
        this.pincel = new Paint();
    }

    /**
     * Cambia imagen.
     */
    public void cambiaImagen() {
        if (System.currentTimeMillis() - tiempoFrameAux > tiempoFrame) {
            indice++;
            if (indice >= skins.length) indice = 0;
            tiempoFrameAux = System.currentTimeMillis();
        }
    }

    /**
     * Cambia indice int.
     *
     * @param tiempoFrame the tiempo frame
     * @return the int
     */
    @Deprecated
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

    /**
     * Da un valor a skins.
     *
     * @param skins the skins
     */
    public void setSkins(Bitmap[] skins) {
        this.skins = skins;
        this.rect.top = posY;
        this.rect.bottom = posY + skins[indice].getHeight();
    }

    /**
     * Devuelve pos x.
     *
     * @return the pos x
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Da un valor a pos x.
     *
     * @param posX the pos x
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Devuelve pos y.
     *
     * @return the pos y
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Da un valor a pos y.
     *
     * @param posY the pos y
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

}
