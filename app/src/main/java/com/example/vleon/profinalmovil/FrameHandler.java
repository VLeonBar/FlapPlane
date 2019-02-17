package com.example.vleon.profinalmovil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;

public class FrameHandler {

    Context context;

    public FrameHandler(Context context) {
        this.context = context;
    }

    public int partePantalla(int regionpantalla, int fraccion) {
        return regionpantalla / fraccion;
    }

    public Bitmap escalaAnchura(String fichero, int nuevoAncho) {
        Bitmap bitmapAux = getBitmapFromAssets(fichero);
        if (nuevoAncho == bitmapAux.getWidth()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, nuevoAncho, (bitmapAux.getHeight() * nuevoAncho) / bitmapAux.getWidth(), true);
    }

    public Bitmap escalaAltura(String fichero, int nuevoAlto) {
        Bitmap bitmapAux = getBitmapFromAssets(fichero);
        if (nuevoAlto == bitmapAux.getHeight()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, (bitmapAux.getWidth() * nuevoAlto) / bitmapAux.getHeight(), nuevoAlto, true);
    }

    public Bitmap[] getFrames(int numImg, String dir, String tag, int width) {
        Bitmap[] aux = new Bitmap[numImg];
        for (int i = 0; i < numImg; i++)
            aux[i] = escalaAltura(dir + "/" + tag + (i + 1) + ".png", width);
        return aux;
    }

    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is = context.getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }
}

