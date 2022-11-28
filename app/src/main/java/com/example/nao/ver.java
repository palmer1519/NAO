package com.example.nao;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import db.DbProductos;
import entidades.Productos;

public class ver extends AppCompatActivity {

    EditText Nombre;
    EditText Codigo;
    EditText PresioV;
    EditText PresioC;
    EditText Cantidad;

    ImageButton Guardar;
    ImageButton Editar;
    ImageButton Eliminar;

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
        Editar = findViewById(R.id.Editar);
        Eliminar = findViewById(R.id.Eliminar);


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

        final DbProductos dbProductos = new DbProductos(ver.this);
        productos = dbProductos.verProductos(id);

        if(productos != null){
            Nombre.setText(productos.getNombre());
            Codigo.setText(productos.getCodigo());
            Cantidad.setText(productos.getCantidad());
            PresioC.setText(productos.getPresioC());
            PresioV.setText(productos.getPresioV());

            Nombre.setInputType(InputType.TYPE_NULL);
            Codigo.setInputType(InputType.TYPE_NULL);
            Cantidad.setInputType(InputType.TYPE_NULL);
            PresioV.setInputType(InputType.TYPE_NULL);
            PresioC.setInputType(InputType.TYPE_NULL);

            Guardar.setVisibility(View.INVISIBLE);

        }
        Editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ver.this, editar.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ver.this);
                builder.setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbProductos.eliminarProducto(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    private void lista(){
        Intent intent = new Intent(this, productos.class);
        startActivity(intent);
    }
}

