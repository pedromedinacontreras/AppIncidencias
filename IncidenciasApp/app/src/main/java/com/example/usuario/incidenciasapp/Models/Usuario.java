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
    public static final String HARDWARE = "HARDWARE";
    public static final String SOFTWARE = "SOFTWARE";
    public static final String REDES = "REDES";
    public static final String SIN_ESPECIALIDAD = "SIN_ESPECIALIDAD";

    @PrimaryKey
    private String pkUsuario;
    private String correo;
    private String contraseña;
    private int tipoUsuario;
    private String especialidad;
    private int esfuerzo;

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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getEsfuerzo() {
        return esfuerzo;
    }

    public void setEsfuerzo(int esfuerzo) {
        this.esfuerzo = esfuerzo;
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

    public static void newUsuario(android.content.Context context, String pkUsuario, String correo, String contraseña, int tipoUsuario, String especialidad){
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Usuario user = realm.createObject(Usuario.class, pkUsuario);
        user.setCorreo(correo);
        user.setContraseña(contraseña);
        user.setTipoUsuario(tipoUsuario);
        user.setEspecialidad(especialidad);
        user.setEsfuerzo(0);
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

    public static ArrayList<Usuario> getTecnicos (android.content.Context context){
        ArrayList<Usuario> usuarioArrayList= new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Usuario> usuarioRealmResults = realm.where(Usuario.class).equalTo("tipoUsuario",Usuario.TIPO_TECNICO).findAll();
        usuarioArrayList.addAll(usuarioRealmResults);
        return usuarioArrayList;
    }

    public static ArrayList<Usuario> getEmpleados (android.content.Context context){
        ArrayList<Usuario> usuarioArrayList= new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Usuario> usuarioRealmResults = realm.where(Usuario.class).equalTo("tipoUsuario",Usuario.TIPO_EMPLEADO).findAll();
        usuarioArrayList.addAll(usuarioRealmResults);
        return usuarioArrayList;
    }

    public static Usuario getUsuarioByEmail(android.content.Context context, String correo) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        Usuario usuario = realm.where(Usuario.class).equalTo("correo",correo).findFirst();
        return usuario;
    }

    public static Usuario getUsuarioByPk(android.content.Context context, String pk) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        Usuario usuario = realm.where(Usuario.class).equalTo("pkUsuario", pk).findFirst();
        return usuario;
    }

    public static void addEsfuerzo(android.content.Context context, String correo, int esfuerzo) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Usuario user = getUsuarioByEmail(context , correo);
        int suma = user.getEsfuerzo();
        suma += esfuerzo;
        user.setEsfuerzo(suma);
        realm.commitTransaction();
    }

    public static void restarEsfuerzo(android.content.Context context, String correo, int esfuerzo) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Usuario user = getUsuarioByEmail(context , correo);
        int nuevoEsfuerzo = user.getEsfuerzo();
        nuevoEsfuerzo -= esfuerzo;
        user.setEsfuerzo(nuevoEsfuerzo);
        realm.commitTransaction();
    }
}
