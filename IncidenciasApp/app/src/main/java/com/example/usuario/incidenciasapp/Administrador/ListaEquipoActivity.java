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
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.incidenciasapp.Adapters.EquipoAdapter;
import com.example.usuario.incidenciasapp.Adapters.UsuarioAdapter;
import com.example.usuario.incidenciasapp.Models.Equipo;
import com.example.usuario.incidenciasapp.Models.Incidencia;
import com.example.usuario.incidenciasapp.Models.Usuario;
import com.example.usuario.incidenciasapp.R;
import com.example.usuario.incidenciasapp.RecyclerItemClickListener;

import java.util.ArrayList;

import io.realm.Realm;

public class ListaEquipoActivity extends AppCompatActivity implements EquipoAdapter.EquipoInterface{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lmanager;
    private EquipoAdapter adapter;
    ArrayList<Equipo> equipos = new ArrayList<>();
    private Button btnNewEquipo;
    private Toolbar toolbar;
    private Equipo equipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_equipo);

        toolbar = (Toolbar) findViewById(R.id.toolbar_lista_equipos);
        setSupportActionBar(toolbar);
        btnNewEquipo = (Button) findViewById(R.id.btn_nuevo_equipo);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_lista_equipo);
        equipos = Equipo.getAll(this);
        lmanager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lmanager);
        adapter = new EquipoAdapter(this,equipos);
        recyclerView.setAdapter(adapter);
        adapter.EquipoInterface(ListaEquipoActivity.this);
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

    public Dialog createDetalleEquipoDialog(final int position){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        equipo = equipos.get(position);
        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_detalle_equipo,null);
        TextView tvDescripciones = (TextView) view.findViewById(R.id.tv_descripciones);
        TextView tvCosto = (TextView) view.findViewById(R.id.tv_costo);
        tvDescripciones.setText(equipo.getDescripcion());
        tvCosto.setText(equipo.getPrecio());
        builder.setView(view)
                .setTitle("Detalle equipo")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        return builder.create();
    }

    @Override
    public void onEliminar(int i, String numeroSerie) {
        Toast.makeText(ListaEquipoActivity.this,"Llamando onEliminar"+numeroSerie,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditar(int i, String numeroSerie) {
        Toast.makeText(ListaEquipoActivity.this,"Llamando onEditar"+numeroSerie,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetalle(int position) {
        Toast.makeText(ListaEquipoActivity.this,"onItemClick"+position,Toast.LENGTH_SHORT).show();
        createDetalleEquipoDialog(position).show();
    }
}
