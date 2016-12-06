package com.example.usuario.incidenciasapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.incidenciasapp.Models.Usuario;
import com.example.usuario.incidenciasapp.Models.UsuarioLogeado;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

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
                if(inicioSesion) {
                  Toast.makeText(LoginActivity.this, "Inicio sesión correcto", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Correo y/o contraseña incorrecto(s)", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
