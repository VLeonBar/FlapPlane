package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.util.Log;
import android.view.MotionEvent;

import com.example.vleon.profinalmovil.Manejadores.FrameHandler;
import com.example.vleon.profinalmovil.Manejadores.Sonidos;
import com.example.vleon.profinalmovil.R;

public class Escena {

    Context contexto;
    public int idEscena;
    int altoPantalla, anchoPantalla;
    Bitmap imgFondo, vueltaAtras;
    Paint pincel, pincel2, pincel3;
    FrameHandler fh;
    Rect vueltaMenu;
    Sonidos sonidos;
    AudioManager audioManager;

    final private int maxSonidosSimultaneos = 10;


    public Escena(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        this.contexto = contexto;
        this.idEscena = idEscena;
        this.altoPantalla = altoPantalla;
        this.anchoPantalla = anchoPantalla;
        fh = new FrameHandler(contexto);
        sonidos = new Sonidos(contexto, 10);
        pincel = new Paint();
        pincel.setColor(Color.rgb(59, 36, 16));
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth((float) fh.getDpH(20, altoPantalla));
        pincel2 = new Paint();
        pincel2.setTextSize(fh.getDpH(150, altoPantalla));
        pincel2.setColor(Color.BLUE);
        vueltaMenu = new Rect(0, 0, anchoPantalla / 15, anchoPantalla / 15);
        vueltaAtras = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.moneda);
        vueltaAtras = Bitmap.createScaledBitmap(vueltaAtras, vueltaMenu.width(), vueltaMenu.height(), false);
    }

    public int actualizarFisica() {
        return idEscena;
    }

    public void dibujar(Canvas c) {
        try {
            if (idEscena != 0) {
                c.drawBitmap(vueltaAtras, vueltaMenu.left, vueltaMenu.top, null);
            }
        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int accion = event.getActionMasked();
        switch (accion) {

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (pulsa(vueltaMenu, event) && idEscena != 0) return 0;
                break;
        }

        return idEscena;
    }

    public boolean pulsa(Rect boton, MotionEvent event) {
        if (boton.contains((int) event.getX(), (int) event.getY())) {
            return true;
        } else {
            return false;
        }
    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    public int getIdEscena() {
        return idEscena;
    }

    public void setIdEscena(int idEscena) {
        this.idEscena = idEscena;
    }

    public int getAltoPantalla() {
        return altoPantalla;
    }

    public void setAltoPantalla(int altoPantalla) {
        this.altoPantalla = altoPantalla;
    }

    public int getAnchoPantalla() {
        return anchoPantalla;
    }

    public void setAnchoPantalla(int anchoPantalla) {
        this.anchoPantalla = anchoPantalla;
    }

    public Bitmap getImgFondo() {
        return imgFondo;
    }

    public void setImgFondo(Bitmap imgFondo) {
        this.imgFondo = imgFondo;
    }

    public Paint getPincel() {
        return pincel;
    }

    public void setPincel(Paint pincel) {
        this.pincel = pincel;
    }

    public Rect getVueltaMenu() {
        return vueltaMenu;
    }

    public void setVueltaMenu(Rect vueltaMenu) {
        this.vueltaMenu = vueltaMenu;
    }
}
