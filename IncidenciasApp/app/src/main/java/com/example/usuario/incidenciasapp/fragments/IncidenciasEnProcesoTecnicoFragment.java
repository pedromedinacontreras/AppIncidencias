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
import android.widget.TextView;

import com.example.usuario.incidenciasapp.R;
import com.example.usuario.incidenciasapp.RecyclerItemClickListener;
import com.example.usuario.incidenciasapp.adapters.IncidenciaEnProcesoAdapter;
import com.example.usuario.incidenciasapp.administrador.IncidenciasAdministradorActivity;
import com.example.usuario.incidenciasapp.models.Incidencia;
import com.example.usuario.incidenciasapp.models.Usuario;
import com.example.usuario.incidenciasapp.tecnico.IncidenciasTecnicoActivity;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncidenciasEnProcesoTecnicoFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lmanager;
    private IncidenciaEnProcesoAdapter adapter;
    ArrayList<Incidencia> incidencias = new ArrayList<>();
    Incidencia incidencia;
    Dialog dialog;
    private boolean isUsuario;

    public IncidenciasEnProcesoTecnicoFragment() {
        // Required empty public constructor
    }

    public static IncidenciasEnProcesoTecnicoFragment create(boolean isUsuario) {
        IncidenciasEnProcesoTecnicoFragment f = new IncidenciasEnProcesoTecnicoFragment();
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
        return inflater.inflate(R.layout.fragment_incidencias_en_proceso_tecnico, container, false);
    }

    private void asignaValores(){
        if (isUsuario) {
            incidencias = Incidencia.getIncidenciasEnProcesoByUser(getContext());
        } else {
            incidencias = Incidencia.getIncidenciasEnProcesoByTecnico(getContext());
        }
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_incidencias_en_proceso);
        lmanager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lmanager);
        adapter = new IncidenciaEnProcesoAdapter(getContext(),incidencias, Incidencia.ESTATUS_EN_PROCESO);
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
        tvCategoria.setText(incidencia.getCategoria());
        tvTitulo.setText(incidencia.getTitulo());
        tvFecha.setText(incidencia.getFechaCreacion());
        tvUsuarioLevanta.setText(incidencia.getUsuarioLevanta().getCorreo());
        tvEquipo.setText(incidencia.getEquipoAfectado());
        tvTecnico.setText(incidencia.getUsuarioTecnico().getCorreo());
        builder.setView(view)
                .setTitle("Incidencia")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Finalizar incidencia", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Realm.init(getContext());
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        incidencia.setStatus(Incidencia.ESTATUS_TERMINADA);
                        realm.commitTransaction();
                        Usuario user = incidencia.getUsuarioTecnico();
                        Usuario.restarEsfuerzo(getContext(), user.getCorreo(), incidencia.getEsfuerzo());
                        updateAdapter();
                        IncidenciasTecnicoActivity administradorActivity = (IncidenciasTecnicoActivity) getActivity();
                        administradorActivity.updateAdapter();
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
        if (isUsuario) {
            incidencias = Incidencia.getIncidenciasEnProcesoByUser(getContext());
        } else {
            incidencias = Incidencia.getIncidenciasEnProcesoByTecnico(getContext());
        }
        adapter = new IncidenciaEnProcesoAdapter(getContext(),incidencias, Incidencia.ESTATUS_EN_PROCESO);
        recyclerView.setAdapter(adapter);
    }

}
