package com.example.usuario.incidenciasapp.Administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.usuario.incidenciasapp.R;

public class MainAdministradorActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_administrador);
        toolbar = (Toolbar) findViewById(R.id.toolbar_admin_main);
        setSupportActionBar(toolbar);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_admin_incidencias:
                Toast.makeText(this, "btn_admin_incidencias", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainAdministradorActivity.this, IncidenciasAdministradorActivity.class);
                startActivity(i);
                break;
            case R.id.btn_admin_usuarios:
                Toast.makeText(this, "btn_admin_usuarios", Toast.LENGTH_SHORT).show();
                Intent iListaUsuarios = new Intent(MainAdministradorActivity.this, ListaUsuariosActivity.class);
                startActivity(iListaUsuarios);
                break;
            case R.id.btn_admin_tecnicos:
                Toast.makeText(this, "btn_admin_tecnicos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_admin_equipos:
                Toast.makeText(this, "btn_admin_equipos", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
