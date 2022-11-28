package com.example.nao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

import adaptadores.ListaProductosAdapter;
import db.DbProductos;

public class Inventario extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView txtbuscar;
    ListaProductosAdapter adapter;
    ArrayList<productos> ListaArrayProductos;
    RecyclerView listaProduc;
    ImageView btnScan;
    EditText busqueda_Inv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        txtbuscar = findViewById(R.id.txtbuscar);
        listaProduc = findViewById(R.id.listaProduct);


        listaProduc.setLayoutManager(new LinearLayoutManager(this));

        DbProductos dbProductos = new DbProductos(this);
        ListaArrayProductos = new ArrayList<>();

        adapter = new ListaProductosAdapter(dbProductos.mostrarProductos());
        listaProduc.setAdapter(adapter);

        txtbuscar.setOnQueryTextListener(this);


    }

    public void irAregistro(View view){
        Intent i = new Intent(this, registro.class);
        startActivity(i);
    }
    public void irAproductos(View view){
        Intent i = new Intent(this, productos.class);
        startActivity(i);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filtrado(newText);
        return false;
    }
}


