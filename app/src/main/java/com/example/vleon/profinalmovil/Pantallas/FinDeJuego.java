package com.example.vleon.profinalmovil.Pantallas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import com.example.vleon.profinalmovil.Datos.BaseDeDatos;
import com.example.vleon.profinalmovil.Manejadores.FrameHandler;
import com.example.vleon.profinalmovil.ObjetosJuego.Boton;
import com.example.vleon.profinalmovil.ObjetosJuego.Nave;
import com.example.vleon.profinalmovil.R;

import java.util.ArrayList;

/**
 * La Escena de Fin de Juego, aparece cuando el jugador pierde y permite guardar su alias y su récord en la base de datos.
 *
 * @author Victor Leon Barciela
 */
public class FinDeJuego extends Escena {

    private Boton btn1Arriba, btn1Abajo, btn2Arriba, btn2Abajo, btn3Arriba, btn3Abajo, btnLetra1, btnLetra2, btnLetra3, btnAceptar, textoCabecera, textoPuntuacion;
    private ArrayList<Boton> botones = new ArrayList<>();
    private ArrayList<Character> alfabeto = new ArrayList<>();
    private char[] letras = new char[3];
    private Bitmap btnAbajo, btnArriba;
    private FrameHandler fh;
    /**
     * Objeto Nave, utilizado para poder acceder a la puntuacion.
     */
    Nave n;
    /**
     * La base de datos.
     */
    BaseDeDatos bd;
    /**
     * El manejador SQLite para la base de datos.
     */
    SQLiteDatabase db;
    /**
     * El cursor.
     */
    Cursor c;
    /**
     * La consulta.
     */
    String query;

    /**
     * Instancia un nuevo objeto de la clase Fin de juego.
     *
     * @param contexto      el contexto
     * @param idEscena      el id  de la escena
     * @param anchoPantalla el ancho pantalla
     * @param altoPantalla  el alto pantalla
     */
    public FinDeJuego(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, anchoPantalla, altoPantalla);
        fh = new FrameHandler(contexto);
        imgFondo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.fondopantallas);
        imgFondo = Bitmap.createScaledBitmap(imgFondo, anchoPantalla, altoPantalla, false);
        for (char i = 'A'; i <= 'Z'; i++) {
            alfabeto.add(i);
        }
        for (int i = 0; i < letras.length; i++) {
            letras[i] = 'A';
        }
        textoCabecera = new Boton(0, 0, anchoPantalla, fh.partePantalla(altoPantalla, 5), Color.TRANSPARENT, typeface2);
        textoCabecera.setTexto("¡HAS PERDIDO!", 150, Color.BLACK);
        botones.add(textoCabecera);
        textoPuntuacion = new Boton(0, fh.partePantalla(altoPantalla, 5), anchoPantalla, fh.partePantalla(altoPantalla, 5) * 2, Color.TRANSPARENT, typeface1);
        textoPuntuacion.setTexto("PUNTUACIÓN: " + n.puntuacion, 100, Color.BLACK);
        botones.add(textoPuntuacion);

        //CAMBIAR TODOS POR FH.PARTEPANTALLA!!!!!!!!!!
        btnAceptar = new Boton(fh.partePantalla(anchoPantalla, 2) - fh.partePantalla(anchoPantalla, 10),
                fh.partePantalla(altoPantalla, 3) * 2 + fh.partePantalla(altoPantalla, 20),
                fh.partePantalla(anchoPantalla, 2) + fh.partePantalla(anchoPantalla, 10),
                fh.partePantalla(altoPantalla, 3) * 2 + fh.partePantalla(altoPantalla, 20) * 2, Color.WHITE, typeface2);

        btnAceptar.setTexto("Aceptar", fh.getDpH(40, altoPantalla), Color.BLACK);
        botones.add(btnAceptar);


        btnAbajo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.flecha_abajo);
        btnAbajo = Bitmap.createScaledBitmap(btnAbajo, anchoPantalla / 10, anchoPantalla / 10, true);

        btnArriba = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.flecha_arriba);
        btnArriba = Bitmap.createScaledBitmap(btnArriba, anchoPantalla / 10, anchoPantalla / 10, true);

        btn1Arriba = new Boton(anchoPantalla / 20 + btnArriba.getWidth() / 2,
                fh.partePantalla(altoPantalla, 2) - fh.partePantalla(altoPantalla, 50) - btnArriba.getHeight() / 2,
                fh.partePantalla(anchoPantalla, 20) + btnArriba.getWidth() + btnArriba.getWidth() / 2,
                fh.partePantalla(altoPantalla, 2) - fh.partePantalla(altoPantalla, 50) + btnArriba.getHeight() / 2, Color.TRANSPARENT, typeface2);
        btn1Arriba.setImg(btnArriba);
        botones.add(btn1Arriba);

        btn1Abajo = new Boton(fh.partePantalla(anchoPantalla, 20) + btnArriba.getWidth() / 2,
                fh.partePantalla(altoPantalla, 3) * 2 - fh.partePantalla(altoPantalla, 100) - btnArriba.getHeight(),
                fh.partePantalla(anchoPantalla, 20) + btnArriba.getWidth() + btnArriba.getWidth() / 2,
                fh.partePantalla(altoPantalla, 3) * 2 - fh.partePantalla(altoPantalla, 100), Color.TRANSPARENT, typeface2);
        btn1Abajo.setImg(btnAbajo);
        botones.add(btn1Abajo);

        btn2Arriba = new Boton(fh.partePantalla(anchoPantalla, 2) - btnArriba.getWidth() / 2,
                fh.partePantalla(altoPantalla, 2) - fh.partePantalla(altoPantalla, 50) - btnArriba.getHeight() / 2,
                fh.partePantalla(anchoPantalla, 2) + btnArriba.getWidth() / 2,
                fh.partePantalla(altoPantalla, 2) - fh.partePantalla(altoPantalla, 50) + btnArriba.getHeight() / 2, Color.TRANSPARENT, typeface2);
        btn2Arriba.setImg(btnArriba);
        botones.add(btn2Arriba);

        btn2Abajo = new Boton(fh.partePantalla(anchoPantalla, 2) - btnArriba.getWidth() / 2,
                fh.partePantalla(altoPantalla, 3) * 2 - fh.partePantalla(altoPantalla, 100) - btnArriba.getHeight(),
                fh.partePantalla(anchoPantalla, 2) + btnArriba.getWidth() / 2,
                fh.partePantalla(altoPantalla, 3) * 2 - fh.partePantalla(altoPantalla, 100), Color.TRANSPARENT, typeface2);
        btn2Abajo.setImg(btnAbajo);
        botones.add(btn2Abajo);

        btn3Arriba = new Boton(anchoPantalla - btnArriba.getWidth() * 2,
                fh.partePantalla(altoPantalla, 2) - fh.partePantalla(altoPantalla, 50) - btnArriba.getHeight() / 2, anchoPantalla - btnArriba.getWidth(),
                fh.partePantalla(altoPantalla, 2) - fh.partePantalla(altoPantalla, 50) + btnArriba.getHeight() / 2, Color.TRANSPARENT, typeface2);
        btn3Arriba.setImg(btnArriba);
        botones.add(btn3Arriba);

        btn3Abajo = new Boton(anchoPantalla - btnArriba.getWidth() * 2,
                fh.partePantalla(altoPantalla, 3) * 2 - fh.partePantalla(altoPantalla, 100) - btnArriba.getHeight(),
                anchoPantalla - btnArriba.getWidth(),
                fh.partePantalla(altoPantalla, 3) * 2 - fh.partePantalla(altoPantalla, 100), Color.TRANSPARENT, typeface2);
        btn3Abajo.setImg(btnAbajo);
        botones.add(btn3Abajo);

        btnLetra1 = new Boton(btn1Arriba.getRect().left,
                btn1Arriba.getRect().bottom + fh.partePantalla(altoPantalla, 25), btn1Arriba.getRect().right, btn1Abajo.getRect().top - fh.partePantalla(altoPantalla, 20), Color.TRANSPARENT, typeface1);
        botones.add(btnLetra1);
        btnLetra2 = new Boton(btn2Arriba.getRect().left,
                btn2Arriba.getRect().bottom + fh.partePantalla(altoPantalla, 25), btn2Arriba.getRect().right, btn2Abajo.getRect().top - fh.partePantalla(altoPantalla, 20), Color.TRANSPARENT, typeface1);
        botones.add(btnLetra2);
        btnLetra3 = new Boton(btn3Arriba.getRect().left,
                btn3Arriba.getRect().bottom + fh.partePantalla(altoPantalla, 25), btn3Arriba.getRect().right, btn3Abajo.getRect().top - fh.partePantalla(altoPantalla, 20), Color.TRANSPARENT, typeface1);
        botones.add(btnLetra3);

    }
    /**
     * Actualiza las físicas, actualiza el texto de las letras de elección de nombre que salen por pantalla.
     *
     * @return int el id de la escena
     */
    public int actualizarFisica() {
        btnLetra1.setTexto("" + letras[0], fh.getDpH(100, altoPantalla), Color.BLACK);
        btnLetra2.setTexto("" + letras[1], fh.getDpH(100, altoPantalla), Color.BLACK);
        btnLetra3.setTexto("" + letras[2], fh.getDpH(100, altoPantalla), Color.BLACK);

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
            for (Boton b : botones) {
                b.dibujar(c);
            }
            super.dibujar(c);
        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    /**
     * Mueve adelante en el array de letras.
     *
     * @param letra la letra
     * @return char
     */
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

    /**
     * Mueve atrás en el array de letras.
     *
     * @param letra la letra
     * @return char
     */
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
    /**
     * Evento OnTouch, se lanza cuando se toca la pantalla.
     *
     * @param event el evento
     * @return int el id de la escena.
     */
    public int onTouchEvent(MotionEvent event) {
        int accion = event.getActionMasked();
        switch (accion) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (pulsa(btnAceptar.getRect(), event)) {
                    if (mejoraPuntuacion())
                        insertPuntuacion();
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
                    letras[1] = adelanteLetra(letras[1]);
                }
                if (pulsa(btn3Arriba.getRect(), event)) {
                    letras[2] = atrasLetra(letras[2]);
                }
                if (pulsa(btn3Abajo.getRect(), event)) {
                    letras[2] = adelanteLetra(letras[2]);
                }

        }
        int padre = super.onTouchEvent(event);
        if (padre != idEscena) return padre;
        return idEscena;
    }

    /**
     * Revisa si la puntuacion del jugador mejora la peor puntuacion de la base de datos.
     *
     * @return el boolean, true si mejora, false sino.
     */
    public boolean mejoraPuntuacion() {
        int lowerScore = 0;
        bd = new BaseDeDatos(contexto, "basededatos", null, 1);
        db = bd.getWritableDatabase();
        query = "SELECT min(score) FROM scores";
        c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                lowerScore = c.getInt(0);
            } while (c.moveToNext());
        }
        c.close();
        if (n.puntuacion > lowerScore) {
            return true;
        }
        return false;
    }

    /**
     * Inserta la puntuacion en la base de datos.
     */
    public void insertPuntuacion() {
        int maxId = 0;
        bd = new BaseDeDatos(contexto, "basededatos", null, 1);
        db = bd.getWritableDatabase();
        query = "SELECT max(id )FROM scores";
        c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                maxId = c.getInt(0);
            } while (c.moveToNext());
        }
        ContentValues fila = new ContentValues();
        fila.put("nick", Character.toString(letras[0]) + "  " + Character.toString(letras[1]) + "  " + Character.toString(letras[2]) + " >");
        fila.put("id", maxId + 1);
        fila.put("score", n.puntuacion);
        db.insert("scores", null, fila);
        c.close();
        db.close();
    }
}
