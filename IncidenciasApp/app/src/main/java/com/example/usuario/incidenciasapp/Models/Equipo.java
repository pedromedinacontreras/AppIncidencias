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
//    private RealmList<Descripcion> descripciones;
    private String descripciones;
    private String precio;
    private String correoUsuario;
    private String nombreGral;

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

    public String getDescripcion() {
        return descripciones;
    }

    public void setDescripcion(String descripciones) {
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

    public String getNombreGral() {
        return nombreGral;
    }

    public void setNombreGral(String nombreGral) {
        this.nombreGral = nombreGral;
    }

    public static ArrayList<Equipo> getAll(android.content.Context context){
        ArrayList<Equipo> equipoArrayList= new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Equipo> equiposRealmResults = realm.where(Equipo.class).findAll();
        equipoArrayList.addAll(equiposRealmResults);
        return equipoArrayList;
    }

    public static void newEquipo(android.content.Context context, String numeroSerie, String marca, String descripcionues, String precio, String usuario, String nombreGral){
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Equipo equipo = realm.createObject(Equipo.class, numeroSerie);
        equipo.setDescripcion(descripcionues);
        equipo.setMarca(marca);
        equipo.setPrecio(precio);
        equipo.setUsuario(usuario);
        equipo.setNombreGral(nombreGral);
        realm.commitTransaction();
    }

    public static Equipo getEquipoByNumeroSerie(Context context, String numeroSerie) {
        Equipo equipo = null;
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        equipo  = realm.where(Equipo.class).equalTo("numeroSerie", numeroSerie).findFirst();
        return equipo;
    }

    public static ArrayList<Equipo> getEquiposByUser(Context context, String correoUsuario){
        ArrayList<Equipo> arrayEquipo = new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Equipo> results = realm.where(Equipo.class).equalTo("correoUsuario",correoUsuario).findAll();
        arrayEquipo.addAll(results);
        return arrayEquipo;
    }

    public static void updateEquipo(Context context, String numeroSerie, String descripciones, String usuario) {
        Equipo equipo = getEquipoByNumeroSerie(context, numeroSerie);
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        equipo.setDescripcion(descripciones);
        equipo.setUsuario(usuario);
        realm.commitTransaction();
    }

    public static void deleteEquipo(Context context, String numeroSerie) {
        Equipo equipo = getEquipoByNumeroSerie(context, numeroSerie);
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        equipo.deleteFromRealm();
        realm.commitTransaction();
    }
}
