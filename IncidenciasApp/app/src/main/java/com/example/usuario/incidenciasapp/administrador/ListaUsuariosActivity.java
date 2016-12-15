package com.example.usuario.incidenciasapp.administrador;

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
import android.widget.Toast;

import com.example.usuario.incidenciasapp.adapters.UsuarioAdapter;
import com.example.usuario.incidenciasapp.models.Usuario;
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
        usuarios = Usuario.getEmpleados(this);
        lmanager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lmanager);
        adapter = new UsuarioAdapter(this, usuarios, true);
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
        final EditText edtNombre = (EditText) view.findViewById(R.id.edt_nombre_usuario_nuevo);
        final EditText edtTel = (EditText) view.findViewById(R.id.edt_telefono_usuario_nuevo);
        final EditText edtCorreo = (EditText) view.findViewById(R.id.edt_correo_usuario_nuevo);
        final EditText edtContraseña = (EditText) view.findViewById(R.id.edt_contrasena_usuario_nuevo);
        builder.setView(view)
                .setTitle("Nuevo usuario")
                .setPositiveButton("GUARDAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Usuario.newUsuario(ListaUsuariosActivity.this, edtTel.getText().toString(), edtCorreo.getText().toString(),
                                edtContraseña.getText().toString(), Usuario.TIPO_EMPLEADO, Usuario.SIN_ESPECIALIDAD, edtNombre.getText().toString());
                        Toast.makeText(ListaUsuariosActivity.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                        updateAdapter();
                        adapter.notifyDataSetChanged();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();
    }

    private void updateAdapter(){
        usuarios = Usuario.getEmpleados(ListaUsuariosActivity.this);
        adapter = new UsuarioAdapter(ListaUsuariosActivity.this, usuarios, true);
        recyclerView.setAdapter(adapter);
    }
}
