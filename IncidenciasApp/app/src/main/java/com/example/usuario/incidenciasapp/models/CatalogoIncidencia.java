package com.example.usuario.incidenciasapp.models;

import android.content.Context;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by usuario on 14/12/16.
 */

public class CatalogoIncidencia extends RealmObject {

    @PrimaryKey
    private int pkCatalogoIncidencia;
    private String Nombre = "";
    private String posibleSolucion = "";
    private String descripcion = "";
    private Integer tiempoSolucion = 0;
    private Integer prioridad = 1;
    private String area = "";

    public static void initCatalogoIncidencia(Context context)
    {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        CatalogoIncidencia CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Instalación y desinstalación de software");
        CI.setPrioridad(3);
        CI.setTiempoSolucion(6);
        CI.setArea(Usuario.SOFTWARE);
        CI.setDescripcion("Se debe instalar y/o desinstalar el software necesario");
        CI.setPosibleSolucion("Se utilizará una memoria y drivers necesarios para la instalación");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Limpieza de PC");
        CI.setPrioridad(1);
        CI.setTiempoSolucion(4);
        CI.setArea(Usuario.HARDWARE);
        CI.setDescripcion("Se debe hacer limpieza a las PC's necesarias");
        CI.setPosibleSolucion("Se utilizará artículos de limpieza necesarios");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Limpieza de Impresoras");
        CI.setPrioridad(2);
        CI.setTiempoSolucion(5);
        CI.setArea(Usuario.HARDWARE);
        CI.setDescripcion("Se debe hacer limpieza a las Impresoras necesarias");
        CI.setPosibleSolucion("Se utilizará artículos de limpieza necesarios");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Limpieza de proyectores");
        CI.setPrioridad(1);
        CI.setTiempoSolucion(3);
        CI.setArea(Usuario.HARDWARE);
        CI.setDescripcion("Se debe hacer limpieza a las proyectores necesarias");
        CI.setPosibleSolucion("Se utilizará artículos de limpieza necesarios");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Mantenimiento de hardware");
        CI.setPrioridad(2);
        CI.setTiempoSolucion(5);
        CI.setArea(Usuario.HARDWARE);
        CI.setDescripcion("Se debe verificar que esta funcionando correctamente el hardware");
        CI.setPosibleSolucion("Se utilizarán los reportes de errores o fallos en cuanto al hardware se refiere");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Respaldo de Información");
        CI.setPrioridad(3);
        CI.setTiempoSolucion(5);
        CI.setArea(Usuario.HARDWARE);
        CI.setDescripcion("Se debe hacer un cambio en el almacenamiento de datos");
        CI.setPosibleSolucion("Se utilizará copian los datos al nuevo almacenamiento");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Reparación de fallas de equipo");
        CI.setPrioridad(3);
        CI.setTiempoSolucion(9);
        CI.setArea(Usuario.HARDWARE);
        CI.setDescripcion("Se identificaron fallos en los equipos utilizados");
        CI.setPosibleSolucion("Se utilizarán las herramientas necesarias para su reparación");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Reparación de fallas de monitor");
        CI.setPrioridad(2);
        CI.setTiempoSolucion(7);
        CI.setArea(Usuario.HARDWARE);
        CI.setDescripcion("Se identificaron fallos en los monitores utilizados");
        CI.setPosibleSolucion("Se utilizarán las herramientas necesarias para su reparación");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Reparación de fallas de teclado");
        CI.setPrioridad(1);
        CI.setTiempoSolucion(3);
        CI.setArea(Usuario.HARDWARE);
        CI.setDescripcion("Se identificaron fallos en los teclados utilizados");
        CI.setPosibleSolucion("Se utilizarán las herramientas necesarias para su reparación");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Reparación de fallas de proyector");
        CI.setPrioridad(1);
        CI.setTiempoSolucion(5);
        CI.setArea(Usuario.HARDWARE);
        CI.setDescripcion("Se identificaron fallos en los proyectores utilizados");
        CI.setPosibleSolucion("Se utilizarán las herramientas necesarias para su reparación");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Reparación de fallas de impresora");
        CI.setPrioridad(2);
        CI.setTiempoSolucion(7);
        CI.setArea(Usuario.HARDWARE);
        CI.setDescripcion("Se identificaron fallos en las impresoras utilizados");
        CI.setPosibleSolucion("Se utilizarán las herramientas necesarias para su reparación");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Tiempo determinado de configuración de Access Point");
        CI.setPrioridad(3);
        CI.setTiempoSolucion(120);
        CI.setArea(Usuario.SOFTWARE);
        CI.setDescripcion("Se debe evaluar el desempeño del Access Point");
        CI.setPosibleSolucion("Se utilizará herramienta necesaria para su revisión");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Resoluciones de problemas inalámbricos");
        CI.setPrioridad(3);
        CI.setTiempoSolucion(4);
        CI.setArea(Usuario.SOFTWARE);
        CI.setDescripcion("Se debe evaluar el desempeño de los dispositivos inalámbricos");
        CI.setPosibleSolucion("Se utilizará herramienta necesaria para la revisión inalámbrica");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Instalación y configuración de teléfono");
        CI.setPrioridad(3);
        CI.setTiempoSolucion(10);
        CI.setArea(Usuario.SOFTWARE);
        CI.setDescripcion("Se debe instalar y analizar el teléfono en cuestión");
        CI.setPosibleSolucion("Se utilizará un método para que no se repitan los usuarios por su número de teléfono");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Instalación y configuración de switch");
        CI.setPrioridad(3);
        CI.setTiempoSolucion(10);
        CI.setArea(Usuario.HARDWARE);
        CI.setDescripcion("Se debe instalar y analizar el switch en cuestión");
        CI.setPosibleSolucion("Se utilizarán las herramientas necesarias para la revisión del switch");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Resolución de problemas de cableado");
        CI.setPrioridad(2);
        CI.setTiempoSolucion(4);
        CI.setArea(Usuario.HARDWARE);
        CI.setDescripcion("Se debe instalar y analizar el cableado en cuestión");
        CI.setPosibleSolucion("Se utilizarán las herramientas necesarias para la revisión del cableado");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Instalación de red cableada");
        CI.setPrioridad(2);
        CI.setTiempoSolucion(5);
        CI.setArea(Usuario.HARDWARE);
        CI.setDescripcion("Se debe instalar el cableado en el lugar necesario");
        CI.setPosibleSolucion("Se utilizarán las herramientas necesarias para la instalación del cableado");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Configuración de FireWall");
        CI.setPrioridad(2);
        CI.setTiempoSolucion(2);
        CI.setArea(Usuario.SOFTWARE);
        CI.setDescripcion("Se debe instalar y analizar el firewall en cuestión");
        CI.setPosibleSolucion("Se utilizarán las configuraciones necesarias para la revisión del firewall");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Mantenimiento de dispositivos de red");
        CI.setPrioridad(2);
        CI.setTiempoSolucion(6);
        CI.setArea(Usuario.HARDWARE);
        CI.setDescripcion("Se debe verificar que esta funcionando correctamente los dispositivos de red");
        CI.setPosibleSolucion("Se utilizarán los reportes de errores o fallos en cuanto a los dispositivos de red se refiere");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Planeación semestral de mantenimiento al área de soporte");
        CI.setPrioridad(3);
        CI.setTiempoSolucion(6);
        CI.setArea(Usuario.SOFTWARE);
        CI.setDescripcion("Se debe verificar que esta funcionando correctamente el área de soporte");
        CI.setPosibleSolucion("Se utilizarán los reportes de errores o fallos en cuanto al área de soporte se refiere");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Mantenimiento y reconstrucción de datos");
        CI.setPrioridad(3);
        CI.setTiempoSolucion(120);
        CI.setArea(Usuario.SOFTWARE);
        CI.setDescripcion("Se debe verificar que se están transmitiendo y recibiendo los daots correctamente");
        CI.setPosibleSolucion("Se utilizarán los reportes de errores o fallos en cuanto a los datos se refiere");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Corrección de datos");
        CI.setPrioridad(3);
        CI.setTiempoSolucion(48);
        CI.setArea(Usuario.SOFTWARE);
        CI.setDescripcion("Se debe verificar si los datos están correctos");
        CI.setPosibleSolucion("Se utilizarán herramientas y configuraciones necesarias para corregir posibles errores en los datos");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Instalación de sistemas");
        CI.setPrioridad(3);
        CI.setTiempoSolucion(10);
        CI.setArea(Usuario.SOFTWARE);
        CI.setDescripcion("Se debe instalar los sistemas necesarios");
        CI.setPosibleSolucion("Se utilizará una memoria y drivers necesarios para la instalación de estos sistemas");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Reconexión de Sistemas");
        CI.setPrioridad(2);
        CI.setTiempoSolucion(5);
        CI.setArea(Usuario.SOFTWARE);
        CI.setDescripcion("Se debe verificar las conexiones que se utilizan y se inspeccionan");
        CI.setPosibleSolucion("Se utilizarán herramientas de medición para saber si ahí fallos en la red");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Cambio de Configuración de Acceso");
        CI.setPrioridad(2);
        CI.setTiempoSolucion(2);
        CI.setArea(Usuario.SOFTWARE);
        CI.setDescripcion("Se debe reconfigurar los usuarios logeados para realizarles cambio");
        CI.setPosibleSolucion("Se utilizarán ID's de programación para éste propósito");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Creación de sistemas nuevos");
        CI.setPrioridad(2);
        CI.setTiempoSolucion(1500);
        CI.setArea(Usuario.SOFTWARE);
        CI.setDescripcion("Se debe hacer una lluvia de ideas para hacer sistemas nuevos");
        CI.setPosibleSolucion("Se utilizarán ID's de programación para éste propósito");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Corrección de Sistemas");
        CI.setPrioridad(3);
        CI.setTiempoSolucion(240);
        CI.setArea(Usuario.SOFTWARE);
        CI.setDescripcion("Se analizán los reportes de errores de los sistemas para saber si se necesitan correcciones");
        CI.setPosibleSolucion("Se utilizarán ID's de programación para éste propósito");
        CI = realm.createObject(CatalogoIncidencia.class, CatalogoIncidencia.newPosition(context));
        CI.setNombre("Ampliación de sistemas");
        CI.setPrioridad(2);
        CI.setTiempoSolucion(1000);
        CI.setArea(Usuario.SOFTWARE);
        CI.setDescripcion("Se debe hacer escalable el código para poderle agregar cosas nuevas y expandirlo");
        CI.setPosibleSolucion("Se utilizarán ID's de programación para éste propósito");
        realm.commitTransaction();
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPosibleSolucion() {
        return posibleSolucion;
    }

    public void setPosibleSolucion(String posibleSolucion) {
        this.posibleSolucion = posibleSolucion;
    }

    public Integer getTiempoSolucion() {
        return tiempoSolucion;
    }

    public void setTiempoSolucion(Integer tiempoSolucion) {
        this.tiempoSolucion = tiempoSolucion;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public static ArrayList<CatalogoIncidencia> getAll(android.content.Context context){
        ArrayList<CatalogoIncidencia> catalogoIncidencias= new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<CatalogoIncidencia> realmResults = realm.where(CatalogoIncidencia.class).findAll();
        catalogoIncidencias.addAll(realmResults);
        return catalogoIncidencias;
    }

    public static int newPosition(Context context) {
        int position;
        ArrayList<CatalogoIncidencia> catalogoIncidencias = CatalogoIncidencia.getAll(context);
        position = catalogoIncidencias.size();
        return position;
    }

}

