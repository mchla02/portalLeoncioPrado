package com.edu.pe.modelo;

import java.io.Serializable;

public class Profesor  extends Usuario{
    private int id_profesor;
    private String nombresProf;
    private String apellidosProf;

    public Profesor() {
    }

    public String getNombresProf() {
        return nombresProf;
    }

    public void setNombresProf(String nombresProf) {
        this.nombresProf = nombresProf;
    }

    public String getApellidosProf() {
        return apellidosProf;
    }

    public void setApellidosProf(String apellidosProf) {
        this.apellidosProf = apellidosProf;
    }

    public int getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }

    
}
