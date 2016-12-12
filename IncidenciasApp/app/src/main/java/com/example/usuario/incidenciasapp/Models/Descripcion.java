package com.example.usuario.incidenciasapp.Models;

import io.realm.RealmObject;

/**
 * Created by usuario on 11/12/16.
 */

public class Descripcion extends RealmObject {
    private String valor;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
