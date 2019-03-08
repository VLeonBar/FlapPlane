package com.example.vleon.profinalmovil.Pantallas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import com.example.vleon.profinalmovil.Datos.BaseDeDatos;
import com.example.vleon.profinalmovil.ObjetosJuego.Boton;
import com.example.vleon.profinalmovil.R;

import java.util.ArrayList;

/**
 * The type Records.
 */
public class Records extends Escena {
    /**
     * El botón dónde muestro los récords
     */
    Boton bRecords, /**
     * El botón de borrado.
     */
    bBorrado, /**
     * El texto de la cabecera.
     */
    textHeader;
    /**
     * Colección que utilizo para tener almacenados todos los botones.
     */
    ArrayList<Boton> botones = new ArrayList<>();
    /**
     * La consulta a la base de datos
     */
    String query, /**
     * La respuesta a la consulta de la base de datos.
     */
    respuestaBD;
    /**
     * El objeto de la clase de la Base de datos.
     */
    BaseDeDatos bd;
    /**
     * El objeto SQLiteDatabase para utilizar la base de datos.
     */
    SQLiteDatabase db;
    /**
     * El cursor para.
     */
    Cursor c;
    /**
     * El array donde se almacenan los datos de la base de datos.
     */
    ArrayList<String> infoBD = new ArrayList<>();

    /**
     * Instancia la clase Records.
     *
     * @param contexto      el contexto
     * @param idEscena      el id  de la escena
     * @param anchoPantalla el ancho pantalla
     * @param altoPantalla  el alto pantalla
     */
    public Records(Context contexto, int idEscena, int anchoPantalla, int altoPantalla) {
        super(contexto, idEscena, anchoPantalla, altoPantalla);
        imgFondo = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.fondopantallas);
        imgFondo = Bitmap.createScaledBitmap(imgFondo, anchoPantalla, altoPantalla, false);
        bBorrado = new Boton(0, fh.partePantalla(altoPantalla, 12) * 11, anchoPantalla, altoPantalla, Color.TRANSPARENT, typeface2);
        bBorrado.setTexto("BORRAR TODO", fh.getDpH(120, altoPantalla), Color.BLACK);
        textHeader = new Boton(0, fh.partePantalla(altoPantalla, 40) * 1, anchoPantalla, fh.partePantalla(altoPantalla, 12) * 1, Color.TRANSPARENT, typeface2);
        textHeader.setTexto("RÉCORDS", fh.getDpH(120, altoPantalla), Color.BLACK);
        for (int i = 1; i < 11; i++) {
            bRecords = new Boton(0, fh.partePantalla(altoPantalla, 12) * i, anchoPantalla, fh.partePantalla(altoPantalla, 12) * (i + 1), Color.TRANSPARENT, typeface1);
            botones.add(bRecords);
        }
        actualizaScores();
    }

    public int actualizarFisica() {
        return idEscena;
    }

    public void dibujar(Canvas c) {
        try {
            try {
                c.drawBitmap(imgFondo, 0, 0, null);
                int cont = 1;
                for (Boton b : botones) {
                    b.setTexto(cont + " -> " + infoBD.get(cont - 1), fh.getDpH(100, altoPantalla), Color.BLACK);
                    b.dibujar(c);
                    cont++;
                }
                textHeader.dibujar(c);
                bBorrado.dibujar(c);
            } catch (Exception e) {
            }
            super.dibujar(c);


        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    /**
     * Borra la base de datos de puntuaciones y crea una nueva vacía.
     */
    public void borraScores() {
        bd = new BaseDeDatos(contexto, "basededatos", null, 1);
        db = bd.getWritableDatabase();
        contexto.deleteDatabase("basededatos");
        db.close();
        actualizaScores();
    }

    /**
     * Actualiza las puntuaciones desde la tabla de la base de datos.
     */
    public void actualizaScores() {
        infoBD = new ArrayList<String>();
        bd = new BaseDeDatos(contexto, "basededatos", null, 1);
        db = bd.getWritableDatabase();

        query = "SELECT * FROM scores order by score desc,id asc limit 10";
        c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                respuestaBD = c.getString(1) + "\t" + c.getInt(2);
                infoBD.add(respuestaBD);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
    }

    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int accion = event.getActionMasked();
        switch (accion) {

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (pulsa(bBorrado.getRect(), event))
                    borraScores();
                break;
        }
        int padre = super.onTouchEvent(event);
        if (padre != idEscena) return padre;
        return idEscena;
    }
}
