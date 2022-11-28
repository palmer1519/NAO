package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import entidades.Productos;


public class DbProductos extends  DbHelper{
    Context contex;

    public DbProductos(@Nullable Context context) {
        super(context);
        this.contex = context;
    }
    public long insertarProductos(String nombre, String codigo, String cantidad, String precioV, String precioC) {
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(contex);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("codigo", codigo);
            values.put("cantidad", cantidad);
            values.put("precioV", precioV);
            values.put("precioC", precioC);

            id = db.insert(TABLE_PRODUCTOS, null, values);

        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public ArrayList<Productos> mostrarProductos(){
        DbHelper dbHelper = new DbHelper(contex);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Productos>listaProductos = new ArrayList<>();
        Productos productos;
        Cursor cursorProductos= null;
        cursorProductos = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS , null);

        if (cursorProductos.moveToFirst()) {
            do {
                productos = new Productos();
                productos.setId(cursorProductos.getInt(0));
                productos.setCodigo(cursorProductos.getString(1));
                productos.setNombre(cursorProductos.getString(2));
                productos.setCantidad(cursorProductos.getString(3));
                productos.setPresioC(cursorProductos.getString(4));
                productos.setPresioV(cursorProductos.getString(5));

                listaProductos.add(productos);
            } while (cursorProductos.moveToNext());
        }

        cursorProductos.close();

        return listaProductos;
    }

    public Productos verProductos(int id){
        DbHelper dbHelper = new DbHelper(contex);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Productos productos=null;
        Cursor cursorProductos;

        cursorProductos = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorProductos.moveToFirst()) {
            productos = new Productos();
            productos.setId(cursorProductos.getInt(0));
            productos.setCodigo(cursorProductos.getString(1));
            productos.setNombre(cursorProductos.getString(2));
            productos.setCantidad(cursorProductos.getString(3));
            productos.setPresioC(cursorProductos.getString(4));
            productos.setPresioV(cursorProductos.getString(5));
        }
        cursorProductos.close();
        return productos;
    }

    public boolean editarProducto(int id, String nombre, String codigo, String cantidad, String precioV, String precioC) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(contex);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_PRODUCTOS + " SET nombre = '" + nombre + "', codigo = '" + codigo + "'" +
                    ", cantidad = '" + cantidad + "'" + "', presioV = '" + precioV + "'" +  "', presioC = '" + precioC +
                    ", WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
    public boolean eliminarProducto(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(contex);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_PRODUCTOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
