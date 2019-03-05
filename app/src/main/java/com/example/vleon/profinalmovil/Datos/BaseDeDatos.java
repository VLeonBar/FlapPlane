package com.example.vleon.profinalmovil.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDatos extends SQLiteOpenHelper {
    String CREA_TABLA = " CREATE TABLE scores ( id INTEGER,nick TEXT, score INTEGER)";
    String INSERTA_TABLA = "INSERT INTO scores VALUES" +
            "(0,'???',0), " +
            "(1,'???',0), " +
            "(2,'???',0), " +
            "(3,'???',0), " +
            "(4,'???',0), " +
            "(5,'???',0), " +
            "(6,'???',0), " +
            "(7,'???',0), " +
            "(8,'???',0), " +
            "(9,'???',0) ";

    public BaseDeDatos(Context contexto, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREA_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
