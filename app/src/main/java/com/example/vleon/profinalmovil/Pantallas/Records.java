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

public class Records extends Escena {
    Boton bRecords, bBorrado, textHeader;
    ArrayList<Boton> botones = new ArrayList<>();
    Paint pPuntuaciones;
    String txtRecords;
    String query, respuestaBD;
    BaseDeDatos bd;
    SQLiteDatabase db;
    Cursor c;
    ArrayList<String> infoBD = new ArrayList<>();

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

    public void borraScores() {
        bd = new BaseDeDatos(contexto, "basededatos", null, 1);
        db = bd.getWritableDatabase();
        contexto.deleteDatabase("basededatos");
        db.close();
        actualizaScores();
    }

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
