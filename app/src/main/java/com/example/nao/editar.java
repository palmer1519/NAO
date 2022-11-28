package com.example.nao;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import db.DbProductos;
import entidades.Productos;

public class editar extends AppCompatActivity {

    EditText Nombre;
    EditText Codigo;
    EditText PresioV;
    EditText PresioC;
    EditText Cantidad;

    ImageButton Guardar;
    ImageButton Eliminar;
    ImageButton Editar;

    boolean correcto = false;
    Productos productos;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);


        Nombre = findViewById(R.id.Nombre);
        Codigo = findViewById(R.id.Codigo);
        PresioV = findViewById(R.id.PrecioV);
        PresioC = findViewById(R.id.PresioC);
        Cantidad = findViewById(R.id.Cantidad);
        Guardar = findViewById(R.id.Guardar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbProductos dbProductos = new DbProductos(editar.this);
        productos = dbProductos.verProductos(id);

        if(productos != null){
            Nombre.setText(productos.getNombre());
            Codigo.setText(productos.getCodigo());
            Cantidad.setText(productos.getCantidad());
            PresioC.setText(productos.getPresioC());
            PresioV.setText(productos.getPresioV());


        }

        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Nombre.getText().toString().equals("") && !Codigo.getText().toString().equals("")) {
                   correcto =  dbProductos.editarProducto(id, Nombre.getText().toString(), Codigo.getText().toString(), Cantidad.getText().toString(), PresioC.getText().toString(), PresioV.getText().toString());

                    if(correcto){
                        Toast.makeText(editar.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(editar.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(editar.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, ver.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
