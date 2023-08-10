package com.dannatrompeta.dasix;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
public class ListarProductos extends AppCompatActivity {
    ListView listViewProducto;
    ArrayList<String> listaInformacion;
    ArrayList<Producto> listaProducto;
    SQLUtilities conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);


        listViewProducto = (ListView) findViewById(R.id.listViewPersonas);


        conexion = new SQLUtilities(this, "Material", null,1);

        consultarListaMateriales();
        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewProducto.setAdapter(adaptador);

        listViewProducto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Producto productoSeleccionado = (Producto) adapterView.getItemAtPosition(pos);
                // Creas el AlertDialog
                CharSequence[] opciones = new CharSequence[]{"Eliminar", "Cancelar"};
                AlertDialog.Builder builder = new AlertDialog.Builder(ListarProductos.this);
                builder.setTitle("Selecciona una opción");
                builder.setItems(opciones,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                Intent intento;
                                switch (which) {
                                    case 0:
                                        //Eliminar
                                        productoSeleccionado.eliminar(ListarProductos.this);
                                        break;
                                    case 1:
                                        // Cancelar
                                        dialog.dismiss();
                                        break;
                                }
                            }
                        });
                builder.show();


            }
        });

    }

    private void consultarListaMateriales() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Producto material = null;
        listaProducto = new ArrayList<Producto>();

        Cursor cursor = db.rawQuery("SELECT * FROM Material", null);

        while (cursor.moveToNext()){
            material = new Producto();
            material.setId(cursor.getInt(0));
            material.setNombre(cursor.getString(1));
            material.setCantidad(cursor.getString(2));
            material.setTipo(cursor.getString(3));

            listaProducto.add(material);

        }
        obtenerLista();



    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i=0; i<listaProducto.size(); i++){
            listaInformacion.add("Nombre: " + listaProducto.get(i).getNombre() + " | Cantidad: " + listaProducto.get(i).getCantidad() + " | Tipo: " + listaProducto.get(i).getTipo());
        }
    }
}