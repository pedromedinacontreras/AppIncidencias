package com.example.usuario.incidenciasapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.incidenciasapp.models.Usuario;
import com.example.usuario.incidenciasapp.R;

import java.util.ArrayList;

/**
 * Created by usuario on 8/12/16.
 */

public class UsuarioDialogAdapter  extends RecyclerView.Adapter<UsuarioDialogAdapter.UsuarioViewHolder> {

    private ArrayList<Usuario> items;
    private Context context;

    public UsuarioDialogAdapter(Context context) {
        this.context = context;
        this.items = Usuario.getTecnicos(context);
    }

    @Override
    public UsuarioDialogAdapter.UsuarioViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_usuario_dialog,viewGroup,false);
        return new UsuarioDialogAdapter.UsuarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UsuarioDialogAdapter.UsuarioViewHolder holder, int i) {
        final Usuario usuario = items.get(i);

        holder.tvNombre.setText(usuario.getNombre());
        holder.tvEspecialidad.setText(usuario.getEspecialidad());
        //TODO: Sumar la calificacion de todos los pendientes de cierto usuario
        holder.tvCalificacionPendientes.setText(usuario.getEsfuerzo() + "");
    }

    @Override
    public int getItemCount(){
        return items.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombre;
        TextView tvEspecialidad;
        TextView tvCalificacionPendientes;


        public UsuarioViewHolder(View view){
            super(view);
            tvNombre = (TextView) view.findViewById(R.id.tv_tecnico_dialog);
            tvEspecialidad = (TextView) view.findViewById(R.id.tv_especialidad_tecnico_dialog);
            tvCalificacionPendientes = (TextView) view.findViewById(R.id.tv_calificacion_pendientes);
        }
    }
}
