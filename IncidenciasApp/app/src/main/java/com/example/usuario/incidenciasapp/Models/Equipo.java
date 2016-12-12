package com.example.usuario.incidenciasapp.Models;

import android.content.Context;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by usuario on 11/12/16.
 */

public class Equipo extends RealmObject{
    @PrimaryKey
    private String numeroSerie;
    private String marca;
    private RealmList<Descripcion> descripciones;
    private String precio;
    private String correoUsuario;

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public RealmList<Descripcion> getDescripcion() {
        return descripciones;
    }

    public void setDescripcion(RealmList<Descripcion> descripciones) {
        this.descripciones = descripciones;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getUsuario() {
        return correoUsuario;
    }

    public void setUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public static ArrayList<Equipo> getAll(android.content.Context context){
        ArrayList<Equipo> equipoArrayList= new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Equipo> equiposRealmResults = realm.where(Equipo.class).findAll();
        equipoArrayList.addAll(equiposRealmResults);
        return equipoArrayList;
    }

    public static void newEquipo(android.content.Context context, String numeroSerie, String marca, RealmList<Descripcion> descripcionues, String precio, String usuario){
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Equipo equipo = realm.createObject(Equipo.class, numeroSerie);
        equipo.setDescripcion(descripcionues);
        equipo.setMarca(marca);
        equipo.setPrecio(precio);
        equipo.setUsuario(usuario);
        realm.commitTransaction();
    }

    public static ArrayList<Equipo> getEquiposByUser(Context context, String correoUsuario){
        ArrayList<Equipo> arrayEquipo = new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Equipo> results = realm.where(Equipo.class).equalTo("correoUsuario",correoUsuario).findAll();
        arrayEquipo.addAll(results);
        return arrayEquipo;
    }
}
