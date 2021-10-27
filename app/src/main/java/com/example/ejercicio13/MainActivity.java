package com.example.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btningresar= (Button) findViewById(R.id.btningresar);
        Button btnaccion= (Button) findViewById(R.id.btnaccion);


        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ActivityIngresar.class);
                startActivity(intent);
            }
        });

        btnaccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ActivityRegistros.class);
                startActivity(intent);
            }
        });
    }
}