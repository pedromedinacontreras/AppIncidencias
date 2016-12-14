package com.example.usuario.incidenciasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.incidenciasapp.administrador.MainAdministradorActivity;
import com.example.usuario.incidenciasapp.models.Usuario;
import com.example.usuario.incidenciasapp.models.UsuarioLogeado;
import com.example.usuario.incidenciasapp.tecnico.MainTecnicoActivity;
import com.example.usuario.incidenciasapp.usuario.MainUsuarioActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edtCorreo;
    private EditText edtContrasena;
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtCorreo = (EditText) findViewById(R.id.edt_correo);
        edtContrasena = (EditText) findViewById(R.id.edt_contrasena);
        btnEntrar = (Button) findViewById(R.id.btn_entrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String correo = edtCorreo.getText().toString();
                final String contraseña = edtContrasena.getText().toString();
                boolean inicioSesion = Usuario.inicioSesion(LoginActivity.this, correo, contraseña);
                Usuario user = Usuario.getUsuarioByEmail(LoginActivity.this, correo);
                if(inicioSesion) {
                  Toast.makeText(LoginActivity.this, "Inicio sesión correcto", Toast.LENGTH_SHORT).show();
                    UsuarioLogeado.setUsuarioLogeado(LoginActivity.this, user);
                    Intent intent = new Intent();
                    if(UsuarioLogeado.getUsuarioLogeado(LoginActivity.this).getUsuario().getTipoUsuario() == Usuario.TIPO_ADMINISTRADOR){
                        intent = new Intent(LoginActivity.this, MainAdministradorActivity.class);
                    } else if (UsuarioLogeado.getUsuarioLogeado(LoginActivity.this).getUsuario().getTipoUsuario() == Usuario.TIPO_TECNICO){
                        intent = new Intent(LoginActivity.this, MainTecnicoActivity.class);
                    } else if (UsuarioLogeado.getUsuarioLogeado(LoginActivity.this).getUsuario().getTipoUsuario() == Usuario.TIPO_EMPLEADO) {
                        intent = new Intent(LoginActivity.this, MainUsuarioActivity.class);
                    }
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Correo y/o contraseña incorrecto(s)", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
