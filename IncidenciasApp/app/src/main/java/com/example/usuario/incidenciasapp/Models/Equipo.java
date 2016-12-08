package com.example.usuario.incidenciasapp.Models;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class Equipo extends RealmObject{



    private String serie;
    private String marca;
    private String Descripcion;
    private int Precio;
    private Usuario usuario;

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Equipo() {}

    public static void  newEquipo( android.content.Context context ,String serie, String marca, String descripcion, int precio, Usuario usuario) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Equipo equipo = realm.createObject(Equipo.class, serie);
        equipo.setMarca(marca);
        equipo.setPrecio(precio);
        equipo.setDescripcion(descripcion);
        equipo.setUsuario(usuario);
        realm.commitTransaction();
    }

    public static ArrayList<Equipo> getAll(Context context) {
        Log.e("llave","getALL EQUIPOS");
        ArrayList<Equipo> arrayEquipos = new ArrayList();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Equipo> equiposRealmResults = realm.where(Equipo.class).findAll();
        arrayEquipos.addAll(equiposRealmResults);
        return arrayEquipos;
    }

    public static Equipo getOneEquipo(Context context, String serie){
        Equipo equipo;
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        equipo = realm.where(Equipo.class).equalTo("serie", serie).findFirst();
        return equipo;
    }
}
