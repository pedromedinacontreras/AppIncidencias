package com.example.usuario.incidenciasapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.usuario.incidenciasapp.administrador.MainAdministradorActivity;
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
            Incidencia.newIncidencia(this,"Teclado con ausencia de teclas", 4, Incidencia.ESTATUS_DISPONIBLE, "C4", "Dell inspiron", "03 dic. 2016", Usuario.getEmpleados(this).get(0), "Teclado falla", (Categoria.HARDWARE), Incidencia.ESFUERZO_BAJO);
            Incidencia.newIncidencia(this,"Impresora no conectada a la pc", 4, Incidencia.ESTATUS_DISPONIBLE, "D3", "HP inyección de tinta", "02 dic. 2016", Usuario.getEmpleados(this).get(0), "PC no manda imprimir", (Categoria.HARDWARE), Incidencia.ESFUERZO_BAJO);
            Incidencia.newIncidencia(this,"Pantalla de la computadora totalmente negra", 4, Incidencia.ESTATUS_DISPONIBLE, "CCDM", "iMAC", "30 nov. 2016", Usuario.getEmpleados(this).get(0), "Pantalla negra", (Categoria.HARDWARE), Incidencia.ESFUERZO_ALTO);
            Incidencia.newIncidencia(this,"No puedo iniciar windows", 4, Incidencia.ESTATUS_DISPONIBLE, "C4", "Dell inspiron", "03 dic. 2016", Usuario.getEmpleados(this).get(0), "Falla en windows", (Categoria.SOFTWARE), Incidencia.ESFUERZO_MEDIO);
            Incidencia.newIncidencia(this,"No puedo abrir el feis", 4, Incidencia.ESTATUS_DISPONIBLE, "D3", "HP inyección de tinta", "02 dic. 2016", Usuario.getEmpleados(this).get(0), "Feis falla", (Categoria.REDES), Incidencia.ESFUERZO_BAJO);
            Incidencia.newIncidencia(this,"No prende el proyector", 4, Incidencia.ESTATUS_DISPONIBLE, "CCDM", "Benq", "30 nov. 2016", Usuario.getEmpleados(this).get(0), "Proyector no prende", (Categoria.HARDWARE), Incidencia.ESFUERZO_MEDIO);
        }

        if (Equipo.getAll(MainActivity.this).size() < 1) {
            String descripcion1 = "Pantalla de 24 pulgadas\nResolución HD\nUltra delgada";
            Equipo.newEquipo(this,"#1a2b3c4d","LG", descripcion1, "$2,000", "pedro@gmail.com","Monitor");
            Equipo.newEquipo(this,"#9P7K6J5H","Samsung", descripcion1, "$1,999", "zavalza@gmail.com","Pantalla plana");
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
                    }
                    //Aquí va el resto de páginas principales, de acuerdo al tipo de usuario
                }
                startActivity(intent);
                finish();
            }
        }, DURACION_SPLASH);
    }
}
