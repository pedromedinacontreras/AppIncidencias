package com.example.usuario.incidenciasapp.Models;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.Context;

/**
 * Created by usuario on 30/11/16.
 */

public class Usuario extends RealmObject {

    public static final int TIPO_ADMINISTRADOR = 1;
    public static final int TIPO_TECNICO = 2;
    public static final int TIPO_EMPLEADO = 3;

    @PrimaryKey
    private String pkUsuario; //numero de celular
    private String correo;
    private String contraseña;
    private int tipoUsuario;

    public Usuario() {
    }

    public String getPkUsuario() {
        return pkUsuario;
    }

    public void setPkUsuario(String pkUsuario) {
        this.pkUsuario = pkUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public static ArrayList<Usuario> getAll(android.content.Context context){
        ArrayList<Usuario> usuarioArrayList= new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Usuario> usuarioRealmResults = realm.where(Usuario.class).findAll();
        usuarioArrayList.addAll(usuarioRealmResults);
    //    for(Usuario user : usuarioRealmResults){
  //          usuarioArrayList.add(user);
//        }
        return usuarioArrayList;
    }

    public static void newUsuario(android.content.Context context, String pkUsuario, String correo, String contraseña, int tipoUsuario){
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Usuario user = realm.createObject(Usuario.class, pkUsuario);
        user.setCorreo(correo);
        user.setContraseña(contraseña);
        user.setTipoUsuario(tipoUsuario);
        realm.commitTransaction();
    }

    public static boolean inicioSesion(android.content.Context context, String correo, String contraseña) {
        boolean inicioCorrecto = false;
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        Usuario user = realm.where(Usuario.class).equalTo("correo", correo).equalTo("contraseña", contraseña).findFirst();
        if(user != null) {
            UsuarioLogeado.setUsuarioLogeado(context, user);
            inicioCorrecto = true;
        }
        return inicioCorrecto;
    }
}
