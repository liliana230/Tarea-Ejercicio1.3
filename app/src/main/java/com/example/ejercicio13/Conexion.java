package com.example.ejercicio13;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexion extends SQLiteOpenHelper
{
    //Constructor creacion de la BD
    public Conexion(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //Tabla
        db.execSQL(BDatos.CreateTablePersonas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
       // Eliminacion de tablas
       db.execSQL(BDatos.DROPTABLEPersonas);
       onCreate(db);
    }
}
