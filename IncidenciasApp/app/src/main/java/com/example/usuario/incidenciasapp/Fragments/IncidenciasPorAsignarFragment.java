package com.example.usuario.incidenciasapp.Fragments;


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
import android.widget.TextView;

import com.example.usuario.incidenciasapp.Adapters.IncidenciaAdapter;
import com.example.usuario.incidenciasapp.Adapters.IncidenciaDisponibleAdapter;
import com.example.usuario.incidenciasapp.Adapters.UsuarioDialogAdapter;
import com.example.usuario.incidenciasapp.Administrador.ListaUsuariosActivity;
import com.example.usuario.incidenciasapp.Models.Incidencia;
import com.example.usuario.incidenciasapp.Models.Usuario;
import com.example.usuario.incidenciasapp.R;
import com.example.usuario.incidenciasapp.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncidenciasPorAsignarFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView recyclerViewTecnicos;
    private RecyclerView.LayoutManager lmanager;
    private RecyclerView.LayoutManager lmanagerTecnicos;
    private IncidenciaDisponibleAdapter adapter;
    private ArrayList<Incidencia> incidencias = new ArrayList<>();
    private Dialog dialogTecnicos;
    private Dialog dialog;
    private Incidencia incidencia;
    private int posicionRecycler;

    public IncidenciasPorAsignarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        asignaValores();
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        posicionRecycler = position;
                        dialog = createDetalleIncidenciaDialog(position);
                        dialog.show();
                        Button btnAsignar = (Button) dialog.findViewById(R.id.btn_asignar_tecnico);
                        btnAsignar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                dialogTecnicos = createListaTecnicosDialog();
                                dialogTecnicos.show();
                                recyclerViewTecnicos = (RecyclerView) dialogTecnicos.findViewById(R.id.recycler_tecnicos_dialog);
                                final UsuarioDialogAdapter adapterTecnios = new UsuarioDialogAdapter(getContext());
                                lmanagerTecnicos = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                                recyclerViewTecnicos.setLayoutManager(lmanagerTecnicos);
                                recyclerViewTecnicos.setAdapter(adapterTecnios);
                                recyclerViewTecnicos.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                       Usuario user = Usuario.getTecnicos(getContext()).get(position);
                                        Realm.init(getContext());
                                        Realm realm = Realm.getDefaultInstance();
                                        realm.beginTransaction();
                                        incidencia.setUsuarioTecnico(user);
                                        incidencia.setStatus(Incidencia.ESTATUS_EN_PROCESO);
                                        realm.commitTransaction();
                                        Usuario.addEsfuerzo(getContext(), user.getCorreo(), incidencia.getEsfuerzo());
                                        dialogTecnicos.dismiss();
                                        adapter.notifyDataSetChanged();
                                        adapterTecnios.notifyDataSetChanged();
                                    }
                                }));
                            }
                        });
                    }
                })
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_incidencias_por_asignar, container, false);
    }

    private void asignaValores(){
        incidencias = Incidencia.getIncidenciasDisponibles(getContext());
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_incidencias_por_asignar);
        lmanager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lmanager);
        adapter = new IncidenciaDisponibleAdapter(getContext(),incidencias, Incidencia.ESTATUS_DISPONIBLE);
        recyclerView.setAdapter(adapter);
    }

    public Dialog createDetalleIncidenciaDialog(int position){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        incidencia = incidencias.get(position);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_detalle_incidencia_por_asignar,null);
        view.findViewById(R.id.btn_asignar_tecnico).setVisibility(View.VISIBLE);
        view.findViewById(R.id.tv_tecnico_incidencia).setVisibility(View.GONE);
        TextView tvCategoria = (TextView) view.findViewById(R.id.tv_categoria_incidencia);
        TextView tvTitulo = (TextView) view.findViewById(R.id.tv_titulo_incidencia);
        TextView tvFecha = (TextView) view.findViewById(R.id.tv_fecha_incidencia);
        TextView tvUsuarioLevanta = (TextView) view.findViewById(R.id.tv_usuario_levanta_incidencia);
        TextView tvEquipo = (TextView) view.findViewById(R.id.tv_equipo_incidencia);
        tvCategoria.setText(incidencia.getCategoria());
        tvTitulo.setText(incidencia.getTitulo());
        tvFecha.setText(incidencia.getFechaCreacion());
        tvUsuarioLevanta.setText(incidencia.getUsuarioLevanta().getCorreo());
        tvEquipo.setText(incidencia.getEquipoAfectado());
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

    public Dialog createListaTecnicosDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_lista_tecnicos,null);
        builder.setView(view)
                .setTitle("Técnicos")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogTecnicos.dismiss();
                    }
                });
        return builder.create();
    }
}