package com.dannatrompeta.dasix;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Producto {
    public int id;

    public String  nombre, cantidad, tipo,descripcion;



    public Producto() {

    }

    public Producto(int id, String nombre, String cantidad, String tipo,String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    //CRUD - metodos
    // Listar
    public static String[][] listarProducto(Context context,String filtro, String orden){
        String miCadenaSql="select * from Material";
        if(filtro !=null) miCadenaSql +=" where "+filtro;
        if(orden !=null) miCadenaSql +=" order by "+orden;
        return SQLUtilities.consultaConRetorno(context, miCadenaSql);
    }

    public static List<Producto> listarProductoObj(Context context, String filtro, String orden){
        String[][] datosProducto=Producto.listarProducto(context,filtro,orden);
        List<Producto> marcasList=new ArrayList<>();
        if(datosProducto !=null){
            // Recorrer los datos
            for(int contador=0; contador < datosProducto.length; contador++){
                Producto miObjetoProducto=new Producto();
                miObjetoProducto.id= Integer.parseInt(datosProducto[contador][0]);
                miObjetoProducto.nombre=datosProducto[contador][1];
                miObjetoProducto.descripcion=datosProducto[contador][2];
                marcasList.add(miObjetoProducto);
            }
        }
        return marcasList;
    }


    // Eliminar una PRODUCTO
    public void eliminar(Context context) {
        List<Producto> listProducto = Producto .listarProductoObj(context,"id="+id,null);
        if ((long)listProducto.size()>0){
            for (int i =0;i<(long) listProducto.size();i++){
                listProducto.get(i).eliminar(context);
            }
        }
        String cadenaSql = "delete from Material where id=" + id;
        SQLUtilities.consultaSinRetorno(context, cadenaSql);
}
}
