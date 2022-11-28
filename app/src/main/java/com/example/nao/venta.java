package com.example.nao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class venta extends AppCompatActivity {

    ImageView btnScan;
    EditText busqueda_Inv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        busqueda_Inv = findViewById(R.id.Busqueda);
        btnScan= findViewById(R.id.Scan);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrador = new IntentIntegrator(venta.this);
                integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrador.setPrompt("Lector - CDP");
                integrador.setCameraId(0);
                integrador.setBeepEnabled(true);
                integrador.setBarcodeImageEnabled(true);
                integrador.initiateScan();
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
                busqueda_Inv.setText( result.getContents());
            }
        }else {
            super.onActivityResult(requesCode , resultCode, data);
        }
    }

}