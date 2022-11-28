package com.example.nao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import db.DbProductos;

public class registro extends AppCompatActivity {

    ImageView btnScan;

    EditText Codigo;
    EditText Nombre;
    EditText PresioV;
    EditText PresioC;
    EditText Cantidad;
    ImageButton Guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Codigo = findViewById(R.id.Codigo);
        Nombre = findViewById(R.id.Nombre);
        PresioV = findViewById(R.id.PrecioV);
        PresioC = findViewById(R.id.PresioC);
        Cantidad = findViewById(R.id.Cantidad);
        Guardar = findViewById(R.id.guardar);
        btnScan= findViewById(R.id.btnScan2);



        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrador = new IntentIntegrator(registro.this);
                integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrador.setPrompt("Lector - CDP");
                integrador.setCameraId(0);
                integrador.setBeepEnabled(true);
                integrador.setBarcodeImageEnabled(true);
                integrador.initiateScan();
            }
        });

        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    DbProductos dbContactos = new DbProductos(registro.this);
                    long id = dbContactos.insertarProductos(Codigo.getText().toString(), Nombre.getText().toString(), PresioV.getText().toString(), PresioC.getText().toString(), Cantidad.getText().toString());

                    if (id > 0) {
                        Toast.makeText(registro.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(registro.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                }
        });
    }
    protected  void onActivityResult(int requesCode, int resultCode, Intent data){

        IntentResult result = IntentIntegrator.parseActivityResult( requesCode,resultCode, data);

        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "Lectora cancelada", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                Codigo.setText( result.getContents());
            }
        }else {
            super.onActivityResult(requesCode , resultCode, data);
        }
    }
    private void limpiar() {
        Nombre.setText("");
        Codigo.setText("");
        Cantidad.setText("");
        PresioC.setText("");
        PresioV.setText("");
    }
}