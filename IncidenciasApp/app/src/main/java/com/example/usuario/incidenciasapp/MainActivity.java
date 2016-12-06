package com.example.usuario.incidenciasapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.usuario.incidenciasapp.Models.Usuario;
import com.example.usuario.incidenciasapp.Models.UsuarioLogeado;

public class MainActivity extends AppCompatActivity {
    private final int DURACION_SPLASH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Usuario.newUsuario(this, "6672064536", "pedro@gmail.com", "1234", 1);
//        UsuarioLogeado.setUsuarioLogeado(this, Usuario.getAll(this).get(0));
//        UsuarioLogeado.setUsuarioLogeadoToNull(this);
        Toast.makeText(this, Usuario.getAll(this).size() + "", Toast.LENGTH_SHORT).show();
        for(Usuario user : Usuario.getAll(this)){
            Log.e("Usuario", user.toString());
        }
        // Intent intentLogin = new Intent( MainActivity.this, login.class );
        // startActivity( intentLogin );
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
