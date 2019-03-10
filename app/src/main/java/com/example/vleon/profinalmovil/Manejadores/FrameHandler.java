package com.example.vleon.profinalmovil.Manejadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * La clase Frame Handler es la que contiene los métodos para controlar lo que sale por pantalla, la densidad de pantalla así como metodos manejadores para imagenes y recursos.
 */
public class FrameHandler {

    /**
     * El Context.
     */
    Context context;

    /**
     * Instancia un nuevo objeto de la clase Frame handler.
     *
     * @param context el context
     */
    public FrameHandler(Context context) {
        this.context = context;
    }

    /**
     * Devuelve una fracción de la pantalla.
     *
     * @param regionpantalla la region de pantalla
     * @param fraccion       la fraccion
     * @return int
     */
    public int partePantalla(int regionpantalla, int fraccion) {
        return regionpantalla / fraccion;
    }

    /**
     * Escala anchura bitmap.
     *
     * @param fichero    el fichero
     * @param nuevoAncho el nuevo ancho
     * @return bitmap
     */
    public Bitmap escalaAnchura(String fichero, int nuevoAncho) {
        Bitmap bitmapAux = getBitmapFromAssets(fichero);
        if (nuevoAncho == bitmapAux.getWidth()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, nuevoAncho, (bitmapAux.getHeight() * nuevoAncho) / bitmapAux.getWidth(), true);
    }

    /**
     * Escala altura bitmap.
     *
     * @param fichero   el fichero
     * @param nuevoAlto el nuevo alto
     * @return el bitmap
     */
    public Bitmap escalaAltura(String fichero, int nuevoAlto) {
        Bitmap bitmapAux = getBitmapFromAssets(fichero);
        if (nuevoAlto == bitmapAux.getHeight()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, (bitmapAux.getWidth() * nuevoAlto) / bitmapAux.getHeight(), nuevoAlto, true);
    }

    /**
     * Devuelve frames bitmap [ ].
     *
     * @param numImg el numero de imagenes
     * @param dir    el directorio
     * @param tag    el tag
     * @param heigth la altura
     * @return el bitmap [ ]
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
     * @param fichero el fichero
     * @return el bitmap de assets
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
     * Devuelve los pixeles segun la densidad de cada pantalla.
     *
     * @param pixels       los pixels
     * @param altoPantalla el alto pantalla
     * @return la dp h
     */
    public int getDpH(int pixels, int altoPantalla) {
        return (int) ((pixels / 19.2) * altoPantalla) / 100;
    }
}

