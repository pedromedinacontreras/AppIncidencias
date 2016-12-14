package com.example.usuario.incidenciasapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.usuario.incidenciasapp.administrador.MainAdministradorActivity;
import com.example.usuario.incidenciasapp.models.CatalogoIncidencia;
import com.example.usuario.incidenciasapp.models.Categoria;
import com.example.usuario.incidenciasapp.models.Equipo;
import com.example.usuario.incidenciasapp.models.Incidencia;
import com.example.usuario.incidenciasapp.models.Usuario;
import com.example.usuario.incidenciasapp.models.UsuarioLogeado;
import com.example.usuario.incidenciasapp.tecnico.MainTecnicoActivity;

public class MainActivity extends AppCompatActivity {
    private final int DURACION_SPLASH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Usuario.getAll(MainActivity.this).size() < 1) {
            Usuario.newUsuario(this, "6672222222", "pedro@gmail.com", "1234", Usuario.TIPO_ADMINISTRADOR, Usuario.SIN_ESPECIALIDAD, "Pedro Medina");
            Usuario.newUsuario(this, "6672640676", "abrahams@gmail.com", "1234", Usuario.TIPO_TECNICO, Usuario.REDES, "Abrahams Gaxiola");
            Usuario.newUsuario(this, "6672640675", "zavalza@gmail.com", "1234", Usuario.TIPO_TECNICO, Usuario.HARDWARE, "Rafael Zavalza");
            Usuario.newUsuario(this, "6672640633", "perry@gmail.com", "1234", Usuario.TIPO_TECNICO, Usuario.SOFTWARE, "Perry Madrigal");
            Usuario.newUsuario(this, "6672444444", "gordaney@gmail.com", "1234", Usuario.TIPO_EMPLEADO, Usuario.SIN_ESPECIALIDAD, "Gordaney López");
            Usuario.newUsuario(this, "0000000000", "general@general.com", "1234", Usuario.TIPO_ADMINISTRADOR, Usuario.SIN_ESPECIALIDAD, "Usuario general");
        }

        if(Incidencia.getAll(MainActivity.this).size() < 1) {
            Incidencia.newIncidencia(this,"Teclado con ausencia de teclas", Incidencia.ESTATUS_DISPONIBLE, "Dell inspiron", "03 dic. 2016", Usuario.getEmpleados(this).get(0), (Categoria.HARDWARE),"Problema con el teclado");
            Incidencia.newIncidencia(this,"Impresora no conectada a la pc", Incidencia.ESTATUS_DISPONIBLE, "HP inyección de tinta", "02 dic. 2016", Usuario.getEmpleados(this).get(0), (Categoria.HARDWARE),"Impresora chafa");
            Incidencia.newIncidencia(this,"Pantalla de la computadora totalmente negra", Incidencia.ESTATUS_DISPONIBLE, "iMAC", "30 nov. 2016", Usuario.getEmpleados(this).get(0), (Categoria.HARDWARE), "Pantallazo negro");
            Incidencia.newIncidencia(this,"No puedo iniciar windows", Incidencia.ESTATUS_DISPONIBLE, "Dell inspiron", "03 dic. 2016", Usuario.getEmpleados(this).get(0), (Categoria.SOFTWARE), "Windows descompuesto");
            Incidencia.newIncidencia(this,"No puedo abrir el feis", Incidencia.ESTATUS_DISPONIBLE, "HP inyección de tinta", "02 dic. 2016", Usuario.getEmpleados(this).get(0), (Categoria.REDES), "sin red en facebook");
            Incidencia.newIncidencia(this,"No prende el proyector", Incidencia.ESTATUS_DISPONIBLE, "Benq", "30 nov. 2016", Usuario.getEmpleados(this).get(0), (Categoria.HARDWARE), "Proyector descoumpuesto");
        }

        if (Equipo.getAll(MainActivity.this).size() < 1) {
            String descripcion1 = "Pantalla de 24 pulgadas\nResolución HD\nUltra delgada";
            Equipo.newEquipo(this,"#1a2b3c4d","LG", descripcion1, "$2,000", "pedro@gmail.com","Monitor");
            Equipo.newEquipo(this,"#9P7K6J5H","Samsung", descripcion1, "$1,999", "zavalza@gmail.com","Pantalla plana");
        }

        if(CatalogoIncidencia.getAll(MainActivity.this).size() < 1) {
            CatalogoIncidencia.initCatalogoIncidencia(MainActivity.this);
        }

        for(Usuario user : Usuario.getAll(this)){
            Log.e("Usuario", user.toString());
        }
        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                if(UsuarioLogeado.getUsuarioLogeado(MainActivity.this) != null){
                    UsuarioLogeado usuarioLogeado = UsuarioLogeado.getUsuarioLogeado(MainActivity.this);
                    if(usuarioLogeado.getUsuario().getTipoUsuario() == Usuario.TIPO_ADMINISTRADOR){
                        intent = new Intent(MainActivity.this, MainAdministradorActivity.class);
                    } else if (usuarioLogeado.getUsuario().getTipoUsuario() == Usuario.TIPO_TECNICO) {
                        intent = new Intent(MainActivity.this, MainTecnicoActivity.class);
                    } else if (usuarioLogeado.getUsuario().getTipoUsuario() == Usuario.TIPO_EMPLEADO) {
                        intent = new Intent(MainActivity.this, MainTecnicoActivity.class);
                    }
                }
                startActivity(intent);
                finish();
            }
        }, DURACION_SPLASH);
    }
}
