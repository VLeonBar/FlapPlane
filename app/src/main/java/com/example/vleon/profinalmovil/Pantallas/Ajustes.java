package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import com.example.vleon.profinalmovil.ObjetosJuego.Boton;
import com.example.vleon.profinalmovil.R;

import java.util.ArrayList;

/**
 * La Escena de Ajustes, en la cual permite activar o desactivar el sonido o la vibracion.
 */
public class Ajustes extends Escena {
    /**
     * El boton de activar sonido.
     */
    Boton btnActivaSonido, /**
     * El boton de activar vibracion.
     */
    btnActivaVibra, /**
     * El texto sonido
     */
    textoSonido, /**
     * El texto vibracion.
     */
    textoVibracion;
    /**
     * Coleccion de botones para gestionar de manera mas comoda.
     */
    ArrayList<Boton> botones = new ArrayList<>();
    /**
     * La imagen para sonido activado.
     */
    Bitmap imgSonidoOn, /**
     * La imagen para sonido desactivado.
     */
    imgSonidoOff, /**
     * La imagen para vibracion activada.
     */
    imgVibraOn, /**
     * La imagen para vibracion activada.
     */
    imgVibraOff;

    /**
     * Instancia un nuevo objeto de la clase Ajustes.
     *
     * @param contexto      el contexto
     * @param idEscena      el id  de la escena
     * @param anchoPantalla el ancho pantalla
     * @param altoPantalla  el alto pantalla
     */
    public Ajustes(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, anchoPantalla, altoPantalla);
        imgFondo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.fondopantallas);
        imgFondo = Bitmap.createScaledBitmap(imgFondo, anchoPantalla, altoPantalla, false);
        textoSonido = new Boton(0, fh.partePantalla(altoPantalla, 10) * 2, fh.partePantalla(anchoPantalla, 10) * 7, fh.partePantalla(altoPantalla, 10) * 3, Color.TRANSPARENT, typeface2);
        textoSonido.setTexto("Sonido", fh.getDpH(120, altoPantalla), Color.BLACK);
        botones.add(textoSonido);
        textoVibracion = new Boton(0, fh.partePantalla(altoPantalla, 10) * 4, fh.partePantalla(anchoPantalla, 10) * 7, fh.partePantalla(altoPantalla, 10) * 5, Color.TRANSPARENT, typeface2);
        textoVibracion.setTexto("Vibración", fh.getDpH(120, altoPantalla), Color.BLACK);
        botones.add(textoVibracion);
        btnActivaSonido = new Boton(fh.partePantalla(anchoPantalla, 10) * 7, fh.partePantalla(altoPantalla, 10) * 2, fh.partePantalla(anchoPantalla, 10) * 9, fh.partePantalla(altoPantalla, 10) * 3, Color.TRANSPARENT, typeface1);
        imgSonidoOn = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.musica_on);
        imgSonidoOn = Bitmap.createScaledBitmap(imgSonidoOn, btnActivaSonido.getRect().width(), btnActivaSonido.getRect().height(), false);
        imgSonidoOff = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.musica_off);
        imgSonidoOff = Bitmap.createScaledBitmap(imgSonidoOff, btnActivaSonido.getRect().width(), btnActivaSonido.getRect().height(), false);
        botones.add(btnActivaSonido);
        btnActivaVibra = new Boton(fh.partePantalla(anchoPantalla, 10) * 7, fh.partePantalla(altoPantalla, 10) * 4, fh.partePantalla(anchoPantalla, 10) * 9, fh.partePantalla(altoPantalla, 10) * 5, Color.TRANSPARENT, typeface1);
        imgVibraOn = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.vibra_on);
        imgVibraOn = Bitmap.createScaledBitmap(imgVibraOn, btnActivaSonido.getRect().width(), btnActivaSonido.getRect().height(), false);
        imgVibraOff = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.vibra_off);
        imgVibraOff = Bitmap.createScaledBitmap(imgVibraOff, btnActivaSonido.getRect().width(), btnActivaSonido.getRect().height(), false);
        botones.add(btnActivaVibra);
    }

    public int actualizarFisica() {
        if (isSoundOn)
            btnActivaSonido.setImg(imgSonidoOn);
        else
            btnActivaSonido.setImg(imgSonidoOff);
        if (isVibrationOn)
            btnActivaVibra.setImg(imgVibraOn);
        else
            btnActivaVibra.setImg(imgVibraOff);
        return idEscena;
    }

    public void dibujar(Canvas c) {
        try {
            c.drawBitmap(imgFondo, 0, 0, null);
            for (Boton b : botones) {
                b.dibujar(c);
            }
            super.dibujar(c);
        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int accion = event.getActionMasked();
        switch (accion) {
            case MotionEvent.ACTION_DOWN:
                if (isVibrationOn)
                    vibrar(100);
                if (isSoundOn)
                    sonidos.getEfectos().play(sonidos.sonidoToque, 1, 1, 1, 0, 1);
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (pulsa(btnActivaSonido.getRect(), event)) {
                    if (isSoundOn) {
                        sonidos.mediaPlayer.pause();
                        isSoundOn = false;
                    } else {
                        sonidos.mediaPlayer.start();
                        isSoundOn = true;
                    }
                    editorPreferencias.putBoolean("btnActivaSonido", isSoundOn);
                } else if (pulsa(btnActivaVibra.getRect(), event)) {
                    if (isVibrationOn) {
                        isVibrationOn = false;
                    } else {
                        isVibrationOn = true;
                    }
                    editorPreferencias.putBoolean("btnActivaVibra", isVibrationOn);
                }
                editorPreferencias.commit();
                break;
        }
        int padre = super.onTouchEvent(event);
        if (padre != idEscena) return padre;
        return idEscena;
    }
}
