package com.edu.pe.modelo;

import java.util.List;

public class SemanaAcademica {

    private int nroSemana;
    private String semana;
    private List<Contenido> archivos;
    private List<Foro> foros;

    public SemanaAcademica() {
    }

    public int getNroSemana() {
        return nroSemana;
    }

    public void setNroSemana(int nroSemana) {
        this.nroSemana = nroSemana;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public List<Contenido> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<Contenido> archivos) {
        this.archivos = archivos;
    }

    public List<Foro> getForos() {
        return foros;
    }

    public void setForos(List<Foro> foros) {
        this.foros = foros;
    }

}
