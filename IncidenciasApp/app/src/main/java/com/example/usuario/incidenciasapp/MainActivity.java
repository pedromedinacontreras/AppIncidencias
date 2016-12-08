package com.example.usuario.incidenciasapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.usuario.incidenciasapp.Administrador.MainAdministradorActivity;
import com.example.usuario.incidenciasapp.Models.Incidencia;
import com.example.usuario.incidenciasapp.Models.Usuario;
import com.example.usuario.incidenciasapp.Models.UsuarioLogeado;

public class MainActivity extends AppCompatActivity {
    private final int DURACION_SPLASH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Usuario.newUsuario(this, "6672222222", "pedro@gmail.com", "1234", Usuario.TIPO_ADMINISTRADOR);
       // Usuario.newUsuario(this, "6672640676", "abrahams@gmail.com", "1234", Usuario.TIPO_TECNICO);
       // Usuario.newUsuario(this, "6672640675", "zavalza@gmail.com", "1234", Usuario.TIPO_TECNICO);
       // Usuario.newUsuario(this, "6672444444", "gordaney@gmail.com", "1234", Usuario.TIPO_EMPLEADO);

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
//        UsuarioLogeado.setUsuarioLogeado(this, Usuario.getAll(this).get(0));
//        UsuarioLogeado.setUsuarioLogeadoToNull(this);
        Toast.makeText(this, Usuario.getAll(this).size() + "", Toast.LENGTH_SHORT).show();
        for(Usuario user : Usuario.getAll(this)){
            Log.e("Usuario", user.toString());
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
