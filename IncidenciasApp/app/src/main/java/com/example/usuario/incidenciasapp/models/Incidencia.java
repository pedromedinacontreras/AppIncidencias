package com.example.usuario.incidenciasapp.models;

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
    public static final int ESTATUS_LIBERADA = 3;
    public static final int ESFUERZO_BAJO = 1;
    public static final int ESFUERZO_MEDIO = 2;
    public static final int ESFUERZO_ALTO = 3;

    @PrimaryKey
    private Integer pkIncidencia;
    private String titulo;
    private String Categoria;
    private String descripcion;
    private Integer prioridad;
    private Integer status;
    private String equipoAfectado;
    private String fechaCreacion;
    private Usuario usuarioLevanta;
    private Usuario usuarioTecnico;
    private int esfuerzo;
    private String correoLevanta;
    private String correoTecnico;
    private int calificacion;

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
        //Aquí puede tronar
        this.usuarioTecnico = usuarioTecnico;
        setCorreoTecnico(usuarioTecnico.getCorreo());
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getEsfuerzo() {
        return esfuerzo;
    }

    public void setEsfuerzo(int esfuerzo) {
        this.esfuerzo = esfuerzo;
    }

    public String getCorreoLevanta() {
        return correoLevanta;
    }

    public void setCorreoLevanta(String correoLevanta) {
        this.correoLevanta = correoLevanta;
    }

    public String getCorreoTecnico() {
        return correoTecnico;
    }

    public void setCorreoTecnico(String correoTecnico) {
        this.correoTecnico = correoTecnico;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public static void newIncidencia(Context context, String descripcion, int status, String equipoAfectado, String fechaCreacion,
                                     Usuario usuarioLevanta, String categoria, String titulo) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Incidencia incidencia = realm.createObject(Incidencia.class, nextId(context));
        incidencia.setDescripcion(descripcion);
        incidencia.setStatus(status);
        incidencia.setEquipoAfectado(equipoAfectado);
        incidencia.setFechaCreacion(fechaCreacion);
        incidencia.setUsuarioLevanta(usuarioLevanta);
        incidencia.setCategoria(categoria);
        incidencia.setTitulo(titulo);
        incidencia.setCorreoLevanta(usuarioLevanta.getCorreo());
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

    public static ArrayList<Incidencia> getIncidenciasEnProcesoByUser (Context context) {
        ArrayList<Incidencia> incidenciasDisponibles= new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Incidencia> incidenciaRealmResults = realm.where(Incidencia.class).equalTo("status", ESTATUS_EN_PROCESO).equalTo("correoLevanta",UsuarioLogeado.getUsuarioLogeado(context).getUsuario().getCorreo()).findAll();
        incidenciasDisponibles.addAll(incidenciaRealmResults);
        return incidenciasDisponibles;
    }

    public static ArrayList<Incidencia> getIncidenciasEnProcesoByTecnico (Context context) {
        ArrayList<Incidencia> incidenciasDisponibles= new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Incidencia> incidenciaRealmResults = realm.where(Incidencia.class).equalTo("status", ESTATUS_EN_PROCESO).equalTo("correoTecnico",UsuarioLogeado.getUsuarioLogeado(context).getUsuario().getCorreo()).findAll();
        incidenciasDisponibles.addAll(incidenciaRealmResults);
        return incidenciasDisponibles;
    }

    public static ArrayList<Incidencia> getIncidenciasLiberadasByEmailTecnico (Context context, String correo) {
        ArrayList<Incidencia> incidenciasDisponibles= new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Incidencia> incidenciaRealmResults = realm.where(Incidencia.class).equalTo("status", ESTATUS_LIBERADA).equalTo("correoTecnico",correo).findAll();
        incidenciasDisponibles.addAll(incidenciaRealmResults);
        return incidenciasDisponibles;
    }

    public static ArrayList<Incidencia> getIncidenciasTerminadas(Context context) {
        ArrayList<Incidencia> incidenciasDisponibles= new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Incidencia> incidenciaRealmResults = realm.where(Incidencia.class).equalTo("status", ESTATUS_TERMINADA).findAll();
        incidenciasDisponibles.addAll(incidenciaRealmResults);
        incidenciaRealmResults = realm.where(Incidencia.class).equalTo("status", ESTATUS_LIBERADA).findAll();
        incidenciasDisponibles.addAll(incidenciaRealmResults);
        return incidenciasDisponibles;
    }

    public static ArrayList<Incidencia> getIncidenciasTerminadasByUser(Context context) {
        ArrayList<Incidencia> incidenciasDisponibles= new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Incidencia> incidenciaRealmResults = realm.where(Incidencia.class).equalTo("status", ESTATUS_TERMINADA).equalTo("correoLevanta",UsuarioLogeado.getUsuarioLogeado(context).getUsuario().getCorreo()).findAll();
        incidenciasDisponibles.addAll(incidenciaRealmResults);
        incidenciaRealmResults = realm.where(Incidencia.class).equalTo("status", ESTATUS_LIBERADA).equalTo("correoLevanta",UsuarioLogeado.getUsuarioLogeado(context).getUsuario().getCorreo()).findAll();
        incidenciasDisponibles.addAll(incidenciaRealmResults);
        return incidenciasDisponibles;
    }

    public static ArrayList<Incidencia> getIncidenciasTerminadasByTecnico(Context context) {
        ArrayList<Incidencia> incidenciasDisponibles= new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Incidencia> incidenciaRealmResults = realm.where(Incidencia.class).equalTo("status", ESTATUS_TERMINADA).equalTo("correoTecnico",UsuarioLogeado.getUsuarioLogeado(context).getUsuario().getCorreo()).findAll();
        incidenciasDisponibles.addAll(incidenciaRealmResults);
        incidenciaRealmResults = realm.where(Incidencia.class).equalTo("status", ESTATUS_LIBERADA).equalTo("correoTecnico",UsuarioLogeado.getUsuarioLogeado(context).getUsuario().getCorreo()).findAll();
        incidenciasDisponibles.addAll(incidenciaRealmResults);
        return incidenciasDisponibles;
    }
}
