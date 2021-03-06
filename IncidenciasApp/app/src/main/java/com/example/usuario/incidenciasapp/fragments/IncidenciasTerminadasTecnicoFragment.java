package com.example.usuario.incidenciasapp.fragments;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.usuario.incidenciasapp.R;
import com.example.usuario.incidenciasapp.RecyclerItemClickListener;
import com.example.usuario.incidenciasapp.adapters.IncidenciaAdapter;
import com.example.usuario.incidenciasapp.adapters.IncidenciaDisponibleAdapter;
import com.example.usuario.incidenciasapp.administrador.IncidenciasAdministradorActivity;
import com.example.usuario.incidenciasapp.models.Incidencia;
import com.example.usuario.incidenciasapp.models.Usuario;
import com.example.usuario.incidenciasapp.models.UsuarioLogeado;
import com.example.usuario.incidenciasapp.tecnico.IncidenciasTecnicoActivity;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncidenciasTerminadasTecnicoFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lmanager;
    private IncidenciaAdapter adapter;
    ArrayList<Incidencia> incidencias = new ArrayList<>();
    Incidencia incidencia;
    Dialog dialog;
    private boolean isUsuario;

    public IncidenciasTerminadasTecnicoFragment() {
        // Required empty public constructor
    }
    public static IncidenciasTerminadasTecnicoFragment create(boolean isUsuario) {
        IncidenciasTerminadasTecnicoFragment f = new IncidenciasTerminadasTecnicoFragment();
        f.isUsuario = isUsuario;
        return f;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        asignaValores();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_incidencias_terminadas_tecnico, container, false);
    }

    private void asignaValores(){
        if(isUsuario) {
            incidencias = Incidencia.getIncidenciasTerminadasByUser(getContext());
        } else {
            incidencias = Incidencia.getIncidenciasTerminadasByTecnico(getContext());
        }
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_incidencias_terminadas);
        lmanager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lmanager);
        adapter = new IncidenciaAdapter(getContext(),incidencias, Incidencia.ESTATUS_TERMINADA);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                dialog = createDetalleIncidenciaDialog(position);
                dialog.show();
            }
        }));
    }

    public Dialog createDetalleIncidenciaDialog(final int position){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        incidencia = incidencias.get(position);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_detalle_incidencia_por_asignar,null);
        view.findViewById(R.id.btn_asignar_tecnico).setVisibility(View.GONE);
        view.findViewById(R.id.tv_tecnico_incidencia).setVisibility(View.VISIBLE);
        TextView tvCategoria = (TextView) view.findViewById(R.id.tv_categoria_incidencia);
        TextView tvTitulo = (TextView) view.findViewById(R.id.tv_titulo_incidencia);
        TextView tvFecha = (TextView) view.findViewById(R.id.tv_fecha_incidencia);
        TextView tvUsuarioLevanta = (TextView) view.findViewById(R.id.tv_usuario_levanta_incidencia);
        TextView tvEquipo = (TextView) view.findViewById(R.id.tv_equipo_incidencia);
        TextView tvTecnico = (TextView) view.findViewById(R.id.tv_tecnico_incidencia);
        if(isUsuario && incidencia.getStatus() == Incidencia.ESTATUS_TERMINADA){
            Button btnLiberar = (Button) view.findViewById(R.id.btn_liberar_incidencia);
            btnLiberar.setVisibility(View.VISIBLE);
            btnLiberar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createCalificarDialog(position).show();
                }
            });
        }

        if(incidencia.getStatus() == Incidencia.ESTATUS_LIBERADA) {
            LinearLayout llcalificacion = (LinearLayout) view.findViewById(R.id.llcalificacion);
            llcalificacion.setVisibility(View.VISIBLE);
            TextView tvCalificacion = (TextView) view.findViewById(R.id.tv_calificacion);
            tvCalificacion.setText(incidencia.getCalificacion()+"");
        }
        tvCategoria.setText(incidencia.getCategoria());
        tvTitulo.setText(incidencia.getTitulo());
        tvFecha.setText(incidencia.getFechaCreacion());
        tvUsuarioLevanta.setText(incidencia.getUsuarioLevanta().getCorreo());
        tvEquipo.setText(incidencia.getEquipoAfectado());
        tvTecnico.setText(incidencia.getUsuarioTecnico().getCorreo());
        builder.setView(view)
                .setTitle("Incidencia")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }

    public Dialog createCalificarDialog(int position){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        incidencia = incidencias.get(position);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_califica,null);
        final NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(1);
        builder.setView(view)
                .setTitle("Calificar")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Realm.init(getContext());
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        incidencia.setStatus(Incidencia.ESTATUS_LIBERADA);
                        incidencia.setCalificacion(numberPicker.getValue());
                        realm.commitTransaction();
                        updateAdapter();
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }

    public void notifyDataChanged(){
        adapter.notifyDataSetChanged();
    }

    private void updateAdapter(){
        incidencias = Incidencia.getIncidenciasTerminadasByUser(getContext());
        adapter = new IncidenciaAdapter(getContext(),incidencias, Incidencia.ESTATUS_TERMINADA);
        recyclerView.setAdapter(adapter);
    }
}