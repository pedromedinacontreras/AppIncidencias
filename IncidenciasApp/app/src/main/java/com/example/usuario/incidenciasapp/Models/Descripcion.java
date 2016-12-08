package com.example.usuario.incidenciasapp.Models;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.internal.Context;

/**
 * Created by Rafa on 12/7/2016.
 */

public class Descripcion extends RealmObject{
    private int claveDescripcion;
    private String descripcion;

    public Descripcion() {}

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static void newDescripcion(android.content.Context context,int clave, String descripcion){
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Descripcion desc = realm.createObject(Descripcion.class,clave);
        desc.setDescripcion(descripcion);
        realm.commitTransaction();
    }

    public static RealmResults<Descripcion> getAll(android.content.Context context) {
        RealmResults<Descripcion> arrayDescripcion = null;
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        arrayDescripcion = realm.where(Descripcion.class).findAll();
        return arrayDescripcion;
    }


}
