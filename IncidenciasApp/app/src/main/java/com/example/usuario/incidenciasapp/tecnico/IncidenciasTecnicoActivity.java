package com.example.usuario.incidenciasapp.tecnico;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.usuario.incidenciasapp.R;
import com.example.usuario.incidenciasapp.adapters.IncidenciasPagerAdapter;
import com.example.usuario.incidenciasapp.adapters.IncidenciasTecnicoAdapter;
import com.example.usuario.incidenciasapp.administrador.ListaTecnicosActivity;
import com.example.usuario.incidenciasapp.models.Incidencia;
import com.example.usuario.incidenciasapp.models.Usuario;
import com.example.usuario.incidenciasapp.models.UsuarioLogeado;

public class IncidenciasTecnicoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private boolean usuario;
    private Button btnNueva;
    private boolean isUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencias_tecnico);

        isUsuario = getIntent().getBooleanExtra("usuario", false);
        toolbar = (Toolbar) findViewById(R.id.toolbar_tecnico_incidencias);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.pager_incidencias_tecnico);
        btnNueva = (Button) findViewById(R.id.btn_nueva_incidencia);
        if(!isUsuario)
            btnNueva.setVisibility(View.INVISIBLE);
        setSupportActionBar(toolbar);

        usuario = getIntent().getBooleanExtra("usuario",false);
        final IncidenciasTecnicoAdapter adapter = new IncidenciasTecnicoAdapter(this, getSupportFragmentManager(), isUsuario);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        btnNueva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNuevoUsuarioDialog().show();
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

    public void updateAdapter(){
        final IncidenciasTecnicoAdapter adapter = new IncidenciasTecnicoAdapter(this, getSupportFragmentManager(), isUsuario);
        viewPager.setAdapter(adapter);
    }

    public Dialog createNuevoUsuarioDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = IncidenciasTecnicoActivity.this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_nueva_incidencia,null);
        final EditText edtTitulo = (EditText) view.findViewById(R.id.edt_titulo);
        final EditText edtDescripcion = (EditText) view.findViewById(R.id.edt_descripcion);
        final EditText edtEquipo = (EditText) view.findViewById(R.id.edt_equipo);
        final EditText edtFecha = (EditText) view.findViewById(R.id.edt_fecha);
        final Spinner spinnerArea = (Spinner) view.findViewById(R.id.spinner_categoria);
        builder.setView(view)
                .setTitle("Nueva incidencia")
                .setPositiveButton("GUARDAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Incidencia.newIncidencia(IncidenciasTecnicoActivity.this, edtDescripcion.getText().toString(),
                                Incidencia.ESTATUS_DISPONIBLE, edtEquipo.getText().toString(), edtFecha.getText().toString(),
                                UsuarioLogeado.getUsuarioLogeado(IncidenciasTecnicoActivity.this).getUsuario(), spinnerArea.getSelectedItem().toString()
                                , edtTitulo.getText().toString());
                        Toast.makeText(IncidenciasTecnicoActivity.this, "Incidencia creada", Toast.LENGTH_SHORT).show();
                        updateAdapter();
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
}
