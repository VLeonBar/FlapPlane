package com.example.vleon.profinalmovil.Manejadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.vleon.profinalmovil.ObjetosJuego.Fondo;

import java.util.ArrayList;

/**
 * La clase Parallax se encarga de mover las imagenes que se le pasan creando un efecto Parallax.
 */
public class Parallax {

    /**
     * El ArrayList de objetos Fondo.
     */
    ArrayList<Fondo> fondos = new ArrayList<>();
    /**
     * El objeto FrameHandler.
     */
    FrameHandler fh;
    /**
     * El Ancho de la pantalla.
     */
    int anchopantalla, /**
     * El  Alto de la pantalla.
     */
    altopantalla;

    /**
     * Instancia un nuevo objeto de la clase Parallax.
     *
     * @param contexto          el contexto
     * @param anchoPantalla     el ancho pantalla
     * @param altoPantalla      el alto pantalla
     * @param cantImagenesFondo la cantidad de imagenes fondo
     */
    public Parallax(Context contexto, int anchoPantalla, int altoPantalla, int cantImagenesFondo) {
        this.anchopantalla = anchoPantalla;
        this.altopantalla = altoPantalla;
        fh = new FrameHandler(contexto);
        creaFondos(fh.getFrames(cantImagenesFondo, "fondos", "fondo", altoPantalla));
    }

    /**
     * Crea el array list de fondos.
     *
     * @param imagenes las imagenes
     * @return el arraylist
     */
    public ArrayList<Fondo> creaFondos(Bitmap[] imagenes) {
        this.fondos.add((new Fondo(imagenes[0], 0, 0)));
        this.fondos.add((new Fondo(imagenes[1], 0, altopantalla / 2)));
        this.fondos.add((new Fondo(imagenes[2], 0, 0)));
        return this.fondos;
    }

    /**
     * Actualizar fisica, mueve los fondos con una velocidad aleatoria entre 4 y 10.
     */
    public void actualizarFisica() {
        for (Fondo fondo : fondos)
            fondo.mover((int)(Math.random()*4+10));
    }


    /**
     * Dibuja los fondos.
     *
     * @param c el Canvas
     */
    public void dibuja(Canvas c) {
        for (Fondo fondo : fondos)
            fondo.dibujar(c);
    }
}
