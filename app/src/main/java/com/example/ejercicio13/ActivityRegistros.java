package com.example.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityRegistros extends AppCompatActivity {

    Conexion conexion;
    EditText id,nombres,apellidos,edad,correo,direccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        // llamar a la conexión de bd
        conexion = new Conexion (this, BDatos.NameDatabase, null, 1);

        //Area de Botones
        Button btnbuscar = (Button) findViewById (R.id.btnbuscar);
        Button btneliminar = (Button) findViewById (R .id.btneliminar);
        Button btnactualizar = (Button) findViewById (R.id.btnactualizar);

        id = (EditText) findViewById (R.id.txtid);
        nombres = (EditText) findViewById (R.id.txtnom);
        apellidos = (EditText) findViewById (R.id.txtape);
        edad = (EditText) findViewById (R.id.txtedad);
        correo = (EditText) findViewById (R.id.txtCorreo);
        direccion = (EditText) findViewById (R.id.txtdir);

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buscar();
            }
        });

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Actualizar();
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();
            }
        });
    }
    private void Buscar()
    {
        SQLiteDatabase db = conexion.getWritableDatabase ();
        //Configuracion de la sentencia SELECT
        String [] params = {id.getText (). toString()}; //parametro de la busqueda
        String [] fields = {BDatos.nombres,
                BDatos.apellidos,
                BDatos.edad,
                BDatos.correo,
                BDatos.direccion};
        String wherecond = BDatos.id + "=?";

        try {
            Cursor cdata = db.query (BDatos .tablaPersonas, fields, wherecond, params, null, null, null);

            cdata.moveToFirst ();
            nombres.setText (cdata.getString (0));
            apellidos.setText (cdata.getString (1));
            edad.setText (cdata.getString (2));
            correo.setText ( cdata.getString (3));
            direccion.setText ( cdata.getString (4));

            Toast.makeText (getApplicationContext (), "Busqueda realizada con exito", Toast.LENGTH_LONG) .show ();
        }

        catch (Exception ex)
        {
            ClearScreen ();
            Toast.makeText(getApplicationContext(),"Registro no encontrado", Toast.LENGTH_LONG).show();
        }
    }
    private void Eliminar()
    {
        SQLiteDatabase db = conexion.getWritableDatabase ();

        String [] params = {id.getText (). toString ()};
        String wherecond = BDatos.id + "=?";
        db.delete (BDatos.tablaPersonas, wherecond, params) ;
        Toast.makeText (getApplicationContext (), "Dato eliminado", Toast.LENGTH_LONG) .show ();
        ClearScreen ();
    }


   private void Actualizar()
{
    SQLiteDatabase db = conexion.getWritableDatabase ();

    String [] params = {id.getText (). toString ()};
    ContentValues valores = new ContentValues();

    valores.put (BDatos.nombres, nombres.getText (). toString ( ));
    valores.put (BDatos.apellidos, apellidos.getText (). toString ());
    valores.put (BDatos.edad, edad.getText (). toString ());
    valores.put (BDatos.correo, correo .getText (). toString ());
    valores.put (BDatos.direccion, direccion.getText (). toString ( ));

    db.update (BDatos.tablaPersonas, valores, BDatos.id + "=?", params);
    Toast.makeText (getApplicationContext (), "Dato actualizado", Toast.LENGTH_LONG).show();
    ClearScreen ();
}

    private void ClearScreen()
    {
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
        direccion.setText("");
    }
}