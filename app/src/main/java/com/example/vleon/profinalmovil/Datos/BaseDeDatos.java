package com.example.vleon.profinalmovil.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * The type Base de datos.
 */
public class BaseDeDatos extends SQLiteOpenHelper {
    /**
     * Sentencia Create a la tabla.
     */
    String CREA_TABLA = " CREATE TABLE scores ( id INTEGER,nick TEXT, score INTEGER)";
    /**
     * Sentencia Insert a la tabla.
     */
    String INSERTA_TABLA = "INSERT INTO scores VALUES" +
            "(0,'-  -  - >',0), " +
            "(1,'-  -  - >',0), " +
            "(2,'-  -  - >',0), " +
            "(3,'-  -  - >',0), " +
            "(4,'-  -  - >',0), " +
            "(5,'-  -  - >',0), " +
            "(6,'-  -  - >',0), " +
            "(7,'-  -  - >',0), " +
            "(8,'-  -  - >',0), " +
            "(9,'-  -  - >',0) ";

    /**
     * Constructor de la clase, inicializa las propiedades de la clase.
     *
     * @param contexto el contexto
     * @param name     el nombre de la base de datos
     * @param factory  the factory
     * @param version  the version
     */
    public BaseDeDatos(Context contexto, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREA_TABLA);
        db.execSQL(INSERTA_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
