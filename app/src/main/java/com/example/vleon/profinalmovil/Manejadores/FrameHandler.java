package com.example.vleon.profinalmovil.Manejadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type Frame handler.
 */
public class FrameHandler {

    /**
     * The Context.
     */
    Context context;

    /**
     * Instantiates a new Frame handler.
     *
     * @param context the context
     */
    public FrameHandler(Context context) {
        this.context = context;
    }

    /**
     * Devuelve una fracción de la pantalla.
     *
     * @param regionpantalla the regionpantalla
     * @param fraccion       the fraccion
     * @return the int
     */
    public int partePantalla(int regionpantalla, int fraccion) {
        return regionpantalla / fraccion;
    }

    /**
     * Escala anchura bitmap.
     *
     * @param fichero    the fichero
     * @param nuevoAncho the nuevo ancho
     * @return the bitmap
     */
    public Bitmap escalaAnchura(String fichero, int nuevoAncho) {
        Bitmap bitmapAux = getBitmapFromAssets(fichero);
        if (nuevoAncho == bitmapAux.getWidth()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, nuevoAncho, (bitmapAux.getHeight() * nuevoAncho) / bitmapAux.getWidth(), true);
    }

    /**
     * Escala altura bitmap.
     *
     * @param fichero   the fichero
     * @param nuevoAlto the nuevo alto
     * @return the bitmap
     */
    public Bitmap escalaAltura(String fichero, int nuevoAlto) {
        Bitmap bitmapAux = getBitmapFromAssets(fichero);
        if (nuevoAlto == bitmapAux.getHeight()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, (bitmapAux.getWidth() * nuevoAlto) / bitmapAux.getHeight(), nuevoAlto, true);
    }

    /**
     * Devuelve frames bitmap [ ].
     *
     * @param numImg the num img
     * @param dir    the dir
     * @param tag    the tag
     * @param heigth the heigth
     * @return the bitmap [ ]
     */
    public Bitmap[] getFrames(int numImg, String dir, String tag, int heigth) {
        Bitmap[] aux = new Bitmap[numImg];
        for (int i = 0; i < numImg; i++)
            aux[i] = escalaAltura(dir + "/" + tag + (i + 1) + ".png", heigth);
        return aux;
    }

    /**
     * Devuelve bitmap de la carpeta assets.
     *
     * @param fichero the fichero
     * @return the bitmap from assets
     */
    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is = context.getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Devuelve dp h.
     *
     * @param pixels       the pixels
     * @param altoPantalla the alto pantalla
     * @return the dp h
     */
    public int getDpH(int pixels, int altoPantalla) {
        return (int) ((pixels / 19.2) * altoPantalla) / 100;
    }
}

