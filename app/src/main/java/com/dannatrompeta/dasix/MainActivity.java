package com.dannatrompeta.dasix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


    public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        private Button btnReg , btnBu, btnLi ;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            btnReg = findViewById(R.id.btnReg);
            btnBu = findViewById(R.id.btnBu);
            btnLi = findViewById(R.id.btnLi);

            btnReg.setOnClickListener(this);
            btnBu.setOnClickListener(this);
            btnLi.setOnClickListener(this);

            SQLUtilities conexion = new SQLUtilities(this, "Material", null, 1);
        }

        //Metodo para mostrar y ocultar el menu
        public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.overflow, menu);
            return true;
        }

        //Metodo para asignar las funciones correspondientes a las opciones
        public boolean onOptionsItemSelected(MenuItem item){
            int id = item.getItemId();

            if (id == R.id.opRegistrar){
                Intent intent = new Intent( MainActivity.this, RegistrarProducto.class);
                startActivity(intent);

            }else if ( id == R.id.opMostrar ){
                Intent intent = new Intent( MainActivity.this, MostrarProductos.class);
                startActivity(intent);

            }else if(id == R.id.opListar){
                Intent intent = new Intent( MainActivity.this, ListarProductos.class);
                startActivity(intent);

            }else{
                finish();
                System.exit(0);
            }
            return super.onOptionsItemSelected(item);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnReg:
                    Intent in = new Intent( MainActivity.this, RegistrarProducto.class);
                    startActivity(in);
                    break;
                case R.id.btnBu:
                    Intent in2 = new Intent( MainActivity.this, MostrarProductos.class);
                    startActivity(in2);
                    break;
                case R.id.btnLi:
                    Intent in3 = new Intent( MainActivity.this, ListarProductos.class);
                    startActivity(in3);
                    break;

            }

        }
    }
