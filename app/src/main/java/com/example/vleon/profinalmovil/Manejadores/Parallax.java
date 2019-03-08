package com.example.vleon.profinalmovil.Manejadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.vleon.profinalmovil.ObjetosJuego.Fondo;

import java.util.ArrayList;

/**
 * The type Parallax.
 */
public class Parallax {

    /**
     * The Fondos.
     */
    ArrayList<Fondo> fondos = new ArrayList<>();
    /**
     * The Fh.
     */
    FrameHandler fh;
    /**
     * The Anchopantalla.
     */
    int anchopantalla, /**
     * The Altopantalla.
     */
    altopantalla;

    /**
     * Instantiates a new Parallax.
     *
     * @param contexto          the contexto
     * @param anchoPantalla     the ancho pantalla
     * @param altoPantalla      the alto pantalla
     * @param cantImagenesFondo the cant imagenes fondo
     */
    public Parallax(Context contexto, int anchoPantalla, int altoPantalla, int cantImagenesFondo) {
        this.anchopantalla = anchoPantalla;
        this.altopantalla = altoPantalla;
        fh = new FrameHandler(contexto);
        creaFondos(fh.getFrames(cantImagenesFondo, "fondos", "fondo", altoPantalla));
    }

    /**
     * Crea fondos array list.
     *
     * @param imagenes the imagenes
     * @return the array list
     */
    public ArrayList<Fondo> creaFondos(Bitmap[] imagenes) {
        this.fondos.add((new Fondo(imagenes[0], 0, 0)));
        this.fondos.add((new Fondo(imagenes[1], 0, altopantalla / 2)));
        this.fondos.add((new Fondo(imagenes[2], 0, 0)));
        return this.fondos;
    }

    /**
     * Actualizar fisica.
     */
    public void actualizarFisica() {
        for (Fondo fondo : fondos)
            fondo.mover((int)(Math.random()*4+10));
    }


    /**
     * Dibuja.
     *
     * @param c the c
     */
    public void dibuja(Canvas c) {
        for (Fondo fondo : fondos)
            fondo.dibujar(c);
    }
}
