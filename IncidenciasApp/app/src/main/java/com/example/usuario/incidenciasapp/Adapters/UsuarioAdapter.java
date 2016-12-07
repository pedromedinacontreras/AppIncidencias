package com.example.usuario.incidenciasapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.usuario.incidenciasapp.Models.Usuario;
import com.example.usuario.incidenciasapp.R;

import java.util.ArrayList;

/**
 * Created by usuario on 6/12/16.
 */

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>{

    private ArrayList<Usuario> items;
    private Context context;

    public UsuarioAdapter(Context context, ArrayList<Usuario> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public UsuarioAdapter.UsuarioViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_usuario,viewGroup,false);
        return new UsuarioAdapter.UsuarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UsuarioAdapter.UsuarioViewHolder holder, int i) {
        final Usuario usuario = items.get(i);

        holder.tvNombreUsuario.setText(usuario.getCorreo());
        holder.tvEquipo.setText(usuario.getPkUsuario());
        switch (usuario.getTipoUsuario()) {
            case Usuario.TIPO_ADMINISTRADOR:
                holder.tvPuesto.setText("Administrador");
                break;
            case Usuario.TIPO_EMPLEADO:
                holder.tvPuesto.setText("Usuario general");
                break;
            case Usuario.TIPO_TECNICO:
                holder.tvPuesto.setText("Técnico");
                break;
            default:
                holder.tvPuesto.setText("Char?");
                break;
        }

    }

    @Override
    public int getItemCount(){
        return items.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombreUsuario;
        TextView tvPuesto;
        TextView tvEquipo;


        public UsuarioViewHolder(View view){
            super(view);
            tvNombreUsuario = (TextView) view.findViewById(R.id.tv_nombre_usuario);
            tvPuesto = (TextView) view.findViewById(R.id.tv_puesto);
            tvEquipo = (TextView) view.findViewById(R.id.tv_equipo);
        }
    }
}
