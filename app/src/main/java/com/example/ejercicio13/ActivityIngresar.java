package com.example.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityIngresar extends AppCompatActivity {

    EditText nombres,apellidos,edad,correo,direccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        nombres = (EditText) findViewById(R.id.txtnombre);
        apellidos= (EditText) findViewById(R.id.txtapellido);
        edad= (EditText) findViewById(R.id.txtedad);
        correo= (EditText) findViewById(R.id.txtcorreo);
        direccion=(EditText) findViewById(R.id.txtdireccion);
        Button btnguardar =(Button) findViewById(R.id.btnguardar);

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                GuardarPersonas();
            }
        });
    }

    private void GuardarPersonas()
    {
        Conexion conexion=new Conexion(this, BDatos.NameDatabase, null, 1);
        SQLiteDatabase db=conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(BDatos.nombres, nombres.getText().toString());
        valores.put(BDatos.apellidos, apellidos.getText().toString());
        valores.put(BDatos.edad, edad.getText().toString());
        valores.put(BDatos.correo, correo.getText().toString());
        valores.put(BDatos.direccion, direccion.getText().toString());


        Long resultado = db.insert(BDatos.tablaPersonas,BDatos.id, valores);
        Toast.makeText(getApplicationContext(),"Registro Ingresado con Exito : Codigo : " + resultado.toString(),Toast.LENGTH_LONG).show();

        db.close();

        LimpiarPantalla();
    }
    private void LimpiarPantalla()
    {
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
        direccion.setText("");
    }
}
