package com.example.vleon.profinalmovil.Manejadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.vleon.profinalmovil.Objetos.Fondo;

import java.util.ArrayList;

public class Parallax {

    ArrayList<Fondo> fondos = new ArrayList<>();
    FrameHandler fh;
    int anchopantalla, altopantalla;

    public Parallax(Context contexto, int anchoPantalla, int altoPantalla, int cantImagenesFondo) {
        this.anchopantalla = anchoPantalla;
        this.altopantalla = altoPantalla;
        fh = new FrameHandler(contexto);
        creaFondos(fh.getFrames(cantImagenesFondo, "fondos", "fondo", altoPantalla));
    }

    public ArrayList<Fondo> creaFondos(Bitmap[] imagenes) {
        this.fondos.add((new Fondo(imagenes[0], 0, 0)));
        this.fondos.add((new Fondo(imagenes[1], 0, altopantalla / 2)));
        this.fondos.add((new Fondo(imagenes[2], 0, 0)));
        return this.fondos;
    }

    public void actualizarFisica() {
        for (Fondo fondo : fondos)
            fondo.mover((int)(Math.random()*4+10));
    }


    public void dibuja(Canvas c) {
        for (Fondo fondo : fondos)
            fondo.dibujar(c);
    }
}
