package com.edu.pe.modelo;

public class Foro {

    private int id_foro;
    private int nro_semana;
    private int nro_seccion;
    private String fechaInicio;
    private String fechaCierre;
    private String descripcion;
    private String titulo;

    @Override
    public String toString() {
        return "Foro{" + "id_foro=" + id_foro + ", nro_semana=" + nro_semana + ", nro_seccion=" + nro_seccion + ", fechaInicio=" + fechaInicio + ", fechaCierre=" + fechaCierre + ", descripcion=" + descripcion + '}';
    }

    public int getId_foro() {
        return id_foro;
    }

    public void setId_foro(int id_foro) {
        this.id_foro = id_foro;
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

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
}
