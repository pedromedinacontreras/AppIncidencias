package com.example.usuario.incidenciasapp.Models;

import android.content.Context;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by usuario on 3/12/16.
 */

public class Incidencia extends RealmObject {

    public static final int ESTATUS_DISPONIBLE = 0;
    public static final int ESTATUS_EN_PROCESO = 1;
    public static final int ESTATUS_TERMINADA = 2;

    @PrimaryKey
    private Integer pkIncidencia;
    private String descripcion;
    private Integer prioridad;
    private String ubicacion;
    private Integer status;
    private String equipoAfectado;
    private String fechaCreacion;
    private Usuario usuarioLevanta;
    private Usuario usuarioTecnico;

    public Integer getPkIncidencia() {
        return pkIncidencia;
    }

    public void setPkIncidencia(Integer pkIncidencia) {
        this.pkIncidencia = pkIncidencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEquipoAfectado() {
        return equipoAfectado;
    }

    public void setEquipoAfectado(String equipoAfectado) {
        this.equipoAfectado = equipoAfectado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getUsuarioLevanta() {
        return usuarioLevanta;
    }

    public void setUsuarioLevanta(Usuario usuarioLevanta) {
        this.usuarioLevanta = usuarioLevanta;
    }

    public Usuario getUsuarioTecnico() {
        return usuarioTecnico;
    }

    public void setUsuarioTecnico(Usuario usuarioTecnico) {
        this.usuarioTecnico = usuarioTecnico;
    }

    public static void newIncidencia(Context context, String descripcion, int prioridad, int status,
                                     String ubicacion, String equipoAfectado, String fechaCreacion,
                                     Usuario usuarioLevanta) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Incidencia incidencia = realm.createObject(Incidencia.class, nextId(context));
        incidencia.setDescripcion(descripcion);
        incidencia.setPrioridad(prioridad);
        incidencia.setStatus(status);
        incidencia.setUbicacion(ubicacion);
        incidencia.setEquipoAfectado(equipoAfectado);
        incidencia.setFechaCreacion(fechaCreacion);
        incidencia.setUsuarioLevanta(usuarioLevanta);
        realm.commitTransaction();
    }

    public static int nextId(Context context){
        int nextId;
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Incidencia> incidenciaRealmResults = realm.where(Incidencia.class).findAll();
        nextId = incidenciaRealmResults.size();
        return nextId + 1001;
    }

    public static ArrayList<Incidencia> getAll(Context context) {
        ArrayList<Incidencia> incidencias= new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Incidencia> incidenciaRealmResults = realm.where(Incidencia.class).findAll();
        incidencias.addAll(incidenciaRealmResults);
        return incidencias;
    }

    public static ArrayList<Incidencia> getIncidenciasDisponibles(Context context) {
        ArrayList<Incidencia> incidenciasDisponibles= new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Incidencia> incidenciaRealmResults = realm.where(Incidencia.class).equalTo("status", ESTATUS_DISPONIBLE).findAll();
        incidenciasDisponibles.addAll(incidenciaRealmResults);
        return incidenciasDisponibles;
    }

    public static ArrayList<Incidencia> getIncidenciasEnProceso(Context context) {
        ArrayList<Incidencia> incidenciasDisponibles= new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Incidencia> incidenciaRealmResults = realm.where(Incidencia.class).equalTo("status", ESTATUS_EN_PROCESO).findAll();
        incidenciasDisponibles.addAll(incidenciaRealmResults);
        return incidenciasDisponibles;
    }

    public static ArrayList<Incidencia> getIncidenciasTerminadas(Context context) {
        ArrayList<Incidencia> incidenciasDisponibles= new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Incidencia> incidenciaRealmResults = realm.where(Incidencia.class).equalTo("status", ESTATUS_TERMINADA).findAll();
        incidenciasDisponibles.addAll(incidenciaRealmResults);
        return incidenciasDisponibles;
    }
}
