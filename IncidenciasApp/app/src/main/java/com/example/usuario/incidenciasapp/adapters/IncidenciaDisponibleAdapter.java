package com.example.usuario.incidenciasapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.incidenciasapp.models.Incidencia;
import com.example.usuario.incidenciasapp.R;

import java.util.ArrayList;

/**
 * Created by usuario on 8/12/16.
 */

public class IncidenciaDisponibleAdapter  extends RecyclerView.Adapter<IncidenciaDisponibleAdapter.IncidenciaViewHolder> {

    private ArrayList<Incidencia> items;
    private Context context;
    private int status;

    public IncidenciaDisponibleAdapter(Context context, ArrayList<Incidencia> items, int status) {
        this.items = items;
        this.context = context;
//        switch (status) {
//            case Incidencia.ESTATUS_DISPONIBLE:
//                this.items = Incidencia.getIncidenciasDisponibles(context);
//                break;
//            case Incidencia.ESTATUS_EN_PROCESO:
//                this.items = Incidencia.getIncidenciasEnProceso(context);
//                break;
//            case Incidencia.ESTATUS_TERMINADA:
//                this.items = Incidencia.getIncidenciasTerminadas(context);
//                break;
//        }
    }

    @Override
    public IncidenciaDisponibleAdapter.IncidenciaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_incidencia,viewGroup,false);
        return new IncidenciaDisponibleAdapter.IncidenciaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(IncidenciaDisponibleAdapter.IncidenciaViewHolder holder, int i) {
        final Incidencia incidencia = items.get(i);

        holder.tvTituloIncidencia.setText(incidencia.getTitulo());
        holder.tvFecha.setText(incidencia.getFechaCreacion());
        holder.tvUsuarioLevanta.setText(incidencia.getUsuarioLevanta().getCorreo());
        holder.tvTecnicoAsignado.setText("Técnico por asignar");
        holder.tvEquipoAfectado.setText(incidencia.getEquipoAfectado());
        holder.tvEsfuerzo.setText(incidencia.getEsfuerzo()+"");
        holder.tvPrioridad.setText(incidencia.getPrioridad()+"");
    }

    @Override
    public int getItemCount(){
        return items.size();
    }

    public class IncidenciaViewHolder extends RecyclerView.ViewHolder{

        TextView tvTituloIncidencia;
        TextView tvEquipoAfectado;
        TextView tvUsuarioLevanta;
        TextView tvTecnicoAsignado;
        TextView tvFecha;
        TextView tvEsfuerzo;
        TextView tvPrioridad;

        public IncidenciaViewHolder(View view){
            super(view);
            tvTituloIncidencia = (TextView) view.findViewById(R.id.tv_titulo_incidencia);
            tvEquipoAfectado = (TextView) view.findViewById(R.id.tv_equipo_afectado);
            tvUsuarioLevanta = (TextView) view.findViewById(R.id.tv_usuario_levanta);
            tvTecnicoAsignado = (TextView) view.findViewById(R.id.tv_tecnico_asignado);
            tvFecha = (TextView) view.findViewById(R.id.tv_fecha);
            tvEsfuerzo = (TextView) view.findViewById(R.id.tv_esfuerzo);
            tvPrioridad = (TextView) view.findViewById(R.id.tv_prioridad);
        }
    }

    public void deleteAt(int position) {
        items.remove(position);
    }

//    public interface AccionRecyclerInterface {
//        public void onCalificar(int pkPrestamo);
//    }
//
//    public void CalificarCompraInterface(AccionRecyclerInterface calificarCompra){
//        this.calificarCompra = calificarCompra;
//    }

}
