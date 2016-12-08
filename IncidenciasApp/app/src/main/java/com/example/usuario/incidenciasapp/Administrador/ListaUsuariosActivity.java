package com.example.usuario.incidenciasapp.Administrador;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

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
        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = createNuevoUsuarioDialog();
                dialog.show();
            }
        });
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

    public Dialog createNuevoUsuarioDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = ListaUsuariosActivity.this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_nuevo_usuario,null);
//        textComentario = (EditText) view.findViewById(R.id.comentario_valorado);
//        ratingBarComentario = (RatingBar) view.findViewById(R.id.rating_producto);
        builder.setView(view)
                .setTitle("Nuevo usuario")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();
    }
}
