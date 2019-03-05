package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import com.example.vleon.profinalmovil.Manejadores.FrameHandler;
import com.example.vleon.profinalmovil.ObjetosJuego.Boton;
import com.example.vleon.profinalmovil.ObjetosJuego.Nave;
import com.example.vleon.profinalmovil.R;

import java.util.ArrayList;

public class FinDeJuego extends Escena {

    private Boton btn1Arriba, btn1Abajo, btn2Arriba, btn2Abajo, btn3Arriba, btn3Abajo, btnLetra1, btnLetra2, btnLetra3, btnAceptar, textoCabecera, textoPuntuacion;
    private ArrayList<Boton> botones = new ArrayList<>();
    private ArrayList<Character> alfabeto = new ArrayList<>();
    private char[] letras = new char[3];
    private Bitmap btnAbajo, btnArriba;
    private FrameHandler fh;
    Nave n;

    public FinDeJuego(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, anchoPantalla, altoPantalla);
        fh = new FrameHandler(contexto);
        imgFondo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.fondopantallas);
        imgFondo = Bitmap.createScaledBitmap(imgFondo, anchoPantalla, altoPantalla, false);
        //Relleno el AL de la A a la Z
        for (char i = 'A'; i <= 'Z'; i++) {
            alfabeto.add(i);
        }
        //Inicializo las 3 posiciones del array de letras
        for (int i = 0; i < letras.length; i++) {
            letras[i] = 'A';
        }
        //Textos de información de pantalla
        textoCabecera = new Boton(0, 0, anchoPantalla, fh.partePantalla(altoPantalla, 5), Color.TRANSPARENT);
        textoCabecera.setTexto("¡HAS PERDIDO!", 150, Color.BLACK);
        botones.add(textoCabecera);
        textoPuntuacion = new Boton(0, fh.partePantalla(altoPantalla, 5), anchoPantalla, fh.partePantalla(altoPantalla, 5) * 2, Color.TRANSPARENT);
        textoPuntuacion.setTexto("PUNTUACIÓN: " + n.puntuacion, 100, Color.BLACK);
        botones.add(textoPuntuacion);
        //CAMBIAR TODOS POR FH.PARTEPANTALLA!!!!!!!!!!
        btnAceptar = new Boton(fh.partePantalla(anchoPantalla, 2) - fh.partePantalla(anchoPantalla, 10),
                fh.partePantalla(altoPantalla, 3) * 2 + fh.partePantalla(altoPantalla, 20),
                fh.partePantalla(anchoPantalla, 2) + fh.partePantalla(anchoPantalla, 10),
                fh.partePantalla(altoPantalla, 3) * 2 + fh.partePantalla(altoPantalla, 20) * 2, Color.CYAN);

        btnAceptar.setTexto("Aceptar", fh.getDpH(40, altoPantalla), Color.BLACK);
        botones.add(btnAceptar);
        // BOTONES PARA LAS FLECHAS DE ELECCION DE LETRAS
        btnAbajo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.flecha_abajo);
        btnAbajo = Bitmap.createScaledBitmap(btnAbajo, anchoPantalla / 10, anchoPantalla / 10, true);

        btnArriba = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.flecha_arriba);
        btnArriba = Bitmap.createScaledBitmap(btnArriba, anchoPantalla / 10, anchoPantalla / 10, true);

        btn1Arriba = new Boton(anchoPantalla / 20 + btnArriba.getWidth() / 2,
                altoPantalla / 2 - altoPantalla / 50 - btnArriba.getHeight() / 2,
                anchoPantalla / 20 + btnArriba.getWidth() + btnArriba.getWidth() / 2,
                altoPantalla / 2 - altoPantalla / 50 + btnArriba.getHeight() / 2, Color.TRANSPARENT);
        btn1Arriba.setImg(btnArriba);
        botones.add(btn1Arriba);

        //LETRA

        btn1Abajo = new Boton(anchoPantalla / 20 + btnArriba.getWidth() / 2,
                altoPantalla / 3 * 2 - altoPantalla / 100 - btnArriba.getHeight(),
                anchoPantalla / 20 + btnArriba.getWidth() + btnArriba.getWidth() / 2,
                altoPantalla / 3 * 2 - altoPantalla / 100, Color.TRANSPARENT);
        btn1Abajo.setImg(btnAbajo);
        botones.add(btn1Abajo);

        btn2Arriba = new Boton(anchoPantalla / 2 - btnArriba.getWidth() / 2,
                altoPantalla / 2 - altoPantalla / 50 - btnArriba.getHeight() / 2,
                anchoPantalla / 2 + btnArriba.getWidth() / 2,
                altoPantalla / 2 - altoPantalla / 50 + btnArriba.getHeight() / 2, Color.TRANSPARENT);
        btn2Arriba.setImg(btnArriba);
        botones.add(btn2Arriba);

        btn2Abajo = new Boton(anchoPantalla / 2 - btnArriba.getWidth() / 2,
                altoPantalla / 3 * 2 - altoPantalla / 100 - btnArriba.getHeight(),
                anchoPantalla / 2 + btnArriba.getWidth() / 2,
                altoPantalla / 3 * 2 - altoPantalla / 100, Color.TRANSPARENT);
        btn2Abajo.setImg(btnAbajo);
        botones.add(btn2Abajo);

        btn3Arriba = new Boton(anchoPantalla - btnArriba.getWidth() * 2,
                altoPantalla / 2 - altoPantalla / 50 - btnArriba.getHeight() / 2,
                anchoPantalla - btnArriba.getWidth(),
                altoPantalla / 2 - altoPantalla / 50 + btnArriba.getHeight() / 2, Color.TRANSPARENT);
        btn3Arriba.setImg(btnArriba);
        botones.add(btn3Arriba);

        btn3Abajo = new Boton(anchoPantalla - btnArriba.getWidth() * 2,
                altoPantalla / 3 * 2 - altoPantalla / 100 - btnArriba.getHeight(),
                anchoPantalla - btnArriba.getWidth(),
                altoPantalla / 3 * 2 - altoPantalla / 100, Color.TRANSPARENT);
        btn3Abajo.setImg(btnAbajo);
        botones.add(btn3Abajo);

        btnLetra1 = new Boton(btn1Arriba.getRect().left,
                btn1Arriba.getRect().bottom + fh.partePantalla(altoPantalla, 25), btn1Arriba.getRect().right, btn1Abajo.getRect().top - fh.partePantalla(altoPantalla, 20), Color.TRANSPARENT);
        botones.add(btnLetra1);
        btnLetra2 = new Boton(btn2Arriba.getRect().left,
                btn2Arriba.getRect().bottom + fh.partePantalla(altoPantalla, 25), btn2Arriba.getRect().right, btn2Abajo.getRect().top - fh.partePantalla(altoPantalla, 20), Color.TRANSPARENT);
        botones.add(btnLetra2);
        btnLetra3 = new Boton(btn3Arriba.getRect().left,
                btn3Arriba.getRect().bottom + fh.partePantalla(altoPantalla, 25), btn3Arriba.getRect().right, btn3Abajo.getRect().top - fh.partePantalla(altoPantalla, 20), Color.TRANSPARENT);
        botones.add(btnLetra3);

    }

    public int actualizarFisica() {
        btnLetra1.setTexto("" + letras[0], fh.getDpH(100, altoPantalla), Color.BLACK);
        btnLetra2.setTexto("" + letras[1], fh.getDpH(100, altoPantalla), Color.BLACK);
        btnLetra3.setTexto("" + letras[2], fh.getDpH(100, altoPantalla), Color.BLACK);

        return idEscena;
    }

    public void dibujar(Canvas c) {
        try {
            //Fondo de pantalla del creditos
            c.drawBitmap(imgFondo, 0, 0, null);
            for (Boton b : botones) {
                b.dibujar(c);
            }
            //llama al dibujar de la clase padre para dibujar los elementos comunes a todas las clases hijas
            super.dibujar(c);
        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    public char adelanteLetra(char letra) {
        int i = alfabeto.indexOf(letra);
        if (i == alfabeto.size() - 1) {
            i = 0;
        } else {
            i++;
        }
        letra = alfabeto.get(i);
        return letra;
    }

    public char atrasLetra(char letra) {
        int i = alfabeto.indexOf(letra);
        if (i == 0) {
            i = alfabeto.size() - 1;
        } else {
            i--;
        }
        letra = alfabeto.get(i);
        return letra;
    }

    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int accion = event.getActionMasked();
        switch (accion) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (pulsa(btnAceptar.getRect(), event)) {
                    return 0;
                }
                if (pulsa(btn1Arriba.getRect(), event)) {
                    letras[0] = atrasLetra(letras[0]);
                }
                if (pulsa(btn1Abajo.getRect(), event)) {
                    letras[0] = adelanteLetra(letras[0]);
                }
                if (pulsa(btn2Arriba.getRect(), event)) {
                    letras[1] = atrasLetra(letras[1]);
                }
                if (pulsa(btn2Abajo.getRect(), event)) {
                    letras[1] = atrasLetra(letras[1]);
                }
                if (pulsa(btn3Arriba.getRect(), event)) {
                    letras[2] = atrasLetra(letras[2]);
                }
                if (pulsa(btn3Abajo.getRect(), event)) {
                    letras[2] = atrasLetra(letras[2]);
                }

        }
        int padre = super.onTouchEvent(event);
        if (padre != idEscena) return padre;
        return idEscena;
    }
}
