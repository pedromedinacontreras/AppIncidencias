package com.example.usuario.incidenciasapp.Administrador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.example.usuario.incidenciasapp.Adapters.IncidenciaAdapter;
import com.example.usuario.incidenciasapp.Adapters.UsuarioAdapter;
import com.example.usuario.incidenciasapp.Models.Incidencia;
import com.example.usuario.incidenciasapp.Models.Usuario;
import com.example.usuario.incidenciasapp.R;

import java.util.ArrayList;

public class ListaUsuariosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lmanager;
    private UsuarioAdapter adapter;
    ArrayList<Usuario> usuarios = new ArrayList<>();
    private Button btnNewUser;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
        toolbar = (Toolbar) findViewById(R.id.toolbar_lista_usuarios);
        setSupportActionBar(toolbar);

        btnNewUser = (Button) findViewById(R.id.btn_nuevo_usuario);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_lista_usuarios);
        usuarios = Usuario.getAll(this);
        lmanager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lmanager);
        adapter = new UsuarioAdapter(this,usuarios);
        recyclerView.setAdapter(adapter);
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
