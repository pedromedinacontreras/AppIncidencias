package com.example.usuario.incidenciasapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.usuario.incidenciasapp.models.Equipo;
import com.example.usuario.incidenciasapp.R;

import java.util.ArrayList;

/**
 * Created by usuario on 11/12/16.
 */

public class EquipoAdapter extends RecyclerView.Adapter<EquipoAdapter.EquipoViewHold>{

    private ArrayList<Equipo> items;
    private Context context;
    private EquipoInterface equipoInterface;

    public EquipoAdapter(Context context, ArrayList<Equipo> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public EquipoAdapter.EquipoViewHold onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_equipo,viewGroup,false);
        return new EquipoAdapter.EquipoViewHold(v);
    }

    @Override
    public void onBindViewHolder(EquipoAdapter.EquipoViewHold holder, final int i) {
        final Equipo equipo = items.get(i);
        holder.tvNombreEquipo.setText(equipo.getNombreGral() + " " + equipo.getNumeroSerie());
        holder.tvMarca.setText(equipo.getMarca());
        holder.tvUsuario.setText(equipo.getUsuario());
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equipoInterface.onEliminar(i, equipo.getNumeroSerie());
            }
        });
        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equipoInterface.onEditar(i, equipo.getNumeroSerie());
            }
        });
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equipoInterface.onDetalle(i);
            }
        });
    }

    @Override
    public int getItemCount(){
        return items.size();
    }

    public class EquipoViewHold extends RecyclerView.ViewHolder{

        TextView tvNombreEquipo;
        TextView tvMarca;
        TextView tvUsuario;
        Button btnEditar;
        Button btnEliminar;
        FrameLayout frameLayout;

        public EquipoViewHold(View view){
            super(view);
            tvNombreEquipo = (TextView) view.findViewById(R.id.tv_nombre_equipo);
            tvMarca = (TextView) view.findViewById(R.id.tv_marca_equipo);
            tvUsuario = (TextView) view.findViewById(R.id.tv_email_equipo);
            btnEditar = (Button) view.findViewById(R.id.btn_editar_equipo);
            btnEliminar = (Button) view.findViewById(R.id.btn_eliminar_equipo);
            frameLayout = (FrameLayout) view.findViewById(R.id.item_click);
        }
    }

    public interface EquipoInterface {
        public void onEliminar(int position, String numeroSerie);
        public void onEditar(int position, String numeroSerie);
        public void onDetalle(int position);
    }

    public void EquipoInterface(EquipoInterface equipoInterface) {
        this.equipoInterface = equipoInterface;
    }
}
