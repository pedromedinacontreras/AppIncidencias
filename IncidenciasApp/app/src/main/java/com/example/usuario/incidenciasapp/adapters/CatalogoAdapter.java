package com.example.usuario.incidenciasapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.incidenciasapp.R;
import com.example.usuario.incidenciasapp.models.CatalogoIncidencia;
import com.example.usuario.incidenciasapp.models.Incidencia;

import java.util.ArrayList;

/**
 * Created by usuario on 14/12/16.
 */

public class CatalogoAdapter extends RecyclerView.Adapter<CatalogoAdapter.CatalogoViewHolder> {

    private ArrayList<CatalogoIncidencia> items;
    private Context context;
    private int status;

    public CatalogoAdapter(Context context, ArrayList<CatalogoIncidencia> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public CatalogoAdapter.CatalogoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_catalogo,viewGroup,false);
        return new CatalogoAdapter.CatalogoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CatalogoAdapter.CatalogoViewHolder holder, int i) {
        final CatalogoIncidencia incidencia = items.get(i);

        holder.tvTitulo.setText(incidencia.getNombre());
        holder.tvDescripcion.setText(incidencia.getDescripcion());
        holder.tvSolucion.setText(incidencia.getPosibleSolucion());
        holder.tvEsfuerzo.setText(incidencia.getTiempoSolucion()+"");
        holder.tvPrioridad.setText(incidencia.getPrioridad()+"");
        holder.tvArea.setText(incidencia.getArea());
    }

    @Override
    public int getItemCount(){
        return items.size();
    }

    public class CatalogoViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitulo;
        TextView tvDescripcion;
        TextView tvSolucion;
        TextView tvEsfuerzo;
        TextView tvPrioridad;
        TextView tvArea;

        public CatalogoViewHolder(View view){
            super(view);
            tvTitulo = (TextView) view.findViewById(R.id.tv_titulo_incidencia);
            tvDescripcion = (TextView) view.findViewById(R.id.tv_descripcion);
            tvSolucion = (TextView) view.findViewById(R.id.tv_solucion);
            tvEsfuerzo = (TextView) view.findViewById(R.id.tv_esfuerzo);
            tvPrioridad = (TextView) view.findViewById(R.id.tv_prioridad);
            tvArea = (TextView) view.findViewById(R.id.tv_area);
        }
    }
}
