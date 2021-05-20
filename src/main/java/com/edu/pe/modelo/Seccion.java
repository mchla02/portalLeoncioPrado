package com.edu.pe.modelo;

public class Seccion extends Curso{

    private int nroSeccion;
    private String nomProfesor;

    public Seccion() {
    }

    public int getNroSeccion() {
        return nroSeccion;
    }

    public void setNroSeccion(int nroSeccion) {
        this.nroSeccion = nroSeccion;
    }

    public String getNomProfesor() {
        return nomProfesor;
    }

    public void setNomProfesor(String nomProfesor) {
        this.nomProfesor = nomProfesor;
    }

}
