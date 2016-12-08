package com.example.usuario.incidenciasapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.usuario.incidenciasapp.Administrador.MainAdministradorActivity;
import com.example.usuario.incidenciasapp.Models.Descripcion;
import com.example.usuario.incidenciasapp.Models.Equipo;
import com.example.usuario.incidenciasapp.Models.Incidencia;
import com.example.usuario.incidenciasapp.Models.Usuario;
import com.example.usuario.incidenciasapp.Models.UsuarioLogeado;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private final int DURACION_SPLASH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Usuario.newUsuario(this, "6672222222", "pedro@gmail.com", "1234", Usuario.TIPO_ADMINISTRADOR);
       Usuario.newUsuario(this, "6672333333", "nataniel@gmail.com", "1234", Usuario.TIPO_TECNICO);
      //  Usuario.newUsuario(this, "6672444444", "gordaney@gmail.com", "1234", Usuario.TIPO_EMPLEADO);

//        Incidencia.newIncidencia(this,"Teclado con ausencia de teclas", 4, Incidencia.ESTATUS_EN_PROCESO, "C4", "Dell inspiron", "03 dic. 2016", Usuario.getAll(this).get(0),Usuario.getAll(this).get(1));
//        Incidencia.newIncidencia(this,"Impresora no conectada a la pc", 4, Incidencia.ESTATUS_TERMINADA, "D3", "HP inyección de tinta", "02 dic. 2016", Usuario.getAll(this).get(0),Usuario.getAll(this).get(1));
//        Incidencia.newIncidencia(this,"Pantalla de la computadora totalmente negra", 4, Incidencia.ESTATUS_DISPONIBLE, "CCDM", "iMAC", "30 nov. 2016", Usuario.getAll(this).get(0),Usuario.getAll(this).get(1));
//        Incidencia.newIncidencia(this,"Teclado con ausencia de teclas", 4, Incidencia.ESTATUS_TERMINADA, "C4", "Dell inspiron", "03 dic. 2016", Usuario.getAll(this).get(0),Usuario.getAll(this).get(1));
//        Incidencia.newIncidencia(this,"Impresora no conectada a la pc", 4, Incidencia.ESTATUS_DISPONIBLE, "D3", "HP inyección de tinta", "02 dic. 2016", Usuario.getAll(this).get(0),Usuario.getAll(this).get(1));
//        Incidencia.newIncidencia(this,"Pantalla de la computadora totalmente negra", 4, Incidencia.ESTATUS_EN_PROCESO, "CCDM", "iMAC", "30 nov. 2016", Usuario.getAll(this).get(0),Usuario.getAll(this).get(1));
//        Incidencia.newIncidencia(this,"Teclado con ausencia de teclas", 4, Incidencia.ESTATUS_EN_PROCESO, "C4", "Dell inspiron", "03 dic. 2016", Usuario.getAll(this).get(0),Usuario.getAll(this).get(1));
//        Incidencia.newIncidencia(this,"Impresora no conectada a la pc", 4, Incidencia.ESTATUS_TERMINADA, "D3", "HP inyección de tinta", "02 dic. 2016", Usuario.getAll(this).get(0),Usuario.getAll(this).get(1));
//        Incidencia.newIncidencia(this,"Pantalla de la computadora totalmente negra", 4, Incidencia.ESTATUS_DISPONIBLE, "CCDM", "iMAC", "30 nov. 2016", Usuario.getAll(this).get(0),Usuario.getAll(this).get(1));
//        Incidencia.newIncidencia(this,"Teclado con ausencia de teclas", 4, Incidencia.ESTATUS_TERMINADA, "C4", "Dell inspiron", "03 dic. 2016", Usuario.getAll(this).get(0),Usuario.getAll(this).get(1));
//        Incidencia.newIncidencia(this,"Impresora no conectada a la pc", 4, Incidencia.ESTATUS_DISPONIBLE, "D3", "HP inyección de tinta", "02 dic. 2016", Usuario.getAll(this).get(0),Usuario.getAll(this).get(1));
//        Incidencia.newIncidencia(this,"Pantalla de la computadora totalmente negra", 4, Incidencia.ESTATUS_EN_PROCESO, "CCDM", "iMAC", "30 nov. 2016", Usuario.getAll(this).get(0),Usuario.getAll(this).get(1));
       // UsuarioLogeado.setUsuarioLogeado(this, Usuario.getAll(this).get(0));
      //  UsuarioLogeado.setUsuarioLogeadoToNull(this);
        Toast.makeText(this, Usuario.getAll(this).size() + "", Toast.LENGTH_SHORT).show();
        for(Usuario user : Usuario.getAll(this)){
            Log.e("Usuario", user.toString());
        }

        ArrayList<Usuario> usuarios = Usuario.getAll(this);

       // Equipo.newEquipo(this, "serie12","APPLE","Computadora macbook pro 2015 core i5 8GB Ram",1500,usuarios.get(0));
     //   Equipo.newEquipo(this, "serie22","APPLE","Computadora mac mini 2016 core i5 8GB RAM",1000,usuarios.get(0));
        Equipo.newEquipo(this, "serie32","HP","Impresora de laser",1200,usuarios.get(1));
       Equipo.newEquipo(this, "serie42","CISCO","Router 8052 con 24 puertos",1500,usuarios.get(2));
        Toast.makeText(this, Equipo.getAll(this).size() + "equipos ", Toast.LENGTH_LONG).show();

        for(Equipo equipo : Equipo.getAll(this)){
            Log.e("Equipo: ", equipo.toString());
        }


        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                if(UsuarioLogeado.getUsuarioLogeado(MainActivity.this) != null){
                    UsuarioLogeado usuarioLogeado = UsuarioLogeado.getUsuarioLogeado(MainActivity.this);
                    if(usuarioLogeado.getUsuario().getTipoUsuario() == 0){
                        intent = new Intent(MainActivity.this, MainAdministradorActivity.class);
                    }
                    //Aquí va el resto de páginas principales, de acuerdo al tipo de usuario
                }
                startActivity(intent);
                finish();
            }
        }, DURACION_SPLASH);
    }
}
