package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.vleon.profinalmovil.ObjetosJuego.Boton;
import com.example.vleon.profinalmovil.ObjetosJuego.MonedaMenu;
import com.example.vleon.profinalmovil.R;

/**
 * La escena del menu principal, que permite acceder al resto de las escenas e implementa una animacion de movimiento para hacerlo mas visual.
 *
 * @author Victor Leon Barciela
 */
public class MenuPrincipal extends Escena {
    /**
     * El rectangulo del boton jugar.
     */
    Rect rectJugar, /**
     * El rectangulo del boton ajustes.
     */
    rectAjustes, /**
     * El rectangulo del boton creditos.
     */
    rectCreditos, /**
     * El rectangulo del boton records.
     */
    rectRecords, /**
     * El rectangulo del boton ayuda.
     */
    rectAyuda, /**
     * El boton pulsado.
     */
    botonPulsado = null;
    /**
     * Bandera que permite el uso de la animacion.
     */
    boolean bandera = true;
    /**
     * Moneda que hace la animacion de moverse.
     */
    MonedaMenu monedaMenu;
    /**
     * La escena destino.
     */
    int escenaDestino = idEscena;
    /**
     * Indica el movimiento de la moneda.
     */
    boolean movMoneda = false;
    /**
     * El icono de la aplicacion de fondo del menu.
     */
    Boton backgroundLogo;

    /**
     * Instancia un nuevo objeto de la clase Menu principal.
     *
     * @param contexto      el contexto
     * @param idEscena      el id  de la escena
     * @param anchoPantalla el ancho pantalla
     * @param altoPantalla  el alto pantalla
     */
    public MenuPrincipal(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, altoPantalla, anchoPantalla);
        imgFondo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.fondo_main_menu);
        imgFondo = Bitmap.createScaledBitmap(imgFondo, anchoPantalla, altoPantalla, false);
        monedaMenu = new MonedaMenu(0, 0, new BitmapFactory().decodeResource(contexto.getResources(), R.drawable.moneda));
        monedaMenu.setImgFicha(Bitmap.createScaledBitmap(monedaMenu.getImgFicha(), fh.partePantalla(altoPantalla, 6) / 2, fh.partePantalla(altoPantalla, 6) / 2, false));
        monedaMenu.setPosX(fh.partePantalla(anchoPantalla, 2) - monedaMenu.getImgFicha().getWidth() / 2);
        monedaMenu.setPosY(fh.partePantalla(altoPantalla, 6) * (float) 5.5 - monedaMenu.getImgFicha().getHeight() / 2);
        rectRecords = new Rect(0, 0, fh.partePantalla(anchoPantalla, 3), fh.partePantalla(altoPantalla, 6));
        rectAjustes = new Rect(fh.partePantalla(anchoPantalla, 3) * 2, 0, fh.partePantalla(anchoPantalla, 3) * 3, fh.partePantalla(altoPantalla, 6));
        rectJugar = new Rect(0, fh.partePantalla(altoPantalla, 6), anchoPantalla, fh.partePantalla(altoPantalla, 6) * 2);
        rectCreditos = new Rect(0, fh.partePantalla(altoPantalla, 6) * 2, fh.partePantalla(anchoPantalla, 3), fh.partePantalla(altoPantalla, 6) * 3);
        rectAyuda = new Rect(fh.partePantalla(anchoPantalla, 3) * 2, fh.partePantalla(altoPantalla, 6) * 2, anchoPantalla, fh.partePantalla(altoPantalla, 6) * 3);
        backgroundLogo = new Boton(fh.partePantalla(anchoPantalla, 3), fh.partePantalla(altoPantalla, 6) * 3, fh.partePantalla(anchoPantalla, 3) * 2, fh.partePantalla(altoPantalla, 6) * 4, Color.TRANSPARENT, typeface2);
        backgroundLogo.setImg(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(contexto.getResources(), R.drawable.icono), fh.partePantalla(anchoPantalla, 3), fh.partePantalla(anchoPantalla, 3), false));
        if (isSoundOn)
            sonidos.mediaPlayer.start();
    }
    /**
     * Actualiza las físicas, encargado de realizar la animacion de movimiento de la moneda del menu.
     *
     * @return int el id de la escena
     */
    public int actualizarFisica() {
        if (movMoneda) {
            bandera = monedaMenu.mueveMoneda(botonPulsado);
            if (!bandera) {
                return escenaDestino;
            }
        }
        return idEscena;
    }
    /**
     * Se encarga de dibujar todos los elementos que se indiquen en el lienzo.
     *
     * @param c el Canvas
     */
    public void dibujar(Canvas c) {
        try {
            c.drawBitmap(imgFondo, 0, 0, null);
            backgroundLogo.dibujar(c);
            c.drawRect(rectRecords, pincel);
            c.drawRect(rectAjustes, pincel);
            c.drawRect(rectJugar, pincel);
            c.drawRect(rectCreditos, pincel);
            c.drawRect(rectAyuda, pincel);
            monedaMenu.dibujar(c);

        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
    /**
     * Evento OnTouch, se lanza cuando se toca la pantalla.
     *
     * @param event el evento
     * @return int el id de la escena.
     */
    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int accion = event.getActionMasked();
        switch (accion) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (pulsa(rectRecords, event)) {
                    this.botonPulsado = rectRecords;
                    movMoneda = true;
                    escenaDestino = 4;
                } else if (pulsa(rectAjustes, event)) {
                    this.botonPulsado = rectAjustes;
                    movMoneda = true;
                    escenaDestino = 2;
                } else if (pulsa(rectJugar, event)) {
                    if (isSoundOn)
                        sonidos.getEfectos().play(sonidos.sonidoInsertCoin, 1, 1, 1, 0, 1);
                    this.botonPulsado = rectJugar;
                    movMoneda = true;
                    escenaDestino = 3;
                } else if (pulsa(rectCreditos, event)) {
                    this.botonPulsado = rectCreditos;
                    movMoneda = true;
                    escenaDestino = 1;
                } else if (pulsa(rectAyuda, event)) {
                    this.botonPulsado = rectAyuda;
                    movMoneda = true;
                    escenaDestino = 5;
                }

                break;
        }
        super.onTouchEvent(event);
        return idEscena;
    }
}
