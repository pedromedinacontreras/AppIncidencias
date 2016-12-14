package com.example.usuario.incidenciasapp.models;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by usuario on 30/11/16.
 */

public class UsuarioLogeado extends RealmObject {
    public static final Integer PKUSUARIOLOGEADO = 0;
    @PrimaryKey
    private Integer pkUsuarioLogeado;
    private Usuario usuario;

    public Integer getPkUsuarioLogeado() {
        return pkUsuarioLogeado;
    }

    public void setPkUsuarioLogeado(Integer pkUsuarioLogeado) {
        this.pkUsuarioLogeado = pkUsuarioLogeado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public static UsuarioLogeado getUsuarioLogeado(android.content.Context context){
        UsuarioLogeado usuarioLogeado;
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        try {
            usuarioLogeado = realm.where(UsuarioLogeado.class).findFirst();
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            return null;
        }
        if(usuarioLogeado == null){
            return null;
        }
        return usuarioLogeado;
    }

    public static void setUsuarioLogeado(android.content.Context context, Usuario usuario) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        UsuarioLogeado usuarioLogeado = getUsuarioLogeado(context);
        if(usuarioLogeado != null) {
            realm.beginTransaction();
            usuarioLogeado.setUsuario(usuario);
            realm.commitTransaction();
        } else {
            realm.beginTransaction();
            UsuarioLogeado user = realm.createObject(UsuarioLogeado.class, PKUSUARIOLOGEADO);
            user.setUsuario(usuario);
            realm.commitTransaction();
        }
    }

    public static void setUsuarioLogeadoToNull(android.content.Context context){
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        UsuarioLogeado usuarioLogeado = getUsuarioLogeado(context);
        realm.beginTransaction();
        realm.delete(UsuarioLogeado.class);
        realm.commitTransaction();
    }
}
