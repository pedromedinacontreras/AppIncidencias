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
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.incidenciasapp.adapters.EquipoAdapter;
import com.example.usuario.incidenciasapp.models.Equipo;
import com.example.usuario.incidenciasapp.models.Usuario;
import com.example.usuario.incidenciasapp.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ListaEquipoActivity extends AppCompatActivity implements EquipoAdapter.EquipoInterface{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lmanager;
    private EquipoAdapter adapter;
    ArrayList<Equipo> equipos = new ArrayList<>();
    private Button btnNewEquipo;
    private Toolbar toolbar;
    private Equipo equipo;
    private boolean tecnico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_equipo);

        tecnico = getIntent().getBooleanExtra("tecnico",false);
        toolbar = (Toolbar) findViewById(R.id.toolbar_lista_equipos);
        setSupportActionBar(toolbar);
        btnNewEquipo = (Button) findViewById(R.id.btn_nuevo_catalogo);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_lista_equipo);
        equipos = Equipo.getAll(this);
        lmanager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lmanager);
        adapter = new EquipoAdapter(this,equipos, tecnico);
        recyclerView.setAdapter(adapter);
        adapter.EquipoInterface(ListaEquipoActivity.this);
        btnNewEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewEquipoDialog().show();
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
    public void onEliminar(final int position, String numeroSerie) {
        Toast.makeText(ListaEquipoActivity.this,"Llamando onEliminar"+numeroSerie,Toast.LENGTH_SHORT).show();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        equipo = equipos.get(position);
        builder.setMessage("¿Quieres eliminar este equipo?")
                .setTitle("Eliminar equipo")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Equipo.deleteEquipo(ListaEquipoActivity.this, equipo.getNumeroSerie());
//                        equipos.remove(position);
//                        adapter.notifyItemRemoved(position);
//                        adapter.notifyDataSetChanged();
                        updateAdapter();
                    }
                });
        builder.create().show();
    }

    @Override
    public void onEditar(int position, String numeroSerie) {
        Toast.makeText(ListaEquipoActivity.this,"Llamando onEditar"+numeroSerie,Toast.LENGTH_SHORT).show();
        createUpdateEquipoDialog(position, numeroSerie).show();
    }

    @Override
    public void onDetalle(int position) {
        Toast.makeText(ListaEquipoActivity.this,"onItemClick"+position,Toast.LENGTH_SHORT).show();
        createDetalleEquipoDialog(position).show();
    }

    public Dialog createUpdateEquipoDialog(final int position, String numeroSerie){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        String numeroSerie = equipos.get(position).getNumeroSerie();
        equipo = Equipo.getEquipoByNumeroSerie(ListaEquipoActivity.this, numeroSerie);
        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_update_equipo,null);
        final EditText edtUsuario = (EditText) view.findViewById(R.id.edt_update_usuario);
        final EditText edtDescripcion1 = (EditText) view.findViewById(R.id.edt_descripcion1);
        final EditText edtDescripcion2 = (EditText) view.findViewById(R.id.edt_descripcion2);
        final EditText edtDescripcion3 = (EditText) view.findViewById(R.id.edt_descripcion3);
        final EditText edtDescripcion4 = (EditText) view.findViewById(R.id.edt_descripcion4);
        edtUsuario.setText(equipo.getUsuario());
        builder.setView(view)
                .setTitle("Detalle equipo")
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(edtUsuario.getText().toString().equals("") || edtDescripcion1.getText().toString().equals("")) {
                            Toast.makeText(ListaEquipoActivity.this,"No se pudo hacer el update",Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();
                        } else {
                            String usuario = edtUsuario.getText().toString();
                            if(Usuario.getUsuarioByEmail(ListaEquipoActivity.this, usuario) == null) {
                                Toast.makeText(ListaEquipoActivity.this,"El usuario no existe",Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                            String descripcion = edtDescripcion1.getText().toString();
                            if(!edtDescripcion2.getText().toString().equals("")) {
                                descripcion = descripcion + "\n" + edtDescripcion2.getText().toString();
                            }

                            if(!edtDescripcion3.getText().toString().equals("")) {
                                descripcion = descripcion + "\n" + edtDescripcion3.getText().toString();
                            }

                            if(!edtDescripcion4.getText().toString().equals("")) {
                                descripcion = descripcion + "\n" + edtDescripcion4.getText().toString();
                            }

                            Equipo.updateEquipo(ListaEquipoActivity.this, equipo.getNumeroSerie(), descripcion, usuario);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
        return builder.create();
    }

    public Dialog createNewEquipoDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_nuevo_equipo,null);
        final EditText edtNombreGral = (EditText) view.findViewById(R.id.edt_nombre_gral);
        final EditText edtMarca = (EditText) view.findViewById(R.id.edt_marca);
        final EditText edtPrecio = (EditText) view.findViewById(R.id.edt_precio);
        final EditText edtNumeroSerie = (EditText) view.findViewById(R.id.edt_numero_serie);
        final EditText edtUsuario = (EditText) view.findViewById(R.id.edt_usuario);
        final EditText edtDescripcion1 = (EditText) view.findViewById(R.id.edt_descripcion1);
        final EditText edtDescripcion2 = (EditText) view.findViewById(R.id.edt_descripcion2);
        final EditText edtDescripcion3 = (EditText) view.findViewById(R.id.edt_descripcion3);
        final EditText edtDescripcion4 = (EditText) view.findViewById(R.id.edt_descripcion4);
        builder.setView(view)
                .setTitle("Detalle equipo")
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(edtUsuario.getText().toString().equals("") || edtDescripcion1.getText().toString().equals("")
                                || edtNombreGral.getText().toString().equals("") || edtMarca.getText().toString().equals("")
                                || edtPrecio.getText().toString().equals("") || edtNumeroSerie.getText().toString().equals("")) {

                            Toast.makeText(ListaEquipoActivity.this,"No se pudo crear equipo",Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();
                        } else {
                            String usuario = edtUsuario.getText().toString();
                            if(Usuario.getUsuarioByEmail(ListaEquipoActivity.this, usuario) == null) {
                                Toast.makeText(ListaEquipoActivity.this,"El usuario no existe",Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                            String descripcion = edtDescripcion1.getText().toString();
                            if(!edtDescripcion2.getText().toString().equals("")) {
                                descripcion = descripcion + "\n" + edtDescripcion2.getText().toString();
                            }

                            if(!edtDescripcion3.getText().toString().equals("")) {
                                descripcion = descripcion + "\n" + edtDescripcion3.getText().toString();
                            }

                            if(!edtDescripcion4.getText().toString().equals("")) {
                                descripcion = descripcion + "\n" + edtDescripcion4.getText().toString();
                            }

                            Equipo.newEquipo(ListaEquipoActivity.this, edtNumeroSerie.getText().toString(), edtMarca.getText().toString(),descripcion,
                                    getFormatNumber(Integer.valueOf(edtPrecio.getText().toString())), usuario, edtNombreGral.getText().toString());
                            updateAdapter();
                        }
                    }
                });
        return builder.create();
    }

    public static String getFormatNumber(Integer number) {
        NumberFormat MxFormat = NumberFormat.getNumberInstance(Locale.US);
        String numberFormat = MxFormat.format(number);
        int size = numberFormat.length();
        Character c = numberFormat.charAt(0);
        if (c == '-')
            return "-" + "$" + numberFormat.substring(1, size);
        else
            return "$" + numberFormat;
    }

    private void updateAdapter(){
        equipos = Equipo.getAll(ListaEquipoActivity.this);
        adapter = new EquipoAdapter(ListaEquipoActivity.this, equipos, tecnico);
        recyclerView.setAdapter(adapter);
        adapter.EquipoInterface(ListaEquipoActivity.this);
    }
}
