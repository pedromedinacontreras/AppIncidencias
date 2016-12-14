package com.example.usuario.incidenciasapp.usuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.usuario.incidenciasapp.LoginActivity;
import com.example.usuario.incidenciasapp.R;
import com.example.usuario.incidenciasapp.administrador.ListaEquipoActivity;
import com.example.usuario.incidenciasapp.models.UsuarioLogeado;
import com.example.usuario.incidenciasapp.tecnico.IncidenciasTecnicoActivity;
import com.example.usuario.incidenciasapp.tecnico.MainTecnicoActivity;

public class MainUsuarioActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tvSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_usuario);
        toolbar = (Toolbar) findViewById(R.id.toolbar_usuario_main);
        setSupportActionBar(toolbar);
        tvSalir = (TextView) findViewById(R.id.tv_toolbar_salir);
        tvSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioLogeado.setUsuarioLogeadoToNull(MainUsuarioActivity.this);
                Intent i = new Intent(MainUsuarioActivity.this, LoginActivity.class);
                finish();
                startActivity(i);
            }
        });
    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_usuario_incidencias_en_proceso:
                Intent i = new Intent(MainUsuarioActivity.this, IncidenciasTecnicoActivity.class);
                i.putExtra("usuario", true);
                startActivity(i);
                break;
            default:
                break;
        }
    }


}
