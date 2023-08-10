package com.dannatrompeta.dasix;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLUtilities extends SQLiteOpenHelper {
    static String bdNombre="Productos";
    static int bdVersion=1;

    String sqlCreate = "CREATE TABLE Material (id integer primary key autoincrement, nombre TEXT, cantidad TEXT, tipo TEXT)";

    public SQLUtilities(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Material");
        onCreate(sqLiteDatabase);

    }
    // CONSULTA SIN RETORNO --MODIFICADOR DE ACCESO
    public static void consultaSinRetorno(Context context, String cadenaSql){
        // update, delete, insert
        SQLUtilities miConector=new SQLUtilities(context,bdNombre,null,bdVersion);
        SQLiteDatabase miBd=miConector.getWritableDatabase();
        miBd.execSQL(cadenaSql);
        miBd.close();
        miConector.close();
    }


    //CONSULTA CON RETORNO
    public static String[][] consultaConRetorno(Context context,String cadenaSql){
        // select
        String [][] datos=null;
        SQLUtilities miConector=new SQLUtilities(context,bdNombre, null,bdVersion);
        SQLiteDatabase miBd=miConector.getReadableDatabase();
        Cursor miCursor= miBd.rawQuery(cadenaSql, null);
        //CONTAR FILAS Y COLUMNAS
        int filas=miCursor.getCount();
        int columnas=miCursor.getColumnCount();

        //DOY LONGITUD A LA MATRIZ
        datos=new String[filas][columnas];
        int contadorFila=0;
        while (miCursor.moveToNext()){ //verdadero si tiene almenos una fila
            // recorrer las columnas de cada fila
            for (int contadorCol=0; contadorCol< columnas; contadorCol++){
                //ASIGNAMOS EL VALOR DE LA TABLA A CADA POSICION
                datos[contadorFila][contadorCol]=miCursor.getString(contadorCol);
            }
            contadorFila++;
        }
        return datos;
    }
}