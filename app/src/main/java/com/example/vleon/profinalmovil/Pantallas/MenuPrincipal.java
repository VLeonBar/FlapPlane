package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.vleon.profinalmovil.ObjetosJuego.MonedaMenu;
import com.example.vleon.profinalmovil.R;

public class MenuPrincipal extends Escena {
    Rect rectJugar, rectAjustes, rectRecord, rectCreditos, rectControles, botonPulsado = null;
    boolean bandera = true;
    int anchoDecimo, altoMedio, anchoTercio, altoSexto, anchoMedio;
    MonedaMenu monedaMenu;
    int cont = 0;
    int escenaDestino = idEscena;
    boolean movMoneda = false;


    public MenuPrincipal(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, altoPantalla, anchoPantalla);
        imgFondo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.fondo_main_menu);
        imgFondo = Bitmap.createScaledBitmap(imgFondo, anchoPantalla, altoPantalla, false);
        anchoDecimo = anchoPantalla / 10;
        anchoTercio = anchoPantalla / 3;
        anchoMedio = anchoPantalla / 2;
        altoMedio = altoPantalla / 2;
        altoSexto = altoMedio / 3;
        monedaMenu = new MonedaMenu(0, 0, new BitmapFactory().decodeResource(contexto.getResources(), R.drawable.moneda));
        monedaMenu.setImgFicha(Bitmap.createScaledBitmap(monedaMenu.getImgFicha(), altoSexto / 2, altoSexto / 2, false));
        monedaMenu.setPosX(anchoMedio - monedaMenu.getImgFicha().getWidth() / 2);
        monedaMenu.setPosY(altoSexto * (float) 5.5 - monedaMenu.getImgFicha().getHeight() / 2);
        rectCreditos = new Rect(0, 0, anchoTercio, altoSexto);
        rectAjustes = new Rect(anchoTercio * 2, 0, anchoTercio * 3, altoSexto);
        rectJugar = new Rect(0, altoSexto, anchoPantalla, altoSexto * 2);
        rectRecord = new Rect(0, altoSexto * 2, anchoTercio, altoSexto * 3);
        rectControles = new Rect(anchoTercio * 2, altoSexto * 2, anchoPantalla, altoSexto * 3);
//       TODO
//        CREAR VARIABLE BOOLEANA PARA CONTROLAR ESTO
        if (isSoundOn)
            sonidos.mediaPlayer.start();
    }

    //FÍSICAS Y DIBUJO DE LA CLASE MENÚ
    public int actualizarFisica() {
        if (movMoneda) {
            bandera = monedaMenu.mueveMoneda(botonPulsado);
            if (!bandera) {
                return escenaDestino;
            }
        }
        return idEscena;
    }

    public void dibujar(Canvas c) {
        try {
            //Fondo de pantalla del menú
            c.drawBitmap(imgFondo, 0, 0, null);
            c.drawRect(rectCreditos, pincel);
            c.drawRect(rectAjustes, pincel);
            c.drawRect(rectJugar, pincel);
            c.drawRect(rectRecord, pincel);
            c.drawRect(rectControles, pincel);
            monedaMenu.dibujar(c);

        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int accion = event.getActionMasked();
        switch (accion) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (pulsa(rectCreditos, event)) {
                    this.botonPulsado = rectCreditos;
                    movMoneda = true;
                    escenaDestino = 1;
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
                } else if (pulsa(rectRecord, event)) {
                    this.botonPulsado = rectRecord;
                    movMoneda = true;
                    escenaDestino = 4;
                } else if (pulsa(rectControles, event)) {
                    this.botonPulsado = rectControles;
                    movMoneda = true;
                    escenaDestino = 5;
                }

                break;
        }
        super.onTouchEvent(event);
        return idEscena;
    }
}
