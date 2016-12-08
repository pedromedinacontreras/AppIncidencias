package com.example.usuario.incidenciasapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.incidenciasapp.Adapters.IncidenciaAdapter;
import com.example.usuario.incidenciasapp.Models.Incidencia;
import com.example.usuario.incidenciasapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncidenciasTerminadasFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lmanager;
    private IncidenciaAdapter adapter;
    ArrayList<Incidencia> incidencias = new ArrayList<>();

    public IncidenciasTerminadasFragment() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_incidencias_terminadas, container, false);
    }

    private void asignaValores(){
        incidencias = Incidencia.getIncidenciasTerminadas(getContext());
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_incidencias_terminadas);
        lmanager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lmanager);
        adapter = new IncidenciaAdapter(getContext(),incidencias, Incidencia.ESTATUS_TERMINADA);
        recyclerView.setAdapter(adapter);
    }
}
