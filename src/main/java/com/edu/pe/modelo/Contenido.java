package com.edu.pe.modelo;

public class Contenido {

    private int id_contenido;
    private String archivo;
    private int nro_semana;
    private int nro_seccion;

    public Contenido() {
    }

    @Override
    public String toString() {
        return "Contenido{" + "id_contenido=" + id_contenido + ", archivo=" + archivo + ", nro_semana=" + nro_semana + ", nro_seccion=" + nro_seccion + '}';
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public int getNro_semana() {
        return nro_semana;
    }

    public void setNro_semana(int nro_semana) {
        this.nro_semana = nro_semana;
    }

    public int getNro_seccion() {
        return nro_seccion;
    }

    public void setNro_seccion(int nro_seccion) {
        this.nro_seccion = nro_seccion;
    }

    public int getId_contenido() {
        return id_contenido;
    }

    public void setId_contenido(int id_contenido) {
        this.id_contenido = id_contenido;
    }

}
