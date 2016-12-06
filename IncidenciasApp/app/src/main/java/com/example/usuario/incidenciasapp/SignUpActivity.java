package com.example.usuario.incidenciasapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.usuario.incidenciasapp.Models.Usuario;

import io.realm.Realm;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtApellidos;
    private EditText edtEmail;
    private EditText edtTelefono;
    private EditText edtTipoUsuario;
    private EditText edtUsuario;
    private EditText edtContrasena;
    private Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtNombre = (EditText) findViewById(R.id.edt_nombre);
        edtApellidos = (EditText) findViewById(R.id.edt_apellidos);
        edtEmail = (EditText) findViewById(R.id.edt_correo);
        edtTelefono = (EditText) findViewById(R.id.edt_telefono);
        edtTipoUsuario = (EditText) findViewById(R.id.edt_tipo);
        edtUsuario = (EditText) findViewById(R.id.edt_usuario);
        edtContrasena = (EditText) findViewById(R.id.edt_contrasena);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = edtNombre.getText().toString();
                String apellidos = edtApellidos.getText().toString();
                final String email = edtEmail.getText().toString();
                final String telefono = edtTelefono.getText().toString();
                final String tipoUsuario = edtTipoUsuario.getText().toString();
                final String usuario = edtUsuario.getText().toString();
                final String contrasena = edtContrasena.getText().toString();
                Usuario.newUsuario(SignUpActivity.this, telefono, email, contrasena, Integer.valueOf(tipoUsuario));
            }
        });
    }
}
