package com.example.usuario.incidenciasapp;

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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.usuario.incidenciasapp.adapters.CatalogoAdapter;
import com.example.usuario.incidenciasapp.adapters.EquipoAdapter;
import com.example.usuario.incidenciasapp.adapters.UsuarioAdapter;
import com.example.usuario.incidenciasapp.administrador.ListaUsuariosActivity;
import com.example.usuario.incidenciasapp.models.CatalogoIncidencia;
import com.example.usuario.incidenciasapp.models.Equipo;
import com.example.usuario.incidenciasapp.models.Usuario;

import java.util.ArrayList;

import static android.view.View.GONE;

public class CatalogoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lmanager;
    private CatalogoAdapter adapter;
    ArrayList<CatalogoIncidencia> catalogos = new ArrayList<>();
    private Button btnNewCatalogo;
    private Toolbar toolbar;
    private boolean isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        isAdmin = getIntent().getBooleanExtra("isAdmin", false);
        toolbar = (Toolbar) findViewById(R.id.toolbar_catalogo);
        setSupportActionBar(toolbar);
        btnNewCatalogo = (Button) findViewById(R.id.btn_nuevo_catalogo);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_catalogo);
        catalogos = CatalogoIncidencia.getAll(CatalogoActivity.this);
        lmanager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lmanager);
        adapter = new CatalogoAdapter(this, catalogos);
        recyclerView.setAdapter(adapter);
        btnNewCatalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = createNuevoCatalogoDialog();
                dialog.show();
            }
        });
        if(!isAdmin) {
            btnNewCatalogo.setVisibility(GONE);
        }
    }

    public Dialog createNuevoCatalogoDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_nuevo_catalogo,null);
        final EditText edtTitulo = (EditText) view.findViewById(R.id.edt_titulo);
        final EditText edtPrioridad = (EditText) view.findViewById(R.id.edt_prioridad);
        final EditText edtTiempo = (EditText) view.findViewById(R.id.edt_tiempo);
        final EditText edtDescripcion = (EditText) view.findViewById(R.id.edt_descripcion);
        final EditText edtSolucion = (EditText) view.findViewById(R.id.edt_solucion);
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner_categoria);
        builder.setView(view)
                .setCancelable(false)
                .setTitle("Nuevo item de catálogo")
                .setPositiveButton("GUARDAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CatalogoIncidencia.newCatalogo(CatalogoActivity.this, edtTitulo.getText().toString(),
                                Integer.valueOf(edtPrioridad.getText().toString()), Integer.valueOf(edtTiempo.getText().toString()),
                                spinner.getSelectedItem().toString(), edtDescripcion.getText().toString(), edtSolucion.getText().toString());
                        Toast.makeText(CatalogoActivity.this, "Catálogo creado", Toast.LENGTH_SHORT).show();
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
        catalogos = CatalogoIncidencia.getAll(CatalogoActivity.this);
        adapter = new CatalogoAdapter(CatalogoActivity.this, catalogos);
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
