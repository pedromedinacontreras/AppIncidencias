package com.example.usuario.incidenciasapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.incidenciasapp.models.Equipo;
import com.example.usuario.incidenciasapp.models.Usuario;
import com.example.usuario.incidenciasapp.R;

import java.util.ArrayList;

/**
 * Created by usuario on 6/12/16.
 */

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>{

    private ArrayList<Usuario> items;
    private Context context;
    private boolean isUsuario;

    public UsuarioAdapter(Context context, ArrayList<Usuario> items, boolean isUsuario) {
        this.items = items;
        this.context = context;
        this.isUsuario = isUsuario;
    }

    @Override
    public UsuarioAdapter.UsuarioViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_usuario,viewGroup,false);
        return new UsuarioAdapter.UsuarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UsuarioAdapter.UsuarioViewHolder holder, int i) {
        final Usuario usuario = items.get(i);
        holder.tvCorreo.setText(usuario.getCorreo());
        if(isUsuario) {
            holder.tvEspecialidad.setVisibility(View.GONE);
            holder.tvEsfuerzo.setVisibility(View.GONE);
            holder.imageView3.setVisibility(View.GONE);
        }

        float calificacion = Usuario.promedioTecnico(context, usuario);
        if(!isUsuario) {
            holder.tvCorreo.setText(usuario.getCorreo() + "         Calificación: " + calificacion);
        }
        holder.tvEspecialidad.setText(usuario.getEspecialidad());
        holder.tvNombreUsuario.setText(usuario.getNombre());
        try {
            Equipo equipoUsuario = Equipo.getEquiposByUser(context,usuario.getCorreo()).get(0);
            holder.tvEquipo.setText(equipoUsuario.getNombreGral() + " " + equipoUsuario.getMarca() + " " + equipoUsuario.getNumeroSerie());
        } catch (Exception e) {
            holder.tvEquipo.setText("Usuario sin equipo asignado");

        }

        holder.tvEsfuerzo.setText(String.valueOf(usuario.getEsfuerzo()));
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
        TextView tvCorreo;
        TextView tvEspecialidad;
        TextView tvEsfuerzo;
        ImageView imageView3;


        public UsuarioViewHolder(View view){
            super(view);
            tvNombreUsuario = (TextView) view.findViewById(R.id.tv_nombre_usuario);
            tvPuesto = (TextView) view.findViewById(R.id.tv_puesto);
            tvEquipo = (TextView) view.findViewById(R.id.tv_equipo);
            tvCorreo = (TextView) view.findViewById(R.id.tv_email_usuario);
            tvEspecialidad = (TextView) view.findViewById((R.id.tv_especialidad));
            tvEsfuerzo = (TextView) view.findViewById(R.id.tv_esfuerzo);
            imageView3 = (ImageView) view.findViewById(R.id.imageView3);
        }
    }
}
