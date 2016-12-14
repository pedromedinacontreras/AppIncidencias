package com.example.usuario.incidenciasapp.tecnico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.incidenciasapp.CatalogoActivity;
import com.example.usuario.incidenciasapp.LoginActivity;
import com.example.usuario.incidenciasapp.models.UsuarioLogeado;
import com.example.usuario.incidenciasapp.R;
import com.example.usuario.incidenciasapp.administrador.IncidenciasAdministradorActivity;
import com.example.usuario.incidenciasapp.administrador.ListaEquipoActivity;
import com.example.usuario.incidenciasapp.administrador.ListaTecnicosActivity;
import com.example.usuario.incidenciasapp.administrador.ListaUsuariosActivity;
import com.example.usuario.incidenciasapp.administrador.MainAdministradorActivity;
import com.example.usuario.incidenciasapp.models.UsuarioLogeado;

public class MainTecnicoActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tvSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tecnico);

        toolbar = (Toolbar) findViewById(R.id.toolbar_tecnico_main);
        setSupportActionBar(toolbar);
        tvSalir = (TextView) findViewById(R.id.tv_toolbar_salir);
        tvSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioLogeado.setUsuarioLogeadoToNull(MainTecnicoActivity.this);
                Intent i = new Intent(MainTecnicoActivity.this, LoginActivity.class);
                finish();
                startActivity(i);
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_tecnicos_incidencias:
                Intent i = new Intent(MainTecnicoActivity.this, IncidenciasTecnicoActivity.class);
                startActivity(i);
                break;
            case R.id.btn_tecnicos_equipos:
                Intent iListaEquipo = new Intent(MainTecnicoActivity.this, ListaEquipoActivity.class);
                iListaEquipo.putExtra("tecnico", true);
                startActivity(iListaEquipo);
                break;
            case R.id.btn_catalogo:
                Intent iCatalogo = new Intent(MainTecnicoActivity.this, CatalogoActivity.class);
                startActivity(iCatalogo);
            default:
                break;
        }
    }
}
